package com.dis.hbase.indexer.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.dis.hbase.indexer.Config;

public class ThreadPool  {

	private ThreadPool(){}
	
	public ThreadPool(Config config){
		ThreadPool(null);
	}
	
	private static final String NEW_CACHED_THREAD_POOL="newCachedThreadPool";
	private static final String NEW_FIXED_THREAD_POOL="newFixedThreadPool";
	private static final String NEW_SCHEDULED_THREAD_POOL="newScheduledThreadPool";
	private static final String NEW_SINGLE_THREAD_EXECUTOR="newSingleThreadExecutor";
	
	
	private  ExecutorService myExecutorService;
	
	public ExecutorService getExecutorService(){
		return this.myExecutorService;
	} 
	
	
	
	static enum ThreadPoolType{
		NEWCACHEDTHREADPOOL("newCachedThreadPool",10);
		
		private String name;
		private int number;
		private int time;
		public int getTime() {
			return time;
		}

		public void setTime(int time) {
			this.time = time;
		}

		public String getName() {
			return name;
		}
		
		public int getNumber() {
			return number;
		}
		
		private ThreadPoolType(String name,int number){
			this.name=name;
			this.number=number;
			
		}	
	}
	
	private void ThreadPool(ThreadPoolType threadType){
		String name=NEW_CACHED_THREAD_POOL;
		if(NEW_CACHED_THREAD_POOL.equals(name)){
			//创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活收回空线程,若无可回收，则新建线程
			myExecutorService=Executors.newCachedThreadPool();
		}else if(NEW_FIXED_THREAD_POOL.equals(name)){
			//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
			//定长线程池的大小最好根据系统资源进行设置
			int procNum=Runtime.getRuntime().availableProcessors();
			procNum=threadType.getNumber()<procNum?threadType.getNumber():procNum;
			myExecutorService=Executors.newFixedThreadPool(procNum);
		}else if(NEW_SCHEDULED_THREAD_POOL.equals(name)){
			//创建一个定长线程池，支持定时及周期性任务执行
			myExecutorService=Executors.newScheduledThreadPool(threadType.getNumber());
		}else if(NEW_SINGLE_THREAD_EXECUTOR.equals(name)){
			//创建一个单线程化的线程池，他只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序（FIFO,LIFO,优先级）执行。
			myExecutorService = Executors.newSingleThreadExecutor();
			
		}
		
	}
}
