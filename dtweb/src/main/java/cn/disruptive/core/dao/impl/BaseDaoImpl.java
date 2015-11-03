package cn.disruptive.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import cn.disruptive.core.common.page.Page;
import cn.disruptive.core.dao.BaseDao;

/**
 * 
 * @ClassName: BaseDaoImpl
 * @Description: DAO基类
 */
@Repository("baseDao")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
	/**
	 * 日志，可用于子类
	 */
	public final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	private Class<T> _clazz;

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}

	@Override
	public PK save(T entity) {
		Assert.notNull(entity, "entity不能为空");
		return (PK) getSession().save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void update(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().update(entity);
	}

	@Override
	public void merge(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().merge(entity);
	}

	@Override
	public void delete(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
	}

	@Override
	public void delete(PK id) {
		Assert.notNull(id);
		delete(get(id));
	}

	@Override
	public List<T> findAll() {
		String hqlAll = "from " + getEntityClazz().getSimpleName();
		Query query = getSession().createQuery(hqlAll);
		List<T> results = query.list();
		return results;
	}

	@Override
	public List find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	@Override
	public List find(String hql, Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	@Override
	public T get(PK id) {
		return (T) getSession().get(getEntityClazz(), id);
	}

	public Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		query = assignValues(query, values);
		return query;
	}

	public Query createQuery(String queryString, Map<String, ?> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		query = assignValues(query, values);
		return query;
	}
	/**
	 * 按顺序绑定
	 * @param query
	 * @param values
	 * @return
	 */
	public Query assignValues(Query query, Object... values) {
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] instanceof Date) {
					query.setTimestamp(i, (Date) values[i]);
				} else {
					query.setParameter(i, values[i]);
				}

			}
		}
		return query;
	}
	/**
	 * 按名称绑定
	 * @param query
	 * @param values
	 * @return
	 */
	public Query assignValues(Query query, Map<String, ?> values) {
		if (values != null) {
			Set<String> keySet = values.keySet();
			for (String string : keySet) {
				Object obj = values.get(string);
				if (obj instanceof Collection<?>) {
					query.setParameterList(string, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(string, (Object[]) obj);
				} else {
					query.setParameter(string, obj);
				}
			}
		}
		return query;
	}

	public T unique(final String hql, final Object... values) {
		Query query = getSession().createQuery(hql);
		query = assignValues(query, values);
		return (T) query.setMaxResults(1).uniqueResult();
	}

	@Override
	public T unique(String hql, Map<String, ?> values) {
		Query query = getSession().createQuery(hql);
		this.assignValues(query, values);
		return (T) query.setMaxResults(1).uniqueResult();
	}

	/**
	 * 执行批处理语句,insert, update, delete 等.
	 */
	@Override
	public int execteBulk(String hql, Object... values) {
		Query query = createQuery(hql, values);
		Object result = query.executeUpdate();
		return result == null ? 0 : ((Integer) result).intValue();
	}

	@Override
	public int execteBulk(String hql, Map<String, ?> values) {
		Query query = createQuery(hql, values);
		Object result = query.executeUpdate();
		return result == null ? 0 : ((Integer) result).intValue();
	}

	@Override
	public int execteNativeBulk(String natvieSQL, Object... values) {
		Query query = getSession().createSQLQuery(natvieSQL);
		query = assignValues(query, values);
		Object result = query.executeUpdate();
		return result == null ? 0 : ((Integer) result).intValue();
	}

	@Override
	public int execteNativeBulk(String natvieSQL, Map<String, ?> values) {
		Query query = getSession().createSQLQuery(natvieSQL);
		query = assignValues(query, values);
		Object result = query.executeUpdate();
		return result == null ? 0 : ((Integer) result).intValue();
	}

	@Override
	public Page findPageByHQL(int pageNo, int pageSize, String hql, final Object... values) {
		int totalRows = countQueryResult(hql, values);
		Page page = new Page(totalRows, pageNo, pageSize);
		if (totalRows < 1) {
			page.setDataList(new ArrayList());
			return page;
		}
		Query query = createQuery(hql, values);
		query.setFirstResult(page.getFirstResult());
		query.setMaxResults(page.getPageRecorders());
		List list = query.list();
		page.setDataList(list);
		return page;
	}

	@Override
	public Page findPageByHQL(int pageNo, int pageSize, String hql, Map<String, ?> values) {
		int totalRows = countQueryResult(hql, values);
		Page page = new Page(totalRows, pageNo, pageSize);
		if (totalRows < 1) {
			page.setDataList(new ArrayList());
			return page;
		}
		Query query = createQuery(hql, values);
		query.setFirstResult(page.getFirstResult());
		query.setMaxResults(page.getPageRecorders());
		List list = query.list();
		page.setDataList(list);
		return page;
	}

	protected Class<T> getEntityClazz() {
		if (this._clazz == null) {
			this._clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}

		return this._clazz;
	}

	public int countQueryResult(String hql, Object... values) {
		Query query = getSession().createQuery(getRowCountHql(hql));
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return ((Number) query.iterate().next()).intValue();
	}
	public int countQueryResult(String hql, Map<String, ?> values) {
		Query query = getSession().createQuery(getRowCountHql(hql));
		query = assignValues(query, values);
		return ((Number) query.iterate().next()).intValue();
	}
	public String getRowCountHql(String hql) {
		int fromIndex = hql.toLowerCase().indexOf("from");
		String projectionHql = hql.substring(0, fromIndex);

		hql = hql.substring(fromIndex);
		String rowCountHql = hql.replace("fetch", "");

		int index = rowCountHql.indexOf("order");
		if (index > 0) {
			rowCountHql = rowCountHql.substring(0, index);
		}
		return wrapProjection(projectionHql) + rowCountHql;

	}

	private String wrapProjection(String projection) {
		if (projection.indexOf("select") == -1) {
			return "select count(*) ";
		} else {
			return projection.replace("select", "select count(") + ") ";
		}
	}

	@Override
	public void flush() {
		getSession().flush();
	}

	@Override
	public void clear() {
		getSession().clear();
	}

	@Override
	public List<T> list(Criteria criteria) {
		return criteria.list();
	}

	@Override
	public T unique(Criteria criteria) {
		return (T) criteria.uniqueResult();
	}

	@Override
	public List<T> list(DetachedCriteria criteria) {
		return list(criteria.getExecutableCriteria(getSession()));
	}

	@Override
	public T unique(DetachedCriteria criteria) {
		return (T) unique(criteria.getExecutableCriteria(getSession()));
	}

	public List<T> findByProp(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		DetachedCriteria criteria = DetachedCriteria.forClass(getEntityClazz());
		criteria.add(Restrictions.eq(propertyName, value));
		return list(criteria);
	}

	/**
	 * 按属性查找唯一对象, 匹配方式为相等.
	 */
	public T findUniqueByProp(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		DetachedCriteria criteria = DetachedCriteria.forClass(getEntityClazz());
		criteria.add(Restrictions.eq(propertyName, value));
		return unique(criteria);
	}

	@Override
	public T findUniqueByTwoProp(String propertyNameOne, String propertyNameTwo, Object valueOne, Object valueTwo) {
		Assert.hasText(propertyNameOne, "propertyNameOne不能为空");
		Assert.hasText(propertyNameTwo, "propertyNameTwo不能为空");
		DetachedCriteria criteria = DetachedCriteria.forClass(getEntityClazz());
		criteria.add(Restrictions.eq(propertyNameOne, valueOne));
		criteria.add(Restrictions.eq(propertyNameTwo, valueTwo));
		return unique(criteria);
	}

	public Criteria createCriteria() {
		Criteria criteria = getSession().createCriteria(getEntityClazz());
		return criteria;
	}

	@Override
	public Object[] countQueryResults(String hql, Map<String, ?> values) {
		Query query = getSession().createQuery(hql);
		query = assignValues(query, values);
		return (Object[])query.iterate().next();
	}
	public Boolean appendWhere(StringBuffer hql,Boolean hasWhere){  
        if(hasWhere == false){  
        	hql.append(" where ");  
        	hasWhere=true;
        }else{  
        	hql.append(" and ");  
        }  
        return hasWhere;
	}

}
