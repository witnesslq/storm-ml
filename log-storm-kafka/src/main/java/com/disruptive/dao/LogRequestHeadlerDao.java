package com.disruptive.dao;


import java.util.HashMap;

import com.disruptive.model.LogRequestHeadler;

public interface LogRequestHeadlerDao {
	
	void save(LogRequestHeadler logRequestHeadler) throws Exception;
	
	void save(HashMap<String,String> map) throws Exception;
	

}
