package com.wuhei.cms.service.basic.impl;

import java.util.List;

import com.wuhei.cms.dao.basic.CcategoryDAO;
import com.wuhei.cms.model.Ccategory;
import com.wuhei.cms.service.basic.CcategoryService;

public class CcategoryServiceImpl implements CcategoryService
 
{
 private CcategoryDAO ccategoryDAO;

 public List<Ccategory> getCcategoryList()
 {
  return ccategoryDAO.getCcategoryList();
 }
 

 
 //getters and setters 
public CcategoryDAO getCcategoryDAO() {
	return ccategoryDAO;
}

public void setCcategoryDAO(CcategoryDAO ccategoryDAO) {
	this.ccategoryDAO = ccategoryDAO;
}  
}
