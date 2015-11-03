package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PermissionRolesVersionId entity. @author Eclipse Persistence Tools
 */
@Embeddable
public class PermissionRolesVersionId implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String name;
	private long transactionId;

	// Constructors

	/** default constructor */
	public PermissionRolesVersionId() {
	}

	/** full constructor */
	public PermissionRolesVersionId(Integer roleId, String name,
			long transactionId) {
		this.roleId = roleId;
		this.name = name;
		this.transactionId = transactionId;
	}

	// Property accessors

	@Column(name = "role_id", nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
		if (!(other instanceof PermissionRolesVersionId))
			return false;
		PermissionRolesVersionId castOther = (PermissionRolesVersionId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& (this.getTransactionId() == castOther.getTransactionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result + (int) this.getTransactionId();
		return result;
	}

}