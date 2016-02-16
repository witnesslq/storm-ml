package com.disruptive.util.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheHelper {
	
	public static final Map<String,String> tableNameCache=new ConcurrentHashMap<String,String>();
	public static final Map<String,String> businessSaveTimeCache=new ConcurrentHashMap<String,String>();

}









