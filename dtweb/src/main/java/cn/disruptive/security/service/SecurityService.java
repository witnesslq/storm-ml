package cn.disruptive.security.service;

import cn.disruptive.core.service.BaseService;

/**
 * 
 * Description：安全控制
 * Data：		2015年9月23日
 */
public interface SecurityService extends BaseService {

	/**
	 * 
	 * Description：记录用户操作日志
	 * Data：		2015年9月23日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param userId		用户id
	 * @param resourceCod	资源码
	 * @param logInfo		操作日志
	 * @param ip			所在ip
	 */
	public void saveHandleLogs(Integer userId, String resourceCod, String logInfo, String ip);

	/**
	 * 
	 * Description：判断权限
	 * Data：		2015年9月24日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param userId		用户id
	 * @param resourceCode	资源码
	 * @return
	 */
	public boolean judgeAuthority(Integer userId, String resourceCode);

}
