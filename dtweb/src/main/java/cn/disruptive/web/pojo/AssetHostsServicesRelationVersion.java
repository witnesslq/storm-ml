package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Description：主机和服务关系版本
 * Data：		2015年10月23日
 */
@Entity
@Table(name = "asset_hosts_services_relation_version", catalog = "ops")
public class AssetHostsServicesRelationVersion implements java.io.Serializable {

	private AssetHostsServicesRelationVersionId id;
	private long endTransactionId;		// 终止事物id
	private short operationType;		// 操作类型

	// Constructors

	/** default constructor */
	public AssetHostsServicesRelationVersion() {
	}

	/** minimal constructor */
	public AssetHostsServicesRelationVersion(
			AssetHostsServicesRelationVersionId id, short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public AssetHostsServicesRelationVersion(
			AssetHostsServicesRelationVersionId id, long endTransactionId,
			short operationType) {
		this.id = id;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "hostId", column = @Column(name = "host_id", nullable = false)),
			@AttributeOverride(name = "serviceId", column = @Column(name = "service_id", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public AssetHostsServicesRelationVersionId getId() {
		return this.id;
	}

	public void setId(AssetHostsServicesRelationVersionId id) {
		this.id = id;
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