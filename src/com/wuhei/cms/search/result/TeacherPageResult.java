package com.wuhei.cms.search.result;

import java.util.List;

import com.wuhei.cms.model.joint.TeacherListView;

/**
 * 返回给教师列表页面的结果
 * 后台分页时使用
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
