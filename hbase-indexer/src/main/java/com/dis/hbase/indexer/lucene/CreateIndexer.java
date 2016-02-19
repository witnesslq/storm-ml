package com.dis.hbase.indexer.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.dis.hbase.indexer.Config;
import com.dis.hbase.indexer.util.FileUtil;

public class CreateIndexer {

	public static Directory directory = null;
	public static IndexWriterConfig m_iwc = null;
	private static IndexWriter m_writer = null;

	private static BlockingQueue<List<Document>> queue = new LinkedBlockingQueue<List<Document>>();
	private static ExecutorService executor=Executors.newFixedThreadPool(4);

	private static List<String> downDir = new ArrayList<String>();

	private static volatile int runSize = 0;

	static {
		try {
			directory = FSDirectory.open(new File(Config.DIR_PATH).toPath());
			m_iwc = new IndexWriterConfig(new StandardAnalyzer());
			m_writer = new IndexWriter(directory, m_iwc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 检测队列
		//MutiIndexRun(3);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			
			@Override
			public void run() {
				if (runSize == 0) {
					System.out.println(runSize+"============");
					synchronized (queue) {
						if (queue != null && queue.size() > 0) {
							MutiIndexRun(3);
						}
					}
				}
			}
		}, 100, 60000);
	}

	/**
	 * 多个线程执行
	 * 
	 */
	public static void MutiIndexRun(int size) {
		
		for (int i = 0; i < size; i++) {
			System.out.println("MutiIndexRun....."+i);
			new Thread(new Runnable() {
				final Thread current = Thread.currentThread();
				boolean isDown = false;
				{
					Timer t = new Timer();
					t.schedule(new TimerTask() {

						@Override
						public void run() {
							isDown = true;
							// current.interrupt();
						}
					}, 4000);
				}

				public void run() {
					runSize++;
					IndexWriter indexWr = CreateIndexer.getDirectory(CreateIndexer.getThreadKey(Thread.currentThread()));
					try {
						while (!isDown) {
							synchronized (queue) {
								CopyOnWriteArrayList<Document> list=new CopyOnWriteArrayList<Document>(queue.take()) ;
								if(list!=null && list.size()>0){
									indexWr.addDocuments(list);
								}
								indexWr.commit();
							}
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if(indexWr!=null){
								indexWr.close();
							}
							synchronized (downDir) {
								downDir.add(CreateIndexer.getThreadKey(current));
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						runSize--;
					}
				}
			}).start();
		}
	}

	public static String getThreadKey(Thread t) {
		return Config.TMP_CHILD_DIR_PATH+"/"+t.getId() + "_" + t.getName();
	}

	// 启动合并线程
	public static void margeThreadStart() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				synchronized (downDir) {
					if (downDir != null && downDir.size() > 0) {
						for (String dirtmp : downDir) {
							CreateIndexer.margeIndex(dirtmp, CreateIndexer.getThreadKey(Thread.currentThread()));
						}
						downDir.clear();
					}
				}
			}

		}, 1000, 60000);
	}

	/**
	 * 获取IndexWriter
	 * 
	 * @param path
	 * @return
	 */
	public static IndexWriter getDirectory(String path) {
		Directory dir = null;
		IndexWriterConfig iwc = null;
		try {
			File folder=new File(path);
			if(!(folder.exists() && folder.isDirectory())) {
				folder.mkdirs();
			}
			dir = FSDirectory.open(folder.toPath());
			iwc= new IndexWriterConfig(new StandardAnalyzer());
			IndexWriter index=new IndexWriter(dir, iwc);
			return index;
		} catch (Exception e) {
			e.printStackTrace();
			//创建文件
			
		} finally {

		}
		return null;
	}

	public static void close() {
		try {
			if (m_writer != null) {
				m_writer.close();
			}
		} catch (Exception e) {

		} finally {
			if (directory != null) {
				try {
					directory.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		index("", "", "", "", "");
	}

	/**
	 * 写主索引文件
	 * 
	 * @param docs
	 * @throws Exception
	 */
	public static void index(List<Document> docs) throws Exception {
		queue.put(docs);
		System.out.println("put....");
		/*synchronized (m_writer) {
			try {
				m_writer.addDocuments(docs);
			} catch (Exception e) {

			} finally {
				m_writer.commit();
			}
		}*/
	}

	public static void index(String row, String content, String time, String tableName, String type) {
		// IndexWriter writer = null;
		try {
			// writer = new IndexWriter(directory, iwc);
			// 创建Doc
			Document doc = null;

			File f = new File("E:/20151001");
			for (File fs : f.listFiles()) {
				for (File fs2 : fs.listFiles()) {
					for (File file : fs2.listFiles()) {
						System.out.println(file.getAbsolutePath());
						doc = new Document();
						// doc.add(new TextField("content", new
						// FileReader(file)));
						FileUtil.readTxtFile(file.getAbsolutePath());
						/*
						 * doc.add(new Field("filename", file.getName(),
						 * Field.Store.YES, Field.Index.NOT_ANALYZED));
						 * doc.add(new Field("xx", file.getAbsolutePath(),
						 * Field.Store.YES, Field.Index.NOT_ANALYZED));
						 * writer.addDocument(doc);
						 */
					}
				}
			}
			//

			/*
			 * { doc=new Document(); doc.add(new
			 * Field("content",content,Field.Store.NO,Field.Index.ANALYZED));
			 * doc.add(new
			 * Field("row",row,Field.Store.NO,Field.Index.ANALYZED));
			 * writer.addDocument(doc); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close();
		}

	}

	/**
	 * 清空回收站
	 */
	public static void forceDelete() {
		try {
			m_writer.forceMergeDeletes();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

	}

	/**
	 * 删除文档 到回收站
	 * 
	 * @param row
	 */
	public static void delete(String row) {
		try {

			m_writer.deleteDocuments(new Term("rowKey", row));

		} catch (Exception e) {

		} finally {

		}
	}

	public static void undelete() {

	}

	public static void margeIndex(String from, String to) {
		IndexWriter indexWriter = null;
		Directory d1 = null;
		Directory d2 = null;
		IndexWriterConfig iwc = null;
		try {
			d1 = FSDirectory.open(new File(from).toPath());
			d2 = FSDirectory.open(new File(to).toPath());
			System.out.println("正在合并索引....");
			iwc = new IndexWriterConfig(new StandardAnalyzer());
			indexWriter = new IndexWriter(d2, iwc);
			indexWriter.addIndexes(d1);
			indexWriter.commit();// 提交索引
			System.out.println("索引合并完成");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				indexWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
