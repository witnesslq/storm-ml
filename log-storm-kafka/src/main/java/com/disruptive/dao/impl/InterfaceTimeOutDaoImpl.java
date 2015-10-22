package com.disruptive.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.disruptive.dao.InterfaceTimeOutDao;
import com.disruptive.model.InterfaceTimeOut;
import com.disruptive.util.MySQLHelper;
import com.disruptive.util.cache.CacheHelper;

public class InterfaceTimeOutDaoImpl implements InterfaceTimeOutDao {

	private static final String TABLE_NAME = "t_interface_timeout";
	
	
	private String createTableSql(){
		
		String sql=""
		+"CREATE TABLE `"+TABLE_NAME+"_yyyyMMdd` ("
		+"		`appName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
		+"		`id`  int(11) NOT NULL AUTO_INCREMENT ,"
		+"		`serverName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
		+"		`sceneCode`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
		+"		`interfaceName`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
		+"		`timeout`  int(11) NOT NULL ,"
		+"		`trackNo`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
		+"		`rowKey`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
		+"		`reqRowKey`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
		+"		`resRowKey`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
		+"		`recordTime`  datetime NOT NULL ,"
		+"		`recordTimeStr`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
		+"		`respCode`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
		+"		`isException`  bit(1) NOT NULL DEFAULT b'0' ,"
		+"		`sceneNameEns`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,"
		+"		`orgnlSeqNo`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
		+"		`bizTrackNo`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,"
		+"		PRIMARY KEY (`id`),"
		+"		FULLTEXT INDEX `index` (`appName`, `serverName`, `sceneCode`, `interfaceName`, `trackNo`, `sceneNameEns`, `orgnlSeqNo`, `bizTrackNo`)" 
		+"		)"
		+"		ENGINE=MyISAM"
		+"		DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci"
		+"		AUTO_INCREMENT=3016"
		+"		CHECKSUM=0"
		+"		ROW_FORMAT=DYNAMIC"
		+"		DELAY_KEY_WRITE=0";
		return sql;
	}

	/**
	 * 将统计结果保存到MySQL数据库中
	 * 1.首先判断 在Cache的Map中是否存在操作的表，如果存在直接insert 数据
	 * 2.不存在 查看MySQL数据库中是否存在，如果存在则同步到Cache的Map中。
	 * 3.Cache中不存在 查看MySQL数据库中不存在，则进行建表操作，并将表明同步到Cache的Map中。
	 * 4.执行insert数据操作。
	 * 
	 */
	public void save(InterfaceTimeOut interfaceTimeOut,Date currentTime) throws Exception {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String yyyyMMdd = formatter.format(currentTime);
		String tableName = TABLE_NAME +"_"+ yyyyMMdd;
		if(CacheHelper.tableNameCache.get(tableName)==null){
			if (!MySQLHelper.dbIsHas(tableName)) {
				// 创建表
				MySQLHelper.executeUpdate(
						//XmlHelper.SQL_MAP.get(TABLE_NAME)
						createTableSql()
						.replace("yyyyMMdd", yyyyMMdd));
				CacheHelper.tableNameCache.put(tableName, "1");
			}else{
				CacheHelper.tableNameCache.put(tableName, "1");
			}
		}
		String sql="insert into "+tableName+" (appName,serverName,sceneCode,interfaceName,timeout,trackNo,rowKey,reqRowKey,resRowKey,recordTime,recordTimeStr,respCode,sceneNameEns,isException,orgnlSeqNo,bizTrackNo) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params=new Object[]{
				interfaceTimeOut.getAppName(),
				interfaceTimeOut.getServerName(),
				interfaceTimeOut.getSeneCode(),
				interfaceTimeOut.getInterfaceName(),
				interfaceTimeOut.getTimeOut(),
				interfaceTimeOut.getTrackNo(),
				interfaceTimeOut.getRowKey(),
				interfaceTimeOut.getReqRowKey(),
				interfaceTimeOut.getResRowKey(),
				interfaceTimeOut.getRecordTime(),
				interfaceTimeOut.getRecordTimeStr(),
				interfaceTimeOut.getRespCode(),
				interfaceTimeOut.getSceneNameEns(),
				interfaceTimeOut.getIsException(),
				interfaceTimeOut.getOrgnlSeqNo(),
				interfaceTimeOut.getBizTrackNo()
				
		};
		//System.out.println(sql);
		MySQLHelper.executeUpdate(sql, params);
		
	}


}
