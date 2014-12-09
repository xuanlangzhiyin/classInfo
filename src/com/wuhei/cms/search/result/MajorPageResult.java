package com.wuhei.cms.search.result;

import java.util.List;

import com.wuhei.cms.model.joint.MajorListView;

public class MajorPageResult extends PageResult {
	protected List<MajorListView> majors;

	public List<MajorListView> getMajors() {
		return majors;
	}

	public void setMajors(List<MajorListView> majors) {
		this.majors = majors;
	}
}
