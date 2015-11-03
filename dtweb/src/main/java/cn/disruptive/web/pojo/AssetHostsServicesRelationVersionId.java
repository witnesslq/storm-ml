package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * Description：主机和服务关系版本
 * Data：		2015年10月23日
 */
@Embeddable
public class AssetHostsServicesRelationVersionId implements java.io.Serializable {

	private Integer hostId;			// 主机id
	private Integer serviceId;		// 服务id
	private long transactionId;		// 事物id

	// Constructors

	/** default constructor */
	public AssetHostsServicesRelationVersionId() {
	}

	/** full constructor */
	public AssetHostsServicesRelationVersionId(Integer hostId,
			Integer serviceId, long transactionId) {
		this.hostId = hostId;
		this.serviceId = serviceId;
		this.transactionId = transactionId;
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

	@Column(name = "transaction_id", nullable = false)
	public long getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AssetHostsServicesRelationVersionId))
			return false;
		AssetHostsServicesRelationVersionId castOther = (AssetHostsServicesRelationVersionId) other;

		return ((this.getHostId() == castOther.getHostId()) || (this
				.getHostId() != null && castOther.getHostId() != null && this
				.getHostId().equals(castOther.getHostId())))
				&& ((this.getServiceId() == castOther.getServiceId()) || (this
						.getServiceId() != null
						&& castOther.getServiceId() != null && this
						.getServiceId().equals(castOther.getServiceId())))
				&& (this.getTransactionId() == castOther.getTransactionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getHostId() == null ? 0 : this.getHostId().hashCode());
		result = 37 * result
				+ (getServiceId() == null ? 0 : this.getServiceId().hashCode());
		result = 37 * result + (int) this.getTransactionId();
		return result;
	}

}