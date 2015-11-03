package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Transaction entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "transaction", catalog = "ops")
public class Transaction implements java.io.Serializable {

	// Fields

	private long id;
	private Date issuedAt;
	private String remoteAddr;
	private Integer userId;

	// Constructors

	/** default constructor */
	public Transaction() {
	}

	/** full constructor */
	public Transaction(Date issuedAt, String remoteAddr, Integer userId) {
		this.issuedAt = issuedAt;
		this.remoteAddr = remoteAddr;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "issued_at", length = 19)
	public Date getIssuedAt() {
		return this.issuedAt;
	}

	public void setIssuedAt(Date issuedAt) {
		this.issuedAt = issuedAt;
	}

	@Column(name = "remote_addr", length = 50)
	public String getRemoteAddr() {
		return this.remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}