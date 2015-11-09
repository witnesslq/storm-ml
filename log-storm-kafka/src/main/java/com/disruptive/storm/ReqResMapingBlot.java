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
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.disruptive.dao.InterfaceTimeOutDao;
import com.disruptive.dao.impl.InterfaceTimeOutDaoImpl;
import com.disruptive.model.InterfaceTimeOut;
import com.disruptive.util.Constant;
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
public class ReqResMapingBlot extends BaseRichBolt {
	public static final Logger LOGGER = LoggerFactory
			.getLogger(ReqResMapingBlot.class);
	private static final String RESPONSE_SUCCESS_CODE = "0000000000";
	private static final String HBASE_TABLE_SYS_BUSINESS_NO_REQUEST_LOG = "sys_business_no_request_log";
	private static final String HBASE_TABLE_LOG_TIME_TMP = "log_time_tmp";
	private static final String HBASE_TABLE_SYS_BUSINESS_LOG = "sys_business_log";
	private OutputCollector collector;
	private static final long serialVersionUID = 1L;

	public void execute(Tuple input) {
		
		String time_str=input.getString(0);
		String domain_name=input.getString(1);
		String app_name=input.getString(2);
		String req_res=input.getString(3);
		String service_name=input.getString(4);
		String header=input.getString(5);
		String line=input.getString(6);
		
		if(req_res.equals("request")){
			try {
				requestHeadler(header,domain_name,service_name,app_name,time_str,line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(req_res.equals("response")){
			responseHeadler(header,domain_name,service_name,app_name,time_str,line);
		}
		this.collector.emit(new Values(time_str,domain_name,app_name,req_res,service_name,header,line));
		
		
//      String line = input.getValue(1).toString().trim();
//		if (!StringUtils.isNotBlank(line)) {
//			// collector.ack(input);
//			collector.emit(input, new Values(line, ""));
//			collector.ack(input);
//			return;
//		}
		// 处理前面|
//		if (line.startsWith("|")) {
//			line = line.substring(1).trim();
//		}
		// LOGGER.info("===="+line);
	/*	String[] words = line.split("\\|", -1);
		if (words.length < 12) {
			collector.emit(input, new Values(line, words));
			collector.ack(input);
			return;
		}*/

	/*	if (words[10].trim().toLowerCase().equals("request")) {
			try {
				requestHeadler(line, words);
				// collector.ack(input);
			} catch (IOException e) {
				LOGGER.error(e.getCause().getClass() + "|"
						+ e.getCause().getMessage() + "|" + e.getMessage());
			}
		} else {
			responseHeadler(line, words);
			// collector.ack(input);
		}*/
		/*collector.emit(input, new Values(line, words));
		collector.ack(input);*/
	}

	private void responseHeadler(
			String header,
			String domain_name,
			String service_name,
			String app_name,
			String time_str,
			String line) {
		String json1 = header;
		
		String[] array = json1.replaceAll("\\{message_head:", "")
				.replaceAll("\\{", "").replaceAll("\\}", "").split(",");
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
		String key = domain_name.toLowerCase() + "|"
				+ service_name.toLowerCase() + "|" + tmpMap.get("reqseqno");
		// 存储响应次数
		/*resCount(words[1], words[2].trim().toLowerCase() + "|"
				+ words[9].trim().toLowerCase(), tmpMap);*/
		
		long endTime = 0;
		try {
			endTime = time2Long(time_str);
		} catch (ParseException e2) {
			LOGGER.error(e2.getCause().getClass() + "|"
					+ e2.getCause().getMessage() + "|" + e2.getMessage());
		}
		String rowKey1 = time_str + "|response|" + key;// +"|"+tmpMap.get("reqseqno");
		Map<String, String> teMap = queryHbase(rowKey1);
		if (teMap != null && teMap.size() > 0) {
			return;
		}
		saveHbase(rowKey1, line);
		Map<String, String> hMap = HBaseHelper.queryRow(
				HBASE_TABLE_LOG_TIME_TMP, key);
		if (hMap != null && hMap.size() > 0) {
			InterfaceTimeOut interfaceTimeOut = new InterfaceTimeOut();
			interfaceTimeOut.setAppName(app_name);
			interfaceTimeOut.setServerName(domain_name);
			interfaceTimeOut.setSeneCode(hMap.get("behavcode") == null ? ""
					: hMap.get("behavcode").toUpperCase()); // 场景码
			interfaceTimeOut
					.setTrackNo(hMap.get("reqSeqNo".toLowerCase()) == null ? ""
							: hMap.get("reqSeqNo".toLowerCase()));// 请求序列号
			interfaceTimeOut
					.setOrgnlSeqNo(hMap.get("orgnlSeqNo".toLowerCase()) == null ? ""
							: hMap.get("orgnlSeqNo".toLowerCase()));
			interfaceTimeOut
					.setBizTrackNo(hMap.get("bizTrackNo".toLowerCase()) == null ? ""
							: hMap.get("bizTrackNo".toLowerCase()));
			interfaceTimeOut.setInterfaceName(service_name);
			interfaceTimeOut.setRowKey("");
			interfaceTimeOut.setReqRowKey(hMap.get("reqrowkey"));
			interfaceTimeOut.setResRowKey(rowKey1);
			interfaceTimeOut.setRecordTime(new Date());
			String value = hMap.get("runtime");
			interfaceTimeOut.setRecordTimeStr(time_str + "-" + value);
			interfaceTimeOut.setRespCode(tmpMap.get("respcode") == null ? ""
					: tmpMap.get("respcode"));
			interfaceTimeOut.setIsException((!RESPONSE_SUCCESS_CODE
					.equals(interfaceTimeOut.getRespCode().trim())) ? 1 : 0);
			if (StringUtils.isNotBlank(interfaceTimeOut.getSeneCode())) {
				String[] aa = interfaceTimeOut.getSeneCode().split("_", -1);
				if (aa.length > 2) {
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
			// timeMap.remove(key);
			long count = endTime - startTime;
			interfaceTimeOut.setTimeOut((int) count);
			InterfaceTimeOutDao dao = new InterfaceTimeOutDaoImpl();
			try {
				dao.save(interfaceTimeOut, timeStr2Date(value));
			} catch (Exception e) {
				LOGGER.error(e.getCause().getClass() + "|"
						+ e.getCause().getMessage() + "|" + e.getMessage());
			}
			// 如果不全为0则提示异常错误信息
			if (!RESPONSE_SUCCESS_CODE.equals(interfaceTimeOut.getRespCode()
					.trim())) {
				saveErrorLog(System.currentTimeMillis() + "", header);
			}
		} else {
			// 找不到请求
			saveNoRequest("response|" + key + "|" + time_str, "");
		}
	}

	private void requestHeadler(
			String header,
			String domain_name,
			String service_name,
			String app_name,
			String time_str,String line) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		// 是请求日志
		String json = header;
		if (json.startsWith("\\{message_head:")) {
			String[] array = json.replaceAll("\\{message_head:", "")
					.replaceAll("\\{", "").replaceAll("\\}", "").split(",");
			for (int i = 0; i < array.length; i++) {
				String[] arr = array[i].split("=");
				if (arr.length == 2) {
					map.put(arr[0].trim().toLowerCase(), arr[1].trim()
							.toLowerCase());
				} else {
					map.put(arr[0].trim().toLowerCase(), "");
				}
			}
		} else {
			String[] array = json.replaceAll("\\{message_head:", "")
					.replaceAll("\\{", "").replaceAll("\\}", "").split(",");
			for (int i = 0; i < array.length; i++) {
				String[] arr = array[i].split(":");
				if (arr.length == 2) {
					map.put(arr[0].replaceAll("\\\"", "").trim().toLowerCase(),
							arr[1].replaceAll("\\\"", "").trim().toLowerCase());

				} else {
					map.put(arr[0].replaceAll("\\\"", "").trim().toLowerCase(),
							"");
				}
			}
		}
		
		String key = domain_name.toLowerCase() + "|"
				+ service_name.toLowerCase() + "|"
				+ map.get("reqSeqNo".toLowerCase());
		reqCount(time_str, domain_name.toLowerCase() + "|"
				+ service_name.toLowerCase());
		
		reqCountByDay(time_str, domain_name.toLowerCase() + "|"
				+ service_name.toLowerCase());
		
		map.put("runtime", time_str);
		String rowKey = time_str + "|request|" + key;// +"|"+map.get("reqSeqNo".toLowerCase());
		saveHbase(rowKey, line);
		map.put("reqrowkey", rowKey);
		map.put("rowKey", key);
		List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
		lists.add(map);
		saveLogTimeTmp(lists);
		// 查询是否存在Response
		String reqRowKey = "response|" + key;
		List<Map<String, String>> reqLists = HBaseHelper.scanByPrefixFilter(
				"sys_business_no_request_log", reqRowKey);
		if (reqLists != null && reqLists.size() > 0) {
			for (Map<String, String> m : reqLists) {
				if (m != null && m.size() > 0) {
					// 处理关联 删除关联上的Response
					if (map.get("rowKey") != null) {
						HBaseHelper.deleteRow("sys_business_no_request_log",
								map.get("rowKey").toString());
					}
					if (map.get("line") != null) {
						/*responseHeadler(reqRowKey, m.get("line").toString()
								.split("\\|", -1));*/
						res(map.get("line").toString());
					}
				}
			}
		}
	}
	
	
	public void res(String line){
		String[] words=line.split("\\|", -1);
		//String log_level=words[0];
		String time_str=words[1];
		String domain_name=words[2].toLowerCase().trim();
		//String function=words[3].toLowerCase().trim();
		//String code_line=words[4];
		//String pname=words[5];
		//String class_name=words[6].toLowerCase().trim();
		String app_name=words[7].toLowerCase().trim();
		//String interface_call=words[8].toLowerCase().trim();
		String service_name=words[9].toLowerCase().trim();
		//String req_res=words[10].toLowerCase().trim();
		String header=words[11];
		//String body=words[12];
		
		responseHeadler(header,domain_name,service_name,app_name,time_str,line);
		
	}
	/**
	 * 保存response 计数
	 * 
	 * @param rowKey
	 */
	/*private void resCount(String timeStr, String rowKey,
			Map<String, String> tmpMap) {
		Integer allCount = 0;
		Integer errorCount = 0;
		Integer nullCount = 0;

		String key = time2Str(timeStr) + "|" + rowKey;
		Map<String, String> map = HBaseHelper.queryRow("response_count", key);

		if (map != null && map.size() > 0) {
			allCount = Integer.parseInt(map.get("allCount").toString().trim());
			errorCount = Integer.parseInt(map.get("errorCount").toString()
					.trim());
			nullCount = Integer
					.parseInt(map.get("nullCount").toString().trim());
		}
		if (tmpMap != null) {
			if (tmpMap.get("respcode") != null) {
				if (!tmpMap.get("respcode").toString()
						.equals(RESPONSE_SUCCESS_CODE)) {
					errorCount++;
				}
			} else {
				nullCount++;
			}
		} else {
			nullCount++;
		}
		allCount++;
		Put put = new Put(key.getBytes());
		put.add("t1".getBytes(), "allCount".getBytes(),
				Bytes.toBytes(allCount + ""));
		put.add("t1".getBytes(), "errorCount".getBytes(),
				Bytes.toBytes(errorCount + ""));
		put.add("t1".getBytes(), "nullCount".getBytes(),
				Bytes.toBytes(nullCount + ""));
		HBaseHelper.save(put, "response_count");
		// "2015-10-08 00:13:"
	}*/

	/**
	 * 保存request 计数 按分钟记录
	 * 
	 * @param rowKey
	 */
	private void reqCount(String timeStr, String rowKey) {
		Integer allCount = 0;
		String key = time2Str(timeStr) + "|" + rowKey;
		Map<String, String> map = HBaseHelper.queryRow("request_count", key);
		if (map != null && map.size() > 0) {
			allCount = Integer.parseInt(map.get("allCount").toString().trim());
		}
		allCount++;
		Put put = new Put(key.getBytes());
		put.add("t1".getBytes(), "allCount".getBytes(),
				Bytes.toBytes(allCount + ""));
		HBaseHelper.save(put, "request_count");
	}
	
	/**
	 * 保存request 计数 按分钟记录
	 * 
	 * @param rowKey
	 */
	private void reqCountByDay(String timeStr, String rowKey) {
		Integer allCount = 0;
		String key = time2yyyyMMdd(timeStr) + "|" + rowKey;
		Map<String, String> map = HBaseHelper.queryRow("request_count", key);
		if (map != null && map.size() > 0) {
			allCount = Integer.parseInt(map.get("allCount").toString().trim());
		}
		allCount++;
		Put put = new Put(key.getBytes());
		put.add("t1".getBytes(), "allCount".getBytes(),
				Bytes.toBytes(allCount + ""));
		HBaseHelper.save(put, "request_count");
	}

	private String time2Str(String timeStr) {
		timeStr = timeStr.trim();
		return timeStr.substring(0, timeStr.lastIndexOf(":")).trim();
	}
	
	private String time2yyyyMMdd(String timeStr){
		timeStr=timeStr.trim();
		return timeStr.substring(0,timeStr.lastIndexOf(" ")).trim();
	}

	/**
	 * 保存ERROR日志
	 * 
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
	 * 
	 * @param rowKey
	 * @return
	 */
	public Map<String, String> queryHbase(String rowKey) {
		Map<String, String> map = HBaseHelper.queryRow(
				HBASE_TABLE_SYS_BUSINESS_LOG, rowKey);
		return map;
	}

	/**
	 * 请求Header 保存到HBase log_time_tmp
	 * 
	 * @param lists
	 */
	public void saveLogTimeTmp(List<Map<String, String>> lists) {

		HBaseHelper.saveListMap(HBASE_TABLE_LOG_TIME_TMP, lists);
	}

	public void saveHbase(String rowKey, String value) {
		List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("rowKey", rowKey);
		map.put("line", value);
		lists.add(map);
		HBaseHelper.saveListMap("sys_business_log", lists);
	}

	/**
	 * 未找到的request Header的response
	 * 
	 * @param rowKey
	 * @param value
	 */
	public void saveNoRequest(String rowKey, String value) {
		List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
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
