package cn.disruptive.web.pojo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Description：
 * Data：		2015年10月26日
 */
@Entity
@Table(name = "asset_network_devices_version", catalog = "ops")
public class AssetNetworkDevicesVersion implements java.io.Serializable {

	// Fields

	private AssetNetworkDevicesVersionId id;
	private String ipAddr;		// 网址
	private String macAddr;		// mac地址
	private String ipmi;		// 智能平台管理接口地址
	private Integer status;		// 状态
	private Date purchaseDate;	// 采购时间
	private Integer creatorId;	// 创建人员id
	private String creatorName;	// 创建人姓名
	private String intro;		// 简介
	private Date created;		// 创建时间
	private Integer type;		// 类型 比如：物理机 虚拟机 交换机 云主机等
	private Date expired;		// 主机过期时间
	private Integer vcpus;		// 虚拟处理器
	private Integer cpuThreads;	// cup线程数
	private String kernelRelease;// 内核版本号
	private String osFamily;	// 系统版本
	private String osRelease;	// 系统版本号
	private String cpuModel;	// CPU型号
	private String memTotal;	// 系统内存 GB
	private String manufacturer;// 制造商
	private String productname;	// 产品型号
	private String productUuid;	// 产品UUID
	private String productSn;	// 设备序列号
	private Integer accessSwitchId;			// 接入的交换机id
	private String accessSwitchPhysicalId;	// 接入的交换机id
	private String accessSwitchPhysicalPort;// 接入的交换机端口
	private String accessSwitchVlan;		// 接入的交换机域
	private String idcRoom;			// 机房
	private String idcCabinet;		// 机柜
	private Integer sysopId;		//
	private long endTransactionId;	// 
	private short operationType;	// 操作类型

	// Constructors

	/** default constructor */
	public AssetNetworkDevicesVersion() {
	}

	/** minimal constructor */
	public AssetNetworkDevicesVersion(AssetNetworkDevicesVersionId id,
			short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public AssetNetworkDevicesVersion(AssetNetworkDevicesVersionId id,
			String ipAddr, String macAddr, String ipmi, Integer status,
			Date purchaseDate, Integer creatorId, String creatorName,
			String intro, Date created, Integer type, Date expired,
			Integer vcpus, Integer cpuThreads, String kernelRelease,
			String osFamily, String osRelease, String cpuModel,
			String memTotal, String manufacturer, String productname,
			String productUuid, String productSn, Integer accessSwitchId,
			String accessSwitchPhysicalId, String accessSwitchPhysicalPort,
			String accessSwitchVlan, String idcRoom, String idcCabinet,
			Integer sysopId, long endTransactionId, short operationType) {
		this.id = id;
		this.ipAddr = ipAddr;
		this.macAddr = macAddr;
		this.ipmi = ipmi;
		this.status = status;
		this.purchaseDate = purchaseDate;
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.intro = intro;
		this.created = created;
		this.type = type;
		this.expired = expired;
		this.vcpus = vcpus;
		this.cpuThreads = cpuThreads;
		this.kernelRelease = kernelRelease;
		this.osFamily = osFamily;
		this.osRelease = osRelease;
		this.cpuModel = cpuModel;
		this.memTotal = memTotal;
		this.manufacturer = manufacturer;
		this.productname = productname;
		this.productUuid = productUuid;
		this.productSn = productSn;
		this.accessSwitchId = accessSwitchId;
		this.accessSwitchPhysicalId = accessSwitchPhysicalId;
		this.accessSwitchPhysicalPort = accessSwitchPhysicalPort;
		this.accessSwitchVlan = accessSwitchVlan;
		this.idcRoom = idcRoom;
		this.idcCabinet = idcCabinet;
		this.sysopId = sysopId;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public AssetNetworkDevicesVersionId getId() {
		return this.id;
	}

	public void setId(AssetNetworkDevicesVersionId id) {
		this.id = id;
	}

	@Column(name = "ip_addr", length = 50)
	public String getIpAddr() {
		return this.ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@Column(name = "mac_addr", length = 50)
	public String getMacAddr() {
		return this.macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	@Column(name = "ipmi", length = 50)
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

	@Column(name = "vcpus")
	public Integer getVcpus() {
		return this.vcpus;
	}

	public void setVcpus(Integer vcpus) {
		this.vcpus = vcpus;
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

	@Column(name = "product_uuid", length = 50)
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

	@Column(name = "access_switch_id")
	public Integer getAccessSwitchId() {
		return this.accessSwitchId;
	}

	public void setAccessSwitchId(Integer accessSwitchId) {
		this.accessSwitchId = accessSwitchId;
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

	@Column(name = "sysop_id")
	public Integer getSysopId() {
		return this.sysopId;
	}

	public void setSysopId(Integer sysopId) {
		this.sysopId = sysopId;
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