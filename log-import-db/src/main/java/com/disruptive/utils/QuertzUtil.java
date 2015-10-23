package com.disruptive.utils;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class QuertzUtil {

	private static SchedulerFactory factory = new StdSchedulerFactory();

	private static String JOB_GROUP_NAME = "group1";
	private static String TRIGGER_GROUP_NAME = "trigger1";

	public static void addJob(String jobName, Job job, String time)
			throws Exception {
		Scheduler sched = factory.getScheduler();
		JobDetail jobDetail = JobBuilder.newJob(job.getClass())
				.withIdentity(jobName, JOB_GROUP_NAME).build();

		// 触发器
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(
						TriggerKey.triggerKey("myTrigger", TRIGGER_GROUP_NAME))
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInMilliseconds(2000)
								.repeatForever()).startNow().build();
		
		sched.scheduleJob(jobDetail, trigger);
		//启动调度
		if(!sched.isShutdown()){
			sched.start();
		}
		

	}
}
