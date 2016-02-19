package com.dis.hbase.indexer;

public class Config {
	//临时索引文件夹
	public static String TMP_CHILD_DIR_PATH="E:/index_test_tmp";
	//主索引文件存放位置
	public static String DIR_PATH="E:/index_test";
	//查询监听TCP端口
	public static Integer QUERY_LISTENER_PORT=15526;
	//创建索引TCP监听端口
	public static Integer INDEX_LISTENER_PORT=15525;
	//测试客户端端口
	public static Integer CLIENT_PORT=16666;
	
	//多少时间清理一次 INDEX_QUEUE队列
	public static Integer FLASH_INDEX_QUEUE=10000;
	//WriterIndexer 多少个Doc文档就保存一次
	public static Integer INDEX_CREATE_DOC_SIZE=10000;
	//启动多少个ThreadCreateIndex线程
	public static Integer THREAD_CREATE_INDEX_SIZE=3;
	
	
	
	
	

}
