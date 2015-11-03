package cn.disruptive.web.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AutomationLockedFiles entity. @author Eclipse Persistence Tools
 */
@Entity
@Table(name = "automation_locked_files", catalog = "ops")
public class AutomationLockedFiles implements java.io.Serializable {

	// Fields

	private Integer id;
	private String filepath;

	// Constructors

	/** default constructor */
	public AutomationLockedFiles() {
	}

	/** full constructor */
	public AutomationLockedFiles(String filepath) {
		this.filepath = filepath;
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

	@Column(name = "filepath")
	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}