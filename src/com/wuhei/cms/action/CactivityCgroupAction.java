package com.wuhei.cms.action;

import java.util.List;
import com.wuhei.cms.model.Cgroup;
import com.wuhei.cms.model.Cstudent;
import com.wuhei.cms.model.joint.CgroupDetailView;
import com.wuhei.cms.model.joint.CstudentDetailView;
import com.wuhei.cms.service.cactivities.CgroupService;
import com.wuhei.cms.service.cactivities.CstudentService;


/**
 * �γ̷������
 * 
 * 
 */
@SuppressWarnings("serial")
public class CactivityCgroupAction extends CactivityCourseAction {

	/**
	 * �γ� С����ѧ��list
	 */
	private List<CstudentDetailView> cstudentDetailViews;

	/**
	 * δ����ѧ��list
	 */
	private List<CstudentDetailView> ungroupedCstudentDetailViews;

	/**
	 * �γ�С��id
	 */
	private Integer cgroupid;

	/**
	 * ��Ҫ����ɾ���Ŀγ�С��id�б�
	 */
	private List<Integer> cgroupids;

	/**
	 * �γ�С��
	 */
	private Cgroup cgroup;

	/**
	 * �γ̷�����Ϣ
	 */
	private List<CgroupDetailView> cgroupDetailViews;

	/**
	 * �γ̷�����Ϣ
	 */
	private CgroupDetailView cgroupDetailView;

	/**
	 * �½��Ŀγ̷�����Ϣ(����newgroup����)
	 */
	private CgroupDetailView addedCgroupDetailView;

	/**
	 * �½���������
	 */
	private String cgroupName;

	/**
	 * �γ�ѧ��id list
	 */
	private List<Integer> cstudentids;

	/**
	 * �γ�ѧ����id
	 */
	private Integer cstudentid;

	/**
	 * �γ��鳤id
	 */
	private Integer leaderid;
	/**
	 * ÿ��Ajax���󣬶���Ҫ���صĲ��� ��̨�����Ľ���Ƿ�ɹ���
	 * 
	 * private String ajaxResult;
	 */
	private CgroupService cgroupService;

	private CstudentService cstudentService;

