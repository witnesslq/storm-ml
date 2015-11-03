package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Description：制造商
 * Data：		2015年10月23日
 */
@Entity
@Table(name = "asset_manufacturers_version", catalog = "ops")
public class AssetManufacturersVersion implements java.io.Serializable {

	private AssetManufacturersVersionId id;
	private String name;			// 制造商名字
	private String website;			// 制造商网站
	private String intro;			// 简介
	private long endTransactionId;	// 结束变更id
	private short operationType;	// 操作类型

	// Constructors

	/** default constructor */
	public AssetManufacturersVersion() {
	}

	/** minimal constructor */
	public AssetManufacturersVersion(AssetManufacturersVersionId id,
			short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public AssetManufacturersVersion(AssetManufacturersVersionId id,
			String name, String website, String intro, long endTransactionId,
			short operationType) {
		this.id = id;
		this.name = name;
		this.website = website;
		this.intro = intro;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public AssetManufacturersVersionId getId() {
		return this.id;
	}

	public void setId(AssetManufacturersVersionId id) {
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