package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * Description：项目测试人员关系
 * Data：		2015年10月26日
 */
@Embeddable
public class AssetProjectsTestersRelationId implements java.io.Serializable {

	private Integer accountId;	// 人员id
	private Integer projectId;	// 项目id

	// Constructors

	/** default constructor */
	public AssetProjectsTestersRelationId() {
	}

	/** full constructor */
	public AssetProjectsTestersRelationId(Integer accountId, Integer projectId) {
		this.accountId = accountId;
		this.projectId = projectId;
	}

	// Property accessors

	@Column(name = "account_id", nullable = false)
	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@Column(name = "project_id", nullable = false)
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AssetProjectsTestersRelationId))
			return false;
		AssetProjectsTestersRelationId castOther = (AssetProjectsTestersRelationId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null && castOther.getAccountId() != null && this
				.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getProjectId() == castOther.getProjectId()) || (this
						.getProjectId() != null
						&& castOther.getProjectId() != null && this
						.getProjectId().equals(castOther.getProjectId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getProjectId() == null ? 0 : this.getProjectId().hashCode());
		return result;
	}

}