package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.model.Cteacher;

public interface CteacherService {


	public void insertCteacher(Cteacher cteacher);
	

	public void updateCteacher(Cteacher cteacher);
	

	public void deleteCteacher(Integer courseid);
	

	public List<Cteacher> getCteacherByCourseid(Integer courseid);
	

	/**
    * 判断当前用户（教师）是否为该课程的开课教师
    */
	 public Boolean isTeacherAuthorizedOnCourse(Integer courseid);
}
