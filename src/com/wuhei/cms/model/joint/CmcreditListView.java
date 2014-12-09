package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Cmcredit;

public class CmcreditListView extends Cmcredit {
	/**
	 * �������
	 */
	private String cmissionName;

	/**
	 * �������� ��С������
	 */
	private String cmissionType;

	
	/**
	 * getters and setters
	 * 
	 * @return
	 */
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
