package cn.disruptive.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;

import cn.disruptive.core.common.page.Page;

@SuppressWarnings("rawtypes")
public interface BaseDao<T, PK extends Serializable> {
	public PK save(T entity);

	public void saveOrUpdate(T entity);

	public void update(T entity);

	public void merge(T entity);

	public void delete(T entity);

	public void delete(PK id);

	public List<T> findAll();

	public T get(PK id);

	public T unique(final String hql, final Object... values);

	public T unique(final String hql, final Map<String, ?> values);

	public List find(final String hql, final Object... values);

	public List find(final String hql, final Map<String, ?> values);

	public Query createQuery(String queryString, Object... values);

	public Query createQuery(String queryString, Map<String, ?> values);

	/**
	 * 执行批处理语句,insert, update, delete 等.
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public int execteBulk(String hql, Object... values);

	public int execteBulk(String hql, Map<String, ?> values);

	public int execteNativeBulk(String natvieSQL, Object... values);

	public int execteNativeBulk(String natvieSQL, Map<String, ?> values);

	/**
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            每页记录数
	 * @param hql
	 *            查询hql
	 * @param values
	 *            参数值
	 * @return
	 */
	public Page findPageByHQL(int pageNo, int pageSize, String hql, final Object... values);

	public Page findPageByHQL(int pageNo, int pageSize, String hql, final Map<String, ?> values);

	public void flush();

	public void clear();

	public List<T> list(Criteria criteria);

	public T unique(Criteria criteria);

	public List<T> list(DetachedCriteria criteria);

	public T unique(DetachedCriteria criteria);

	public List<T> findByProp(String propertyName, Object value);

	public T findUniqueByProp(String propertyName, Object value);

	public T findUniqueByTwoProp(String propertyNameOne, String propertyNameTwo, Object valueOne, Object valueTwo);

	public Criteria createCriteria();

	public int countQueryResult(String hql, Object... values);

	public int countQueryResult(String hql, Map<String, ?> values);
	
	public Object[] countQueryResults(String hql, Map<String, ?> values);
}
