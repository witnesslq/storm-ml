package cn.disruptive.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.disruptive.core.service.impl.BaseServiceImpl;
import cn.disruptive.web.dao.UserInfoDao;
import cn.disruptive.web.po.UserInfo;
import cn.disruptive.web.service.UserInfoService;

/**
 * 
 * Description：登录
 * Data：		2015年9月11日
 */
@Transactional
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl implements UserInfoService {

	@Resource
	private UserInfoDao userInfoDao;

	@Override
	public UserInfo getAccountById(Integer userId) {
		logger.debug("通过用户id获取用户，userId={}", userId);
		return userInfoDao.get(userId);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		logger.debug("更新用户");
		userInfoDao.saveOrUpdate(userInfo);
	}

}
