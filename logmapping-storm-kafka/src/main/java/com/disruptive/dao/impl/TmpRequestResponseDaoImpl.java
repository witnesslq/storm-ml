package com.disruptive.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.disruptive.dao.TmpRequestResponseDao;
import com.disruptive.util.MySQLHelper;
import com.disruptive.util.cache.CacheHelper;

public class TmpRequestResponseDaoImpl implements TmpRequestResponseDao {

	private static final String TABLE_NAME="tmp_request_response";
	
	private String createTableSql(String yyyyMMdd){
		StringBuilder sql=new StringBuilder();
		sql.append(" CREATE TABLE `"+TABLE_NAME+"_"+yyyyMMdd+"` ( ");
		sql.append("		`id`  varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' , ");
		sql.append("		`msg`  varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,");
		sql.append("		`behavcode`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,");
		sql.append("        `reqseqno`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,");
		sql.append("		`orgnlseqno`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,");
		sql.append("		`biztrackno`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,");
		sql.append("		`runtime`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,");
		sql.append("		`respcode`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,");
		sql.append("		PRIMARY KEY (`id`),");
		sql.append("		INDEX `runtime_index` USING BTREE (`runtime`) ");
		sql.append("		) ");
		sql.append("		ENGINE=MyISAM ");
		sql.append("		DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci ");
		sql.append("		CHECKSUM=0");
		sql.append("		ROW_FORMAT=DYNAMIC");
		sql.append("	DELAY_KEY_WRITE=0 ;");
				
		return sql.toString();
	}
	
	@Override
	public void save(Object[] params, String yyyyMMdd) throws Exception {
		
		String tableName=TABLE_NAME+"_"+yyyyMMdd;
		if(CacheHelper.tableNameCache.get(tableName)==null){
			if(!MySQLHelper.dbIsHas(tableName)){
				MySQLHelper.executeUpdate(createTableSql(yyyyMMdd));
				CacheHelper.tableNameCache.put(tableName, "1");
			}else{
				CacheHelper.tableNameCache.put(tableName, "1");
			}
		}
		String sql="insert into "+tableName+" (id,msg,behavcode,reqseqno,orgnlseqno,biztrackno,runtime,respcode) values(?,?,?,?,?,?,?,?)";
		MySQLHelper.executeUpdate(sql, params);
	}

	@Override
	public void delete(Object[] params, String yyyyMMdd) throws Exception {
		String tableName=TABLE_NAME+"_"+yyyyMMdd;
		if(CacheHelper.tableNameCache.get(tableName)==null){
			if(!MySQLHelper.dbIsHas(tableName)){
				return;
			}else{
				CacheHelper.tableNameCache.put(tableName, "1");
			}
		}
		
		String sql="delete from "+tableName+" where  id = ? ";
		MySQLHelper.executeUpdate(sql,params);
		
	}

	@Override
	public List<HashMap<String, Object>> query(Object[] params, String yyyyMMdd)
			throws Exception {
		String tableName=TABLE_NAME+"_"+yyyyMMdd;
		if(CacheHelper.tableNameCache.get(tableName)==null){
			if(!MySQLHelper.dbIsHas(tableName)){
				return null;
			}else{
				CacheHelper.tableNameCache.put(tableName, "1");
			}
		}
		
		String sql=" select * from "+tableName+" where id=? ";
		return MySQLHelper.ExecuteQuery(sql, params);
	}
	
	
	

}
