package cn.disruptive.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.disruptive.core.common.page.Page;
import cn.disruptive.core.service.impl.BaseServiceImpl;
import cn.disruptive.security.Security;
import cn.disruptive.web.dao.AlarmGroupDao;
import cn.disruptive.web.dao.ErrorCodeDao;
import cn.disruptive.web.dao.ErrorCodeTipsDao;
import cn.disruptive.web.dao.InterfaceTimeOutDao;
import cn.disruptive.web.dao.SceneDao;
import cn.disruptive.web.po.AlarmGroup;
import cn.disruptive.web.po.ErrorCode;
import cn.disruptive.web.po.ErrorCodeTips;
import cn.disruptive.web.po.Scene;
import cn.disruptive.web.service.AlertService;
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
 * Data：		2015年9月11日
 */
@Transactional
@Service("alertService")
public class AlertServiceImpl extends BaseServiceImpl implements AlertService {
	@Resource
	private ErrorCodeDao errorCodeDao;
	@Resource
	private AlarmGroupDao alarmGroupDao;
	@Resource
	private InterfaceTimeOutDao interfaceTimeOutDao;
	@Resource
	private SceneDao sceneDao;
	@Resource
	private ErrorCodeTipsDao errorCodeTipsDao;

	@Override
	@Security(describe = "获取所有场景错误信息")
	public List<SceneGroupVO> getAllSceneErr(String appName, Integer errType, String startDate, String endDate, String orderField) {
		logger.debug("获取所有场景错误信息,appName={}, errType={}", appName, errType);
		return interfaceTimeOutDao.getAllScene(appName, errType, startDate, endDate, orderField);
	}

	@Override
	public Page getSceneErrorCode(String appName, String sceneNameEns, String sceneCode, Integer errType, Integer curPage, String startDate, String endDate) {
		logger.debug("分页获取特定场景错误信息");
		return interfaceTimeOutDao.getSceneErrorCode(appName, sceneNameEns, sceneCode, errType, curPage, startDate, endDate);
	}

	@Override
	public AlarmGroup getAlarmGroup(String sceneNameEns) {
		logger.debug("通过告警组名码称获取告警组,sceneNameEns={}", sceneNameEns);
		return alarmGroupDao.findUniqueByProp("alarmGroupCode", sceneNameEns);
	}

	@Override
	public ErrorCode getOne(Integer errorCodeId) {
		logger.debug("获取错误码信息表,errorCodeId={}", errorCodeId);
		return errorCodeDao.get(errorCodeId);
	}

	@Override
	public Integer errCount() {
		logger.debug("获取场景错误数量");
		return interfaceTimeOutDao.errCount();
	}

	@Override
	public Integer logCount() {
		logger.debug("获取当日日志处理数量");
		return interfaceTimeOutDao.logCount();
	}

	@Override
	public SceneErrVO getSceneErrVO(Integer interfaceTimeOutId) {
		logger.debug("获取场景错误信息:interfaceTimeOutId={}", interfaceTimeOutId);
		return interfaceTimeOutDao.getSceneErrVO(interfaceTimeOutId);
	}

	@Override
	public Page getAllSceneCodeCounts(String yyyyMMdd,Integer curPage) {
		logger.debug("获取所有场景码统计信息");
		return interfaceTimeOutDao.getAllSceneCodeCounts(yyyyMMdd,curPage);
	}

	@Override
	public List<Scene> getSceneList(String sceneNameEns) {
		logger.debug("按场景组缩写码获取场景列表，sceneNameEns={}", sceneNameEns);
		return sceneDao.findByProp("sceneGroupCode", "TRAN_" + sceneNameEns);
	}

	@Override
	public Page showOneSceneCode(String sceneCode, String serverName,
			String interfaceName, String appName, String yyyyMMdd,
			Integer curPage) {
		logger.debug("获取单个场景码统计信息");
		
		return interfaceTimeOutDao.getOneSceneCode(sceneCode,serverName,interfaceName,appName,yyyyMMdd,curPage);
	}

