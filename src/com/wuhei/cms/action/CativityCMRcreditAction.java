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
 * 课程活动｜课程任务｜任务评分
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class CativityCMRcreditAction extends CactivityCmissionAction {

	Logger logger = Logger.getLogger(CativityCMRcreditAction.class);

	// 课程任务的任务小组编号
	private Integer cmgroupid;

	private Cmission cmission;

	private CmstudentDetailView cmgroupleader;

	private CmcreditDetailView cmcredit4leader;

	// 课程任务参与学生List
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
	 * 课程任务分组信息列表
	 */
	private List<CmgroupDetailView> cmgroupDetailViews;

	/**
	 * 列出一个任务分组的所有成员信息
	 */
	private List<CmstudentDetailView> cmstudentDetailViews;

	/**
	 * 个人任务学生信息
	 */
	private CmstudentDetailView cpersonalmstudentDetailView;

	/**
	 * 用于获取个人任务学生的ismarked，判断是否已经全部评完
	 */
	private List<Cmstudent> cmstudentList;

	private List<Cmcredit> cmcreditList;

	/**
	 * 课程任务组长id
	 */
	private Integer leaderid;

	private Integer cmstudentid;

	private String description;

	private Cmcredit cmcredit;

	private Cmcredit newcmcredit;

	private Cmstudent cmstudent;

	/**
	 * 对参与到课程任务的所有小组的单个小组的所有成员评分
	 * 
	 * @return
	 */
	public String evaluateGroupMission() {
		try {

			// 获取当前登录学生id
			studentid = CmsWebContext.getCurrentUser().getStudentid();
			// 根据前端传来的cmissionid 获取cmission对象
			cmission = cmissionService.getCmission(cmissionid);
			// 拿出cmission对象的courseid
			courseid = cmission.getCourseid();
			// 根据studentid和courseid，拿到唯一的cstudent对象，也就是该登陆学生在这门开设课程中的cstudent对象
			cstudent = cmissionService.getCStudentByCondition(studentid,
					courseid);
			// 获取cstudentid
			cstudentid = cstudent.getId();
			// 获取cmission信息
			cmission = cmissionService.getCmission(cmissionid);

			if (cmission.getStype().equalsIgnoreCase("按个人提交")) {
				cmreport = cmreportService.getCmreportByCondition(true,
						cstudentid, cmgroupid, cmissionid);
			}

			else {
				// 获取小组所提交的报告
				cmreport = cmreportService.getCmreportByCondition(true,
						null, cmgroupid, cmissionid);
			}

			// 获取小组成员
			cmstudentListDetailView = cmgroupService
					.getCmstudentDetailViewListByCmgroupid(cmgroupid);
			
			// 根据前台传来的cmgroupid获取小组详细信息
			cmgroupDetailView = cmgroupService.getCmgroupDetailView(cmgroupid);

			// 根据前台传来的cmgroupid获取评分详细内容
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
	 * 对小组任务个人提交的所有参与任务的学生的单个学生进行评分
	 * 
	 * @return
	 */
	public String evaluateGroupMission4personal() {
		try {

			cmission = cmissionService.getCmission(cmissionid);

			// 获取个人所提交的报告id
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
	 * 对个人任务的所有参与任务的学生的单个学生进行评分
	 * 
	 * @return
	 */
	public String evaluatePersonalMission() {
		try {

			cmission = cmissionService.getCmission(cmissionid);

			// 获取个人所提交的报告id
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
	 * 查看参与小组任务的所有小组评分情况
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
	 * 列出某一个任务分组的所有成员的详细信息
	 * 
	 * 根据cmgroupid
	 * 
	 * 返回该组的学生List<CmstudentDetialView> byzhuchengrong
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
	 * 查看参与个人任务的所有学生评分情况
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
	 * 提交小组任务小组成员评分 author:GWB
	 * 
	 * @return
	 */
	public String saveGroupScore() {
		try {
			// 根据前台传来的courseid,cmgroupid, cmissionid获取cmstudentDetailView的以列表显示的详细信息
			cmstudentListDetailView = cmstudentDetailViewService
					.getCmstudentDetailListByCondition(null, courseid,
							cmgroupid, cmissionid, true);			
			
			// 根据前台传来的cmgroupid获取小组详细信息
			cmgroupDetailView = cmgroupService.getCmgroupDetailView(cmgroupid);

			Cmcredit cmcredit = new Cmcredit();

			Cmstudent tempcmstudent = new Cmstudent();

			for (int i = 0; i < cmStudentIDList.size(); i++) {

				cmstudent = cmstudentService.getCmstudentById(cmStudentIDList
						.get(i));

				if (cmScoreList.get(i) == null) {
					continue;
				}

				// 根据前台传来的信息设定要更新的cmcredit信息
				cmcredit.setDescription(cmDescriptionList.get(i));
				cmcredit.setCredit(cmScoreList.get(i));
				cmcredit.setCstudentid(cmstudent.getCstudentid());
				cmcredit.setCmissionid(cmissionid);

				// 判断小组成员是否已评分
				if (cmstudent.getIsmarked().equals(false)) {
					cmcreditService.insertCmcredit(cmcredit);

					tempcmstudent.setId(cmstudent.getId());
					tempcmstudent.setIsmarked(true);
					cmstudentService.updateCmstudent(tempcmstudent);
				} else {
					// 根据cstudentid,cmissionid获取评分详细内容
					cmcreditDetailView = cmcreditService
							.getCmcreditDetailViewByCondition(
									cmstudent.getCstudentid(), cmissionid);
					cmcredit.setId(cmcreditDetailView.getId());
					cmcreditService.updateCmcredit(cmcredit);
				}
			}
			
			// 根据前台传来的cmissionid获取小组详细信息
			cmgroupDetailViews = cmgroupService
					.getCmgroupDetailViewListByCmissionidIsmarked(cmissionid);

			// 如果所有小组任务都评分完毕，就将cmission里的ismarked设置为"1"
			Integer isAllmarked = 1;
			for (int i = 0; i < cmgroupDetailViews.size(); i++) {
				if (cmgroupDetailViews.get(i).getIsmarked() == 0)
					isAllmarked = 0;
			}

			// 判断小组是否所有成员已评分
			if (isAllmarked == 1) {
				Cmission cmission = new Cmission();
				cmission.setId(cmstudent.getCmissionid());
				cmission.setIsmarked("1");
				cmissionService.updateCmission(cmission);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// 获取过程发生错误
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 提交个人学生评分
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

			// 如果所有个人任务都评分完毕，就将cmission里的ismarked设置为"1"
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
			// 获取过程发生错误
			return ERROR;
		}
		return SUCCESS;
	}

	
	/**
	 * 教师下载报告
	 * 
	 * @return
	 */
	public String downloadViewable() {
		try {
			// 获取cmreport
			Cmreport temp = cmreportService.getCmreport(cmreport.getId());

			this.fileFileName = temp.getViewablefilename();

			downloadFilePath = temp.getViewablefilepath() + file.separator
					+ fileFileName;

			// 该附件的输入流
			InputStream inputStream = ServletActionContext.getServletContext()
					.getResourceAsStream(downloadFilePath);

			this.fileFileName = new String(temp.getOldfilename().getBytes(),
					"ISO-8859-1");

			// 如果课程附件不存在
			if (inputStream == null) {
				return ERROR;
			}
		} catch (Exception e) {
			// 获取过程发生错误
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
