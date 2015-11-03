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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Description：主机信息
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "asset_hosts", catalog = "ops")
public class AssetHosts implements java.io.Serializable {

	private Integer id;
	private AssetProjects assetProjects;		// 所属资产项目
	private AssetVendors assetVendors;			// 所属供应商
	private AssetDomainNames assetDomainNames;	// 所属域名
	private AssetHostModels assetHostModels;	// 所属主机模型
	private AssetIdcs assetIdcs;				// 所属idc机房
	private String ipAddr;			// 内网网卡0ip地址
	private String eth1Ip;			// 外网网卡1ip地址
	private String ipInfo;			// 所有IP
	private String name;			// 主机名称
	private String macAddr;			// 主机mac地址
	private String ipmi;			// 智能平台管理接口地址
	private Integer status;			// 状态
	private Date purchaseDate;		// 采购时间
	private String serviceInstances;// 服务实例
	private String graphiteProject;	// 
	private Integer sysopId;		//
	private Integer creatorId;		// 创建人员id
	private String creatorName;		// 创建人员姓名
	private String intro;			// 简介
	private Date created;			// 创建时间
	private Date updated;			// 更新时间
	private Integer type;			// 主机类型，比如：物理机 虚拟机 交换机 云主机等
	private Date expired;			// 主机过期时间
	private Integer environment;	// 主机环境，比如：生产 测试 发布 压测等
	private Integer vcpus;			// 虚拟处理器
	private Integer accessSwitchId;	// 转换入口id
	private Integer cpuThreads;		// cup线程数
	private String kernelRelease;	// 内核版本号
	private String osFamily;		// 系统版本
	private String osRelease;		// 系统版本号
	private String cpuModel;		// CPU型号
	private String memTotal;		// 系统内存 GB
	private String accessSwitchPhysicalId;	// 接入的交换机id
	private String accessSwitchPhysicalPort;// 接入的交换机端口
	private String accessSwitchVlan;		// 接入的交换机域
	private String manufacturer;	// 制造商
	private String productname;		// 产品型号
	private String productUuid;		// 产品UUID
	private String productSn;		// 设备序列号
	private String idcRoom;			// 机房
	private String idcCabinet;		// 机柜
	private boolean unused;			// True表示未使用机器
	private Set<ServerAuthHosts> serverAuthHostses = new HashSet<ServerAuthHosts>(0);	// 包含的认证信息
	private Set<AutomationJobReturns> automationJobReturnses = new HashSet<AutomationJobReturns>(0);// 包换的可回滚的作业
	private Set<AlertHosts> alertHostses = new HashSet<AlertHosts>(0);	// 包含的告警主机
	private Set<AssetHostsServicesRelation> assetHostsServicesRelations = new HashSet<AssetHostsServicesRelation>(0); // 包含的服务
	private Set<IssueIssues> issueIssueses = new HashSet<IssueIssues>(0);	// 包含的问题
	private Set<MonitorHosts> monitorHostses = new HashSet<MonitorHosts>(0);// 包含的所监听的主机

	// Constructors

	/** default constructor */
	public AssetHosts() {
	}

