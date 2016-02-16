package com.disruptive.storm;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.commons.lang.StringUtils;

import com.disruptive.util.PropertiesUtil;

import storm.kafka.BrokerHosts;
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
public class LogMappingTopology {

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

	public static class LogSplitter extends BaseRichBolt {
		/*
		 * private static final Logger LOG = LoggerFactory
		 * .getLogger(LogSplitter.class);
		 */

		private static final long serialVersionUID = 886149197481637894L;
		private OutputCollector collector;

		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			this.collector = collector;
		}

		private void emit(String str) {
			if (str.contains("InterfaceCall")) {
				if (str.startsWith("|")) {
					str = str.substring(1).trim();
				}
				String[] words = str.split("\\|", -1);
				if (words.length > 11) {
					// System.out.println("===========" + str);
					// String log_level=words[0];
					String time_str = words[1];
					String domain_name = words[2].toLowerCase().trim();
					// String function=words[3].toLowerCase().trim();
					// String code_line=words[4];
					// String pname=words[5];
					// String class_name=words[6].toLowerCase().trim();
					String app_name = words[7].toLowerCase().trim();
					// String interface_call =
					// words[8].toLowerCase().trim();
					String service_name = words[9].toLowerCase().trim();
					String req_res = words[10].toLowerCase().trim();
					String header = words[11];
					// String body=words[12];
					if(StringUtils.isNotBlank(app_name)){
						collector.emit(new Values(time_str, domain_name, app_name, req_res, service_name, header, str));
					}
				}
			}
		}

