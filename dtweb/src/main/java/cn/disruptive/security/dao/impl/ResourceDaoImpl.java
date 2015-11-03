package cn.disruptive.security.dao.impl;

import org.springframework.stereotype.Repository;

import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.security.dao.ResourceDao;
import cn.disruptive.security.po.Resource;


/**
 * 
 * Description：资源
 * Data：		2015年9月23日
 */
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource, Integer> implements ResourceDao {

}
