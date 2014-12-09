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
 * 小组任务|小组管理
 * 
 * 
 */
@SuppressWarnings("serial")
public class CactivityCmgroupAction extends CactivityCmissionAction {

	/**
	 * 课程任务小组内学生list
	 */
	private List<CmstudentDetailView> cmstudentDetailViews;

	/**
	 * 未分组学生list
	 */
	private List<CmstudentDetailView> ungroupedCmstudentDetailViews;

	/**
	 * 课程任务小组id
	 */
	private Integer cmgroupid;

	

	/**
	 * 课程任务小组id列表
	 */
	private List<Integer> cmgroupids;

	/**
	 * 课程任务小组
	 */
	private Cmgroup cmgroup;
	
	private Cmission cmission;

	/**
	 * 课程任务分组信息列表
	 */
	private List<CmgroupDetailView> cmgroupDetailViews;

	/**
	 * 课程任务分组信息
	 */
	private CmgroupDetailView cmgroupDetailView;

	/**
	 * 新建的课程任务分组信息(用于newgroup返回)
	 */
	private CmgroupDetailView addedCmgroupDetailView;

	/**
	 * 新建分组名称
	 */
	private String cmgroupName;

	/**
	 * 课程学生id list
	 */
	private List<Integer> cmstudentids;

	/**
	 * 课程学生id
	 */
	private Integer cmstudentid;

	/**
	 * 课程任务组长id
	 */
	private Integer leaderid;

	private CmgroupService cmgroupService;

	private CmstudentService cmstudentService;
	
	private CmissionService cmissionService;

	/**
	 * 列出所有课程任务未分组学生
	 * 
	 * 前端传来 cmission
	 * 
	 * 根据cmstudent.isgrouped判断学生是否已经有分组
	 * 
	 * 返回未分组的学生 List<CmstudentDetialView>
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
	 * 列出所有组的基本信息（不展开分组，不显示组内成员的详细信息）
	 * 
	 * 前端传来 cmissionid
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
	 * 列出某一个任务分组的所有成员的详细信息
	 * 
	 * 根据cmgroupid
	 * 
	 * 返回该组的学生List<CmstudentDetialView>
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
	 * 新建一个任务分组
	 * 
	 * 传入 所属任务id、一个学生list|list<>
	 * 
	 * 新建一个课程分组Cgroup， 并插入学生List<cstudentid>
	 * 
	 * 返回一条新建的分组
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
			// 将传回来的数据构造成一个cgroup
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
			// 小组人数
			cmgroup.setCount((short) cmstudentids.size());
			// 组长
			cmgroup.setLeaderid(cmstudentids.get(0));
			// 使用List<Integer> cstudentids 需要修改service接口
			cmgroupService.newCmgroup(cmgroup, cmstudentids);
			
			//更改cmissioin里的scount字段
			cmission=cmissionService.getCmission(cmissionid);
			cmission.setScount((short)(cmission.getScount()+cmstudentids.size()));
			cmissionService.updateCmission(cmission);
			// 传到前端的addedCgroupDetailView
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
	 * 添加学生到某一个分组
	 * 
	 * 传入一个学生list|List<cmstudentid>
	 * 
	 * 传入List<cmstudentid> cmstudentids
	 * 
	 * 将学生添加到组，返回新组cmgroupdetialview
	 * 
	 * @return
	 */
	public String addToCmgroup() {
		try {
			// 使用List<Integer> cstudentids 需要修改service接口
			cmgroupService.addCmgroupMember(cmstudentids, cmgroupid);
			
			//更改cmissioin里的scount字段
			cmission=cmissionService.getCmission(cmissionid);
			cmission.setScount((short)(cmission.getScount()+cmstudentids.size()));
			cmissionService.updateCmission(cmission);
			
			// 传到前端的addedCgroupDetailView
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
	 * 将学生设置为未分组
	 * 
	 * 前端传来cmstudentids，cmgroupid
	 * 
	 * 将此学生设置成为未分组
	 * 
	 * 返回所有分组
	 * 
	 * @return
	 */
	public String cancelCmstudentGrouped() {
		try {
			Integer cmstudentId = cmstudentids.get(0);
			cmgroupService.deleteCmgroupMember(cmstudentId);
			
			//更改cmissioin里的scount字段
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
	 * 删除一个小组 前端传来groupid，
	 * 
	 * cmissionid 小组中的学生都成为未分组，
	 * 
	 * 删除这个group，
	 * 
	 * 返回list<UngroupedStudent>
	 * 
	 * @return
	 */
	public String cancelCmgroupGrouped() {
		try {
			
			
			cmgroup=cmgroupService.getCmgroupById(cmgroupid);
			
			//更改cmissioin里的scount字段
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
	 * 批量删除组 前端传来groupids，
	 * 
	 * cmissionid 小组中的学生都成为未分组，
	 * 
	 * 删除这个group，
	 * 
	 * 返回list<UngroupedStudent>
	 * 
	 * @return
	 */
	public String cancelCmgroupsGrouped() {
		try {
			
			
			for(int i=0;i<cmgroupids.size();i++){
				Cmgroup tempcmgroup=new Cmgroup();
				tempcmgroup=cmgroupService.getCmgroupById(cmgroupids.get(i));
				
				//更改cmissioin里的scount字段
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
	 * 设置新的组长
	 * 
	 * 前端传回需要修改的组id：cmgroupid、组长id：leaderid
	 * 
	 * 设置新的组长
	 * 
	 * @return
	 */
	public String setCmgroupLeader() {
		try {
			// 指定组长id
			Cmgroup tempcmgroup = new Cmgroup();
			tempcmgroup.setId(cmgroupid);
			tempcmgroup.setLeaderid(leaderid);
			// 更新cmgroup信息
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
	 * 对未分组的学生进行分组，每个学生一个小组
	 * 
	 * 所属课程id|cmgroup.cmissionid
	 * 
	 * 传入一个学生idlist|list<Integer>
	 * 
	 * 新建一个课程任务分组Cmgroup
	 * 
	 * 
	 * @return
	 */
	public String setUngroupedStudents2newCmgroup() {

		try {

			for (int i = 0; i < cmstudentids.size(); i++) {

				Cmstudent tempCmstudent = cmstudentService
						.getCmstudentById(cmstudentids.get(i));
				// 将传回来的数据构造成一个cgroup
				cmgroup = new Cmgroup();
				// 学生名字做组名
				cmgroup.setName(tempCmstudent.getName());

				cmgroup.setCmissionid(cmissionid);
				// 小组人数
				cmgroup.setCount((short) 1);
				// 组长
				cmgroup.setLeaderid(cmstudentids.get(i));
				//
				cmgroupService.newCmgroupByOne(cmgroup, cmstudentids.get(i));
			}
			
			//更改cmissioin里的scount字段
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
