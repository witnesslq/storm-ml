package com.disruptive.dao;

import java.util.HashMap;
import java.util.List;

public interface TmpRequestResponseDao {
	
	public void save(Object[] params,String yyyyMMdd) throws Exception;
	
	public void delete(Object[] params,String yyyyMMdd) throws Exception;
	
	public List<HashMap<String,Object>> query(Object[] params,String yyyyMMdd) throws Exception;
	
}
