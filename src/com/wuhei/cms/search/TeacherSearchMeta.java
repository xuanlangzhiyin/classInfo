package com.wuhei.cms.search;

/**
 * 查询条件：教师列表页面
 * 
 *
 */
public class TeacherSearchMeta extends SearchMeta {
	
	/**
	 * 所属学校的id
	 */
	private Integer universityid;
	
	/**
	 * 所属院系的id
	 */
	private Integer departmentid;
	
	/**
	 * getters and setters
	 */
	
	public Integer getUniversityid() {
		return universityid;
	}

	public void setUniversityid(Integer universityid) {
		this.universityid = universityid;
	}

	public Integer getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}


}
