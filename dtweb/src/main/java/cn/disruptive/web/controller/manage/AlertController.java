package cn.disruptive.web.controller.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.disruptive.common.util.DateUtils;
import cn.disruptive.common.util.HBaseHelper;
import cn.disruptive.common.util.ReadConfigation;
import cn.disruptive.core.common.page.Page;
import cn.disruptive.core.controller.BaseController;
import cn.disruptive.web.po.ErrorCodeTips;
import cn.disruptive.web.po.Scene;
import cn.disruptive.web.service.AlertService;
import cn.disruptive.web.vo.AllInfoSumDynamicVO;
import cn.disruptive.web.vo.AllInfoVO;
import cn.disruptive.web.vo.BizTrackNoInfoVO;
import cn.disruptive.web.vo.InterfaceTimeOutVo;
import cn.disruptive.web.vo.InterfaceVarianceVO;
import cn.disruptive.web.vo.SceneErrVO;
import cn.disruptive.web.vo.SceneGroupVO;

/**
 * 
 * Description：警告模块 Data： 2015年9月11日
 */
@Controller
@RequestMapping("/manage/alert")
public class AlertController extends BaseController {

	@Resource
	private AlertService alertService;

	private static final String HBASE_TABLE_SYS_BUSINESS_LOG = "sys_business_log";

	@RequestMapping("/allInfo")
	public String allInfo(ModelMap modelMap) {
		logger.debug("获取总览信息");
		return "manage/alert/allInfo";
	}
	@RequestMapping("/getDynamicAllInfo")
	@ResponseBody
	public Object getDynamicAllInfo() {
		logger.debug("动态获取总览信息");
		Map<String, Object> map = new HashMap<String, Object>();
		AllInfoVO allInfoVO = null;
		try {
			allInfoVO = alertService.getAllInfoVO();
		} catch (Exception e) {
			allInfoVO = new AllInfoVO();
			logger.error(e.getMessage());
		}
		map.put("flag", true);
		map.put("allInfoVO", allInfoVO);
		return map;
	}

	@RequestMapping("/getDynamicData")
	@ResponseBody
	public Object getDynamicData(HttpServletResponse response, long lastTime) throws IOException {
		logger.info("动态加载最新总交易数和成功交易数");
		Map<String, Object> map = new HashMap<String, Object>();

		// 获取指定时间内的几组监控数据
		List<AllInfoSumDynamicVO> list = null;
		try {
			list = alertService.getAllInfoSumDynamicVO();
		} catch (Exception e) {
			map.put("flag", false);
			logger.error(e.getMessage());
			return map;
		}
		logger.debug("查询到的数据条数：" + list.size());
		if (list.size() == 0) {
			map.put("flag", false);
			return map;
		}
		StringBuffer timeLine = new StringBuffer();
		StringBuffer numUsedLine = new StringBuffer();
		StringBuffer numSuccLine = new StringBuffer();
		for (AllInfoSumDynamicVO vo : list) {
			if (vo.getTime().getTime() > lastTime) {
				map.put("flag", true);
				lastTime = vo.getTime().getTime();
				timeLine.append(vo.getTime().getTime() + ";");
				numUsedLine.append(vo.getSumCount() + ";");
				numSuccLine.append((vo.getSumCount() - vo.getFailCount()) + ";");
			}
		}
		// TODO
		if (Integer.parseInt(ReadConfigation.getConfigItem("isTesting")) == 1) {
			lastTime = new Date().getTime();
			int nextInt = new Random().nextInt(100) + 20;
			timeLine = timeLine.append(lastTime + ";");
			numUsedLine = numUsedLine.append(nextInt + ";");
			numSuccLine = numSuccLine.append((nextInt - new Random().nextInt(20)) + ";");
			map.put("flag", true);
		}

		logger.info("返回的时间线：" + timeLine.toString());
		logger.info("返回的总线：" + numUsedLine.toString());
		logger.info("返回的成功线：" + numSuccLine.toString());

		map.put("lastTime", lastTime);
		map.put("timeLine", timeLine.toString());
		map.put("numUsedLine", numUsedLine.toString());
		map.put("numSuccLine", numSuccLine.toString());
		return map;
	}

