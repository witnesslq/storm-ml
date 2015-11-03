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
 * IssueIssueAlerts entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "issue_issue_alerts", catalog = "ops")
public class IssueIssueAlerts implements java.io.Serializable {

	// Fields

	private Integer id;
	private IssueIssues issueIssues;
	private AccountAccounts accountAccounts;
	private Integer type;
	private Integer severity;
	private String body;
	private String sysop;
	private Date occurred;
	private Date created;
	private String data;

	// Constructors

	/** default constructor */
	public IssueIssueAlerts() {
	}

	/** full constructor */
	public IssueIssueAlerts(IssueIssues issueIssues,
			AccountAccounts accountAccounts, Integer type, Integer severity,
			String body, String sysop, Date occurred, Date created, String data) {
		this.issueIssues = issueIssues;
		this.accountAccounts = accountAccounts;
		this.type = type;
		this.severity = severity;
		this.body = body;
		this.sysop = sysop;
		this.occurred = occurred;
		this.created = created;
		this.data = data;
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
	@JoinColumn(name = "issue_id")
	public IssueIssues getIssueIssues() {
		return this.issueIssues;
	}

	public void setIssueIssues(IssueIssues issueIssues) {
		this.issueIssues = issueIssues;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id")
	public AccountAccounts getAccountAccounts() {
		return this.accountAccounts;
	}

	public void setAccountAccounts(AccountAccounts accountAccounts) {
		this.accountAccounts = accountAccounts;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "severity")
	public Integer getSeverity() {
		return this.severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	@Column(name = "body", length = 65535)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name = "sysop", length = 30)
	public String getSysop() {
		return this.sysop;
	}

	public void setSysop(String sysop) {
		this.sysop = sysop;
	}

	@Column(name = "occurred", length = 19)
	public Date getOccurred() {
		return this.occurred;
	}

	public void setOccurred(Date occurred) {
		this.occurred = occurred;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "data", length = 65535)
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

}