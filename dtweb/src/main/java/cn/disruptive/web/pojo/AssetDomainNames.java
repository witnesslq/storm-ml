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
 * Description：域名名称表
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "asset_domain_names", catalog = "ops", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class AssetDomainNames implements java.io.Serializable {

	private Integer id;							//
	private AccountAccounts accountAccounts;	// 所属负责人
	private String name;						// 名称
	private String cname;						// 域名
	private String topLevelDomain;				// 顶级域名
	private Date created;						// 创建时间
	private Date updated;						// 更新时间
	private Set<AssetHosts> assetHostses = new HashSet<AssetHosts>(0); // 包含的主机

	// Constructors

	/** default constructor */
	public AssetDomainNames() {
	}

	/** full constructor */
	public AssetDomainNames(AccountAccounts accountAccounts, String name,
			String cname, String topLevelDomain, Date created, Date updated,
			Set<AssetHosts> assetHostses) {
		this.accountAccounts = accountAccounts;
		this.name = name;
		this.cname = cname;
		this.topLevelDomain = topLevelDomain;
		this.created = created;
		this.updated = updated;
		this.assetHostses = assetHostses;
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
	@JoinColumn(name = "sysop_id")
	public AccountAccounts getAccountAccounts() {
		return this.accountAccounts;
	}

	public void setAccountAccounts(AccountAccounts accountAccounts) {
		this.accountAccounts = accountAccounts;
	}

	@Column(name = "name", unique = true, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "cname", length = 50)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "top_level_domain", length = 50)
	public String getTopLevelDomain() {
		return this.topLevelDomain;
	}

	public void setTopLevelDomain(String topLevelDomain) {
		this.topLevelDomain = topLevelDomain;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetDomainNames")
	public Set<AssetHosts> getAssetHostses() {
		return this.assetHostses;
	}

	public void setAssetHostses(Set<AssetHosts> assetHostses) {
		this.assetHostses = assetHostses;
	}

}