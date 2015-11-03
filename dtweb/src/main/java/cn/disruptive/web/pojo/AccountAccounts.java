package cn.disruptive.web.pojo;

import java.util.Date;
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
import javax.persistence.UniqueConstraint;

/**
 * 
 * Description：客户
 * Data：		2015年10月26日
 */
@Entity
@Table(name = "account_accounts", catalog = "ops", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class AccountAccounts implements java.io.Serializable {

	// Fields

	private Integer id;
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
	private Set<IssueIssues> issueIssuesesForCreatorId = new HashSet<IssueIssues>(
			0);
	private Set<AssetProjectsDevelopersRelation> assetProjectsDevelopersRelations = new HashSet<AssetProjectsDevelopersRelation>(
			0);
	private Set<IssueIssues> issueIssuesesForOwnerId = new HashSet<IssueIssues>(
			0);
	private Set<IssueIssues> issueIssuesesForContacteeId = new HashSet<IssueIssues>(
			0);
	private Set<DocArticles> docArticleses = new HashSet<DocArticles>(0);
	private Set<IssueIssues> issueIssuesesForCaretakerId = new HashSet<IssueIssues>(
			0);
	private Set<AssetProjectsServerAccountsRelation> assetProjectsServerAccountsRelations = new HashSet<AssetProjectsServerAccountsRelation>(
			0);
	private Set<IssueIssues> issueIssuesesForSolverId = new HashSet<IssueIssues>(
			0);
	private Set<DocCategories> docCategorieses = new HashSet<DocCategories>(0);
	private Set<AssetProjects> assetProjectsesForLeaderId = new HashSet<AssetProjects>(
			0);
	private Set<AssetProjects> assetProjectsesForDevLeaderId = new HashSet<AssetProjects>(
			0);
	private Set<AccountAccountsRolesRelation> accountAccountsRolesRelations = new HashSet<AccountAccountsRolesRelation>(
			0);
	private Set<IssueIssueAlerts> issueIssueAlertses = new HashSet<IssueIssueAlerts>(
			0);
	private Set<IssueIssueNotes> issueIssueNoteses = new HashSet<IssueIssueNotes>(
			0);
	private Set<AlertHostsAccountsRelation> alertHostsAccountsRelations = new HashSet<AlertHostsAccountsRelation>(
			0);
	private Set<AssetProjectsTestersRelation> assetProjectsTestersRelations = new HashSet<AssetProjectsTestersRelation>(
			0);
	private Set<ServerAuthHosts> serverAuthHostses = new HashSet<ServerAuthHosts>(
			0);
	private Set<AlertToggleStatusNotes> alertToggleStatusNoteses = new HashSet<AlertToggleStatusNotes>(
			0);
	private Set<AssetNetworkDevices> assetNetworkDeviceses = new HashSet<AssetNetworkDevices>(
			0);
	private Set<AccountLoginLogs> accountLoginLogses = new HashSet<AccountLoginLogs>(
			0);
	private Set<ServerAuthProjects> serverAuthProjectses = new HashSet<ServerAuthProjects>(
			0);
	private Set<AssetProjectsSysopsRelation> assetProjectsSysopsRelations = new HashSet<AssetProjectsSysopsRelation>(
			0);
	private Set<AssetDomainNames> assetDomainNameses = new HashSet<AssetDomainNames>(
			0);
	private Set<AutomationJobs> automationJobses = new HashSet<AutomationJobs>(
			0);

	// Constructors

	/** default constructor */
	public AccountAccounts() {
	}

	/** full constructor */
	public AccountAccounts(
			String email,
			String password,
			String name,
			String cellPhone,
			String apiKey,
			String avatar,
			Integer status,
			String locale,
			String timezone,
			Date created,
			Date lastLoginTime,
			String lastLoginIp,
			String intro,
			String sshPubKey,
			Set<IssueIssues> issueIssuesesForCreatorId,
			Set<AssetProjectsDevelopersRelation> assetProjectsDevelopersRelations,
			Set<IssueIssues> issueIssuesesForOwnerId,
			Set<IssueIssues> issueIssuesesForContacteeId,
			Set<DocArticles> docArticleses,
			Set<IssueIssues> issueIssuesesForCaretakerId,
			Set<AssetProjectsServerAccountsRelation> assetProjectsServerAccountsRelations,
			Set<IssueIssues> issueIssuesesForSolverId,
			Set<DocCategories> docCategorieses,
			Set<AssetProjects> assetProjectsesForLeaderId,
			Set<AssetProjects> assetProjectsesForDevLeaderId,
			Set<AccountAccountsRolesRelation> accountAccountsRolesRelations,
			Set<IssueIssueAlerts> issueIssueAlertses,
			Set<IssueIssueNotes> issueIssueNoteses,
			Set<AlertHostsAccountsRelation> alertHostsAccountsRelations,
			Set<AssetProjectsTestersRelation> assetProjectsTestersRelations,
			Set<ServerAuthHosts> serverAuthHostses,
			Set<AlertToggleStatusNotes> alertToggleStatusNoteses,
			Set<AssetNetworkDevices> assetNetworkDeviceses,
			Set<AccountLoginLogs> accountLoginLogses,
			Set<ServerAuthProjects> serverAuthProjectses,
			Set<AssetProjectsSysopsRelation> assetProjectsSysopsRelations,
			Set<AssetDomainNames> assetDomainNameses,
			Set<AutomationJobs> automationJobses) {
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
		this.issueIssuesesForCreatorId = issueIssuesesForCreatorId;
		this.assetProjectsDevelopersRelations = assetProjectsDevelopersRelations;
		this.issueIssuesesForOwnerId = issueIssuesesForOwnerId;
		this.issueIssuesesForContacteeId = issueIssuesesForContacteeId;
		this.docArticleses = docArticleses;
		this.issueIssuesesForCaretakerId = issueIssuesesForCaretakerId;
		this.assetProjectsServerAccountsRelations = assetProjectsServerAccountsRelations;
		this.issueIssuesesForSolverId = issueIssuesesForSolverId;
		this.docCategorieses = docCategorieses;
		this.assetProjectsesForLeaderId = assetProjectsesForLeaderId;
		this.assetProjectsesForDevLeaderId = assetProjectsesForDevLeaderId;
		this.accountAccountsRolesRelations = accountAccountsRolesRelations;
		this.issueIssueAlertses = issueIssueAlertses;
		this.issueIssueNoteses = issueIssueNoteses;
		this.alertHostsAccountsRelations = alertHostsAccountsRelations;
		this.assetProjectsTestersRelations = assetProjectsTestersRelations;
		this.serverAuthHostses = serverAuthHostses;
		this.alertToggleStatusNoteses = alertToggleStatusNoteses;
		this.assetNetworkDeviceses = assetNetworkDeviceses;
		this.accountLoginLogses = accountLoginLogses;
		this.serverAuthProjectses = serverAuthProjectses;
		this.assetProjectsSysopsRelations = assetProjectsSysopsRelations;
		this.assetDomainNameses = assetDomainNameses;
		this.automationJobses = automationJobses;
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

	@Column(name = "name", unique = true, length = 80)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccountsByCreatorId")
	public Set<IssueIssues> getIssueIssuesesForCreatorId() {
		return this.issueIssuesesForCreatorId;
	}

	public void setIssueIssuesesForCreatorId(
			Set<IssueIssues> issueIssuesesForCreatorId) {
		this.issueIssuesesForCreatorId = issueIssuesesForCreatorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<AssetProjectsDevelopersRelation> getAssetProjectsDevelopersRelations() {
		return this.assetProjectsDevelopersRelations;
	}

	public void setAssetProjectsDevelopersRelations(
			Set<AssetProjectsDevelopersRelation> assetProjectsDevelopersRelations) {
		this.assetProjectsDevelopersRelations = assetProjectsDevelopersRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccountsByOwnerId")
	public Set<IssueIssues> getIssueIssuesesForOwnerId() {
		return this.issueIssuesesForOwnerId;
	}

	public void setIssueIssuesesForOwnerId(
			Set<IssueIssues> issueIssuesesForOwnerId) {
		this.issueIssuesesForOwnerId = issueIssuesesForOwnerId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccountsByContacteeId")
	public Set<IssueIssues> getIssueIssuesesForContacteeId() {
		return this.issueIssuesesForContacteeId;
	}

	public void setIssueIssuesesForContacteeId(
			Set<IssueIssues> issueIssuesesForContacteeId) {
		this.issueIssuesesForContacteeId = issueIssuesesForContacteeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<DocArticles> getDocArticleses() {
		return this.docArticleses;
	}

	public void setDocArticleses(Set<DocArticles> docArticleses) {
		this.docArticleses = docArticleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccountsByCaretakerId")
	public Set<IssueIssues> getIssueIssuesesForCaretakerId() {
		return this.issueIssuesesForCaretakerId;
	}

	public void setIssueIssuesesForCaretakerId(
			Set<IssueIssues> issueIssuesesForCaretakerId) {
		this.issueIssuesesForCaretakerId = issueIssuesesForCaretakerId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<AssetProjectsServerAccountsRelation> getAssetProjectsServerAccountsRelations() {
		return this.assetProjectsServerAccountsRelations;
	}

	public void setAssetProjectsServerAccountsRelations(
			Set<AssetProjectsServerAccountsRelation> assetProjectsServerAccountsRelations) {
		this.assetProjectsServerAccountsRelations = assetProjectsServerAccountsRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccountsBySolverId")
	public Set<IssueIssues> getIssueIssuesesForSolverId() {
		return this.issueIssuesesForSolverId;
	}

	public void setIssueIssuesesForSolverId(
			Set<IssueIssues> issueIssuesesForSolverId) {
		this.issueIssuesesForSolverId = issueIssuesesForSolverId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<DocCategories> getDocCategorieses() {
		return this.docCategorieses;
	}

	public void setDocCategorieses(Set<DocCategories> docCategorieses) {
		this.docCategorieses = docCategorieses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccountsByLeaderId")
	public Set<AssetProjects> getAssetProjectsesForLeaderId() {
		return this.assetProjectsesForLeaderId;
	}

	public void setAssetProjectsesForLeaderId(
			Set<AssetProjects> assetProjectsesForLeaderId) {
		this.assetProjectsesForLeaderId = assetProjectsesForLeaderId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccountsByDevLeaderId")
	public Set<AssetProjects> getAssetProjectsesForDevLeaderId() {
		return this.assetProjectsesForDevLeaderId;
	}

	public void setAssetProjectsesForDevLeaderId(
			Set<AssetProjects> assetProjectsesForDevLeaderId) {
		this.assetProjectsesForDevLeaderId = assetProjectsesForDevLeaderId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<AccountAccountsRolesRelation> getAccountAccountsRolesRelations() {
		return this.accountAccountsRolesRelations;
	}

	public void setAccountAccountsRolesRelations(
			Set<AccountAccountsRolesRelation> accountAccountsRolesRelations) {
		this.accountAccountsRolesRelations = accountAccountsRolesRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<IssueIssueAlerts> getIssueIssueAlertses() {
		return this.issueIssueAlertses;
	}

	public void setIssueIssueAlertses(Set<IssueIssueAlerts> issueIssueAlertses) {
		this.issueIssueAlertses = issueIssueAlertses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<IssueIssueNotes> getIssueIssueNoteses() {
		return this.issueIssueNoteses;
	}

	public void setIssueIssueNoteses(Set<IssueIssueNotes> issueIssueNoteses) {
		this.issueIssueNoteses = issueIssueNoteses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<AlertHostsAccountsRelation> getAlertHostsAccountsRelations() {
		return this.alertHostsAccountsRelations;
	}

	public void setAlertHostsAccountsRelations(
			Set<AlertHostsAccountsRelation> alertHostsAccountsRelations) {
		this.alertHostsAccountsRelations = alertHostsAccountsRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<AssetProjectsTestersRelation> getAssetProjectsTestersRelations() {
		return this.assetProjectsTestersRelations;
	}

	public void setAssetProjectsTestersRelations(
			Set<AssetProjectsTestersRelation> assetProjectsTestersRelations) {
		this.assetProjectsTestersRelations = assetProjectsTestersRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<ServerAuthHosts> getServerAuthHostses() {
		return this.serverAuthHostses;
	}

	public void setServerAuthHostses(Set<ServerAuthHosts> serverAuthHostses) {
		this.serverAuthHostses = serverAuthHostses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<AlertToggleStatusNotes> getAlertToggleStatusNoteses() {
		return this.alertToggleStatusNoteses;
	}

	public void setAlertToggleStatusNoteses(
			Set<AlertToggleStatusNotes> alertToggleStatusNoteses) {
		this.alertToggleStatusNoteses = alertToggleStatusNoteses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<AssetNetworkDevices> getAssetNetworkDeviceses() {
		return this.assetNetworkDeviceses;
	}

	public void setAssetNetworkDeviceses(
			Set<AssetNetworkDevices> assetNetworkDeviceses) {
		this.assetNetworkDeviceses = assetNetworkDeviceses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<AccountLoginLogs> getAccountLoginLogses() {
		return this.accountLoginLogses;
	}

	public void setAccountLoginLogses(Set<AccountLoginLogs> accountLoginLogses) {
		this.accountLoginLogses = accountLoginLogses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<ServerAuthProjects> getServerAuthProjectses() {
		return this.serverAuthProjectses;
	}

	public void setServerAuthProjectses(
			Set<ServerAuthProjects> serverAuthProjectses) {
		this.serverAuthProjectses = serverAuthProjectses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<AssetProjectsSysopsRelation> getAssetProjectsSysopsRelations() {
		return this.assetProjectsSysopsRelations;
	}

	public void setAssetProjectsSysopsRelations(
			Set<AssetProjectsSysopsRelation> assetProjectsSysopsRelations) {
		this.assetProjectsSysopsRelations = assetProjectsSysopsRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<AssetDomainNames> getAssetDomainNameses() {
		return this.assetDomainNameses;
	}

	public void setAssetDomainNameses(Set<AssetDomainNames> assetDomainNameses) {
		this.assetDomainNameses = assetDomainNameses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountAccounts")
	public Set<AutomationJobs> getAutomationJobses() {
		return this.automationJobses;
	}

	public void setAutomationJobses(Set<AutomationJobs> automationJobses) {
		this.automationJobses = automationJobses;
	}

}