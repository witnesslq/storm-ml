package cn.disruptive.web.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import cn.disruptive.common.util.DateUtils;
import cn.disruptive.common.util.ReadConfigation;
import cn.disruptive.core.common.page.Page;
import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.web.dao.InterfaceTimeOutDao;
import cn.disruptive.web.po.InterfaceTimeOut;
import cn.disruptive.web.vo.AllInfoSumDynamicVO;
import cn.disruptive.web.vo.AllInfoVO;
import cn.disruptive.web.vo.AllSceneCodeCountVo;
import cn.disruptive.web.vo.BizTrackNoInfoVO;
import cn.disruptive.web.vo.InterfaceTimeOutVo;
import cn.disruptive.web.vo.InterfaceVarianceVO;
import cn.disruptive.web.vo.OneDayBusinessCountVo;
import cn.disruptive.web.vo.SceneCodeStatistics;
import cn.disruptive.web.vo.SceneErrVO;
import cn.disruptive.web.vo.SceneGroupCountVo;
import cn.disruptive.web.vo.SceneGroupVO;
import cn.disruptive.web.vo.TimeOutTop10VO;

/**
 * 
 * Description：接口时间统计
 * Data：		2015年9月12日
 */
@SuppressWarnings("unchecked")
@Repository("interfaceTimeOutDao")
public class InterfaceTimeOutDaoImpl extends BaseDaoImpl<InterfaceTimeOut, Integer> implements InterfaceTimeOutDao {

