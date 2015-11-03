package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VariablesVersionId entity. @author Eclipse Persistence Tools
 */
@Embeddable
public class VariablesVersionId implements java.io.Serializable {

	// Fields

	private String name;
	private long transactionId;

	// Constructors

	/** default constructor */
	public VariablesVersionId() {
	}

	/** full constructor */
	public VariablesVersionId(String name, long transactionId) {
		this.name = name;
		this.transactionId = transactionId;
	}

	// Property accessors

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
		if (!(other instanceof VariablesVersionId))
			return false;
		VariablesVersionId castOther = (VariablesVersionId) other;

		return ((this.getName() == castOther.getName()) || (this.getName() != null
				&& castOther.getName() != null && this.getName().equals(
				castOther.getName())))
				&& (this.getTransactionId() == castOther.getTransactionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result + (int) this.getTransactionId();
		return result;
	}

}