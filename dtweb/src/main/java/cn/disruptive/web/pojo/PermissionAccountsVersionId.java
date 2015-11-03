package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PermissionAccountsVersionId entity. @author Eclipse Persistence Tools
 */
@Embeddable
public class PermissionAccountsVersionId implements java.io.Serializable {

	// Fields

	private Integer accountId;
	private String name;
	private long transactionId;

	// Constructors

	/** default constructor */
	public PermissionAccountsVersionId() {
	}

	/** full constructor */
	public PermissionAccountsVersionId(Integer accountId, String name,
			long transactionId) {
		this.accountId = accountId;
		this.name = name;
		this.transactionId = transactionId;
	}

	// Property accessors

	@Column(name = "account_id", nullable = false)
	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
		if (!(other instanceof PermissionAccountsVersionId))
			return false;
		PermissionAccountsVersionId castOther = (PermissionAccountsVersionId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null && castOther.getAccountId() != null && this
				.getAccountId().equals(castOther.getAccountId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& (this.getTransactionId() == castOther.getTransactionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result + (int) this.getTransactionId();
		return result;
	}

}