	@RequestMapping("/getPointsData")
	@ResponseBody
	public Object getPointsData(HttpServletResponse response) throws IOException {
		logger.info("加载所有交易数和成功交易数");
		List<AllInfoSumDynamicVO> list = null;
		try {
			list = alertService.getAllInfoSumDynamicVO();
		} catch (Exception e) {
			list = null;
			logger.error(e.getMessage());
		}
		String data0 = "";
		String data1 = "";
		long lastTime = 0;
		if (list == null || list.size() == 0) {
			data0 = "[]";
			data1 = "[]";
		} else {
			StringBuffer dataSbf0 = new StringBuffer();
			StringBuffer dataSbf1 = new StringBuffer();
			for (AllInfoSumDynamicVO vo : list) {
				dataSbf0.append("[" + vo.getTime().getTime() + "," + vo.getSumCount() + "],");
				dataSbf1.append("[" + vo.getTime().getTime() + "," + (vo.getSumCount() - vo.getFailCount()) + "],");
				lastTime = vo.getTime().getTime();
			}
			data0 = "[" + dataSbf0.toString() + "]";
			data1 = "[" + dataSbf1.toString() + "]";
		}
		logger.debug("#####返回的数据#####：data0=" + data0);
		logger.debug("#####返回的数据#####：data1=" + data1);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("{\"msg\":\"" + data0 + ";" + data1 + "\",\"lastTime\":\"" + lastTime + "\"}");
		out.flush();
		out.close();
		return null;
	}

	@RequestMapping("/getInterfaceVariance")
	@ResponseBody
	public Object getInterfaceVariance() {
		logger.debug("首页展示接口访问量Top10耗时统计");
		Map<String, Object> map = new HashMap<String, Object>();
		List<InterfaceVarianceVO> interfaceVarianceVOList = null;
		try {
			interfaceVarianceVOList = alertService.getInterfaceVarianceVO();
		} catch (Exception e) {
			interfaceVarianceVOList = new ArrayList<InterfaceVarianceVO>();
			logger.error(e.getMessage());
		}
		map.put("flag", true);
		map.put("interfaceVarianceVOList", interfaceVarianceVOList);
		return map;
	}

	@RequestMapping("/list")
	public String list(ModelMap modelMap, String appName, Integer errType,
			String startDate, String endDate, String orderField) {
		logger.debug("获取所有警告,appName={},orderField={}", appName, orderField);
		// 时间处理
		if (StringUtils.isBlank(startDate)) {
			startDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd")
					+ " 00:00:00";
		}
		if (StringUtils.isBlank(endDate)) {
			endDate = startDate.substring(0, 10) + " 23:59:59";
		}
		if (!endDate.startsWith(startDate.substring(0, 10))) {
			endDate = startDate.substring(0, 10) + " 23:59:59";
		}
		if (StringUtils.isBlank(orderField)) {
			orderField = "sumNum";
		}
		List<SceneGroupVO> listOfSceneGroupVO = null;
		Integer logCount = 0;
		try {
			listOfSceneGroupVO = alertService.getAllSceneErr(appName, errType, startDate, endDate, orderField);
			logCount = alertService.logCount();
		} catch (Exception e) {
			listOfSceneGroupVO = new ArrayList<SceneGroupVO>();
			logger.error(e.getMessage());
		}
		modelMap.put("listOfSceneGroupVO", listOfSceneGroupVO);
		modelMap.put("logCount", logCount);
		modelMap.put("appName", appName);
		modelMap.put("errType", errType);
		modelMap.put("startDate", startDate);
		modelMap.put("endDate", endDate);
		modelMap.put("orderField", orderField);
		return "manage/alert/listAll";
	}

