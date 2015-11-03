package cn.disruptive.web.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 
 * Description：告警配置
 * Data：		2015年9月14日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_alarm_conf")
public class AlarmConf {
	
	private Integer id;
	private Integer alarmRuleId;	// 告警规则id
	private Integer alarmGroupId;	// 告警组Id
	private String alarmGroupCode;	// 告警组Code
	private Integer alarmTypeId;	// 告警类型Id
	private String alarmTypeCode;	// 告警类型Code
	private Integer isDelete;		// 是否删除，1：删除，0：未删除

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "alarmRuleId")
	public Integer getAlarmRuleId() {
		return alarmRuleId;
	}

	@Column(name = "alarmGroupId")
	public Integer getAlarmGroupId() {
		return alarmGroupId;
	}

	@Column(name = "alarmGroupCode", length = 10)
	public String getAlarmGroupCode() {
		return alarmGroupCode;
	}

	@Column(name = "alarmTypeId")
	public Integer getAlarmTypeId() {
		return alarmTypeId;
	}

	@Column(name = "alarmTypeCode", length = 10)
	public String getAlarmTypeCode() {
		return alarmTypeCode;
	}

	@Column(name = "isDelete")
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAlarmRuleId(Integer alarmRuleId) {
		this.alarmRuleId = alarmRuleId;
	}

	public void setAlarmGroupId(Integer alarmGroupId) {
		this.alarmGroupId = alarmGroupId;
	}

	public void setAlarmGroupCode(String alarmGroupCode) {
		this.alarmGroupCode = alarmGroupCode;
	}

	public void setAlarmTypeId(Integer alarmTypeId) {
		this.alarmTypeId = alarmTypeId;
	}

	public void setAlarmTypeCode(String alarmTypeCode) {
		this.alarmTypeCode = alarmTypeCode;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
