package com.wuhei.cms.service;

import java.util.List;

import com.wuhei.cms.model.Ccategory;
import com.wuhei.cms.model.Department;
import com.wuhei.cms.model.Major;

/**
 * Service层的common接口
 * 
 *
 */
public interface BaseService {
	
	/**
	 * 课程类别列表 在新增课程设置时，需要下拉选择课程类别列表
	 * @return
	 */
	public List<Ccategory> getCcategoryList();

	/**
	 * 获得所有院系id、名称
	 * 新增教师时，需要选择该教师所属的院系，使用下拉框
	 * @return
	 */
	public List<Department> getDepartment();
	
	/**
	 * 专业列表 在新增课程设置时，需要下拉选择专业
	 * @param id
	 * @return
	 */
	public List<Major> getMajorListByDepartmentId(Integer id);

	
}
