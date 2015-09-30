package com.disruptive.util.cache;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

public class CacheHelper {
	
	static{
		
		
		
	}
	
	public static final Map<String,String> tableNameCache=new ConcurrentHashMap<String,String>();
	public static final Map<String,String> businessSaveTimeCache=new ConcurrentHashMap<String,String>();

}









