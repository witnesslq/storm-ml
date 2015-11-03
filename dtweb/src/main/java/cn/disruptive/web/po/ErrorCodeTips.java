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
 * Description：错误处理建议
 * Data：		2015年9月18日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_error_tips")
public class ErrorCodeTips implements PO<Integer>, Serializable {

	private static final long serialVersionUID = -1317453514710509804L;
	
	private Integer id;
	private String respCode;	// 错误码
	private String errorTips;	// 解决建议
	private Date createTime; 	// 创建时间
	private Date updateTime; 	// 更新时间

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "respCode", length = 12)
	public String getRespCode() {
		return respCode;
	}

	@Column(name = "errorTips", length = 1024)
	public String getErrorTips() {
		return errorTips;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public void setErrorTips(String errorTips) {
		this.errorTips = errorTips;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
