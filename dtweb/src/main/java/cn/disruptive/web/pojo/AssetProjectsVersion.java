package cn.disruptive.web.pojo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Description：资产项目 版本
 * Data：		2015年10月26日
 */
@Entity
@Table(name = "asset_projects_version", catalog = "ops")
public class AssetProjectsVersion implements java.io.Serializable {

	private AssetProjectsVersionId id;
	private Integer parentId;	// 所属父资产项目id
	private String name;		// 项目名称
	private Integer ipStatis;	// ip量统计
	private Integer pvStatis;	// 页面浏览器统计
	private String urls;		// 连接
	private String intro;		// 简介
	private Date created;		// 创建时间
	private Date updated;		// 更新时间
	private Integer leaderId;	// 所属负责人
	private Integer devLeaderId;// 所属开发负责人
	private String graphiteProject; // 
	private String path;			// 路径
	private String descendants;		// 子节点
	private String uniqueName;		// 唯一标识符
	private long endTransactionId;	// 变更结束id
	private short operationType;	// 操作类型

	// Constructors

	/** default constructor */
	public AssetProjectsVersion() {
	}

	/** minimal constructor */
	public AssetProjectsVersion(AssetProjectsVersionId id, short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public AssetProjectsVersion(AssetProjectsVersionId id, Integer parentId,
			String name, Integer ipStatis, Integer pvStatis, String urls,
			String intro, Date created, Date updated, Integer leaderId,
			Integer devLeaderId, String graphiteProject, String path,
			String descendants, String uniqueName, long endTransactionId,
			short operationType) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.ipStatis = ipStatis;
		this.pvStatis = pvStatis;
		this.urls = urls;
		this.intro = intro;
		this.created = created;
		this.updated = updated;
		this.leaderId = leaderId;
		this.devLeaderId = devLeaderId;
		this.graphiteProject = graphiteProject;
		this.path = path;
		this.descendants = descendants;
		this.uniqueName = uniqueName;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public AssetProjectsVersionId getId() {
		return this.id;
	}

	public void setId(AssetProjectsVersionId id) {
		this.id = id;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "intro", length = 65535)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	@Column(name = "leader_id")
	public Integer getLeaderId() {
		return this.leaderId;
	}

	public void setLeaderId(Integer leaderId) {
		this.leaderId = leaderId;
	}

	@Column(name = "dev_leader_id")
	public Integer getDevLeaderId() {
		return this.devLeaderId;
	}

	public void setDevLeaderId(Integer devLeaderId) {
		this.devLeaderId = devLeaderId;
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

	@Column(name = "unique_name", length = 50)
	public String getUniqueName() {
		return this.uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
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