package com.disruptive.storm;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

/**
 * 存款 交易笔数
 * 失败笔数 系统类失败
 * 平均 耗时 
 * 
 * @author Administrator
 *
 */
public class StatisticsCountBolt extends BaseRichBolt {

	private static final long serialVersionUID = 886149197481637894L;
	private OutputCollector collector;

	private StatisticsCountBiz statisticsCountBiz;

	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
		this.statisticsCountBiz = new StatisticsCountBiz();
	}

	public void execute(Tuple input) {

		String time_str = input.getString(0);
		String domain_name = input.getString(1);
		String app_name = input.getString(2);
		String req_res = input.getString(3);
		String service_name = input.getString(4);
		String header = input.getString(5);
		String line = input.getString(6);
		statisticsCountBiz.execute(time_str, domain_name, app_name, req_res,
				service_name, header, line);
		// this.collector.emit(new Values(line));
		this.collector.ack(input);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// declarer.declare(new Fields("line"));
	}

}
