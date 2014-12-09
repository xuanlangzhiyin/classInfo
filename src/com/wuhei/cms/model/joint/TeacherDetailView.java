package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Teacher;

/**
 * ����γ���ϸ��ͼ
 * ���ظ��ʦ��ϸҳ��Ľ��
 * ��ʾ��༭��ʦ����Ϣҳ��ʹ��
 *
 */
public class TeacherDetailView extends Teacher

{
	   /**
     * Ժϵ����
     */
    private String departmentCode;

    /**
     * Ժϵ���
     */
    private String departmentName;

    /**
     * �������ѧУ��university
     */
    private Integer universityid;
    
    
    
    
    
    
    /***
      * getter and setter
      * @return
      */
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getUniversityid() {
		return universityid;
	}

	public void setUniversityid(Integer universityid) {
		this.universityid = universityid;
	}
}
