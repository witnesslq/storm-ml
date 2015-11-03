package cn.disruptive.web.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * AlertServices entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "alert_services", catalog = "ops")
public class AlertServices implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set<IssueIssues> issueIssueses = new HashSet<IssueIssues>(0);

	// Constructors

	/** default constructor */
	public AlertServices() {
	}

	/** full constructor */
	public AlertServices(String name, Set<IssueIssues> issueIssueses) {
		this.name = name;
		this.issueIssueses = issueIssueses;
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

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "alertServices")
	public Set<IssueIssues> getIssueIssueses() {
		return this.issueIssueses;
	}

	public void setIssueIssueses(Set<IssueIssues> issueIssueses) {
		this.issueIssueses = issueIssueses;
	}

}