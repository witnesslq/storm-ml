package com.disruptive.storm;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.disruptive.dao.InterfaceTimeOutDao;
import com.disruptive.dao.impl.InterfaceTimeOutDaoImpl;
import com.disruptive.model.InterfaceTimeOut;
import com.disruptive.util.HBaseHelper;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 
 * @author sunwubin 接口耗时统计 根据业务可知 请求日志先于响应日志，是逻辑有序的，
 *         将请求日志的时间保存入timeMap中，（暂不考虑三方存储） 当响应日志过来时在timeMap中取出请求日志的时间做差
 * 
 *         得出结果后存储于MySQL数据库中，然后删除timeMap中的请求时间的数据。 同时如果响应的的respCode 不是
 *         0000000000 则是异常日志。
 *
 */
public class LogInterfaceCountBlot extends BaseRichBolt {
	public static final Logger LOGGER = LoggerFactory
			.getLogger(LogInterfaceCountBlot.class);
	private static final String RESPONSE_SUCCESS_CODE="0000000000";
	private static final String HBASE_TABLE_SYS_BUSINESS_NO_REQUEST_LOG="sys_business_no_request_log";
	private static final String HBASE_TABLE_LOG_TIME_TMP="log_time_tmp";
	private static final String HBASE_TABLE_SYS_BUSINESS_LOG="sys_business_log";
	private OutputCollector collector;
	private static final long serialVersionUID = 1L;
	public void execute(Tuple input) {
		String line = input.getValue(1).toString().trim();
		if (!StringUtils.isNotBlank(line)) {
		//	collector.ack(input);
			collector.emit(input, new Values(line,""));
			collector.ack(input);
			return;
		}
		//处理前面|
		if(line.startsWith("|")){
			line=line.substring(1).trim();
		}
	//	LOGGER.info("===="+line);
		String[] words = line.split("\\|",-1);
		if(words.length<12){
			collector.emit(input, new Values(line,words));
			collector.ack(input);
			return;
		}
		
		if (words[10].trim().toLowerCase().equals("request")) {
			try {
				requestHeadler(line,words);
			//	collector.ack(input);
			} catch (IOException e) {
				LOGGER.error(e.getCause().getClass() + "|"
						+ e.getCause().getMessage() + "|" + e.getMessage());
			}
		}else{
			responseHeadler(line,words);
		//collector.ack(input);
		}
		collector.emit(input, new Values(line,words));
		collector.ack(input);
	}
	
	private void responseHeadler(String line,String[] words){
		String json1 = words[11];
		String[] array = json1.replaceAll("\\{message_head:", "")
				.replaceAll("\\{", "").replaceAll("\\}", "")
				.split(",");
		Map<String, String> tmpMap = new HashMap<String, String>();
		for (int i = 0; i < array.length; i++) {
			String[] arr = array[i].split(":");
			if (arr.length == 2) {
				tmpMap.put(arr[0].replaceAll("\\\"", "").trim().toLowerCase(),
						arr[1].replaceAll("\\\"", "").trim().toLowerCase());
			} else {
				tmpMap.put(arr[0].replaceAll("\\\"", "").trim().toLowerCase(),
						"");
			}
		}
		String key=words[2].trim().toLowerCase() + "|"+words[9].trim().toLowerCase()+"|"+tmpMap.get("reqseqno");
		long endTime = 0;
		try {
			endTime = time2Long(words[1]);
		} catch (ParseException e2) {
			LOGGER.error(e2.getCause().getClass() + "|"
					+ e2.getCause().getMessage() + "|" + e2.getMessage());
		}
		String rowKey1=words[1]+"|response|"+key;//+"|"+tmpMap.get("reqseqno");
		Map<String,String> teMap=queryHbase(rowKey1);
		if(teMap!=null&&teMap.size()>0){
			return;
		}
		saveHbase(rowKey1,line);
		Map<String, String> hMap =HBaseHelper.queryRow(HBASE_TABLE_LOG_TIME_TMP, key);
		if (hMap != null && hMap.size()>0) {
			InterfaceTimeOut interfaceTimeOut = new InterfaceTimeOut();
			interfaceTimeOut.setAppName(words[7]);
			interfaceTimeOut.setServerName(words[2]);
			interfaceTimeOut.setSeneCode(hMap.get("behavcode") == null ? "": hMap.get("behavcode").toUpperCase()); // 场景码
			interfaceTimeOut.setTrackNo(hMap.get( "reqSeqNo".toLowerCase()) == null ? "": hMap.get( "reqSeqNo".toLowerCase()));// 请求序列号
			interfaceTimeOut.setOrgnlSeqNo(hMap.get("orgnlSeqNo".toLowerCase())==null?"":hMap.get("orgnlSeqNo".toLowerCase()));
			interfaceTimeOut.setBizTrackNo(hMap.get("bizTrackNo".toLowerCase())==null?"":hMap.get("bizTrackNo".toLowerCase()));
			interfaceTimeOut.setInterfaceName(words[9]);
			interfaceTimeOut.setRowKey("");
			interfaceTimeOut.setReqRowKey(hMap.get("reqrowkey"));
			interfaceTimeOut.setResRowKey(rowKey1);
			interfaceTimeOut.setRecordTime(new Date());
			String value = hMap.get("runtime");
			interfaceTimeOut.setRecordTimeStr(words[1] + "-" + value);
			interfaceTimeOut
					.setRespCode(tmpMap.get("respcode") == null ? ""
							: tmpMap.get("respcode"));
			interfaceTimeOut.setIsException((!RESPONSE_SUCCESS_CODE.equals(interfaceTimeOut.getRespCode().trim()))?1:0);
			if(StringUtils.isNotBlank(interfaceTimeOut.getSeneCode())){
				String[] aa=interfaceTimeOut.getSeneCode().split("_",-1);
				if(aa.length>2){
					interfaceTimeOut.setSceneNameEns(aa[1].toUpperCase());
				}
			}
			long startTime = 0;
			try {
				startTime = time2Long(value);
			} catch (ParseException e1) {
				LOGGER.error(e1.getCause().getClass() + "|"
					+ e1.getCause().getMessage() + "|" + e1.getMessage());
			}
			//timeMap.remove(key);
			long count = endTime - startTime;
			interfaceTimeOut.setTimeOut((int) count);
			InterfaceTimeOutDao dao = new InterfaceTimeOutDaoImpl();
			try {
				dao.save(interfaceTimeOut,timeStr2Date(value));
			} catch (Exception e) {
				LOGGER.error(e.getCause().getClass() + "|"
						+ e.getCause().getMessage() + "|" + e.getMessage());
			}
			// 如果不全为0则提示异常错误信息
			if (!RESPONSE_SUCCESS_CODE.equals(interfaceTimeOut.getRespCode().trim())) {
				saveErrorLog(System.currentTimeMillis()+"", line);
			}
		}else{
			//找不到请求
			saveNoRequest("response|"+key+"|"+words[1],line);
		}
	}
	
