package cn.disruptive.security.dao.impl;

import org.springframework.stereotype.Repository;

import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.security.dao.RoleDao;
import cn.disruptive.security.po.Role;


/**
 * 
 * Description：角色
 * Data：		2015年9月12日
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role, Integer> implements RoleDao {

}
