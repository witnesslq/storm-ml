package cn.disruptive.web.dao.impl;

import org.springframework.stereotype.Repository;

import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.web.dao.AlarmConfDao;
import cn.disruptive.web.po.AlarmConf;

/**
 * 
 * Description：告警配置
 * Data：		2015年9月12日
 */
@Repository("alarmConfDao")
public class AlarmConfDaoImpl extends BaseDaoImpl<AlarmConf, Integer> implements AlarmConfDao {

}
