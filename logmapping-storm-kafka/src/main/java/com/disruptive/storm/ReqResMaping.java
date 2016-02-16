package com.disruptive.storm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/



import com.alibaba.fastjson.JSON;
import com.disruptive.dao.InterfaceTimeOutDao;
import com.disruptive.dao.TmpRequestResponseDao;
import com.disruptive.dao.impl.InterfaceTimeOutDaoImpl;
import com.disruptive.dao.impl.TmpRequestResponseDaoImpl;
import com.disruptive.model.InterfaceTimeOut;
import com.disruptive.model.ReqHeadler;
import com.disruptive.model.ResHeadler;
import com.disruptive.util.MySQLHelper;


public class ReqResMaping {
	/*private static final Logger LOGGER = LoggerFactory
			.getLogger(ReqResMaping.class);*/
	private static final String RESPONSE_SUCCESS_CODE = "0000000000";
	
	private TmpRequestResponseDao tmpDao=null;
	
	public ReqResMaping(){
		tmpDao=new TmpRequestResponseDaoImpl();
	}

	public void execute(String time_str, String domain_name, String app_name,
			String req_res, String service_name, String header, String line) {
		System.out.println(time_str);
		try {
			if (req_res.equals("request")) {

				requestHeadler(header, domain_name, service_name, app_name,
						time_str, line);

			} else if (req_res.equals("response")) {
				responseHeadler(header, domain_name, service_name, app_name,
						time_str, line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void responseHeadler(String header, String domain_name,
			String service_name, String app_name, String time_str, String line)
			throws Exception {
		ResHeadler resHeadler = rsResHeadler(header);
		String key = domain_name.toLowerCase() + "|"
				+ service_name.toLowerCase() + "|" + resHeadler.getReqSeqNo()+"|"+app_name.toLowerCase().trim();

		long endTime = 0;
		try {
			endTime = time2Long(time_str);
		} catch (ParseException e2) {
			/*LOGGER.error(e2.getCause().getClass() + "|"
					+ e2.getCause().getMessage() + "|" + e2.getMessage());*/
		}
		
		List<HashMap<String, Object>> list =null;
		/*List<HashMap<String, Object>> list = MySQLHelper.ExecuteQuery(
				"select * from t_tmp_request_response where id=?",
				new Object[] { key });*/
		try{
			list=tmpDao.query(new Object[] { key }, str2yyyyMMdd(time_str));
		}catch(Exception e){
			e.printStackTrace();
		}
		if (list != null && list.size() > 0) {
			Map<String, Object> hMap = list.get(0);
		//	System.out.println(hMap.toString());
			if (hMap != null && hMap.size() > 0) {
				InterfaceTimeOut interfaceTimeOut = new InterfaceTimeOut();
				interfaceTimeOut.setAppName(app_name);
				interfaceTimeOut.setServerName(domain_name);
				interfaceTimeOut.setSeneCode(hMap.get("behavcode").toString()); // 场景码
				interfaceTimeOut.setTrackNo(hMap.get("reqseqno").toString());// 请求序列号
				interfaceTimeOut.setOrgnlSeqNo(hMap.get("orgnlseqno")
						.toString());
				interfaceTimeOut.setBizTrackNo(hMap.get("biztrackno")
						.toString());
				interfaceTimeOut.setInterfaceName(service_name);
				interfaceTimeOut.setRowKey("");
				interfaceTimeOut.setReqRowKey("");
				interfaceTimeOut.setResRowKey("");
				interfaceTimeOut.setRecordTime(new Date());
				String value = hMap.get("runtime").toString();
				interfaceTimeOut.setRecordTimeStr(time_str + "-" + value);
				interfaceTimeOut.setRespCode(resHeadler.getRespCode().trim());
				interfaceTimeOut
						.setIsException((!RESPONSE_SUCCESS_CODE
								.equals(interfaceTimeOut.getRespCode().trim())) ? 1
								: 0);
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
					e1.printStackTrace();
				}
				long count = endTime - startTime;
				interfaceTimeOut.setTimeOut((int) count);
				InterfaceTimeOutDao dao = new InterfaceTimeOutDaoImpl();
				try {
					dao.save(interfaceTimeOut, timeStr2Date(value));
					/*MySQLHelper
							.executeUpdate("delete from t_tmp_request_response where id='"
									+ key + "'");*/
					tmpDao.delete(new Object[]{key}, str2yyyyMMdd(value));
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				MySQLHelper
				.executeUpdate(
						"insert into t_question_log (`key`,line,logtype) values(?,?,?)",
						new Object[] { key, line, "response" });
			}
		}else{
			MySQLHelper
			.executeUpdate(
					"insert into t_question_log (`key`,line,logtype) values(?,?,?)",
					new Object[] { key, line, "response" });
		}
	}

	private ReqHeadler rsReqHeadler(String json) {
		ReqHeadler group2 = JSON.parseObject(json.trim(), ReqHeadler.class);
		return group2;
	}

	private ResHeadler rsResHeadler(String json) {
		ResHeadler group2 = JSON.parseObject(json.trim(), ResHeadler.class);
		return group2;
	}

	private void requestHeadler(String header, String domain_name,
			String service_name, String app_name, String time_str, String line)
			throws Exception {
		// 是请求日志
		ReqHeadler reqHeadler = rsReqHeadler(header);

		String key = domain_name.toLowerCase() + "|"
				+ service_name.toLowerCase() + "|" + reqHeadler.getReqSeqNo()+"|"+app_name.toLowerCase().trim();
		String behavcode = reqHeadler.getBehavCode(); // 场景码;
		String reqseqno = reqHeadler.getReqSeqNo();
		String orgnlseqno = reqHeadler.getOrgnlSeqNo();
		String biztrackno = reqHeadler.getBizTrackNo();
		String runtime = time_str;
		String respcode = "";
		//String sql = "insert into t_tmp_request_response (id,msg,behavcode,reqseqno,orgnlseqno,biztrackno,runtime,respcode) values(?,?,?,?,?,?,?,?)";
		try {
			/*MySQLHelper.executeUpdate(sql, new Object[] { key, line, behavcode,
					reqseqno, orgnlseqno, biztrackno, runtime, respcode });*/
			String yyyyMMdd=str2yyyyMMdd(runtime);
			tmpDao.save(new Object[] { key, line, behavcode,
					reqseqno, orgnlseqno, biztrackno, runtime, respcode }, yyyyMMdd);
		} catch (Exception e) {
			MySQLHelper
					.executeUpdate(
							" insert into t_question_log (`key`,line,logtype) values (?,?,?) ",
							new Object[] { key, line, "request" });
			e.printStackTrace();
		}
	}

	public void res(String line) throws Exception {
		String[] words = line.split("\\|", -1);
		// String log_level=words[0];
		String time_str = words[1];
		String domain_name = words[2].toLowerCase().trim();
		// String function=words[3].toLowerCase().trim();
		// String code_line=words[4];
		// String pname=words[5];
		// String class_name=words[6].toLowerCase().trim();
		String app_name = words[7].toLowerCase().trim();
		// String interface_call=words[8].toLowerCase().trim();
		String service_name = words[9].toLowerCase().trim();
		// String req_res=words[10].toLowerCase().trim();
		String header = words[11];
		// String body=words[12];

		responseHeadler(header, domain_name, service_name, app_name, time_str,
				line);
	}

	private long time2Long(String value) throws ParseException {
		String str = value.substring(0, value.indexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(str);
		return date.getTime()
				+ Integer.parseInt(value.substring(value.indexOf(".") + 1));
	}

	private Date timeStr2Date(String value) throws ParseException {
		String str = value.substring(0, value.indexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(str);
		return date;
	}
	
	private String str2yyyyMMdd(String value) throws Exception {
		String str = value.substring(0, value.indexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(str);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date).trim();
		
	}

}
