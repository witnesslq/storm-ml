package com.disruptive.util.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.disruptive.model.InterfaceTimeOut;

/**
 * MySQL入库缓冲队列
 * @author Administrator
 *
 */
public class InterfaceTimeOutQueue {

	private static ConcurrentLinkedQueue<InterfaceTimeOut> queue = new ConcurrentLinkedQueue<InterfaceTimeOut>();

	private static int count = 2;// 线程数
	//private static CountDownLatch latch = new CountDownLatch(count);

	public static void offer(InterfaceTimeOut interfaceTimeOut) {
		queue.offer(interfaceTimeOut);
	}

	public static void init() throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(4);
		for (int i = 0; i < count; i++) {
			es.submit(new Poll());
		}
		//latch.await();
	}

	private static long startTime = System.currentTimeMillis();

	static class Poll implements Runnable {
		public void run() {
			while (true) {
				if (queue.size() > 500) {
					List<InterfaceTimeOut> array = new ArrayList<InterfaceTimeOut>();
					for (int i = 0; i < 500; i++) {
						if (!queue.isEmpty()) {
							array.add(queue.poll());
						}
					}
					insertInterfaceTimeOut(array);
				} else if (System.currentTimeMillis() - startTime > 5000) {
					List<InterfaceTimeOut> array = new ArrayList<InterfaceTimeOut>();
					for(int i=0;i<500;i++){
						if (!queue.isEmpty()) {
							array.add(queue.poll());
						}
					}
					insertInterfaceTimeOut(array);
					startTime=System.currentTimeMillis();
				}
			}
		}

		static int  j=0;
		public static void insertInterfaceTimeOut(
				List<InterfaceTimeOut> array) {
			j++;
	        for(int i=0;i<array.size();i++){
	        	
	        	System.out.println(j+"----------"+array.get(i).getId());
	        }

		}

	}

}
