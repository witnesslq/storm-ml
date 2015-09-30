package com.disruptive.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.disruptive.dao.LogRequestHeadlerDao;
import com.disruptive.model.LogRequestHeadler;
import com.disruptive.util.HBaseHelper;

public class LogRequestHeadlerDaoImpl implements LogRequestHeadlerDao {
	
	private static final String TABLE_NAME="tableName";

	public void save(LogRequestHeadler logRequestHeadler) throws Exception {
		//HBaseHelper.saveListMap(tableName, lists)
	}
	
	public void save(HashMap<String,String> map) throws Exception{
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		list.add(map);
		HBaseHelper.saveListMap(TABLE_NAME,list);
	}

}
