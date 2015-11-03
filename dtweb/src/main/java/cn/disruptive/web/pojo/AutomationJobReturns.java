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
 * 
 * Description：作业自动回滚
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "automation_job_returns", catalog = "ops")
public class AutomationJobReturns implements java.io.Serializable {

	// Fields

	private Integer id;
	private AssetHosts assetHosts;
	private String jobId;
	private String ipAddr;
	private String minionId;
	private String fun;
	private String text;
	private Integer code;
	private boolean success;
	private Integer errorCount;
	private Integer status;
	private String data;
	private Date created;
	private Date updated;
	private Date finished;

	// Constructors

	/** default constructor */
	public AutomationJobReturns() {
	}

	/** full constructor */
	public AutomationJobReturns(AssetHosts assetHosts, String jobId,
			String ipAddr, String minionId, String fun, String text,
			Integer code, boolean success, Integer errorCount, Integer status,
			String data, Date created, Date updated, Date finished) {
		this.assetHosts = assetHosts;
		this.jobId = jobId;
		this.ipAddr = ipAddr;
		this.minionId = minionId;
		this.fun = fun;
		this.text = text;
		this.code = code;
		this.success = success;
		this.errorCount = errorCount;
		this.status = status;
		this.data = data;
		this.created = created;
		this.updated = updated;
		this.finished = finished;
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
	@JoinColumn(name = "host_id")
	public AssetHosts getAssetHosts() {
		return this.assetHosts;
	}

	public void setAssetHosts(AssetHosts assetHosts) {
		this.assetHosts = assetHosts;
	}

	@Column(name = "job_id", length = 20)
	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@Column(name = "ip_addr", length = 50)
	public String getIpAddr() {
		return this.ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@Column(name = "minion_id", length = 50)
	public String getMinionId() {
		return this.minionId;
	}

	public void setMinionId(String minionId) {
		this.minionId = minionId;
	}

	@Column(name = "fun", length = 50)
	public String getFun() {
		return this.fun;
	}

	public void setFun(String fun) {
		this.fun = fun;
	}

	@Column(name = "text", length = 16777215)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "code")
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Column(name = "success")
	public boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Column(name = "_error_count")
	public Integer getErrorCount() {
		return this.errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "data", length = 16777215)
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
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

	@Column(name = "finished", length = 19)
	public Date getFinished() {
		return this.finished;
	}

	public void setFinished(Date finished) {
		this.finished = finished;
	}

}