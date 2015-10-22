package com.disruptive.storm;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EbbcBlot extends BaseRichBolt {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  Logger LOG =LoggerFactory
			.getLogger(EbbcBlot.class);
	
	private static final String TIME_STR="time_str";
	private static final String DOMAIN_NAME="domain_name";
	private static final String FUNCTION="function";
	private static final String PID="pid";
	private static final String PNAME="pname";
	private static final String CLASSNAME="class_name";
	private static final String APPNAME="app_name";
	private static final String INTERFACECALL="interface_call";
	private static final String REQRES="req_res";
	private static final String SERVICENAME="service_name";
	private static final String OTHRE="OTHRER";
	private OutputCollector collector;
	
	
	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector=collector;
		
	}

	@Override
	public void execute(Tuple input) {
		String line=input.getValue(1).toString().trim();
		if(!StringUtils.isNotBlank(line)){
			collector.emit(input,new Values(line,""));
			collector.ack(input);
			LOG.info("==消息NULL");
			return;
		}
		
		if(line.startsWith("|")){
			line=line.substring(1).trim();
		}
		String[] words=line.split("\\|",-1);
		
		if(words.length<12){
			collector.emit(input,new Values(line,words));
			collector.ack(input);
			LOG.info("==Message格式不是标准格式,msg=={}",line);
			return;
		}
		//解析逻辑
		//
		String time_str=words[0].trim();
		String domain_name=words[1].trim();
		String function=words[2].trim();
		String pid=words[3].trim();
		String pname=words[4].trim();
		String class_name=words[5].trim();
		String app_name=words[6].trim();
		String interfaceCall=words[7].trim();
		String reqres=words[8].trim();
		String service_name=words[9].trim();
		String other=line.substring(line.indexOf("|", 10));
	    collector.emit(input,new Values(
	    		time_str,
	    		domain_name,
	    		function,
	    		pid,
	    		pname,
	    		class_name,
	    		app_name,
	    		interfaceCall,
	    		reqres,
	    		service_name,
	    		other
	    		));
	    collector.ack(input);
	    
	    LOG.info("Successed");
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(
				TIME_STR,
				DOMAIN_NAME,
				FUNCTION,
				PID,
				PNAME,
				CLASSNAME,
				APPNAME,
				INTERFACECALL,
				REQRES,
				SERVICENAME,
				OTHRE
				));
		
	}

}
