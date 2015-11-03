package cn.disruptive.web.vo;

/**
 * Description :场景码访问量统计
 * Date: 2015-09-17
 * @author Administrator
 *
 */
public class AllSceneCodeCountVo {
	
	private String sceneCode; //场景码
	private Integer allCount; //场景码访问总量
	private Integer successCount;//访问成功
	private Integer errorCount;//访问非全零
	private String sceneName;//场景名称
	
	public String getSceneName() {
		return sceneName;
	}
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
	public String getSceneCode() {
		return sceneCode;
	}
	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}
	public Integer getAllCount() {
		return allCount;
	}
	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}
	public Integer getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}
	public Integer getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

}
