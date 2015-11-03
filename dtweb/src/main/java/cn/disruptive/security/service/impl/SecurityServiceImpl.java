package cn.disruptive.security.service.impl;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.disruptive.core.service.impl.BaseServiceImpl;
import cn.disruptive.security.dao.HandleLogsDao;
import cn.disruptive.security.po.HandleLogs;
import cn.disruptive.security.po.Role;
import cn.disruptive.security.service.RoleService;
import cn.disruptive.security.service.SecurityService;

/**
 * 
 * Description：安全控制
 * Data：		2015年9月23日
 */
@Transactional
@Service("securityService")
public class SecurityServiceImpl extends BaseServiceImpl implements SecurityService {
	@Resource
	private HandleLogsDao handleLogsDao;
	@Resource
	private RoleService roleService;

	@Override
	public void saveHandleLogs(Integer userId, String resourceCod, String logInfo, String ipAddr) {
		logger.info("用户操作日志，userId={}, resourceCod={}, logInfo={}, ipAddr={} ", new Object[] { userId, resourceCod, logInfo, ipAddr });
		HandleLogs handleLogs = new HandleLogs(null, userId, resourceCod, logInfo, ipAddr, new Date());
		handleLogsDao.save(handleLogs);
	}

	@Override
	public boolean judgeAuthority(Integer userId, String resourceCode) {
		logger.debug("开始判断权限：userId={}, resourceCode={}", userId, resourceCode);

		Set<Role> roles = roleService.getRolrsByUserId(userId);
		if (roles == null || roles.size() == 0) {
			return false;
		}

		for (Role role : roles) {
			Set<cn.disruptive.security.po.Resource> resources = role.getResources();
			if (resources == null || resources.size() == 0) {
				return false;
			}
			for (cn.disruptive.security.po.Resource resource : resources) {
				if (resource.getResourceCode().equals(resourceCode)) {
					logger.debug("验证通过");
					return true;
				}
			}
		}
		logger.info("验证未通过，调试期间返回true");
		return true;
	}
}
