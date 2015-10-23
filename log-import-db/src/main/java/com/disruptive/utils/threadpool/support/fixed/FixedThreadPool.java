package com.disruptive.utils.threadpool.support.fixed;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.disruptive.utils.NamedThreadFactory;
import com.disruptive.utils.threadpool.ThreadPool;
import com.disruptive.utils.threadpool.support.AbortPolicyWithReport;

/**
 * 此线程池启动时即创建固定大小的线程数，不做任务伸缩
 * 来源：Executors.newFixedThreadPool()
 * @author Administrator
 *
 */
public class FixedThreadPool implements ThreadPool {

	public Executor getExecutor(String id) {
		 String name =id; //url.getParameter(Constants.THREAD_NAME_KEY, Constants.DEFAULT_THREAD_NAME);
	        int threads = 10;// url.getParameter(Constants.THREADS_KEY, Constants.DEFAULT_THREADS);
	        int queues = 10;//url.getParameter(Constants.QUEUES_KEY, Constants.DEFAULT_QUEUES);
	        return new ThreadPoolExecutor(threads, threads, 0, TimeUnit.MILLISECONDS, 
	        		queues == 0 ? new SynchronousQueue<Runnable>() : 
	        			(queues < 0 ? new LinkedBlockingQueue<Runnable>() 
	        					: new LinkedBlockingQueue<Runnable>(queues)),
	        		new NamedThreadFactory(name, true), new AbortPolicyWithReport(name, id));
	}
	

}
