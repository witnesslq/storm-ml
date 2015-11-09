package com.disruptive.storm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.disruptive.util.Constant;
import com.disruptive.util.HBaseHelper;
import com.disruptive.util.PropertiesUtil;

import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 
 * @author barry 从kafka中获取日志属于InterfaceCall的日志，并将它发送到下一个日志处理的Bolt
 *
 */
public class LogKafkaTopology {

	public static class LogSplitter extends BaseRichBolt {
		private static final Logger LOG =LoggerFactory
				.getLogger(LogSplitter.class);
		
		private static final long serialVersionUID = 886149197481637894L;
		private OutputCollector collector;
		public void prepare(Map stormConf, TopologyContext context,
				OutputCollector collector) {
			this.collector = collector;
		}
		public void execute(Tuple input) {
			String line = input.getString(0);
			//处理前面|
			if(line.startsWith("|")){
				line=line.substring(1).trim();
			}
			String[] arraylines = line.split("\\n");
			for (String str : arraylines) {
				//是否error
				if (str.contains("ERROR") || str.contains("error")) {
					String key = System.currentTimeMillis()+"";//words[0].trim().trim() + "|"
							//  + "|"+ words[2].trim().toLowerCase() + "|"
					saveErrorLog(key, str);
				}
				//根据|拆分 
				String[] words = str.split("\\|", -1);
				if(words.length>12){
					//String log_level=words[0];
					String time_str=words[1];
					String domain_name=words[2].toLowerCase().trim();
					//String function=words[3].toLowerCase().trim();
					//String code_line=words[4];
					//String pname=words[5];
					//String class_name=words[6].toLowerCase().trim();
					String app_name=words[7].toLowerCase().trim();
					String interface_call=words[8].toLowerCase().trim();
					String service_name=words[9].toLowerCase().trim();
					String req_res=words[10].toLowerCase().trim();
					String header=words[11];
				//	String body=words[12];
					if(interface_call.endsWith("interfacecall")
							&&(!app_name.equals("ebbc"))){
						collector.emit(new Values(
								time_str,
								domain_name,
								app_name,
								req_res,
								service_name,
								header,
								line));
					}
				}
			}
		}

		/**
		 * 保存ERROR日志
		 * @param rowKey
		 * @param value
		 */
		public void saveErrorLog(String rowKey, String value) {
			List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
			Map<String, String> map = new HashMap<String, String>();
			map.put("rowKey", rowKey);
			map.put("line", value);
			lists.add(map);
			HBaseHelper.saveListMap("sys_business_error_log", lists);
		}

		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			declarer.declare(new Fields(
					Constant.TIME_STR,
					Constant.DOMAIN_NAME,
					Constant.APPNAME,
					Constant.REQRES,
					Constant.SERVICENAME,
					Constant.HEADER,
					Constant.LINESTR
					));
		}
	}

	public static class WordCounter extends BaseRichBolt {

		private static final Logger LOG = LoggerFactory.getLogger(WordCounter.class);
		private static final long serialVersionUID = 886149197481637894L;
		private OutputCollector collector;
		private Map<String, AtomicInteger> counterMap;

		public void prepare(Map stormConf, TopologyContext context,
				OutputCollector collector) {
			this.collector = collector;
			this.counterMap = new HashMap<String, AtomicInteger>();
		}

		public void execute(Tuple input) {
			String word = input.getString(0);
			int count = input.getInteger(1);
			AtomicInteger ai = this.counterMap.get(word);
			if (ai == null) {
				ai = new AtomicInteger();
				this.counterMap.put(word, ai);
			}
			ai.addAndGet(count);
			send(input);
		}
		
		private  void  send(Tuple input){
			   this.collector.ack(input);
		   }

		@Override
		public void cleanup() {
			LOG.info("The final result:");
			Iterator<Entry<String, AtomicInteger>> iter = this.counterMap
					.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, AtomicInteger> entry = iter.next();
			}
		}

		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			declarer.declare(new Fields("word", "count"));
		}
	}
	

	public static void main(String[] args) throws AlreadyAliveException,
			InvalidTopologyException, InterruptedException {
		if(args.length!=5){
			printDes();
			return ;
		}
		String zks01=args[1].trim();
		String zks02=args[2].trim();
		String zks03=args[3].trim();
		String topic = args[4];//test//wgptopic2
		String zkRoot = "/storm"; // default zookeeper root configuration for  storm
		String id = "word";
		String zks = zks01+":2181,"+zks02+":2181,"+zks03+":2181";
        //"lognn2te","lognn1te", "logrmte"
		BrokerHosts brokerHosts = new ZkHosts(zks);
		SpoutConfig spoutConf = new SpoutConfig(brokerHosts, topic, zkRoot, id);
		spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());
		spoutConf.forceFromStart = false;
		spoutConf.zkServers = Arrays.asList(new String[] { zks01,
				zks02, zks03 });
		spoutConf.zkPort = 2181;
		/**
		 * 处理流程
		 */
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("kafka-reader", new KafkaSpout(spoutConf), 1); 
		// 过滤标记为 InterfaceCall 日志
		builder.setBolt("log-splitter", new LogSplitter(), 4)
				.shuffleGrouping("kafka-reader");

		// 接口耗时统计分流
		builder.setBolt("log-interfaceSplit", new ReqCountBlot(), 4)
				.shuffleGrouping("word-splitter");

		// 接口耗时统计
		builder.setBolt("log-reqresmaping", new ReqResMapingBlot(), 4)
				.fieldsGrouping("log-interfaceSplit", new Fields(Constant.DOMAIN_NAME,Constant.SERVICENAME));

		Config conf = new Config();
		// conf.setDebug(true);
		String name = LogKafkaTopology.class.getSimpleName();
		conf.setNumAckers(5);
		if (args != null && args.length > 0) {
			// Nimbus host name passed from command line
			int numWorker=Integer.parseInt(args[0].trim());
			conf.put(Config.NIMBUS_HOST,
					PropertiesUtil.getPropertyString("nimbus.host"));
			conf.setNumWorkers(numWorker);
			
			StormSubmitter.submitTopologyWithProgressBar(name, conf,
					builder.createTopology());
		} else {
			conf.setMaxTaskParallelism(3);
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology(name, conf, builder.createTopology());
			Thread.sleep(60000);
			cluster.shutdown();
		}
	}
	
	public static void printDes(){
		System.out.println();
		System.out.println();
		System.out.println("===========================");
		System.out.println();
		System.out.println("===params:");
		System.out.println("{numWorker} {zks01} {zks02} {zks03} {topic} ");
		System.out.println("numWorker:StringConfig->NumWorkers");
		System.out.println("zks01:ZooKeeper-> zk01");
		System.out.println("zks02:ZooKeeper-> zk02");
		System.out.println("zks03:ZooKeeper-> zk03");
		System.out.println("topic:SpoutConfig->topic");
		System.out.println();
		System.out.println("===========================");
		System.out.println();
		
	}

}
