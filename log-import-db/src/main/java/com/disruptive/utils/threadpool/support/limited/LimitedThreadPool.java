package com.disruptive.utils.threadpool.support.limited;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.disruptive.utils.NamedThreadFactory;
import com.disruptive.utils.threadpool.ThreadPool;
import com.disruptive.utils.threadpool.support.AbortPolicyWithReport;

/**
 * 此线程池一直增长，直到上限，增长后不收缩。
 * @author Administrator
 *
 */
public class LimitedThreadPool implements ThreadPool {

	public Executor getExecutor(String id) {
		    String name = id;//url.getParameter(Constants.THREAD_NAME_KEY, Constants.DEFAULT_THREAD_NAME);
	        int cores = 10;// url.getParameter(Constants.CORE_THREADS_KEY, Constants.DEFAULT_CORE_THREADS);
	        int threads =10;// url.getParameter(Constants.THREADS_KEY, Constants.DEFAULT_THREADS);
	        int queues = 10;//url.getParameter(Constants.QUEUES_KEY, Constants.DEFAULT_QUEUES);
	        return new ThreadPoolExecutor(cores, threads, Long.MAX_VALUE, TimeUnit.MILLISECONDS, 
	        		queues == 0 ? new SynchronousQueue<Runnable>() : 
	        			(queues < 0 ? new LinkedBlockingQueue<Runnable>() 
	        					: new LinkedBlockingQueue<Runnable>(queues)),
	        		new NamedThreadFactory(name, true), new AbortPolicyWithReport(name, id));
	}

}
