package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Description：服务 版本
 * Data：		2015年10月26日
 */
@Entity
@Table(name = "asset_services_version", catalog = "ops")
public class AssetServicesVersion implements java.io.Serializable {

	private AssetServicesVersionId id;
	private String name;		// 名称
	private String website;		// 域名
	private String listen;		// 监听
	private Integer port;		// 端口
	private String intro;		// 简介
	private long endTransactionId;	// 结束变更id
	private short operationType;	// 变更类型

	// Constructors

	/** default constructor */
	public AssetServicesVersion() {
	}

	/** minimal constructor */
	public AssetServicesVersion(AssetServicesVersionId id, short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public AssetServicesVersion(AssetServicesVersionId id, String name,
			String website, String listen, Integer port, String intro,
			long endTransactionId, short operationType) {
		this.id = id;
		this.name = name;
		this.website = website;
		this.listen = listen;
		this.port = port;
		this.intro = intro;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public AssetServicesVersionId getId() {
		return this.id;
	}

	public void setId(AssetServicesVersionId id) {
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