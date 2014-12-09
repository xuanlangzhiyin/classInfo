package com.wuhei.cms.service.basic;

import java.util.List;

import com.wuhei.cms.model.Department;

public interface DepartmentService {

	/**
	 * 获得所有院系id、名称
	 * 新增教师时，需要选择该教师所属的院系，使用下拉框
	 * @return 返回教师信息列表
	 */
	public List<Department> getDepartmentList();
}