	@Override
	public List<SceneGroupVO> getAllScene(String appName, Integer errType, String startDate, String endDate, String orderField) {
		logger.debug("按错误数从大到小获取所有场景错误信息,appName={}, errType={}, startDate={}, endDate={} ,orderField={}", new Object[] { appName, errType, startDate, endDate, orderField });
		String strDate = DateUtils.formatDate(DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"), "yyyyMMdd");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  count(t1.trackNo) sumNum, ");
		sql.append("  SUM(CASE WHEN t1.errNum = 0 THEN 1 ELSE 0 END) AS rightNum, ");
		sql.append("  SUM(CASE WHEN t1.errNum > 0 THEN 1 ELSE 0 END) AS errNum, ");
		sql.append("  t1.sceneGroupName sceneGroupName, ");
		sql.append("  t1.sceneNameEns sceneNameEns ");
		sql.append("FROM ");
		sql.append("(SELECT ");
		sql.append("  a.trackNo trackNo,");
		sql.append("  SUM(CASE WHEN a.isException = 1 THEN 1 ELSE 0 END) AS errNum,");
		sql.append("  b.sceneGroup sceneGroupName,");
		sql.append("  a.sceneNameEns sceneNameEns ");
		sql.append("FROM ");
		sql.append("  t_interface_timeout_");
		sql.append(strDate);
		sql.append("  as a ");
		sql.append("LEFT JOIN t_scene b ON a.sceneCode = b.sceneCode ");
		sql.append("WHERE ");
		sql.append("  a.appName IN ('EBGB', 'EBCP', 'EBMP')");
		sql.append("AND ");
		sql.append("  a.recordTime ");
		sql.append("BETWEEN ");
		sql.append("  :startDate ");
		sql.append("AND ");
		sql.append("  :endDate ");
		if (errType != null) {
			sql.append("AND ");
			sql.append("  a.isException=:errType ");
		}
		if (StringUtils.isNotBlank(appName)) {
			sql.append("AND ");
			sql.append("  a.appName=:appName ");
		}
		sql.append("GROUP BY trackNo ");
		sql.append(") t1 ");
		sql.append("GROUP BY sceneNameEns ");
		sql.append("ORDER BY " + orderField + " desc");
		Query queryData = getSession().createSQLQuery(sql.toString())
				.addScalar("sumNum", StandardBasicTypes.INTEGER)
				.addScalar("rightNum", StandardBasicTypes.INTEGER)
				.addScalar("errNum", StandardBasicTypes.INTEGER)
				.addScalar("sceneGroupName", StandardBasicTypes.STRING)
				.addScalar("sceneNameEns", StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(SceneGroupVO.class));
		queryData.setTimestamp("startDate", DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		queryData.setTimestamp("endDate", DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
		// queryData.setString("orderField", orderField);
		if (errType != null) {
			queryData.setInteger("errType", errType);
		}
		if (StringUtils.isNotBlank(appName)) {
			queryData.setString("appName", appName);
		}
		return queryData.list();
	}

	@Override
	public Page getSceneErrorCode(String appName, String sceneNameEns, String sceneCode, Integer errType, Integer curPage, String startDate, String endDate) {
		logger.debug("分页获取特定场景错误信息,条件：sceneNameEns={}, sceneCode={}, startDate={}, endDate={}", new Object[] { sceneNameEns, sceneCode, startDate, endDate });
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		StringBuilder sqlCount = new StringBuilder();
		sqlCount.append("SELECT distinct count(a1.id) count FROM");
		sqlCount.append("(SELECT distinct  a.id AS id FROM ");
		sqlCount.append("t_interface_timeout_");
		sqlCount.append(strDate);
		sqlCount.append(" as a ");
		sqlCount.append(" LEFT JOIN t_scene b ON a.sceneCode = b.sceneCode ");
		sqlCount.append(" LEFT JOIN t_error_code c ON a.sceneCode = c.trakNo ");
		sqlCount.append("WHERE a.sceneNameEns=:sceneNameEns ");
		sqlCount.append("AND ");
		sqlCount.append("  a.recordTime ");
		sqlCount.append("BETWEEN ");
		sqlCount.append("  :startDate ");
		sqlCount.append("AND ");
		sqlCount.append("  :endDate ");
		sqlCount.append("AND a.isException=:errType ");
		if (StringUtils.isNotBlank(appName)) {
			sqlCount.append("AND a.appName=:appName ");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct ");
		sql.append("  a.id AS id,");
		sql.append("  a.appName AS appName,");
		sql.append("  a.serverName AS hostName,");
		sql.append("  a.sceneCode AS sceneCode,");
		sql.append("  a.interfaceName AS interfaceName,");
		sql.append("  a.timeOut AS timeOut,");
		sql.append("  a.trackNo AS trackNo,");
		sql.append("  a.recordTime AS recordTime,");
		sql.append("  a.respCode AS respCode,");
		sql.append("  a.resRowKey AS resRowKey,");

		sql.append("  b.sceneName AS sceneName,");
		sql.append("  b.sceneNameEns AS sceneNameEns,");
		sql.append("  b.sceneDes AS sceneDes,");

		sql.append("  c.tradingName AS tradingName,");
		sql.append("  c.serverName AS serverName,");
		sql.append("  c.trakNo AS trakNo,");
		sql.append("  c.channelNo AS channelNo,");
		sql.append("  c.triggerPoint AS triggerPoint,");
		sql.append("  c.errorCode AS errorCode,");
		sql.append("  c.errorDes AS errorDes,");
		sql.append("  c.businessDes AS businessDes ");

		sql.append("FROM ");
		sql.append("t_interface_timeout_");
		sql.append(strDate);
		sql.append(" as a ");
		sql.append(" LEFT JOIN t_scene b ON a.sceneCode = b.sceneCode ");
		sql.append(" LEFT JOIN t_error_code c ON a.sceneCode = c.trakNo ");
		sql.append("WHERE a.sceneNameEns=:sceneNameEns ");
		sql.append("AND ");
		sql.append("  a.recordTime ");
		sql.append("BETWEEN ");
		sql.append("  :startDate ");
		sql.append("AND ");
		sql.append("  :endDate ");
		sql.append("AND a.isException=:errType ");
		if (StringUtils.isNotBlank(appName)) {
			sql.append("AND a.appName=:appName ");
		}

		if (!StringUtils.isBlank(sceneCode)) {
			sql.append(" AND a.sceneCode=:sceneCode ");
			sqlCount.append(" AND a.sceneCode=:sceneCode ");
		}

		sqlCount.append("GROUP BY id ) a1");
		sql.append("GROUP BY id ORDER BY recordTime DESC ");
		Query queryCount = getSession().createSQLQuery(sqlCount.toString()).addScalar("count", StandardBasicTypes.INTEGER);

		Query queryData = getSession().createSQLQuery(sql.toString())
				.addScalar("id", StandardBasicTypes.INTEGER)
				.addScalar("appName", StandardBasicTypes.STRING)
				.addScalar("serverName", StandardBasicTypes.STRING)
				.addScalar("sceneCode", StandardBasicTypes.STRING)
				.addScalar("interfaceName", StandardBasicTypes.STRING)
				.addScalar("timeOut", StandardBasicTypes.INTEGER)
				.addScalar("trackNo", StandardBasicTypes.STRING)
				.addScalar("recordTime", StandardBasicTypes.TIMESTAMP)
				.addScalar("respCode", StandardBasicTypes.STRING)
.addScalar("resRowKey", StandardBasicTypes.STRING)
				
				.addScalar("sceneName", StandardBasicTypes.STRING)
				.addScalar("sceneNameEns", StandardBasicTypes.STRING)
				.addScalar("sceneDes", StandardBasicTypes.STRING)
				
				.addScalar("tradingName", StandardBasicTypes.STRING)
				.addScalar("serverName", StandardBasicTypes.STRING)
				.addScalar("trakNo", StandardBasicTypes.STRING)
				.addScalar("channelNo", StandardBasicTypes.STRING)
				.addScalar("triggerPoint", StandardBasicTypes.STRING)
				.addScalar("errorCode", StandardBasicTypes.STRING)
				.addScalar("errorDes", StandardBasicTypes.STRING)
				.addScalar("businessDes", StandardBasicTypes.STRING)
				
				.setResultTransformer(Transformers.aliasToBean(SceneErrVO.class));

		queryCount.setInteger("errType", errType);
		queryData.setInteger("errType", errType);
		queryCount.setString("sceneNameEns", sceneNameEns);
		queryData.setString("sceneNameEns", sceneNameEns);

		queryCount.setTimestamp("startDate", DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		queryCount.setTimestamp("endDate", DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
		queryData.setTimestamp("startDate", DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		queryData.setTimestamp("endDate", DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));

		if (StringUtils.isNotBlank(appName)) {
			queryCount.setString("appName", appName);
			queryData.setString("appName", appName);
		}

		if (!StringUtils.isEmpty(sceneCode)) {
			queryCount.setString("sceneCode", sceneCode);
			queryData.setString("sceneCode", sceneCode);
		}
		Integer totalCount = (Integer) queryCount.uniqueResult();
		Page page = new Page(totalCount.intValue(), curPage.intValue(), 10);

		queryData.setFirstResult(page.getPageStartRow() - 1);
		queryData.setMaxResults(page.getPageRecorders());
		List list = queryData.list();
		page.setDataList(list);
		return page;
	}
	
	@Override
	public Integer errCount() {
		logger.debug("获取场景错误数量");
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		StringBuilder sqlCount = new StringBuilder();
		sqlCount.append("SELECT ");
		sqlCount.append("  count(t1.trackNo) count ");
		sqlCount.append("FROM ");
		sqlCount.append("(SELECT ");
		sqlCount.append("  a.trackNo trackNo ");
		sqlCount.append("FROM ");
		sqlCount.append("t_interface_timeout_");
		sqlCount.append(strDate);
		sqlCount.append(" AS a ");
		sqlCount.append("WHERE ");
		sqlCount.append(" a.isException=1 ");
		sqlCount.append(" AND appName IN ('EBGB', 'EBCP', 'EBMP') ");
		sqlCount.append("GROUP BY ");
		sqlCount.append(" trackNo ");
		sqlCount.append(") t1");
		Query queryCount = getSession().createSQLQuery(sqlCount.toString()).addScalar("count", StandardBasicTypes.INTEGER);
		Integer totalCount = (Integer) queryCount.uniqueResult();
		logger.debug("日期：{} 错误数：{}", strDate, totalCount);
		if (totalCount == null) {
			totalCount = 0;
		}
		return totalCount;
	}

	@Override
	public Integer logCount() {
		logger.debug("获取当日日志处理数量");
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		StringBuilder sqlCount = new StringBuilder();
		sqlCount.append("select count(a.id) count from ");
		sqlCount.append("t_interface_timeout_");
		sqlCount.append(strDate);
		sqlCount.append(" as a ");

		Query queryCount = getSession().createSQLQuery(sqlCount.toString()).addScalar("count", StandardBasicTypes.INTEGER);
		Integer totalCount = (Integer) queryCount.uniqueResult();
		logger.debug("日期：{} 日志处理量：{}", strDate, totalCount);
		if (totalCount == null) {
			totalCount = 0;
		}
		return totalCount;
	}

	@Override
	public SceneErrVO getSceneErrVO(Integer interfaceTimeOutId) {
		logger.debug("获取场景错误信息,interfaceTimeOutId={}", interfaceTimeOutId);
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct ");
		sql.append("  a.id AS id,");
		sql.append("  a.appName AS appName,");
		sql.append("  a.serverName AS hostName,");
		sql.append("  a.sceneCode AS sceneCode,");
		sql.append("  a.interfaceName AS interfaceName,");
		sql.append("  a.timeOut AS timeOut,");
		sql.append("  a.trackNo AS trackNo,");
		sql.append("  a.recordTime AS recordTime,");
		sql.append("  a.respCode AS respCode,");
		sql.append("  a.resRowKey AS resRowKey,");
		sql.append("  a.reqRowKey AS reqRowKey,");
		sql.append("  a.bizTrackNo AS bizTrackNo,");

		sql.append("  b.sceneName AS sceneName,");
		sql.append("  b.sceneNameEns AS sceneNameEns,");
		sql.append("  b.sceneDes AS sceneDes,");

		sql.append("  c.tradingName AS tradingName,");
		sql.append("  c.serverName AS serverName,");
		sql.append("  c.trakNo AS trakNo,");
		sql.append("  c.channelNo AS channelNo,");
		sql.append("  c.triggerPoint AS triggerPoint,");
		sql.append("  c.errorCode AS errorCode,");
		sql.append("  c.errorDes AS errorDes,");
		sql.append("  c.businessDes AS businessDes ");

		sql.append("FROM ");
		sql.append("t_interface_timeout_");
		sql.append(strDate);
		sql.append(" as a ");
		sql.append(" LEFT JOIN t_scene b ON a.sceneCode = b.sceneCode ");
		sql.append(" LEFT JOIN t_error_code c ON a.sceneCode = c.trakNo ");
		sql.append("WHERE a.id=:id ");
		sql.append("AND a.isException=1 ");
		sql.append("GROUP BY id ORDER BY recordTime DESC ");
		Query queryData = getSession().createSQLQuery(sql.toString())
				.addScalar("id", StandardBasicTypes.INTEGER)
				.addScalar("appName", StandardBasicTypes.STRING)
				.addScalar("hostName", StandardBasicTypes.STRING)
				.addScalar("sceneCode", StandardBasicTypes.STRING)
				.addScalar("interfaceName", StandardBasicTypes.STRING)
				.addScalar("timeOut", StandardBasicTypes.INTEGER)
				.addScalar("trackNo", StandardBasicTypes.STRING)
				.addScalar("recordTime", StandardBasicTypes.TIMESTAMP)
				.addScalar("respCode", StandardBasicTypes.STRING)
				.addScalar("resRowKey",StandardBasicTypes.STRING)
				.addScalar("reqRowKey",StandardBasicTypes.STRING)
				.addScalar("bizTrackNo",StandardBasicTypes.STRING)
				
				.addScalar("sceneName", StandardBasicTypes.STRING)
				.addScalar("sceneNameEns", StandardBasicTypes.STRING)
				.addScalar("sceneDes", StandardBasicTypes.STRING)
				
				.addScalar("tradingName", StandardBasicTypes.STRING)
				.addScalar("serverName", StandardBasicTypes.STRING)
				.addScalar("trakNo", StandardBasicTypes.STRING)
				.addScalar("channelNo", StandardBasicTypes.STRING)
				.addScalar("triggerPoint", StandardBasicTypes.STRING)
				.addScalar("errorCode", StandardBasicTypes.STRING)
				.addScalar("errorDes", StandardBasicTypes.STRING)
				.addScalar("businessDes", StandardBasicTypes.STRING)
				
				.setResultTransformer(Transformers.aliasToBean(SceneErrVO.class));

		queryData.setInteger("id", interfaceTimeOutId);
		return (SceneErrVO) queryData.list().get(0);
	}

	public Page getAllSceneCodeCounts(String yyyyMMdd,Integer curPage){
		logger.debug("获取所有场景码的总量,成功,失败");
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		StringBuilder sql = new StringBuilder();
		String tabelName="t_interface_timeout_"+strDate;
		sql.append("select *,t_scene.sceneName from (select  count(sceneCode) as allCount,sceneCode from "+tabelName+" group by sceneCode) as a LEFT JOIN ");
		sql.append("(select count(sceneCode) as successCount,sceneCode from "+tabelName+" where isException=0 group by sceneCode) as b on  a.sceneCode=b.sceneCode LEFT JOIN ");
		sql.append("(select count(sceneCode) as errorCount,sceneCode from "+tabelName+" where isException=1 group by sceneCode) as c on a.sceneCode=c.sceneCode"
				+ " LEFT JOIN t_scene ON t_scene.sceneCode=a.sceneCode order by allCount desc,successCount desc,errorCount desc ");

		StringBuilder sqlCount=new StringBuilder();
		sqlCount.append("select count(1) as count from (select  count(sceneCode) as allCount,sceneCode from "+tabelName+" group by sceneCode) as a LEFT JOIN ");
		sqlCount.append("(select count(sceneCode) as successCount,sceneCode from "+tabelName+" where isException=0 group by sceneCode) as b on  a.sceneCode=b.sceneCode LEFT JOIN ");
		sqlCount.append("(select count(sceneCode) as errorCount,sceneCode from "+tabelName+" where isException=1 group by sceneCode) as c on a.sceneCode=c.sceneCode order by allCount desc,successCount desc,errorCount desc ");

		Query queryCount = getSession().createSQLQuery(sqlCount.toString()).addScalar("count", StandardBasicTypes.INTEGER);
		
		Query queryData = getSession().createSQLQuery(sql.toString())
				.addScalar("sceneCode", StandardBasicTypes.STRING)
				.addScalar("successCount", StandardBasicTypes.INTEGER)
				.addScalar("errorCount", StandardBasicTypes.INTEGER)
				.addScalar("allCount", StandardBasicTypes.INTEGER)
				.addScalar("sceneName",StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(AllSceneCodeCountVo.class));
	//	queryData.setInteger("errType", errType);
		// queryData.setTimestamp("endTime", DateUtils.parseDate(date + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		Integer totalCount = (Integer) queryCount.uniqueResult();
		Page page = new Page(totalCount.intValue(), curPage.intValue(), 10);

		queryData.setFirstResult(page.getPageStartRow() - 1);
		queryData.setMaxResults(page.getPageRecorders());
		List list = queryData.list();
		page.setDataList(list);
		return page;
	}

	@Override
	public Page getOneSceneCode(String sceneCode, String serverName,
			String interfaceName, String appName, String yyyyMMdd,
			Integer curPage) {
		logger.debug("获取所有场景码的总量,成功,失败");
		String date = DateUtils.formatDate(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		StringBuilder sqlWhere=new StringBuilder("");
		if(serverName!=null){
			sqlWhere.append(" and serverName=:serverName ");
		}
		if(appName!=null){
            sqlWhere.append(" and appName=:appName ");
		}
		if(appName!=null){
			sqlWhere.append(" and interfaceName:interfaceName ");
		}
		
		StringBuilder sql = new StringBuilder();
		String tabelName="t_interface_timeout_"+strDate;
		sql.append("select VARIANCE(timeout) as tvar,interfaceName,appName,serverName,max(timeout) as timeOutMax,min(timeout) as timeOutMin,avg(timeout) as timeOutAvg,count(timeout) as count from ");
		sql.append(tabelName);
		sql.append(" where sceneCode=:sceneCode ");
		sql.append(sqlWhere);
		sql.append( "group by interfaceName,serverName,appName ");
		
		StringBuilder sqlCount=new StringBuilder();
		sqlCount.append("select count(1) as count from ( select VARIANCE(timeout) as tvar,interfaceName,appName,serverName,max(timeout) as timeOutMax,min(timeout) as timeOutMin,avg(timeout) as timeOutAvg,count(timeout) as count from ");
		sqlCount.append(tabelName);
		sqlCount.append(" where sceneCode=:sceneCode ");
		sqlCount.append(sqlWhere);
		sqlCount.append( "group by interfaceName,serverName,appName ) as t");
		
		Query queryCount = getSession().createSQLQuery(sqlCount.toString()).addScalar("count", StandardBasicTypes.INTEGER);
		Query queryData = getSession().createSQLQuery(sql.toString())
				.addScalar("tvar", StandardBasicTypes.DOUBLE)
				.addScalar("interfaceName", StandardBasicTypes.STRING)
				.addScalar("appName", StandardBasicTypes.STRING)
				.addScalar("serverName", StandardBasicTypes.STRING)
				.addScalar("timeOutMax",StandardBasicTypes.DOUBLE)
				.addScalar("timeOutMin",StandardBasicTypes.DOUBLE)
				.addScalar("timeOutAvg",StandardBasicTypes.DOUBLE)
				.addScalar("count",StandardBasicTypes.INTEGER)
				.setResultTransformer(Transformers.aliasToBean(SceneCodeStatistics.class));
		queryCount.setString("sceneCode", sceneCode);
		queryData.setString("sceneCode", sceneCode);
		if(serverName!=null){
			queryCount.setString("serverName", serverName);
			queryData.setString("serverName", serverName);
		}
		if(appName!=null){
            queryCount.setString("appName",appName);
            queryData.setString("appName",appName);
		}
		if(appName!=null){
			queryCount.setString("interfaceName",interfaceName);
			queryData.setString("interfaceName",interfaceName);
		}
		Integer totalCount = (Integer) queryCount.uniqueResult();
		Page page = new Page(totalCount.intValue(), curPage.intValue(), 10);
		queryData.setFirstResult(page.getPageStartRow() - 1);
		queryData.setMaxResults(page.getPageRecorders());
		List list = queryData.list();
		page.setDataList(list);
		return page;
	}

	@Override
	public Integer appCount(String yyyyMMdd, String appName) {
		StringBuilder sql=new StringBuilder();
		String date = DateUtils.formatDate(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		String talelName="t_interface_timeout_"+strDate;
		sql.append("select count(1) as count from "+talelName+" where (appName=:appName or appName=:appName2)  group By appName ");
		Query queryCount = getSession().createSQLQuery(sql.toString()).addScalar("count", StandardBasicTypes.INTEGER);
		 queryCount.setString("appName",appName.toLowerCase());
		 queryCount.setString("appName2",appName.toUpperCase());
		// queryCount.setString("recordTime",yyyyMMdd);
		return (Integer) queryCount.uniqueResult();
		//

	}

	@Override
	public List<OneDayBusinessCountVo> oneDayBusinessCount(String yyyyMMdd,String whereStr) {
		
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		String tableName="t_interface_timeout_"+strDate;
		StringBuilder sql=new StringBuilder("select (case when count(1) is null then 0 else count(1) end) as count,floor(date_format(recordTime,'%H')/1) as dtime from "+tableName+" "+whereStr+"  group By dtime");
		Query query=getSession().createSQLQuery(sql.toString())
				.addScalar("count",StandardBasicTypes.INTEGER)
				.addScalar("dtime",StandardBasicTypes.INTEGER)
				.setResultTransformer(Transformers.aliasToBean(OneDayBusinessCountVo.class));
		return query.list();
	}

	@Override
	public List<BizTrackNoInfoVO> getAnalyseListByBizTrackNo(String bizTrackNo) {
		logger.debug("通过业务追踪码分析场景情况：bizTrackNo={}", bizTrackNo);
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct ");
		sql.append("  a.id AS id,");
		sql.append("  a.appName AS appName,");
		sql.append("  a.serverName AS hostName,");
		sql.append("  a.sceneCode AS sceneCode,");
		sql.append("  a.interfaceName AS interfaceName,");
		sql.append("  a.timeOut AS timeOut,");
		sql.append("  a.trackNo AS trackNo,");
		sql.append("  a.recordTime AS recordTime,");
		sql.append("  a.respCode AS respCode,");
		sql.append("  a.resRowKey AS resRowKey,");
		sql.append("  a.reqRowKey AS reqRowKey,");
		sql.append("  a.bizTrackNo AS bizTrackNo,");

		sql.append("  b.sceneName AS sceneName,");
		sql.append("  b.sceneNameEns AS sceneNameEns,");
		sql.append("  b.sceneDes AS sceneDes,");

		sql.append("  c.tradingName AS tradingName,");
		sql.append("  c.serverName AS serverName,");
		sql.append("  c.trakNo AS trakNo,");
		sql.append("  c.channelNo AS channelNo,");
		sql.append("  c.triggerPoint AS triggerPoint,");
		sql.append("  c.errorCode AS errorCode,");
		sql.append("  c.errorDes AS errorDes,");
		sql.append("  c.businessDes AS businessDes ");

		sql.append("FROM ");
		sql.append("t_interface_timeout_");
		sql.append(strDate);
		sql.append(" as a ");
		sql.append(" LEFT JOIN t_scene b ON a.sceneCode = b.sceneCode ");
		sql.append(" LEFT JOIN t_error_code c ON a.sceneCode = c.trakNo ");
		sql.append("WHERE a.bizTrackNo=:bizTrackNo ");
		sql.append("GROUP BY trackNo ORDER BY recordTime ASC ");
		Query queryData = getSession().createSQLQuery(sql.toString())
				.addScalar("id", StandardBasicTypes.INTEGER)
				.addScalar("appName", StandardBasicTypes.STRING)
				.addScalar("hostName", StandardBasicTypes.STRING)
				.addScalar("sceneCode", StandardBasicTypes.STRING)
				.addScalar("interfaceName", StandardBasicTypes.STRING)
				.addScalar("timeOut", StandardBasicTypes.INTEGER)
				.addScalar("trackNo", StandardBasicTypes.STRING)
				.addScalar("recordTime", StandardBasicTypes.TIMESTAMP)
				.addScalar("respCode", StandardBasicTypes.STRING)
				.addScalar("resRowKey",StandardBasicTypes.STRING)
				.addScalar("reqRowKey",StandardBasicTypes.STRING)
				.addScalar("bizTrackNo",StandardBasicTypes.STRING)
				
				.addScalar("sceneName", StandardBasicTypes.STRING)
				.addScalar("sceneNameEns", StandardBasicTypes.STRING)
				.addScalar("sceneDes", StandardBasicTypes.STRING)
				
				.addScalar("tradingName", StandardBasicTypes.STRING)
				.addScalar("serverName", StandardBasicTypes.STRING)
				.addScalar("trakNo", StandardBasicTypes.STRING)
				.addScalar("channelNo", StandardBasicTypes.STRING)
				.addScalar("triggerPoint", StandardBasicTypes.STRING)
				.addScalar("errorCode", StandardBasicTypes.STRING)
				.addScalar("errorDes", StandardBasicTypes.STRING)
				.addScalar("businessDes", StandardBasicTypes.STRING)
				
				.setResultTransformer(Transformers.aliasToBean(BizTrackNoInfoVO.class));

		queryData.setString("bizTrackNo", bizTrackNo);
		return queryData.list();
	}

	@Override
	public void ignoreError(Integer errorId) {
		logger.debug("忽略错误：errorId={}", errorId);
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append("  t_interface_timeout_");
		sql.append(strDate);
		sql.append("  as a ");
		sql.append("SET ");
		sql.append("  a.isException=0 ");
		sql.append("WHERE ");
		sql.append("  a.id=? ");
		int result = getSession().createSQLQuery(sql.toString()).setInteger(0, errorId).executeUpdate();
		logger.debug("更新结果条数:{}", result);
	}

	@Override
	public Page timeOutTop10(Integer curPage, String appName, String startDate, String endDate) {
		logger.debug("分页获取前10条超时信息,条件：curPage={}, appName={}, startDate={}, endDate={}", new Object[] { curPage, appName, startDate, endDate });
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		StringBuilder sqlCount = new StringBuilder();
		sqlCount.append("SELECT distinct count(a1.id) count FROM");
		sqlCount.append("(SELECT distinct  a.id AS id FROM ");
		sqlCount.append("t_interface_timeout_");
		sqlCount.append(strDate);
		sqlCount.append(" as a ");
		sqlCount.append(" LEFT JOIN t_scene b ON a.sceneCode = b.sceneCode ");
		sqlCount.append("WHERE a.isException=1 ");
		sqlCount.append("AND ");
		sqlCount.append("  a.recordTime ");
		sqlCount.append("BETWEEN ");
		sqlCount.append("  :startDate ");
		sqlCount.append("AND ");
		sqlCount.append("  :endDate ");
		if (StringUtils.isNotBlank(appName)) {
			sqlCount.append("AND a.appName=:appName ");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  a.id id,");
		sql.append("  a.sceneCode sceneCode,");
		sql.append("  a.isException isException,");
		sql.append("  a.bizTrackNo bizTrackNo,");
		sql.append("  SUM(a.timeout) timeout,");
		sql.append("  b.sceneName AS sceneName,");
		sql.append("  b.sceneNameEns AS sceneNameEns,");
		sql.append("  b.sceneDes AS sceneDes ");

		sql.append("FROM ");
		sql.append("t_interface_timeout_");
		sql.append(strDate);
		sql.append(" as a ");
		sql.append(" LEFT JOIN t_scene b ON a.sceneCode = b.sceneCode ");
		sql.append("WHERE a.isException=1 ");
		sql.append("AND ");
		sql.append("  a.recordTime ");
		sql.append("BETWEEN ");
		sql.append("  :startDate ");
		sql.append("AND ");
		sql.append("  :endDate ");
		if (StringUtils.isNotBlank(appName)) {
			sql.append("AND a.appName=:appName ");
		}

		sqlCount.append("GROUP BY bizTrackNo) a1");
		sql.append("GROUP BY bizTrackNo ORDER BY timeout DESC ");
		Query queryCount = getSession().createSQLQuery(sqlCount.toString()).addScalar("count", StandardBasicTypes.INTEGER);

		Query queryData = getSession().createSQLQuery(sql.toString())
				.addScalar("id", StandardBasicTypes.INTEGER)
				.addScalar("sceneCode", StandardBasicTypes.STRING)
				.addScalar("bizTrackNo", StandardBasicTypes.STRING)
				.addScalar("timeOut", StandardBasicTypes.INTEGER)
				.addScalar("sceneName", StandardBasicTypes.STRING)
				.addScalar("sceneNameEns", StandardBasicTypes.STRING)
				.addScalar("sceneDes", StandardBasicTypes.STRING)
				
				.setResultTransformer(Transformers.aliasToBean(TimeOutTop10VO.class));

		queryCount.setTimestamp("startDate", DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		queryCount.setTimestamp("endDate", DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
		queryData.setTimestamp("startDate", DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		queryData.setTimestamp("endDate", DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));

		if (StringUtils.isNotBlank(appName)) {
			queryCount.setString("appName", appName);
			queryData.setString("appName", appName);
		}

		Integer totalCount = (Integer) queryCount.uniqueResult();
		Page page = new Page(totalCount.intValue(), curPage.intValue(), 10);

		queryData.setFirstResult(page.getPageStartRow() - 1);
		queryData.setMaxResults(page.getPageRecorders());
		List list = queryData.list();
		page.setDataList(list);
		return page;
	}

	@Override
	public AllInfoVO getAllInfoVO() {
		logger.debug("获取概览");
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  SUM(t1.id) allSumCount,");
		sql.append("  AVG(t1.timeout) timeout,");
		sql.append("  SUM(CASE WHEN t1.exceptionNum = 0 THEN 0 ELSE 1 END) AS allExceptionNum ");
		sql.append("FROM ");
		sql.append("(SELECT");
		sql.append("  COUNT(1) id,");
		sql.append("  a.trackNo trackNo,");
		sql.append("  sum(a.isException) exceptionNum,");
		sql.append("  a.timeout timeout ");
		sql.append("FROM ");
		sql.append("t_interface_timeout_");
		sql.append(strDate);
		sql.append(" as a ");
		sql.append("WHERE ");
		sql.append("  a.appName IN ('EBGB', 'EBCP', 'EBMP') ");
		sql.append("GROUP BY ");
		sql.append("  trackNo ");
		sql.append(") t1");
		
		Query queryData = getSession().createSQLQuery(sql.toString())
				.addScalar("allSumCount", StandardBasicTypes.INTEGER)
				.addScalar("timeout", StandardBasicTypes.INTEGER)
				.addScalar("allExceptionNum", StandardBasicTypes.INTEGER)
				
				.setResultTransformer(Transformers.aliasToBean(AllInfoVO.class));

		return (AllInfoVO) queryData.list().get(0);
	}

	@Override
	public List<AllInfoSumDynamicVO> getAllInfoSumDynamicVO() {
		logger.debug("动态加载当天历史数据");
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		// TODO
		if (Integer.parseInt(ReadConfigation.getConfigItem("isTesting")) == 1) {
			strDate = "20151009";
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  SUM(t1.sumCount) sumCount,");
		sql.append("  SUM(CASE WHEN t1.isException = 0 THEN 0 ELSE 1 END) AS failCount,");
		sql.append("  t1.time time ");
		sql.append("FROM ");
		sql.append("(SELECT ");
		sql.append("  count(DISTINCT a.trackNo) sumCount,");
		sql.append("  SUM(a.isException) isException,");
		sql.append("  DATE_FORMAT(a.recordTime,'%Y-%m-%d %H:%i:00') time ");
		sql.append("FROM ");
		sql.append("t_interface_timeout_");
		sql.append(strDate);
		sql.append(" as a ");
		sql.append("WHERE ");
		sql.append("  a.appName IN ('EBGB', 'EBCP', 'EBMP') ");
		sql.append("GROUP BY ");
		sql.append("  a.trackNo ");
		sql.append(") t1 ");
		sql.append("GROUP BY time");

		Query queryData = getSession().createSQLQuery(sql.toString())
				.addScalar("sumCount", StandardBasicTypes.INTEGER)
				.addScalar("failCount", StandardBasicTypes.INTEGER)
				.addScalar("time", StandardBasicTypes.TIMESTAMP)

		.setResultTransformer(Transformers.aliasToBean(AllInfoSumDynamicVO.class));

		return queryData.list();
	}

	@Override
	public List<SceneGroupCountVo> getSceneGroupCountVos(String yyyyMMdd)
			throws Exception {
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select sceneGroup,sceneGroupCode,sum(tt.succcount) as count,sum(tt.succedcount) as succ,sum(tt.errorcount) as err from ");
        sql.append("(");
		sql.append("select sceneName,t_scene.sceneCode,t_scene.sceneGroup,t_scene.sceneGroupCode,t.id,(case when t.id is null then 0 else 1 end) as succcount,(case when t.respCode = '0000000000' then 1 else 0 end ) as succedcount,(case when t.respCode != '0000000000' then 1 else 0 end) as errorcount from t_scene left join ");
		sql.append("(select id,sceneCode,respCode from t_interface_timeout_"+strDate+"  where (appName ='ebmp' or appName ='ebgb' or appName = 'ebcp') ) as t ");
		sql.append("on t.sceneCode=t_scene.sceneCode");
		sql.append(") as tt group by tt.sceneGroupCode order by count desc ");
		
		Query queryData=getSession().createSQLQuery(sql.toString())
				.addScalar("sceneGroup",StandardBasicTypes.STRING)
				.addScalar("sceneGroupCode",StandardBasicTypes.STRING)
				.addScalar("count",StandardBasicTypes.INTEGER)
				.addScalar("succ",StandardBasicTypes.INTEGER)
				.addScalar("err",StandardBasicTypes.INTEGER)
				.setResultTransformer(Transformers.aliasToBean(SceneGroupCountVo.class));
		
		
		return queryData.list();
	}

	@Override
	public List<InterfaceVarianceVO> getInterfaceVarianceVO() {
		logger.debug("首页展示接口访问量Top10耗时统计");
		String strDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
		// TODO
		if (Integer.parseInt(ReadConfigation.getConfigItem("isTesting")) == 1) {
			strDate = "20151009";
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  COUNT(t1.id) callSum,");
		sql.append("  MAX(t1.timeout) maxTimeout,");
		sql.append("  MIN(t1.timeout) minTimeout,");
		sql.append("  VARIANCE(t1.timeout)/50 variance,");
		sql.append("  t1.interfaceName0 interfaceName,");
		sql.append("  t2.serverName serverName ");
		sql.append("FROM ");
		sql.append("(SELECT ");
		sql.append("  a.id id,");
		sql.append("  a.interfaceName interfaceName0,");
		sql.append("  a.timeout timeout,");
		sql.append("  a.trackNo trackNo ");
		sql.append("FROM ");
		sql.append("t_interface_timeout_");
		sql.append(strDate);
		sql.append(" as a ");
		sql.append("WHERE ");
		sql.append("  a.sceneCode!='' ");
		sql.append("GROUP BY ");
		sql.append("  trackNo ");
		sql.append(") t1, ");
		sql.append("(SELECT ");
		sql.append("  b.serverName serverName,");
		sql.append("  b.interfaceName interfaceName1 ");
		sql.append("FROM ");
		sql.append("  t_error_code b ");
		sql.append("GROUP BY ");
		sql.append("  interfaceName1 ");
		sql.append(") t2 ");
		sql.append("WHERE ");
		sql.append("  t1.interfaceName0=t2.interfaceName1 ");
		sql.append("GROUP BY interfaceName ");
		sql.append("ORDER BY callSum DESC ");
		sql.append("LIMIT 10");

		Query queryData = getSession().createSQLQuery(sql.toString())
				.addScalar("callSum", StandardBasicTypes.INTEGER)
				.addScalar("maxTimeout", StandardBasicTypes.INTEGER)
				.addScalar("minTimeout", StandardBasicTypes.INTEGER)
				.addScalar("variance", StandardBasicTypes.INTEGER)
				.addScalar("interfaceName", StandardBasicTypes.STRING)
				.addScalar("serverName", StandardBasicTypes.STRING)

		.setResultTransformer(Transformers.aliasToBean(InterfaceVarianceVO.class));

		return queryData.list();
	}

	@Override
	public Page getAllOutTimes(String yyyyMMdd, Integer curPage,String appName,String respCode,String serverName,String interfaceName,String sceneCode,String bizTrackNo) {
		
		StringBuilder sqlCount = new StringBuilder();
		sqlCount.append("select count(id) as count from t_interface_timeout_"+yyyyMMdd+" where 1=1 ");
		
		if (StringUtils.isNotBlank(appName)) {
			sqlCount.append("AND appName=:appName ");
		}
		if(StringUtils.isNotBlank(sceneCode)){
			sqlCount.append("AND sceneCode=:sceneCode ");
		}
		if(StringUtils.isNotBlank(respCode)){
			sqlCount.append("AND trackNo=:trackNo ");
		}
		if(StringUtils.isNotBlank(serverName)){
			sqlCount.append("AND serverName=:serverName ");
		}
		if(StringUtils.isNotBlank(interfaceName)){
			sqlCount.append("AND interfaceName=:interfaceName ");
		}
		if(StringUtils.isNotBlank(bizTrackNo)){
			sqlCount.append("AND bizTrackNo=:bizTrackNo ");
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_interface_timeout_"+yyyyMMdd+" where 1=1 ");
		if (StringUtils.isNotBlank(appName)) {
			sql.append("AND appName=:appName ");
		}
		if(StringUtils.isNotBlank(sceneCode)){
			sql.append("AND sceneCode=:sceneCode ");
		}
		if(StringUtils.isNotBlank(respCode)){
			sql.append("AND trackNo=:trackNo ");
		}
		if(StringUtils.isNotBlank(serverName)){
			sql.append("AND serverName=:serverName ");
		}
		if(StringUtils.isNotBlank(interfaceName)){
			sql.append("AND interfaceName=:interfaceName ");
		}
		if(StringUtils.isNotBlank(bizTrackNo)){
			sql.append("AND bizTrackNo=:bizTrackNo ");
		}
		
		sql.append("GROUP BY bizTrackNo ORDER BY timeout DESC ");
		Query queryCount = getSession().createSQLQuery(sqlCount.toString()).addScalar("count", StandardBasicTypes.INTEGER);

		Query queryData = getSession().createSQLQuery(sql.toString())
				.addScalar("id", StandardBasicTypes.INTEGER)
				.addScalar("appName", StandardBasicTypes.STRING)
				.addScalar("serverName", StandardBasicTypes.STRING)
				.addScalar("sceneCode", StandardBasicTypes.STRING)
				.addScalar("interfaceName", StandardBasicTypes.STRING)
				.addScalar("timeout", StandardBasicTypes.INTEGER)
				.addScalar("trackNo", StandardBasicTypes.STRING)
				.addScalar("rowKey", StandardBasicTypes.STRING)
				.addScalar("reqRowKey", StandardBasicTypes.STRING)
				.addScalar("resRowKey", StandardBasicTypes.STRING)
				.addScalar("respCode", StandardBasicTypes.STRING)
				.addScalar("isException", StandardBasicTypes.INTEGER)
				.addScalar("sceneNameEns", StandardBasicTypes.STRING)
				.addScalar("orgnlSeqNo", StandardBasicTypes.STRING)
				.addScalar("bizTrackNo", StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(InterfaceTimeOutVo.class));

		/*queryCount.setTimestamp("sceneCode", DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		queryCount.setTimestamp("endDate", DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
		queryData.setTimestamp("startDate", DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		queryData.setTimestamp("endDate", DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));*/

		if (StringUtils.isNotBlank(appName)) {
			queryCount.setString("appName", appName.trim());
			queryData.setString("appName", appName.trim());
		}
		if(StringUtils.isNotBlank(sceneCode)){
			queryCount.setString("sceneCode", sceneCode.trim());
			queryData.setString("sceneCode", sceneCode.trim());
		}
		if(StringUtils.isNotBlank(respCode)){
			queryCount.setString("trackNo", respCode.trim());
			queryData.setString("trackNo", respCode.trim());
		}
		if(StringUtils.isNotBlank(serverName)){
			queryCount.setString("serverName", serverName.trim());
			queryData.setString("serverName", serverName.trim());
		}
		if(StringUtils.isNotBlank(interfaceName)){
			queryCount.setString("interfaceName", interfaceName.trim());
			queryData.setString("interfaceName", interfaceName.trim());
		}
		if(StringUtils.isNotBlank(bizTrackNo)){
			queryCount.setString("bizTrackNo", bizTrackNo.trim());
			queryData.setString("bizTrackNo", bizTrackNo.trim());
		}
		
		

		Integer totalCount = (Integer) queryCount.uniqueResult();
		Page page = new Page(totalCount.intValue(), curPage.intValue(), 10);
		queryData.setFirstResult(page.getPageStartRow() - 1);
		queryData.setMaxResults(page.getPageRecorders());
		List list = queryData.list();
		page.setDataList(list);
		return page;
	}

	@Override
	public List<InterfaceTimeOutVo> showInfos(String yyyyMMdd, Integer id) {
		
		StringBuilder sql=new StringBuilder();
		sql.append("select t.* from t_interface_timeout_"+yyyyMMdd+" t,(select x.trackNo,x.bizTrackNo,x.orgnlSeqNo from t_interface_timeout_"+yyyyMMdd+" as x where id=:id) as tt ");
        sql.append(" where t.trackNo=tt.trackNo or t.bizTrackNo=tt.bizTrackNo or t.orgnlSeqNo=tt.orgnlSeqNo");
		Query queryData = getSession().createSQLQuery(sql.toString())
				.addScalar("id", StandardBasicTypes.INTEGER)
				.addScalar("appName", StandardBasicTypes.STRING)
				.addScalar("serverName", StandardBasicTypes.STRING)
				.addScalar("sceneCode", StandardBasicTypes.STRING)
				.addScalar("interfaceName", StandardBasicTypes.STRING)
				.addScalar("timeout", StandardBasicTypes.INTEGER)
				.addScalar("trackNo", StandardBasicTypes.STRING)
				.addScalar("rowKey", StandardBasicTypes.STRING)
				.addScalar("reqRowKey", StandardBasicTypes.STRING)
				.addScalar("resRowKey", StandardBasicTypes.STRING)
				.addScalar("respCode", StandardBasicTypes.STRING)
				.addScalar("isException", StandardBasicTypes.INTEGER)
				.addScalar("sceneNameEns", StandardBasicTypes.STRING)
				.addScalar("orgnlSeqNo", StandardBasicTypes.STRING)
				.addScalar("bizTrackNo", StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(InterfaceTimeOutVo.class));
		
		queryData.setInteger("id", id);

		return queryData.list();
	}
}