	@RequestMapping("/timeOutTop10")
	public String timeOutTop10(ModelMap modelMap, Integer curPage,
			String appName, String startDate, String endDate) {
		logger.debug("失败业务耗时Top10分析");
		// 时间处理
		if (StringUtils.isBlank(startDate)) {
			startDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd")
					+ " 00:00:00";
		}
		if (StringUtils.isBlank(endDate)) {
			endDate = startDate.substring(0, 10) + " 23:59:59";
		}
		if (!endDate.startsWith(startDate.substring(0, 10))) {
			endDate = startDate.substring(0, 10) + " 23:59:59";
		}
		if (curPage == null) {
			curPage = 1;
		}
		Page page = alertService.timeOutTop10(curPage, appName, startDate,
				endDate);

		modelMap.put("appName", appName);
		modelMap.put("page", page);
		modelMap.put("startDate", startDate);
		modelMap.put("endDate", endDate);
		return "manage/alert/timeOutTop10";
	}

	@RequestMapping("/showErrorList")
	public String list(ModelMap modelMap, String startRow, String endRow,
			Integer curPage) {
		logger.debug("获取所有警告,appName={}", startRow);
		String name = "sys_business_error_log";
		if (curPage == null) {
			curPage = 0;
		}
		String startTime = startRow;
		String endTime = endRow;
		if (StringUtils.isNotBlank(startRow) && StringUtils.isNotBlank(endRow)) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = format.parse(startRow);
			} catch (Exception e) {
				e.printStackTrace();
			}
			startRow = date.getTime() + "";
			try {
				date = format.parse(endRow);
			} catch (Exception e) {
				e.printStackTrace();
			}
			endRow = date.getTime() + "";
		} else {
			startRow = System.currentTimeMillis() + "";
			endRow = System.currentTimeMillis() + "";
		}
		List<Map<String, String>> rs = new ArrayList<Map<String, String>>();
		List<Map<String, String>> ls = HBaseHelper.scanByPage(name, startRow,
				endRow, curPage, null);
		for (int i = ls.size() - 1; i >= 0; i--) {
			Map<String, String> map = ls.get(i);
			Map<String, String> m = new HashMap<String, String>();
			m.put("line", map.get("line"));
			rs.add(m);
		}
		modelMap.put("errorList", rs);
		modelMap.put("startRow", startTime);
		modelMap.put("endRow", endTime);
		return "manage/alert/showErrorList";
	}

	@RequestMapping("/listAllSceneCode")
	public String listAllSceneCode(String sceneCode, ModelMap modelMap,
			Integer curPage) {
		logger.debug("获取所有场景码列表");
		if (curPage == null) {
			curPage = 1;
		}
		
		Page page = alertService.getAllSceneCodeCounts("", curPage);

		modelMap.put("page", page);
		// modelMap.put("alarmGroup", alarmGroup);

		// modelMap.put("logCount", logCount);
		return "manage/alert/listAllSceneCode";
	}
	/**
	 * 耗时日志查询
	 * @param sceneCode
	 * @param modelMap
	 * @param curPage
	 * @return
	 */
	@RequestMapping("/listAllOutTime")
	public String listAllOutTime( ModelMap modelMap,String yyyyMMdd,
			Integer curPage,String appName,String respCode,String serverName,
			String interfaceName,String sceneCode,String bizTrackNo) {
		logger.debug("获取所有场景码列表");
		if (curPage == null) {
			curPage = 1;
		}
		if(!StringUtils.isNotBlank(yyyyMMdd)){
			yyyyMMdd=DateUtils.formatDate(new Date(), "yyyyMMdd");
		}
		Page page = alertService.getAllOutTimes(yyyyMMdd, curPage,appName,respCode,serverName,interfaceName,sceneCode,bizTrackNo);
		modelMap.put("page", page);
		modelMap.put("yyyyMMdd",yyyyMMdd);
		modelMap.put("appName", appName);
		modelMap.put("respCode",respCode);
		modelMap.put("serverName", serverName);
		modelMap.put("interfaceName", interfaceName);
		modelMap.put("sceneCode", sceneCode);
		modelMap.put("bizTrackNo", bizTrackNo);
		
		
		return "manage/alert/listAllOutTime";
	}

	@RequestMapping("/listAllBusinessCode")
	public String listAllBusinessCode(String sceneCode, ModelMap modelMap,
			Integer curPage) {
		logger.debug("获取所有场景码列表");
		if (curPage == null) {
			curPage = 1;
		}
		modelMap.put("oneDayBusinessCounts",
				alertService.oneDayBusinessCount("", ""));
		modelMap.put("oneDayEerrorBusinessCounts",
				alertService.oneDayBusinessCount("", " where isException = 1 "));
		modelMap.put("ebcp", alertService.oneDayBusinessCount("",
				" where appName='ebcp' or appName='EBCP' "));
		modelMap.put("ebgb", alertService.oneDayBusinessCount("",
				" where appName='ebgb' or appName='EBGB' "));
		modelMap.put("ebmp", alertService.oneDayBusinessCount("",
				" where appName='ebmp' or appName='EBMP' "));
		try {
			modelMap.put("sceneGroup", alertService.getSceneGroupCountVos(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/alert/listAllBusinessCode";
	}

	@RequestMapping("/getScene")
	public String getScene(String sceneNameEns, String appName,
			String sceneCode, Integer curPage, String startDate,
			String endDate, ModelMap modelMap) {
		logger.debug("分页获取场景：{} 的错误列表,场景码：{}", sceneNameEns, sceneCode);
		if (curPage == null) {
			curPage = 1;
		}
		Integer errType = 1;
		Page page = alertService.getSceneErrorCode(appName, sceneNameEns,
				sceneCode, errType, curPage, startDate, endDate);
		List<SceneErrVO> dataList = page.getDataList();
		for (int i = 0; i < dataList.size(); i++) {
			String responseMsg = getResponseMsg(dataList.get(i).getResRowKey());
			dataList.get(i).setResponseMsg(responseMsg);
		}
		page.setDataList(dataList);
		// AlarmGroup alarmGroup = alertService.getAlarmGroup(sceneNameEns);
		List<Scene> listScene = alertService.getSceneList(sceneNameEns);
		modelMap.put("page", page);
		// modelMap.put("alarmGroup", alarmGroup);
		modelMap.put("appName", appName);
		modelMap.put("listScene", listScene);
		modelMap.put("sceneCode", sceneCode);
		modelMap.put("sceneNameEns", sceneNameEns);
		modelMap.put("startDate", startDate);
		modelMap.put("endDate", endDate);
		return "manage/alert/scenePage";
	}

	@RequestMapping("/showOne")
	public String showOne(Integer errorCodeId, ModelMap modelMap) {
		logger.debug("获取一个错误信息：errorCodeId={}", errorCodeId);
		SceneErrVO sceneErrVO = alertService.getSceneErrVO(errorCodeId);
		logger.debug("获取到的错误信息：{}", sceneErrVO.toString());
		modelMap.put("sceneErrVO", sceneErrVO);
		return "manage/alert/showOne";
	}
	
	@RequestMapping("/showInfo")
	public String showInfo(String yyyyMMdd,Integer id,
			ModelMap modelMap) {
		
		if(!StringUtils.isNotBlank(yyyyMMdd)){
			yyyyMMdd=DateUtils.formatDate(new Date(), "yyyyMMdd");
		}
		if (id == null) {
			id = 1;
		}
		List<InterfaceTimeOutVo> vos = alertService.showInfos(yyyyMMdd,id);
		
		List<Map<String, String>> rs = new ArrayList<Map<String, String>>();
		for(InterfaceTimeOutVo vo:vos){
			Map<String,String> map=new HashMap<String,String>();
			Map m=HBaseHelper.queryRow(HBASE_TABLE_SYS_BUSINESS_LOG, vo.getReqRowKey());
			if(m!=null&&m.get("line")!=null){
				map.put("req", vo.getReqRowKey()+"--->"+m.get("line").toString());
			}else{
				map.put("req", "");
			}
			//map.put("req", m==null?"":m.get("line").toString());
			m=HBaseHelper.queryRow(HBASE_TABLE_SYS_BUSINESS_LOG, vo.getResRowKey());
			if(m!=null&&m.get("line")!=null){
				map.put("res", vo.getResRowKey()+"--->"+m.get("line").toString());
			}else{
				map.put("res", "");
			}
			rs.add(map);
		}
		
		modelMap.put("infoList", rs);
		//modelMap.put("sceneCode", sceneCode);
		return "manage/alert/showInfo";
	}

	@RequestMapping("/showOneSceneCode")
	public String showOneSceneCode(String sceneCode, String serverName,
			String interfaceName, String appName, Integer curPage,
			ModelMap modelMap) {
		logger.debug("获取一个错误信息：sceneCode={}", sceneCode);
		if (curPage == null) {
			curPage = 1;
		}
		if (sceneCode == null) {
			sceneCode = "";
		}
		Page page = alertService.showOneSceneCode(sceneCode, serverName,
				interfaceName, appName, "", curPage);
		modelMap.put("page", page);
		modelMap.put("sceneCode", sceneCode);
		return "manage/alert/showOneSceneCode";
	}

	@RequestMapping("/getLogs")
	@ResponseBody
	public Object getLogs(String resRowKey, String reqRowKey) {
		logger.debug("从HBase获取错误日志,resRowKey={},reqRowKey={}", resRowKey,
				reqRowKey);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> mapOfHBaseRes = HBaseHelper.queryRow(
				HBASE_TABLE_SYS_BUSINESS_LOG, resRowKey);
		if (mapOfHBaseRes != null && !mapOfHBaseRes.isEmpty()) {
			map.put("resRowValue", mapOfHBaseRes.get("line"));
		}
		Map<String, String> mapOfHBaseRsq = HBaseHelper.queryRow(
				HBASE_TABLE_SYS_BUSINESS_LOG, reqRowKey);
		if (mapOfHBaseRsq != null && !mapOfHBaseRsq.isEmpty()) {
			map.put("reqRowValue", mapOfHBaseRsq.get("line"));
		}
		map.put("flag", true);
		return map;
	}

	@RequestMapping("/errCount")
	@ResponseBody
	public Object errCount(ModelMap modelMap) {
		logger.debug("获取今天的错误日志数量");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", true);
		try {
			Integer errCount = alertService.errCount();
			map.put("msgCount", errCount);
			return map;
		} catch (Exception e) {
			map.put("msgCount", 0);
			return map;
		}
	}

	@RequestMapping("/appCount")
	@ResponseBody
	public Object appCount(@RequestParam String yyyyMMdd,
			@RequestParam String appName) {
		logger.debug("执行统计查下时间{} appName{}", yyyyMMdd, appName);
		Integer count = alertService.appCount(yyyyMMdd, appName);
		if (count == null) {
			count = 0;
		}
		return count;

	}

	@RequestMapping("/getErrorCodeTips")
	@ResponseBody
	public Object getErrorCodeTips(String respCode) {
		logger.debug("按照错误码加载错误解决建议,respCode={}", respCode);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		ErrorCodeTips errorCodeTips = alertService.getErrorCodeTips(respCode);
		if (errorCodeTips != null) {
			map.put("flag", true);
			map.put("errorCodeTips", errorCodeTips);
		}
		return map;
	}

	@RequestMapping("/addErrTips")
	@ResponseBody
	public Object addErrTips(String respCode, String errorTips) {
		logger.debug("保存错误码加载错误解决建议,respCode={},errorTips={}", respCode,
				errorTips);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		alertService.addErrTips(respCode, errorTips);
		map.put("flag", true);
		return map;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/getAnalyseListByBizTrackNo")
	@ResponseBody()
	public Object getAnalyseListByBizTrackNo(String bizTrackNo) {
		logger.debug("通过业务追踪码分析场景情况：bizTrackNo={}", bizTrackNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		List<BizTrackNoInfoVO> analyseList = alertService
				.getAnalyseListByBizTrackNo(bizTrackNo);
		if (analyseList.size() == 0) {
			return map;
		}
		Integer timeOut = 0;
		for (int i = 0; i < analyseList.size(); i++) {
			timeOut = timeOut + analyseList.get(i).getTimeOut();
			Long timeMillis = Long.parseLong(analyseList.get(i).getTrackNo()
					.substring(4, analyseList.get(i).getTrackNo().length()));
			analyseList.get(i).setTimeMillis(timeMillis);
			logger.debug("累计时间：{}", timeOut);
			logger.debug("排序字段：{}", timeMillis);
		}
		Collections.sort(analyseList);
		map.put("flag", true);
		map.put("analyseList", analyseList);
		map.put("timeOut", timeOut);

		return map;
	}

	@RequestMapping("/ignoreError")
	@ResponseBody
	public Object ignoreError(Integer errorId) {
		logger.debug("忽略错误,errorId={}", errorId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		alertService.ignoreError(errorId);
		map.put("flag", true);
		return map;
	}

	/**
	 * 
	 * @param resRowKey
	 * @return response Msg
	 * 返回响应描述
	 * 
	 */
	private String getResponseMsg(String resRowKey) {
		String str = "";
		Map<String, String> mapOfHBaseRes = HBaseHelper.queryRow(
				HBASE_TABLE_SYS_BUSINESS_LOG, resRowKey);
		if (mapOfHBaseRes != null && !mapOfHBaseRes.isEmpty()) {
			str = mapOfHBaseRes.get("line");
			logger.debug("Hbase查找到:{}", str);
		}
		if (!StringUtils.isNotBlank(str)) {
			return "";
		}
		if (str.startsWith("|")) {
			str = str.substring(1).trim();
		}
		Map<String, String> tmpMap = new HashMap<String, String>();
		String[] words = str.split("\\|", -1);
		if (words.length >= 12) {
			String json1 = words[11];
			String[] array = json1.replaceAll("\\{message_head:", "")
					.replaceAll("\\{", "").replaceAll("\\}", "").split(",");
			for (int i = 0; i < array.length; i++) {
				String[] arr = array[i].split(":");
				if (arr.length == 2) {
					tmpMap.put(arr[0].replaceAll("\\\"", "").trim()
							.toLowerCase(), arr[1].replaceAll("\\\"", "")
							.trim().toLowerCase());
				} else {
					tmpMap.put(arr[0].replaceAll("\\\"", "").trim()
							.toLowerCase(), "");
				}
			}
		}
		logger.debug("Hbase查找响应描述key:{},返回：{}", resRowKey, tmpMap.get("respmsg") != null ? tmpMap.get("respmsg") : tmpMap.get("respMsg"));
		return tmpMap.get("respmsg") != null ? tmpMap.get("respmsg") : "";
	}
	
	
	/**
	 * 根据时间得到 EBUI、EBCP、EBMP的访问量总和
	 * @param date
	 * @return 
	 */
	public Integer businessTotal(Date date){
		
		if(date==null){
			throw new IllegalArgumentException("date :不能为空 ");
		}
		Integer all_Count=0;
		String dateStr=DateUtils.formatDate(date, "yyyy-MM-dd HH:mm");
		try{
			List<Map<String,String>> list=HBaseHelper.scanByPrefixFilter("request_count", dateStr+"|"+"ebmp");
			List<Map<String,String>> list2=HBaseHelper.scanByPrefixFilter("request_count", dateStr+"|"+"ebcp");
			List<Map<String,String>> list3=HBaseHelper.scanByPrefixFilter("request_count", dateStr+"|"+"ebui");
			if(list!=null&&list.size()>0){
				for(Map<String,String> li:list){
					all_Count=all_Count+Integer.parseInt(li.get("allCount").toString().trim());
				}
			}
			if(list2!=null&&list2.size()>0){
				for(Map<String,String> li:list2){
					all_Count=all_Count+Integer.parseInt(li.get("allCount").toString().trim());
				}
			}
			if(list3!=null && list3.size()>0){
				for(Map<String,String> li:list3){
					all_Count=all_Count+Integer.parseInt(li.get("allCount").toString().trim());
				}
			}
			
			
		}catch(Exception e){
			
		}
		
		return all_Count;
	}

}
