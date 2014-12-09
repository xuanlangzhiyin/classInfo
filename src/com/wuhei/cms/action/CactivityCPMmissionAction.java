package com.wuhei.cms.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.wuhei.cms.model.Cmstudent;
import com.wuhei.cms.model.joint.CmstudentDetailView;

import com.wuhei.cms.service.cactivities.CmgroupService;
import com.wuhei.cms.service.cactivities.CmissionService;
import com.wuhei.cms.service.cactivities.CmstudentDetailViewService;
import com.wuhei.cms.web.context.CmsWebContext;

/**
 * �γ̻���γ��������������
 * 
 * 
 */
@SuppressWarnings("serial")
public class CactivityCPMmissionAction extends CactivityCmissionAction {

	Logger logger = Logger.getLogger(CactivityCPMmissionAction.class);

	
	private List<Cmstudent> cmstudentList;

	private List<CmstudentDetailView> involvedCmstudentDetailViewList;

	private List<CmstudentDetailView> uninvolvedCmstudentDetailViewList;

	private CmgroupService cmgroupService;

	private CmissionService cmissionService;

	private CmstudentDetailViewService cmstudentDetailViewService;

	/**
	 * �����������������ҳ��
	 * 
	 * @return
	 */
	public String newPersonalCmission() {
		return SUCCESS;
	}

	/**
	 * ��д �������ơ�������Ȼ�����Ϣ��ȷ������һ���������� ǰ�˴��� cmission ���� courseid �ÿγ�id
	 * 
	 * @return ��ǰ�˷��� involvedCmstudentDetailViewList ����������ѧ����ϸ��Ϣ�б�
	 */
	public String addNewPersonalCmission() {
		try {
			cmission.setStype("��������");
			cmission.setTeacherid(CmsWebContext.getCurrentUser().getTeacherid());// ��ǰ��������Ľ�ʦid
			cmission.setCourseid(courseid);
			cmissionService.addCmission(cmission); // ������ӵ���С�����񣬸�������Ӧ����дһ���ӿ�
			courseid = cmission.getCourseid();
			involvedCmstudentDetailViewList = cmstudentDetailViewService
					.getCmstudentDetailListByCondition(null, courseid, null,
							cmission.getId(), true);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * ����ѧ���Ĳ���״̬ ǰ�˴��� cmstudentList ��Ҫ���ĵ�ѧ���б� ��������cmstudent.id, isinvolved
	 * courseid �γ�id cmissionid ����id
	 * 
	 * @return ��ǰ�˷��ز����ɹ���Ϣ
	 */
	public String updateStudentsIsinvolved() {
		try {
			cmissionService.update(cmstudentList);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * �����޸ĸ�������Ļ�����Ϣ�������������� ǰ�˴��� cmissionid
	 * 
	 * @return cmission���� ��ǰ�˷�������List<CmstudentDetailView> �ֱ��ǲ��������ѧ����δ���������ѧ��
	 *         courseid �γ�id
	 */
	public String viewPersonalCmission() {
		try {
			cmission = cmissionService.getCmission(cmissionid);
			involvedCmstudentDetailViewList = cmstudentDetailViewService
					.getCmstudentDetailListByCondition(null, null, null,
							cmissionid, true);
			uninvolvedCmstudentDetailViewList = cmstudentDetailViewService
					.getCmstudentDetailListByCondition(null, null, null,
							cmissionid, false);
			courseid = cmission.getCourseid();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * �޸ĸ�������Ļ�����Ϣ������ �������� �����ֹʱ�� ������ ����(δʵ��) ǰ�˴��� cmission ����
	 * 
	 * @return �����ɹ� ajaxResult
	 */
	public String updatePersonalCmission() {
		try {
			if (cmissionService.getCmission(cmission.getId()).getIseditable()) {
				cmissionService.modifyCmission(cmission);
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	// getter and setter


	public CmgroupService getCmgroupService() {
		return cmgroupService;
	}

	public void setCmgroupService(CmgroupService cmgroupService) {
		this.cmgroupService = cmgroupService;
	}

	public List<CmstudentDetailView> getInvolvedCmstudentDetailViewList() {
		return involvedCmstudentDetailViewList;
	}

	public List<CmstudentDetailView> getUninvolvedCmstudentDetailViewList() {
		return uninvolvedCmstudentDetailViewList;
	}

	public CmstudentDetailViewService getCmstudentDetailViewService() {
		return cmstudentDetailViewService;
	}

	public List<Cmstudent> getCmstudentList() {
		return cmstudentList;
	}

	public void setCmstudentList(List<Cmstudent> cmstudentList) {
		this.cmstudentList = cmstudentList;
	}

	public void setInvolvedCmstudentDetailViewList(
			List<CmstudentDetailView> involvedCmstudentDetailViewList) {
		this.involvedCmstudentDetailViewList = involvedCmstudentDetailViewList;
	}

	public void setUninvolvedCmstudentDetailViewList(
			List<CmstudentDetailView> uninvolvedCmstudentDetailViewList) {
		this.uninvolvedCmstudentDetailViewList = uninvolvedCmstudentDetailViewList;
	}

	public void setCmstudentDetailViewService(
			CmstudentDetailViewService cmstudentDetailViewService) {
		this.cmstudentDetailViewService = cmstudentDetailViewService;
	}

	
	public CmissionService getCmissionService() {
		return cmissionService;
	}

	public void setCmissionService(CmissionService cmissionService) {
		this.cmissionService = cmissionService;
	}

}
