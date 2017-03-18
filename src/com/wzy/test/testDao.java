package com.wzy.test;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wzy.dao.IElecTextDao;
import com.wzy.domain.ElecText;

public class testDao {
	@Resource(name=IElecTextDao.SERVICE_NAME)
	private static IElecTextDao elecTextDao;
	
	public IElecTextDao getElecTextDao() {
		return elecTextDao;
	}

	public void setElecTextDao(IElecTextDao elecTextDao) {
		this.elecTextDao = elecTextDao;
	}

	public static void main(String[] args){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		elecTextDao = (IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
		ElecText elecText = new ElecText();
		elecText.setTextDate(new Date());
		elecText.setTextID("333");
		elecText.setTextName("wzy");
		elecText.setTextRemark("hsfeehe");
		
		elecTextDao.save(elecText);
	}
}
