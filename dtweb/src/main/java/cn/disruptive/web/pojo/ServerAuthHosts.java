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
 * Description：认证过的主机
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "server_auth_hosts", catalog = "ops")
public class ServerAuthHosts implements java.io.Serializable {

	// Fields

	private ServerAuthHostsId id;
	private AssetHosts assetHosts;
	private AccountAccounts accountAccounts;
	private Integer gid;

	// Constructors

	/** default constructor */
	public ServerAuthHosts() {
	}

	/** minimal constructor */
	public ServerAuthHosts(ServerAuthHostsId id, AssetHosts assetHosts,
			AccountAccounts accountAccounts) {
		this.id = id;
		this.assetHosts = assetHosts;
		this.accountAccounts = accountAccounts;
	}

	/** full constructor */
	public ServerAuthHosts(ServerAuthHostsId id, AssetHosts assetHosts,
			AccountAccounts accountAccounts, Integer gid) {
		this.id = id;
		this.assetHosts = assetHosts;
		this.accountAccounts = accountAccounts;
		this.gid = gid;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "hostId", column = @Column(name = "host_id", nullable = false)),
			@AttributeOverride(name = "accountId", column = @Column(name = "account_id", nullable = false)) })
	public ServerAuthHostsId getId() {
		return this.id;
	}

	public void setId(ServerAuthHostsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "host_id", nullable = false, insertable = false, updatable = false)
	public AssetHosts getAssetHosts() {
		return this.assetHosts;
	}

	public void setAssetHosts(AssetHosts assetHosts) {
		this.assetHosts = assetHosts;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false, insertable = false, updatable = false)
	public AccountAccounts getAccountAccounts() {
		return this.accountAccounts;
	}

	public void setAccountAccounts(AccountAccounts accountAccounts) {
		this.accountAccounts = accountAccounts;
	}

	@Column(name = "gid")
	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

}