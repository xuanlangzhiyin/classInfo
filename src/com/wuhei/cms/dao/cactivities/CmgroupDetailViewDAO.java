package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.CmgroupDetailView;


public interface CmgroupDetailViewDAO {
	
	public List<CmgroupDetailView> getCmgroupDetailViewListByCondition(
			@Param(value = "isinvolved")Boolean isinvolved,
			@Param(value = "cmissionid")Integer cmissionid);

	public CmgroupDetailView getCmgroupDetailViewListByCmgroupid(Integer cmgroupid);
	
	public List<CmgroupDetailView> getNotinMCmgroupDetailViewListByCmissionid(Integer cmissionid);
}
