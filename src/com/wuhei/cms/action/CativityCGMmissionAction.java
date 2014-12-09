package com.wuhei.cms.action;


import java.util.List;

import org.apache.log4j.Logger;
import com.wuhei.cms.model.Cmgroup;
import com.wuhei.cms.model.joint.CgroupDetailView;
import com.wuhei.cms.model.joint.CmgroupDetailView;
import com.wuhei.cms.model.joint.CmstudentDetailView;
import com.wuhei.cms.service.cactivities.CgroupService;
import com.wuhei.cms.service.cactivities.CmgroupService;
import com.wuhei.cms.service.cactivities.CmissionService;
import com.wuhei.cms.service.cactivities.CmstudentService;


/**
 * �γ�С���������
 * 
 * 
 */
@SuppressWarnings("serial")
public class CativityCGMmissionAction extends CactivityCmissionAction {

	Logger logger = Logger.getLogger(CativityCGMmissionAction.class);
	/**
	 * ��Ҫ�л�������С��ģ�������id;
	 */
	private Integer wantedCmissionid;

	
	/**
	 * ����γ�����С����ϸ
	 */
	private List<CmgroupDetailView> cmgroupinMDetailViews;

	/**
	 * δ����γ�����С����ϸ
	 */
	private List<CmgroupDetailView> cmgroupNotinMDetailViews;

	/**
	 * �γ�С����ϸ
	 */
	private List<CgroupDetailView> cgroupDetailViews;

	/**
	 * С��ѧ����ϸ��Ϣ
	 */
	private List<CmstudentDetailView> cmstudentDetailViews;

	/**
	 * �γ�С��id list
	 */
	private List<Integer> cmgroupids;

	/**
	 * �γ�С��id
	 */
	private Integer cmgroupid;

	

	/**
	 * ����ǰ����Ҫ�޸ĵ�cmgroup����list(���ò��룬���ò�����)
	 */
	private List<Cmgroup> cmgroupList;

	private CmgroupService cmgroupService;

	private CmissionService cmissionService;

	private CgroupService cgroupService;

	private CmstudentService cmstudentService;

	/**
	 * ����courseid
	 * 
	 * ����newGroupMission.jsp
	 * 
	 * @return
	 */
	public String newGroupMission() {

		return SUCCESS;
	}

