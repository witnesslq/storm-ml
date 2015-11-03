package cn.disruptive.web.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * Description：服务
 * Data：		2015年10月26日
 */
@Entity
@Table(name = "asset_services", catalog = "ops")
public class AssetServices implements java.io.Serializable {

	private Integer id;
	private String name;		// 名称
	private String website;		// 域名
	private String listen;		// 监听
	private Integer port;		// 端口
	private String intro;		// 简介
	private Set<AssetHostsServicesRelation> assetHostsServicesRelations = new HashSet<AssetHostsServicesRelation>(0); // 包含的主机

	// Constructors

	/** default constructor */
	public AssetServices() {
	}

	/** full constructor */
	public AssetServices(String name, String website, String listen,
			Integer port, String intro,
			Set<AssetHostsServicesRelation> assetHostsServicesRelations) {
		this.name = name;
		this.website = website;
		this.listen = listen;
		this.port = port;
		this.intro = intro;
		this.assetHostsServicesRelations = assetHostsServicesRelations;
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

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "website")
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "listen", length = 50)
	public String getListen() {
		return this.listen;
	}

	public void setListen(String listen) {
		this.listen = listen;
	}

	@Column(name = "port")
	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Column(name = "intro", length = 65535)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetServices")
	public Set<AssetHostsServicesRelation> getAssetHostsServicesRelations() {
		return this.assetHostsServicesRelations;
	}

	public void setAssetHostsServicesRelations(
			Set<AssetHostsServicesRelation> assetHostsServicesRelations) {
		this.assetHostsServicesRelations = assetHostsServicesRelations;
	}

}