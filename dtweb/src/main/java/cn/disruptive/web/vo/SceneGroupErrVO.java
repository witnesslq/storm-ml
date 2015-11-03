package cn.disruptive.web.vo;

/**
 * 
 * Description：场景组错误信息
 * Data：		2015年9月14日
 */
public class SceneGroupErrVO {

	private Integer errNum;			// 错误次数
	private String sceneGroupName; // 场景组名称
	private String sceneNameEns;	// 场景组缩写名

	public Integer getErrNum() {
		return errNum;
	}

	public void setErrNum(Integer errNum) {
		this.errNum = errNum;
	}

	public String getSceneGroupName() {
		return sceneGroupName;
	}

	public void setSceneGroupName(String sceneGroupName) {
		this.sceneGroupName = sceneGroupName;
	}

	public String getSceneNameEns() {
		return sceneNameEns;
	}

	public void setSceneNameEns(String sceneNameEns) {
		this.sceneNameEns = sceneNameEns;
	}

}