	/**
	 * ǰ�˴���Cmission
	 * 
	 * ����һ��С�����񣬽��γ̷��鸴�Ƶ�С�������£�
	 * 
	 * ���ز�������С���б�(�½�����������С��Ĭ�ϲ�������)
	 * 
	 * list<CmgroupDetailView> cmgroupinMDetailViews
	 * 
	 * @return
	 */
	public String getCourseGroup() {
		try {
			cgroupDetailViews = cgroupService
					.getCgroupDetailViewListByCourseid(courseid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}

		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	

	public String changeGroupToCourseGroup() {

		try {
			cmissionService.changeGroupToCourseGroup(cmissionid, courseid);
			cmgroupinMDetailViews = cmgroupService
					.getinMCmgroupDetailViewListByCmissionid(cmissionid);
			cmgroupNotinMDetailViews = cmgroupService
					.getNotinMCmgroupDetailViewListByCmissionid(cmissionid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	public String saveNewGroupMission() {

		try {
			cmgroupinMDetailViews = cmgroupService
					.getinMCmgroupDetailViewListByCmissionid(cmissionid);
			cmgroupNotinMDetailViews = cmgroupService
					.getNotinMCmgroupDetailViewListByCmissionid(cmissionid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;

	}

	/**
	 * ǰ�˴��� cmissionid | cmgroup.cmissionid��
	 * 
	 * List<Integer> cmgroupids | cmgroup.id
	 * 
	 * List�е�С����뵽С��������
	 * 
	 * ����ajaxResult;
	 * 
	 * @return
	 */
	public String addCmGroupstoMission() {

		try {
			cmgroupService.addCmgrouptoMission(cmgroupids);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * ǰ�˴���cmissionid | cmgroup.cmissionid��
	 * 
	 * List<Integer> cmgroupid | cmgroup.id
	 * 
	 * List�е�С���С���������ų�
	 * 
	 * ����ajaxResult;
	 * 
	 * @return
	 */
	public String removeCmGroupsFromMission() {

		try {
			cmgroupService.removeCmgroupfromMission(cmgroupids);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * ǰ�˴���cmissionid | cmstudent.cmissionid
	 * 
	 * ����list<CmstudentDetailView>
	 * 
	 * @return
	 */
	public String getCmGroupMemberDetial() {
		try {
			cmstudentDetailViews = cmgroupService
					.getCmstudentDetailViewListByCmgroupid(cmgroupid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * ����С��γ�����
	 * 
	 * ǰ�˴���cmission
	 * 
	 * 
	 * @return
	 */
	public String updateGroupCmission() {

		try {
			cmissionService.modifyCmission(cmission);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * ǰ�˴���List<Cmgroup> cmgroupList
	 * 
	 * cmgroup�����а���id��isinvolove����
	 * 
	 * ���������µĸ�С�����״̬
	 * 
	 * @return
	 */
	public String updateCmissionGroup() {

		try {
			cmgroupService.confirmCmgroup(cmgroupList);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	// getter and setter


	public List<CmgroupDetailView> getCmgroupinMDetailViews() {
		return cmgroupinMDetailViews;
	}

	public void setCmgroupinMDetailViews(
			List<CmgroupDetailView> cmgroupinMDetailViews) {
		this.cmgroupinMDetailViews = cmgroupinMDetailViews;
	}

	public List<CmgroupDetailView> getCmgroupNotinMDetailViews() {
		return cmgroupNotinMDetailViews;
	}

	public void setCmgroupNotinMDetailViews(
			List<CmgroupDetailView> cmgroupNotinMDetailViews) {
		this.cmgroupNotinMDetailViews = cmgroupNotinMDetailViews;
	}

	public List<CmstudentDetailView> getCmstudentDetailViews() {
		return cmstudentDetailViews;
	}

	public void setCmstudentDetailViews(
			List<CmstudentDetailView> cmstudentDetailViews) {
		this.cmstudentDetailViews = cmstudentDetailViews;
	}

	public List<Integer> getCmgroupids() {
		return cmgroupids;
	}

	public void setCmgroupids(List<Integer> cmgroupids) {
		this.cmgroupids = cmgroupids;
	}

	public CmgroupService getCmgroupService() {
		return cmgroupService;
	}

	public void setCmgroupService(CmgroupService cmgroupService) {
		this.cmgroupService = cmgroupService;
	}

	public Integer getCmgroupid() {
		return cmgroupid;
	}

	public void setCmgroupid(Integer cmgroupid) {
		this.cmgroupid = cmgroupid;
	}

	public List<Cmgroup> getCmgroupList() {
		return cmgroupList;
	}

	public void setCmgroupList(List<Cmgroup> cmgroupList) {
		this.cmgroupList = cmgroupList;
	}

	public CmissionService getCmissionService() {
		return cmissionService;
	}

	public void setCmissionService(CmissionService cmissionService) {
		this.cmissionService = cmissionService;
	}



	public List<CgroupDetailView> getCgroupDetailViews() {
		return cgroupDetailViews;
	}

	public void setCgroupDetailViews(List<CgroupDetailView> cgroupDetailViews) {
		this.cgroupDetailViews = cgroupDetailViews;
	}

	public CgroupService getCgroupService() {
		return cgroupService;
	}

	public void setCgroupService(CgroupService cgroupService) {
		this.cgroupService = cgroupService;
	}


	public Integer getWantedCmissionid() {
		return wantedCmissionid;
	}

	public void setWantedCmissionid(Integer wantedCmissionid) {
		this.wantedCmissionid = wantedCmissionid;
	}

	public CmstudentService getCmstudentService() {
		return cmstudentService;
	}

	public void setCmstudentService(CmstudentService cmstudentService) {
		this.cmstudentService = cmstudentService;
	}

}
