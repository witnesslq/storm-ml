package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AlertHostsAccountsRelationVersion entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "alert_hosts_accounts_relation_version", catalog = "ops")
public class AlertHostsAccountsRelationVersion implements java.io.Serializable {

	// Fields

	private AlertHostsAccountsRelationVersionId id;
	private long endTransactionId;
	private short operationType;

	// Constructors

	/** default constructor */
	public AlertHostsAccountsRelationVersion() {
	}

	/** minimal constructor */
	public AlertHostsAccountsRelationVersion(
			AlertHostsAccountsRelationVersionId id, short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public AlertHostsAccountsRelationVersion(
			AlertHostsAccountsRelationVersionId id, long endTransactionId,
			short operationType) {
		this.id = id;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "alertHostId", column = @Column(name = "alert_host_id", nullable = false)),
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public AlertHostsAccountsRelationVersionId getId() {
		return this.id;
	}

	public void setId(AlertHostsAccountsRelationVersionId id) {
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