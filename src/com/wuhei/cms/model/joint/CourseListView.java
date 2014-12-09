package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Course;


/**
 * ����γ��б���ͼ
 * ���ظ���γ��б�ҳ��Ľ��
 * ��ʾ����γ��б�ҳ��ʹ��
 *
 */
public class CourseListView extends Course {
	/**
	 * ��ʦ����
	 */
	private String teachername;
	/**
	 * �γ�������
	 */
	private String ccategoryname;
	
	/**
	 * �γ�����
	 */
	private String coursetype;
	
	/**
	 * ���ſε�������ʦ������
	 * 
	 */
	private String allTeacherName;

	
	//getters and setters
	public String getAllTeacherName() {
		return allTeacherName;
	}

	public void setAllTeacherName(String allTeacherName) {
		this.allTeacherName = allTeacherName;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getCcategoryname() {
		return ccategoryname;
	}

	public void setCcategoryname(String ccategoryname) {
		this.ccategoryname = ccategoryname;
	}


	
	public String getCoursetype() {
		return coursetype;
	}

	public void setCoursetype(String coursetype) {
		this.coursetype = coursetype;
	}

	public String toString() {
		//���ø���Ĵ�ӡ����
		String string=super.toString();
		//ȡ�����һ����]��
		string.substring(0,string.length()-1);
		string+=", teachername="+teachername+", coursetype="+coursetype+", ccategoryname="+ccategoryname;
		return string;
	}
	
}
