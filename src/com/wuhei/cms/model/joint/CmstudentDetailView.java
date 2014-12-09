package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Cmstudent;

public class CmstudentDetailView extends Cmstudent {

	/**
	 * �Ƿ�����������ģ�����ǰ����ʾ ��������|����������
	 */
	private String isinvolvedChinese;

	/**
	 * �μӵĿγ���������
	 */
	private String cmissionName;

	/**
	 * �Ƿ����ύ��������
	 */
	private String isSubmitChinese;
	
	/**
	 * �μӵĿγ�����÷�
	 */
	private Byte credit;

	/**
	 * �����ѧ����student
	 */
	private Integer studentid;

	/**
	 * ѧ��ѧ��studentid->code
	 */
	private String studentCode;

	/**
	 * ѧ���꼶studentid->grade
	 */
	private String studentGrade;

	/**
	 * ѧ���༶studentid->stuclass
	 */
	private String stuClass;

	/**
	 * �Ա�Ĭ��Ϊ��studentid->sex
	 */
	private String sex;


	/**
	 * ѧ��id
	 */
	public String getStudentCode() {
		return studentCode;
	}

	/**
	 * ѧ���꼶
	 */
	public String getStudentGrade() {
		return studentGrade;
	}

	/**
	 * ѧ���༶
	 */
	public String getStuClass() {
		return stuClass;
	}

	/**
	 * �Ա�Ĭ��Ϊ��
	 */
	public String getSex() {
		return sex;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}

	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIsinvolvedChinese() {
		return isinvolvedChinese;
	}

	public void setIsinvolvedChinese(String isinvolvedChinese) {
		this.isinvolvedChinese = isinvolvedChinese;
	}

	public String getCmissionName() {
		return cmissionName;
	}

	public void setCmissionName(String cmissionName) {
		this.cmissionName = cmissionName;
	}

	public Byte getCredit() {
		return credit;
	}

	public void setCredit(Byte credit) {
		this.credit = credit;
	}

	public Integer getStudentid() {
		return studentid;
	}
	
	
	public String getIsSubmitChinese() {
		return isSubmitChinese;
	}

	public void setIsSubmitChinese(String isSubmitChinese) {
		this.isSubmitChinese = isSubmitChinese;
	}

	@Override
	public String toString() {

		// ���ø���Ĵ�ӡ����
		String string = super.toString();
		// ȡ�����һ����]��
		string.substring(0, string.length() - 1);
		string += ", isinvolvedChinese=" + isinvolvedChinese
				+ ", cmissionName=" + cmissionName + ", credit=" + credit
				+ ", studentid=" + studentid + ", studentCode=" + studentCode
				+ ", studentGrade=" + studentGrade + ", stuClass=" + stuClass
				+ ", sex=" + sex;
		return string;

	}

}
