package com.disruptive.util.cache;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;


public class CacheClearSc{
	private static Scheduler sched=null;
	
	public static void init(){
		try{
			//初始化Scheduler
			sched=new StdSchedulerFactory().getScheduler();
			//定时执行
			//第一个是任务的名称，第二个是组名，第三个就是实际当任务需要执行的回调类。
			JobDetail jobDetail = new JobDetailImpl("cacheClearJob","cache",CacheClearJob.class);
			//第一个是Trigger的名称,第二个是Trigger的组名，第三个是任务开始时间，第四个是任务结束时间，第五个是重复次数
			//（使用SimpleTrigger.REPEAT_INDEFINITELY常量表示无限次）,最后一个是重复周期（单位是毫秒）,
			//触发器，这里是开始3秒后执行，之后每十秒执行一次
			SimpleTrigger simpletrigger=new SimpleTriggerImpl("simpletringger","groupsimpletringger",new Date(System.currentTimeMillis()+3000),null,SimpleTrigger.REPEAT_INDEFINITELY,10L*1000L);
			//给时间计划sched添加Job及触发器，每个sched可添加多个job及触发器
			sched.scheduleJob(jobDetail,simpletrigger);
			//开始执行时间计划
			sched.start();
		}catch(Exception e){
			
		}finally{
			
		}
	}
}
