package com.wuhei.cms.model.deletedata;


public class DeleteResult {

	// �ɹ���Ŀ
	protected Integer successCount;

	// ʧ����Ŀ
	protected Integer errorCount;

	public String toString(String subject) {
		return "����ɾ��" + subject + "�����ɾ���ɹ���Ŀ[" + successCount + "],ɾ��ʧ����Ŀ[" + errorCount + "]";
	}
	/*
	 * getters and setters
	 */

	public Integer getSuccessCount() {
		return successCount;
	}


	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}


	public Integer getErrorCount() {
		return errorCount;
	}


	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

}
