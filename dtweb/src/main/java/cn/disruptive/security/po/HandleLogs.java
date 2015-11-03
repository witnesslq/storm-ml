package cn.disruptive.security.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import cn.disruptive.core.po.PO;

/**
 * 
 * Description：权限控制角色操作日志
 * Data：		2015年9月23日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_security_logs")
public class HandleLogs implements PO<String>, Serializable {

	private static final long serialVersionUID = -5115758100663921446L;

	private String id;
	private Integer userId; 	// 用户id
	private String resourceCod; // 访问的资源码（类名.方法）
	private String logInfo;		// 日志信息
	private String ipAddr;		// ip 地址
	private Date createTime;	// 数据创建时间

	public HandleLogs() {
		super();
	}

	public HandleLogs(String id, Integer userId, String resourceCod, String logInfo, String ipAddr, Date createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.resourceCod = resourceCod;
		this.logInfo = logInfo;
		this.ipAddr = ipAddr;
		this.createTime = createTime;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}

	@Column(name = "resourceCod", length = 32)
	public String getResourceCod() {
		return resourceCod;
	}

	@Column(name = "logInfo", length = 256)
	public String getLogInfo() {
		return logInfo;
	}

	@Column(name = "ipAddr", length = 64)
	public String getIpAddr() {
		return ipAddr;
	}

	@Column(name = "createTime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setResourceCod(String resourceCod) {
		this.resourceCod = resourceCod;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
}
