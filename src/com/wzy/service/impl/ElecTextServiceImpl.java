package com.wzy.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wzy.dao.IElecTextDao;
import com.wzy.domain.ElecText;
import com.wzy.service.IElecTextService;
import com.wzy.web.form.ElecTextForm;
@Transactional(readOnly=true)
@Service(IElecTextService.SERVICE_NAME)
public class ElecTextServiceImpl implements IElecTextService{
	
	@Resource(name=IElecTextDao.SERVICE_NAME)
	private IElecTextDao elecTextDao;
	
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecText(ElecText elecText) {
		elecTextDao.save(elecText);
	}

	@Override
	public List<ElecText> findCollectionByConditionNoPage(
			ElecTextForm elecTextForm) {
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecTextForm!=null && StringUtils.isNotBlank(elecTextForm.getTextName())){
			hqlWhere += " and o.textName like ?";
			paramsList.add("%"+elecTextForm.getTextName()+"%");
		}
		if(elecTextForm!=null && StringUtils.isNotBlank(elecTextForm.getTextRemark())){
			hqlWhere += " and o.textRemark like ?";
			paramsList.add("%"+elecTextForm.getTextRemark()+"%");
		}
		Object [] params = paramsList.toArray();
		/**
		 * 组织排序语句
		 *     order by o.textDate desc , o.textName asc 
		 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.textDate", "desc");
		orderby.put("o.textName", "asc");
		//查询列表
		List<ElecText> list = elecTextDao.findCollectionByConditionNoPage(hqlWhere,params,orderby);
		for(int i=0;list!=null && i<list.size();i++){
			ElecText elecText = list.get(i);
			System.out.println(elecText.getTextName() + "　" + elecText.getTextRemark());
		}
		return null;
	}

}
