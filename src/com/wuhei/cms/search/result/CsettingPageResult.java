package com.wuhei.cms.search.result;

import java.util.List;

import com.wuhei.cms.model.joint.CsettingListView;

@SuppressWarnings("serial")
public class CsettingPageResult extends PageResult {

private List<CsettingListView> csettings;
	
	/**
	 * getters and setters
	 */

	public List<CsettingListView> getCsettings() {
		return csettings;
	}

	public void setCsettings(List<CsettingListView> csettings) {
		this.csettings = csettings;
	}
	
}
