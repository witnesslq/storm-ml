package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SystemWatchdog entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "system_watchdog", catalog = "ops")
public class SystemWatchdog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer creatorId;
	private String creatorName;
	private String type;
	private String message;
	private Integer severity;
	private String link;
	private String location;
	private String referer;
	private String hostname;
	private Date created;

	// Constructors

	/** default constructor */
	public SystemWatchdog() {
	}

	/** full constructor */
	public SystemWatchdog(Integer creatorId, String creatorName, String type,
			String message, Integer severity, String link, String location,
			String referer, String hostname, Date created) {
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.type = type;
		this.message = message;
		this.severity = severity;
		this.link = link;
		this.location = location;
		this.referer = referer;
		this.hostname = hostname;
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

	@Column(name = "type", length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "message", length = 65535)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "severity")
	public Integer getSeverity() {
		return this.severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	@Column(name = "link")
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "location")
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "referer")
	public String getReferer() {
		return this.referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	@Column(name = "hostname", length = 128)
	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}