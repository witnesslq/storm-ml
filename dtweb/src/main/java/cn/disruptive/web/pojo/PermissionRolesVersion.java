package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PermissionRolesVersion entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "permission_roles_version", catalog = "ops")
public class PermissionRolesVersion implements java.io.Serializable {

	// Fields

	private PermissionRolesVersionId id;
	private String module;
	private long endTransactionId;
	private short operationType;

	// Constructors

	/** default constructor */
	public PermissionRolesVersion() {
	}

	/** minimal constructor */
	public PermissionRolesVersion(PermissionRolesVersionId id,
			short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public PermissionRolesVersion(PermissionRolesVersionId id, String module,
			long endTransactionId, short operationType) {
		this.id = id;
		this.module = module;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)),
			@AttributeOverride(name = "name", column = @Column(name = "name", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public PermissionRolesVersionId getId() {
		return this.id;
	}

	public void setId(PermissionRolesVersionId id) {
		this.id = id;
	}

	@Column(name = "module", length = 50)
	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
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