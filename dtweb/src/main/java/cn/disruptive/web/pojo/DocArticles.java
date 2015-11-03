package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DocArticles entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "doc_articles", catalog = "ops")
public class DocArticles implements java.io.Serializable {

	// Fields

	private Integer id;
	private AccountAccounts accountAccounts;
	private DocCategories docCategories;
	private String title;
	private String body;
	private Date created;
	private Date updated;

	// Constructors

	/** default constructor */
	public DocArticles() {
	}

	/** full constructor */
	public DocArticles(AccountAccounts accountAccounts,
			DocCategories docCategories, String title, String body,
			Date created, Date updated) {
		this.accountAccounts = accountAccounts;
		this.docCategories = docCategories;
		this.title = title;
		this.body = body;
		this.created = created;
		this.updated = updated;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	public AccountAccounts getAccountAccounts() {
		return this.accountAccounts;
	}

	public void setAccountAccounts(AccountAccounts accountAccounts) {
		this.accountAccounts = accountAccounts;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	public DocCategories getDocCategories() {
		return this.docCategories;
	}

	public void setDocCategories(DocCategories docCategories) {
		this.docCategories = docCategories;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "body", length = 65535)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "updated", length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}