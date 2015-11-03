package cn.disruptive.web.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * Description：问题集
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "issue_issues", catalog = "ops")
public class IssueIssues implements java.io.Serializable {

	// Fields

	private Integer id;
	private AssetProjects assetProjects;
	private AccountAccounts accountAccountsBySolverId;
	private AlertServices alertServices;
	private AccountAccounts accountAccountsByCreatorId;
	private AssetHosts assetHosts;
	private AccountAccounts accountAccountsByOwnerId;
	private AccountAccounts accountAccountsByCaretakerId;
	private AccountAccounts accountAccountsByContacteeId;
	private String title;
	private String body;
	private Date contacted;
	private Integer type;
	private Integer alertSeverity;
	private Integer alertTimes;
	private Integer status;
	private Date created;
	private Date updated;
	private Date solved;
	private Date occurred;
	private Date lastOccurred;
	private Date resumed;
	private Integer triggerId;
	private String triggerName;
	private Set<IssueIssueNotes> issueIssueNoteses = new HashSet<IssueIssueNotes>(
			0);
	private Set<IssueIssueAlerts> issueIssueAlertses = new HashSet<IssueIssueAlerts>(
			0);

	// Constructors

	/** default constructor */
	public IssueIssues() {
	}

	/** full constructor */
	public IssueIssues(AssetProjects assetProjects,
			AccountAccounts accountAccountsBySolverId,
			AlertServices alertServices,
			AccountAccounts accountAccountsByCreatorId, AssetHosts assetHosts,
			AccountAccounts accountAccountsByOwnerId,
			AccountAccounts accountAccountsByCaretakerId,
			AccountAccounts accountAccountsByContacteeId, String title,
			String body, Date contacted, Integer type, Integer alertSeverity,
			Integer alertTimes, Integer status, Date created, Date updated,
			Date solved, Date occurred, Date lastOccurred, Date resumed,
			Integer triggerId, String triggerName,
			Set<IssueIssueNotes> issueIssueNoteses,
			Set<IssueIssueAlerts> issueIssueAlertses) {
		this.assetProjects = assetProjects;
		this.accountAccountsBySolverId = accountAccountsBySolverId;
		this.alertServices = alertServices;
		this.accountAccountsByCreatorId = accountAccountsByCreatorId;
		this.assetHosts = assetHosts;
		this.accountAccountsByOwnerId = accountAccountsByOwnerId;
		this.accountAccountsByCaretakerId = accountAccountsByCaretakerId;
		this.accountAccountsByContacteeId = accountAccountsByContacteeId;
		this.title = title;
		this.body = body;
		this.contacted = contacted;
		this.type = type;
		this.alertSeverity = alertSeverity;
		this.alertTimes = alertTimes;
		this.status = status;
		this.created = created;
		this.updated = updated;
		this.solved = solved;
		this.occurred = occurred;
		this.lastOccurred = lastOccurred;
		this.resumed = resumed;
		this.triggerId = triggerId;
		this.triggerName = triggerName;
		this.issueIssueNoteses = issueIssueNoteses;
		this.issueIssueAlertses = issueIssueAlertses;
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
	@JoinColumn(name = "project_id")
	public AssetProjects getAssetProjects() {
		return this.assetProjects;
	}

	public void setAssetProjects(AssetProjects assetProjects) {
		this.assetProjects = assetProjects;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "solver_id")
	public AccountAccounts getAccountAccountsBySolverId() {
		return this.accountAccountsBySolverId;
	}

	public void setAccountAccountsBySolverId(
			AccountAccounts accountAccountsBySolverId) {
		this.accountAccountsBySolverId = accountAccountsBySolverId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_service_id")
	public AlertServices getAlertServices() {
		return this.alertServices;
	}

	public void setAlertServices(AlertServices alertServices) {
		this.alertServices = alertServices;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id")
	public AccountAccounts getAccountAccountsByCreatorId() {
		return this.accountAccountsByCreatorId;
	}

	public void setAccountAccountsByCreatorId(
			AccountAccounts accountAccountsByCreatorId) {
		this.accountAccountsByCreatorId = accountAccountsByCreatorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "host_id")
	public AssetHosts getAssetHosts() {
		return this.assetHosts;
	}

	public void setAssetHosts(AssetHosts assetHosts) {
		this.assetHosts = assetHosts;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	public AccountAccounts getAccountAccountsByOwnerId() {
		return this.accountAccountsByOwnerId;
	}

	public void setAccountAccountsByOwnerId(
			AccountAccounts accountAccountsByOwnerId) {
		this.accountAccountsByOwnerId = accountAccountsByOwnerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caretaker_id")
	public AccountAccounts getAccountAccountsByCaretakerId() {
		return this.accountAccountsByCaretakerId;
	}

	public void setAccountAccountsByCaretakerId(
			AccountAccounts accountAccountsByCaretakerId) {
		this.accountAccountsByCaretakerId = accountAccountsByCaretakerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contactee_id")
	public AccountAccounts getAccountAccountsByContacteeId() {
		return this.accountAccountsByContacteeId;
	}

	public void setAccountAccountsByContacteeId(
			AccountAccounts accountAccountsByContacteeId) {
		this.accountAccountsByContacteeId = accountAccountsByContacteeId;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "body", length = 65535)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name = "contacted", length = 19)
	public Date getContacted() {
		return this.contacted;
	}

	public void setContacted(Date contacted) {
		this.contacted = contacted;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "alert_severity")
	public Integer getAlertSeverity() {
		return this.alertSeverity;
	}

	public void setAlertSeverity(Integer alertSeverity) {
		this.alertSeverity = alertSeverity;
	}

	@Column(name = "alert_times")
	public Integer getAlertTimes() {
		return this.alertTimes;
	}

	public void setAlertTimes(Integer alertTimes) {
		this.alertTimes = alertTimes;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "updated", length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "solved", length = 19)
	public Date getSolved() {
		return this.solved;
	}

	public void setSolved(Date solved) {
		this.solved = solved;
	}

	@Column(name = "occurred", length = 19)
	public Date getOccurred() {
		return this.occurred;
	}

	public void setOccurred(Date occurred) {
		this.occurred = occurred;
	}

	@Column(name = "last_occurred", length = 19)
	public Date getLastOccurred() {
		return this.lastOccurred;
	}

	public void setLastOccurred(Date lastOccurred) {
		this.lastOccurred = lastOccurred;
	}

	@Column(name = "resumed", length = 19)
	public Date getResumed() {
		return this.resumed;
	}

	public void setResumed(Date resumed) {
		this.resumed = resumed;
	}

	@Column(name = "trigger_id")
	public Integer getTriggerId() {
		return this.triggerId;
	}

	public void setTriggerId(Integer triggerId) {
		this.triggerId = triggerId;
	}

	@Column(name = "trigger_name")
	public String getTriggerName() {
		return this.triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "issueIssues")
	public Set<IssueIssueNotes> getIssueIssueNoteses() {
		return this.issueIssueNoteses;
	}

	public void setIssueIssueNoteses(Set<IssueIssueNotes> issueIssueNoteses) {
		this.issueIssueNoteses = issueIssueNoteses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "issueIssues")
	public Set<IssueIssueAlerts> getIssueIssueAlertses() {
		return this.issueIssueAlertses;
	}

	public void setIssueIssueAlertses(Set<IssueIssueAlerts> issueIssueAlertses) {
		this.issueIssueAlertses = issueIssueAlertses;
	}

}