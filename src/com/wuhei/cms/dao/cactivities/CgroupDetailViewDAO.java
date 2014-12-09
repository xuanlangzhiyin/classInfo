package com.wuhei.cms.dao.cactivities;

import java.util.List;

import com.wuhei.cms.model.joint.CgroupDetailView;




public interface CgroupDetailViewDAO {

	public List<CgroupDetailView> getCgroupDetailListByCondition(
			Integer courseid);

	/**
	 * ���cgroupid��ȡCgroupDetailView
	 * @param cgroupid
	 * @return
	 */
	public CgroupDetailView getCgroupDetailView(Integer cgroupid);

}
