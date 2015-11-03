package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AccountAccountsVersionId entity. @author Eclipse Persistence Tools
 */
@Embeddable
public class AccountAccountsVersionId implements java.io.Serializable {

	// Fields

	private Integer id;
	private long transactionId;

	// Constructors

	/** default constructor */
	public AccountAccountsVersionId() {
	}

	/** full constructor */
	public AccountAccountsVersionId(Integer id, long transactionId) {
		this.id = id;
		this.transactionId = transactionId;
	}

	// Property accessors

	@Column(name = "id", nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		if (!(other instanceof AccountAccountsVersionId))
			return false;
		AccountAccountsVersionId castOther = (AccountAccountsVersionId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& (this.getTransactionId() == castOther.getTransactionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (int) this.getTransactionId();
		return result;
	}

}