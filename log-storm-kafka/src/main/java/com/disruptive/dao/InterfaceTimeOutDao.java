package com.disruptive.dao;

import java.util.Date;

import com.disruptive.model.InterfaceTimeOut;

public interface InterfaceTimeOutDao {
	
	void save(InterfaceTimeOut interfaceTimeOut ,Date currentTime) throws Exception;
	
	

}
