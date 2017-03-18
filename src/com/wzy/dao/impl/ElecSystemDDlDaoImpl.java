package com.wzy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wzy.dao.IElecSystemDDlDao;
import com.wzy.domain.ElecSystemDDl;
@Repository(IElecSystemDDlDao.SERVICE_NAME)
public class ElecSystemDDlDaoImpl extends CommonDaoImpl<ElecSystemDDl> implements IElecSystemDDlDao{

	@Override
	public List<Object> findKeyWord() {
		String hql = "select distinct o.keyword from ElecSystemDDl o";
		List<Object> list = this.getHibernateTemplate().find(hql);
		return list;
	}

}
