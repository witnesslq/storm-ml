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
 * IssueIssueNotes entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "issue_issue_notes", catalog = "ops")
public class IssueIssueNotes implements java.io.Serializable {

	// Fields

	private Integer id;
	private IssueIssues issueIssues;
	private AccountAccounts accountAccounts;
	private String body;
	private Date created;
	private Integer issuePrevStatus;
	private Integer issueStatus;
	private String issueStatusNote;
	private boolean issueStatusChanged;

	// Constructors

	/** default constructor */
	public IssueIssueNotes() {
	}

	/** full constructor */
	public IssueIssueNotes(IssueIssues issueIssues,
			AccountAccounts accountAccounts, String body, Date created,
			Integer issuePrevStatus, Integer issueStatus,
			String issueStatusNote, boolean issueStatusChanged) {
		this.issueIssues = issueIssues;
		this.accountAccounts = accountAccounts;
		this.body = body;
		this.created = created;
		this.issuePrevStatus = issuePrevStatus;
		this.issueStatus = issueStatus;
		this.issueStatusNote = issueStatusNote;
		this.issueStatusChanged = issueStatusChanged;
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

	@Column(name = "body", length = 65535)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "issue_prev_status")
	public Integer getIssuePrevStatus() {
		return this.issuePrevStatus;
	}

	public void setIssuePrevStatus(Integer issuePrevStatus) {
		this.issuePrevStatus = issuePrevStatus;
	}

	@Column(name = "issue_status")
	public Integer getIssueStatus() {
		return this.issueStatus;
	}

	public void setIssueStatus(Integer issueStatus) {
		this.issueStatus = issueStatus;
	}

	@Column(name = "issue_status_note")
	public String getIssueStatusNote() {
		return this.issueStatusNote;
	}

	public void setIssueStatusNote(String issueStatusNote) {
		this.issueStatusNote = issueStatusNote;
	}

	@Column(name = "issue_status_changed")
	public boolean getIssueStatusChanged() {
		return this.issueStatusChanged;
	}

	public void setIssueStatusChanged(boolean issueStatusChanged) {
		this.issueStatusChanged = issueStatusChanged;
	}

}