	@Override
	public Integer appCount(String yyyyMMdd, String appName) {
		return interfaceTimeOutDao.appCount(yyyyMMdd,appName);
	}

	@Override
	public List<OneDayBusinessCountVo> oneDayBusinessCount(String yyyyMMdd,String whereStr) {
		return interfaceTimeOutDao.oneDayBusinessCount(yyyyMMdd,whereStr);
	}

	@Override
	public ErrorCodeTips getErrorCodeTips(String respCode) {
		logger.debug("获取错误信息处理意见,respCode={}", respCode);
		return errorCodeTipsDao.findUniqueByProp("respCode", respCode);
	}

	@Override
	public void addErrTips(String respCode, String errorTips) {
		logger.debug("保存错误码加载错误解决建议,respCode={},errorTips={}", respCode, errorTips);
		ErrorCodeTips errorCodeTips = errorCodeTipsDao.findUniqueByProp("respCode", respCode);
		if (errorCodeTips == null) {
			errorCodeTips = new ErrorCodeTips();
			errorCodeTips.setRespCode(respCode);
			errorCodeTips.setErrorTips(errorTips);
			errorCodeTips.setCreateTime(new Date());
			errorCodeTips.setUpdateTime(new Date());
			errorCodeTipsDao.save(errorCodeTips);
		} else {
			errorCodeTips.setErrorTips(errorTips);
			errorCodeTips.setUpdateTime(new Date());
			errorCodeTipsDao.saveOrUpdate(errorCodeTips);
		}
	}

	@Override
	public List<BizTrackNoInfoVO> getAnalyseListByBizTrackNo(String bizTrackNo) {
		logger.debug("通过业务追踪码分析场景情况：bizTrackNo={}", bizTrackNo);
		return interfaceTimeOutDao.getAnalyseListByBizTrackNo(bizTrackNo);
	}

	@Override
	public void ignoreError(Integer errorId) {
		logger.debug("忽略错误：errorId={}", errorId);
		interfaceTimeOutDao.ignoreError(errorId);
	}

	@Override
	public Page timeOutTop10(Integer curPage, String appName, String startDate, String endDate) {
		logger.debug("失败业务耗时Top10分析");
		return interfaceTimeOutDao.timeOutTop10(curPage, appName, startDate, endDate);
	}

	@Override
	public AllInfoVO getAllInfoVO() {
		logger.debug("获取概览");
		return interfaceTimeOutDao.getAllInfoVO();
	}

	@Override
	public List<AllInfoSumDynamicVO> getAllInfoSumDynamicVO() {
		logger.debug("动态加载，当天历史数据");
		return interfaceTimeOutDao.getAllInfoSumDynamicVO();
	}

	@Override
	public List<SceneGroupCountVo> getSceneGroupCountVos(String yyyyMMdd)
			throws Exception {
		return interfaceTimeOutDao.getSceneGroupCountVos(yyyyMMdd);
	}

	@Override
	public List<InterfaceVarianceVO> getInterfaceVarianceVO() {
		logger.debug("首页展示接口访问量Top10耗时统计");
		return interfaceTimeOutDao.getInterfaceVarianceVO();
	}

	@Override
	public Page getAllOutTimes(String yyyyMMdd, Integer curPage,String appName,String respCode,String serverName,String interfaceName,String sceneCode,String bizTrackNo) {
		// TODO Auto-generated method stub
		return interfaceTimeOutDao.getAllOutTimes(yyyyMMdd,curPage,appName,respCode,serverName,interfaceName,sceneCode,bizTrackNo);
	}

	@Override
	public List<InterfaceTimeOutVo> showInfos(String yyyyMMdd, Integer id) {
		return interfaceTimeOutDao.showInfos(yyyyMMdd,id);
	}

}
