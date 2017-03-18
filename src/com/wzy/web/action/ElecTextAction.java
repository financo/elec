package com.wzy.web.action;

import com.opensymphony.xwork2.ModelDriven;
import com.wzy.container.ServiceProvider;
import com.wzy.domain.ElecText;
import com.wzy.service.IElecTextService;
import com.wzy.util.StringHelper;
import com.wzy.web.form.ElecTextForm;

@SuppressWarnings("serial")
public class ElecTextAction extends BaseAction implements ModelDriven<ElecTextForm>{

	private ElecTextForm elecTextForm = new ElecTextForm();
	
	private IElecTextService elecTextService = (IElecTextService) ServiceProvider.getService(IElecTextService.SERVICE_NAME);
	@Override
	public ElecTextForm getModel() {
		return elecTextForm;
	}
	
	public String save() {
		System.out.println("============================");
		ElecText elecText = new ElecText();
		elecText.setTextDate(StringHelper.stringConvertDate(elecTextForm.getTextDate()));
		elecText.setTextName(elecTextForm.getTextName());
		elecText.setTextRemark(elecTextForm.getTextRemark());
		elecTextService.saveElecText(elecText);
		return "save";
	}
	
}
