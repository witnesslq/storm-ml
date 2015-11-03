package cn.disruptive.web.dao.impl;

import org.springframework.stereotype.Repository;

import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.web.dao.AlarmRuleDao;
import cn.disruptive.web.po.AlarmRule;

/**
 * 
 * Description：告警规则
 * Data：		2015年9月12日
 */
@Repository("alarmRuleDao")
public class AlarmRuleDaoImpl extends BaseDaoImpl<AlarmRule, Integer> implements AlarmRuleDao {

}
