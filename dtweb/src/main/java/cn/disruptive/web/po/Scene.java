package cn.disruptive.web.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.disruptive.core.po.PO;

/**
 * 
 * Description：交易场景列表
 * Data：		2015年9月14日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_scene")
public class Scene implements PO<Integer>, Serializable {
	
	private static final long serialVersionUID = -1929725645270200670L;

	private Integer id;
	private Integer sceneNumber;	// 序号
	private String sceneGroup;		// 场景组
	private String sceneGroupCode;	// 场景组编号
	private String sceneName;		// 交易场景名称
	private String sceneCode;		// 场景编号
	private String sceneNameEn;		// 英文全称
	private String sceneNameEns;	// 英文缩写
	private String sceneDes;		// 备注
	private String sceneVersion;	// 版本号
	private Integer isDelete;		// 是否删除，1：删除，0：未删除

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "sceneNumber")
	public Integer getSceneNumber() {
		return sceneNumber;
	}

	@Column(name = "sceneGroup", length = 20)
	public String getSceneGroup() {
		return sceneGroup;
	}

	@Column(name = "sceneGroupCode", length = 20)
	public String getSceneGroupCode() {
		return sceneGroupCode;
	}

	@Column(name = "sceneName", length = 200)
	public String getSceneName() {
		return sceneName;
	}

	@Column(name = "sceneCode", length = 20)
	public String getSceneCode() {
		return sceneCode;
	}

	@Column(name = "sceneNameEn", length = 20)
	public String getSceneNameEn() {
		return sceneNameEn;
	}

	@Column(name = "sceneNameEns", length = 20)
	public String getSceneNameEns() {
		return sceneNameEns;
	}

	@Column(name = "sceneDes", length = 1000)
	public String getSceneDes() {
		return sceneDes;
	}

	@Column(name = "sceneVersion", length = 20)
	public String getSceneVersion() {
		return sceneVersion;
	}

	@Column(name = "isDelete")
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSceneNumber(Integer sceneNumber) {
		this.sceneNumber = sceneNumber;
	}

	public void setSceneGroup(String sceneGroup) {
		this.sceneGroup = sceneGroup;
	}

	public void setSceneGroupCode(String sceneGroupCode) {
		this.sceneGroupCode = sceneGroupCode;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}

	public void setSceneNameEn(String sceneNameEn) {
		this.sceneNameEn = sceneNameEn;
	}

	public void setSceneNameEns(String sceneNameEns) {
		this.sceneNameEns = sceneNameEns;
	}

	public void setSceneDes(String sceneDes) {
		this.sceneDes = sceneDes;
	}

	public void setSceneVersion(String sceneVersion) {
		this.sceneVersion = sceneVersion;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
}
