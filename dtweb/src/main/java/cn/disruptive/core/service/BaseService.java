package cn.disruptive.core.service;

import java.io.Serializable;

public interface BaseService {

	@Deprecated
	public <T, PK extends Serializable> PK save(T entity);

	@Deprecated
	public <T> void saveOrUpdate(T entity);

	@Deprecated
	public <T> void update(T entity);

	@Deprecated
	public <T> void merge(T entity);

	@Deprecated
	public <T> void delete(T entity);

	@Deprecated
	public <T, PK extends Serializable> void delete(PK id);

	@Deprecated
	public <T, PK extends Serializable> T get(PK id);
}
