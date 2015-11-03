package cn.disruptive.web.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ServerAuthHostsVersion entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "server_auth_hosts_version", catalog = "ops")
public class ServerAuthHostsVersion implements java.io.Serializable {

	// Fields

	private ServerAuthHostsVersionId id;
	private Integer gid;
	private long endTransactionId;
	private short operationType;

	// Constructors

	/** default constructor */
	public ServerAuthHostsVersion() {
	}

	/** minimal constructor */
	public ServerAuthHostsVersion(ServerAuthHostsVersionId id,
			short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public ServerAuthHostsVersion(ServerAuthHostsVersionId id, Integer gid,
			long endTransactionId, short operationType) {
		this.id = id;
		this.gid = gid;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "hostId", column = @Column(name = "host_id", nullable = false)),
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public ServerAuthHostsVersionId getId() {
		return this.id;
	}

	public void setId(ServerAuthHostsVersionId id) {
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