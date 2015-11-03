package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ServerAuthProjectsId entity. @author Eclipse Persistence Tools
 */
@Embeddable
public class ServerAuthProjectsId implements java.io.Serializable {

	// Fields

	private Integer projectId;
	private Integer accountId;

	// Constructors

	/** default constructor */
	public ServerAuthProjectsId() {
	}

	/** full constructor */
	public ServerAuthProjectsId(Integer projectId, Integer accountId) {
		this.projectId = projectId;
		this.accountId = accountId;
	}

	// Property accessors

	@Column(name = "project_id", nullable = false)
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Column(name = "account_id", nullable = false)
	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ServerAuthProjectsId))
			return false;
		ServerAuthProjectsId castOther = (ServerAuthProjectsId) other;

		return ((this.getProjectId() == castOther.getProjectId()) || (this
				.getProjectId() != null && castOther.getProjectId() != null && this
				.getProjectId().equals(castOther.getProjectId())))
				&& ((this.getAccountId() == castOther.getAccountId()) || (this
						.getAccountId() != null
						&& castOther.getAccountId() != null && this
						.getAccountId().equals(castOther.getAccountId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getProjectId() == null ? 0 : this.getProjectId().hashCode());
		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		return result;
	}

}