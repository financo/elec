package com.wzy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wzy.dao.IElecCommonMsgDao;
import com.wzy.domain.ElecCommonMsg;
import com.wzy.service.IElecCommonMsgService;
import com.wzy.web.form.ElecCommonMsgForm;

@Service(IElecCommonMsgService.SERVICE_NAME)
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService{
	
	@Resource(name=IElecCommonMsgDao.SERVICE_NAME)
	private IElecCommonMsgDao elecCommonMsgDao;

	@Override
	public List<ElecCommonMsgForm> findElecCommonMsgList() {
		String hqlWhere = "";
		Object [] params = null;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put(" o.createDate", "desc");
		List<ElecCommonMsg> list = elecCommonMsgDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		List<ElecCommonMsgForm> formList = this.elecCommonMsgPOListToVOList(list);
		return formList;
	}

	private List<ElecCommonMsgForm> elecCommonMsgPOListToVOList(
			List<ElecCommonMsg> list) {
		List<ElecCommonMsgForm> formList = new ArrayList<ElecCommonMsgForm>();
		ElecCommonMsgForm elecCommonMsgForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			ElecCommonMsg elecCommonMsg = list.get(i);
			elecCommonMsgForm = new ElecCommonMsgForm();
			elecCommonMsgForm.setComID(elecCommonMsg.getComID());
			elecCommonMsgForm.setDevRun(elecCommonMsg.getDevRun());
			elecCommonMsgForm.setStationRun(elecCommonMsg.getStationRun());
			elecCommonMsgForm.setCreateDate(String.valueOf(elecCommonMsg.getCreateDate()!=null?elecCommonMsg.getCreateDate():""));
			formList.add(elecCommonMsgForm);
		}
		return formList;
	}

	@Override
	public void saveElecCommonMsg(ElecCommonMsgForm elecCommonMsgForm) {
		ElecCommonMsg elecCommonMsg = this.elecCommonMsgVOToPO(elecCommonMsgForm);
		elecCommonMsgDao.save(elecCommonMsg);
	}

	private ElecCommonMsg elecCommonMsgVOToPO(
			ElecCommonMsgForm elecCommonMsgForm) {
		ElecCommonMsg elecCommonMsg = new ElecCommonMsg();
		if(elecCommonMsgForm!=null){
			elecCommonMsg.setStationRun(elecCommonMsgForm.getStationRun());
			elecCommonMsg.setDevRun(elecCommonMsgForm.getDevRun());
			elecCommonMsg.setCreateDate(new Date());
		}
		return elecCommonMsg;
	}

	@Override
	public List<ElecCommonMsgForm> findElecCommonMsgListByCurrentDate() {
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		String currentDate = date.toString();
		List<Object[]> list = elecCommonMsgDao.findElecCommonMsgListByCurrentDate(currentDate);
		List<ElecCommonMsgForm> formList = this.elecCommonMsgObjectListToVOList(list);
		return formList;
	}

	private List<ElecCommonMsgForm> elecCommonMsgObjectListToVOList(
			List<Object[]> list) {
		List<ElecCommonMsgForm> formList = new ArrayList<ElecCommonMsgForm>();
		ElecCommonMsgForm elecCommonMsgForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			Object[] object = list.get(i);
			elecCommonMsgForm = new ElecCommonMsgForm();
			elecCommonMsgForm.setStationRun(object[0].toString());
			elecCommonMsgForm.setDevRun(object[1].toString());
			formList.add(elecCommonMsgForm);
		}
		return formList;
	}

	
}
