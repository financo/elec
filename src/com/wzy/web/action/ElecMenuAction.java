package com.wzy.web.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.wzy.container.ServiceProvider;
import com.wzy.service.IElecCommonMsgService;
import com.wzy.web.form.ElecCommonMsgForm;
import com.wzy.web.form.ElecMenuForm;

public class ElecMenuAction extends BaseAction implements ModelDriven<ElecMenuForm>{
	private IElecCommonMsgService elecCommonMsgService = (IElecCommonMsgService) ServiceProvider.getService(IElecCommonMsgService.SERVICE_NAME);
	
	private ElecMenuForm elecMenuForm = new ElecMenuForm();

	@Override
	public ElecMenuForm getModel() {
		return elecMenuForm;
	}
	
	public String home(){
		return "home";
	}
	
	public String title(){
		return "title";
	}
	
	public String left(){
		return "left";
	}
	
	public String change1(){
		return "change1";
	}
	
	public String loading(){
		return "loading";
	}
	
	public String alermJX(){
		return "alermJX";
	}
	
	public String alermSB(){
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgListByCurrentDate();
		request.setAttribute("commonList", list);
		return "alermSB";
	}
	
	public String alermXZ(){
		return "alermXZ";
	}
	
	public String alermYS(){
		return "alermYS";
	}
	
	public String alermZD(){
		List<ElecCommonMsgForm> list = elecCommonMsgService.findElecCommonMsgListByCurrentDate();
		request.setAttribute("commonList", list);
		return "alermZD";
	}
}
