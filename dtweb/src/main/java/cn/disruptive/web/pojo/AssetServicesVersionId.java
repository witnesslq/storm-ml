package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * Description：服务 版本
 * Data：		2015年10月26日
 */
@Embeddable
public class AssetServicesVersionId implements java.io.Serializable {

	private Integer id;
	private long transactionId;	// 变更id

	// Constructors

	/** default constructor */
	public AssetServicesVersionId() {
	}

	/** full constructor */
	public AssetServicesVersionId(Integer id, long transactionId) {
		this.id = id;
		this.transactionId = transactionId;
	}

	// Property accessors

	@Column(name = "id", nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		if (!(other instanceof AssetServicesVersionId))
			return false;
		AssetServicesVersionId castOther = (AssetServicesVersionId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& (this.getTransactionId() == castOther.getTransactionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (int) this.getTransactionId();
		return result;
	}

}