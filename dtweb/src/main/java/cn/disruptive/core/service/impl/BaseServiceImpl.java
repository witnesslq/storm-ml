package cn.disruptive.core.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import cn.disruptive.core.dao.BaseDao;
import cn.disruptive.core.service.BaseService;

@Transactional
@SuppressWarnings({"rawtypes","unchecked"})
public class BaseServiceImpl implements BaseService {
	public final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;
	@Override
	public <T, PK extends Serializable> PK save(T entity) {
		
		return (PK) baseDao.save(entity);
	}
	
	@Override
	public <T> void saveOrUpdate(T entity) {
		baseDao.saveOrUpdate(entity);
	}
	@Override
	public <T> void update(T entity) {
		baseDao.update(entity);
	}
	@Override
	public <T> void merge(T entity) {
		baseDao.merge(entity);
	}
	@Override
	public <T> void delete(T entity) {
		baseDao.delete(entity);
	}
	@Override
	public <T, PK extends Serializable> void delete(PK id) {
		baseDao.delete(id);
	}
	@Override
	public <T, PK extends Serializable> T get(PK id) {
		
		return (T) baseDao.get(id);
	}
}
