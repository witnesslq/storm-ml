package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AccountAccountsRolesRelation entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "account_accounts_roles_relation", catalog = "ops")
public class AccountAccountsRolesRelation implements java.io.Serializable {

	// Fields

	private AccountAccountsRolesRelationId id;
	private AccountRoles accountRoles;
	private AccountAccounts accountAccounts;

	// Constructors

	/** default constructor */
	public AccountAccountsRolesRelation() {
	}

	/** full constructor */
	public AccountAccountsRolesRelation(AccountAccountsRolesRelationId id,
			AccountRoles accountRoles, AccountAccounts accountAccounts) {
		this.id = id;
		this.accountRoles = accountRoles;
		this.accountAccounts = accountAccounts;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id", nullable = false)),
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)) })
	public AccountAccountsRolesRelationId getId() {
		return this.id;
	}

	public void setId(AccountAccountsRolesRelationId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
	public AccountRoles getAccountRoles() {
		return this.accountRoles;
	}

	public void setAccountRoles(AccountRoles accountRoles) {
		this.accountRoles = accountRoles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false, insertable = false, updatable = false)
	public AccountAccounts getAccountAccounts() {
		return this.accountAccounts;
	}

	public void setAccountAccounts(AccountAccounts accountAccounts) {
		this.accountAccounts = accountAccounts;
	}

}