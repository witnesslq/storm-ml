package com.disruptive.model;

import java.util.Date;

public class InterfaceTimeOut {
	
	private int id;
	private String appName;
	private String serverName;
	private String seneCode;
	private String interfaceName;
	private Integer timeOut;
	private String trackNo;
	private String rowKey;
	private String reqRowKey;
	private String resRowKey;
	private Date recordTime;
	private String recordTimeStr;
	private String respCode;
	private Integer isException;
	private String sceneNameEns;
	private String bizTrackNo;
	private String orgnlSeqNo;
	public String getBizTrackNo() {
		return bizTrackNo;
	}
	public void setBizTrackNo(String bizTrackNo) {
		this.bizTrackNo = bizTrackNo;
	}
	public String getOrgnlSeqNo() {
		return orgnlSeqNo;
	}
	public void setOrgnlSeqNo(String orgnlSeqNo) {
		this.orgnlSeqNo = orgnlSeqNo;
	}
	public Integer getIsException() {
		return isException;
	}
	public void setIsException(Integer isException) {
		this.isException = isException;
	}
	public String getSceneNameEns() {
		return sceneNameEns;
	}
	public void setSceneNameEns(String sceneNameEns) {
		this.sceneNameEns = sceneNameEns;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getSeneCode() {
		return seneCode;
	}
	public void setSeneCode(String seneCode) {
		this.seneCode = seneCode;
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
	public String getRowKey() {
		return rowKey;
	}
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	public String getReqRowKey() {
		return reqRowKey;
	}
	public void setReqRowKey(String reqRowKey) {
		this.reqRowKey = reqRowKey;
	}
	public String getResRowKey() {
		return resRowKey;
	}
	public void setResRowKey(String resRowKey) {
		this.resRowKey = resRowKey;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getRecordTimeStr() {
		return recordTimeStr;
	}
	public void setRecordTimeStr(String recordTimeStr) {
		this.recordTimeStr = recordTimeStr;
	}

}
