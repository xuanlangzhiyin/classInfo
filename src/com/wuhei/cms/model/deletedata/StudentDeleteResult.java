package com.wuhei.cms.model.deletedata;

import java.util.List;

import com.wuhei.cms.model.Student;


public class StudentDeleteResult extends DeleteResult {

	// 删除失败的学生列表
	List<Student> errorStudents;

	public List<Student> getErrorStudents() {
		return errorStudents;
	}

	public void setErrorStudents(List<Student> errorStudents) {
		this.errorStudents = errorStudents;
	}

}
