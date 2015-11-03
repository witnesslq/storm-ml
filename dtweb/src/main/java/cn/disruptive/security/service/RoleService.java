package cn.disruptive.security.service;

import java.util.Set;

import cn.disruptive.core.service.BaseService;
import cn.disruptive.security.po.Role;

/**
 * 
 * Description：角色
 * Data：		2015年9月24日
 */
public interface RoleService extends BaseService {

	/**
	 * 
	 * Description：通过用户id加载角色
	 * Data：		2015年9月24日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param userId	用户id
	 * @return
	 */
	public Set<Role> getRolrsByUserId(Integer userId);
	
	/**
	 * 
	 * Description：清除用户权限缓存
	 * Data：		2015年9月25日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param userId	用户id
	 */
	public void cleanGetRolesCache(Integer userId);

}
