package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ServerAuthHostsId entity. @author Eclipse Persistence Tools
 */
@Embeddable
public class ServerAuthHostsId implements java.io.Serializable {

	// Fields

	private Integer hostId;
	private Integer accountId;

	// Constructors

	/** default constructor */
	public ServerAuthHostsId() {
	}

	/** full constructor */
	public ServerAuthHostsId(Integer hostId, Integer accountId) {
		this.hostId = hostId;
		this.accountId = accountId;
	}

	// Property accessors

	@Column(name = "host_id", nullable = false)
	public Integer getHostId() {
		return this.hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
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
		if (!(other instanceof ServerAuthHostsId))
			return false;
		ServerAuthHostsId castOther = (ServerAuthHostsId) other;

		return ((this.getHostId() == castOther.getHostId()) || (this
				.getHostId() != null && castOther.getHostId() != null && this
				.getHostId().equals(castOther.getHostId())))
				&& ((this.getAccountId() == castOther.getAccountId()) || (this
						.getAccountId() != null
						&& castOther.getAccountId() != null && this
						.getAccountId().equals(castOther.getAccountId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getHostId() == null ? 0 : this.getHostId().hashCode());
		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		return result;
	}

}