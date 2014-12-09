package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Cmstudent;

public class CmstudentDetailView extends Cmstudent {

	/**
	 * 是否参与任务中文，用于前端显示 参与任务|不参与任务
	 */
	private String isinvolvedChinese;

	/**
	 * 参加的课程任务名称
	 */
	private String cmissionName;

	/**
	 * 是否已提交报告中文
	 */
	private String isSubmitChinese;
	
	/**
	 * 参加的课程任务得分
	 */
	private Byte credit;

	/**
	 * 外键，学生：student
	 */
	private Integer studentid;

	/**
	 * 学生学号studentid->code
	 */
	private String studentCode;

	/**
	 * 学生年级studentid->grade
	 */
	private String studentGrade;

	/**
	 * 学生班级studentid->stuclass
	 */
	private String stuClass;

	/**
	 * 性别，默认为男studentid->sex
	 */
	private String sex;


	/**
	 * 学生id
	 */
	public String getStudentCode() {
		return studentCode;
	}

	/**
	 * 学生年级
	 */
	public String getStudentGrade() {
		return studentGrade;
	}

	/**
	 * 学生班级
	 */
	public String getStuClass() {
		return stuClass;
	}

	/**
	 * 性别，默认为男
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

		// 调用父类的打印方法
		String string = super.toString();
		// 取消最后一个“]”
		string.substring(0, string.length() - 1);
		string += ", isinvolvedChinese=" + isinvolvedChinese
				+ ", cmissionName=" + cmissionName + ", credit=" + credit
				+ ", studentid=" + studentid + ", studentCode=" + studentCode
				+ ", studentGrade=" + studentGrade + ", stuClass=" + stuClass
				+ ", sex=" + sex;
		return string;

	}

}
