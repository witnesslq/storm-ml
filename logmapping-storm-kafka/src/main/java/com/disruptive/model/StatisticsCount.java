package com.disruptive.model;

import java.util.Date;

public class StatisticsCount {

	private int id;
	private Date count_time;
	private Integer tradingVolume;
	private Integer tradingBase;
	private String sceneGroup;
	private String sceneCode;
	private Integer failureQuantity;
	private Integer sysFailure;
	private Double average_time;
	private Integer isDelete;
	private String extend1;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCount_time() {
		return count_time;
	}
	public void setCount_time(Date count_time) {
		this.count_time = count_time;
	}
	public Integer getTradingVolume() {
		return tradingVolume;
	}
	public void setTradingVolume(Integer tradingVolume) {
		this.tradingVolume = tradingVolume;
	}
	public Integer getTradingBase() {
		return tradingBase;
	}
	public void setTradingBase(Integer tradingBase) {
		this.tradingBase = tradingBase;
	}
	public String getSceneGroup() {
		return sceneGroup;
	}
	public void setSceneGroup(String sceneGroup) {
		this.sceneGroup = sceneGroup;
	}
	public String getSceneCode() {
		return sceneCode;
	}
	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}
	public Integer getFailureQuantity() {
		return failureQuantity;
	}
	public void setFailureQuantity(Integer failureQuantity) {
		this.failureQuantity = failureQuantity;
	}
	public Integer getSysFailure() {
		return sysFailure;
	}
	public void setSysFailure(Integer sysFailure) {
		this.sysFailure = sysFailure;
	}
	public Double getAverage_time() {
		return average_time;
	}
	public void setAverage_time(Double average_time) {
		this.average_time = average_time;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getExtend1() {
		return extend1;
	}
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	
}
