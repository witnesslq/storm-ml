package cn.disruptive.web.dao.impl;

import org.springframework.stereotype.Repository;

import cn.disruptive.core.dao.impl.BaseDaoImpl;
import cn.disruptive.web.dao.UserInfoDao;
import cn.disruptive.web.po.UserInfo;

/**
 * 
 * Description：用户信息
 * Data：		2015年9月12日
 */
@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo, Integer> implements UserInfoDao {

}
