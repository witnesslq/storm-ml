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
 * Description：项目测试人员关系
 * Data：		2015年10月26日
 */
@Entity
@Table(name = "asset_projects_testers_relation", catalog = "ops")
public class AssetProjectsTestersRelation implements java.io.Serializable {

	private AssetProjectsTestersRelationId id;
	private AssetProjects assetProjects;		// 项目
	private AccountAccounts accountAccounts;	// 人员

	// Constructors

	/** default constructor */
	public AssetProjectsTestersRelation() {
	}

	/** full constructor */
	public AssetProjectsTestersRelation(AssetProjectsTestersRelationId id,
			AssetProjects assetProjects, AccountAccounts accountAccounts) {
		this.id = id;
		this.assetProjects = assetProjects;
		this.accountAccounts = accountAccounts;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id", nullable = false)),
			@AttributeOverride(name = "projectId", column = @Column(name = "project_id", nullable = false)) })
	public AssetProjectsTestersRelationId getId() {
		return this.id;
	}

	public void setId(AssetProjectsTestersRelationId id) {
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

}