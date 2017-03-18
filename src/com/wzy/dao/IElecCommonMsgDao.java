package com.wzy.dao;

import java.util.List;

import com.wzy.domain.ElecCommonMsg;

public interface IElecCommonMsgDao extends ICommonDao<ElecCommonMsg>{
	public final static String SERVICE_NAME="com.wzy.dao.impl.ElecCommonMsgDaoImpl";
	
	List<Object[]> findElecCommonMsgListByCurrentDate(String currentDate);
}
