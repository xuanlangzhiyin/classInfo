package com.wuhei.cms.action;

import org.apache.log4j.Logger;

import com.wuhei.cms.model.Course;

/**
 * 
 * �γ̻|����
 * 
 * 
 */
@SuppressWarnings("serial")
public class CactivityAction extends BaseAction {

	Logger logger = Logger.getLogger(CactivityAction.class);
	
	/**
	 * ����γ�id
	 */
	protected Integer courseid;
	
	/**
	 * ����γ�
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
