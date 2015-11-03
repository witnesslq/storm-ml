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
 * Description：接口时间统计
 * Data：		2015年9月14日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_interface_timeout")
public class InterfaceTimeOut implements PO<Integer>, Serializable {

	private static final long serialVersionUID = -1580655760536773835L;
	
	private Integer id;
	private String appName;			// 应用名称
	private String serverName;		// 主机名
	private String sceneCode;		// 场景码
	private String  sceneNameEns;	// 英文缩写
	private String interfaceName;	// 接口
	private Integer timeOut;		// 调用耗时
	private String trackNo;			// 业务跟踪号
	private String rowKey;			// HBase RowKey规则
	private String reqRowKey;		// 
	private String resRowKey;		// 
	private Date recordTime;		// 记录时间
	private String recordTimeStr;	// 
	private String respCode;		// 响应码
	private Integer isException; 	// 是否是异常日志，1：是，0：否

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "appName", length = 255)
	public String getAppName() {
		return appName;
	}

	@Column(name = "serverName", length = 255)
	public String getServerName() {
		return serverName;
	}

	@Column(name = "sceneCode", length = 255)
	public String getSceneCode() {
		return sceneCode;
	}

	@Column(name = "sceneNameEns", length = 10)
	public String getSceneNameEns() {
		return sceneNameEns;
	}

	@Column(name = "interfaceName", length = 255)
	public String getInterfaceName() {
		return interfaceName;
	}

	@Column(name = "timeOut")
	public Integer getTimeOut() {
		return timeOut;
	}

	@Column(name = "trackNo", length = 255)
	public String getTrackNo() {
		return trackNo;
	}

	@Column(name = "rowKey", length = 255)
	public String getRowKey() {
		return rowKey;
	}

	@Column(name = "reqRowKey", length = 255)
	public String getReqRowKey() {
		return reqRowKey;
	}

	@Column(name = "resRowKey", length = 255)
	public String getResRowKey() {
		return resRowKey;
	}

	@Column(name = "recordTime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRecordTime() {
		return recordTime;
	}

	@Column(name = "recordTimeStr", length = 255)
	public String getRecordTimeStr() {
		return recordTimeStr;
	}

	@Column(name = "respCode", length = 255)
	public String getRespCode() {
		return respCode;
	}

	@Column(name = "isException")
	public Integer getIsException() {
		return isException;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

	public void setTrackNo(String trackNo) {
		this.trackNo = trackNo;
	}

	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

	public void setReqRowKey(String reqRowKey) {
		this.reqRowKey = reqRowKey;
	}

	public void setResRowKey(String resRowKey) {
		this.resRowKey = resRowKey;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public void setRecordTimeStr(String recordTimeStr) {
		this.recordTimeStr = recordTimeStr;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public void setIsException(Integer isException) {
		this.isException = isException;
	}

	public void setSceneNameEns(String sceneNameEns) {
		this.sceneNameEns = sceneNameEns;
	}
	
}
