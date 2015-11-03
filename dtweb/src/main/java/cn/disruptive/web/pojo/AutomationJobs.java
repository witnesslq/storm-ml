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
 * AutomationJobs entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "automation_jobs", catalog = "ops")
public class AutomationJobs implements java.io.Serializable {

	// Fields

	private String id;
	private AccountAccounts accountAccounts;
	private String fun;
	private String funArgs;
	private Integer targetCount;
	private Integer returnCount;
	private Integer offlineCount;
	private String type;
	private Integer status;
	private Date created;
	private Date finished;
	private String projectName;
	private String environmentEnName;
	private String scmRevision;
	private String scmBranch;
	private boolean isTest;

	// Constructors

	/** default constructor */
	public AutomationJobs() {
	}

	/** full constructor */
	public AutomationJobs(AccountAccounts accountAccounts, String fun,
			String funArgs, Integer targetCount, Integer returnCount,
			Integer offlineCount, String type, Integer status, Date created,
			Date finished, String projectName, String environmentEnName,
			String scmRevision, String scmBranch, boolean isTest) {
		this.accountAccounts = accountAccounts;
		this.fun = fun;
		this.funArgs = funArgs;
		this.targetCount = targetCount;
		this.returnCount = returnCount;
		this.offlineCount = offlineCount;
		this.type = type;
		this.status = status;
		this.created = created;
		this.finished = finished;
		this.projectName = projectName;
		this.environmentEnName = environmentEnName;
		this.scmRevision = scmRevision;
		this.scmBranch = scmBranch;
		this.isTest = isTest;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sysop_id")
	public AccountAccounts getAccountAccounts() {
		return this.accountAccounts;
	}

	public void setAccountAccounts(AccountAccounts accountAccounts) {
		this.accountAccounts = accountAccounts;
	}

	@Column(name = "fun", length = 50)
	public String getFun() {
		return this.fun;
	}

	public void setFun(String fun) {
		this.fun = fun;
	}

	@Column(name = "fun_args")
	public String getFunArgs() {
		return this.funArgs;
	}

	public void setFunArgs(String funArgs) {
		this.funArgs = funArgs;
	}

	@Column(name = "target_count")
	public Integer getTargetCount() {
		return this.targetCount;
	}

	public void setTargetCount(Integer targetCount) {
		this.targetCount = targetCount;
	}

	@Column(name = "_return_count")
	public Integer getReturnCount() {
		return this.returnCount;
	}

	public void setReturnCount(Integer returnCount) {
		this.returnCount = returnCount;
	}

	@Column(name = "offline_count")
	public Integer getOfflineCount() {
		return this.offlineCount;
	}

	public void setOfflineCount(Integer offlineCount) {
		this.offlineCount = offlineCount;
	}

	@Column(name = "type", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	@Column(name = "finished", length = 19)
	public Date getFinished() {
		return this.finished;
	}

	public void setFinished(Date finished) {
		this.finished = finished;
	}

	@Column(name = "project_name")
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "environment_en_name")
	public String getEnvironmentEnName() {
		return this.environmentEnName;
	}

	public void setEnvironmentEnName(String environmentEnName) {
		this.environmentEnName = environmentEnName;
	}

	@Column(name = "scm_revision", length = 50)
	public String getScmRevision() {
		return this.scmRevision;
	}

	public void setScmRevision(String scmRevision) {
		this.scmRevision = scmRevision;
	}

	@Column(name = "scm_branch", length = 50)
	public String getScmBranch() {
		return this.scmBranch;
	}

	public void setScmBranch(String scmBranch) {
		this.scmBranch = scmBranch;
	}

	@Column(name = "is_test")
	public boolean getIsTest() {
		return this.isTest;
	}

	public void setIsTest(boolean isTest) {
		this.isTest = isTest;
	}

}