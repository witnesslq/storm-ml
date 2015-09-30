package com.disruptive.util.cache;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CacheClearJob implements Job{

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		CacheHelper.businessSaveTimeCache.clear();
		System.out.println("clear");
	}
	
	
}