package com.wzy.service;

import java.util.List;

import com.wzy.web.form.ElecCommonMsgForm;

public interface IElecCommonMsgService {
	public final static String SERVICE_NAME="com.wzy.service.impl.ElecCommonMsgServiceImpl";
	
	List<ElecCommonMsgForm> findElecCommonMsgList();
	
	void saveElecCommonMsg(ElecCommonMsgForm elecCommonMsgForm);
	
	List<ElecCommonMsgForm> findElecCommonMsgListByCurrentDate();
}
