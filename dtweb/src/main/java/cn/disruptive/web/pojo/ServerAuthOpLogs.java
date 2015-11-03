package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ServerAuthOpLogs entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "server_auth_op_logs", catalog = "ops")
public class ServerAuthOpLogs implements java.io.Serializable {

	// Fields

	private Integer id;
	private String entity;
	private Integer entityId;
	private Integer creatorId;
	private String creatorName;
	private Integer action;
	private Date created;
	private Date finished;

	// Constructors

	/** default constructor */
	public ServerAuthOpLogs() {
	}

	/** full constructor */
	public ServerAuthOpLogs(String entity, Integer entityId, Integer creatorId,
			String creatorName, Integer action, Date created, Date finished) {
		this.entity = entity;
		this.entityId = entityId;
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.action = action;
		this.created = created;
		this.finished = finished;
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

	@Column(name = "creator_id")
	public Integer getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	@Column(name = "creator_name", length = 50)
	public String getCreatorName() {
		return this.creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	@Column(name = "action")
	public Integer getAction() {
		return this.action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "finished", length = 19)
	public Date getFinished() {
		return this.finished;
	}

	public void setFinished(Date finished) {
		this.finished = finished;
	}

}