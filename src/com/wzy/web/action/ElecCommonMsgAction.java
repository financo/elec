package com.wzy.web.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.wzy.container.ServiceProvider;
import com.wzy.service.IElecCommonMsgService;
import com.wzy.web.form.ElecCommonMsgForm;

@SuppressWarnings("serial")
public class ElecCommonMsgAction extends BaseAction implements ModelDriven<ElecCommonMsgForm>{

	private IElecCommonMsgService elecCommonMsgService = (IElecCommonMsgService) ServiceProvider.getService(IElecCommonMsgService.SERVICE_NAME);
	
	private ElecCommonMsgForm elecCommonMsgForm = new ElecCommonMsgForm();
	@Override
	public ElecCommonMsgForm getModel() {
		return elecCommonMsgForm;
	}
	
	public String home(){
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgList();
		request.setAttribute("commonList", list);
		return "home";
	}
	
	public String save(){
		elecCommonMsgService.saveElecCommonMsg(elecCommonMsgForm);
		return "save";
	}

}
