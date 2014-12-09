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
 * 课程小组任务管理
 * 
 * 
 */
@SuppressWarnings("serial")
public class CativityCGMmissionAction extends CactivityCmissionAction {

	Logger logger = Logger.getLogger(CativityCGMmissionAction.class);
	/**
	 * 想要切换的任务小组模块的任务id;
	 */
	private Integer wantedCmissionid;

	
	/**
	 * 参与课程任务小组详细
	 */
	private List<CmgroupDetailView> cmgroupinMDetailViews;

	/**
	 * 未参与课程任务小组详细
	 */
	private List<CmgroupDetailView> cmgroupNotinMDetailViews;

	/**
	 * 课程小组详细
	 */
	private List<CgroupDetailView> cgroupDetailViews;

	/**
	 * 小组学生详细信息
	 */
	private List<CmstudentDetailView> cmstudentDetailViews;

	/**
	 * 课程小组id list
	 */
	private List<Integer> cmgroupids;

	/**
	 * 课程小组id
	 */
	private Integer cmgroupid;

	

	/**
	 * 接收前端需要修改的cmgroup对象list(设置参与，设置不参与)
	 */
	private List<Cmgroup> cmgroupList;

	private CmgroupService cmgroupService;

	private CmissionService cmissionService;

	private CgroupService cgroupService;

	private CmstudentService cmstudentService;

	/**
	 * 传入courseid
	 * 
	 * 返回newGroupMission.jsp
	 * 
	 * @return
	 */
	public String newGroupMission() {

		return SUCCESS;
	}

	/**
	 * 前端传来Cmission
	 * 
	 * 新增一个小组任务，将课程分组复制到小组任务下，
	 * 
	 * 返回参与任务小组列表(新建的任务所有小组默认参与任务)
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
	 * 前端传来 cmissionid | cmgroup.cmissionid，
	 * 
	 * List<Integer> cmgroupids | cmgroup.id
	 * 
	 * List中的小组参与到小组任务中
	 * 
	 * 返回ajaxResult;
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
	 * 前端传来cmissionid | cmgroup.cmissionid，
	 * 
	 * List<Integer> cmgroupid | cmgroup.id
	 * 
	 * List中的小组从小组任务中排除
	 * 
	 * 返回ajaxResult;
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
	 * 前端传来cmissionid | cmstudent.cmissionid
	 * 
	 * 返回list<CmstudentDetailView>
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
	 * 更新小组课程任务
	 * 
	 * 前端传来cmission
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
	 * 前端传来List<Cmgroup> cmgroupList
	 * 
	 * cmgroup对象中包含id和isinvolove属性
	 * 
	 * 更新任务下的各小组参与状态
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
