package com.wuhei.cms.service.cactivities.impl;

import java.util.List;


import com.wuhei.cms.dao.cactivities.CmcreditDAO;
import com.wuhei.cms.dao.cactivities.CmcreditDetailViewDAO;

import com.wuhei.cms.dao.cactivities.CmissionDAO;

import com.wuhei.cms.dao.cactivities.CmcreditListViewDAO;

import com.wuhei.cms.dao.cactivities.CmreportDAO;
import com.wuhei.cms.dao.cactivities.CmstudentDAO;
import com.wuhei.cms.model.Cmcredit;
import com.wuhei.cms.model.Cmreport;
import com.wuhei.cms.model.joint.CmcreditDetailView;
import com.wuhei.cms.model.joint.CmcreditListView;
import com.wuhei.cms.service.cactivities.CmcreditService;

public class CmcreditServiceImpl implements CmcreditService {

	private CmreportDAO cmreportDAO;
	private CmcreditDetailViewDAO cmcreditDetailViewDAO;
	private CmcreditListViewDAO cmcreditListViewDAO;
	private CmcreditDAO cmcreditDAO;

	private CmstudentDAO cmstudentDAO;
	private CmissionDAO cmissionDAO;

	@Override
	public List<CmcreditDetailView> getCmcreditDetailViewsByCmreportid(
			Integer cmreportid) {
		Cmreport cmreport = cmreportDAO.getCmreport(cmreportid);
		if (cmreport.getIsgroup())
			return cmcreditDetailViewDAO
					.getCmcreditDetailViewsByCmreportidForCmgroup(cmreportid);
		else
			return cmcreditDetailViewDAO
					.getCmcreditDetailViewsByCmreportidForPesonal(cmreportid);
	}

	@Override
	public List<CmcreditDetailView> getCmcreditDetailViewsByCmgroupid(Integer cmgroupid)
	{	
		return cmcreditDetailViewDAO.getCmcreditDetailViewsByCmgroupid(cmgroupid);
	}
	
	@Override
	public Cmcredit getCmcreditById(Integer cmcreditid) {
		return cmcreditDAO.getCmcredit(cmcreditid);
	}

	@Override
	public void updateCmcredit(Cmcredit cmcredit) {
		cmcreditDAO.updateCmcredit(cmcredit);

	}

	@Override
	public void insertCmcredit(Cmcredit cmcredit) {
		cmcreditDAO.insertCmcredit(cmcredit);

	}
	
	
	
	public CmcreditDetailView getCmcreditDetailViewByCondition(Integer cstudentid, Integer cmissionid)
	{
		 return cmcreditDetailViewDAO.getCmcreditDetailViewByCondition(cstudentid, cmissionid);
	}
	
	public List<CmcreditListView> getCmcreditListViewsByCondition(Integer cstudentid, Integer courseid)
	{
		
		List<CmcreditListView> cmcreditListView =cmcreditListViewDAO.getCmcreditListViewsByCondition(cstudentid, courseid);
		return cmcreditListView;
	}
	
	
	public CmcreditListViewDAO getCmcreditListViewDAO() {
		return cmcreditListViewDAO;
	}

	public void setCmcreditListViewDAO(CmcreditListViewDAO cmcreditListViewDAO) {
		this.cmcreditListViewDAO = cmcreditListViewDAO;
	}

	public CmissionDAO getCmissionDAO() {
		return cmissionDAO;
	}

	public void setCmissionDAO(CmissionDAO cmissionDAO) {
		this.cmissionDAO = cmissionDAO;
	}

	public CmreportDAO getCmreportDAO() {
		return cmreportDAO;
	}

	public void setCmreportDAO(CmreportDAO cmreportDAO) {
		this.cmreportDAO = cmreportDAO;
	}

	public CmcreditDetailViewDAO getCmcreditDetailViewDAO() {
		return cmcreditDetailViewDAO;
	}

	public void setCmcreditDetailViewDAO(
			CmcreditDetailViewDAO cmcreditDetailViewDAO) {
		this.cmcreditDetailViewDAO = cmcreditDetailViewDAO;
	}

	public CmcreditDAO getCmcreditDAO() {
		return cmcreditDAO;
	}

	public void setCmcreditDAO(CmcreditDAO cmcreditDAO) {
		this.cmcreditDAO = cmcreditDAO;
	}

	public CmstudentDAO getCmstudentDAO() {
		return cmstudentDAO;
	}

	public void setCmstudentDAO(CmstudentDAO cmstudentDAO) {
		this.cmstudentDAO = cmstudentDAO;
	}
	
	

}