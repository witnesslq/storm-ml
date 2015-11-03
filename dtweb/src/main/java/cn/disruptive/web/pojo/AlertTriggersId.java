package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AlertTriggersId entity. @author Eclipse Persistence Tools
 */
@Embeddable
public class AlertTriggersId implements java.io.Serializable {

	// Fields

	private Integer hostId;
	private Integer triggerId;

	// Constructors

	/** default constructor */
	public AlertTriggersId() {
	}

	/** full constructor */
	public AlertTriggersId(Integer hostId, Integer triggerId) {
		this.hostId = hostId;
		this.triggerId = triggerId;
	}

	// Property accessors

	@Column(name = "host_id", nullable = false)
	public Integer getHostId() {
		return this.hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

	@Column(name = "trigger_id", nullable = false)
	public Integer getTriggerId() {
		return this.triggerId;
	}

	public void setTriggerId(Integer triggerId) {
		this.triggerId = triggerId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AlertTriggersId))
			return false;
		AlertTriggersId castOther = (AlertTriggersId) other;

		return ((this.getHostId() == castOther.getHostId()) || (this
				.getHostId() != null && castOther.getHostId() != null && this
				.getHostId().equals(castOther.getHostId())))
				&& ((this.getTriggerId() == castOther.getTriggerId()) || (this
						.getTriggerId() != null
						&& castOther.getTriggerId() != null && this
						.getTriggerId().equals(castOther.getTriggerId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getHostId() == null ? 0 : this.getHostId().hashCode());
		result = 37 * result
				+ (getTriggerId() == null ? 0 : this.getTriggerId().hashCode());
		return result;
	}

}