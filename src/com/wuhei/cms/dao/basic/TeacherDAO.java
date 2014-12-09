package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Teacher;


public interface TeacherDAO {

	public void insertTeacher(Teacher teacher);

	
	public void updateTeacher(Teacher teacher);

	
	
	public void deleteTeacher(Integer id);

	
	public Teacher getTeacher(Integer id);

	
	public List<Teacher> getTeacherList();

	
	public List<Teacher> getTeacherListByCourseId(Integer id);
	
	public List<Teacher> getTeacherListByDepartmentid(Integer departmentid);
	
	
	public List<Teacher> getTeacherListByCondition(
			@Param(value = "departmentId") Integer departmentId,
			@Param(value = "keyword") String keyword);

	
	public List<Teacher> getTeacherListByCode(String code);
			
	
    public Teacher getMainCteacher(Integer courseid);
}