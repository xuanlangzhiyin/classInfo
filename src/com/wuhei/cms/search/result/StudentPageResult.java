package com.wuhei.cms.search.result;

import java.util.List;

import com.wuhei.cms.model.joint.StudentListView;


public class StudentPageResult extends PageResult{
	
	List<StudentListView> students;

	/**
	 * getters and setters
	 */
	
	public List<StudentListView> getStudents() {
		return students;
	}

	public void setStudents(List<StudentListView> students) {
		this.students = students;
	}
	
}
