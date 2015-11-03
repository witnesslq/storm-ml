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
 * Description：告警规则
 * Data：		2015年9月14日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_alarm_rule")
public class AlarmRule implements PO<Integer>, Serializable {
	
	private static final long serialVersionUID = 4194723193148405521L;

	private Integer id;
	private String expressing;	// 匹配规则
	private Integer isAction;	// 是否使用，1：是，0，否
	private Date createTime;	// 创建时间
	private Date updateTime;	// 更新时间
	private Integer isDelete;	// 是否删除，1：删除，0：未删除

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "expressing", length = 200)
	public String getExpressing() {
		return expressing;
	}

	@Column(name = "isAction")
	public Integer getIsAction() {
		return isAction;
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

	public void setExpressing(String expressing) {
		this.expressing = expressing;
	}

	public void setIsAction(Integer isAction) {
		this.isAction = isAction;
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
