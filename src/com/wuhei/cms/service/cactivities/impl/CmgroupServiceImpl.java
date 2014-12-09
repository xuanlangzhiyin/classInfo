package com.wuhei.cms.service.cactivities.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuhei.cms.dao.cactivities.CmgroupDAO;
import com.wuhei.cms.dao.cactivities.CmgroupDetailViewDAO;
import com.wuhei.cms.dao.cactivities.CmissionDAO;
import com.wuhei.cms.dao.cactivities.CmreportDAO;
import com.wuhei.cms.dao.cactivities.CmstudentDAO;
import com.wuhei.cms.dao.cactivities.CmstudentDetailViewDAO;
import com.wuhei.cms.exception.AccessDeniedException;
import com.wuhei.cms.model.Cmgroup;
import com.wuhei.cms.model.joint.CmgroupDetailView;
import com.wuhei.cms.model.joint.CmstudentDetailView;
import com.wuhei.cms.model.Cmission;
import com.wuhei.cms.model.Cmreport;
import com.wuhei.cms.model.Cmstudent;

import com.wuhei.cms.service.cactivities.CmgroupService;


public class CmgroupServiceImpl implements CmgroupService {

	private CmgroupDAO cmgroupDAO;
	private CmgroupDetailViewDAO cmgroupDetailViewDAO;
	private CmstudentDetailViewDAO cmstudentDetailViewDAO;
	private CmstudentDAO cmstudentDAO;
	private CmissionDAO cmissionDAO;
	private List<CmgroupDetailView> cmgroupDetailViews;
	private List<CmstudentDetailView> cmstudentDetailViews;
	private  CmreportDAO cmreportDAO;
	

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<Cmgroup> getCmgrouplist(Integer cmissionid) {
		return cmgroupDAO.getCmgroupListByCondtion(null, cmissionid);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void newCmgroup(Cmgroup cmgroup, List<Integer> cmstudentidList) {
		cmgroupDAO.insertCmgroup(cmgroup);
		for (int i = 0; i < cmstudentidList.size(); i++) {
			Cmstudent cmstudent = new Cmstudent();
			cmstudent.setId(cmstudentidList.get(i));
			cmstudent.setIsgrouped(true);
			cmstudent.setIsinvolved(true);
			cmstudent.setCmgroupid(cmgroup.getId());
			cmstudentDAO.updateCmstudent(cmstudent);
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void newCmgroupByOne(Cmgroup cmgroup, Integer cmstudentid) {
		cmgroupDAO.insertCmgroup(cmgroup);
			Cmstudent cmstudent = new Cmstudent();
			cmstudent.setId(cmstudentid);
			cmstudent.setIsgrouped(true);
			cmstudent.setCmgroupid(cmgroup.getId());
			cmstudentDAO.updateCmstudent(cmstudent);
		
	}

	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CmstudentDetailView> getCmstudentDetailViewListByCmgroupid(
			Integer cmgroupid) {
		return cmstudentDetailViewDAO.getCmstudentDetailListByCondition(null,
				null, cmgroupid, null, null, null);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public CmgroupDetailView getCmgroupDetailView(Integer cmgroupid) {
		return cmgroupDetailViewDAO
				.getCmgroupDetailViewListByCmgroupid(cmgroupid);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<Cmstudent> getUngroupedStudent(Integer cmissionid) {
		return cmstudentDAO.getCmstudentListByCondition(false, cmissionid,
				null, null);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void addCmissionMember(List<Cmstudent> cmstudentList)
			throws AccessDeniedException {
		Iterator iterator = cmstudentList.iterator();
		Cmstudent cmstudent = ((Cmstudent) iterator.next());
		Cmission cmission = cmissionDAO.getCmission(cmstudent.getCmissionid());
		
		if (cmission.getIseditable() == false)
			throw new AccessDeniedException("该任务不可编辑");

		
		Cmgroup cmgroup = cmgroupDAO.getCmgroup(cmstudent.getCmgroupid());

		
		Integer count = cmgroup.getCount().intValue();
		count++;

		cmstudentDAO.updateCmstudent(cmstudent);

		if (cmgroup.getLeaderid() == null)
			cmgroup.setLeaderid(cmstudent.getId());
		while (iterator.hasNext()) {
			cmstudentDAO.updateCmstudent(((Cmstudent) iterator.next()));
			count++;
		}

		cmgroup.setCount(count.shortValue());
		cmgroupDAO.updateCmgroup(cmgroup);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteCmgroupMember(Integer cmstudentid)
			throws AccessDeniedException {

		
		Integer cmissionid = this.cmstudentDAO.getCmstudent(cmstudentid)
				.getCmissionid();
		if (!this.cmissionDAO.getCmission(cmissionid).getIseditable()) {
			throw new AccessDeniedException("该任务不可编辑");
		}

		Integer cmgroupid = cmstudentDAO.getCmstudent(cmstudentid)
				.getCmgroupid();
		Cmgroup cmgroup = cmgroupDAO.getCmgroup(cmgroupid);
		Cmstudent cmstudent = new Cmstudent();
		cmstudent.setId(cmstudentid);
		cmstudent.setIsgrouped(Boolean.FALSE);
		cmstudent.setIsinvolved(Boolean.FALSE);
		cmstudent.setCmgroupid(-1);
		cmstudentDAO.updateCmstudent(cmstudent);
		short count = (short) (cmgroup.getCount() - 1);
		cmgroup.setCount(count);
		cmgroupDAO.updateCmgroup(cmgroup);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void modifyCmgroup(Cmgroup cmgroup)
			throws AccessDeniedException {
	
		Cmission cmission = this.cmissionDAO.getCmission(this.cmgroupDAO.getCmgroup(cmgroup.getId()).getCmissionid());
		if (!cmission.getIseditable()) {
			throw new AccessDeniedException("该任务不可编辑");
		}
		cmgroupDAO.updateCmgroup(cmgroup);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteCmgroup(Integer id) throws AccessDeniedException {
		Integer cmissionid = this.cmgroupDAO.getCmgroup(id).getCmissionid();
		if (!this.cmissionDAO.getCmission(cmissionid).getIseditable()) {
			throw new AccessDeniedException("该任务不可编辑");
		}
		cmstudentDAO.updateCmstudentToUngroupedByCmgroupId(id);
		cmgroupDAO.deleteCmgroup(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void confirmCmgroup(List<Cmgroup> cmgroupList) {
		for (Iterator iterator1 = cmgroupList.iterator(); iterator1.hasNext();) {
			Cmgroup cmgroup = (Cmgroup) (iterator1.next());
			List<Cmstudent> cmstudentList = cmstudentDAO
					.getCmstudentListByCondition(null, null, cmgroup.getId(),
							null);
			for (Iterator iterator2 = cmstudentList.iterator(); iterator2
					.hasNext();) {
				Cmstudent cmstudent = (Cmstudent) (iterator2.next());
				cmstudent.setIsinvolved(cmgroup.getIsinvolved());
				cmstudentDAO.updateCmstudent(cmstudent);
			}
			cmgroupDAO.updateCmgroup(cmgroup);
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Cmgroup getCmgroupById(Integer cmgroupid) {
		return cmgroupDAO.getCmgroup(cmgroupid);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<Cmstudent> getCmgroup(Integer cmgroupid) {
		return cmstudentDAO.getCmstudentListByCondition(null, null, cmgroupid,
				null);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CmgroupDetailView> getNotinMCmgroupDetailViewListByCmissionid(
			Integer cmissionid) {
		return cmgroupDetailViewDAO
				.getNotinMCmgroupDetailViewListByCmissionid(cmissionid);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CmgroupDetailView> getinMCmgroupDetailViewListByCmissionid(
			Integer cmissionid) {
		return cmgroupDetailViewDAO.getCmgroupDetailViewListByCondition(
				Boolean.TRUE, cmissionid);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CmgroupDetailView> getCmgroupDetailViewListByCmissionid(
			Integer cmissionid) {
		
		return cmgroupDetailViewDAO.getCmgroupDetailViewListByCondition(null,cmissionid);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CmgroupDetailView> getCmgroupDetailViewListByCmissionidIsmarked(
			Integer cmissionid) {
		cmgroupDetailViews=cmgroupDetailViewDAO.getCmgroupDetailViewListByCondition(true,cmissionid);
		for(int i=0;i<cmgroupDetailViews.size();i++)
		{
			int ismarked=1;
			cmstudentDetailViews = getCmstudentDetailViewListByCmgroupid(cmgroupDetailViews.get(i).getId());
			for(int j=0;j<cmstudentDetailViews.size();j++)
			{
				if(cmstudentDetailViews.get(j).getIsmarked().equals(false))
				{	ismarked=0;
				    break;
				}
			}
			cmgroupDetailViews.get(i).setIsmarked(ismarked);
		}
		for(int i=0;i<cmgroupDetailViews.size();i++)
		{
			Cmreport cmreport=cmreportDAO.getCmreportByCondition(null,cmgroupDetailViews.get(i).getId(),null , cmissionid);
			if(cmreport==null)
			{
				cmgroupDetailViews.get(i).setIsSubmitChinese("否");
			}
			else{
				cmgroupDetailViews.get(i).setIsSubmitChinese("是");
			}
		}
		return cmgroupDetailViews;
	}
	
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void addCmgrouptoMission(List<Integer> cmgroupids)
			throws AccessDeniedException {
		
		Integer addCmstudentNumber=0;
		
		Integer cmissionid = this.cmgroupDAO.getCmgroup(cmgroupids.get(0))
				.getCmissionid();
		
		Cmission cmission=cmissionDAO.getCmission(cmissionid);
		
		if (!this.cmissionDAO.getCmission(cmissionid).getIseditable()) {
			throw new AccessDeniedException("该任务不可编辑");
		}
		for (Iterator iterator = cmgroupids.iterator(); iterator.hasNext();) {
			Cmgroup cmgroup = cmgroupDAO
					.getCmgroup(((Integer) iterator.next()));
			
			addCmstudentNumber+=cmgroup.getCount();
			
			
			List<Cmstudent> cmstudentList = cmstudentDAO
					.getCmstudentListByCondition(null, null, cmgroup.getId(),
							null);
			for (Iterator iterator1 = cmstudentList.iterator(); iterator1
					.hasNext();) {
				Cmstudent cmstudent = ((Cmstudent) (iterator1.next()));
				cmstudent.setIsinvolved(Boolean.TRUE);
				// cmstudent.setIsinvolved(true);
				cmstudentDAO.updateCmstudent(cmstudent);
			}
			cmgroup.setIsinvolved(Boolean.TRUE);
			cmgroupDAO.updateCmgroup(cmgroup);
		}
		
		short newScout=(short) (cmission.getScount()+addCmstudentNumber);
		
		cmission.setScount(newScout);
		
		cmissionDAO.updateCmission(cmission);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void removeCmgroupfromMission(List<Integer> cmgroupids)
			throws AccessDeniedException {
		
		Integer removeCmstudentNumber=0;
		
		Integer cmissionid = this.cmgroupDAO.getCmgroup(cmgroupids.get(0))
				.getCmissionid();
		
		Cmission cmission=cmissionDAO.getCmission(cmissionid);
		
		if (!this.cmissionDAO.getCmission(cmissionid).getIseditable()) {
			throw new AccessDeniedException("该任务不可编辑");
		}
		
		for (Iterator iterator = cmgroupids.iterator(); iterator.hasNext();) {
			Cmgroup cmgroup = cmgroupDAO
					.getCmgroup(((Integer) iterator.next()));
			removeCmstudentNumber+=cmgroup.getCount();
			
			List<Cmstudent> cmstudentList = cmstudentDAO
					.getCmstudentListByCondition(null, null, cmgroup.getId(),
							null);
			for (Iterator iterator1 = cmstudentList.iterator(); iterator1
					.hasNext();) {
				Cmstudent cmstudent = ((Cmstudent) (iterator1.next()));
				cmstudent.setIsinvolved(Boolean.FALSE);
				cmstudentDAO.updateCmstudent(cmstudent);
			}
			cmgroup.setIsinvolved(Boolean.FALSE);
			cmgroupDAO.updateCmgroup(cmgroup);
		}
		
		short newScout=(short) (cmission.getScount()-removeCmstudentNumber);
		cmission.setScount(newScout);
		cmissionDAO.updateCmission(cmission);
		
	
		

	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CmstudentDetailView> getUngroupedCmstudentDetailViewListByCmissionid(
			Integer cmissionid) {
		return this.cmstudentDetailViewDAO.listUnGroupedCmstudentDetailViewByCmissionid(cmissionid);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void addCmgroupMember(List<Integer> cmstudentids, Integer cmgroupid)
			throws AccessDeniedException {

		Cmgroup cmgroup = this.cmgroupDAO.getCmgroup(cmgroupid);

		if (!this.cmissionDAO.getCmission(cmgroup.getCmissionid())
				.getIseditable()) {
			throw new AccessDeniedException("该任务不可编辑");
		}

		for (int i = 0; i < cmstudentids.size(); i++) {
			Cmstudent cmstudent = this.cmstudentDAO.getCmstudent(cmstudentids
					.get(i));
			cmstudent.setCmgroupid(cmgroupid);
			cmstudent.setIsgrouped(true);
			cmstudent.setCmissionid(cmgroup.getCmissionid());
			cmstudent.setIsinvolved(cmgroup.getIsinvolved());

			this.cmstudentDAO.updateCmstudent(cmstudent);

		}

		Short count = cmgroup.getCount();
		count = (short) (count + cmstudentids.size());
		cmgroup.setCount(count);

		
		if (cmgroup.getLeaderid() == null) {
			cmgroup.setLeaderid(cmstudentids.get(0));
		}

		
		this.cmgroupDAO.updateCmgroup(cmgroup);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteCmgroupMembers(List<Integer> cmstudentids,
			Integer cmgroupid) throws AccessDeniedException {
		
		Cmgroup cmgroup = this.cmgroupDAO.getCmgroup(cmgroupid);

	
		if (!this.cmissionDAO.getCmission(cmgroup.getCmissionid())
				.getIseditable()) {
			throw new AccessDeniedException("该任务不可编辑");
		}

		for (int i = 0; i < cmstudentids.size(); i++) {
			
			Cmstudent cmstudent = this.cmstudentDAO.getCmstudent(cmstudentids
					.get(i));

			cmstudent.setIsgrouped(Boolean.FALSE);
			cmstudent.setIsinvolved(Boolean.FALSE);
			cmstudent.setCmgroupid(-1);
			cmstudentDAO.updateCmstudent(cmstudent);

		}

		
		Short count = cmgroup.getCount();
		count = (short) (count - cmstudentids.size());
		cmgroup.setCount(count);

		
		this.cmgroupDAO.updateCmgroup(cmgroup);

	}

	@Override
	public void deleteCmgroups(List<Integer> cmgroupids) {
		
		Integer cmissionid = this.cmgroupDAO.getCmgroup(cmgroupids.get(0)).getCmissionid();
		if (!this.cmissionDAO.getCmission(cmissionid).getIseditable()) {
			throw new AccessDeniedException("该任务不可编辑");
		}
		
		for (int i = 0; i < cmgroupids.size(); i++) {
			cmstudentDAO.updateCmstudentToUngroupedByCmgroupId(cmgroupids.get(i));
			this.deleteCmgroup(cmgroupids.get(i));
			
		}

	}
	
	public void addCmgroup (Cmgroup cmgroup)
	{
		cmgroupDAO.insertCmgroup(cmgroup);
	}

	

	/**
	 * ========================== getter and setter==========================
	 */
	public CmgroupDAO getCmgroupDAO() {
		return cmgroupDAO;
	}

	public void setCmgroupDAO(CmgroupDAO cmgroupDAO) {
		this.cmgroupDAO = cmgroupDAO;
	}

	public CmgroupDetailViewDAO getCmgroupDetailViewDAO() {
		return cmgroupDetailViewDAO;
	}

	public void setCmgroupDetailViewDAO(
			CmgroupDetailViewDAO cmgroupDetailViewDAO) {
		this.cmgroupDetailViewDAO = cmgroupDetailViewDAO;
	}

	public CmstudentDAO getCmstudentDAO() {
		return cmstudentDAO;
	}

	public void setCmstudentDAO(CmstudentDAO cmstudentDAO) {
		this.cmstudentDAO = cmstudentDAO;
	}

	public CmissionDAO getCmissionDAO() {
		return cmissionDAO;
	}

	public CmstudentDetailViewDAO getCmstudentDetailViewDAO() {
		return cmstudentDetailViewDAO;
	}

	public void setCmstudentDetailViewDAO(
			CmstudentDetailViewDAO cmstudentDetailViewDAO) {
		this.cmstudentDetailViewDAO = cmstudentDetailViewDAO;
	}

	public void setCmissionDAO(CmissionDAO cmissionDAO) {
		this.cmissionDAO = cmissionDAO;
	}

	public List<CmgroupDetailView> getCmgroupDetailViews() {
		return cmgroupDetailViews;
	}

	public void setCmgroupDetailViews(List<CmgroupDetailView> cmgroupDetailViews) {
		this.cmgroupDetailViews = cmgroupDetailViews;
	}
	
	public List<CmstudentDetailView> getCmstudentDetailViews() {
		return cmstudentDetailViews;
	}

	public void setCmstudentDetailViews(
			List<CmstudentDetailView> cmstudentDetailViews) {
		this.cmstudentDetailViews = cmstudentDetailViews;
	}

	public CmreportDAO getCmreportDAO() {
		return cmreportDAO;
	}

	public void setCmreportDAO(CmreportDAO cmreportDAO) {
		this.cmreportDAO = cmreportDAO;
	}
	
}