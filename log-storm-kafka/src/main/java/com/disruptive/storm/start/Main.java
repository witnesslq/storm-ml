package com.disruptive.storm.start;

import java.util.Arrays;

import com.disruptive.storm.LogKafkaTopology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;

public class Main {

	public static void main(String[] args)
			throws AlreadyAliveException, InvalidTopologyException, InterruptedException {
		String json="{\"servcId\":\"ebui_oim_usr_0003\"}";
		String str=json.replaceAll("\\\"","").replaceAll("\\{","").replaceAll("\\}", "");
		System.out.println(str.split(":")[1]);
		//LogKafkaTopology.main(args);

//		String zks = "lognn2te:2181,lognn1te:2181,logrmte:2181";
//		String topic = "test";
//		String zkRoot = "/storm"; // default zookeeper root configuration for
//									// storm
//		String id = "word";
//
//		BrokerHosts brokerHosts = new ZkHosts(zks);
//		SpoutConfig spoutConf = new SpoutConfig(brokerHosts, topic, zkRoot, id);
//		spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());
//		spoutConf.forceFromStart = false;
//		spoutConf.zkServers = Arrays.asList(new String[] { "lognn2te", "lognn1te", "logrmte" });
//		spoutConf.zkPort = 2181;
//
//		TopologyBuilder builder = new TopologyBuilder();
//		//
//		// builder.setSpout("spout", "123123,123123", 5);
//		builder.setSpout("kafka-reader", new KafkaSpout(spoutConf), 5); // Kafka我们创建了一个5分区的Topic，这里并行度设置为5
//		builder.setBolt("word-splitter", new KafkaWordSplitter(), 2).shuffleGrouping("kafka-reader");
//		builder.setBolt("word-counter", new WordCounter()).fieldsGrouping("word-splitter", new Fields("word"));
//
//		Config conf = new Config();
//
//		String name = LogKafkaTopology.class.getSimpleName();
//		if (args != null && args.length > 0) {
//			// Nimbus host name passed from command line
//			conf.put(Config.NIMBUS_HOST, PropertiesUtil.getPropertyString("nimbus.host"));
//			conf.setNumWorkers(3);
//			StormSubmitter.submitTopologyWithProgressBar(name, conf, builder.createTopology());
//		} else {
//			conf.setMaxTaskParallelism(3);
//			LocalCluster cluster = new LocalCluster();
//			cluster.submitTopology(name, conf, builder.createTopology());
//			Thread.sleep(60000);
//			cluster.shutdown();
//		}

		// TopologyBuilder builder = new TopologyBuilder();
		// builder.setSpout("spout", new RandomSentenceSpout(), 5);
		//
		// builder.setBolt("split", new SplitSentence(),
		// 8).shuffleGrouping("spout");
		// builder.setBolt("count", new WordCount(), 12).fieldsGrouping("split",
		// new Fields("word"));
		// Config conf = new Config();
		// conf.setDebug(true);
		//
		// if (args != null && args.length > 0) {
		// conf.setNumWorkers(3);
		// StormSubmitter.submitTopologyWithProgressBar("wordCount", conf,
		// builder.createTopology());
		// }
		// else {
		// conf.setMaxTaskParallelism(3);
		// LocalCluster cluster = new LocalCluster();
		// cluster.submitTopology("word-count", conf, builder.createTopology());
		// Thread.sleep(10000);
		// cluster.shutdown();
		//
	}

}
