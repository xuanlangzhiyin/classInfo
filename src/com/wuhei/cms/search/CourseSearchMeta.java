package com.wuhei.cms.search;

public class CourseSearchMeta extends SearchMeta {
	
	
	/**
	 * 查询条件：学年，例如：2013-2014
	 */
	private String year;

	/**
	 * 查询条件：学期，例如：1和2
	 */
	private String term;
	
	
	/**
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
}
