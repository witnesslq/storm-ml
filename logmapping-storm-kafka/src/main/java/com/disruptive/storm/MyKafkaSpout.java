package com.disruptive.storm;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class MyKafkaSpout extends BaseRichSpout {
	public final Logger logger = LoggerFactory.getLogger(MyKafkaSpout.class);
	private static final long serialVersionUID = 1L;
	private SpoutOutputCollector collector;
	
	private ConsumerIterator it;
	String zk="";
	String topic="";
	String groupId="";

	public MyKafkaSpout(String zk,String topic,String groupid){
		this.zk=zk;
		this.topic=topic;
		this.groupId=groupid;
		//this.groupId = "test_groupxx123";
	}
	
	@Override
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		this.collector = collector;
		Properties props = new Properties();
		props.put("zookeeper.connect", zk);
		props.put("zookeeper.connectiontimeout.ms", "1000000");
		props.put("group.id", groupId);
		ConsumerConfig consumerConfig = new ConsumerConfig(props);
		ConsumerConnector consumer = kafka.consumer.Consumer
				.createJavaConsumerConnector(consumerConfig);
		Map topicCountMap = new HashMap();
		topicCountMap.put(topic, new Integer(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
				.createMessageStreams(topicCountMap);
		KafkaStream stream = consumerMap.get(topic).get(0);
		it = stream.iterator();
	}

	@Override
	public void nextTuple() {
		if (it.hasNext()) {
			Message message = it.next().copy$default$3();
			ByteBuffer buffer = message.payload();
			byte[] bytes = new byte[message.payloadSize()];
			buffer.get(bytes);
			String tmp = new String(bytes);
			collector.emit(new Values(tmp));
			}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("line"));
	}

}
