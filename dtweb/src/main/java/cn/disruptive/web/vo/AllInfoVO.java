package cn.disruptive.web.vo;


/**
 * 
 * Description：概览 
 * Data： 		2015年10月12日
 */
public class AllInfoVO {
	private Integer allSumCount;	// 接口调用次数
	private Integer bizSumCount;	// 交易量
	private Integer timeout;		// 平均耗时
	private Integer allExceptionNum;// 接口调用失败量
	private Integer bizExceptionNum;// 交易失败量

	public Integer getAllSumCount() {
		return allSumCount;
	}

	public void setAllSumCount(Integer allSumCount) {
		this.allSumCount = allSumCount;
	}

	public Integer getBizSumCount() {
		return bizSumCount;
	}

	public void setBizSumCount(Integer bizSumCount) {
		this.bizSumCount = bizSumCount;
	}

	public Integer getAllExceptionNum() {
		return allExceptionNum;
	}

	public void setAllExceptionNum(Integer allExceptionNum) {
		this.allExceptionNum = allExceptionNum;
	}

	public Integer getBizExceptionNum() {
		return bizExceptionNum;
	}

	public void setBizExceptionNum(Integer bizExceptionNum) {
		this.bizExceptionNum = bizExceptionNum;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	
}
