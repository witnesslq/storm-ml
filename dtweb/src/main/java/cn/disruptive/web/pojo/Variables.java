package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Variables entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "variables", catalog = "ops")
public class Variables implements java.io.Serializable {

	// Fields

	private String name;
	private String value;

	// Constructors

	/** default constructor */
	public Variables() {
	}

	/** full constructor */
	public Variables(String value) {
		this.value = value;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "name", unique = true, nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "value", length = 65535)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}