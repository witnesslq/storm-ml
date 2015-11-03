package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PermissionAccounts entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "permission_accounts", catalog = "ops")
public class PermissionAccounts implements java.io.Serializable {

	// Fields

	private PermissionAccountsId id;
	private String module;

	// Constructors

	/** default constructor */
	public PermissionAccounts() {
	}

	/** minimal constructor */
	public PermissionAccounts(PermissionAccountsId id) {
		this.id = id;
	}

	/** full constructor */
	public PermissionAccounts(PermissionAccountsId id, String module) {
		this.id = id;
		this.module = module;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id", nullable = false)),
			@AttributeOverride(name = "name", column = @Column(name = "name", nullable = false)) })
	public PermissionAccountsId getId() {
		return this.id;
	}

	public void setId(PermissionAccountsId id) {
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