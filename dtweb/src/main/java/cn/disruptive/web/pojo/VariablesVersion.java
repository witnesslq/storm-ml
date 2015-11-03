package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VariablesVersion entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "variables_version", catalog = "ops")
public class VariablesVersion implements java.io.Serializable {

	// Fields

	private VariablesVersionId id;
	private String value;
	private long endTransactionId;
	private short operationType;

	// Constructors

	/** default constructor */
	public VariablesVersion() {
	}

	/** minimal constructor */
	public VariablesVersion(VariablesVersionId id, short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public VariablesVersion(VariablesVersionId id, String value,
			long endTransactionId, short operationType) {
		this.id = id;
		this.value = value;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "name", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public VariablesVersionId getId() {
		return this.id;
	}

	public void setId(VariablesVersionId id) {
		this.id = id;
	}

	@Column(name = "value", length = 65535)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "end_transaction_id")
	public long getEndTransactionId() {
		return this.endTransactionId;
	}

	public void setEndTransactionId(long endTransactionId) {
		this.endTransactionId = endTransactionId;
	}

	@Column(name = "operation_type", nullable = false)
	public short getOperationType() {
		return this.operationType;
	}

	public void setOperationType(short operationType) {
		this.operationType = operationType;
	}

}