		@Override
		public void execute(Tuple input) {
			String line = input.getString(0);
			emit(line);
			/*String[] arraylines = line.split("\\n");
			if (arraylines.length > 0) {
				for (String str : arraylines) {
					emit(str);
				}
			} else {
				if (StringUtils.isNotBlank(line)) {
					emit(line);
				}
			}*/
			this.collector.ack(input);
		}
		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			declarer.declare(new Fields(TIME_STR, DOMAIN_NAME, APPNAME, REQRES, SERVICENAME, HEADER, LINESTR));
		}
	}

	public static class Mapping extends BaseRichBolt {

		/*
		 * private static final Logger LOG = LoggerFactory
		 * .getLogger(Mapping.class);
		 */
		private static final long serialVersionUID = 886149197481637894L;
		private OutputCollector collector;

		private ReqResMaping reqResMaping;
		private Producer<String, String> producer;

		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			this.collector = collector;
			this.reqResMaping = new ReqResMaping();
			
			Properties props = new Properties();
			// 指定kafka节点：注意这里无需指定集群中所有Boker，只要指定其中部分即可，它会自动取meta信息并连接到对应的Boker节点
			props.put("metadata.broker.list", PropertiesUtil.getPropertyString("metadata.broker.list"));
			// 指定采用哪种序列化方式将消息传输给Boker,你也可以在发送消息的时候指定序列化类型，不指定则以此为默认序列化类型
			props.put("serializer.class", "kafka.serializer.StringEncoder");
			// 指定消息发送对应分区方式，若不指定，则随机发送到一个分区，也可以在发送消息的时候指定分区类型。
			// props.put("partitioner.class",
			// "example.producer.SimplePartitioner");
			// 该属性表示你需要在消息被接收到的时候发送ack给发送者。以保证数据不丢失
			props.put("request.required.acks", "1");
			ProducerConfig config = new ProducerConfig(props);
			// 申明生产者：泛型1为分区key类型，泛型2为消息类型
			producer = new Producer<String, String>(config);
		}

		public void execute(Tuple input) {

			String time_str = input.getString(0);
			String domain_name = input.getString(1);
			String app_name = input.getString(2);
			String req_res = input.getString(3);
			String service_name = input.getString(4);
			String header = input.getString(5);
			String line = input.getString(6);
			reqResMaping.execute(time_str, domain_name, app_name, req_res, service_name, header, line);
			// this.collector.emit(new Values(line));
			writeKafka(line, app_name);
			this.collector.ack(input);
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			// declarer.declare(new Fields("line"));
		}

		private void writeKafka(String msg, String msgType) {
			/*
			 * Properties props = new Properties(); //
			 * 指定kafka节点：注意这里无需指定集群中所有Boker，只要指定其中部分即可，它会自动取meta信息并连接到对应的Boker节点
			 * props.put("metadata.broker.list", "172.17.1.163:9093"); //
			 * 指定采用哪种序列化方式将消息传输给Boker,你也可以在发送消息的时候指定序列化类型，不指定则以此为默认序列化类型
			 * props.put("serializer.class", "kafka.serializer.StringEncoder");
			 * // 指定消息发送对应分区方式，若不指定，则随机发送到一个分区，也可以在发送消息的时候指定分区类型。
			 * //props.put("partitioner.class",
			 * "example.producer.SimplePartitioner"); //
			 * 该属性表示你需要在消息被接收到的时候发送ack给发送者。以保证数据不丢失
			 * props.put("request.required.acks", "1"); ProducerConfig config =
			 * new ProducerConfig(props); // 申明生产者：泛型1为分区key类型，泛型2为消息类型
			 * Producer<String, String> producer = new Producer<String,
			 * String>(config);
			 */
			// 创建KeyedMessage发送消息，参数1为topic名，参数2为分区名（若为null则随机发到一个分区），参数3为消息
			producer.send(new KeyedMessage<String, String>(msgType.toLowerCase().trim(), msg));
		}

		@Override
		public void cleanup() {
			producer.close();
		}
	}

	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, InterruptedException {

		TopologyBuilder builder = new TopologyBuilder();
		Config conf = new Config();
		conf.setDebug(true);
		String name = LogMappingTopology.class.getSimpleName();
//nblogsearch06.scrcu.com,nblogsearch04.scrcu.com,nbbasemonitor.scrcu.com
		String zks01 = "nblogsearch06.scrcu.com";
		String zks02 = "nblogsearch04.scrcu.com";
		String zks03 = "nbbasemonitor.scrcu.com";
		String topic = "business_log";// test//wgptopic2
		String zkRoot = "/storm";
		String id = "word4";
		
		
		
		String zks = zks01 + ":2181," + zks02 + ":2181," + zks03 + ":2181";
		// mapping 并发度
		int mapping_parallelism_hint = 4;

		boolean is_cluster = (args != null && args.length > 0);

		if (is_cluster) {
			int numWorker = Integer.parseInt(args[0].trim());
			zks01 = args[1].trim();
			zks02 = args[2].trim();
			zks03 = args[3].trim();
			topic = args[4];
			id = "word";
			zks = zks01 + ":2181," + zks02 + ":2181," + zks03 + ":2181";

			conf.put(Config.NIMBUS_HOST, PropertiesUtil.getPropertyString("nimbus.host"));
			// 设置工作进程
			// 设置工作进程 , 需要在storm.yaml下添加端口 ，一个进程占用一个端口
			conf.setNumWorkers(numWorker);

		} else {
			// 本地模式下一个组件运行的最大线程数
			conf.setMaxTaskParallelism(3);
		}

		BrokerHosts brokerHosts = new ZkHosts(zks);
		SpoutConfig spoutConf = new SpoutConfig(brokerHosts, topic, zkRoot, id);

		spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());
		spoutConf.forceFromStart = false;
		spoutConf.zkServers = Arrays.asList(new String[] { zks01, zks02, zks03 });
		spoutConf.zkPort = 2181;
		MyKafkaSpout mySport=new MyKafkaSpout(zks, topic, "tttt");
		
		/**
		 * 处理流程
		 */
		if (args.length > 6) {
			spoutConf.sleepTime = Integer.parseInt(args[5].trim());
			mapping_parallelism_hint = Integer.parseInt(args[6].trim());
		}

		//builder.setSpout("spout", new KafkaSpout(spoutConf), 6);
		builder.setSpout("spout",  mySport, 2);
		builder.setBolt("split", new LogSplitter(), 2).shuffleGrouping("spout");
		builder.setBolt("mapping", new Mapping(), mapping_parallelism_hint).fieldsGrouping("split",
				new Fields(DOMAIN_NAME));

		// statistics
		// builder.setBolt("", new
		// StatisticsCountBolt(),2).shuffleGrouping("split");

		// Nimbus host name passed from command line
		if (is_cluster) {
			StormSubmitter.submitTopology(name, conf, builder.createTopology());
		} else {
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology(name, conf, builder.createTopology());
			Thread.sleep(60000);
			cluster.shutdown();
		}

	}

}
