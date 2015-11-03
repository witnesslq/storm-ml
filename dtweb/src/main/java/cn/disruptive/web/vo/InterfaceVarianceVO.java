package cn.disruptive.web.vo;

/**
 * 
 * Description：首页展示接口访问量Top10耗时统计
 * Data：		2015年10月21日
 */
public class InterfaceVarianceVO {
	private Integer callSum;		// 接口调用次数
	private Integer maxTimeout;		// 最大耗时
	private Integer minTimeout;		// 最小耗时
	private Integer variance;		// 波动因子
	private String interfaceName;	// 接口名称
	private String serverName;		// 服务名称

	public Integer getCallSum() {
		return callSum;
	}

	public void setCallSum(Integer callSum) {
		this.callSum = callSum;
	}

	public Integer getMaxTimeout() {
		return maxTimeout;
	}

	public void setMaxTimeout(Integer maxTimeout) {
		this.maxTimeout = maxTimeout;
	}

	public Integer getMinTimeout() {
		return minTimeout;
	}

	public void setMinTimeout(Integer minTimeout) {
		this.minTimeout = minTimeout;
	}

	public Integer getVariance() {
		return variance;
	}

	public void setVariance(Integer variance) {
		this.variance = variance;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

}