	private void requestHeadler(String line,String[] words) throws IOException{
		Map<String, String> map = new HashMap<String, String>();
		// 是请求日志
		String json = words[11];
		if (json.startsWith("\\{message_head:")) {
			String[] array = json.replaceAll("\\{message_head:", "")
					.replaceAll("\\{", "").replaceAll("\\}", "").split(",");
			for (int i = 0; i < array.length; i++) {
				String[] arr = array[i].split("=");
				if (arr.length == 2) {
					map.put(arr[0].trim().toLowerCase(), arr[1].trim().toLowerCase());
				} else {
					map.put(arr[0].trim().toLowerCase(), "");
				}
			}
		} else {
			String[] array = json.replaceAll("\\{message_head:", "")
					.replaceAll("\\{", "").replaceAll("\\}", "")
					.split(",");
			for (int i = 0; i < array.length; i++) {
				String[] arr = array[i].split(":");
				if (arr.length == 2) {
					map.put(arr[0].replaceAll("\\\"", "").trim().toLowerCase(), arr[1]
							.replaceAll("\\\"", "").trim().toLowerCase());
				
				} else {
					map.put( arr[0].replaceAll("\\\"", "").trim().toLowerCase(), "");
				}
			}
		}
		String key=words[2].trim().toLowerCase() + "|"+words[9].trim().toLowerCase()+"|"+map.get("reqSeqNo".toLowerCase());
		map.put("runtime", words[1]);
		String rowKey=words[1]+"|request|"+key;//+"|"+map.get("reqSeqNo".toLowerCase());
		saveHbase(rowKey,line);
		map.put("reqrowkey", rowKey);
		map.put("rowKey", key);
		List<Map<String,String>> lists=new ArrayList<Map<String,String>>();
		lists.add(map);
		saveLogTimeTmp(lists);
		//查询是否存在Response
		String reqRowKey="response|"+key;
		List<Map<String,String>> reqLists=HBaseHelper.scanByPrefixFilter("sys_business_no_request_log", reqRowKey);
		if(reqLists!=null&&reqLists.size()>0){
			for(Map<String,String> m:reqLists){
				if(m!=null&&m.size()>0){
					//处理关联 删除关联上的Response
					HBaseHelper.deleteRow("sys_business_no_request_log", map.get("rowKey").toString());
					responseHeadler(reqRowKey,m.get("line").toString().split("\\|",-1));
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

	public long time2Long(String value) throws ParseException {
		String str = value.substring(0, value.indexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(str);
		return date.getTime()
				+ Integer.parseInt(value.substring(value.indexOf(".") + 1));

	}
	/**
	 * 保存到sys_business_log
	 * @param rowKey
	 * @return
	 */
	public Map<String,String> queryHbase(String rowKey){
		Map<String,String> map=HBaseHelper.queryRow(HBASE_TABLE_SYS_BUSINESS_LOG, rowKey);
		return map;
	}
	/**
	 * 请求Header 保存到HBase log_time_tmp
	 * @param lists
	 */
	public void  saveLogTimeTmp(List<Map<String,String>> lists){
		
		HBaseHelper.saveListMap(HBASE_TABLE_LOG_TIME_TMP, lists);
	}
	
	public void saveHbase(String rowKey,String value){
		List<Map<String, String>> lists=new ArrayList<Map<String,String>>();
    		Map<String,String> map=new HashMap<String,String>();
    		map.put("rowKey", rowKey);
    		map.put("line", value);
    		lists.add(map);
    	HBaseHelper.saveListMap("sys_business_log", lists);
	}
	
	/**
	 * 未找到的request Header的response
	 * @param rowKey
	 * @param value
	 */
	public void saveNoRequest(String rowKey,String value){
		List<Map<String, String>> lists=new ArrayList<Map<String,String>>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("rowKey", rowKey);
		map.put("line", value);
		lists.add(map);
	HBaseHelper.saveListMap(HBASE_TABLE_SYS_BUSINESS_NO_REQUEST_LOG, lists);
	}
	
	public Date timeStr2Date(String value) throws ParseException {
		String str = value.substring(0, value.indexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(str);
		return date;
	}

	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("response", "count"));
	}

}
