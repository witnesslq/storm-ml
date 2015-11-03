package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ServerAuthProjects entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "server_auth_projects", catalog = "ops")
public class ServerAuthProjects implements java.io.Serializable {

	// Fields

	private ServerAuthProjectsId id;
	private AssetProjects assetProjects;
	private AccountAccounts accountAccounts;
	private Integer gid;

	// Constructors

	/** default constructor */
	public ServerAuthProjects() {
	}

	/** minimal constructor */
	public ServerAuthProjects(ServerAuthProjectsId id,
			AssetProjects assetProjects, AccountAccounts accountAccounts) {
		this.id = id;
		this.assetProjects = assetProjects;
		this.accountAccounts = accountAccounts;
	}

	/** full constructor */
	public ServerAuthProjects(ServerAuthProjectsId id,
			AssetProjects assetProjects, AccountAccounts accountAccounts,
			Integer gid) {
		this.id = id;
		this.assetProjects = assetProjects;
		this.accountAccounts = accountAccounts;
		this.gid = gid;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "project_id", nullable = false)),
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id", nullable = false)) })
	public ServerAuthProjectsId getId() {
		return this.id;
	}

	public void setId(ServerAuthProjectsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false, insertable = false, updatable = false)
	public AssetProjects getAssetProjects() {
		return this.assetProjects;
	}

	public void setAssetProjects(AssetProjects assetProjects) {
		this.assetProjects = assetProjects;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false, insertable = false, updatable = false)
	public AccountAccounts getAccountAccounts() {
		return this.accountAccounts;
	}

	public void setAccountAccounts(AccountAccounts accountAccounts) {
		this.accountAccounts = accountAccounts;
	}

	@Column(name = "gid")
	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

}