package com.disruptive.storm;

import java.util.Map;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 接口耗时统计
 * 
 * @author Administrator 根据不同的appName分流。
 *
 */
public class LogInterfaceSplitBlot extends BaseRichBolt {
	private static final Logger LOG =LoggerFactory
			.getLogger(LogInterfaceSplitBlot.class);
	private OutputCollector collector;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void execute(Tuple input) {
		String line = input.getString(0);
		// 处理前面|
		if (line.startsWith("|")) {
			line = line.substring(1).trim();
		}
	//	LOG.info("||||"+line);
		String[] arra = line.split("\\|", -1);
		String word = arra[2].trim().toLowerCase() + "|"
				+ arra[9].trim().toLowerCase();
		this.collector.emit(input, new Values(word, line));
		send(input);
	}

	
	private  void send(Tuple input) {
		this.collector.ack(input);
	}

	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word", "line"));

	}

}
