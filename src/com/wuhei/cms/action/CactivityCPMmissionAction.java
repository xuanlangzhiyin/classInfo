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
 * 课程活动｜课程任务｜个人任务
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
	 * 进入新增个人任务的页面
	 * 
	 * @return
	 */
	public String newPersonalCmission() {
		return SUCCESS;
	}

	/**
	 * 填写 任务名称、任务简介等基本信息，确定新增一个个人任务。 前端传来 cmission 对象 courseid 该课程id
	 * 
	 * @return 给前端返回 involvedCmstudentDetailViewList 参与该任务的学生详细信息列表
	 */
	public String addNewPersonalCmission() {
		try {
			cmission.setStype("个人任务");
			cmission.setTeacherid(CmsWebContext.getCurrentUser().getTeacherid());// 当前任务关联的教师id
			cmission.setCourseid(courseid);
			cmissionService.addCmission(cmission); // 这里添加的是小组任务，个人任务应该新写一个接口
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
	 * 更改学生的参与状态 前端传来 cmstudentList 需要更改的学生列表 包含属性cmstudent.id, isinvolved
	 * courseid 课程id cmissionid 任务id
	 * 
	 * @return 给前端返回操作成功信息
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
	 * 进入修改个人任务的基本信息，加载已有内容 前端传来 cmissionid
	 * 
	 * @return cmission对象 给前端返回两个List<CmstudentDetailView> 分别是参与任务的学生和未参与任务的学生
	 *         courseid 课程id
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
	 * 修改个人任务的基本信息，包括 任务名称 任务截止时间 人物简介 附件(未实现) 前端传来 cmission 对象
	 * 
	 * @return 操作成功 ajaxResult
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
