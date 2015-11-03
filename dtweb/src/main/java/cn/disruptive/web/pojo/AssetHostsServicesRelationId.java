package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * Description：资产主机和服务的关系
 * Data：		2015年10月23日
 */
@Embeddable
public class AssetHostsServicesRelationId implements java.io.Serializable {

	private Integer hostId;		// 主机id
	private Integer serviceId;	// 服务id

	/** default constructor */
	public AssetHostsServicesRelationId() {
	}

	/** full constructor */
	public AssetHostsServicesRelationId(Integer hostId, Integer serviceId) {
		this.hostId = hostId;
		this.serviceId = serviceId;
	}

	// Property accessors

	@Column(name = "host_id", nullable = false)
	public Integer getHostId() {
		return this.hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

	@Column(name = "service_id", nullable = false)
	public Integer getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AssetHostsServicesRelationId))
			return false;
		AssetHostsServicesRelationId castOther = (AssetHostsServicesRelationId) other;

		return ((this.getHostId() == castOther.getHostId()) || (this
				.getHostId() != null && castOther.getHostId() != null && this
				.getHostId().equals(castOther.getHostId())))
				&& ((this.getServiceId() == castOther.getServiceId()) || (this
						.getServiceId() != null
						&& castOther.getServiceId() != null && this
						.getServiceId().equals(castOther.getServiceId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getHostId() == null ? 0 : this.getHostId().hashCode());
		result = 37 * result
				+ (getServiceId() == null ? 0 : this.getServiceId().hashCode());
		return result;
	}

}