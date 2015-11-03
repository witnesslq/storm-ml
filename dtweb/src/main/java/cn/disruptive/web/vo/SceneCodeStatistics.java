package cn.disruptive.web.vo;

/**
 * Description:场景码统计
 * Date:2015-09-17
 * @author Administrator
 *
 */
public class SceneCodeStatistics {

	private String interfaceName; //服务名称
	private String serverName;//主机名
	private Double tvar;//方差
	private Double timeOutMax;//最大耗时
	private Double timeOutMin;//最小耗时
	private Double timeOutAvg;//平均耗时
	private Integer count;//访问量
	private String appName;//应用名称
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
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
	public Double getTvar() {
		return tvar;
	}
	public void setTvar(Double tvar) {
		this.tvar = tvar;
	}
	public Double getTimeOutMax() {
		return timeOutMax;
	}
	public void setTimeOutMax(Double timeOutMax) {
		this.timeOutMax = timeOutMax;
	}
	public Double getTimeOutMin() {
		return timeOutMin;
	}
	public void setTimeOutMin(Double timeOutMin) {
		this.timeOutMin = timeOutMin;
	}
	public Double getTimeOutAvg() {
		return timeOutAvg;
	}
	public void setTimeOutAvg(Double timeOutAvg) {
		this.timeOutAvg = timeOutAvg;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
