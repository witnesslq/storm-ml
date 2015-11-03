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
import javax.persistence.UniqueConstraint;

/**
 * AccountRoles entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "account_roles", catalog = "ops", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class AccountRoles implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set<AccountAccountsRolesRelation> accountAccountsRolesRelations = new HashSet<AccountAccountsRolesRelation>(
			0);

	// Constructors

	/** default constructor */
	public AccountRoles() {
	}

	/** full constructor */
	public AccountRoles(String name,
			Set<AccountAccountsRolesRelation> accountAccountsRolesRelations) {
		this.name = name;
		this.accountAccountsRolesRelations = accountAccountsRolesRelations;
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

	@Column(name = "name", unique = true, length = 80)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountRoles")
	public Set<AccountAccountsRolesRelation> getAccountAccountsRolesRelations() {
		return this.accountAccountsRolesRelations;
	}

	public void setAccountAccountsRolesRelations(
			Set<AccountAccountsRolesRelation> accountAccountsRolesRelations) {
		this.accountAccountsRolesRelations = accountAccountsRolesRelations;
	}

}