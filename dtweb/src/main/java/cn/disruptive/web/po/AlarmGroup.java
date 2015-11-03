package cn.disruptive.web.po;

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

/**
 * 
 * Description：告警组
 * Data：		2015年9月14日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_alarm_group")
public class AlarmGroup {
	
	private Integer id;
	private String alarmGroupCode;	// 告警组Code
	private String alarmGroupName;	// 告警组名称
	private Integer isSwitch;		// 是否接收告警，1：是，0：否
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更改时间
	private Integer isDelete;		// 是否删除，1：删除，0：未删除

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "alarmGroupCode", length = 10)
	public String getAlarmGroupCode() {
		return alarmGroupCode;
	}

	@Column(name = "alarmGroupName", length = 20)
	public String getAlarmGroupName() {
		return alarmGroupName;
	}

	@Column(name = "isSwitch")
	public Integer getIsSwitch() {
		return isSwitch;
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

	public void setAlarmGroupCode(String alarmGroupCode) {
		this.alarmGroupCode = alarmGroupCode;
	}

	public void setAlarmGroupName(String alarmGroupName) {
		this.alarmGroupName = alarmGroupName;
	}

	public void setIsSwitch(Integer isSwitch) {
		this.isSwitch = isSwitch;
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
