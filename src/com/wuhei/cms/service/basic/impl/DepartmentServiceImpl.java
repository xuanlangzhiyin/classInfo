package com.wuhei.cms.service.basic.impl;

import java.util.List;

import com.wuhei.cms.dao.basic.DepartmentDAO;
import com.wuhei.cms.model.Department;
import com.wuhei.cms.service.basic.DepartmentService;


public class DepartmentServiceImpl implements DepartmentService{

	public DepartmentDAO departmentDAO;
	
	@Override
	public List<Department> getDepartmentList() {
		return departmentDAO.getDepartmentList();
	}

	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
}
