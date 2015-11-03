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
 * AccountLoginLogs entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "account_login_logs", catalog = "ops")
public class AccountLoginLogs implements java.io.Serializable {

	// Fields

	private Integer id;
	private AccountAccounts accountAccounts;
	private String remoteAddr;
	private Date created;

	// Constructors

	/** default constructor */
	public AccountLoginLogs() {
	}

	/** full constructor */
	public AccountLoginLogs(AccountAccounts accountAccounts, String remoteAddr,
			Date created) {
		this.accountAccounts = accountAccounts;
		this.remoteAddr = remoteAddr;
		this.created = created;
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
	@JoinColumn(name = "account_id")
	public AccountAccounts getAccountAccounts() {
		return this.accountAccounts;
	}

	public void setAccountAccounts(AccountAccounts accountAccounts) {
		this.accountAccounts = accountAccounts;
	}

	@Column(name = "remote_addr", length = 30)
	public String getRemoteAddr() {
		return this.remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}