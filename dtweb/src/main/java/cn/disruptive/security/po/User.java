package cn.disruptive.security.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.disruptive.core.po.PO;

/**
 * 
 * Description：权限控制用户信息
 * Data：		2015年9月23日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_user")
public class User implements PO<Integer>, Serializable {
	
	private static final long serialVersionUID = -2490661469887106831L;

	private Integer id;
	private String userLoginName;	// 用户登陆名

	private Set<Role> roleses = new HashSet<Role>(); // 用户对应的角色

	public User() {
		super();
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "userLoginName", length = 10)
	public String getUserLoginName() {
		return userLoginName;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_security_role_user", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	public Set<Role> getRoleses() {
		return roleses;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public void setRoleses(Set<Role> roleses) {
		this.roleses = roleses;
	}

}
