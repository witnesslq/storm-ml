package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ServerAuthProjectsVersionId entity. @author Eclipse Persistence Tools
 */
@Embeddable
public class ServerAuthProjectsVersionId implements java.io.Serializable {

	// Fields

	private Integer projectId;
	private Integer accountId;
	private long transactionId;

	// Constructors

	/** default constructor */
	public ServerAuthProjectsVersionId() {
	}

	/** full constructor */
	public ServerAuthProjectsVersionId(Integer projectId, Integer accountId,
			long transactionId) {
		this.projectId = projectId;
		this.accountId = accountId;
		this.transactionId = transactionId;
	}

	// Property accessors

	@Column(name = "project_id", nullable = false)
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(name = "account_id", nullable = false)
	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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
		if (!(other instanceof ServerAuthProjectsVersionId))
			return false;
		ServerAuthProjectsVersionId castOther = (ServerAuthProjectsVersionId) other;

		return ((this.getProjectId() == castOther.getProjectId()) || (this
				.getProjectId() != null && castOther.getProjectId() != null && this
				.getProjectId().equals(castOther.getProjectId())))
				&& ((this.getAccountId() == castOther.getAccountId()) || (this
						.getAccountId() != null
						&& castOther.getAccountId() != null && this
						.getAccountId().equals(castOther.getAccountId())))
				&& (this.getTransactionId() == castOther.getTransactionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getProjectId() == null ? 0 : this.getProjectId().hashCode());
		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result + (int) this.getTransactionId();
		return result;
	}

}