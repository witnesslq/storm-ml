package cn.disruptive.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.disruptive.common.util.Encrypt;
import cn.disruptive.core.service.impl.BaseServiceImpl;
import cn.disruptive.core.session.AuthUser;
import cn.disruptive.web.dao.UserInfoDao;
import cn.disruptive.web.po.UserInfo;
import cn.disruptive.web.service.LoginService;

/**
 * 
 * Description：登录
 * Data：		2015年9月11日
 */
@Transactional
@Service("lsoginService")
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

	@Resource
	private UserInfoDao userInfoDao;

	@Override
	public AuthUser login(String loginName, String loginPwd, AuthUser auth) {
		logger.info("用户登录验证，loginName={},loginPwd={}", loginName, Encrypt.encryptMD5(loginPwd));
		UserInfo userInfo = userInfoDao.findUniqueByProp("userLoginName", loginName);
		if (userInfo != null) {
			if (userInfo.getUserPwd().equals(Encrypt.encryptMD5(loginPwd))) {
				if (userInfo.getIsDelete() != 0) {
					return auth;
				}
				auth.setUserId(userInfo.getId());
				auth.setUserName(userInfo.getUserLoginName());
			} else {
				// 登录密码不正确
			}
		} else {
			// 登录帐号 没找到
		}
		return auth;
	}

}
