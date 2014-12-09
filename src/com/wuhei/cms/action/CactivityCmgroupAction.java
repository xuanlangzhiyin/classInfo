package com.wuhei.cms.action;

import java.util.List;

import com.wuhei.cms.model.Cmgroup;
import com.wuhei.cms.model.Cmission;
import com.wuhei.cms.model.joint.CmgroupDetailView;
import com.wuhei.cms.model.Cmstudent;
import com.wuhei.cms.model.joint.CmstudentDetailView;
import com.wuhei.cms.service.cactivities.CmgroupService;
import com.wuhei.cms.service.cactivities.CmissionService;
import com.wuhei.cms.service.cactivities.CmstudentService;

/**
 * С������|С�����
 * 
 * 
 */
@SuppressWarnings("serial")
public class CactivityCmgroupAction extends CactivityCmissionAction {

	/**
	 * �γ�����С����ѧ��list
	 */
	private List<CmstudentDetailView> cmstudentDetailViews;

	/**
	 * δ����ѧ��list
	 */
	private List<CmstudentDetailView> ungroupedCmstudentDetailViews;

	/**
	 * �γ�����С��id
	 */
	private Integer cmgroupid;

	

	/**
	 * �γ�����С��id�б�
	 */
	private List<Integer> cmgroupids;

	/**
	 * �γ�����С��
	 */
	private Cmgroup cmgroup;
	
	private Cmission cmission;

	/**
	 * �γ����������Ϣ�б�
	 */
	private List<CmgroupDetailView> cmgroupDetailViews;

	/**
	 * �γ����������Ϣ
	 */
	private CmgroupDetailView cmgroupDetailView;

	/**
	 * �½��Ŀγ����������Ϣ(����newgroup����)
	 */
	private CmgroupDetailView addedCmgroupDetailView;

	/**
	 * �½���������
	 */
	private String cmgroupName;

	/**
	 * �γ�ѧ��id list
	 */
	private List<Integer> cmstudentids;

	/**
	 * �γ�ѧ��id
	 */
	private Integer cmstudentid;

	/**
	 * �γ������鳤id
	 */
	private Integer leaderid;

	private CmgroupService cmgroupService;

	private CmstudentService cmstudentService;
	
	private CmissionService cmissionService;

