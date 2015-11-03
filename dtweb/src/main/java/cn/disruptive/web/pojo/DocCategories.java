package cn.disruptive.web.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DocCategories entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "doc_categories", catalog = "ops")
public class DocCategories implements java.io.Serializable {

	// Fields

	private Integer id;
	private AccountAccounts accountAccounts;
	private Integer parentId;
	private String name;
	private Set<DocArticles> docArticleses = new HashSet<DocArticles>(0);

	// Constructors

	/** default constructor */
	public DocCategories() {
	}

	/** full constructor */
	public DocCategories(AccountAccounts accountAccounts, Integer parentId,
			String name, Set<DocArticles> docArticleses) {
		this.accountAccounts = accountAccounts;
		this.parentId = parentId;
		this.name = name;
		this.docArticleses = docArticleses;
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
	@JoinColumn(name = "owner_id")
	public AccountAccounts getAccountAccounts() {
		return this.accountAccounts;
	}

	public void setAccountAccounts(AccountAccounts accountAccounts) {
		this.accountAccounts = accountAccounts;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "docCategories")
	public Set<DocArticles> getDocArticleses() {
		return this.docArticleses;
	}

	public void setDocArticleses(Set<DocArticles> docArticleses) {
		this.docArticleses = docArticleses;
	}

}