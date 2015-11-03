package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PermissionRoles entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "permission_roles", catalog = "ops")
public class PermissionRoles implements java.io.Serializable {

	// Fields

	private PermissionRolesId id;
	private String module;

	// Constructors

	/** default constructor */
	public PermissionRoles() {
	}

	/** minimal constructor */
	public PermissionRoles(PermissionRolesId id) {
		this.id = id;
	}

	/** full constructor */
	public PermissionRoles(PermissionRolesId id, String module) {
		this.id = id;
		this.module = module;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)),
			@AttributeOverride(name = "name", column = @Column(name = "name", nullable = false)) })
	public PermissionRolesId getId() {
		return this.id;
	}

	public void setId(PermissionRolesId id) {
		this.id = id;
	}

	@Column(name = "module", length = 50)
	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

}