package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SystemAudits entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "system_audits", catalog = "ops")
public class SystemAudits implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer creatorId;
	private String creatorName;
	private String entity;
	private Integer entityId;
	private String data;
	private Date created;

	// Constructors

	/** default constructor */
	public SystemAudits() {
	}

	/** full constructor */
	public SystemAudits(Integer creatorId, String creatorName, String entity,
			Integer entityId, String data, Date created) {
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.entity = entity;
		this.entityId = entityId;
		this.data = data;
		this.created = created;
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

	@Column(name = "entity", length = 50)
	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Column(name = "entity_id")
	public Integer getEntityId() {
		return this.entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	@Column(name = "data", length = 65535)
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}