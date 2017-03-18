package com.wzy.web.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;
import com.wzy.container.ServiceProvider;
import com.wzy.service.IElecSystemDDlService;
import com.wzy.web.form.ElecSystemDDlForm;

public class ElecSystemDDlAction extends BaseAction implements ModelDriven<ElecSystemDDlForm>{
	private IElecSystemDDlService elecSystemDDlService = (IElecSystemDDlService) ServiceProvider.getService(IElecSystemDDlService.SERVICE_NAME);
	
	private ElecSystemDDlForm elecSystemDDlForm = new ElecSystemDDlForm();

	@Override
	public ElecSystemDDlForm getModel() {
		return elecSystemDDlForm;
	}
	
	public String home(){
		List<ElecSystemDDlForm> list = elecSystemDDlService.findKeyWord();
		request.setAttribute("systemList", list);
		return "home";
	}
	
	public String edit(){
		//获取数据类型
		String keyword = elecSystemDDlForm.getKeyword();
		List<ElecSystemDDlForm> list = elecSystemDDlService.findElecSystemDDlListByKeyword(keyword);
		request.setAttribute("systemList", list);
		return "edit";
	}
	
	public String save(){
		elecSystemDDlService.saveElecSystemDDl(elecSystemDDlForm);
		return "save";
	}
}
