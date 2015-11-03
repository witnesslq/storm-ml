package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Description：项目开 与 开发者 关系 版本
 * Data：		2015年10月26日
 */
@Entity
@Table(name = "asset_projects_developers_relation_version", catalog = "ops")
public class AssetProjectsDevelopersRelationVersion implements java.io.Serializable {

	private AssetProjectsDevelopersRelationVersionId id;
	private long endTransactionId;	// 变更结束id
	private short operationType;	// 操作类型

	// Constructors

	/** default constructor */
	public AssetProjectsDevelopersRelationVersion() {
	}

	/** minimal constructor */
	public AssetProjectsDevelopersRelationVersion(
			AssetProjectsDevelopersRelationVersionId id, short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public AssetProjectsDevelopersRelationVersion(
			AssetProjectsDevelopersRelationVersionId id, long endTransactionId,
			short operationType) {
		this.id = id;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id", nullable = false)),
			@AttributeOverride(name = "projectId", column = @Column(name = "project_id", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public AssetProjectsDevelopersRelationVersionId getId() {
		return this.id;
	}

	public void setId(AssetProjectsDevelopersRelationVersionId id) {
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