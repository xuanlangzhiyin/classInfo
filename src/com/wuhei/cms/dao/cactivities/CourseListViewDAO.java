package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.CourseListView;




public interface CourseListViewDAO {
	
	/**
	 * 根据条件搜索courselist
	 * @param teacherid
	 * @param year
	 * @param term
	 * @return
	 */
	public List<CourseListView> getCourseListViewByCondition (
			@Param(value = "teacherid")Integer teacherid,
			@Param(value = "year")String year,
			@Param(value = "term")String term);
	/**
	 * 
	 * @param studentid
	 * @param year
	 * @param term
	 * @return  List<CourseListView>
	 */
	public List<CourseListView> getCourseListView4stuByCondition (
			@Param(value = "studentid")Integer studentid,
			@Param(value = "year")String year,
			@Param(value = "term")String term);
	
	/**
	 * @param departmentid
	 * @param year
	 * @param term
	 */
	public List<CourseListView> getCourseListViewByDepartmentId(
			@Param(value = "departmentId")Integer departmentid,
			@Param(value = "year")String year,
			@Param(value = "term")String term);
}
