package com.wzy.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wzy.dao.ICommonDao;
import com.wzy.util.GenericSuperClass;

public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T>{

	private Class entity = (Class)GenericSuperClass.getClass(this.getClass());
	
	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}
	
	@Resource(name="sessionFactory")
	public final void setSessionFactoryDi(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findObjectByID(Serializable id) {
		return (T)this.getHibernateTemplate().get(entity , id);
	}

	@Override
	public void deleteObjectByIDs(Serializable ... ids) {
		for(int i=0;ids!=null && i<ids.length;i++){
			Serializable id = ids[i];
			Object object = (Object)this.getHibernateTemplate().get(entity, id);
			this.getHibernateTemplate().delete(object);
		}
	}

	@Override
	public void deleteObjectByCollection(Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}

	@Override
	public List<T> findCollectionByConditionNoPage(String hqlWhere,
			final Object[] params, LinkedHashMap<String, String> orderby) {
		String hql = "from " + entity.getSimpleName() + " o where 1=1";
		//组织排序条件
		String hqlOrderBy = this.orderByCondition(orderby);
		hql = hql + hqlWhere + hqlOrderBy;
		final String finalHql = hql;
		List<T> list = (List<T>)this.getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(finalHql);
				setParams(query,params);
				return query.list();
			}
		});
		return list;
	}

	private String orderByCondition(LinkedHashMap<String, String> orderby) {
		StringBuffer buffer = new StringBuffer("");
		if(orderby!=null){
			buffer.append(" order by ");
			for(Map.Entry<String, String> map:orderby.entrySet()){
				buffer.append(" " + map.getKey() + " " + map.getValue() + ",");
			}
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}
	
	private void setParams(Query query,Object[] params) {
		for(int i=0;params!=null && i<params.length;i++){
			query.setParameter(i, params[i]);
		}
	}

	@Override
	public void saveObjectByCollection(Collection<T> entities) {
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}
}
