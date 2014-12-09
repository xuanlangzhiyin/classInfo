package com.wuhei.cms.service.cactivities.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuhei.cms.dao.cactivities.CmissionDAO;
import com.wuhei.cms.dao.cactivities.CmreportDAO;
import com.wuhei.cms.exception.AccessDeniedException;
import com.wuhei.cms.exception.FileProcessException;
import com.wuhei.cms.model.Cmreport;
import com.wuhei.cms.service.cactivities.CmreportService;
import com.wuhei.cms.web.context.CmsWebContext;


public class CmreportServiceImpl implements CmreportService {

	private CmreportDAO cmreportDAO;
	private CmissionDAO cmissionDAO;


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Cmreport getCmreport(Integer id) throws FileProcessException {
		// TODO
		
		return cmreportDAO.getCmreport(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Cmreport getCmreportByCondition(Boolean isgroup,
			Integer cstudentid, Integer cmgroupid, Integer cmissionid) {
		return cmreportDAO.getCmreportByCondition(isgroup, cmgroupid,
				cstudentid, cmissionid);
	}

	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteCmreport(Integer id) {
		cmreportDAO.deleteCmreport(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void submitCmreport(Cmreport cmreport) throws AccessDeniedException {

		// �ж������Ƿ񼤻�
		if (this.cmissionDAO.getCmission(cmreport.getCmissionid())
				.getIsactive().equals("���ύ")) {
			throw new AccessDeniedException("��������Ѿ��رգ������ύ������");
		}

		// ����ǰ�С���ύ������
		if (cmissionDAO.getCmission(cmreport.getCmissionid()).getStype()
				.equals("�������ύ")) {
			// ��������Ѿ��������񱨸棬���޸����񱨸棬����������񱨸�
			if (cmreportDAO.getCmreportByCondition(null,
					cmreport.getCmgroupid(), null, null) != null) {
				cmreportDAO.updateCmreport(cmreport);
			} else
				cmreportDAO.insertCmreport(cmreport);
		}

		// ����ǰ������ύ������
		else {
			// ��������Ѿ��������񱨸棬���޸����񱨸棬����������񱨸�
			if (cmreportDAO.getCmreportByCondition(null,
					cmreport.getCmgroupid(), cmreport.getCstudentid(), null)
					!= null) {
				cmreportDAO.updateCmreport(cmreport);
			} else
				cmreportDAO.insertCmreport(cmreport);
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Cmreport stuGetCmreport(Integer id) {
		return cmreportDAO.getCmreport(id);
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateCmreport(Cmreport cmreport) {

		this.cmreportDAO.updateCmreport(cmreport);
	}

	

	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertCmreport(Cmreport cmreport) {
		cmreportDAO.insertCmreport(cmreport);
	}

	/**
	 * �жϸñ����ѧ���Ƿ�Ϊ��ǰѧ��
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Boolean isStudentAuthorizedOnCmreport(Integer cmreportid) {
		Boolean isAuthorized = false;

		Integer realstudentid = CmsWebContext.getCurrentUser().getStudentid();

		Integer studentid = cmreportDAO.getStudentidByCMreportid(cmreportid);

		if (studentid != null && studentid.equals(realstudentid)) {
			isAuthorized = true;
			return isAuthorized;
		}
		return isAuthorized;
	}

	// getter&setter

	public CmreportDAO getCmreportDAO() {
		return cmreportDAO;
	}

	public void setCmreportDAO(CmreportDAO cmreportDAO) {
		this.cmreportDAO = cmreportDAO;
	}

	public CmissionDAO getCmissionDAO() {
		return cmissionDAO;
	}

	public void setCmissionDAO(CmissionDAO cmissionDAO) {
		this.cmissionDAO = cmissionDAO;
	}

}
