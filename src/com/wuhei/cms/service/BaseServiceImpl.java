package com.wuhei.cms.service;

import java.util.List;

import com.wuhei.cms.dao.basic.CcategoryDAO;
import com.wuhei.cms.dao.basic.DepartmentDAO;
import com.wuhei.cms.model.Ccategory;
import com.wuhei.cms.model.Department;
import com.wuhei.cms.model.Major;

public class BaseServiceImpl implements BaseService{

	private DepartmentDAO departmentDAO;
	private CcategoryDAO ccategoryDAO;

	@Override
	public List<Department> getDepartment() {
		return departmentDAO.getDepartmentList();
	}

	@Override
	public List<Ccategory> getCcategoryList() {
		return ccategoryDAO.getCcategoryList();
	}

	@Override
	public List<Major> getMajorListByDepartmentId(
			Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


}
