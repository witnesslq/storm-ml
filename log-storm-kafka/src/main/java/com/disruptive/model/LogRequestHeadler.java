package com.disruptive.model;

public class LogRequestHeadler {
	
	private String timeStr;
	private String logLevel;
	private String appName;
	private String serverName;
	private String pid;
	private String interfaceCode;
	private String packetType;
	
	private String appId;
	private String hostIp;
	private String userId;
	private String chnlId;
	private Integer reqTime;
	private String reqSeqNo;
	private String termnId;
	private String bizTrackNo;
	private String behavCode;
	private String servcId;
	private String other;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getChnlId() {
		return chnlId;
	}
	public void setChnlId(String chnlId) {
		this.chnlId = chnlId;
	}
	public Integer getReqTime() {
		return reqTime;
	}
	public void setReqTime(Integer reqTime) {
		this.reqTime = reqTime;
	}
	public String getReqSeqNo() {
		return reqSeqNo;
	}
	public void setReqSeqNo(String reqSeqNo) {
		this.reqSeqNo = reqSeqNo;
	}
	public String getTermnId() {
		return termnId;
	}
	public void setTermnId(String termnId) {
		this.termnId = termnId;
	}
	public String getBizTrackNo() {
		return bizTrackNo;
	}
	public void setBizTrackNo(String bizTrackNo) {
		this.bizTrackNo = bizTrackNo;
	}
	public String getBehavCode() {
		return behavCode;
	}
	public void setBehavCode(String behavCode) {
		this.behavCode = behavCode;
	}
	public String getServcId() {
		return servcId;
	}
	public void setServcId(String servcId) {
		this.servcId = servcId;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getInterfaceCode() {
		return interfaceCode;
	}
	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}
	public String getPacketType() {
		return packetType;
	}
	public void setPacketType(String packetType) {
		this.packetType = packetType;
	}
	
	public String reqReqStr(){
		return this.serverName.trim()+this.appName.trim()+this.reqSeqNo.trim();
	}

}
