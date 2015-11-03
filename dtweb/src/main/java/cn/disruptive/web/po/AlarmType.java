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
 * Description：警告类型
 * Data：		2015年9月14日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_alarm_type")
public class AlarmType implements PO<Integer>, Serializable {
	
	private static final long serialVersionUID = -2654474573497623825L;

	private Integer id;
	private String alarmTypeCode;	// 告警类型Code
	private String alarmTypeName;	// 告警类型名称
	private String alarmTypeDes;	// 告警类型名称描述
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private Integer isDelete;		// 是否删除，1：删除，0：未删除

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "alarmTypeCode", length = 10)
	public String getAlarmTypeCode() {
		return alarmTypeCode;
	}

	@Column(name = "alarmTypeName", length = 20)
	public String getAlarmTypeName() {
		return alarmTypeName;
	}

	@Column(name = "alarmTypeDes", length = 1000)
	public String getAlarmTypeDes() {
		return alarmTypeDes;
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

	public void setAlarmTypeCode(String alarmTypeCode) {
		this.alarmTypeCode = alarmTypeCode;
	}

	public void setAlarmTypeName(String alarmTypeName) {
		this.alarmTypeName = alarmTypeName;
	}

	public void setAlarmTypeDes(String alarmTypeDes) {
		this.alarmTypeDes = alarmTypeDes;
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
