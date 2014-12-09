package com.wuhei.cms.action;

import org.apache.log4j.Logger;

import com.wuhei.cms.model.Course;

/**
 * 
 * 课程活动|基类
 * 
 * 
 */
@SuppressWarnings("serial")
public class CactivityAction extends BaseAction {

	Logger logger = Logger.getLogger(CactivityAction.class);
	
	/**
	 * 开设课程id
	 */
	protected Integer courseid;
	
	/**
	 * 开设课程
	 */
	protected Course course;
	

	/*
	 * getters and setters
	 */
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}


}