	/**
	 * �г����пγ�����δ����ѧ��
	 * 
	 * ǰ�˴��� cmission
	 * 
	 * ����cmstudent.isgrouped�ж�ѧ���Ƿ��Ѿ��з���
	 * 
	 * ����δ�����ѧ�� List<CmstudentDetialView>
	 * 
	 * @return
	 */
	public String listUngroupedCmstudents() {

		try {
			ungroupedCmstudentDetailViews = cmgroupService
					.getUngroupedCmstudentDetailViewListByCmissionid(cmissionid);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * �г�������Ļ�����Ϣ����չ�����飬����ʾ���ڳ�Ա����ϸ��Ϣ��
	 * 
	 * ǰ�˴��� cmissionid
	 * 
	 * 
	 */
	public String getCmgroups() {
		try {
			cmgroupDetailViews = cmgroupService
					.getCmgroupDetailViewListByCmissionid(cmissionid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * �г�ĳһ�������������г�Ա����ϸ��Ϣ
	 * 
	 * ����cmgroupid
	 * 
	 * ���ظ����ѧ��List<CmstudentDetialView>
	 * 
	 * @return
	 */
	public String getCmgroupMemberDetial() {
		try {
			cmstudentDetailViews = cmgroupService
					.getCmstudentDetailViewListByCmgroupid(cmgroupid);
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
	 * 
	 * �½�һ���������
	 * 
	 * ���� ��������id��һ��ѧ��list|list<>
	 * 
	 * �½�һ���γ̷���Cgroup�� ������ѧ��List<cstudentid>
	 * 
	 * ����һ���½��ķ���
	 * 
	 * @return
	 */
	public String newCmgroup() {
		try {
			if(cmgroupName.length()>30)
			{
			    ajaxResult = ERROR;
				return ERROR;
			}
			
			List<CmgroupDetailView> tempCmgroupDetailViews=cmgroupService.getCmgroupDetailViewListByCmissionid(cmissionid);
			for(int i=0;i<tempCmgroupDetailViews.size();i++){
				if(cmgroupName.equals(tempCmgroupDetailViews.get(i).getName()))
				{
				    ajaxResult = ERROR;
					return ERROR;
				}
			}
			// �������������ݹ����һ��cgroup
			cmgroup = new Cmgroup();
			// cgroup.setCode(code);

			if (cmgroupName.equals("")) {
				Cmstudent temCmstudent = cmstudentService
						.getCmstudentById(cmstudentids.get(0));
				cmgroup.setName(temCmstudent.getName());
			} else {
				cmgroup.setName(cmgroupName);
			}
			cmgroup.setCmissionid(cmissionid);
			// С������
			cmgroup.setCount((short) cmstudentids.size());
			// �鳤
			cmgroup.setLeaderid(cmstudentids.get(0));
			// ʹ��List<Integer> cstudentids ��Ҫ�޸�service�ӿ�
			cmgroupService.newCmgroup(cmgroup, cmstudentids);
			
			//����cmissioin���scount�ֶ�
			cmission=cmissionService.getCmission(cmissionid);
			cmission.setScount((short)(cmission.getScount()+cmstudentids.size()));
			cmissionService.updateCmission(cmission);
			// ����ǰ�˵�addedCgroupDetailView
			addedCmgroupDetailView = cmgroupService
					.getCmgroupDetailView(cmgroup.getId());

		} catch (Exception e) {

			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * ���ѧ����ĳһ������
	 * 
	 * ����һ��ѧ��list|List<cmstudentid>
	 * 
	 * ����List<cmstudentid> cmstudentids
	 * 
	 * ��ѧ����ӵ��飬��������cmgroupdetialview
	 * 
	 * @return
	 */
	public String addToCmgroup() {
		try {
			// ʹ��List<Integer> cstudentids ��Ҫ�޸�service�ӿ�
			cmgroupService.addCmgroupMember(cmstudentids, cmgroupid);
			
			//����cmissioin���scount�ֶ�
			cmission=cmissionService.getCmission(cmissionid);
			cmission.setScount((short)(cmission.getScount()+cmstudentids.size()));
			cmissionService.updateCmission(cmission);
			
			// ����ǰ�˵�addedCgroupDetailView
			addedCmgroupDetailView = cmgroupService
					.getCmgroupDetailView(cmgroupid);
			
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * ��ѧ������Ϊδ����
	 * 
	 * ǰ�˴���cmstudentids��cmgroupid
	 * 
	 * ����ѧ�����ó�Ϊδ����
	 * 
	 * �������з���
	 * 
	 * @return
	 */
	public String cancelCmstudentGrouped() {
		try {
			Integer cmstudentId = cmstudentids.get(0);
			cmgroupService.deleteCmgroupMember(cmstudentId);
			
			//����cmissioin���scount�ֶ�
			cmission=cmissionService.getCmission(cmissionid);
			cmission.setScount((short)(cmission.getScount()-1));
			cmissionService.updateCmission(cmission);
			
			ungroupedCmstudentDetailViews = cmgroupService
					.getUngroupedCmstudentDetailViewListByCmissionid(cmissionid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * ɾ��һ��С�� ǰ�˴���groupid��
	 * 
	 * cmissionid С���е�ѧ������Ϊδ���飬
	 * 
	 * ɾ�����group��
	 * 
	 * ����list<UngroupedStudent>
	 * 
	 * @return
	 */
	public String cancelCmgroupGrouped() {
		try {
			
			
			cmgroup=cmgroupService.getCmgroupById(cmgroupid);
			
			//����cmissioin���scount�ֶ�
			cmission=cmissionService.getCmission(cmissionid);
			cmission.setScount((short)(cmission.getScount()-cmgroup.getCount()));
			cmissionService.updateCmission(cmission);
			
			cmgroupService.deleteCmgroup(cmgroupid);
			
			ungroupedCmstudentDetailViews = cmgroupService
					.getUngroupedCmstudentDetailViewListByCmissionid(cmissionid);
		
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * ����ɾ���� ǰ�˴���groupids��
	 * 
	 * cmissionid С���е�ѧ������Ϊδ���飬
	 * 
	 * ɾ�����group��
	 * 
	 * ����list<UngroupedStudent>
	 * 
	 * @return
	 */
	public String cancelCmgroupsGrouped() {
		try {
			
			
			for(int i=0;i<cmgroupids.size();i++){
				Cmgroup tempcmgroup=new Cmgroup();
				tempcmgroup=cmgroupService.getCmgroupById(cmgroupids.get(i));
				
				//����cmissioin���scount�ֶ�
				cmission=cmissionService.getCmission(cmissionid);
				cmission.setScount((short)(cmission.getScount()-tempcmgroup.getCount()));
				cmissionService.updateCmission(cmission);
			}
			
			cmgroupService.deleteCmgroups(cmgroupids);
			
			ungroupedCmstudentDetailViews = cmgroupService
					.getUngroupedCmstudentDetailViewListByCmissionid(cmissionid);
			
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * �����µ��鳤
	 * 
	 * ǰ�˴�����Ҫ�޸ĵ���id��cmgroupid���鳤id��leaderid
	 * 
	 * �����µ��鳤
	 * 
	 * @return
	 */
	public String setCmgroupLeader() {
		try {
			// ָ���鳤id
			Cmgroup tempcmgroup = new Cmgroup();
			tempcmgroup.setId(cmgroupid);
			tempcmgroup.setLeaderid(leaderid);
			// ����cmgroup��Ϣ
			cmgroupService.modifyCmgroup(tempcmgroup);
			cmgroupDetailView = cmgroupService.getCmgroupDetailView(cmgroupid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * 
	 * ��δ�����ѧ�����з��飬ÿ��ѧ��һ��С��
	 * 
	 * �����γ�id|cmgroup.cmissionid
	 * 
	 * ����һ��ѧ��idlist|list<Integer>
	 * 
	 * �½�һ���γ��������Cmgroup
	 * 
	 * 
	 * @return
	 */
	public String setUngroupedStudents2newCmgroup() {

		try {

			for (int i = 0; i < cmstudentids.size(); i++) {

				Cmstudent tempCmstudent = cmstudentService
						.getCmstudentById(cmstudentids.get(i));
				// �������������ݹ����һ��cgroup
				cmgroup = new Cmgroup();
				// ѧ������������
				cmgroup.setName(tempCmstudent.getName());

				cmgroup.setCmissionid(cmissionid);
				// С������
				cmgroup.setCount((short) 1);
				// �鳤
				cmgroup.setLeaderid(cmstudentids.get(i));
				//
				cmgroupService.newCmgroupByOne(cmgroup, cmstudentids.get(i));
			}
			
			//����cmissioin���scount�ֶ�
			cmission=cmissionService.getCmission(cmissionid);
			cmission.setScount((short)(cmission.getScount()+cmstudentids.size()));
			cmissionService.updateCmission(cmission);
			
			
		} catch (Exception e) {

			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		cmgroupDetailViews = cmgroupService
				.getCmgroupDetailViewListByCmissionid(cmissionid);
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	// getter and setter
	public List<CmstudentDetailView> getCmstudentDetailViews() {
		return cmstudentDetailViews;
	}

	public void setCmstudentDetailViews(
			List<CmstudentDetailView> cmstudentDetailViews) {
		this.cmstudentDetailViews = cmstudentDetailViews;
	}

	public List<CmstudentDetailView> getUngroupedCmstudentDetailViews() {
		return ungroupedCmstudentDetailViews;
	}

	public void setUngroupedCmstudentDetailViews(
			List<CmstudentDetailView> ungroupedCmstudentDetailViews) {
		this.ungroupedCmstudentDetailViews = ungroupedCmstudentDetailViews;
	}

	public Integer getCmgroupid() {
		return cmgroupid;
	}

	public void setCmgroupid(Integer cmgroupid) {
		this.cmgroupid = cmgroupid;
	}

	public List<Integer> getCmgroupids() {
		return cmgroupids;
	}

	public void setCmgroupids(List<Integer> cmgroupids) {
		this.cmgroupids = cmgroupids;
	}

	public Cmgroup getCmgroup() {
		return cmgroup;
	}

	public void setCmgroup(Cmgroup cmgroup) {
		this.cmgroup = cmgroup;
	}

	public List<CmgroupDetailView> getCmgroupDetailViews() {
		return cmgroupDetailViews;
	}

	public void setCmgroupDetailViews(List<CmgroupDetailView> cmgroupDetailViews) {
		this.cmgroupDetailViews = cmgroupDetailViews;
	}

	public CmgroupDetailView getCmgroupDetailView() {
		return cmgroupDetailView;
	}

	public void setCmgroupDetailView(CmgroupDetailView cmgroupDetailView) {
		this.cmgroupDetailView = cmgroupDetailView;
	}

	public CmgroupDetailView getAddedCmgroupDetailView() {
		return addedCmgroupDetailView;
	}

	public void setAddedCmgroupDetailView(
			CmgroupDetailView addedCmgroupDetailView) {
		this.addedCmgroupDetailView = addedCmgroupDetailView;
	}

	public String getCmgroupName() {
		return cmgroupName;
	}

	public void setCmgroupName(String cmgroupName) {
		this.cmgroupName = cmgroupName;
	}

	public List<Integer> getCmstudentids() {
		return cmstudentids;
	}

	public void setCmstudentids(List<Integer> cmstudentids) {
		this.cmstudentids = cmstudentids;
	}

	public Integer getLeaderid() {
		return leaderid;
	}

	public void setLeaderid(Integer leaderid) {
		this.leaderid = leaderid;
	}

	public CmgroupService getCmgroupService() {
		return cmgroupService;
	}

	public void setCmgroupService(CmgroupService cmgroupService) {
		this.cmgroupService = cmgroupService;
	}

	public CmstudentService getCmstudentService() {
		return cmstudentService;
	}

	public void setCmstudentService(CmstudentService cmstudentService) {
		this.cmstudentService = cmstudentService;
	}

	
	/*
	 * remove by mulan
	 * 
	 * public Integer getCmissionid() { return cmissionid; }
	 * 
	 * public void setCmissionid(Integer cmissionid) { this.cmissionid =
	 * cmissionid; }
	 */

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

	public Cmission getCmission() {
		return cmission;
	}

	public void setCmission(Cmission cmission) {
		this.cmission = cmission;
	}
	
	

}
