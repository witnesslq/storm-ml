package cn.disruptive.web.service;

import java.util.List;

import cn.disruptive.core.common.page.Page;
import cn.disruptive.core.service.BaseService;
import cn.disruptive.web.po.AlarmGroup;
import cn.disruptive.web.po.ErrorCode;
import cn.disruptive.web.po.ErrorCodeTips;
import cn.disruptive.web.po.Scene;
import cn.disruptive.web.vo.AllInfoSumDynamicVO;
import cn.disruptive.web.vo.AllInfoVO;
import cn.disruptive.web.vo.BizTrackNoInfoVO;
import cn.disruptive.web.vo.InterfaceTimeOutVo;
import cn.disruptive.web.vo.InterfaceVarianceVO;
import cn.disruptive.web.vo.OneDayBusinessCountVo;
import cn.disruptive.web.vo.SceneErrVO;
import cn.disruptive.web.vo.SceneGroupCountVo;
import cn.disruptive.web.vo.SceneGroupVO;

/**
 * 
 * Description：报警模块
 * Data：		2015年9月12日
 */
public interface AlertService extends BaseService {

	/**
	 * 
	 * Description：获取所有场景错误信息
	 * Data：		2015年9月14日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @return
	 */
	public List<SceneGroupVO> getAllSceneErr(String appName, Integer errType, String startDate, String endDate, String orderField);
	
	/**
	 * Description:获取场景码统计信息
	 * Data:    2015年9月17日
	 * @param yyyyMMdd
	 * @return
	 */
	public Page getAllSceneCodeCounts(String yyyyMMdd,Integer curPage);
	
	
	public Page getAllOutTimes(String yyyyMMdd,Integer curPage,String appName,String respCode,String serverName,String interfaceName,String sceneCode,String bizTrackNo);
	

	/**
	 * 
	 * Description：分页获取特定场景码错误信息
	 * Data：		2015年9月14日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param sceneNameEns	场景码英文缩写
	 * @param curPage		当前页码
	 * @return
	 */
	public Page getSceneErrorCode(String appName, String sceneNameEns, String sceneCode, Integer errType, Integer curPage, String startDate, String endDate);

	/**
	 * 
	 * Description：通过告警组名称码获取告警组
	 * Data：		2015年9月14日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param sceneNameEns	告警组名称
	 * @return
	 */
	public AlarmGroup getAlarmGroup(String sceneNameEns);

	/**
	 * 
	 * Description：获取错误码信息表
	 * Data：		2015年9月15日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param errorCodeId	错误码信息表id
	 * @return
	 */
	public ErrorCode getOne(Integer errorCodeId);

	/**
	 * 
	 * Description：获取当天日志错误数
	 * Data：		2015年9月15日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @return
	 */
	public Integer errCount();

	/**
	 * 
	 * Description：获取当日日志处理数量
	 * Data：		2015年9月15日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @return
	 */
	public Integer logCount();

	/**
	 * 
	 * Description：获取场景错误信息
	 * Data：		2015年9月16日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param interfaceTimeOutId 场景错误信息id
	 * @return
	 */
	public SceneErrVO getSceneErrVO(Integer interfaceTimeOutId);

	/**
	 * 
	 * Description：通过场景组码获取场景列表
	 * Data：		2015年9月18日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param sceneNameEns
	 * @return
	 */
	public List<Scene> getSceneList(String sceneNameEns);

	/**
	 * Description:获取场景码 对比信息
	 * Date: 2015-09-18
	 * @param sceneCode 场景码
	 * @param serverName 服务器名称
	 * @param interfaceName 接口名称 
	 * @param appName 应用名称
	 * @param yyyyMMdd 时间
	 * @param curPage 当前页面
	 * @return
	 */
	public Page showOneSceneCode(String sceneCode, String serverName,
			String interfaceName,String appName, String yyyyMMdd, Integer curPage);

	/**
	 * Description:根据应用统计
	 * @param yyyyMMdd
	 * @param appName
	 * @return
	 */
	public Integer appCount(String yyyyMMdd, String appName);

	public List<OneDayBusinessCountVo> oneDayBusinessCount(String yyyyMMdd,String whereStr);

	/**
	 * 
	 * Description：获取错误信息处理意见
	 * Data：		2015年9月18日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param respCode	错误码
	 * @return
	 */
	public ErrorCodeTips getErrorCodeTips(String respCode);

	/**
	 * 
	 * Description：保存错误码加载错误解决建议
	 * Data：		2015年9月18日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param respCode	错误码
	 * @param errorTips	错误解决建议
	 */
	public void addErrTips(String errorCode, String respCode);

	/**
	 * 
	 * Description：通过业务追踪码分析场景情况
	 * Data：		2015年9月21日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param bizTrackNo	业务追踪号
	 * @return
	 */
	public List<BizTrackNoInfoVO> getAnalyseListByBizTrackNo(String bizTrackNo);

	/**
	 * 
	 * Description：忽略错误
	 * Data：		2015年9月22日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param errorId
	 */
	public void ignoreError(Integer errorId);

	/**
	 * 
	 * Description：失败业务耗时Top10分析
	 * Data：		2015年9月28日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param curPage
	 * @param appName
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Page timeOutTop10(Integer curPage, String appName, String startDate, String endDate);

	/**
	 * 
	 * Description：获取概览
	 * Data：		2015年10月12日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @return
	 */
	public AllInfoVO getAllInfoVO();

	/**
	 * 
	 * Description：动态加载历史数据
	 * Data：		2015年10月13日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @return
	 */
	public List<AllInfoSumDynamicVO> getAllInfoSumDynamicVO();
	/**
	 * Des: 统计当天的场景组量
	 * @param yyyyMMdd
	 * @return
	 * @throws Exception
	 */
	public List<SceneGroupCountVo> getSceneGroupCountVos(String yyyyMMdd) throws Exception;

	/**
	 * 
	 * Description：首页展示接口访问量Top10耗时统计
	 * Data：		2015年10月21日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @return
	 */
	public List<InterfaceVarianceVO> getInterfaceVarianceVO();

	public List<InterfaceTimeOutVo> showInfos(String yyyyMMdd, Integer id);

}
