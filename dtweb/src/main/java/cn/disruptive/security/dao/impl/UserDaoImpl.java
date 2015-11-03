package cn.disruptive.security.dao.impl;

import org.springframework.stereotype.Repository;

import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.security.dao.UserDao;
import cn.disruptive.security.po.User;

/**
 * 
 * Description：用户信息
 * Data：		2015年9月23日
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

}
