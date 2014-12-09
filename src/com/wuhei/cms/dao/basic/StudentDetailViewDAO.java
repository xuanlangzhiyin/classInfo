package com.wuhei.cms.dao.basic;

import com.wuhei.cms.model.joint.StudentDetailView;

public interface StudentDetailViewDAO {

	/**
	 * 获取学生详细信息StudentDetailView
	 * 
	 * @param id要查询详细信息的学生id
	 * @return 返回学生详细信息类实例
	 */
	public StudentDetailView getStudentDetailViewById(Integer id);

}