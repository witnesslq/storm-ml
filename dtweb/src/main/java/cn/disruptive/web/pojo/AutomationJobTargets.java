package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AutomationJobTargets entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "automation_job_targets", catalog = "ops")
public class AutomationJobTargets implements java.io.Serializable {

	// Fields

	private Integer id;
	private String jobId;
	private Integer hostId;
	private String host;
	private String ipAddr;
	private String minionId;
	private String fun;
	private String ret;
	private Integer retcode;
	private boolean success;
	private Integer status;
	private String data;
	private Date updated;
	private Date finished;
	private Date created;
	private boolean hasError;

	// Constructors

	/** default constructor */
	public AutomationJobTargets() {
	}

	/** full constructor */
	public AutomationJobTargets(String jobId, Integer hostId, String host,
			String ipAddr, String minionId, String fun, String ret,
			Integer retcode, boolean success, Integer status, String data,
			Date updated, Date finished, Date created, boolean hasError) {
		this.jobId = jobId;
		this.hostId = hostId;
		this.host = host;
		this.ipAddr = ipAddr;
		this.minionId = minionId;
		this.fun = fun;
		this.ret = ret;
		this.retcode = retcode;
		this.success = success;
		this.status = status;
		this.data = data;
		this.updated = updated;
		this.finished = finished;
		this.created = created;
		this.hasError = hasError;
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

	@Column(name = "job_id", length = 32)
	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@Column(name = "host_id")
	public Integer getHostId() {
		return this.hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

	@Column(name = "_host", length = 16777215)
	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
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

	@Column(name = "ret", length = 65535)
	public String getRet() {
		return this.ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	@Column(name = "retcode")
	public Integer getRetcode() {
		return this.retcode;
	}

	public void setRetcode(Integer retcode) {
		this.retcode = retcode;
	}

	@Column(name = "success")
	public boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "data", length = 65535)
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
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

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "has_error")
	public boolean getHasError() {
		return this.hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

}