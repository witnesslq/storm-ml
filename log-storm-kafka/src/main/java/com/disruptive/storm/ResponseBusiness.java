package com.disruptive.storm;

import java.util.HashMap;
import java.util.Map;

import com.disruptive.model.LogRequestHeadler;
import com.disruptive.model.LogResponseHeadler;
import com.disruptive.util.HBaseHelper;

/**
 * 
 * @author Administrator
 *
 */
public class ResponseBusiness {
	
	/**
	 * 根据LogResponseHeadler查询请求日志记录，统计调用耗时并存入MySQL中，并删除HBase中的logRequestHeadler。
	 * 如果没有找到日志则存储到HBase中
	 * 
	 * @param logResponseHeadler
	 */
	public void responseCollection(LogResponseHeadler logResponseHeadler){
		Map<String,String> map=HBaseHelper.queryRow("", logResponseHeadler.reqReqStr()+"req");
	    LogRequestHeadler logRequestHeadler=new LogRequestHeadler();
	    logRequestHeadler.setAppId(map.get("appId"));
	    logRequestHeadler.setAppName(map.get("appName"));
	    logRequestHeadler.setBehavCode(map.get("behavCode"));
	    logRequestHeadler.setBizTrackNo(map.get("bizTrackNo"));
	    
	    
	    
		
	}
	
	/**
	 * 保存LogRequestHeadler的日志
	 * @param logRequestHeadler
	 */
	public void reqSave(LogRequestHeadler logRequestHeadler){
		Map<String,String> map=logRequestHeadler2Map(logRequestHeadler);
		
		
		
		
		
		
	}
	/**
	 * 
	 * @param map
	 * @return
	 */
	private LogRequestHeadler map2LogRequestHeadler(Map<String,String> map){
		LogRequestHeadler logRequestHeadler=new LogRequestHeadler();
	    logRequestHeadler.setAppId(map.get("appId"));
	    logRequestHeadler.setAppName(map.get("appName"));
	    logRequestHeadler.setBehavCode(map.get("behavCode"));
	    logRequestHeadler.setBizTrackNo(map.get("bizTrackNo"));
	    return logRequestHeadler;
	}
	/**
	 * 
	 * @param logRequestHeadler
	 * @return
	 */
	private Map<String,String> logRequestHeadler2Map(LogRequestHeadler logRequestHeadler){
		Map<String,String> map=new HashMap<String,String>();
		map.put("appId", logRequestHeadler.getAppId().trim());
		map.put("appName", logRequestHeadler.getAppName().trim());
		map.put("behavCode", logRequestHeadler.getBehavCode().trim());
		map.put("bizTrackNo", logRequestHeadler.getBizTrackNo().trim());
		map.put("chnlId",logRequestHeadler.getChnlId().trim());
		map.put("hostIp", logRequestHeadler.getHostIp().trim());
		map.put("interfaceCode", logRequestHeadler.getInterfaceCode().trim());
		map.put("logLevel", logRequestHeadler.getLogLevel().trim());
		map.put("other",logRequestHeadler.getOther().trim());
		map.put("packetType", logRequestHeadler.getPacketType().trim());
		map.put("pid",logRequestHeadler.getPid().trim());
		map.put("reqSeqNo", logRequestHeadler.getReqSeqNo().trim());
		map.put("servcId",logRequestHeadler.getServcId().trim());
		map.put("serverName", logRequestHeadler.getServerName().trim());
		return map;
	}

}
