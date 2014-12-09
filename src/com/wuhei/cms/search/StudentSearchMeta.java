package com.wuhei.cms.search;

public class StudentSearchMeta extends SearchMeta{
	
	/**
	 * 所属专业
	 */
	protected Integer majorid;

	/**
	 * 学生年级
	 */
	protected String stdgrade;
	
	/**
	 * 学生班级
	 */
	protected String stdclass;
	
	
	
	/*
	 * getters and setters
	 */
	
	public String getStdgrade() {
		return stdgrade;
	}

	public void setStdgrade(String stdgrade) {
		this.stdgrade = stdgrade;
	}

	public String getStdclass() {
		return stdclass;
	}

	public void setStdclass(String stdclass) {
		this.stdclass = stdclass;
	}
	
	public Integer getMajorid() {
		return majorid;
	}

	public void setMajorid(Integer majorid) {
		this.majorid = majorid;
	}
}
