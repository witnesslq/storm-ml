package cn.disruptive.security.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.disruptive.core.service.impl.BaseServiceImpl;
import cn.disruptive.security.service.ResourceService;

/**
 * 
 * Description： 资源
 * Data： 		2015年9月24日
 */
@Transactional
@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl implements ResourceService {

}
