package com.wzy.service;

import java.util.List;

import com.wzy.web.form.ElecSystemDDlForm;


public interface IElecSystemDDlService {
	public static final String SERVICE_NAME="com.wzy.service.impl.ElecSystemDDlServiceImpl";
	
	List<ElecSystemDDlForm> findKeyWord();

	List<ElecSystemDDlForm> findElecSystemDDlListByKeyword(String keyword);

	void saveElecSystemDDl(ElecSystemDDlForm elecSystemDDlForm);
}
