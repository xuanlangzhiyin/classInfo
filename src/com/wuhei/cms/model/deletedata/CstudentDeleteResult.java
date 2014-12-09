package com.wuhei.cms.model.deletedata;

import java.util.List;

import com.wuhei.cms.model.joint.CstudentDetailView;

public class CstudentDeleteResult extends DeleteResult{

	protected List<CstudentDetailView> errorCstudentDetailViews;

	
	/*
	 * getters and setters
	 */
	public List<CstudentDetailView> getErrorCstudentDetailViews() {
		return errorCstudentDetailViews;
	}

	public void setErrorCstudentDetailViews(
			List<CstudentDetailView> errorCstudentDetailViews) {
		this.errorCstudentDetailViews = errorCstudentDetailViews;
	}

}