	/** full constructor */
	public AssetHosts(AssetProjects assetProjects, AssetVendors assetVendors,
			AssetDomainNames assetDomainNames, AssetHostModels assetHostModels,
			AssetIdcs assetIdcs, String ipAddr, String eth1Ip, String name,
			String macAddr, String ipmi, Integer status, Date purchaseDate,
			String serviceInstances, String graphiteProject, Integer sysopId,
			Integer creatorId, String creatorName, String intro, Date created,
			Date updated, Integer type, Date expired, Integer environment,
			Integer vcpus, Integer accessSwitchId, Integer cpuThreads,
			String kernelRelease, String osFamily, String osRelease,
			String cpuModel, String memTotal, String accessSwitchPhysicalId,
			String accessSwitchPhysicalPort, String accessSwitchVlan,
			String manufacturer, String productname, String productUuid,
			String productSn, String idcRoom, String idcCabinet,
			boolean unused, String ipInfo,
			Set<ServerAuthHosts> serverAuthHostses,
			Set<AutomationJobReturns> automationJobReturnses,
			Set<AlertHosts> alertHostses,
			Set<AssetHostsServicesRelation> assetHostsServicesRelations,
			Set<IssueIssues> issueIssueses, Set<MonitorHosts> monitorHostses) {
		this.assetProjects = assetProjects;
		this.assetVendors = assetVendors;
		this.assetDomainNames = assetDomainNames;
		this.assetHostModels = assetHostModels;
		this.assetIdcs = assetIdcs;
		this.ipAddr = ipAddr;
		this.eth1Ip = eth1Ip;
		this.name = name;
		this.macAddr = macAddr;
		this.ipmi = ipmi;
		this.status = status;
		this.purchaseDate = purchaseDate;
		this.serviceInstances = serviceInstances;
		this.graphiteProject = graphiteProject;
		this.sysopId = sysopId;
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.intro = intro;
		this.created = created;
		this.updated = updated;
		this.type = type;
		this.expired = expired;
		this.environment = environment;
		this.vcpus = vcpus;
		this.accessSwitchId = accessSwitchId;
		this.cpuThreads = cpuThreads;
		this.kernelRelease = kernelRelease;
		this.osFamily = osFamily;
		this.osRelease = osRelease;
		this.cpuModel = cpuModel;
		this.memTotal = memTotal;
		this.accessSwitchPhysicalId = accessSwitchPhysicalId;
		this.accessSwitchPhysicalPort = accessSwitchPhysicalPort;
		this.accessSwitchVlan = accessSwitchVlan;
		this.manufacturer = manufacturer;
		this.productname = productname;
		this.productUuid = productUuid;
		this.productSn = productSn;
		this.idcRoom = idcRoom;
		this.idcCabinet = idcCabinet;
		this.unused = unused;
		this.ipInfo = ipInfo;
		this.serverAuthHostses = serverAuthHostses;
		this.automationJobReturnses = automationJobReturnses;
		this.alertHostses = alertHostses;
		this.assetHostsServicesRelations = assetHostsServicesRelations;
		this.issueIssueses = issueIssueses;
		this.monitorHostses = monitorHostses;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	public AssetProjects getAssetProjects() {
		return this.assetProjects;
	}

	public void setAssetProjects(AssetProjects assetProjects) {
		this.assetProjects = assetProjects;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	public AssetVendors getAssetVendors() {
		return this.assetVendors;
	}

	public void setAssetVendors(AssetVendors assetVendors) {
		this.assetVendors = assetVendors;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "domain_name_id")
	public AssetDomainNames getAssetDomainNames() {
		return this.assetDomainNames;
	}

	public void setAssetDomainNames(AssetDomainNames assetDomainNames) {
		this.assetDomainNames = assetDomainNames;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "host_model_id")
	public AssetHostModels getAssetHostModels() {
		return this.assetHostModels;
	}

	public void setAssetHostModels(AssetHostModels assetHostModels) {
		this.assetHostModels = assetHostModels;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idc_id")
	public AssetIdcs getAssetIdcs() {
		return this.assetIdcs;
	}

	public void setAssetIdcs(AssetIdcs assetIdcs) {
		this.assetIdcs = assetIdcs;
	}

	@Column(name = "ip_addr", length = 50)
	public String getIpAddr() {
		return this.ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@Column(name = "eth1_ip", length = 45)
	public String getEth1Ip() {
		return this.eth1Ip;
	}

	public void setEth1Ip(String eth1Ip) {
		this.eth1Ip = eth1Ip;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "mac_addr", length = 50)
	public String getMacAddr() {
		return this.macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	@Column(name = "ipmi", length = 45)
	public String getIpmi() {
		return this.ipmi;
	}

	public void setIpmi(String ipmi) {
		this.ipmi = ipmi;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "purchase_date", length = 10)
	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Column(name = "service_instances")
	public String getServiceInstances() {
		return this.serviceInstances;
	}

	public void setServiceInstances(String serviceInstances) {
		this.serviceInstances = serviceInstances;
	}

	@Column(name = "graphite_project", length = 150)
	public String getGraphiteProject() {
		return this.graphiteProject;
	}

	public void setGraphiteProject(String graphiteProject) {
		this.graphiteProject = graphiteProject;
	}

	@Column(name = "sysop_id")
	public Integer getSysopId() {
		return this.sysopId;
	}

	public void setSysopId(Integer sysopId) {
		this.sysopId = sysopId;
	}

	@Column(name = "creator_id")
	public Integer getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	@Column(name = "creator_name", length = 80)
	public String getCreatorName() {
		return this.creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	@Column(name = "intro", length = 65535)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "updated", length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "expired", length = 19)
	public Date getExpired() {
		return this.expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}

	@Column(name = "environment")
	public Integer getEnvironment() {
		return this.environment;
	}

	public void setEnvironment(Integer environment) {
		this.environment = environment;
	}

	@Column(name = "vcpus")
	public Integer getVcpus() {
		return this.vcpus;
	}

	public void setVcpus(Integer vcpus) {
		this.vcpus = vcpus;
	}

	@Column(name = "access_switch_id")
	public Integer getAccessSwitchId() {
		return this.accessSwitchId;
	}

	public void setAccessSwitchId(Integer accessSwitchId) {
		this.accessSwitchId = accessSwitchId;
	}

	@Column(name = "cpu_threads")
	public Integer getCpuThreads() {
		return this.cpuThreads;
	}

	public void setCpuThreads(Integer cpuThreads) {
		this.cpuThreads = cpuThreads;
	}

	@Column(name = "kernel_release", length = 30)
	public String getKernelRelease() {
		return this.kernelRelease;
	}

	public void setKernelRelease(String kernelRelease) {
		this.kernelRelease = kernelRelease;
	}

	@Column(name = "os_family", length = 30)
	public String getOsFamily() {
		return this.osFamily;
	}

	public void setOsFamily(String osFamily) {
		this.osFamily = osFamily;
	}

	@Column(name = "os_release", length = 30)
	public String getOsRelease() {
		return this.osRelease;
	}

	public void setOsRelease(String osRelease) {
		this.osRelease = osRelease;
	}

	@Column(name = "cpu_model", length = 50)
	public String getCpuModel() {
		return this.cpuModel;
	}

	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}

	@Column(name = "mem_total", length = 30)
	public String getMemTotal() {
		return this.memTotal;
	}

	public void setMemTotal(String memTotal) {
		this.memTotal = memTotal;
	}

	@Column(name = "access_switch_physical_id", length = 30)
	public String getAccessSwitchPhysicalId() {
		return this.accessSwitchPhysicalId;
	}

	public void setAccessSwitchPhysicalId(String accessSwitchPhysicalId) {
		this.accessSwitchPhysicalId = accessSwitchPhysicalId;
	}

	@Column(name = "access_switch_physical_port", length = 45)
	public String getAccessSwitchPhysicalPort() {
		return this.accessSwitchPhysicalPort;
	}

	public void setAccessSwitchPhysicalPort(String accessSwitchPhysicalPort) {
		this.accessSwitchPhysicalPort = accessSwitchPhysicalPort;
	}

	@Column(name = "access_switch_vlan", length = 30)
	public String getAccessSwitchVlan() {
		return this.accessSwitchVlan;
	}

	public void setAccessSwitchVlan(String accessSwitchVlan) {
		this.accessSwitchVlan = accessSwitchVlan;
	}

	@Column(name = "manufacturer", length = 30)
	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "productname", length = 30)
	public String getProductname() {
		return this.productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	@Column(name = "product_uuid", length = 45)
	public String getProductUuid() {
		return this.productUuid;
	}

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}

	@Column(name = "product_sn", length = 100)
	public String getProductSn() {
		return this.productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	@Column(name = "idc_room", length = 25)
	public String getIdcRoom() {
		return this.idcRoom;
	}

	public void setIdcRoom(String idcRoom) {
		this.idcRoom = idcRoom;
	}

	@Column(name = "idc_cabinet", length = 25)
	public String getIdcCabinet() {
		return this.idcCabinet;
	}

	public void setIdcCabinet(String idcCabinet) {
		this.idcCabinet = idcCabinet;
	}

	@Column(name = "unused")
	public boolean getUnused() {
		return this.unused;
	}

	public void setUnused(boolean unused) {
		this.unused = unused;
	}

	@Column(name = "ip_info")
	public String getIpInfo() {
		return this.ipInfo;
	}

	public void setIpInfo(String ipInfo) {
		this.ipInfo = ipInfo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetHosts")
	public Set<ServerAuthHosts> getServerAuthHostses() {
		return this.serverAuthHostses;
	}

	public void setServerAuthHostses(Set<ServerAuthHosts> serverAuthHostses) {
		this.serverAuthHostses = serverAuthHostses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetHosts")
	public Set<AutomationJobReturns> getAutomationJobReturnses() {
		return this.automationJobReturnses;
	}

	public void setAutomationJobReturnses(
			Set<AutomationJobReturns> automationJobReturnses) {
		this.automationJobReturnses = automationJobReturnses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetHosts")
	public Set<AlertHosts> getAlertHostses() {
		return this.alertHostses;
	}

	public void setAlertHostses(Set<AlertHosts> alertHostses) {
		this.alertHostses = alertHostses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetHosts")
	public Set<AssetHostsServicesRelation> getAssetHostsServicesRelations() {
		return this.assetHostsServicesRelations;
	}

	public void setAssetHostsServicesRelations(
			Set<AssetHostsServicesRelation> assetHostsServicesRelations) {
		this.assetHostsServicesRelations = assetHostsServicesRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetHosts")
	public Set<IssueIssues> getIssueIssueses() {
		return this.issueIssueses;
	}

	public void setIssueIssueses(Set<IssueIssues> issueIssueses) {
		this.issueIssueses = issueIssueses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetHosts")
	public Set<MonitorHosts> getMonitorHostses() {
		return this.monitorHostses;
	}

	public void setMonitorHostses(Set<MonitorHosts> monitorHostses) {
		this.monitorHostses = monitorHostses;
	}

}