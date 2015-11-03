package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AccountAccountsVersion entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "account_accounts_version", catalog = "ops")
public class AccountAccountsVersion implements java.io.Serializable {

	// Fields

	private AccountAccountsVersionId id;
	private String email;
	private String password;
	private String name;
	private String cellPhone;
	private String apiKey;
	private String avatar;
	private Integer status;
	private String locale;
	private String timezone;
	private Date created;
	private Date lastLoginTime;
	private String lastLoginIp;
	private String intro;
	private String sshPubKey;
	private long endTransactionId;
	private short operationType;

	// Constructors

	/** default constructor */
	public AccountAccountsVersion() {
	}

	/** minimal constructor */
	public AccountAccountsVersion(AccountAccountsVersionId id,
			short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public AccountAccountsVersion(AccountAccountsVersionId id, String email,
			String password, String name, String cellPhone, String apiKey,
			String avatar, Integer status, String locale, String timezone,
			Date created, Date lastLoginTime, String lastLoginIp, String intro,
			String sshPubKey, long endTransactionId, short operationType) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.cellPhone = cellPhone;
		this.apiKey = apiKey;
		this.avatar = avatar;
		this.status = status;
		this.locale = locale;
		this.timezone = timezone;
		this.created = created;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
		this.intro = intro;
		this.sshPubKey = sshPubKey;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public AccountAccountsVersionId getId() {
		return this.id;
	}

	public void setId(AccountAccountsVersionId id) {
		this.id = id;
	}

	@Column(name = "email", length = 120)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", length = 36)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name", length = 80)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "cell_phone", length = 20)
	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@Column(name = "api_key", length = 36)
	public String getApiKey() {
		return this.apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Column(name = "avatar")
	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "locale", length = 20)
	public String getLocale() {
		return this.locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@Column(name = "timezone", length = 20)
	public String getTimezone() {
		return this.timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "last_login_time", length = 19)
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "last_login_ip", length = 30)
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Column(name = "intro", length = 65535)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "ssh_pub_key", length = 65535)
	public String getSshPubKey() {
		return this.sshPubKey;
	}

	public void setSshPubKey(String sshPubKey) {
		this.sshPubKey = sshPubKey;
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