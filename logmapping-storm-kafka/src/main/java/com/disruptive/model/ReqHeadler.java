package com.disruptive.model;

public class ReqHeadler {
	
	private String userId;
	private String reqTime;
	private String behavCode;
	private String servcId;
	private String reqSeqNo;
	private String bizTrackNo;
	private String uuId;
	private String orgnlSeqNo;
	

	public String getOrgnlSeqNo() {
		return orgnlSeqNo==null?"":orgnlSeqNo;
	}

	public void setOrgnlSeqNo(String orgnlSeqNo) {
		this.orgnlSeqNo = orgnlSeqNo;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getBehavCode() {
		return behavCode==null?"":behavCode;
	}

	public void setBehavCode(String behavCode) {
		this.behavCode = behavCode;
	}

	public String getServcId() {
		return servcId==null?"":servcId;
	}

	public void setServcId(String servcId) {
		this.servcId = servcId;
	}

	public String getReqSeqNo() {
		return reqSeqNo==null?"":reqSeqNo;
	}

	public void setReqSeqNo(String reqSeqNo) {
		this.reqSeqNo = reqSeqNo;
	}

	public String getBizTrackNo() {
		return bizTrackNo==null?"":bizTrackNo;
	}

	public void setBizTrackNo(String bizTrackNo) {
		this.bizTrackNo = bizTrackNo;
	}

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