	/**
	 * �г�����δ����ѧ��
	 * 
	 * ǰ�˴��� courseid |cstudent.courseid
	 * 
	 * ����cstudent.isgrouped�ж�ѧ���Ƿ��Ѿ��з���
	 * 
	 * ����δ�����ѧ�� List<cStudentDetialView>
	 * 
	 * @return
	 */
	public String listUngroupedStudents() {

		// ActionContext.getContext().getValueStack().findValue("courseid");

		// ����Ӧ�÷���һ��List<CstudentDetailView> �ȴ�service����ȷ����ɡ�
		try {
			ungroupedCstudentDetailViews = cgroupService
					.getUngroupedCstudentDetailViewListByCourseid(courseid);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * �г�������Ļ�����Ϣ����չ�����飬����ʾ���ڳ�Ա����ϸ��Ϣ��
	 * 
	 * ǰ�˴��� courseid |cgroup.courseid
	 * 
	 * ����List<cGroupDetialView>
	 * 
	 * ���ص���ÿ������Ļ�����Ϣ ps����Ҫͬʱ����json����
	 */
	public String getGroups() {
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

	/**
	 * �г�ĳһ����������г�Ա����ϸ��Ϣ
	 * 
	 * ����cgroupid |cstudent.cgroupid
	 * 
	 * ���ظ����ѧ��List<CstudentDetialView> json
	 * 
	 * @return
	 */
	public String getGroupMemberDetial() {
		
		try {
			cstudentDetailViews = cgroupService
					.getCstudentDetailViewListByCgroupid(cgroupid);
			CgroupDetailView tempDetailView = cgroupService
					.getCgroupDetailViewByCgroupid(cgroupid);
			// TODO cgroupid = tempDetailView.getId();
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
	 * �½�һ������
	 * 
	 * ����һ���¿γ̷�����|cgroup.name�������γ�id|cgroup.courseid
	 * 
	 * ����һ��ѧ��list|list<>
	 * 
	 * �½�һ���γ̷���Cgroup�� ������ѧ��List<cstudentid>
	 * 
	 * ����һ���½��ķ���
	 * 
	 * 
	 * @return
	 */
	public String newGroup() {
		try {
			// �������������ݹ����һ��cgroup
			cgroup = new Cgroup();
			List<CgroupDetailView> tempCgroupDetailViews=cgroupService.getCgroupDetailViewListByCourseid(courseid);
			for(int i=0;i<tempCgroupDetailViews.size();i++){
				if(cgroupName.equals(tempCgroupDetailViews.get(i).getName()))
				{
				    ajaxResult = ERROR;
					return ERROR;
				}
			}
			// cgroup.setCode(code);
			if(cgroupName.equals("")){
				Cstudent tempCstudent = cstudentService
						.getCstudentById(cstudentids.get(0));
				cgroup.setName(tempCstudent.getName());
			}
			else{
				cgroup.setName(cgroupName);
			}
			
			cgroup.setCourseid(courseid);
			// С������
			cgroup.setCount((short) cstudentids.size());
			// �鳤
			cgroup.setLeaderid(cstudentids.get(0));
			// ʹ��List<Integer> cstudentids ��Ҫ�޸�service�ӿ�
			cgroupService.newCgroup(cgroup, cstudentids);
			// ����ǰ�˵�addedCgroupDetailView
			addedCgroupDetailView = cgroupService
					.getCgroupDetailViewByCgroupid(cgroup.getId());
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
	 * ����һ��ѧ��list|List<cstudentid>
	 * 
	 * ����List<cstudentid> cstudentids
	 * 
	 * ��ѧ����ӵ��飬��������cgroupdetialview
	 * 
	 * @return
	 */
	public String addToGroup() {
		try {
			// ʹ��List<Integer> cstudentids ��Ҫ�޸�service�ӿ�
			cgroupService.addCgroupMember(cstudentids, cgroupid);
			// ����ǰ�˵�addedCgroupDetailView
			addedCgroupDetailView = cgroupService
					.getCgroupDetailViewByCgroupid(cgroupid);
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
	 * ǰ�˴���cstudentid��groupid
	 * 
	 * ����ѧ�����ó�Ϊδ����
	 * 
	 * �������з���
	 * 
	 * @return
	 */
	public String cancelStudentGrouped() {
		try {
			Integer cstudentId=cstudentids.get(0);
			// ʹ��List<Integer> cstudentids ��Ҫ�޸�service�ӿ�
			cgroupService.deleteCgroupMember(cstudentId, cgroupid);
			ungroupedCstudentDetailViews = cgroupService
					.getUngroupedCstudentDetailViewListByCourseid(courseid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * ɾ��һ����
	 * 
	 * ǰ�˴���groupid�� courseid
	 * 
	 * С���е�ѧ������Ϊδ���飬
	 * 
	 * ɾ�����group��
	 * 
	 * ����list<UngroupedStudent>
	 * 
	 * @return
	 */
	public String cancelcGroupGrouped() {
		try {
			cgroupService.deleteCgroup(cgroupid);
			ungroupedCstudentDetailViews = cgroupService
					.getUngroupedCstudentDetailViewListByCourseid(courseid);
	
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * ����ɾ������� ǰ�˴���groupid��
	 * 
	 * courseid С���е�ѧ������Ϊδ���飬
	 * 
	 * ɾ�����group��
	 * 
	 * ����list<UngroupedStudent>
	 * 
	 * @return
	 */
	public String cancelcGroupsGrouped() {
		try {
			cgroupService.deleteCgroups(cgroupids);
			ungroupedCstudentDetailViews = cgroupService
					.getUngroupedCstudentDetailViewListByCourseid(courseid);
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
	 * ǰ�˴�����Ҫ�޸ĵ���id��cgroupid���鳤id��cstudentid
	 * 
	 * �����µ��鳤
	 * 
	 * @return
	 */
	public String setGroupLeader() {
		try {
			cgroupService.modifyCgroupLeader(cgroupid, cstudentid);
			cgroupDetailView = cgroupService
					.getCgroupDetailViewByCgroupid(cgroupid);
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
	 * �����γ�id|cgroup.courseid
	 * 
	 * ����һ��ѧ��idlist|list<Integer>
	 * 
	 * �½�һ���γ̷���Cgroup
	 * 
	 * 
	 * @return
	 */
	public String setUngroupedStudents2newGroup() {
		try {

			for (int i = 0; i < cstudentids.size(); i++) {

				Cstudent tempCstudent = cstudentService
						.getCstudentById(cstudentids.get(i));
				// �������������ݹ����һ��cgroup
				cgroup = new Cgroup();
				// ѧ������������
				cgroup.setName(tempCstudent.getName());

				cgroup.setCourseid(courseid);
				// С������
				cgroup.setCount((short) 1);
				// �鳤
				cgroup.setLeaderid(cstudentids.get(i));

				cgroupService.newCgroupByOne(cgroup, cstudentids.get(i));
			}
		} catch (Exception e) {

			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		cgroupDetailViews = cgroupService
				.getCgroupDetailViewListByCourseid(courseid);
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	// getter and setter

	public List<CstudentDetailView> getCstudentDetailViews() {
		return cstudentDetailViews;
	}

	public void setCstudentDetailViews(
			List<CstudentDetailView> cstudentDetailViews) {
		this.cstudentDetailViews = cstudentDetailViews;
	}

	public List<CstudentDetailView> getUngroupedCstudentDetailViews() {
		return ungroupedCstudentDetailViews;
	}

	public void setUngroupedCstudentDetailViews(
			List<CstudentDetailView> ungroupedCstudentDetailViews) {
		this.ungroupedCstudentDetailViews = ungroupedCstudentDetailViews;
	}

	public Integer getCgroupid() {
		return cgroupid;
	}

	public void setCgroupid(Integer cgroupid) {
		this.cgroupid = cgroupid;
	}

	public List<Integer> getCgroupids() {
		return cgroupids;
	}

	public void setCgroupids(List<Integer> cgroupids) {
		this.cgroupids = cgroupids;
	}

	public Cgroup getCgroup() {
		return cgroup;
	}

	public void setCgroup(Cgroup cgroup) {
		this.cgroup = cgroup;
	}

	public List<CgroupDetailView> getCgroupDetailViews() {
		return cgroupDetailViews;
	}

	public void setCgroupDetailViews(List<CgroupDetailView> cgroupDetailViews) {
		this.cgroupDetailViews = cgroupDetailViews;
	}

	public CgroupDetailView getCgroupDetailView() {
		return cgroupDetailView;
	}

	public void setCgroupDetailView(CgroupDetailView cgroupDetailView) {
		this.cgroupDetailView = cgroupDetailView;
	}

	public CgroupDetailView getAddedCgroupDetailView() {
		return addedCgroupDetailView;
	}

	public void setAddedCgroupDetailView(CgroupDetailView addedCgroupDetailView) {
		this.addedCgroupDetailView = addedCgroupDetailView;
	}

	public String getCgroupName() {
		return cgroupName;
	}

	public void setCgroupName(String cgroupName) {
		this.cgroupName = cgroupName;
	}

	public List<Integer> getCstudentids() {
		return cstudentids;
	}

	public void setCstudentids(List<Integer> cstudentids) {
		this.cstudentids = cstudentids;
	}

	public Integer getCstudentid() {
		return cstudentid;
	}

	public void setCstudentid(Integer cstudentid) {
		this.cstudentid = cstudentid;
	}

	public Integer getLeaderid() {
		return leaderid;
	}

	public void setLeaderid(Integer leaderid) {
		this.leaderid = leaderid;
	}

	public CgroupService getCgroupService() {
		return cgroupService;
	}

	public void setCgroupService(CgroupService cgroupService) {
		this.cgroupService = cgroupService;
	}

	public CstudentService getCstudentService() {
		return cstudentService;
	}

	public void setCstudentService(CstudentService cstudentService) {
		this.cstudentService = cstudentService;
	}

}
