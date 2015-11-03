package cn.disruptive.security.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.disruptive.core.po.PO;

/**
 * 
 * Description：权限控制资源表
 * Data：		2015年9月23日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_security_resource")
public class Resource implements PO<Integer>, Serializable {

	private static final long serialVersionUID = -4746639654233564383L;

	private Integer id;
	private String resourceCode; // 资源码（类名.方法）
	private String resourceName; // 资源名称
	private String resourceDes; // 资源描述
	private Date createTime;	// 数据创建时间
	private Date updateTime;	// 数据更新时间
	private Integer isDelete;	// 是否删除，1：删除，0：未删除

	private Resource parentResource;								// 父资源
	private Set<Resource> childResource = new HashSet<Resource>();	// 子资源
	private Set<Role> roles = new HashSet<Role>();					// 资源对应的角色

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "resourceCode", length = 32)
	public String getResourceCode() {
		return resourceCode;
	}

	@Column(name = "resourceName", length = 32)
	public String getResourceName() {
		return resourceName;
	}

	@Column(name = "resourceDes", length = 256)
	public String getResourceDes() {
		return resourceDes;
	}

	@Column(name = "createTime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	@Column(name = "updateTime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return updateTime;
	}

	@Column(name = "isDelete")
	public Integer getIsDelete() {
		return isDelete;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "parentResourceId", updatable = true, insertable = true)
	public Resource getParentResource() {
		return parentResource;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parentResource", fetch = FetchType.EAGER)
	public Set<Resource> getChildResource() {
		return childResource;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_security_role_resource", joinColumns = @JoinColumn(name = "resourceId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public void setResourceDes(String resourceDes) {
		this.resourceDes = resourceDes;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public void setParentResource(Resource parentResource) {
		this.parentResource = parentResource;
	}

	public void setChildResource(Set<Resource> childResource) {
		this.childResource = childResource;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
