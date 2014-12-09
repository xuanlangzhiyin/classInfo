package com.wuhei.cms.search.result;

import java.util.List;

import com.wuhei.cms.model.joint.TeacherListView;

/**
 * ���ظ���ʦ�б�ҳ��Ľ��
 * ��̨��ҳʱʹ��
 *
 */
public class TeacherPageResult extends PageResult{
	
	private List<TeacherListView> teachers;
	
	/**
	 * getters and setters
	 */

	public List<TeacherListView> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherListView> teachers) {
		this.teachers = teachers;
	}
}
