package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Cmcredit;

public class CmcreditDetailView extends Cmcredit{
	/**
	 * ѧ������
	 */
	
	private String cstudentName;

	/**
	 * ��������
	 */
	private String cmissionName;

	/**
	 * �������� ��С������
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
