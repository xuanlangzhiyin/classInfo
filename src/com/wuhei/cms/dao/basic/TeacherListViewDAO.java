package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.TeacherListView;

public interface TeacherListViewDAO {
	/**
	 * 进行教师筛选， 列出符合条件的教师最基本信息， 筛选条件包括学院Id、 姓名关键字
	 * @param departmentId
	 * @param keyword
	 * @return
	 */
	public List<TeacherListView> listTeacherByConditions(
			@Param(value = "departmentId") Integer departmentId,
			@Param(value = "keyword") String keyword,
	        @Param(value = "start") Integer start,
	        @Param(value = "count") Integer count);
	
	public Integer countTeacherByConditions(
			@Param(value = "departmentId") Integer departmentId,
			@Param(value = "keyword") String keyword);
}
