package com.disruptive.storm;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.disruptive.model.ReqHeadler;
import com.disruptive.model.ResHeadler;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class StatisticsSplitBolt extends BaseRichBolt{
	
	public static final String LOG_LEVEL = "log_level";
	public static final String TIME_STR = "time_str";
	public static final String DOMAIN_NAME = "domain_name";
	public static final String FUNCTION = "function";
	public static final String CODELINE = "code_line";
	public static final String PNAME = "pname";
	public static final String CLASSNAME = "class_name";
	public static final String APPNAME = "app_name";
	public static final String INTERFACECALL = "interface_call";
	public static final String REQRES = "req_res";
	public static final String SERVICENAME = "service_name";
	public static final String HEADER = "header";
	public static final String BODY = "body";
	public static final String OTHRE = "other";
	public static final String LINESTR = "line";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Tuple input) {
		
		String time_str=input.getString(0);
		String domain_name=input.getString(1);
		String app_name=input.getString(2);
		String req_res=input.getString(3);
		String service_name=input.getString(4);
		String header = input.getString(5);
		String line = input.getString(6);
		
		
		this.collector.emit(new Values(
				time_str,domain_name,app_name,req_res,
				service_name,header,line));
		
		this.collector.ack(input);
	}
	
	public void exe(String time_str, String domain_name, String app_name,
			String req_res, String service_name, String header, String line){
		try{
			if(req_res.equals("request")){
				ReqHeadler reqHeadler = rsReqHeadler(header);
				String behavcode = reqHeadler.getBehavCode(); // 场景码;
				String reqseqno = reqHeadler.getReqSeqNo();
				String orgnlseqno = reqHeadler.getOrgnlSeqNo();
				String biztrackno = reqHeadler.getBizTrackNo();
				String runtime = time_str;
			}else if(req_res.equals("response")){
				ResHeadler resHeadler = rsResHeadler(header);
				resHeadler.getReqSeqNo();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private ReqHeadler rsReqHeadler(String json) {
		ReqHeadler group2 = JSON.parseObject(json.trim(), ReqHeadler.class);
		return group2;
	}
	
	private ResHeadler rsResHeadler(String json) {
		ResHeadler group2 = JSON.parseObject(json.trim(), ResHeadler.class);
		return group2;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(TIME_STR, DOMAIN_NAME, APPNAME, REQRES,
				SERVICENAME, HEADER, LINESTR));
		
	}

}
