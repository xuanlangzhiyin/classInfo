package com.wuhei.cms.action;

import com.wuhei.cms.fileprocessing.AttachProcessing;
import com.wuhei.cms.fileprocessing.FileHelper;
import com.wuhei.cms.model.Cmission;

import com.wuhei.cms.model.Cstudent;
import com.wuhei.cms.model.joint.CmissionListView;
import com.wuhei.cms.model.joint.CmstudentDetailView;
import com.wuhei.cms.service.cactivities.CmissionService;
import com.wuhei.cms.service.cactivities.CmstudentDetailViewService;
import com.wuhei.cms.web.context.CmsWebContext;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * 课程活动｜课程任务
 * 
 * 
 */
@SuppressWarnings("serial")
public class CactivityCmissionAction extends CactivityAction {

	Logger logger = Logger.getLogger(CactivityCmissionAction.class);

	/**
	 * 存储任务附件的路径 classInfo/file/course/intro
	 */
	private String attachPath = File.separator + "file" + File.separator
			+ "course" + File.separator + "mission"
			+ File.separator + "intro" + File.separator;
	
	/**
	 * 任务id
	 */
	protected Integer cmissionid;
	
	/**
	 * 任务
	 */
	protected Cmission cmission;
	
		
	protected Integer studentid;

	protected Integer cstudentid;

	protected Cstudent cstudent;


	public CmissionService cmissionService;
	
	
	/**
	 * 返回页面数据：课程任务List
	 */
	public List<CmissionListView> cmissionList;
	
	
	private List<CmstudentDetailView> involvedCmstudentDetailViewList; 

	private CmstudentDetailViewService cmstudentDetailViewService;



	/**
	 * 课程任务列表4教师 
	 * 
	 * @return
	 */
	
	public String listCmission4Teacher() {

		cmissionList = cmissionService.getCmissionList4Teacher(courseid);

		return SUCCESS;

	};
	
	/**
	 * listCmission4std 
	 * @return
	 */
	
	public String listCmission4Student(){
		try {
			//获取当前登录学生id
			studentid = CmsWebContext.getCurrentUser().getStudentid();
			//根据studentid和courseid，拿到唯一的cstudent对象，也就是该登陆学生在这门开设课程中的cstudent对象
			cstudent = cmissionService.getCStudentByCondition(studentid, courseid);
			//获取cstudentid
			cstudentid = cstudent.getId();
			//根据courseid+cstudentid唯一确定当前选课学生的课程任务
			cmissionList = cmissionService.getCmissionList4Student(courseid, cstudentid);
	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return ERROR;
		}
			
		return SUCCESS;
	};
	
	/**
	 * 新增小组课程任务
	 */
	
	public String addGroupMission() {
		
		return SUCCESS;
		
	}
	
	/**
	 *  新增个人任务
	 */
	public String addPersonMission() {
		
		return SUCCESS;
		
	}
	
	/**
	 * 保存新增小组课程任务
	 * 
	 */
	public String saveGroupMission() {
		
		cmission.setTeacherid(CmsWebContext.getCurrentUser().getTeacherid());
		cmission.setCourseid(courseid);
		
		try {
			// 无上传附件直接保存cmission
			if(fileFileName == null){
				cmission.setMtype("小组任务");
				cmissionService.addCmission(cmission);
				cmissionid = cmission.getId();
				return SUCCESS;
			}
			
			/*
			 * 附件上传
			 */
			// 设定可允许上传的附件类型
			String[] allowsuffix = {"doc", "docx", "pdf", "zip", "rar"};
			
			// 上传附件，返回时间戳名字
			String randName = AttachProcessing.saveAttach(attachPath, allowsuffix, fileFileName, file);
			
			// 返回null说明文件上传类型出错
			if(randName == "error"){
				return ERROR;
			}
			
			/*
			 * 更新cmission附件名字
			 */
			cmission.setRandomname(randName);
			cmission.setOldname(FileHelper.setOldname(fileFileName));
			
			/*
			 * 设置cmission任务类型
			 */
			cmission.setMtype("小组任务");
			cmission.setApath(attachPath);
			
			/*
			 * 保存cmission
			 */
			cmissionService.addCmission(cmission);
			
			cmissionid = cmission.getId();
			
		} catch (Exception e) {
			// TODO: handle exception
			return ERROR;
		}
		
		return SUCCESS;
	}
	/**
	 * 查看小组任务
	 *
	 * 从前台传来cmissionid和courseid
	 * @return
	 */
	public String viewGroupMission() {
		cmission=cmissionService.getCmission(cmissionid);
		//获取该课程的所有小组任务
		cmissionList=cmissionService.getCmissionListViewByCondition(courseid, "小组任务");
		return SUCCESS;
	}	
	
