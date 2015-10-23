package com.disruptive.utils.threadpool;

import java.util.concurrent.Executor;

public interface ThreadPool {
	
	Executor getExecutor(String id);

}
