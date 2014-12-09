package com.wuhei.cms.service.cactivities.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuhei.cms.dao.cactivities.CgroupDAO;
import com.wuhei.cms.dao.cactivities.CgroupDetailViewDAO;
import com.wuhei.cms.dao.cactivities.CstudentDAO;
import com.wuhei.cms.dao.cactivities.CstudentDetailViewDAO;
import com.wuhei.cms.model.Cgroup;
import com.wuhei.cms.model.Cstudent;
import com.wuhei.cms.model.joint.CgroupDetailView;
import com.wuhei.cms.model.joint.CstudentDetailView;
import com.wuhei.cms.service.cactivities.CgroupService;


public class CgroupServiceImpl implements CgroupService {

	private CgroupDetailViewDAO cgroupDetailViewDAO;
	private CgroupDAO cgroupDAO;
	private CstudentDAO cstudentDAO;
	private CstudentDetailViewDAO cstudentDetailViewDAO;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CgroupDetailView> getCgroupDetailViewListByCourseid(
			Integer courseid) {
		return cgroupDetailViewDAO.getCgroupDetailListByCondition(courseid);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void newCgroup(Cgroup cgroup, List<Integer> cstudentidList) {
		cgroupDAO.insertCgroup(cgroup);
		for (int i = 0; i < cstudentidList.size(); i++) {
			Cstudent cstudent = new Cstudent();
			cstudent.setId(cstudentidList.get(i));
			cstudent.setIsgrouped(true);
			cstudent.setCgroupid(cgroup.getId());
			cstudentDAO.updateCstudent(cstudent);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void newCgroupByOne(Cgroup cgroup,Integer cstudentid) {
		cgroupDAO.insertCgroup(cgroup);
		
			Cstudent cstudent = new Cstudent();
			cstudent.setId(cstudentid);
			cstudent.setIsgrouped(true);
			cstudent.setCgroupid(cgroup.getId());
			cstudentDAO.updateCstudent(cstudent);
		
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CstudentDetailView> getCstudentDetailViewListByCgroupid(
			Integer cgroupid) {
		return cstudentDetailViewDAO.listStudentDetailByCgroupid(cgroupid);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public CgroupDetailView getCgroupDetailViewByCgroupid(Integer cgroupid) {
		return cgroupDetailViewDAO.getCgroupDetailView(cgroupid);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CstudentDetailView> getUngroupedCstudentDetailViewListByCourseid(
			Integer courseid) {
		return cstudentDetailViewDAO
				.listUnGroupedCstudentDetailViewByCourseid(courseid);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void addCgroupMember(List<Integer> cstudentidList, Integer cgroupid) {
		for (int i = 0; i < cstudentidList.size(); i++) {
			Cstudent cstudent = new Cstudent();
			cstudent.setId(cstudentidList.get(i));
			cstudent.setIsgrouped(true);
			cstudent.setCgroupid(cgroupid);
			cstudentDAO.updateCstudent(cstudent);
		}
		short count = (short) (cstudentidList.size() + cgroupDAO.getCgroup(
				cgroupid).getCount());
		Cgroup cgroup = new Cgroup();
		cgroup.setId(cgroupid);
		if (cgroupDAO.getCgroup(cgroupid).getLeaderid() == null
				&& cstudentidList.size() != 0) {
			cgroup.setLeaderid(cstudentidList.get(0));
		}
		cgroup.setCount(count);
		cgroupDAO.updateCgroup(cgroup);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteCgroupMembers(List<Integer> cstudentidList,
			Integer cgroupid) {
		Cgroup cgroupFromDateBase = cgroupDAO.getCgroup(cgroupid);
		for (int i = 0; i < cstudentidList.size(); i++) {
			if (cstudentidList.get(i).equals(cgroupFromDateBase.getLeaderid()) ) {
				System.out.println("×é³¤");
				return; 
			}
		}
		for (int i = 0; i < cstudentidList.size(); i++) {
			Cstudent cstudent = new Cstudent();
			cstudent.setId(cstudentidList.get(i));
			cstudent.setIsgrouped(false);
			cstudent.setCgroupid(-1);
			cstudentDAO.updateCstudent(cstudent);
		}
		short count = (short) (cgroupDAO.getCgroup(cgroupid).getCount() - cstudentidList
				.size());
		Cgroup cgroup = new Cgroup();
		cgroup.setId(cgroupid);
		cgroup.setCount(count);
		cgroupDAO.updateCgroup(cgroup);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteCgroupMember(Integer cstudentid,
			Integer cgroupid) {
			Cstudent cstudent = new Cstudent();
			cstudent.setId(cstudentid);
			cstudent.setIsgrouped(false);
			cstudent.setCgroupid(-1);
			cstudentDAO.updateCstudent(cstudent);
		
		short count = (short) (cgroupDAO.getCgroup(cgroupid).getCount()-1);
		Cgroup cgroup = new Cgroup();
		cgroup.setId(cgroupid);
		cgroup.setCount(count);
		cgroupDAO.updateCgroup(cgroup);

	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CstudentDetailView> getCstudentDetailViewListByCourseid(
			Integer courseid) {
		return cstudentDetailViewDAO.listCstudentDetailViewByCourseid(courseid);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void modifyCgroupName(Integer id, String name) {
		Cgroup cgroup = new Cgroup();
		cgroup.setId(id);
		cgroup.setName(name);
		cgroupDAO.updateCgroup(cgroup);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void modifyCgroupLeader(Integer cgroupid, Integer leaderid) {
		Cgroup cgroup = new Cgroup();
		cgroup.setId(cgroupid);
		cgroup.setLeaderid(leaderid);
		cgroupDAO.updateCgroup(cgroup);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteCgroup(Integer id) {
		cstudentDAO.updateCstudentToUngroupedByCgroupId(id);
		cgroupDAO.deleteCgroup(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteCgroups(List<Integer> idList) {
		for (int i = 0; i < idList.size(); i++) {
			Integer id = idList.get(i);
			cstudentDAO.updateCstudentToUngroupedByCgroupId(id);
			cgroupDAO.deleteCgroup(id);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void autoGrouping(Integer courseid) {
		List<Cstudent> cstudenList = cstudentDAO.getCStudentListByCondition(
				null, courseid, null, null);
		for (int i = 0; i < cstudenList.size(); i++) {
			Cstudent cstudent = cstudenList.get(i);
			Cgroup cgroup = new Cgroup();
			cgroup.setName(cstudent.getName() + "ï¿½ï¿½");
			cgroup.setCount((short) 1);
			cgroup.setLeaderid(cstudent.getId());
			cgroup.setCourseid(courseid);
			cgroupDAO.insertCgroup(cgroup);
			cstudent.setIsgrouped(true);
			cstudent.setCgroupid(cgroup.getId());
			cstudentDAO.updateCstudent(cstudent);
		}
	}

	/**
	 * getter and setter
	 * 
	 * @return
	 */
	public CgroupDetailViewDAO getCgroupDetailViewDAO() {
		return cgroupDetailViewDAO;
	}

	public void setCgroupDetailViewDAO(CgroupDetailViewDAO cgroupDetailViewDAO) {
		this.cgroupDetailViewDAO = cgroupDetailViewDAO;
	}

	public CgroupDAO getCgroupDAO() {
		return cgroupDAO;
	}

	public void setCgroupDAO(CgroupDAO cgroupDAO) {
		this.cgroupDAO = cgroupDAO;
	}

	public CstudentDAO getCstudentDAO() {
		return cstudentDAO;
	}

	public void setCstudentDAO(CstudentDAO cstudentDAO) {
		this.cstudentDAO = cstudentDAO;
	}

	public CstudentDetailViewDAO getCstudentDetailViewDAO() {
		return cstudentDetailViewDAO;
	}

	public void setCstudentDetailViewDAO(
			CstudentDetailViewDAO cstudentDetailViewDAO) {
		this.cstudentDetailViewDAO = cstudentDetailViewDAO;
	}

}