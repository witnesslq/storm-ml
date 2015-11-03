package cn.disruptive.web.vo;

/**
 * 
 * Description：失败业务耗时Top10
 * Data：		2015年9月28日
 */
public class TimeOutTop10VO {
	private Integer id;			// 数据库记录的id
	private String sceneCode; 	// 场景码
	private String isException;	// 是否错误
	private String bizTrackNo;	// 业务追踪号
	private Integer timeOut;	// 消耗时间
	private String sceneName;	// 交易场景名称
	private String sceneNameEns;// 场景英文缩写
	private String sceneDes;	// 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getSceneCode() {
		return sceneCode;
	}
	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}
	public String getIsException() {
		return isException;
	}
	public void setIsException(String isException) {
		this.isException = isException;
	}
	public String getBizTrackNo() {
		return bizTrackNo;
	}
	public void setBizTrackNo(String bizTrackNo) {
		this.bizTrackNo = bizTrackNo;
	}

	public Integer getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
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

}
