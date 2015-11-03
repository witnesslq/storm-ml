package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ServerAuthHostsVersionId entity. @author Eclipse Persistence Tools
 */
@Embeddable
public class ServerAuthHostsVersionId implements java.io.Serializable {

	// Fields

	private Integer hostId;
	private Integer accountId;
	private long transactionId;

	// Constructors

	/** default constructor */
	public ServerAuthHostsVersionId() {
	}

	/** full constructor */
	public ServerAuthHostsVersionId(Integer hostId, Integer accountId,
			long transactionId) {
		this.hostId = hostId;
		this.accountId = accountId;
		this.transactionId = transactionId;
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

	@Column(name = "transaction_id", nullable = false)
	public long getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ServerAuthHostsVersionId))
			return false;
		ServerAuthHostsVersionId castOther = (ServerAuthHostsVersionId) other;

		return ((this.getHostId() == castOther.getHostId()) || (this
				.getHostId() != null && castOther.getHostId() != null && this
				.getHostId().equals(castOther.getHostId())))
				&& ((this.getAccountId() == castOther.getAccountId()) || (this
						.getAccountId() != null
						&& castOther.getAccountId() != null && this
						.getAccountId().equals(castOther.getAccountId())))
				&& (this.getTransactionId() == castOther.getTransactionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getHostId() == null ? 0 : this.getHostId().hashCode());
		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result + (int) this.getTransactionId();
		return result;
	}

}