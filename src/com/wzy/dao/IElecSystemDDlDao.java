package com.wzy.dao;

import java.util.List;

import com.wzy.domain.ElecSystemDDl;

public interface IElecSystemDDlDao extends ICommonDao<ElecSystemDDl>{
	public static final String SERVICE_NAME="com.wzy.dao.impl.ElecSystemDDlDaoImpl";
	
	List<Object> findKeyWord();
}
