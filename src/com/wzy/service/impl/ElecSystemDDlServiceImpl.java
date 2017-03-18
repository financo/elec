package com.wzy.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wzy.dao.IElecSystemDDlDao;
import com.wzy.domain.ElecSystemDDl;
import com.wzy.service.IElecSystemDDlService;
import com.wzy.web.form.ElecSystemDDlForm;

@Transactional(readOnly=true)
@Service(IElecSystemDDlService.SERVICE_NAME)
public class ElecSystemDDlServiceImpl implements IElecSystemDDlService{
	@Resource(name=IElecSystemDDlDao.SERVICE_NAME)
	private IElecSystemDDlDao elecSystemDDlDao;
	@Override
	public List<ElecSystemDDlForm> findKeyWord() {
		List<Object> list = elecSystemDDlDao.findKeyWord();
		List<ElecSystemDDlForm> formList = this.elecSystemDDlObjectToVO(list);
		return formList;
	}

	private List<ElecSystemDDlForm> elecSystemDDlObjectToVO(List<Object> list) {
		List<ElecSystemDDlForm> formList = new ArrayList<ElecSystemDDlForm>();
		ElecSystemDDlForm elecSystemDDlForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			Object object = list.get(i);
			elecSystemDDlForm = new ElecSystemDDlForm();
			elecSystemDDlForm.setKeyword(object.toString());
			formList.add(elecSystemDDlForm);
		}
		return formList;
	}

	@Override
	public List<ElecSystemDDlForm> findElecSystemDDlListByKeyword(String keyword) {
		List<ElecSystemDDl> list = this.findSystemDDlListByCondition(keyword);
		List<ElecSystemDDlForm> formList = this.elecSystemDDlPOListToVOList(list);
		return formList;
	}

	private List<ElecSystemDDlForm> elecSystemDDlPOListToVOList(
			List<ElecSystemDDl> list) {
		List<ElecSystemDDlForm> formList = new ArrayList<ElecSystemDDlForm>();
		ElecSystemDDlForm elecSystemDDlForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			ElecSystemDDl elecSystemDDl = list.get(i);
			elecSystemDDlForm = new ElecSystemDDlForm();
			elecSystemDDlForm.setSeqID(String.valueOf(elecSystemDDl.getSeqID()));
			elecSystemDDlForm.setKeyword(elecSystemDDl.getKeyword());
			elecSystemDDlForm.setDdlCode(String.valueOf(elecSystemDDl.getDdlCode()));
			elecSystemDDlForm.setDdlName(elecSystemDDl.getDdlName());
			formList.add(elecSystemDDlForm);
		}
		return formList;
	}

	private List<ElecSystemDDl> findSystemDDlListByCondition(String keyword) {
		String hqlWhere = " and o.keyword = ?";
		Object [] params = {keyword};
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.ddlCode", "asc");
		List<ElecSystemDDl> list = elecSystemDDlDao.findCollectionByConditionNoPage(hqlWhere, params, orderby);
		return list;
	}
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public void saveElecSystemDDl(ElecSystemDDlForm elecSystemDDlForm) {
		//获取页面传递的表单值
				//获取数据类型
				String keyword = elecSystemDDlForm.getKeywordname();
				//获取判断是新增数据类型还是在原有的数据类型中编辑的标识
				String typeflag = elecSystemDDlForm.getTypeflag();
				//获取需要保存的数据项的名称
				String [] itemname = elecSystemDDlForm.getItemname();
				//如果是新增数据类型的操作，此时typeflag==new
				if(typeflag!=null && typeflag.equals("new")){
					//保存数据字典
					this.saveSystemDDlWithParams(keyword,itemname);
				}
				//否则是表示在原有的数据类型中进行修改和编辑，此时typeflag==add
				else{
					List<ElecSystemDDl> list = findSystemDDlListByCondition(keyword);
					elecSystemDDlDao.deleteObjectByCollection(list);
					//保存数据字典
					this.saveSystemDDlWithParams(keyword,itemname);
				}
	}

	private void saveSystemDDlWithParams(String keyword, String[] itemname) {
		List<ElecSystemDDl> list = new ArrayList<ElecSystemDDl>();
		for(int i=0;itemname!=null && i<itemname.length;i++){
			ElecSystemDDl elecSystemDDl = new ElecSystemDDl();
			elecSystemDDl.setKeyword(keyword);
			elecSystemDDl.setDdlName(itemname[i]);
			elecSystemDDl.setDdlCode(new Integer(i+1));
			list.add(elecSystemDDl);
			//elecSystemDDlDao.save(elecSystemDDl);
		}
		elecSystemDDlDao.saveObjectByCollection(list);
	}

}
