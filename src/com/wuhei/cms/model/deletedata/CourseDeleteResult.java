package com.wuhei.cms.model.deletedata;

import java.util.List;

import com.wuhei.cms.model.joint.CourseDetailView;



public class CourseDeleteResult extends DeleteResult 
{
	 List<CourseDetailView>errorCourses;
	 //getters and setters
	public List<CourseDetailView> getErrorCourses() {
		return errorCourses;
	}

	public void setErrorCourses(List<CourseDetailView> errorCourses) {
		this.errorCourses = errorCourses;
	}

	

}
