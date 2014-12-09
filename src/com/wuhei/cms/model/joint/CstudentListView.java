package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Cstudent;

public class CstudentListView extends Cstudent {
	/**
	 * ѧ��ѧ��|studentid->code
	 */
	private String code;
	/**
	 * ѧ���꼶|studentid->grade
	 */
	private String grade;
	/**
	 * ѧ��༶|studentid->stuclass
	 */
	private String stuclass;

	/**
	 * ѧ���Ա�
	 * 
	 * @return
	 */

	private String sex;

	/**
	 * ѧ�� �Ƿ�����
	 * @return
	 */
	
	private String isEvaluation;
	
	
	
	// getters and setters
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStuclass() {
		return stuclass;
	}

	public void setStuclass(String stuclass) {
		this.stuclass = stuclass;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIsEvaluation() {
		return isEvaluation;
	}

	public void setIsEvaluation(String isEvaluation) {
	   this.isEvaluation=isEvaluation;
	}

	
}
