package cn.disruptive.web.dao.impl;

import org.springframework.stereotype.Repository;

import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.web.dao.AlarmGroupDao;
import cn.disruptive.web.po.AlarmGroup;

/**
 * 
 * Description：告警组
 * Data：		2015年9月12日
 */
@Repository("alarmGroupDao")
public class AlarmGroupDaoImpl extends BaseDaoImpl<AlarmGroup, Integer> implements AlarmGroupDao {

}
