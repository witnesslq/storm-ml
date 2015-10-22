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
			.getLogger(BaseRichBolt.class);
	
	private static final String APP_NAME="appName";
	private static final String SERVER_NAME="serverName";
	private static final String SCENE_CODE="sceneCode";
	private static final String TRACK_NO="trackNo";
	private static final String ORGNL_SEQ_NO="orgnlSeqNo";
	private static final String BIZ_TRACK_NO="bizTrackNo";
	private static final String INTERFACE_NAME="interfaceName";
	private static final String RUN_TIME="runtime";
	private static final String RESPCODE="respCode";
	private static final String REQRES="reqRes";
	
	
	
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
		}
		
		if(line.startsWith("|")){
			line=line.substring(1).trim();
		}
		String[] words=line.split("\\|",-1);
		
		if(words.length<12){
			collector.emit(input,new Values(line,words));
			collector.ack(input);
			return;
		}
		//解析逻辑
		//
		
		
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(
				"lines",
				APP_NAME,
				SERVER_NAME,
				SCENE_CODE,
				TRACK_NO,
				ORGNL_SEQ_NO,
				BIZ_TRACK_NO,
				INTERFACE_NAME,
				RUN_TIME,
				RESPCODE,
				REQRES
				));
		
	}

}
