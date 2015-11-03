package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AlertHostsAccountsRelationVersionId entity. @author Eclipse Persistence
 * Tools
 */
@Embeddable
public class AlertHostsAccountsRelationVersionId implements
		java.io.Serializable {

	// Fields

	private Integer alertHostId;
	private Integer accountId;
	private long transactionId;

	// Constructors

	/** default constructor */
	public AlertHostsAccountsRelationVersionId() {
	}

	/** full constructor */
	public AlertHostsAccountsRelationVersionId(Integer alertHostId,
			Integer accountId, long transactionId) {
		this.alertHostId = alertHostId;
		this.accountId = accountId;
		this.transactionId = transactionId;
	}

	// Property accessors

	@Column(name = "alert_host_id", nullable = false)
	public Integer getAlertHostId() {
		return this.alertHostId;
	}

	public void setAlertHostId(Integer alertHostId) {
		this.alertHostId = alertHostId;
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
		if (!(other instanceof AlertHostsAccountsRelationVersionId))
			return false;
		AlertHostsAccountsRelationVersionId castOther = (AlertHostsAccountsRelationVersionId) other;

		return ((this.getAlertHostId() == castOther.getAlertHostId()) || (this
				.getAlertHostId() != null && castOther.getAlertHostId() != null && this
				.getAlertHostId().equals(castOther.getAlertHostId())))
				&& ((this.getAccountId() == castOther.getAccountId()) || (this
						.getAccountId() != null
						&& castOther.getAccountId() != null && this
						.getAccountId().equals(castOther.getAccountId())))
				&& (this.getTransactionId() == castOther.getTransactionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAlertHostId() == null ? 0 : this.getAlertHostId()
						.hashCode());
		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result + (int) this.getTransactionId();
		return result;
	}

}