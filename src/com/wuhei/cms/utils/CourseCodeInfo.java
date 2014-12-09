package com.wuhei.cms.utils;

public class CourseCodeInfo {

	/**
	 * (2014-2015-1)-155372-G03005-1
	 * 
	 */
	private String courseCode;

	/**
	 * 学年：2014-2015
	 */
	private String year;

	/**
	 * 学期:1 or 2
	 */
	private String term;

	private String csettingcode;

	private String teachercode;
	
	private String classMark;

	public CourseCodeInfo(String courseCode) {
		/*
		 * (2014-2015-1)-155372-G03005-1
		 */
		this.setCourseCode(courseCode);
		// 首先去掉左右括号
		courseCode = courseCode.replace("(", "");
		courseCode = courseCode.replace(")", "");
		// 按“-”对原字符串进行分割
		String[] sourceStrArray = courseCode.split("-");
		this.setYear(sourceStrArray[0] + "-" + sourceStrArray[1]);
		this.setTerm(sourceStrArray[2]);
		this.setCsettingcode(sourceStrArray[3]);
		this.setTeachercode(sourceStrArray[4]);
		this.setClassMark(sourceStrArray[5]);

	}

	/*
	 * getters and setters
	 */

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getCsettingcode() {
		return csettingcode;
	}

	public void setCsettingcode(String csettingcode) {
		this.csettingcode = csettingcode;
	}

	public String getTeachercode() {
		return teachercode;
	}

	public void setTeachercode(String teachercode) {
		this.teachercode = teachercode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getClassMark() {
		return classMark;
	}

	public void setClassMark(String classMark) {
		this.classMark = classMark;
	}
	
	
}
