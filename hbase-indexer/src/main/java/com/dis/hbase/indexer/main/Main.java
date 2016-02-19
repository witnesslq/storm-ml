package com.dis.hbase.indexer.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.dis.hbase.indexer.Config;
import com.dis.hbase.indexer.client.Client;
import com.dis.hbase.indexer.lucene.CreateIndexer;
import com.dis.hbase.indexer.lucene.Searcher;
import com.dis.hbase.indexer.pb.Message;
import com.dis.hbase.indexer.util.ThreadPool;

public class Main {

	private static Thread threadCreateIndexListener = null;// 监听线程
	private static Thread threadQueryListener = null;// 监听查询
	private static Thread[] threadCreateIndex = new Thread[Config.THREAD_CREATE_INDEX_SIZE];// 创建索引的线程
	//private static Thread[] threadQueryDoc = null;// 查询文件的线程
	private static Config config;
	private static ExecutorService myThreadPool = new ThreadPool(config).getExecutorService();

	public static BlockingQueue<Message.IndexReqMsg> index_queue = new LinkedBlockingQueue<Message.IndexReqMsg>();

	private static String path = "";

	static List<Document> docs = new ArrayList<Document>();

	public static void main(String[] args) throws InterruptedException {
		// 解析参数
		if (args.length == 0) {
			path = System.getenv("HBASE_INDEX_CONF");
		} else {
			path = args[0];
		}
		if (path == null || ("".equals(path.trim()))) {
			System.out.println("未找到配置文件");
			// return;
		}

		// 埋关闭钩子
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("关闭钩子");
					// 关闭索引文件
					CreateIndexer.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {

				}
			}
		}));

		// 启动服务
		threadCreateIndexListener = new Thread(new Runnable() {
			public void run() {
				listener_index();
			}
		});

		threadQueryListener = new Thread(new Runnable() {
			public void run() {
				listener_query();
			}
		});

		/**
		 * 10秒执行一个一次索引建立清理
		 */
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					System.out.println("timer....");
					synchronized (docs) {
						if (docs != null && docs.size() > 0) {
							CreateIndexer.index(docs);
							docs.clear();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 1000, Config.FLASH_INDEX_QUEUE);

		/**
		 * 启动6个解析线程
		 */

		for (int i = 0; i < Config.THREAD_CREATE_INDEX_SIZE; i++) {
			final int k = i;
			threadCreateIndex[i] = new Thread(new Runnable() {
				public void run() {
					// 批量解析成DOC 保存索引
					System.out.println("启动 threadCreateIndex:" + k);
					try {
						for (int i = 0; i <= Config.INDEX_CREATE_DOC_SIZE; i++) {
							Message.IndexReqMsg msg = index_queue.take();
							Document doc = msg2Doc(msg);
							// System.out.println(msg.getRow());
							synchronized (docs) {
								docs.add(doc);
								if (i >= Config.INDEX_CREATE_DOC_SIZE) {
									CreateIndexer.index(docs);
									docs.clear();
									i = 0;
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			threadCreateIndex[i].start();
		}

		threadCreateIndexListener.start();
		threadQueryListener.start();
		System.out.println("start....");
		
		Client.readTxtFile();
		
		// stopThread();
	}

	private static Document msg2Doc(Message.IndexReqMsg msg) {
		Document doc = new Document();
		String rowKey = msg.getRow();
		String context = msg.getContext();
		String ip = msg.getIp();
		String wtime = msg.getWtime();
		String mtable = msg.getMtable();
		doc.add(new Field("ip", ip, Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field("context", context, Field.Store.NO, Field.Index.ANALYZED));
		doc.add(new Field("rowKey", rowKey, Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field("wtime", wtime, Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field("mtable", mtable, Field.Store.YES, Field.Index.NOT_ANALYZED));
		return doc;

	}

	/**
	 * 启动查询TCP监听端口
	 */
	private static void listener_query() {
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;
		try {
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.socket().setReuseAddress(true);
			serverSocketChannel.socket().bind(new InetSocketAddress(Config.QUERY_LISTENER_PORT));
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			while (selector.select() > 0) {
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey readyKey = it.next();
					it.remove();
					query_execute((ServerSocketChannel) readyKey.channel());
				}
			}
		} catch (Exception e) {

		} finally {
			try {
				selector.close();
			} catch (Exception ex) {
			}
			try {
				serverSocketChannel.close();
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * 启动创建索引TCP监听端口
	 */
	private static void listener_index() {
		Selector selector = null;
		ServerSocketChannel serverSocketChannel = null;
		try {
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.socket().setReuseAddress(true);
			serverSocketChannel.socket().bind(new InetSocketAddress(Config.INDEX_LISTENER_PORT));
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			while (selector.select() > 0) {
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey readyKey = it.next();
					it.remove();
					index_execute((ServerSocketChannel) readyKey.channel());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				selector.close();
			} catch (Exception ex) {
			}
			try {
				serverSocketChannel.close();
			} catch (Exception ex) {
			}
		}

	}

	private static void query_execute(ServerSocketChannel serverSocketChannel) throws IOException {
		SocketChannel socketChannel = null;
		try {
			socketChannel = serverSocketChannel.accept();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			byte[] bytes;
			int size = 0;
			while ((size = socketChannel.read(buffer)) >= 0) {
				buffer.flip();
				bytes = new byte[size];
				buffer.get(bytes);
				baos.write(bytes);
				buffer.clear();
			}
			bytes = baos.toByteArray();

			Message.QueryReqMsg indexMsg = Message.QueryReqMsg.parseFrom(bytes);
			// snedData
			final String query = indexMsg.getQuery();

			Message.QueryRepMsg.Builder msg = Message.QueryRepMsg.newBuilder();

			msg.setRow(QueryExecutorService(new Callable<String>() {
				public String call() throws Exception {
					return Searcher.search(query, 100);
				}
			})

			);
			msg.setStatus(1);
			sendData(socketChannel, msg.build());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			socketChannel.close();
		}
	}

	private static void index_execute(ServerSocketChannel serverSocketChannel) throws IOException {
		SocketChannel socketChannel = null;
		try {
			socketChannel = serverSocketChannel.accept();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			byte[] bytes;
			int size = 0;
			while ((size = socketChannel.read(buffer)) >= 0) {
				buffer.flip();
				bytes = new byte[size];
				buffer.get(bytes);
				baos.write(bytes);
				buffer.clear();
			}
			bytes = baos.toByteArray();
			Message.IndexReqMsg indexMsg = Message.IndexReqMsg.parseFrom(bytes);
			index_queue.put(indexMsg);
			// sendData
			Message.IndexRepMsg.Builder msg = Message.IndexRepMsg.newBuilder();
			msg.setStatus(1);
			msg.setRow("Ok");
			sendData(socketChannel, msg.build());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			socketChannel.close();
		}
	}

	private static void sendData(SocketChannel socketChannel, Message.IndexRepMsg msg) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(msg.toByteArray());
		socketChannel.write(buffer);
	}

	private static void sendData(SocketChannel socketChannel, Message.QueryRepMsg msg) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(msg.toByteArray());
		socketChannel.write(buffer);
	}

	private static void stopThread() {
		System.out.println("关闭监听....");
		/*
		 * threadCreateIndexListener.stop(); threadQueryListener.stop();
		 */

		/*
		 * if (!((threadCreateIndex.getState() == Thread.State.TERMINATED) &&
		 * (threadQueryDoc.getState() == Thread.State.TERMINATED))) {
		 * 
		 * }
		 */
		try {
			System.out.println("等待处理未处理完消息...");
			clrQueue();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 清空队列
	 * 
	 * @throws InterruptedException
	 */
	private static void clrQueue() throws InterruptedException {
		while (index_queue.size() > 0) {
			Message.IndexReqMsg msg = index_queue.take();
			// CreateIndexer.index();
		}

	}

	public static String QueryExecutorService(Callable<String> run) throws InterruptedException, ExecutionException {
		Future<String> future = myThreadPool.submit(run);
		String re = future.get();
		return re;
	}

}
