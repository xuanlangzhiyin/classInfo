package com.wuhei.cms.search.result;

import java.util.List;

import com.wuhei.cms.model.joint.StudentUserListView;
import com.wuhei.cms.model.joint.TeacherUserListView;

public class UserPageResult extends PageResult{

	List <StudentUserListView> students;

	List <TeacherUserListView> teachers;
	
	/*
	 * getter and setter
	 */
	

	public List<TeacherUserListView> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherUserListView> teachers) {
		this.teachers = teachers;
	}

	public List<StudentUserListView> getStudents() {
		return students;
	}

	public void setStudents(List<StudentUserListView> students) {
		this.students = students;
	}
	
	
	
	
	
}
