package com.wuhei.cms.search.result;

import java.util.List;

import com.wuhei.cms.model.joint.CourseListView;

@SuppressWarnings("serial")
public class CoursePageResult extends PageResult {
	
	private List<CourseListView> courses;

	
	/*
	 * getters and setters
	 */
	
	public List<CourseListView> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseListView> courses) {
		this.courses = courses;
	}
	
}
