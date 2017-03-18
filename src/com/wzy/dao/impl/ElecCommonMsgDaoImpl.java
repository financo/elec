package com.wzy.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.wzy.dao.IElecCommonMsgDao;
import com.wzy.domain.ElecCommonMsg;
@Repository(IElecCommonMsgDao.SERVICE_NAME)
public class ElecCommonMsgDaoImpl extends CommonDaoImpl<ElecCommonMsg> implements IElecCommonMsgDao{

	@Override
	public List<Object[]> findElecCommonMsgListByCurrentDate(String currentDate) {
		final String sql = "SELECT o.StationRun as stationRun,o.DevRun as devRun " +
				     "FROM Elec_CommonMsg o " + 
	                "WHERE o.CreateDate = '" + currentDate + "'";
		List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback(){
	
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql)
							  .addScalar("stationRun",Hibernate.STRING)
				              .addScalar("devRun",Hibernate.STRING);
				return query.list();
			}
		});
		return list;
	}

}
