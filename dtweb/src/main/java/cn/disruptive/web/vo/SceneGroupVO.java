package cn.disruptive.web.vo;

/**
 * 
 * Description：场景组错误信息
 * Data：		2015年9月25日
 */
public class SceneGroupVO {

	private Integer sumNum;			// 总数
	private Integer rightNum;		// 正确次数
	private Integer errNum;			// 错误次数
	private String sceneGroupName; 	// 场景组名称
	private String sceneNameEns;	// 场景组缩写名

	public Integer getSumNum() {
		return sumNum;
	}

	public void setSumNum(Integer sumNum) {
		this.sumNum = sumNum;
	}

	public Integer getRightNum() {
		return rightNum;
	}

	public void setRightNum(Integer rightNum) {
		this.rightNum = rightNum;
	}
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
