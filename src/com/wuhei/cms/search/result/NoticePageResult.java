package com.wuhei.cms.search.result;

import java.util.List;

import com.wuhei.cms.model.joint.NoticeListView;

public class NoticePageResult extends PageResult {
	
	protected List<NoticeListView> notices;

	public List<NoticeListView> getNotices() {
		return notices;
	}

	public void setNotices(List<NoticeListView> notices) {
		this.notices = notices;
	}
	
	
}
