package com.disruptive.dao;

import com.disruptive.model.StatisticsCount;

public interface StatisticsCountDao {
	
	void save(String tableName,StatisticsCount count) throws Exception;

}
