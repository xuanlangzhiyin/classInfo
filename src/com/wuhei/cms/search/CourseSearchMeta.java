package com.wuhei.cms.search;

public class CourseSearchMeta extends SearchMeta {
	
	
	/**
	 * ��ѯ������ѧ�꣬���磺2013-2014
	 */
	private String year;

	/**
	 * ��ѯ������ѧ�ڣ����磺1��2
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
