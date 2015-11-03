package cn.disruptive.web.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * Description：资产项目
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "asset_projects", catalog = "ops", uniqueConstraints = @UniqueConstraint(columnNames = "unique_name"))
public class AssetProjects implements java.io.Serializable {

	private Integer id;		
	private AssetProjects assetProjects;				// 所属父资产项目
	private AccountAccounts accountAccountsByLeaderId;	// 所属负责人
	private AccountAccounts accountAccountsByDevLeaderId;// 所属开发负责人
	private String name;			// 项目名称
	private String intro;			// 简介
	private Integer ipStatis;		// ip量统计
	private Integer pvStatis;		// 页面浏览器统计
	private String urls;			// 连接
	private Date created;			// 创建时间
	private Date updated;			// 更新时间
	private String graphiteProject;	//
	private String path;			// 路径
	private String descendants;		// 子节点
	private String uniqueName;		// 唯一标识符
	private Set<IssueIssues> issueIssueses = new HashSet<IssueIssues>(0);		// 包含的问题
	private Set<AssetProjectsTestersRelation> assetProjectsTestersRelations = new HashSet<AssetProjectsTestersRelation>(0);		// 包含的测试人员信息	
	private Set<AssetProjectsSysopsRelation> assetProjectsSysopsRelations = new HashSet<AssetProjectsSysopsRelation>(0);		// 包含的系统操作人员信息
	private Set<AssetProjectsServerAccountsRelation> assetProjectsServerAccountsRelations = new HashSet<AssetProjectsServerAccountsRelation>(0);// 包换的服务人员信息
	private Set<AssetProjectsDevelopersRelation> assetProjectsDevelopersRelations = new HashSet<AssetProjectsDevelopersRelation>(0);	// 包含的开发人员信息
	private Set<AssetHosts> assetHostses = new HashSet<AssetHosts>(0);							// 包含的主机
	private Set<ServerAuthProjects> serverAuthProjectses = new HashSet<ServerAuthProjects>(0);	// 包含的自动客服信息
	private Set<AssetProjects> assetProjectses = new HashSet<AssetProjects>(0);					//	包含的子项目


	/** default constructor */
	public AssetProjects() {
	}

	/** full constructor */
	public AssetProjects(
			AssetProjects assetProjects,
			AccountAccounts accountAccountsByLeaderId,
			AccountAccounts accountAccountsByDevLeaderId,
			String name,
			String intro,
			Integer ipStatis,
			Integer pvStatis,
			String urls,
			Date created,
			Date updated,
			String graphiteProject,
			String path,
			String descendants,
			String uniqueName,
			Set<IssueIssues> issueIssueses,
			Set<AssetProjectsTestersRelation> assetProjectsTestersRelations,
			Set<AssetProjectsSysopsRelation> assetProjectsSysopsRelations,
			Set<AssetProjectsServerAccountsRelation> assetProjectsServerAccountsRelations,
			Set<AssetProjectsDevelopersRelation> assetProjectsDevelopersRelations,
			Set<AssetHosts> assetHostses,
			Set<ServerAuthProjects> serverAuthProjectses,
			Set<AssetProjects> assetProjectses) {
		this.assetProjects = assetProjects;
		this.accountAccountsByLeaderId = accountAccountsByLeaderId;
		this.accountAccountsByDevLeaderId = accountAccountsByDevLeaderId;
		this.name = name;
		this.intro = intro;
		this.ipStatis = ipStatis;
		this.pvStatis = pvStatis;
		this.urls = urls;
		this.created = created;
		this.updated = updated;
		this.graphiteProject = graphiteProject;
		this.path = path;
		this.descendants = descendants;
		this.uniqueName = uniqueName;
		this.issueIssueses = issueIssueses;
		this.assetProjectsTestersRelations = assetProjectsTestersRelations;
		this.assetProjectsSysopsRelations = assetProjectsSysopsRelations;
		this.assetProjectsServerAccountsRelations = assetProjectsServerAccountsRelations;
		this.assetProjectsDevelopersRelations = assetProjectsDevelopersRelations;
		this.assetHostses = assetHostses;
		this.serverAuthProjectses = serverAuthProjectses;
		this.assetProjectses = assetProjectses;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public AssetProjects getAssetProjects() {
		return this.assetProjects;
	}

	public void setAssetProjects(AssetProjects assetProjects) {
		this.assetProjects = assetProjects;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "leader_id")
	public AccountAccounts getAccountAccountsByLeaderId() {
		return this.accountAccountsByLeaderId;
	}

	public void setAccountAccountsByLeaderId(
			AccountAccounts accountAccountsByLeaderId) {
		this.accountAccountsByLeaderId = accountAccountsByLeaderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dev_leader_id")
	public AccountAccounts getAccountAccountsByDevLeaderId() {
		return this.accountAccountsByDevLeaderId;
	}

	public void setAccountAccountsByDevLeaderId(
			AccountAccounts accountAccountsByDevLeaderId) {
		this.accountAccountsByDevLeaderId = accountAccountsByDevLeaderId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "intro", length = 65535)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "ip_statis")
	public Integer getIpStatis() {
		return this.ipStatis;
	}

	public void setIpStatis(Integer ipStatis) {
		this.ipStatis = ipStatis;
	}

	@Column(name = "pv_statis")
	public Integer getPvStatis() {
		return this.pvStatis;
	}

	public void setPvStatis(Integer pvStatis) {
		this.pvStatis = pvStatis;
	}

	@Column(name = "urls", length = 65535)
	public String getUrls() {
		return this.urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "updated", length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "graphite_project", length = 200)
	public String getGraphiteProject() {
		return this.graphiteProject;
	}

	public void setGraphiteProject(String graphiteProject) {
		this.graphiteProject = graphiteProject;
	}

	@Column(name = "_path")
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "_descendants", length = 1024)
	public String getDescendants() {
		return this.descendants;
	}

