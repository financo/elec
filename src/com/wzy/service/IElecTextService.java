package com.wzy.service;


import java.util.List;

import com.wzy.domain.ElecText;
import com.wzy.web.form.ElecTextForm;

public interface IElecTextService {
	public static final String SERVICE_NAME="com.wzy.service.ElecTextService.Impl";
	public void saveElecText(ElecText elecText);
	public List<ElecText> findCollectionByConditionNoPage(ElecTextForm elecTextForm);
}
