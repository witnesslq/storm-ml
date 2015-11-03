package cn.disruptive.web.vo;

import java.util.Date;

/**
 * 
 * Description：场景错误信息
 * Data：		2015年9月15日
 */
public class SceneErrVO {

	private Integer id;				// 数据库记录的id
	private String appName;			// 应用名称
	private String hostName;		// 主机名
	private String sceneCode;		// 场景码
	private String interfaceName;	// 接口
	private Integer timeOut;		// 调用耗时
	private String trackNo;			// 请求序列号
	private Date recordTime;		// 记录时间
	private String respCode;		// 响应码
	private String resRowKey;       // HBase响应Key
	private String reqRowKey;       // HBase请求Key
	private String bizTrackNo;		// 业务追踪号

	private String sceneName;		// 交易场景名称
	private String sceneNameEns;	// 场景英文缩写
	private String sceneDes;		// 备注
	
	private String tradingName;		// 交易名称
	private String serverName;		// 服务名
	private String trakNo;			// 行为码
	private String channelNo;		// 渠道号
	private String triggerPoint;	// 错误触发点
	private String errorCode;		// 错误码
	private String errorDes;		// 系统错误描述
	private String businessDes;		// 业务描述
	
	private String resHeadlerMsg;   // 响应头Msg
	private String reqHeadlerMsg;   // 请求头Msg	
	private String responseMsg;		// 响应描述
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getSceneCode() {
		return sceneCode;
	}

	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public Integer getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

	public String getTrackNo() {
		return trackNo;
	}

	public void setTrackNo(String trackNo) {
		this.trackNo = trackNo;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public String getSceneNameEns() {
		return sceneNameEns;
	}

	public void setSceneNameEns(String sceneNameEns) {
		this.sceneNameEns = sceneNameEns;
	}

	public String getSceneDes() {
		return sceneDes;
	}

	public void setSceneDes(String sceneDes) {
		this.sceneDes = sceneDes;
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public String getTrakNo() {
		return trakNo;
	}

	public void setTrakNo(String trakNo) {
		this.trakNo = trakNo;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getTriggerPoint() {
		return triggerPoint;
	}

	public void setTriggerPoint(String triggerPoint) {
		this.triggerPoint = triggerPoint;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDes() {
		return errorDes;
	}

	public void setErrorDes(String errorDes) {
		this.errorDes = errorDes;
	}

	public String getBusinessDes() {
		return businessDes;
	}

	public void setBusinessDes(String businessDes) {
		this.businessDes = businessDes;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public String getResRowKey() {
		return resRowKey;
	}

	public void setResRowKey(String resRowKey) {
		this.resRowKey = resRowKey;
	}

	public String getReqRowKey() {
		return reqRowKey;
	}

	public void setReqRowKey(String reqRowKey) {
		this.reqRowKey = reqRowKey;
	}

	public String getResHeadlerMsg() {
		return resHeadlerMsg;
	}

	public void setResHeadlerMsg(String resHeadlerMsg) {
		this.resHeadlerMsg = resHeadlerMsg;
	}

	public String getReqHeadlerMsg() {
		return reqHeadlerMsg;
	}

	public void setReqHeadlerMsg(String reqHeadlerMsg) {
		this.reqHeadlerMsg = reqHeadlerMsg;
	}

	public String getBizTrackNo() {
		return bizTrackNo;
	}

	public void setBizTrackNo(String bizTrackNo) {
		this.bizTrackNo = bizTrackNo;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	@Override
	public String toString() {
		return "SceneErrVO [\n id=" + id + ", \n appName=" + appName + ", \n hostName=" + hostName + ", \n sceneCode=" + sceneCode + ", \n interfaceName=" + interfaceName + ", \n timeOut=" + timeOut + ", \n trackNo=" + trackNo + ",\n recordTime=" + recordTime + ",\n  respCode=" + respCode + ",\n  resRowKey=" + resRowKey + ",\n  reqRowKey=" + reqRowKey + "\n bizTrackNo=" + bizTrackNo + ",\n  sceneName=" + sceneName + ",\n  sceneNameEns=" + sceneNameEns + ",\n  sceneDes=" + sceneDes + ",\n  tradingName=" + tradingName + ",\n  serverName=" + serverName + ",\n  trakNo=" + trakNo + ",\n  channelNo=" + channelNo + ",\n  triggerPoint=" + triggerPoint + ",\n  errorCode=" + errorCode + ",\n  errorDes=" + errorDes + ",\n  businessDes=" + businessDes + ",\n  resHeadlerMsg=" + resHeadlerMsg
				+ ",\n  reqHeadlerMsg=" + reqHeadlerMsg
				+ "\n ]";
	}
}
