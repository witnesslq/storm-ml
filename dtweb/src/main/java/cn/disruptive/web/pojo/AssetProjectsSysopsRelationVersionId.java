package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * Description：项目和系统操作人员的关系 版本
 * Data：		2015年10月26日
 */
@Embeddable
public class AssetProjectsSysopsRelationVersionId implements java.io.Serializable {

	private Integer accountId;	// 人员id
	private Integer projectId;	// 项目id
	private long transactionId;	// 变更id

	/** default constructor */
	public AssetProjectsSysopsRelationVersionId() {
	}

	/** full constructor */
	public AssetProjectsSysopsRelationVersionId(Integer accountId,
			Integer projectId, long transactionId) {
		this.accountId = accountId;
		this.projectId = projectId;
		this.transactionId = transactionId;
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

	@Column(name = "transaction_id", nullable = false)
	public long getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AssetProjectsSysopsRelationVersionId))
			return false;
		AssetProjectsSysopsRelationVersionId castOther = (AssetProjectsSysopsRelationVersionId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null && castOther.getAccountId() != null && this
				.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getProjectId() == castOther.getProjectId()) || (this
						.getProjectId() != null
						&& castOther.getProjectId() != null && this
						.getProjectId().equals(castOther.getProjectId())))
				&& (this.getTransactionId() == castOther.getTransactionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getProjectId() == null ? 0 : this.getProjectId().hashCode());
		result = 37 * result + (int) this.getTransactionId();
		return result;
	}

}