package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.TeacherDetailView;



/**
 * 定义接口
 *
 */
public interface TeacherDetailViewDAO {
	
	/**
	 * 进行教师筛选， 列出符合条件的教师最基本信息， 筛选条件包括学院Id、 姓名关键字
	 * 
	 * @param departmentId
	 * @param majorid
	 * @return
	 */
	public List<TeacherDetailView> getTeacherDetailViewListByCondition(
			@Param(value = "departmentId") Integer departmentId,
			@Param(value = "majorId") Integer majorid);
	
	public TeacherDetailView getTeacherDetailViewById(Integer id);
}
