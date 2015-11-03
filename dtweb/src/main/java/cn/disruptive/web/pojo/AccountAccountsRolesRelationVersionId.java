package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AccountAccountsRolesRelationVersionId entity. @author Eclipse Persistence
 * Tools
 */
@Embeddable
public class AccountAccountsRolesRelationVersionId implements
		java.io.Serializable {

	// Fields

	private Integer accountId;
	private Integer roleId;
	private long transactionId;

	// Constructors

	/** default constructor */
	public AccountAccountsRolesRelationVersionId() {
	}

	/** full constructor */
	public AccountAccountsRolesRelationVersionId(Integer accountId,
			Integer roleId, long transactionId) {
		this.accountId = accountId;
		this.roleId = roleId;
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

	@Column(name = "role_id", nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
		if (!(other instanceof AccountAccountsRolesRelationVersionId))
			return false;
		AccountAccountsRolesRelationVersionId castOther = (AccountAccountsRolesRelationVersionId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null && castOther.getAccountId() != null && this
				.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null && castOther.getRoleId() != null && this
						.getRoleId().equals(castOther.getRoleId())))
				&& (this.getTransactionId() == castOther.getTransactionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result + (int) this.getTransactionId();
		return result;
	}

}