package com.wuhei.cms.model.deletedata;

import java.util.List;

import com.wuhei.cms.model.Student;


public class StudentDeleteResult extends DeleteResult {

	// ɾ��ʧ�ܵ�ѧ���б�
	List<Student> errorStudents;

	public List<Student> getErrorStudents() {
		return errorStudents;
	}

	public void setErrorStudents(List<Student> errorStudents) {
		this.errorStudents = errorStudents;
	}

}