	public void setDescendants(String descendants) {
		this.descendants = descendants;
	}

	@Column(name = "unique_name", unique = true, length = 50)
	public String getUniqueName() {
		return this.uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetProjects")
	public Set<IssueIssues> getIssueIssueses() {
		return this.issueIssueses;
	}

	public void setIssueIssueses(Set<IssueIssues> issueIssueses) {
		this.issueIssueses = issueIssueses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetProjects")
	public Set<AssetProjectsTestersRelation> getAssetProjectsTestersRelations() {
		return this.assetProjectsTestersRelations;
	}

	public void setAssetProjectsTestersRelations(
			Set<AssetProjectsTestersRelation> assetProjectsTestersRelations) {
		this.assetProjectsTestersRelations = assetProjectsTestersRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetProjects")
	public Set<AssetProjectsSysopsRelation> getAssetProjectsSysopsRelations() {
		return this.assetProjectsSysopsRelations;
	}

	public void setAssetProjectsSysopsRelations(
			Set<AssetProjectsSysopsRelation> assetProjectsSysopsRelations) {
		this.assetProjectsSysopsRelations = assetProjectsSysopsRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetProjects")
	public Set<AssetProjectsServerAccountsRelation> getAssetProjectsServerAccountsRelations() {
		return this.assetProjectsServerAccountsRelations;
	}

	public void setAssetProjectsServerAccountsRelations(
			Set<AssetProjectsServerAccountsRelation> assetProjectsServerAccountsRelations) {
		this.assetProjectsServerAccountsRelations = assetProjectsServerAccountsRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetProjects")
	public Set<AssetProjectsDevelopersRelation> getAssetProjectsDevelopersRelations() {
		return this.assetProjectsDevelopersRelations;
	}

	public void setAssetProjectsDevelopersRelations(
			Set<AssetProjectsDevelopersRelation> assetProjectsDevelopersRelations) {
		this.assetProjectsDevelopersRelations = assetProjectsDevelopersRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetProjects")
	public Set<AssetHosts> getAssetHostses() {
		return this.assetHostses;
	}

	public void setAssetHostses(Set<AssetHosts> assetHostses) {
		this.assetHostses = assetHostses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetProjects")
	public Set<ServerAuthProjects> getServerAuthProjectses() {
		return this.serverAuthProjectses;
	}

	public void setServerAuthProjectses(
			Set<ServerAuthProjects> serverAuthProjectses) {
		this.serverAuthProjectses = serverAuthProjectses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetProjects")
	public Set<AssetProjects> getAssetProjectses() {
		return this.assetProjectses;
	}

	public void setAssetProjectses(Set<AssetProjects> assetProjectses) {
		this.assetProjectses = assetProjectses;
	}

}