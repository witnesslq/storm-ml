package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DocArticlesVersion entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "doc_articles_version", catalog = "ops")
public class DocArticlesVersion implements java.io.Serializable {

	// Fields

	private DocArticlesVersionId id;
	private String title;
	private String body;
	private Integer authorId;
	private Date created;
	private Integer categoryId;
	private long endTransactionId;
	private short operationType;

	// Constructors

	/** default constructor */
	public DocArticlesVersion() {
	}

	/** minimal constructor */
	public DocArticlesVersion(DocArticlesVersionId id, short operationType) {
		this.id = id;
		this.operationType = operationType;
	}

	/** full constructor */
	public DocArticlesVersion(DocArticlesVersionId id, String title,
			String body, Integer authorId, Date created, Integer categoryId,
			long endTransactionId, short operationType) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.authorId = authorId;
		this.created = created;
		this.categoryId = categoryId;
		this.endTransactionId = endTransactionId;
		this.operationType = operationType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)) })
	public DocArticlesVersionId getId() {
		return this.id;
	}

	public void setId(DocArticlesVersionId id) {
		this.id = id;
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

	@Column(name = "author_id")
	public Integer getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "category_id")
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "end_transaction_id")
	public long getEndTransactionId() {
		return this.endTransactionId;
	}

	public void setEndTransactionId(long endTransactionId) {
		this.endTransactionId = endTransactionId;
	}

	@Column(name = "operation_type", nullable = false)
	public short getOperationType() {
		return this.operationType;
	}

	public void setOperationType(short operationType) {
		this.operationType = operationType;
	}

}