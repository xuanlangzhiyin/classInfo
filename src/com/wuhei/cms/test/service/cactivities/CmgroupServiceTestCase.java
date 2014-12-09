package com.wuhei.cms.test.service.cactivities;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wuhei.cms.model.joint.CmgroupDetailView;
import com.wuhei.cms.model.joint.CmstudentDetailView;
import com.wuhei.cms.service.cactivities.CmgroupService;
import com.wuhei.cms.test.service.ServiceTestCase;

public class CmgroupServiceTestCase extends ServiceTestCase{
	@Autowired
	private CmgroupService cmgroupService;
	private List<CmstudentDetailView> ungroupedCmstudentDetailViews;
	private List<CmgroupDetailView> cmgroupDetailViews;
	private List<CmstudentDetailView> getCmgroupMemberDetials;
	
	@Test
	public void testgetUngroupedCmstudentDetailViewListByCmissionid() {
		Integer cmissionid=1;
		ungroupedCmstudentDetailViews=cmgroupService.getUngroupedCmstudentDetailViewListByCmissionid(cmissionid);
		for(int i=0;i<ungroupedCmstudentDetailViews.size();i++)
		{
		System.out.println(ungroupedCmstudentDetailViews.get(i).getName());
		}
	}
	
	@Test
	public void testgetCmgroupDetailViewListByCmissionid() {
		Integer cmissionid=1;
		
		cmgroupDetailViews=cmgroupService
				.getCmgroupDetailViewListByCmissionid(cmissionid);
		for(int i=0;i<cmgroupDetailViews.size();i++)
		{
		System.out.println(cmgroupDetailViews.get(i).getLeadername());
		}
	}
	
	@Test
	public void testgetCmstudentDetailViewListByCmgroupid() {
		Integer cmgroupid=1;
		getCmgroupMemberDetials=cmgroupService.getCmstudentDetailViewListByCmgroupid(cmgroupid);
		for(int i=0;i<getCmgroupMemberDetials.size();i++)
		{
		System.out.println(getCmgroupMemberDetials.get(i).getName());
		}
	}
	
	@Test
	public void testdeleteCmgroupMember() {
		Integer cmstudentid=1001;
		cmgroupService.deleteCmgroupMember(cmstudentid);
	}
}