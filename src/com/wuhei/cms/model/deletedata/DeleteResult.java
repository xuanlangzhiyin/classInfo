package com.wuhei.cms.model.deletedata;


public class DeleteResult {

	// 成功条目
	protected Integer successCount;

	// 失败条目
	protected Integer errorCount;

	public String toString(String subject) {
		return "批量删除" + subject + "结果：删除成功条目[" + successCount + "],删除失败条目[" + errorCount + "]";
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
