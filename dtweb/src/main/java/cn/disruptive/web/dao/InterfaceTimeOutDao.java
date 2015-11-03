package cn.disruptive.web.dao;

import java.util.List;

import cn.disruptive.core.common.page.Page;
import cn.disruptive.core.dao.BaseDao;
import cn.disruptive.web.po.InterfaceTimeOut;
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
 * Description：接口时间统计
 * Data：		2015年9月12日
 */
public interface InterfaceTimeOutDao extends BaseDao<InterfaceTimeOut, Integer> {

	/**
	 * 
	 * Description：获取所有场景错误信息
	 * Data：		2015年9月14日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @return
	 */
	public List<SceneGroupVO> getAllScene(String appName, Integer errType, String startDate, String endDate, String orderField);
	
	/**
	 * Description:获取场景码统计信息
	 * Data:    2015年9月17日
	 * @param yyyyMMdd
	 * @return
	 */
	public Page getAllSceneCodeCounts(String yyyyMMdd,Integer curPage);

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
	 * Description：获取场景错误数量
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
	 * Description:获取单个场景码对比
	 * @param sceneCode 场景码
	 * @param serverName 服务名
	 * @param interfaceName 接口名称
	 * @param appName 应用名称
	 * @param yyyyMMdd 时间
	 * @param curPage 当前页面
	 * @return
	 */
	public Page getOneSceneCode(String sceneCode, String serverName,
			String interfaceName, String appName, String yyyyMMdd,
			Integer curPage);

	/**
	 * Description:获取appName统计量
	 * @param yyyyMMdd
	 * @param appName
	 * @return
	 */
	public Integer appCount(String yyyyMMdd, String appName);

	/**
	 * Description: 当天业务量统计
	 * @param yyyyMMdd
	 * @return
	 */
	public List<OneDayBusinessCountVo> oneDayBusinessCount(String yyyyMMdd,String whereStr);

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
	 * Description：动态加载当天历史数据
	 * Data：		2015年10月13日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @return
	 */
	public List<AllInfoSumDynamicVO> getAllInfoSumDynamicVO();
	
	
	/**
	 * Des: 根据业务场景组统计当天
	 * @param yyyyMMdd
	 * @return
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

	public Page getAllOutTimes(String yyyyMMdd, Integer curPage,String appName,String respCode,String serverName,String interfaceName,String sceneCode,String bizTrackNo);

	public List<InterfaceTimeOutVo> showInfos(String yyyyMMdd, Integer id);

}
