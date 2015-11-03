package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AccountAccountsRolesRelationVersion entity. @author Eclipse Persistence
 * Tools
 */
@Entity
@Table(name = "account_accounts_roles_relation_version", catalog = "ops")
public class AccountAccountsRolesRelationVersion implements
		java.io.Serializable {

	// Fields

	private AccountAccountsRolesRelationVersionId id;
	private long endTransactionId;
	private short operationType;

	// Constructors

	/** default constructor */
	public AccountAccountsRolesRelationVersion() {
	}

	/** minimal constructor */
	public AccountAccountsRolesRelationVersion(
			AccountAccountsRolesRelationVersionId id, short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public AccountAccountsRolesRelationVersion(
			AccountAccountsRolesRelationVersionId id, long endTransactionId,
			short operationType) {
		this.id = id;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id", nullable = false)),
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public AccountAccountsRolesRelationVersionId getId() {
		return this.id;
	}

	public void setId(AccountAccountsRolesRelationVersionId id) {
		this.id = id;
	}

	@Column(name = "end_transaction_id")
	public long getEndTransactionId() {
		return this.endTransactionId;
	}

	public void setEndTransactionId(long endTransactionId) {
		this.endTransactionId = endTransactionId;
	}

	@Column(name = "operation_type", nullable = false)
	public short getOperationType() {
		return this.operationType;
	}

	public void setOperationType(short operationType) {
		this.operationType = operationType;
	}

}