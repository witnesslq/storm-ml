package com.disruptive.utils.threadpool.support.cached;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.disruptive.utils.threadpool.ThreadPool;
import com.disruptive.utils.threadpool.support.AbortPolicyWithReport;
import com.disruptive.utils.NamedThreadFactory;

/**
 * 此线程池可伸缩，线程空闲一分钟后回收，新请求重新创建线程
 * 来源：Executors.newCachedThreadPool()
 * @author Administrator
 *
 */
public class CachedThreadPool implements ThreadPool {

    public Executor getExecutor(String id) {
        String name = id; //url.getParameter(Constants.THREAD_NAME_KEY, Constants.DEFAULT_THREAD_NAME);
        int cores = 10;//url.getParameter(Constants.CORE_THREADS_KEY, Constants.DEFAULT_CORE_THREADS);
        int threads =10; //url.getParameter(Constants.THREADS_KEY, Integer.MAX_VALUE);
        int queues = 10; //url.getParameter(Constants.QUEUES_KEY, Constants.DEFAULT_QUEUES);
        int alive =1;// url.getParameter(Constants.ALIVE_KEY, Constants.DEFAULT_ALIVE);
        
        return new ThreadPoolExecutor(cores, threads, alive, TimeUnit.MILLISECONDS, 
        		queues == 0 ? new SynchronousQueue<Runnable>() : 
        			(queues < 0 ? new LinkedBlockingQueue<Runnable>() 
        					: new LinkedBlockingQueue<Runnable>(queues)),
        		new NamedThreadFactory(name, true), new AbortPolicyWithReport(name, id));
    }

}
