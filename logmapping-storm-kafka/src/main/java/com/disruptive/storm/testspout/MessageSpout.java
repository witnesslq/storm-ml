package com.disruptive.storm.testspout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class MessageSpout extends BaseRichSpout {

	private static final long serialVersionUID = 2116820671178094755L;
	public final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String[] lines;
	
	private SpoutOutputCollector collector;
	
	public MessageSpout(){
		logger.info("################################################测试数据初始化");
		//|时间戳|日志等级|appName|服务器名|进程或线程ID（名）|接口服务码|报文标识(request/response)|报文头(json)|报文体(报文头(json))|自定义字段|换行
		lines = new String[]{
				//"08:50:16.075|INFO|ebuisit03.scrcu.com|requestLogger|14|DubboServerHandler-10.16.10.60:20880-thread-400|SYSLOG|EBUI|InterfaceCall|ebui_oim_usr_0007|Request|{\"userId\":\"40384724249368431038191853586482\",\"servcId\":\"ebui_oim_usr_0007\"}|{\"randomNo\":\"f13d5249-3a57-4d9e-8fbd-7b4effa2754a\"}",
		        //"08:50:16.085|INFO|ebuisit03.scrcu.com|responseLogger|19|DubboServerHandler-10.16.10.60:20880-thread-400|SYSLOG|EBUI|InterfaceCall|ebui_oim_usr_0007|Response|{\"respCode\":\"0000000000\",\"returnTime\":1441587016085,\"serverIp\":\"10.16.10.60\",\"respMsg\":\"成功\"}|{}",
				//"08:50:17.109|INFO|ebuisit03.scrcu.com|requestLogger|14|DubboServerHandler-10.16.10.60:20880-thread-400|SYSLOG|EBUI|InterfaceCall|ebui_oim_usr_0008|Request|{\"servcId\":\"ebui_oim_usr_0008\"}|{\"randomNo\":\"f13d5249-3a57-4d9e-8fbd-7b4effa2754a\"}",
                //"08:50:17.111|INFO|ebuisit03.scrcu.com|responseLogger|19|DubboServerHandler-10.16.10.60:20880-thread-400|SYSLOG|EBUI|InterfaceCall|ebui_oim_usr_0008|Response|{\"respCode\":\"0000000000\",\"returnTime\":1441587017111,\"serverIp\":\"10.16.10.60\",\"respMsg\":\"成功\"}|{\"userId\":\"40384724249368431038191853586482\",\"token\":\"92693546879384416705366382951152\"}",
				"08:51:37.028|INFO|ebuisit03.scrcu.com|requestLogger|14|DubboServerHandler-10.16.10.60:20880-thread-400|SYSLOG|EBUI|InterfaceCall|ebui_oam_aun_0002|Request|{\"userCertLevel\":\"05\",\"appId\":\"EBCC\",\"hostIp\":\"10.16.9.251\",\"userId\":\"UR000000071063\",\"chnlId\":\"06\",\"reqTime\":1441587097016,\"reqSeqNo\":\"EBCC1509070002796720\",\"termnId\":\"06\",\"orgnlSeqNo\":\"ebgb1509060000031545\",\"bizTrackNo\":\"ebgb1509060000031545\",\"behavCode\":\"TRAN_LIMT_0002\",\"servcId\":\"ebui_oam_aun_0002\"}|"
						+ "{\"@EbccSvcSrlNo\":\"EBCC1509070003047948\",\"cert1\":\"5CD4F5D957D5093E5C0E3269737FCC0BE970F064A8AB4F62BE79197D0BC1B254C2EFB038AD67769F6C79FADCE79B5CF663A781D6824715D3D182E2618E465A07AC09D7DA07E7810A8BCF618FC9F75F81C2A50FFA73261B4449592787805D3F7796E41C044D7F\",\"authComb\":[{\"cert\":\"5CD4F5D957D5093E5C0E3269737FCC0BE970F064A8AB4F62BE79197D0BC1B254C2EFB038AD67769F6C79FADCE79B5CF663A781D6824715D3D182E2618E465A07AC09D7DA07E7810A8BCF618FC9F75F81C2A50FFA73261B4449592787805D3F7796E41C044D7F\",\"authType\":\"04\"}],\"tranCode\":\"ebbc_lmt_mfy_0002\",\"authSeqNo\":\"32517\",\"bizType\":\"02\",\"authType1\":\"04\"}\n"+
                "08:51:37.044|INFO|ebuisit03.scrcu.com|responseLogger|19|DubboServerHandler-10.16.10.60:20880-thread-400|SYSLOG|EBUI|InterfaceCall|ebui_oam_aun_0002|Response|{\"respCode\":\"EBUI020001\",\"returnTime\":1441587097043,\"serverIp\":\"10.16.10.60\",\"respMsg\":\"支付密码错误，您还可以输入2次！\"}|{\"remainTimes\":\"2\",\"authType\":\"04\"}",
		};
	}
	
	private Random random = new Random();
	


	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		logger.warn("################################################open() method invoked");
		this.collector = collector;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		logger.warn("################################################declareOutputFields() method invoked");
		declarer.declare(new Fields("line"));
	}

	public void nextTuple() {
		logger.warn("################################################nextTuple() method invoked");
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int i  = random.nextInt(lines.length);
		logger.info("i=========={}",i);
		String line = lines[i];
		collector.emit(new Values(line));
	}
	
}
