package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Description：服务实例
 * Data：		2015年10月26日
 */
@Entity
@Table(name = "asset_service_instances", catalog = "ops")
public class AssetServiceInstances implements java.io.Serializable {

	private Integer id;
	private Integer hostId;		// 主机id
	private Integer serviceId;	// 服务id
	private String listen;		// 监听
	private Integer port;		// 端口

	// Constructors

	/** default constructor */
	public AssetServiceInstances() {
	}

	/** full constructor */
	public AssetServiceInstances(Integer hostId, Integer serviceId,
			String listen, Integer port) {
		this.hostId = hostId;
		this.serviceId = serviceId;
		this.listen = listen;
		this.port = port;
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

	@Column(name = "host_id")
	public Integer getHostId() {
		return this.hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

	@Column(name = "service_id")
	public Integer getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	@Column(name = "listen", length = 50)
	public String getListen() {
		return this.listen;
	}

	public void setListen(String listen) {
		this.listen = listen;
	}

	@Column(name = "port")
	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}