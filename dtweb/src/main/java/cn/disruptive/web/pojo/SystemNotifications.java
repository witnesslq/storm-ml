package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SystemNotifications entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "system_notifications", catalog = "ops")
public class SystemNotifications implements java.io.Serializable {

	// Fields

	private Integer id;
	private String linkTo;
	private String content;
	private Integer status;
	private Date created;

	// Constructors

	/** default constructor */
	public SystemNotifications() {
	}

	/** full constructor */
	public SystemNotifications(String linkTo, String content, Integer status,
			Date created) {
		this.linkTo = linkTo;
		this.content = content;
		this.status = status;
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

	@Column(name = "link_to")
	public String getLinkTo() {
		return this.linkTo;
	}

	public void setLinkTo(String linkTo) {
		this.linkTo = linkTo;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}