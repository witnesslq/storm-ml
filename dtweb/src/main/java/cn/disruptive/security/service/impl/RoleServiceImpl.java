package cn.disruptive.security.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.disruptive.core.service.impl.BaseServiceImpl;
import cn.disruptive.security.dao.UserDao;
import cn.disruptive.security.po.Role;
import cn.disruptive.security.po.User;
import cn.disruptive.security.service.RoleService;

/**
 * 
 * Description：角色
 * Data：		2015年9月24日
 */
@Transactional
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	@Resource
	private UserDao userDao;

	@Override
	@Cacheable(value = "dataCache", key = "'security_'+#userId")
	public Set<Role> getRolrsByUserId(Integer userId) {
		logger.debug("通过用户id加载角色：userId={}", userId);
		User user = userDao.get(userId);
		return user.getRoleses();
	}

	@Override
	@CacheEvict(value = "dataCache", key = "'security_'+#userId")
	public void cleanGetRolesCache(Integer userId) {
		logger.info("清除用户权限缓存，用户id={}", userId);

	}

}
