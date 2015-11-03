package cn.disruptive.web.service;

import cn.disruptive.core.service.BaseService;
import cn.disruptive.core.session.AuthUser;
import cn.disruptive.web.po.UserInfo;

/**
 * 
 * Description：用户信息
 * Data：		2015年9月11日
 */
public interface UserInfoService extends BaseService {

	/**
	 * 
	 * Description：通过用户id获取用户信息
	 * Data：		2015年9月15日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param userId	用户id
	 * @return
	 */
	public UserInfo getAccountById(Integer userId);

	/**
	 * 
	 * Description：更新用户
	 * Data：		2015年9月15日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param userInfo	用户实体
	 */
	public void updateUserInfo(UserInfo userInfo);

}
