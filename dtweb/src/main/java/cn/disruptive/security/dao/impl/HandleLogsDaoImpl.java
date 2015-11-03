package cn.disruptive.security.dao.impl;

import org.springframework.stereotype.Repository;

import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.security.dao.HandleLogsDao;
import cn.disruptive.security.po.HandleLogs;


/**
 * 
 * Description：权限控制角色操作日志
 * Data：		2015年9月12日
 */
@Repository("handleLogsDao")
public class HandleLogsDaoImpl extends BaseDaoImpl<HandleLogs, String> implements HandleLogsDao {

}
