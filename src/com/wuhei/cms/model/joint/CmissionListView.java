package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Cmission;

public class CmissionListView extends Cmission {

	// 提交了报告的学生人数
	private Integer reportedStudentNumber;

	private String isSubmited;
	
	private Boolean isSubmitionShowed;
	
	

	public Boolean getIsSubmitionShowed() {
		return isSubmitionShowed;
	}

	public void setIsSubmitionShowed(Boolean isSubmitionShowed) {
		this.isSubmitionShowed = isSubmitionShowed;
	}

	public String getIsSubmited() {
		return isSubmited;
	}

	public void setIsSubmited(String isSubmited) {
		this.isSubmited = isSubmited;
	}

	public Integer getReportedStudentNumber() {
		return reportedStudentNumber;
	}

	public void setReportedStudentNumber(Integer reportedStudentNumber) {
		this.reportedStudentNumber = reportedStudentNumber;
	}

}
