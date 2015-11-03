package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AlertToggleStatusNotes entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "alert_toggle_status_notes", catalog = "ops")
public class AlertToggleStatusNotes implements java.io.Serializable {

	// Fields

	private Integer id;
	private AccountAccounts accountAccounts;
	private AlertHosts alertHosts;
	private String host;
	private String status;
	private boolean enabled;
	private Date created;
	private Date emailNotified;
	private boolean needNotify;
	private Integer type;

	// Constructors

	/** default constructor */
	public AlertToggleStatusNotes() {
	}

	/** full constructor */
	public AlertToggleStatusNotes(AccountAccounts accountAccounts,
			AlertHosts alertHosts, String host, String status, boolean enabled,
			Date created, Date emailNotified, boolean needNotify, Integer type) {
		this.accountAccounts = accountAccounts;
		this.alertHosts = alertHosts;
		this.host = host;
		this.status = status;
		this.enabled = enabled;
		this.created = created;
		this.emailNotified = emailNotified;
		this.needNotify = needNotify;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id")
	public AccountAccounts getAccountAccounts() {
		return this.accountAccounts;
	}

	public void setAccountAccounts(AccountAccounts accountAccounts) {
		this.accountAccounts = accountAccounts;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_id")
	public AlertHosts getAlertHosts() {
		return this.alertHosts;
	}

	public void setAlertHosts(AlertHosts alertHosts) {
		this.alertHosts = alertHosts;
	}

	@Column(name = "host", length = 50)
	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Column(name = "status", length = 50)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "enabled")
	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "email_notified", length = 19)
	public Date getEmailNotified() {
		return this.emailNotified;
	}

	public void setEmailNotified(Date emailNotified) {
		this.emailNotified = emailNotified;
	}

	@Column(name = "need_notify")
	public boolean getNeedNotify() {
		return this.needNotify;
	}

	public void setNeedNotify(boolean needNotify) {
		this.needNotify = needNotify;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}