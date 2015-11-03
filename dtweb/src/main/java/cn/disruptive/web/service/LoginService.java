package cn.disruptive.web.service;

import cn.disruptive.core.service.BaseService;
import cn.disruptive.core.session.AuthUser;

/**
 * 
 * Description：登录
 * Data：		2015年9月11日
 */
public interface LoginService extends BaseService {

	/**
	 * 
	 * Description：用户登录验证
	 * Data：		2015年9月11日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param loginName	登录姓名
	 * @param loginPwd	登录密码
	 * @param auth		session中的登录信息实体
	 * @return
	 */
	public AuthUser login(String loginName, String loginPwd, AuthUser auth);

}
