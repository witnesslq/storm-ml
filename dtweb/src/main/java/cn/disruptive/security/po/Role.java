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
 * Description：权限控制角色表
 * Data：		2015年9月23日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_security_role")
public class Role implements PO<Integer>, Serializable {

	private static final long serialVersionUID = 8084972112604132788L;

	private Integer id;
	private String roleCode;	// 角色Code
	private String roleName;	// 角色名称
	private String roleDes;		// 角色描述
	private Date createTime;	// 数据创建时间
	private Date updateTime;	// 数据更新时间
	private Integer isDelete;	// 是否删除，1：删除，0：未删除

	private Role parentRole;									// 父角色(角色组，不关联具体的资源，也不对应资源组)
	private Set<Role> childRole = new HashSet<Role>();			// 子角色
	private Set<Resource> resources = new HashSet<Resource>(); 	// 角色对应的资源（父角色不对具体的资源）
	private Set<User> users = new HashSet<User>();				// 角色对应的用户

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "roleCode", length = 10)
	public String getRoleCode() {
		return roleCode;
	}

	@Column(name = "roleName", length = 32)
	public String getRoleName() {
		return roleName;
	}

	@Column(name = "roleDes", length = 256)
	public String getRoleDes() {
		return roleDes;
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
	@JoinColumn(name = "parentRoleId", updatable = true, insertable = true)
	public Role getParentRole() {
		return parentRole;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parentRole", fetch = FetchType.EAGER)
	public Set<Role> getChildRole() {
		return childRole;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_security_role_resource", joinColumns = @JoinColumn(name = "roleId"), inverseJoinColumns = @JoinColumn(name = "resourceId"))
	public Set<Resource> getResources() {
		return resources;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_security_role_user", joinColumns = @JoinColumn(name = "roleId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	public Set<User> getUsers() {
		return users;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setRoleDes(String roleDes) {
		this.roleDes = roleDes;
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

	public void setParentRole(Role parentRole) {
		this.parentRole = parentRole;
	}

	public void setChildRole(Set<Role> childRole) {
		this.childRole = childRole;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
