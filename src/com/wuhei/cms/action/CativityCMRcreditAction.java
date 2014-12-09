package com.wuhei.cms.action;

import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.wuhei.cms.model.joint.CmcreditDetailView;
import com.wuhei.cms.model.joint.CmgroupDetailView;

import com.wuhei.cms.model.Cmcredit;
import com.wuhei.cms.model.Cmission;
import com.wuhei.cms.model.Cmreport;
import com.wuhei.cms.model.Cmstudent;

import com.wuhei.cms.model.joint.CmstudentDetailView;

import com.wuhei.cms.service.cactivities.CmcreditService;
import com.wuhei.cms.service.cactivities.CmgroupService;
import com.wuhei.cms.service.cactivities.CmissionService;
import com.wuhei.cms.service.cactivities.CmstudentService;

import com.wuhei.cms.service.cactivities.CmreportService;

import com.wuhei.cms.service.cactivities.CmstudentDetailViewService;
import com.wuhei.cms.web.context.CmsWebContext;

/**
 * �γ̻���γ��������������
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class CativityCMRcreditAction extends CactivityCmissionAction {

	Logger logger = Logger.getLogger(CativityCMRcreditAction.class);

	// �γ����������С����
	private Integer cmgroupid;

	private Cmission cmission;

	private CmstudentDetailView cmgroupleader;

	private CmcreditDetailView cmcredit4leader;

	// �γ��������ѧ��List
	private List<CmstudentDetailView> cmstudentListDetailView;

	private List<CmcreditDetailView> cmcreditListDetailView;

	private List<Byte> cmScoreList;

	private List<Integer> cmStudentIDList;

	private List<String> cmDescriptionList;

	private Cmreport cmreport;

	private CmstudentDetailViewService cmstudentDetailViewService;

	private CmreportService cmreportService;

	private CmcreditService cmcreditService;

	private CmgroupService cmgroupService;

	private CmgroupDetailView cmgroupDetailView;

	private CmcreditDetailView cmcreditDetailView;

	private CmstudentService cmstudentService;

	private CmissionService cmissionService;
	/**
	 * 
	 * �γ����������Ϣ�б�
	 */
	private List<CmgroupDetailView> cmgroupDetailViews;

	/**
	 * �г�һ�������������г�Ա��Ϣ
	 */
	private List<CmstudentDetailView> cmstudentDetailViews;

	/**
	 * ��������ѧ����Ϣ
	 */
	private CmstudentDetailView cpersonalmstudentDetailView;

	/**
	 * ���ڻ�ȡ��������ѧ����ismarked���ж��Ƿ��Ѿ�ȫ������
	 */
	private List<Cmstudent> cmstudentList;

	private List<Cmcredit> cmcreditList;

	/**
	 * �γ������鳤id
	 */
	private Integer leaderid;

	private Integer cmstudentid;

	private String description;

	private Cmcredit cmcredit;

	private Cmcredit newcmcredit;

	private Cmstudent cmstudent;

	/**
	 * �Բ��뵽�γ����������С��ĵ���С������г�Ա����
	 * 
	 * @return
	 */
	public String evaluateGroupMission() {
		try {

			// ��ȡ��ǰ��¼ѧ��id
			studentid = CmsWebContext.getCurrentUser().getStudentid();
			// ����ǰ�˴�����cmissionid ��ȡcmission����
			cmission = cmissionService.getCmission(cmissionid);
			// �ó�cmission�����courseid
			courseid = cmission.getCourseid();
			// ����studentid��courseid���õ�Ψһ��cstudent����Ҳ���Ǹõ�½ѧ�������ſ���γ��е�cstudent����
			cstudent = cmissionService.getCStudentByCondition(studentid,
					courseid);
			// ��ȡcstudentid
			cstudentid = cstudent.getId();
			// ��ȡcmission��Ϣ
			cmission = cmissionService.getCmission(cmissionid);

			if (cmission.getStype().equalsIgnoreCase("�������ύ")) {
				cmreport = cmreportService.getCmreportByCondition(true,
						cstudentid, cmgroupid, cmissionid);
			}

			else {
				// ��ȡС�����ύ�ı���
				cmreport = cmreportService.getCmreportByCondition(true,
						null, cmgroupid, cmissionid);
			}

			// ��ȡС���Ա
			cmstudentListDetailView = cmgroupService
					.getCmstudentDetailViewListByCmgroupid(cmgroupid);
			
			// ����ǰ̨������cmgroupid��ȡС����ϸ��Ϣ
			cmgroupDetailView = cmgroupService.getCmgroupDetailView(cmgroupid);

			// ����ǰ̨������cmgroupid��ȡ������ϸ����
			cmcreditListDetailView = cmcreditService
					.getCmcreditDetailViewsByCmgroupid(cmgroupid);

			for (int i = 0; i < cmstudentListDetailView.size(); i++) {

				if (cmgroupDetailView.getLeaderid().equals(
						cmstudentListDetailView.get(i).getId())) {
					cmgroupleader = cmstudentListDetailView.get(i);
					cmstudentListDetailView.remove(i);

					cmcredit4leader = cmcreditListDetailView.get(i);
					cmcreditListDetailView.remove(i);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * ��С����������ύ�����в��������ѧ���ĵ���ѧ����������
	 * 
	 * @return
	 */
	public String evaluateGroupMission4personal() {
		try {

			cmission = cmissionService.getCmission(cmissionid);

			// ��ȡ�������ύ�ı���id
			cmreport = cmreportService
					.getCmreportByCondition(false, cstudentid, null,
							cmissionid);

			cpersonalmstudentDetailView = cmstudentService
					.getCmstudentDetailViewByIdAndCmissionId(cmstudentid);
			Cmstudent tempCmstudent = new Cmstudent();
			tempCmstudent = cmstudentService.getCmstudentById(cmstudentid);

			if (tempCmstudent.getIsmarked().equals(true)) {
				cmcredit = cmcreditService.getCmcreditDetailViewByCondition(
						cstudentid, cmissionid);
			} else
				cmcredit.setDescription("");

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;

	}
	
	/**
	 * �Ը�����������в��������ѧ���ĵ���ѧ����������
	 * 
	 * @return
	 */
	public String evaluatePersonalMission() {
		try {

			cmission = cmissionService.getCmission(cmissionid);

			// ��ȡ�������ύ�ı���id
			cmreport = cmreportService
					.getCmreportByCondition(false, cstudentid, null,
							cmissionid);

			cpersonalmstudentDetailView = cmstudentService
					.getCmstudentDetailViewByIdAndCmissionId(cmstudentid);
			Cmstudent tempCmstudent = new Cmstudent();
			tempCmstudent = cmstudentService.getCmstudentById(cmstudentid);

			if (tempCmstudent.getIsmarked().equals(true)) {
				cmcredit = cmcreditService.getCmcreditDetailViewByCondition(
						cstudentid, cmissionid);
			} else
				cmcredit.setDescription("");

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;

	}

	/**
	 * �鿴����С�����������С���������
	 */
	public String viewGroupMissionScore() {
		try {
			cmgroupDetailViews = cmgroupService
					.getCmgroupDetailViewListByCmissionidIsmarked(cmissionid);

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * �г�ĳһ�������������г�Ա����ϸ��Ϣ
	 * 
	 * ����cmgroupid
	 * 
	 * ���ظ����ѧ��List<CmstudentDetialView> byzhuchengrong
	 * 
	 * @return
	 */
	public String getCmgroupMemberDetialScore() {
		try {
			cmstudentDetailViews=cmstudentDetailViewService
			.getCmstudentDetailListByCondition(null, null, cmgroupid,
					cmissionid, null);
			CmgroupDetailView tempDetailView = cmgroupService
					.getCmgroupDetailView(cmgroupid);
			leaderid = tempDetailView.getLeaderid();
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * �鿴����������������ѧ���������
	 */
	public String viewPersonalMissionScore() {
		try {
			cmstudentListDetailView = cmstudentDetailViewService
					.getCmstudentDetailListByCondition(null, courseid, null,
							cmissionid, true);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * �ύС������С���Ա���� author:GWB
	 * 
	 * @return
	 */
	public String saveGroupScore() {
		try {
			// ����ǰ̨������courseid,cmgroupid, cmissionid��ȡcmstudentDetailView�����б���ʾ����ϸ��Ϣ
			cmstudentListDetailView = cmstudentDetailViewService
					.getCmstudentDetailListByCondition(null, courseid,
							cmgroupid, cmissionid, true);			
			
			// ����ǰ̨������cmgroupid��ȡС����ϸ��Ϣ
			cmgroupDetailView = cmgroupService.getCmgroupDetailView(cmgroupid);

			Cmcredit cmcredit = new Cmcredit();

			Cmstudent tempcmstudent = new Cmstudent();

			for (int i = 0; i < cmStudentIDList.size(); i++) {

				cmstudent = cmstudentService.getCmstudentById(cmStudentIDList
						.get(i));

				if (cmScoreList.get(i) == null) {
					continue;
				}

				// ����ǰ̨��������Ϣ�趨Ҫ���µ�cmcredit��Ϣ
				cmcredit.setDescription(cmDescriptionList.get(i));
				cmcredit.setCredit(cmScoreList.get(i));
				cmcredit.setCstudentid(cmstudent.getCstudentid());
				cmcredit.setCmissionid(cmissionid);

				// �ж�С���Ա�Ƿ�������
				if (cmstudent.getIsmarked().equals(false)) {
					cmcreditService.insertCmcredit(cmcredit);

					tempcmstudent.setId(cmstudent.getId());
					tempcmstudent.setIsmarked(true);
					cmstudentService.updateCmstudent(tempcmstudent);
				} else {
					// ����cstudentid,cmissionid��ȡ������ϸ����
					cmcreditDetailView = cmcreditService
							.getCmcreditDetailViewByCondition(
									cmstudent.getCstudentid(), cmissionid);
					cmcredit.setId(cmcreditDetailView.getId());
					cmcreditService.updateCmcredit(cmcredit);
				}
			}
			
			// ����ǰ̨������cmissionid��ȡС����ϸ��Ϣ
			cmgroupDetailViews = cmgroupService
					.getCmgroupDetailViewListByCmissionidIsmarked(cmissionid);

			// �������С������������ϣ��ͽ�cmission���ismarked����Ϊ"1"
			Integer isAllmarked = 1;
			for (int i = 0; i < cmgroupDetailViews.size(); i++) {
				if (cmgroupDetailViews.get(i).getIsmarked() == 0)
					isAllmarked = 0;
			}

			// �ж�С���Ƿ����г�Ա������
			if (isAllmarked == 1) {
				Cmission cmission = new Cmission();
				cmission.setId(cmstudent.getCmissionid());
				cmission.setIsmarked("1");
				cmissionService.updateCmission(cmission);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// ��ȡ���̷�������
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * �ύ����ѧ������
	 * 
	 * @return
	 */
	public String savePersonalScore() {
		try {

			if (cmcredit.getDescription().equals(null)
					|| cmcredit.getDescription().equals("")) {
				cmcredit.setDescription("");
			}
			cmissionService.savePersonalMissionScore(cmstudent, cmcredit);

			cmstudentList = cmstudentService
					.getCmstudentListByCmissionidAndIsinvolved(
							cmstudent.getCmissionid(), true);

			// ������и�������������ϣ��ͽ�cmission���ismarked����Ϊ"1"
			Boolean isAllmarked = true;
			for (int i = 0; i < cmstudentList.size(); i++) {
				if (cmstudentList.get(i).getIsmarked().equals(false))
					isAllmarked = false;
			}

			if (isAllmarked.equals(true)) {
				Cmission cmission = new Cmission();
				cmission.setId(cmstudent.getCmissionid());
				cmission.setIsmarked("1");
				cmissionService.updateCmission(cmission);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// ��ȡ���̷�������
			return ERROR;
		}
		return SUCCESS;
	}

	
	/**
	 * ��ʦ���ر���
	 * 
	 * @return
	 */
	public String downloadViewable() {
		try {
			// ��ȡcmreport
			Cmreport temp = cmreportService.getCmreport(cmreport.getId());

			this.fileFileName = temp.getViewablefilename();

			downloadFilePath = temp.getViewablefilepath() + file.separator
					+ fileFileName;

			// �ø�����������
			InputStream inputStream = ServletActionContext.getServletContext()
					.getResourceAsStream(downloadFilePath);

			this.fileFileName = new String(temp.getOldfilename().getBytes(),
					"ISO-8859-1");

			// ����γ̸���������
			if (inputStream == null) {
				return ERROR;
			}
		} catch (Exception e) {
			// ��ȡ���̷�������
			return ERROR;
		}
		return SUCCESS;
	}

	
	/*
	 * getters and setters
	 */

	public Integer getCmgroupid() {
		return cmgroupid;
	}

	public void setCmgroupid(Integer cmgroupid) {
		this.cmgroupid = cmgroupid;
	}

	public CmstudentDetailViewService getCmstudentDetailViewService() {
		return cmstudentDetailViewService;
	}

	public void setCmstudentDetailViewService(
			CmstudentDetailViewService cmstudentDetailViewService) {
		this.cmstudentDetailViewService = cmstudentDetailViewService;
	}

	public List<CmstudentDetailView> getCmstudentDetailView() {
		return cmstudentListDetailView;
	}

	public void setCmstudentDetailView(
			List<CmstudentDetailView> cmstudentDetailView) {
		this.cmstudentListDetailView = cmstudentDetailView;
	}


	public List<CmstudentDetailView> getCmstudentListDetailView() {
		return cmstudentListDetailView;
	}

	public void setCmstudentListDetailView(
			List<CmstudentDetailView> cmstudentListDetailView) {
		this.cmstudentListDetailView = cmstudentListDetailView;
	}

	public Cmreport getCmreport() {
		return cmreport;
	}

	public void setCmreports(Cmreport cmreport) {
		this.cmreport = cmreport;
	}

	public CmgroupService getCmgroupService() {
		return cmgroupService;
	}

	public void setCmgroupService(CmgroupService cmgroupService) {
		this.cmgroupService = cmgroupService;
	}

	public List<CmstudentDetailView> getCmstudentDetailViews() {
		return cmstudentDetailViews;
	}

	public void setCmstudentDetailViews(
			List<CmstudentDetailView> cmstudentDetailViews) {
		this.cmstudentDetailViews = cmstudentDetailViews;
	}

	public List<CmgroupDetailView> getCmgroupDetailViews() {
		return cmgroupDetailViews;
	}

	public void setCmgroupDetailViews(List<CmgroupDetailView> cmgroupDetailViews) {
		this.cmgroupDetailViews = cmgroupDetailViews;
	}

	public Integer getLeaderid() {
		return leaderid;
	}

	public void setLeaderid(Integer leaderid) {
		this.leaderid = leaderid;
	}

	public CmreportService getCmreportService() {
		return cmreportService;
	}

	public void setCmreportService(CmreportService cmreportService) {
		this.cmreportService = cmreportService;
	}

	public CmgroupDetailView getCmgroupDetailView() {
		return cmgroupDetailView;
	}

	public void setCmgroupDetailView(CmgroupDetailView cmgroupDetailView) {
		this.cmgroupDetailView = cmgroupDetailView;
	}

	public CmstudentDetailView getCmgroupleader() {
		return cmgroupleader;
	}

	public void setCmgroupleader(CmstudentDetailView cmgroupleader) {
		this.cmgroupleader = cmgroupleader;
	}

	public List<Byte> getCmScoreList() {
		return cmScoreList;
	}

	public void setCmScoreList(List<Byte> cmScoreList) {
		this.cmScoreList = cmScoreList;
	}

	public CmcreditService getCmcreditService() {
		return cmcreditService;
	}

	public void setCmcreditService(CmcreditService cmcreditService) {
		this.cmcreditService = cmcreditService;
	}

	public Cmission getCmission() {
		return cmission;
	}

	public void setCmission(Cmission cmission) {
		this.cmission = cmission;
	}

	public Cmcredit getCmcredit() {
		return cmcredit;
	}

	public void setCmcredit(Cmcredit cmcredit) {
		this.cmcredit = cmcredit;
	}

	public CmstudentService getCmstudentService() {
		return cmstudentService;
	}

	public void setCmstudentService(CmstudentService cmstudentService) {
		this.cmstudentService = cmstudentService;
	}

	public Cmstudent getCmstudent() {
		return cmstudent;
	}

	public void setCmstudent(Cmstudent cmstudent) {
		this.cmstudent = cmstudent;
	}

	public CmissionService getCmissionService() {
		return cmissionService;
	}

	public void setCmissionService(CmissionService cmissionService) {
		this.cmissionService = cmissionService;
	}

	public Integer getCmstudentid() {
		return cmstudentid;
	}

	public void setCmstudentid(Integer cmstudentid) {
		this.cmstudentid = cmstudentid;
	}

	public CmstudentDetailView getCpersonalmstudentDetailView() {
		return cpersonalmstudentDetailView;
	}

	public void setCpersonalmstudentDetailView(
			CmstudentDetailView cpersonalmstudentDetailView) {
		this.cpersonalmstudentDetailView = cpersonalmstudentDetailView;
	}

	public List<Cmstudent> getCmstudentList() {
		return cmstudentList;
	}

	public void setCmstudentList(List<Cmstudent> cmstudentList) {
		this.cmstudentList = cmstudentList;
	}

	public List<Integer> getCmStudentIDList() {
		return cmStudentIDList;
	}

	public void setCmStudentIDList(List<Integer> cmStudentIDList) {
		this.cmStudentIDList = cmStudentIDList;
	}

	public void setCmreport(Cmreport cmreport) {
		this.cmreport = cmreport;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Cmcredit getNewcmcredit() {
		return newcmcredit;
	}

	public void setNewcmcredit(Cmcredit newcmcredit) {
		this.newcmcredit = newcmcredit;
	}

	public CmcreditDetailView getCmcreditDetailView() {
		return cmcreditDetailView;
	}

	public void setCmcreditDetailView(CmcreditDetailView cmcreditDetailView) {
		this.cmcreditDetailView = cmcreditDetailView;
	}

	public List<Cmcredit> getCmcreditList() {
		return cmcreditList;
	}

	public void setCmcreditList(List<Cmcredit> cmcreditList) {
		this.cmcreditList = cmcreditList;
	}

	public List<String> getCmDescriptionList() {
		return cmDescriptionList;
	}

	public void setCmDescriptionList(List<String> cmDescriptionList) {
		this.cmDescriptionList = cmDescriptionList;
	}

	public List<CmcreditDetailView> getCmcreditListDetailView() {
		return cmcreditListDetailView;
	}

	public void setCmcreditListDetailView(
			List<CmcreditDetailView> cmcreditListDetailView) {
		this.cmcreditListDetailView = cmcreditListDetailView;
	}

	public CmcreditDetailView getCmcredit4leader() {
		return cmcredit4leader;
	}

	public void setCmcredit4leader(CmcreditDetailView cmcredit4leader) {
		this.cmcredit4leader = cmcredit4leader;
	}

}
