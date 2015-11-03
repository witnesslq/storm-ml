package cn.disruptive.web.vo;

import java.util.Date;

/**
 * 
 * Description：交易总数动态加载
 * Data： 		2015年10月13日
 */
public class AllInfoSumDynamicVO {
	private Integer sumCount;	// 总交易量
	private Integer failCount;	// 失败量
	private Date time; 			// 时间

	public Integer getSumCount() {
		return sumCount;
	}

	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}


}
