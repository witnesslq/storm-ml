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
 * Description：资产主机和服务的关系
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "asset_hosts_services_relation", catalog = "ops")
public class AssetHostsServicesRelation implements java.io.Serializable {

	private AssetHostsServicesRelationId id;
	private AssetServices assetServices;	// 服务
	private AssetHosts assetHosts;			// 主机

	// Constructors

	/** default constructor */
	public AssetHostsServicesRelation() {
	}

	/** full constructor */
	public AssetHostsServicesRelation(AssetHostsServicesRelationId id,
			AssetServices assetServices, AssetHosts assetHosts) {
		this.id = id;
		this.assetServices = assetServices;
		this.assetHosts = assetHosts;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "hostId", column = @Column(name = "host_id", nullable = false)),
			@AttributeOverride(name = "serviceId", column = @Column(name = "service_id", nullable = false)) })
	public AssetHostsServicesRelationId getId() {
		return this.id;
	}

	public void setId(AssetHostsServicesRelationId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id", nullable = false, insertable = false, updatable = false)
	public AssetServices getAssetServices() {
		return this.assetServices;
	}

	public void setAssetServices(AssetServices assetServices) {
		this.assetServices = assetServices;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "host_id", nullable = false, insertable = false, updatable = false)
	public AssetHosts getAssetHosts() {
		return this.assetHosts;
	}

	public void setAssetHosts(AssetHosts assetHosts) {
		this.assetHosts = assetHosts;
	}

}