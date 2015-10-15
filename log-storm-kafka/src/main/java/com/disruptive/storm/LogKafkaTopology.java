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

	public static class KafkaWordSplitter extends BaseRichBolt {
		private static final Logger LOG =LoggerFactory
				.getLogger(KafkaWordSplitter.class);
		private static final long serialVersionUID = 886149197481637894L;
		
		private static final String LOG_LEVEL="log_level";
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
				
				if (words.length > 9
						&& words[8].trim().toLowerCase()
								.equals("interfacecall")
						&& (!words[7].trim().toLowerCase().equals("ebbc"))) {
					//LOG.info("line:{}"+line);
					collector.emit(input, new Values(str));
				} else {
					// 记录有问题的日志
				}
			}
			send(input);
		}
		
		private  synchronized void  send(Tuple input){
			   this.collector.ack(input);
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
			declarer.declare(new Fields("line"));
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
		builder.setBolt("word-splitter", new KafkaWordSplitter(), 4)
				.shuffleGrouping("kafka-reader");

		// 接口耗时统计分流
		builder.setBolt("log-interfaceSplit", new LogInterfaceSplitBlot(), 4)
				.shuffleGrouping("word-splitter");

		// 接口耗时统计
		builder.setBolt("log-interfaceCount", new LogInterfaceCountBlot(), 4)
				.fieldsGrouping("log-interfaceSplit", new Fields("word"));

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
			// Thread.sleep(60000);
			// cluster.shutdown();
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
