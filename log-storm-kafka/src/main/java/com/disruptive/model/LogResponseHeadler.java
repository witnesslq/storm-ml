package com.disruptive.model;

public class LogResponseHeadler {
	
	private String timeStr;
	private String logLevel;
	private String appName;
	private String serverName;
	private String pid;
	private String interfaceCode;
	private String packetType;
	
	private String respCode;
	private String serverIp;
	private String servcReturnTime;
	private String servcIp;
	private String reqSeqNo;
	private String respMsg;
	private String servcSeqNo;
	private String totalRecord;
	
	private String other;

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

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServcReturnTime() {
		return servcReturnTime;
	}

	public void setServcReturnTime(String servcReturnTime) {
		this.servcReturnTime = servcReturnTime;
	}

	public String getServcIp() {
		return servcIp;
	}

	public void setServcIp(String servcIp) {
		this.servcIp = servcIp;
	}

	public String getReqSeqNo() {
		return reqSeqNo;
	}

	public void setReqSeqNo(String reqSeqNo) {
		this.reqSeqNo = reqSeqNo;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getServcSeqNo() {
		return servcSeqNo;
	}

	public void setServcSeqNo(String servcSeqNo) {
		this.servcSeqNo = servcSeqNo;
	}

	public String getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	public String reqReqStr(){
		return this.serverName.trim()+this.appName.trim()+this.reqSeqNo.trim();
	}
	
	
	

}
