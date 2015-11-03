package cn.disruptive.web.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.disruptive.core.po.PO;

/**
 * 
 * Description：错误码信息表
 * Data：		2015年9月14日
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_error_code")
public class ErrorCode implements PO<Integer>, Serializable {
	
	private static final long serialVersionUID = -417700822979582554L;

	private Integer id;
	private String tradingName;		// 交易名称
	private String trakNo;			// 请求序列号
	private String sceneNameEns;	// 场景组英文缩写
	private String channelNo;		// 渠道号
	private String triggerPoint;	// 错误触发点
	private String serverName;		// 服务名
	private String interfaceName;	// 接口名
	private String errorCode;		// 错误码
	private String errorDes;		// 系统错误描述
	private String businessDes;		// 业务描述
	private String orgnlSeqNo;		// 原始跟踪号
	private String bizTrackNo;		// 业务跟踪号
	private Integer isDelete;		// 删除，1：是，0：否

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(name = "tradingName", length = 255)
	public String getTradingName() {
		return tradingName;
	}

	@Column(name = "trakNo", length = 20)
	public String getTrakNo() {
		return trakNo;
	}

	@Column(name = "sceneNameEns", length = 10)
	public String getSceneNameEns() {
		return sceneNameEns;
	}

	@Column(name = "channelNo", length = 20)
	public String getChannelNo() {
		return channelNo;
	}

	@Column(name = "triggerPoint", length = 20)
	public String getTriggerPoint() {
		return triggerPoint;
	}

	@Column(name = "serverName", length = 20)
	public String getServerName() {
		return serverName;
	}

	@Column(name = "interfaceName", length = 20)
	public String getInterfaceName() {
		return interfaceName;
	}

	@Column(name = "errorCode", length = 20)
	public String getErrorCode() {
		return errorCode;
	}

	@Column(name = "errorDes", length = 255)
	public String getErrorDes() {
		return errorDes;
	}

	@Column(name = "businessDes", length = 255)
	public String getBusinessDes() {
		return businessDes;
	}

	@Column(name = "orgnlSeqNo", length = 255)
	public String getOrgnlSeqNo() {
		return orgnlSeqNo;
	}

	@Column(name = "bizTrackNo", length = 255)
	public String getBizTrackNo() {
		return bizTrackNo;
	}

	@Column(name = "isDelete")
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public void setTrakNo(String trakNo) {
		this.trakNo = trakNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public void setTriggerPoint(String triggerPoint) {
		this.triggerPoint = triggerPoint;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorDes(String errorDes) {
		this.errorDes = errorDes;
	}

	public void setBusinessDes(String businessDes) {
		this.businessDes = businessDes;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public void setSceneNameEns(String sceneNameEns) {
		this.sceneNameEns = sceneNameEns;
	}

	public void setOrgnlSeqNo(String orgnlSeqNo) {
		this.orgnlSeqNo = orgnlSeqNo;
	}

	public void setBizTrackNo(String bizTrackNo) {
		this.bizTrackNo = bizTrackNo;
	}

}
