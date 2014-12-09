package com.wuhei.cms.service.cactivities.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuhei.cms.dao.cactivities.CgroupDAO;
import com.wuhei.cms.dao.cactivities.CmcreditDAO;
import com.wuhei.cms.dao.cactivities.CmgroupDAO;
import com.wuhei.cms.dao.cactivities.CmissionDAO;
import com.wuhei.cms.dao.cactivities.CmissionListViewDAO;
import com.wuhei.cms.dao.cactivities.CmreportDAO;
import com.wuhei.cms.dao.cactivities.CmstudentDAO;
import com.wuhei.cms.dao.cactivities.CmstudentDetailViewDAO;
import com.wuhei.cms.dao.cactivities.CstudentDAO;
import com.wuhei.cms.exception.AccessDeniedException;
import com.wuhei.cms.model.Cgroup;
import com.wuhei.cms.model.Cmcredit;
import com.wuhei.cms.model.Cmgroup;
import com.wuhei.cms.model.Cmission;
import com.wuhei.cms.model.Cmreport;
import com.wuhei.cms.model.Cmstudent;
import com.wuhei.cms.model.Cstudent;
import com.wuhei.cms.model.joint.CmissionListView;
import com.wuhei.cms.service.cactivities.CmissionService;
import com.wuhei.cms.web.context.CmsWebContext;



public class CmissionServiceImpl implements CmissionService {
	private CmissionDAO cmissionDAO;

	private CmstudentDetailViewDAO cmstudentDetailViewDAO;
	private CmstudentDAO cmstudentDAO;
	private CmgroupDAO cmgroupDAO;
	private CstudentDAO cstudentDAO;
	private CgroupDAO cgroupDAO;
	private CmcreditDAO cmcreditDAO;

	private CmissionListViewDAO cmissionListViewDAO;
	private CmreportDAO cmreportDAO;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<Cmission> getCmissionList(Integer teacherid, Integer courseid) {
		return cmissionDAO.getCmissionListByCondition(teacherid, courseid);
	}

	public List<CmissionListView> getCmissionList4Teacher(Integer courseid) {
		
		List<CmissionListView> cmissionListView = cmissionListViewDAO
				.getCmissionListView4Teacher(courseid);
		
		for (int i = 0; i < cmissionListView.size(); i++) {
			Integer cmissionid = cmissionListView.get(i).getId();

		
			Integer reportedStudentNumber = cmreportDAO
					.countReportedStudent(cmissionid);

			cmissionListView.get(i).setReportedStudentNumber(
					reportedStudentNumber);
			if (cmissionListView.get(i).getStype().equalsIgnoreCase("按小组提交")) {
			 // 按小组提交时，页面显示的cmissionListView.get(i).scount:参与数量应该为小组的数量，而不是参与学生的总数
			 short cmgroupedNumber = Short.valueOf(cmgroupDAO
					.countCMgroupedStudent(cmissionid).toString());
			  cmissionListView.get(i).setScount(cmgroupedNumber);
			}
		}
		
		
		return cmissionListView;
	}

