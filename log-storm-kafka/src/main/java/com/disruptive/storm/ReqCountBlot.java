package com.disruptive.storm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.disruptive.util.Constant;
import com.disruptive.util.HBaseHelper;
import com.disruptive.util.LockCurator;

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
public class ReqCountBlot extends BaseRichBolt {
	private static final Logger LOG =LoggerFactory
			.getLogger(ReqCountBlot.class);
	
	
	private static final String _TABLE_NAME="request_domain_count";
	
	private OutputCollector collector;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static boolean isOpen = true;
	
	private static Map<String, Integer> memberMap= null;

	public void execute(Tuple input) {
		
		String time_str=input.getString(0);
		String domain_name=input.getString(1);
		String app_name=input.getString(2);
		String req_res=input.getString(3);
		String service_name=input.getString(4);
		String header=input.getString(5);
		String line=input.getString(6);
		
		if(isOpen){
			if(checkMap()){
				if(req_res.equals("request")){
					Integer count=memberMap.get(domain_name);
					if(count==null){
						count=0;
					}
					memberMap.put(domain_name, count+1);
				}
			}
		}
		this.collector.emit(new Values(
				time_str, 
				domain_name,
				app_name,
				req_res,
				service_name,
				header,
				line));
		//send(input);
	}
	
	private boolean checkMap(){
		if(memberMap == null){
			memberMap = new HashMap<String, Integer>();
		}
		return true;
	}
	
	
	private void save_memberMap(Map<String,Integer> tmpMap){
		InterProcessMutex lock = new InterProcessMutex(LockCurator.getCF(), Constant.LOCKS_ORDER);
		try{
			while(lock.acquire(10, TimeUnit.MINUTES)){
				for(Map.Entry<String, Integer> entry:tmpMap.entrySet()){
				  Map<String,String> re=HBaseHelper.queryRow(_TABLE_NAME, entry.getKey());
				  int count=0;
				  if(re!=null&&re.size()>0){
					  String count_str=re.get("count");
					  if(StringUtils.isNotBlank(count_str)){
						  count=Integer.parseInt(count_str.trim());
					  }
				  }
				  count=count+Integer.parseInt(entry.getValue().toString().trim());
				  Put put = new Put(entry.getKey().toString().getBytes());
				  put.add("t1".getBytes(), "count".getBytes(),
							Bytes.toBytes(count+""));
				  HBaseHelper.save(put, _TABLE_NAME);
				}
			}
		}catch(Exception e){
			
		}finally{
			try {
				lock.release();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*private  void send(Tuple input) {
		this.collector.ack(input);
	}*/

	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
		Timer timer = new Timer();
		timer.schedule(new ReqCountBlot.cacheTimer(), new Date(), 10000);
	}
	
	//刷新缓存到数据库中
	class cacheTimer extends TimerTask{

		@Override
		public void run() {
			try{
			isOpen = false;
			Map<String, Integer> tmpMap = new HashMap<String, Integer>();
			tmpMap.putAll(memberMap);
			memberMap = new HashMap<String, Integer>();
		    save_memberMap(tmpMap);
			}finally{
				isOpen = true;
			}
		}
		
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
