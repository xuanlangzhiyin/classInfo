package com.wuhei.cms.search;

/**
 * ��ѯ��������ʦ�б�ҳ��
 * 
 *
 */
public class TeacherSearchMeta extends SearchMeta {
	
	/**
	 * ����ѧУ��id
	 */
	private Integer universityid;
	
	/**
	 * ����Ժϵ��id
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
