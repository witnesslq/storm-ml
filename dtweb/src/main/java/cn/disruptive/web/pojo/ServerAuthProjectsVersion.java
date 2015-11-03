package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ServerAuthProjectsVersion entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "server_auth_projects_version", catalog = "ops")
public class ServerAuthProjectsVersion implements java.io.Serializable {

	// Fields

	private ServerAuthProjectsVersionId id;
	private Integer gid;
	private long endTransactionId;
	private short operationType;

	// Constructors

	/** default constructor */
	public ServerAuthProjectsVersion() {
	}

	/** minimal constructor */
	public ServerAuthProjectsVersion(ServerAuthProjectsVersionId id,
			short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public ServerAuthProjectsVersion(ServerAuthProjectsVersionId id,
			Integer gid, long endTransactionId, short operationType) {
		this.id = id;
		this.gid = gid;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "projectId", column = @Column(name = "project_id", nullable = false)),
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public ServerAuthProjectsVersionId getId() {
		return this.id;
	}

	public void setId(ServerAuthProjectsVersionId id) {
		this.id = id;
	}

	@Column(name = "gid")
	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
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