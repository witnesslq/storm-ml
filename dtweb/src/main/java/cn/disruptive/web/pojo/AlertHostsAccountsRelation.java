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
 * AlertHostsAccountsRelation entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "alert_hosts_accounts_relation", catalog = "ops")
public class AlertHostsAccountsRelation implements java.io.Serializable {

	// Fields

	private AlertHostsAccountsRelationId id;
	private AlertHosts alertHosts;
	private AccountAccounts accountAccounts;

	// Constructors

	/** default constructor */
	public AlertHostsAccountsRelation() {
	}

	/** minimal constructor */
	public AlertHostsAccountsRelation(AlertHostsAccountsRelationId id) {
		this.id = id;
	}

	/** full constructor */
	public AlertHostsAccountsRelation(AlertHostsAccountsRelationId id,
			AlertHosts alertHosts, AccountAccounts accountAccounts) {
		this.id = id;
		this.alertHosts = alertHosts;
		this.accountAccounts = accountAccounts;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "alertHostId", column = @Column(name = "alert_host_id")),
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id")) })
	public AlertHostsAccountsRelationId getId() {
		return this.id;
	}

	public void setId(AlertHostsAccountsRelationId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_host_id", insertable = false, updatable = false)
	public AlertHosts getAlertHosts() {
		return this.alertHosts;
	}

	public void setAlertHosts(AlertHosts alertHosts) {
		this.alertHosts = alertHosts;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", insertable = false, updatable = false)
	public AccountAccounts getAccountAccounts() {
		return this.accountAccounts;
	}

	public void setAccountAccounts(AccountAccounts accountAccounts) {
		this.accountAccounts = accountAccounts;
	}

}