	public List<CmissionListView> getCmissionList4Student(Integer courseid,
			Integer cstudentId) {
		
		List<CmissionListView> cmissionListView = cmissionListViewDAO
				.getCmissionListView4Student(courseid, cstudentId);

		
		for (int i = 0; i < cmissionListView.size(); i++) {

			Integer cmissionid = cmissionListView.get(i).getId();
			
			List<Cmstudent> cmstudents = cmstudentDAO
					.getCmstudentListByCondition(null, cmissionid, null,
							cstudentId);

			// 填充已提交报告的数量
			Integer reportedStudentNumber = cmreportDAO
					.countReportedStudent(cmissionid);

			cmissionListView.get(i).setReportedStudentNumber(
					reportedStudentNumber);

			// 构造当前cmstudent决定是否显示学生页面的编辑按钮
			Cmstudent cmstudent = cmstudents.get(0);

		
			Cmission cmission = cmissionListView.get(i);

			if (cmission.getMtype().equalsIgnoreCase("小组任务")) {
				Integer cmgroupid = cmstudent.getCmgroupid();

				Cmgroup cmgroup = new Cmgroup();
				cmgroup = cmgroupDAO.getCmgroup(cmgroupid);

				List<Cmreport> cmreports = cmreportDAO
						.getCmreportListByCondition(true, cmgroupid, null,
								cmissionid);

				// 判断该学生是不是组长确认是否显示提交按钮
				if (cmission.getStype().equalsIgnoreCase("按小组提交")) {
					if (cmstudent.getId().equals(cmgroup.getLeaderid())) {
						cmissionListView.get(i).setIsSubmitionShowed(true);
					} else {
						cmissionListView.get(i).setIsSubmitionShowed(false);
					}

					 // 按小组提交时，页面显示的cmissionListView.get(i).scount:参与数量应该为小组的数量，而不是参与学生的总数
					short cmgroupedNumber = Short.valueOf(cmgroupDAO
							.countCMgroupedStudent(cmissionid).toString());
					  cmissionListView.get(i).setScount(cmgroupedNumber);
					
				} else if (cmission.getStype().equalsIgnoreCase("按个人提交")) {
					cmissionListView.get(i).setIsSubmitionShowed(true);
				}
				if (cmreports.isEmpty()) {
					cmissionListView.get(i).setIsSubmited("未提交");
				} else {
					if (cmission.getStype().equalsIgnoreCase("按个人提交")) {
						if (cmreports.size() < cmgroup.getCount()) {
							cmissionListView.get(i).setIsSubmited("部分提交");
						} else {
							cmissionListView.get(i).setIsSubmited("已提交");
						}
					} else if (cmission.getStype().equalsIgnoreCase("按小组提交")) {
						cmissionListView.get(i).setIsSubmited("已提交");
					}
				}
			} else if (cmission.getMtype().equalsIgnoreCase("个人任务")) {
				// 显示提交按钮
				cmissionListView.get(i).setIsSubmitionShowed(true);

				List<Cmreport> cmreports = cmreportDAO
						.getCmreportListByCondition(false, null, cstudentId,
								cmissionid);

				if (cmreports.isEmpty()) {
					cmissionListView.get(i).setIsSubmited("未提交");
				} else {
					cmissionListView.get(i).setIsSubmited("已提交");
				}
			}

		}
		return cmissionListView;
	}


