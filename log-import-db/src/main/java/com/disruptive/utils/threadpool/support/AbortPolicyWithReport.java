package com.disruptive.utils.threadpool.support;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbortPolicyWithReport extends ThreadPoolExecutor.AbortPolicy {

	protected static final Logger logger = LoggerFactory.getLogger(AbortPolicyWithReport.class);
	
	private final String threadName;
	private final String id;
	
	public AbortPolicyWithReport(String threadName,String id){
		this.threadName=threadName;
		this.id=id;
	}
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        String msg = 
        		String.format("Thread pool is EXHAUSTED!" +
                " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d)," +
                " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s), in %s!" ,
                threadName, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(),
                e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating(),
                id);
        logger.warn(msg);
        throw new RejectedExecutionException(msg);
    }
}
