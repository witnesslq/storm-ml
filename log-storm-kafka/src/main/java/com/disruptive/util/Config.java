package com.disruptive.util;

public  class Config {
	/**
	 * HBase config
	 */
	public static String  HBASE_ZOOKEEPER_QUORUM="";
	public static Integer HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT=2181;
	public static String HBASE_ZOOKEEPER_MASTER="";
	public static String ZOOKEEPER_ZNODE_PARENT="";
	
	/**
	 * MySQL config
	 */
	public static String MYSQL_DB_DRIVER="com.mysql.jdbc.Driver";
	public static String MYSQL_DB_URL="";
	public static String MYSQL_DB_USER="";
	public static String MYSQL_DB_PWD="";
	
	/**
	 * storm config
	 */
	public static String STORM_ZOOKEEPER_ROOT="";
	public static Integer STORM_ZOOKEEPER_PORT=2181;
	public static String NIMBUS_HOST="";
	public static Integer NIMBUS_THRIFT_PORT=6627;
	public static String STORM_TOPIC="";
	
	
	

}
