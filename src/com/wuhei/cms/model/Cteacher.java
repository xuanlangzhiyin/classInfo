package com.wuhei.cms.model;

public class Cteacher {
	private Integer id;
	
	private Integer teacherid;
	
	private Integer courseid;
	
	private Boolean isdominate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public Boolean getIsdominate() {
		return isdominate;
	}

	public void setIsdominate(Boolean isdominate) {
		this.isdominate = isdominate;
	}
}
