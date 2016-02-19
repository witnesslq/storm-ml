package com.dis.hbase.indexer.client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.dis.hbase.indexer.Config;
import com.dis.hbase.indexer.lucene.CreateIndexer;
import com.dis.hbase.indexer.main.Main;
import com.dis.hbase.indexer.pb.Message;
import com.dis.hbase.indexer.util.FileUtil;


public class Client {

	public static void main(String[] args) {
		
		//query_index();
		readTxtFile();

	}
	public static void create_index() {
		//读取文件发送

	}
	

	
	
	static volatile  int ixx=0;
	
	public static void readTxtFile(){
		
		try{
			File f = new File("E:/20151001");
			int  i=0;
			for (File fs : f.listFiles()) {
				for (File fs2 : fs.listFiles()) {
					for (File file : fs2.listFiles()) {
						System.out.println(file.getAbsolutePath());
						// doc.add(new TextField("content", new
						// FileReader(file)));
						final String path=file.getAbsolutePath();
						final String encoding="UTF-8";
						ExecutorService exe=Executors.newFixedThreadPool(4);
						exe.execute(new Runnable(){

							public void run() {
								try{
									final File file=new File(path);
									try {
										
										if(file.isFile() && file.exists()){
											InputStreamReader read=new InputStreamReader(new FileInputStream(file),encoding);
											BufferedReader bufferReader = new BufferedReader(read);
											String lineTxt=null;
											while((lineTxt=bufferReader.readLine())!=null){
												try{
													Message.IndexReqMsg.Builder msg=Message.IndexReqMsg.newBuilder();
													msg.setContext(lineTxt);
													msg.setMtable("t_barry");
													msg.setWtime(lineTxt.split("\\|")[0]);
													msg.setRow(lineTxt.split("\\|")[0]+"-"+Math.random()+"asdfsadfasdf");
													msg.setIp("localhost");
												//	sendData(socketChannel, msg.build());
													Main.index_queue.put(msg.build());
													/*SocketChannel socketChannel= SocketChannel.open();
													SocketAddress socketAddress = new InetSocketAddress("localhost", Config.INDEX_LISTENER_PORT);
													//SocketAddress localAddress=new InetSocketAddress("localhost",Config.CLIENT_PORT+(i++));
													//socketChannel.bind(localAddress);
													socketChannel.connect(socketAddress);
														send_data(socketChannel,msg.build());
														receiveData(socketChannel);
														socketChannel.close();*/
													System.out.println(ixx++);
													}catch(Exception e){
														e.printStackTrace();
														continue;
													}
												
												}
												
												
											}
								}catch(Exception e){
									e.printStackTrace();
								}

							}catch(Exception e){
								e.printStackTrace();
								
							}
							}
							
						});
						
						
						
					}
				}
			}	
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			try {
			//	socketChannel.close();
			} catch (Exception ex) {
			}
		}
	}
	
	/**
	 * 远程查询
	 */

	public static void query_index() {
		SocketChannel socketChannel = null;
		try {
			socketChannel = SocketChannel.open();
			SocketAddress socketAddress = new InetSocketAddress("localhost", Config.QUERY_LISTENER_PORT);
			SocketAddress localAddress=new InetSocketAddress("localhost",Config.CLIENT_PORT);
			socketChannel.bind(localAddress);
			socketChannel.connect(socketAddress);
			
			Message.QueryReqMsg.Builder req = Message.QueryReqMsg.newBuilder();
			req.setQuery("电子账户已成功开户");
			sendData(socketChannel, req.build());
			receiveData(socketChannel);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socketChannel.close();
			} catch (Exception ex) {
			}
		}
	}

	private static void send_data(SocketChannel socketChannel, Message.IndexReqMsg msg) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(msg.toByteArray());
		socketChannel.write(buffer);
		socketChannel.socket().shutdownOutput();
	}

	public static void indexReceiveData(SocketChannel socketChannel) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			byte[] bytes;
			int count = 0;
			while ((count = socketChannel.read(buffer)) >= 0) {
				buffer.flip();
				bytes = new byte[count];
				buffer.get(bytes);
				baos.write(bytes);
				buffer.clear();
			}
			bytes = baos.toByteArray();			
			Message.IndexRepMsg.Builder builder=Message.IndexRepMsg.newBuilder();
			System.out.println(builder.getRow());
			socketChannel.socket().shutdownInput();
		} finally {
			try {
				baos.close();
			} catch (Exception e) {
			}
		}
	}

	
	static volatile int i=0;

	private static void receiveData(SocketChannel socketChannel) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			byte[] bytes;
			int count = 0;
			while ((count = socketChannel.read(buffer)) >= 0) {
				buffer.flip();
				bytes = new byte[count];
				buffer.get(bytes);
				baos.write(bytes);
				buffer.clear();
			}
			bytes = baos.toByteArray();
			Message.QueryRepMsg indexMsg = Message.QueryRepMsg.parseFrom(bytes);
			System.out.println(i++);
			socketChannel.socket().shutdownInput();
		} finally {
			try {
				baos.close();
			} catch (Exception ex) {
			}
		}
		return;
	}

	private static void sendData(SocketChannel socketChannel, Message.QueryReqMsg build) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(build.toByteArray());
		socketChannel.write(buffer);
		socketChannel.socket().shutdownOutput();

	}

}
