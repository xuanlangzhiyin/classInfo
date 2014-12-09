package com.wuhei.cms.model.deletedata;

import java.util.List;

import com.wuhei.cms.model.Teacher;


public class TeacherDeleteResult extends DeleteResult 
{
	 List<Teacher>errTeachers;

	 
	 //getters and setters
	public List<Teacher> getErrTeachers() {
		return errTeachers;
	}

	public void setErrTeachers(List<Teacher> errTeachers) {
		this.errTeachers = errTeachers;
	}
}
