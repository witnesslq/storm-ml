package cn.disruptive.web.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.disruptive.core.po.PO;

/**
 * 
 * Description：用户信息
 * Data：		2015年9月11日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_user")
public class UserInfo implements PO<Integer>, Serializable {
	private static final long serialVersionUID = -5081520523165379797L;
	
	private Integer id;
	private String userCode;		// 用户Code
	private String userName;		// 用户名称
	private String userLoginName;	// 用户登陆名
	private String userPwd;			// 用户密码
	private Integer loginSwitch;	// 是否允许登陆，1：允许，0：不允许
	private Integer alarmSwitch;	// 是否接收报警，1：是，0：否
	private Date createTime;		// 数据创建时间
	private Date updateTime;		// 数据更改时间
	private Integer isDelete;		// 是否删除，1：删除，0：未删除

	public UserInfo() {
		super();
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "userCode", length = 10)
	public String getUserCode() {
		return userCode;
	}

	@Column(name = "userName", length = 10)
	public String getUserName() {
		return userName;
	}

	@Column(name = "userLoginName", length = 10)
	public String getUserLoginName() {
		return userLoginName;
	}

	@Column(name = "userPwd", length = 10)
	public String getUserPwd() {
		return userPwd;
	}

	@Column(name = "loginSwitch", length = 10)
	public Integer getLoginSwitch() {
		return loginSwitch;
	}

	@Column(name = "alarmSwitch", length = 10)
	public Integer getAlarmSwitch() {
		return alarmSwitch;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public void setLoginSwitch(Integer loginSwitch) {
		this.loginSwitch = loginSwitch;
	}

	public void setAlarmSwitch(Integer alarmSwitch) {
		this.alarmSwitch = alarmSwitch;
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

}
