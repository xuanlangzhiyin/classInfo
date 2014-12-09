package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Cmcredit;

public class CmcreditDetailView extends Cmcredit{
	/**
	 * 学生姓名
	 */
	
	private String cstudentName;

	/**
	 * 任务名称
	 */
	private String cmissionName;

	/**
	 * 任务类型 （小组任务）
	 */
	private String cmissionType;

	public String getCstudentName() {
		return cstudentName;
	}

	public void setCstudentName(String cstudentName) {
		this.cstudentName = cstudentName;
	}

	public String getCmissionName() {
		return cmissionName;
	}

	public void setCmissionName(String cmissionName) {
		this.cmissionName = cmissionName;
	}

	public String getCmissionType() {
		return cmissionType;
	}

	public void setCmissionType(String cmissionType) {
		this.cmissionType = cmissionType;
	}
	
	
	
	
	
	
}