	public List<Cmission> getUnmarkedCmissionListByCondition4Student(
			Integer cstudentId, Integer courseid) {
		return cmissionDAO.getUnmarkedCmissionListByCondition4Student(
				cstudentId, courseid);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void changeGroupToCourseGroup(Integer cmissionid, Integer courseid) {
		
		cmstudentDAO.deleteCmstudentByCmissionid(cmissionid);
		cmgroupDAO.deleteCmgroupByCmissionid(cmissionid);
	
		List<Cstudent> cstudentList = cstudentDAO.getCStudentListByCondition(
				null, courseid, null, null);
		
		List<Cgroup> cgroupList = cgroupDAO.getCgroupListByCondition(courseid);

		
		Map<Integer, Integer> cstudentidTocgroupid = new HashMap<Integer, Integer>();
		
		Map<Integer, Integer> cstudentidTocmstudentid = new HashMap<Integer, Integer>();
		
		Map<Integer, Integer> cmgroupidTocgroupLeaderId = new HashMap<Integer, Integer>();

		
		for (int i = 0; i < cstudentList.size(); i++) {
			Cstudent cstudent = cstudentList.get(i);
			Cmstudent cmstudent = new Cmstudent();
			cmstudent.setName(cstudent.getName());
			cmstudent.setIsgrouped(cstudent.getIsgrouped());
			cmstudent.setIsinvolved(true);
			cmstudent.setCstudentid(cstudent.getId());
			cmstudent.setCmissionid(cmissionid);
			cmstudentDAO.insertCmstudent(cmstudent);
			
			cstudentidTocgroupid.put(cstudent.getId(), cstudent.getCgroupid());
		
		}

		
		Map<Integer, Integer> cgroupidTocmgroupid = new HashMap<Integer, Integer>();
	
		Map<Integer, Integer> cmgroupidTocgroupid = new HashMap<Integer, Integer>();

		for (int i = 0; i < cgroupList.size(); i++) {
			Cgroup cgroup = cgroupList.get(i);
			Cmgroup cmgroup = new Cmgroup();
			cmgroup.setCode(cgroup.getCode());
			cmgroup.setName(cgroup.getName());
			cmgroup.setCount(cgroup.getCount());
			cmgroup.setIsinvolved(true);
			cmgroup.setCmissionid(cmissionid);
			cmgroupDAO.insertCmgroup(cmgroup);
		
			cgroupidTocmgroupid.put(cgroup.getId(), cmgroup.getId());
		
			cmgroupidTocgroupid.put(cmgroup.getId(), cgroup.getId());
		
			cmgroupidTocgroupLeaderId
					.put(cmgroup.getId(), cgroup.getLeaderid());
		}

		
		List<Cmstudent> cmstudentList = cmstudentDAO
				.getCmstudentListByCondition(null, cmissionid, null, null);

		
		for (int i = 0; i < cmstudentList.size(); i++) {
			Cmstudent cmstudent = cmstudentList.get(i);
			Integer cstudentid = cmstudent.getCstudentid();
			Integer cgroupid = cstudentidTocgroupid.get(cstudentid);
			Integer cmgroupid = cgroupidTocmgroupid.get(cgroupid);
			cmstudent.setCmgroupid(cmgroupid);
			cmstudentDAO.updateCmstudent(cmstudent);
		}

	
		List<Cmgroup> cmgroupList = cmgroupDAO.getCmgroupListByCondtion(null,
				cmissionid);

	
		for (int i = 0; i < cmgroupList.size(); i++) {
			Cmgroup cmgroup = cmgroupList.get(i);
			Integer cmgroupid = cmgroup.getId();
			Integer leaderIdForCgroup = cmgroupidTocgroupLeaderId
					.get(cmgroupid);
			Integer leaderIdForCmgroup = cstudentidTocmstudentid
					.get(leaderIdForCgroup);
			cmgroup.setLeaderid(leaderIdForCmgroup);
			cmgroupDAO.updateCmgroup(cmgroup);

		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void changeGroupToMissionGroup(Integer cmissionid,
			Integer wantedCmissionid) {

		
		cmstudentDAO.deleteCmstudentByCmissionid(cmissionid);
		cmgroupDAO.deleteCmgroupByCmissionid(cmissionid);
	
		List<Cmstudent> wCmstudents = cmstudentDAO.getCmstudentListByCondition(
				null, wantedCmissionid, null, null);

		
		Map<Integer, Integer> wcmstudentidTowcmgroupid = new HashMap<Integer, Integer>();
		Map<Integer, Integer> wcmstudentidToncmstudentid = new HashMap<Integer, Integer>();
		Map<Integer, Integer> ncmstudentidTowcmstudentid = new HashMap<Integer, Integer>();
	
		for (int i = 0; i < wCmstudents.size(); i++) {
			Cmstudent newCmstudent = wCmstudents.get(i);
			Integer oldCmstudentid = newCmstudent.getId();
			Integer oldCmgroupid = newCmstudent.getCmgroupid();
			
			newCmstudent.setId(null);
			newCmstudent.setCmissionid(cmissionid);
			cmstudentDAO.insertCmstudent(newCmstudent);
		
			wcmstudentidTowcmgroupid.put(oldCmstudentid, oldCmgroupid);
			wcmstudentidToncmstudentid
					.put(oldCmstudentid, newCmstudent.getId());
			ncmstudentidTowcmstudentid
					.put(newCmstudent.getId(), oldCmstudentid);
		}

		Map<Integer, Integer> wcmgroupidToncmgroupid = new HashMap<Integer, Integer>();
		Map<Integer, Integer> ncmgroupidTowcmstudentid = new HashMap<Integer, Integer>();
		Map<Integer, Integer> wcmgroupidTowcmstudentid = new HashMap<Integer, Integer>();

		
		List<Cmgroup> wCmgroups = cmgroupDAO.getCmgroupListByCondtion(null,
				wantedCmissionid);

		for (int i = 0; i < wCmgroups.size(); i++) {
			Cmgroup newCmgroup = wCmgroups.get(i);
			Integer oldCmstudentid = newCmgroup.getLeaderid();
			Integer oldCmgroupid = newCmgroup.getId();
			newCmgroup.setCmissionid(cmissionid);
			newCmgroup.setId(null);
			cmgroupDAO.insertCmgroup(newCmgroup);
		
			wcmgroupidToncmgroupid.put(oldCmgroupid, newCmgroup.getId());
			ncmgroupidTowcmstudentid.put(newCmgroup.getId(), oldCmstudentid);
			wcmgroupidTowcmstudentid.put(oldCmgroupid, oldCmstudentid);
		}

	

		Iterator iterator = ncmstudentidTowcmstudentid.entrySet().iterator();
		while (iterator.hasNext()) {
			Cmstudent cmstudent = new Cmstudent();
			Map.Entry entry = (Map.Entry) iterator.next();
			Integer cmstudentid = (Integer) entry.getKey();
			cmstudent.setId(cmstudentid);
			cmstudent.setCmgroupid(wcmgroupidToncmgroupid
					.get(wcmstudentidTowcmgroupid.get(entry.getValue())));
			cmstudentDAO.updateCmstudent(cmstudent);
		}

		

		Iterator iterator2 = ncmgroupidTowcmstudentid.entrySet().iterator();
		while (iterator2.hasNext()) {
			Cmgroup cmgroup = new Cmgroup();
			Map.Entry entry = (Map.Entry) iterator2.next();
			Integer cmgroupid = (Integer) entry.getKey();
			cmgroup.setId(cmgroupid);
			cmgroup.setLeaderid(wcmstudentidToncmstudentid.get(entry.getValue()));
			cmgroupDAO.updateCmgroup(cmgroup);
		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void addCmission(Cmission cmission) {
		
		cmissionDAO.insertCmission(cmission);
		Integer cmissionid = cmission.getId();

		
		Integer courseid = cmission.getCourseid();
		
		List<Cstudent> cstudentList = cstudentDAO.getCStudentListByCondition(
				null, courseid, null, null);
		
		List<Cgroup> cgroupList = cgroupDAO.getCgroupListByCondition(courseid);

	
		Map<Integer, Integer> cstudentidTocgroupid = new HashMap<Integer, Integer>();
		
		Map<Integer, Integer> cstudentidTocmstudentid = new HashMap<Integer, Integer>();
		
		Map<Integer, Integer> cmgroupidTocgroupLeaderId = new HashMap<Integer, Integer>();

		for (int i = 0; i < cstudentList.size(); i++) {
			Cstudent cstudent = cstudentList.get(i);
			Cmstudent cmstudent = new Cmstudent();
			cmstudent.setName(cstudent.getName());
			cmstudent.setIsgrouped(cstudent.getIsgrouped());
			cmstudent.setIsinvolved(cstudent.getIsgrouped());
			cmstudent.setCstudentid(cstudent.getId());
			cmstudent.setCmissionid(cmissionid);
			cmstudentDAO.insertCmstudent(cmstudent);
		
			cstudentidTocgroupid.put(cstudent.getId(), cstudent.getCgroupid());
		
			cstudentidTocmstudentid.put(cstudent.getId(), cmstudent.getId());
		}

		
		Map<Integer, Integer> cgroupidTocmgroupid = new HashMap<Integer, Integer>();
		
		Map<Integer, Integer> cmgroupidTocgroupid = new HashMap<Integer, Integer>();

	
		for (int i = 0; i < cgroupList.size(); i++) {
			Cgroup cgroup = cgroupList.get(i);
			Cmgroup cmgroup = new Cmgroup();
			cmgroup.setCode(cgroup.getCode());
			cmgroup.setName(cgroup.getName());
			cmgroup.setCount(cgroup.getCount());
			cmgroup.setIsinvolved(true);
			cmgroup.setCmissionid(cmissionid);
			cmgroupDAO.insertCmgroup(cmgroup);
		
			cgroupidTocmgroupid.put(cgroup.getId(), cmgroup.getId());
			
			cmgroupidTocgroupid.put(cmgroup.getId(), cgroup.getId());
			
			cmgroupidTocgroupLeaderId
					.put(cmgroup.getId(), cgroup.getLeaderid());
		}

		
		List<Cmstudent> cmstudentList = cmstudentDAO
				.getCmstudentListByCondition(null, cmissionid, null, null);

	    //为了更新cmgroupid这个字段
		for (int i = 0; i < cmstudentList.size(); i++) {
			Cmstudent cmstudent = cmstudentList.get(i);
			Integer cstudentid = cmstudent.getCstudentid();
			Integer cgroupid = cstudentidTocgroupid.get(cstudentid);
			Integer cmgroupid = cgroupidTocmgroupid.get(cgroupid);
			cmstudent.setCmgroupid(cmgroupid);
			cmstudentDAO.updateCmstudent(cmstudent);
		}

	
		List<Cmgroup> cmgroupList = cmgroupDAO.getCmgroupListByCondtion(null,
				cmissionid);

	
		short scount = 0;
         //为了更新leaderid这个字段
		for (int i = 0; i < cmgroupList.size(); i++) {
			Cmgroup cmgroup = cmgroupList.get(i);
			Integer cmgroupid = cmgroup.getId();
			Integer leaderIdForCgroup = cmgroupidTocgroupLeaderId
					.get(cmgroupid);
			Integer leaderIdForCmgroup = cstudentidTocmstudentid
					.get(leaderIdForCgroup);
			cmgroup.setLeaderid(leaderIdForCmgroup);
			cmgroupDAO.updateCmgroup(cmgroup);

			scount += cmgroup.getCount();

		}
		cmission.setScount(scount);
		cmissionDAO.updateCmission(cmission);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void addCmission4person(Cmission cmission) {
		
		cmissionDAO.insertCmission(cmission);
		Integer cmissionid = cmission.getId();

		
		Integer courseid = cmission.getCourseid();
	
		List<Cstudent> cstudentList = cstudentDAO.getCStudentListByCondition(
				null, courseid, null, null);

		
		for (int i = 0; i < cstudentList.size(); i++) {
			Cstudent cstudent = cstudentList.get(i);
			Cmstudent cmstudent = new Cmstudent();
			cmstudent.setName(cstudent.getName());
			cmstudent.setIsgrouped(false);
			cmstudent.setIsinvolved(true);
			cmstudent.setCstudentid(cstudent.getId());
			cmstudent.setCmissionid(cmissionid);
			cmstudentDAO.insertCmstudent(cmstudent);

		}

	
		short scount = (short) cstudentList.size();
		cmission.setScount(scount);
		cmissionDAO.updateCmission(cmission);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteCmission(Integer id) throws AccessDeniedException {
		if (!cmissionDAO.getCmission(id).getIsdeletable()) {
			throw new AccessDeniedException("该任务不能删除");
		}
		cmissionDAO.deleteCmission(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void modifyCmission(Cmission cmission) {
		if (cmissionDAO.getCmission(cmission.getId()).getIseditable()) {
			cmissionDAO.updateCmission(cmission);
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<Cmission> stuGetCmissionList(Integer studentid) {
		List<Cmission> cmissionList = new ArrayList<Cmission>(100);// TODO
																	
		int count = cmstudentDAO.getCmstudentListByCondition(null, null, null,
				studentid).size();
		for (int i = 0; i < count; i++) {
			Integer cmissionid = cmstudentDAO
					.getCmstudentListByCondition(null, null, null, studentid)
					.get(i).getCmissionid();
			cmissionList.add(cmissionDAO.getCmission(cmissionid));
		}
		return cmissionList;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Cmission getCmission(Integer cmissionid) {
		return cmissionDAO.getCmission(cmissionid);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void update(List<Cmstudent> cmstudentList) {
	
		short changeCount = (short) cmstudentList.size();
		
		Cmission cmission = cmissionDAO.getCmission(cmstudentList.get(0)
				.getCmissionid());
		short scount = cmission.getScount();
		for (int i = 0; i < cmstudentList.size(); i++) {
			cmstudentDAO.updateCmstudent(cmstudentList.get(i));
		}
		if (cmstudentList.get(0).getIsinvolved()) {
			scount += changeCount;
			cmission.setScount(scount);
		} else {
			scount -= changeCount;
			cmission.setScount(scount);
		}
		cmissionDAO.updateCmission(cmission);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Cstudent getCStudentByCondition(Integer studentid, Integer courseid) {
		List<Cstudent> cstudentList = cstudentDAO.getCStudentListByCondition(
				null, courseid, null, studentid);
		if (cstudentList.size() > 0) {
			return cstudentList.get(0);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CmissionListView> getCmissionListViewByCondition(
			Integer courseid, String mtype) {
		return cmissionListViewDAO.getCmissionListViewByCondition(courseid,
				mtype);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateCmission(Cmission cmission) {
		cmissionDAO.updateCmission(cmission);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void savePersonalMissionScore(Cmstudent cmstudent, Cmcredit cmcredit) {
		try {
			Cmstudent tempCmstudent = new Cmstudent();
			tempCmstudent = cmstudentDAO.getCmstudent(cmstudent.getId());
			if (tempCmstudent.getIsmarked().equals(false)) {
				cmcreditDAO.insertCmcredit(cmcredit);

				cmstudent.setIsmarked(true);
				cmstudentDAO.updateCmstudent(cmstudent);
			} else {
				List<Cmcredit> cmcreditList = cmcreditDAO
						.getCmcreditListByCondition(cmcredit.getCstudentid(),
								cmcredit.getCmissionid());
				cmcredit.setId(cmcreditList.get(0).getId());
				cmcreditDAO.updateCmcredit(cmcredit);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}


	
	public CmissionDAO getCmissionDAO() {
		return cmissionDAO;
	}

	public void setCmissionDAO(CmissionDAO cmissionDAO) {
		this.cmissionDAO = cmissionDAO;
	}

	public CmstudentDetailViewDAO getCmstudentDetailViewDAO() {
		return cmstudentDetailViewDAO;
	}

	public void setCmstudentDetailViewDAO(
			CmstudentDetailViewDAO cmstudentDetailViewDAO) {
		this.cmstudentDetailViewDAO = cmstudentDetailViewDAO;
	}

	public CmstudentDAO getCmstudentDAO() {
		return cmstudentDAO;
	}

	public void setCmstudentDAO(CmstudentDAO cmstudentDAO) {
		this.cmstudentDAO = cmstudentDAO;
	}

	public CmgroupDAO getCmgroupDAO() {
		return cmgroupDAO;
	}

	public void setCmgroupDAO(CmgroupDAO cmgroupDAO) {
		this.cmgroupDAO = cmgroupDAO;
	}

	public CstudentDAO getCstudentDAO() {
		return cstudentDAO;
	}

	public void setCstudentDAO(CstudentDAO cstudentDAO) {
		this.cstudentDAO = cstudentDAO;
	}

	public CgroupDAO getCgroupDAO() {
		return cgroupDAO;
	}

	public void setCgroupDAO(CgroupDAO cgroupDAO) {
		this.cgroupDAO = cgroupDAO;
	}

	public CmissionListViewDAO getCmissionListViewDAO() {
		return cmissionListViewDAO;
	}

	public void setCmissionListViewDAO(CmissionListViewDAO cmissionListViewDAO) {
		this.cmissionListViewDAO = cmissionListViewDAO;
	}

	public CmreportDAO getCmreportDAO() {
		return cmreportDAO;
	}

	public void setCmreportDAO(CmreportDAO cmreportDAO) {
		this.cmreportDAO = cmreportDAO;
	}

	public CmcreditDAO getCmcreditDAO() {
		return cmcreditDAO;
	}

	public void setCmcreditDAO(CmcreditDAO cmcreditDAO) {
		this.cmcreditDAO = cmcreditDAO;
	}

}
