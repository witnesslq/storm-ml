package com.disruptive.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskUtil {

	private final ConcurrentMap<Object, Future<String>> taskCache = new ConcurrentHashMap<Object, Future<String>>();

	private String executionTask(final String taskName)
			throws ExecutionException, InterruptedException {
		while (true) {
			Future<String> future = taskCache.get(taskName);
			if (future == null) {
				Callable<String> task = new Callable<String>() {
					public String call() throws InterruptedException {
						return taskName;
					}
				};
				// 创建任务
				FutureTask<String> futureTask = new FutureTask<String>(task);
				future = taskCache.putIfAbsent(taskName, futureTask);
				if (future == null) {
					future = futureTask;
					futureTask.run();// 执行任务
				}
			}
			try {
				return future.get();
			} catch (CancellationException e) {
				taskCache.remove(taskName, future);
			}
		}
	}

}