	/**
	 * 查看小组任务
	 * by: Ganwenbin
	 * 从前台传来cmissionid
	 * @return
	 */
	public String changeIsActive() {
		// 根据前台传来的cmissionid获取小组任务信息
		cmission = cmissionService.getCmission(cmissionid);
		
		// 更改小组任务的isActive是否可以提交状态
		try{
			if(cmission.getIsactive().equals("可提交")) {
				cmission.setIsactive("不可提交");
				cmissionService.updateCmission(cmission);
			}
			else{
				cmission.setIsactive("可提交");
				cmissionService.updateCmission(cmission);
			}
		}catch (Exception e) {
			
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/*
	 * 新增个人任务
	 * @return
	 */
	public String addPersonalMission() {
		
		return SUCCESS;
		
	}
	
	/**
	 * 保存新增个人任务
	 * @return
	 */
	public String savePersonalMission() {
		
		
		
		cmission.setTeacherid(CmsWebContext.getCurrentUser().getTeacherid());
		cmission.setCourseid(courseid);
		
		try {
			// 无上传附件直接保存cmission
			if(fileFileName == null){
				cmission.setMtype("个人任务");
				cmissionService.addCmission4person(cmission);
				courseid = cmission.getCourseid();
				cmissionid = cmission.getId();
				involvedCmstudentDetailViewList = 
						cmstudentDetailViewService.getCmstudentDetailListByCondition(null, courseid, null, cmission.getId(), true);
				
				return SUCCESS;
			}
			
			/*
			 * 附件上传
			 */
			// 设定可允许上传的附件类型
			String[] allowsuffix = {"doc", "docx", "pdf", "zip", "rar"};
			
			// 上传附件，返回时间戳名字
			String randName = AttachProcessing.saveAttach(attachPath, allowsuffix, fileFileName, file);
			
			// 返回null说明文件上传类型出错
			if(randName == "error"){
			
				return ERROR;
			}
			
			/*
			 * 更新cmission附件名字
			 */
			cmission.setRandomname(randName);
			cmission.setOldname(FileHelper.setOldname(fileFileName));
			
			/*
			 * 保存cmission
			 */
			cmission.setMtype("个人任务");
			cmission.setApath(attachPath);
			cmissionService.addCmission4person(cmission);
			courseid = cmission.getCourseid();
			cmissionid = cmission.getId();
			involvedCmstudentDetailViewList = 
					cmstudentDetailViewService.getCmstudentDetailListByCondition(null, courseid, null, cmission.getId(), true);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			return ERROR;
		}
		
	
		return SUCCESS;	
	}
	
	/**
	 * 
	 * 下载任务信息附件
	 * @return
	 */
	public String downloadAttach() {
		
		try {
			// 获取任务
			Cmission temp = cmissionService.getCmission(cmissionid);

			// 获取任务附件文件名:(支持中文)
			this.fileFileName = new String(temp.getRandomname().getBytes(),
					"ISO-8859-1");
			
			downloadFilePath = attachPath + fileFileName;
			// 该附件的输入流
			InputStream inputStream = ServletActionContext.getServletContext()
					.getResourceAsStream(downloadFilePath);
			
			// 如果课程附件不存在
			if (inputStream == null) {
			
				return ERROR;
			}
		} catch (Exception e) {
		
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 编辑任务小组任务
	 * 
	 * @return
	 */
	public String editGroupMission() {
		
		try {
			
			cmission = cmissionService.getCmission(cmissionid);
			
		} catch (Exception e) {
			
			return ERROR;
			
		}
		
		return SUCCESS;
		
	}
	
	/**
	 * 编辑个人任务
	 * 
	 * @return
	 */
	public String editPersonalMission() {
		
		try {
			
			cmission = cmissionService.getCmission(cmissionid);
			
		} catch (Exception e) {
			
			return ERROR;
			
		}
		
		return SUCCESS;
		
	}
	
	/**
	 * 保存编辑后的课程任务
	 * 
	 * @return
	 */
	public String saveEditMission() {
		
		Cmission newcmission = cmissionService.getCmission(cmissionid);
		
		try{
			newcmission.setDeadline(cmission.getDeadline());
			newcmission.setStype(cmission.getStype());
			newcmission.setRequirement(cmission.getRequirement());
			newcmission.setCourseid(cmission.getCourseid());
		} catch (Exception e) {
			return ERROR;
		}
		
		String realpath = ServletActionContext.getServletContext().getRealPath(attachPath);
		
		/*
		 * 查看有没有上传附件 
		 */
		if(file == null) {
			
	    	// 直接更新newcmission
			cmissionService.modifyCmission(newcmission);
			
	    	return SUCCESS;
	    }
		
		/*
		 * 如果有新的附件上传
		 */
		
		// 设定可允许上传的附件类型
		String[] allowsuffix = {"doc", "docx", "pdf", "zip", "rar"};
		
		// 上传附件，返回时间戳名字
		String randName = AttachProcessing.saveAttach(attachPath, allowsuffix, fileFileName, file);
		
		// 返回null说明文件上传类型出错
		if(randName == "error"){
			
			return ERROR;
		}
		if(newcmission.getRandomname() != null) {
			try {
				File deletefile = new File(realpath + newcmission.getRandomname());
				// 删除通知文件
				if (deletefile.isFile() && deletefile.exists()) {
					deletefile.delete();
				}
			} catch (Exception e) {
				// do nothing
			}
		}
		
		newcmission.setOldname(FileHelper.setOldname(fileFileName));
		newcmission.setRandomname(randName);
		
		try {
			cmissionService.modifyCmission(newcmission);
		} catch (Exception e) {
			
			
			return ERROR;
			// TODO: handle exception
		}
		
		return SUCCESS;
	}

	/**
	 * 删除课程任务 
	 * 
	 * @return
	 */
	public String deleteCmission() {

		try {

			cmissionService.deleteCmission(cmissionid);

		

			return SUCCESS;
		} catch (Exception e) {

			

			return ERROR;
		}
	}

	/*
	 * getters and setters
	 */
	
	public Cmission getCmission() {
		return cmission;
	}


	public void setCmission(Cmission cmission) {
		this.cmission = cmission;
	}


	public Integer getCmissionid() {
		return cmissionid;
	}

	public void setCmissionid(Integer cmissionid) {
		this.cmissionid = cmissionid;
	}

	public CmissionService getCmissionService() {
		return cmissionService;
	}

	public void setCmissionService(CmissionService cmissionService) {
		this.cmissionService = cmissionService;
	}

	public List<CmissionListView> getCmissionList() {
		return cmissionList;
	}

	public void setCmissionList(List<CmissionListView> cmissionList) {
		this.cmissionList = cmissionList;
	}

	public CmstudentDetailViewService getCmstudentDetailViewService() {
		return cmstudentDetailViewService;
	}


	public void setCmstudentDetailViewService(
			CmstudentDetailViewService cmstudentDetailViewService) {
		this.cmstudentDetailViewService = cmstudentDetailViewService;
	}


	public List<CmstudentDetailView> getInvolvedCmstudentDetailViewList() {
		return involvedCmstudentDetailViewList;
	}


	public void setInvolvedCmstudentDetailViewList(
			List<CmstudentDetailView> involvedCmstudentDetailViewList) {
		this.involvedCmstudentDetailViewList = involvedCmstudentDetailViewList;
	}
	
	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public Integer getCstudentid() {
		return cstudentid;
	}

	public void setCstudentid(Integer cstudentid) {
		this.cstudentid = cstudentid;
	}

	public Cstudent getCstudent() {
		return cstudent;
	}

	public void setCstudent(Cstudent cstudent) {
		this.cstudent = cstudent;
	}

}
