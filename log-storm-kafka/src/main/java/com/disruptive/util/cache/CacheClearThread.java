package com.disruptive.util.cache;


public class CacheClearThread implements Runnable{

	public void run() {
		CacheClearSc.init();
	}
	
}