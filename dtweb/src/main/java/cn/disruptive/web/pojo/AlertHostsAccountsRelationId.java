package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AlertHostsAccountsRelationId entity. @author Eclipse Persistence Tools
 */
@Embeddable
public class AlertHostsAccountsRelationId implements java.io.Serializable {

	// Fields

	private Integer alertHostId;
	private Integer accountId;

	// Constructors

	/** default constructor */
	public AlertHostsAccountsRelationId() {
	}

	/** full constructor */
	public AlertHostsAccountsRelationId(Integer alertHostId, Integer accountId) {
		this.alertHostId = alertHostId;
		this.accountId = accountId;
	}

	// Property accessors

	@Column(name = "alert_host_id")
	public Integer getAlertHostId() {
		return this.alertHostId;
	}

	public void setAlertHostId(Integer alertHostId) {
		this.alertHostId = alertHostId;
	}

	@Column(name = "account_id")
	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AlertHostsAccountsRelationId))
			return false;
		AlertHostsAccountsRelationId castOther = (AlertHostsAccountsRelationId) other;

		return ((this.getAlertHostId() == castOther.getAlertHostId()) || (this
				.getAlertHostId() != null && castOther.getAlertHostId() != null && this
				.getAlertHostId().equals(castOther.getAlertHostId())))
				&& ((this.getAccountId() == castOther.getAccountId()) || (this
						.getAccountId() != null
						&& castOther.getAccountId() != null && this
						.getAccountId().equals(castOther.getAccountId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAlertHostId() == null ? 0 : this.getAlertHostId()
						.hashCode());
		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		return result;
	}

}