package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AccountAccountsRolesRelationId entity. @author Eclipse Persistence Tools
 */
@Embeddable
public class AccountAccountsRolesRelationId implements java.io.Serializable {

	// Fields

	private Integer accountId;
	private Integer roleId;

	// Constructors

	/** default constructor */
	public AccountAccountsRolesRelationId() {
	}

	/** full constructor */
	public AccountAccountsRolesRelationId(Integer accountId, Integer roleId) {
		this.accountId = accountId;
		this.roleId = roleId;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AccountAccountsRolesRelationId))
			return false;
		AccountAccountsRolesRelationId castOther = (AccountAccountsRolesRelationId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null && castOther.getAccountId() != null && this
				.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null && castOther.getRoleId() != null && this
						.getRoleId().equals(castOther.getRoleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		return result;
	}

}