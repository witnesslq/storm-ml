package cn.disruptive.web.dao.impl;

import org.springframework.stereotype.Repository;

import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.web.dao.AlarmTypeDao;
import cn.disruptive.web.po.AlarmType;

/**
 * 
 * Description：警告类型
 * Data：		2015年9月12日
 */
@Repository("alarmTypeDao")
public class AlarmTypeDaoImpl extends BaseDaoImpl<AlarmType, Integer> implements AlarmTypeDao {

}
