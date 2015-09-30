package com.disruptive.storm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.apache.hadoop.hbase.client.HTableInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.disruptive.util.HBaseHelper;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

/**
 * respCode 不全为0 存入到HBase中。
 * @author Administrator
 *
 */
public class ErrorCount  extends BaseRichBolt {
	
	private static final long serialVersionUID = 1L;

	public final Logger logger = LoggerFactory.getLogger(getClass());
	
	private OutputCollector collector;
	
	public void execute(Tuple input) {
		//declarer.declare(new Fields("response", "count"));
		String line=input.getValue(1).toString();
		//String response=input.getValue(0).toString();
		String response = input.getValueByField("count").toString();
		logger.debug("存HBase的bolt收到：line={},response={}",input.getValueByField("count"),input.getValueByField("response"));
		String[] array=response.trim().split("\\|");
		String appName=array[0].trim();
		String interfraceName=array[1].trim();
		String errorCode=array[2].trim();
		logger.debug("#######存入Hbase的信息：line={},response={},appName={},interfraceName={},errorCode={}",line,response,appName,interfraceName,errorCode);
		//create 'errlog','clo','errorCode','appName','interfraceName','line','response_all'
		this.saveHbase(errorCode,appName,interfraceName,line,response);
	}

	private void saveHbase(String errorCode, String appName, String interfraceName, String line, String response) {
		List<Map<String, String>> lists=new ArrayList<Map<String,String>>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("rowKey", "rowKey_" +UUID.randomUUID().toString().replace("-", "") );
		map.put("errorCode",errorCode);
		map.put("appName",appName);
		map.put("interfraceName",interfraceName);
		map.put("line",line);
		map.put("response_all",response);
		lists.add(map);
		HBaseHelper.saveListMap("errlog", lists);
		
	}

	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("response", "count"));
		
	}
	
	public static void main(String[] args) {
    	List<Map<String,String>> lis=HBaseHelper.scan("errlog");
    	for(Map<String,String> m:lis){
    		Iterator iter = m.entrySet().iterator();
    		  while (iter.hasNext()) {
    	      Map.Entry entry = (Map.Entry) iter.next();
    		    Object key = entry.getKey();
    		    Object val = entry.getValue();
    		    System.out.println("key:"+key+" val:"+val);
    		  }
    	}
		
	}
	
	

}
