package com.wzy.dao.impl;

import org.springframework.stereotype.Repository;

import com.wzy.dao.IElecTextDao;
import com.wzy.domain.ElecText;

@Repository(IElecTextDao.SERVICE_NAME)
public class ElecTextDaoImpl extends CommonDaoImpl<ElecText> implements IElecTextDao{

}
