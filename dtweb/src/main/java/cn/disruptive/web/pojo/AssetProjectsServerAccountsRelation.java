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
 * 
 * Description：项目和服务人员信息关系
 * Data：		2015年10月26日
 */
@Entity
@Table(name = "asset_projects_server_accounts_relation", catalog = "ops")
public class AssetProjectsServerAccountsRelation implements
		java.io.Serializable {
	private AssetProjectsServerAccountsRelationId id;
	private AssetProjects assetProjects;		// 项目
	private AccountAccounts accountAccounts;	// 人员

	// Constructors

	/** default constructor */
	public AssetProjectsServerAccountsRelation() {
	}

	/** minimal constructor */
	public AssetProjectsServerAccountsRelation(
			AssetProjectsServerAccountsRelationId id) {
		this.id = id;
	}

	/** full constructor */
	public AssetProjectsServerAccountsRelation(
			AssetProjectsServerAccountsRelationId id,
			AssetProjects assetProjects, AccountAccounts accountAccounts) {
		this.id = id;
		this.assetProjects = assetProjects;
		this.accountAccounts = accountAccounts;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id")),
			@AttributeOverride(name = "projectId", column = @Column(name = "project_id")) })
	public AssetProjectsServerAccountsRelationId getId() {
		return this.id;
	}

	public void setId(AssetProjectsServerAccountsRelationId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", insertable = false, updatable = false)
	public AssetProjects getAssetProjects() {
		return this.assetProjects;
	}

	public void setAssetProjects(AssetProjects assetProjects) {
		this.assetProjects = assetProjects;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", insertable = false, updatable = false)
	public AccountAccounts getAccountAccounts() {
		return this.accountAccounts;
	}

	public void setAccountAccounts(AccountAccounts accountAccounts) {
		this.accountAccounts = accountAccounts;
	}

}