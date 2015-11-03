package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AlertTriggers entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "alert_triggers", catalog = "ops")
public class AlertTriggers implements java.io.Serializable {

	// Fields

	private AlertTriggersId id;
	private String triggerName;
	private boolean enabled;
	private Date enableAt;
	private Integer creatorId;
	private String creatorName;
	private Date created;

	// Constructors

	/** default constructor */
	public AlertTriggers() {
	}

	/** minimal constructor */
	public AlertTriggers(AlertTriggersId id) {
		this.id = id;
	}

	/** full constructor */
	public AlertTriggers(AlertTriggersId id, String triggerName,
			boolean enabled, Date enableAt, Integer creatorId,
			String creatorName, Date created) {
		this.id = id;
		this.triggerName = triggerName;
		this.enabled = enabled;
		this.enableAt = enableAt;
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.created = created;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "hostId", column = @Column(name = "host_id", nullable = false)),
			@AttributeOverride(name = "triggerId", column = @Column(name = "trigger_id", nullable = false)) })
	public AlertTriggersId getId() {
		return this.id;
	}

	public void setId(AlertTriggersId id) {
		this.id = id;
	}

	@Column(name = "trigger_name")
	public String getTriggerName() {
		return this.triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	@Column(name = "enabled")
	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "enable_at", length = 19)
	public Date getEnableAt() {
		return this.enableAt;
	}

	public void setEnableAt(Date enableAt) {
		this.enableAt = enableAt;
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

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}