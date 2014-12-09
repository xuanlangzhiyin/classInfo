package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.wuhei.cms.model.joint.StudentUserListView;

public interface StudentUserListViewDAO {
	
	

	/**
	 * 学生用户后台分页
	 * @param keyword
	 * @param start
	 * @param count
	 * @return
	 */
	
	public List<StudentUserListView> listStudentUserByCondition(
			@Param(value = "keyword") String keyword,
			@Param(value = "start") Integer start,
			@Param(value = "count") Integer count);

	/** 
	 * 获取满足关键字学生用户的数量
	 * @param keyword
	 * @return
	 */
	
	public Integer countStudentUserByCondition(
			@Param(value = "keyword") String keyword);
}
