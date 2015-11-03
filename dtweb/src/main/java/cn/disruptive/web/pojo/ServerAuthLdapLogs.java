package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ServerAuthLdapLogs entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "server_auth_ldap_logs", catalog = "ops")
public class ServerAuthLdapLogs implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer serverAuthLogId;
	private String entity;
	private Integer entityId;
	private Integer action;
	private String dn;
	private String attrs;
	private Date created;

	// Constructors

	/** default constructor */
	public ServerAuthLdapLogs() {
	}

	/** full constructor */
	public ServerAuthLdapLogs(Integer serverAuthLogId, String entity,
			Integer entityId, Integer action, String dn, String attrs,
			Date created) {
		this.serverAuthLogId = serverAuthLogId;
		this.entity = entity;
		this.entityId = entityId;
		this.action = action;
		this.dn = dn;
		this.attrs = attrs;
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

	@Column(name = "server_auth_log_id")
	public Integer getServerAuthLogId() {
		return this.serverAuthLogId;
	}

	public void setServerAuthLogId(Integer serverAuthLogId) {
		this.serverAuthLogId = serverAuthLogId;
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

	@Column(name = "action")
	public Integer getAction() {
		return this.action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	@Column(name = "dn")
	public String getDn() {
		return this.dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	@Column(name = "attrs", length = 65535)
	public String getAttrs() {
		return this.attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}