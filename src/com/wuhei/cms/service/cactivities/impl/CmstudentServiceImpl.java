package com.wuhei.cms.service.cactivities.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuhei.cms.dao.cactivities.CmstudentDAO;
import com.wuhei.cms.dao.cactivities.CmstudentDetailViewDAO;
import com.wuhei.cms.exception.AccessDeniedException;
import com.wuhei.cms.model.Cmstudent;
import com.wuhei.cms.model.joint.CmstudentDetailView;
import com.wuhei.cms.service.cactivities.CmstudentService;
import com.wuhei.cms.web.context.CmsWebContext;

public class CmstudentServiceImpl implements CmstudentService {

	private CmstudentDAO cmstudentDAO;
	private CmstudentDetailViewDAO cmstudentDetailViewDAO;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Cmstudent getCmstudentById(Integer id) {
		return cmstudentDAO.getCmstudent(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public CmstudentDetailView getCmstudentDetailViewByIdAndCmissionId(
			Integer cmstudentid) {
		return cmstudentDetailViewDAO.getCmstudentDetailViewById(
				cmstudentid);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<Cmstudent> getCmstudentListByCmgroupid(Integer cmgroupid) {
		return this.cmstudentDAO.getCmstudentListByCondition(true, null, cmgroupid, null);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Cmstudent getCmstudentByCondition(Integer cstudentid,
			Integer cmissionid) throws AccessDeniedException {
		List<Cmstudent> cmstudentList = cmstudentDAO
				.getCmstudentListByCondition(null, cmissionid, null, cstudentid);
		if (cmstudentList.size() > 1) {
			throw new AccessDeniedException("任务学生不唯一，请联系管理员");
		}
		if (cmstudentList.size() == 1)
			return cmstudentList.get(0);
		else
			return null;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<Cmstudent> getCmstudentListByCmissionid(Integer cmissionid)
	{
		return cmstudentDAO.getCmstudentListByCondition(null, cmissionid, null, null);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void addCmstudent(Cmstudent cmstudent)
	{
		cmstudentDAO.insertCmstudent(cmstudent);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateCmstudent(Cmstudent cmstudent)
	{
		cmstudentDAO.updateCmstudent(cmstudent);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<Cmstudent> getCmstudentListByCmissionidAndIsinvolved(Integer cmissionid,Boolean isinvolved)
	{
		return cmstudentDAO.getCmstudentListByCmissionidAndIsinvolved(cmissionid,isinvolved);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Boolean isStudentAuthorizedOnCmission(Integer cmissionid){
		Boolean isAuthorized = false;
		
		Integer studentid = CmsWebContext.getCurrentUser().getStudentid();

		List<Integer> studentids = cmstudentDAO.getStudentidsByCmissionid(cmissionid);
		
		if(studentids==null||studentids.isEmpty()){
			return isAuthorized;
		}
		
		for(int i=0;i<studentids.size();i++){
			if(studentid.equals(studentids.get(i))){
				isAuthorized = true;
				return isAuthorized;
			}
		}	
		return isAuthorized;
	}
	
	public CmstudentDAO getCmstudentDAO() {
		return cmstudentDAO;
	}

	public void setCmstudentDAO(CmstudentDAO cmstudentDAO) {
		this.cmstudentDAO = cmstudentDAO;
	}

	public CmstudentDetailViewDAO getCmstudentDetailViewDAO() {
		return cmstudentDetailViewDAO;
	}

	public void setCmstudentDetailViewDAO(
			CmstudentDetailViewDAO cmstudentDetailViewDAO) {
		this.cmstudentDetailViewDAO = cmstudentDetailViewDAO;
	}

}