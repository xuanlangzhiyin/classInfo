package com.wuhei.cms.action;

import org.apache.log4j.Logger;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.wuhei.cms.fileprocessing.AttachProcessing;
import com.wuhei.cms.model.Cmgroup;
import com.wuhei.cms.model.Cmission;
import com.wuhei.cms.model.Cmreport;
import com.wuhei.cms.model.Cmstudent;

import com.wuhei.cms.model.Cstudent;

import com.wuhei.cms.service.cactivities.CmgroupService;
import com.wuhei.cms.service.cactivities.CmissionService;
import com.wuhei.cms.service.cactivities.CmreportService;
import com.wuhei.cms.service.cactivities.CmstudentService;
import com.wuhei.cms.service.cactivities.CourseService;
import com.wuhei.cms.web.context.CmsWebContext;


/**
 * 课程活动｜课程任务｜任务报告
 * 
 * 
 */
@SuppressWarnings("serial")
public class CativityCMreportAction extends CactivityCmissionAction {

	Logger logger = Logger.getLogger(CativityCMreportAction.class);

	/**
	 * 学生id
	 */
	private Integer studentid;

	/**
	 * 课程学生id
	 */
	private Integer cstudentid;

	/**
	 * 课程学生对象
	 */
	private Cstudent cstudent;

	/**
	 * 课程任务学生对象
	 */
	private Cmstudent cmstudent;

	/**
	 * 课程任务学生对象列表
	 */
	private List<Cmstudent> cmstudents;

	/**
	 * 课程任务学生id
	 */
	private Integer cmstudentid;

	/**
	 * 课程任务小组id
	 */
	private Integer cmgroupid;

	/**
	 * 报告说明
	 */
	private String description;
	
	/**
	 * 课程任务小组
	 */
	private Cmgroup cmgroup;

	/**
	 * 用于显示任务分组名称
	 */
	private String cmgroupName;

	/**
	 * 用于显示任务的组长名称
	 */
	private String cmgroupLeader;

	/**
	 * 用于显示任务的组员名称，格式类似 “张xx 王xx 李xx" 名字之间以空格隔开
	 */
	private String cmgroupMembers = "";

	/**
	 * 是否显示提交
	 */

	private Boolean isSubmitionShowed;

	/**
	 * 任务报告对象，用于存储当前提交的报告
	 */
	private Integer cmreportid;

	private Cmreport cmreport;


	private CmstudentService cmstudentService;

	private CmgroupService cmgroupService;

	private CmissionService cmissionService;

	private CmreportService cmreportService;



	private CourseService courseService;

	

	/**
	 * 用来存储前端传来的文件
	 */
	private File viewablefile;

	/**
	 * 这里定义的fileFileName一定要是xxxFileName的形式, 否则无法取到文件的文件名.
	 * 其中xxx必须与上面定义的File类型的变量一致, 如果上面定义的是File img,那么这里就 定义为String imgFileName
	 */
	private String viewablefileFileName;

	


	// 定义文件存储路径
	
	private String viewableFilepath = File.separator + "cmreport" + File.separator +"viewable";




	public String saveCMreport() throws IOException {


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
			// 根据cstudentid和cmissionid 获取该登陆学生的cmstudent对象
			cmstudent = cmstudentService.getCmstudentByCondition(cstudentid,
					cmissionid);
			// 获取课程代号，填充文件存储路径时使用
			String ccode = courseService.getCourse(courseid).getCode();

			
			viewableFilepath += File.separator + ccode + File.separator + cmissionid + File.separator+ cstudentid+File.separator;

		
			// 任务报告不允许为空
			if (viewablefileFileName == null) {
				return ERROR;
			}
			
			// 声明允许上传的文件类型
			String[] allowsuffix = { "pdf", "doc", "docx","xls","xlsx",
					"rar", "zip" };

			// 上传附件，返回时间戳名字
			String randName = AttachProcessing.saveAttach(viewableFilepath, allowsuffix, viewablefileFileName, viewablefile);
						
			// 返回null说明文件上传类型出错
			if(randName == "error"){
					return ERROR;
						}

			// 构造cmreport
			cmreport = new Cmreport();
			cmreport.setName(cmission.getName());
			cmreport.setCstudentid(cstudentid);
			cmreport.setCmissionid(cmissionid);
			cmreport.setOldfilename(viewablefileFileName);
			cmreport.setViewablefilename(randName);
			cmreport.setViewablefilepath(viewableFilepath);
			// 判断该任务为小组任务or个人任务
			if (cmstudent.getIsgrouped()) {
				// 找到登陆学生所在的组id
				cmgroupid = cmstudent.getCmgroupid();
				cmreport.setIsgroup(true);
				cmreport.setCmgroupid(cmgroupid);
			} else {
				cmreport.setIsgroup(false);
			}

		// 插入一条cmreport
		cmreportService.insertCmreport(cmreport);
		
		// 记录cmreportid，更新cmreport的时候使用
		cmreportid = cmreport.getId();

		} catch (Exception e) {
			// 暂未修改数据库和操作文件，直接返回ERROR
			System.out.print(e);
			// 前台通过cmreport是否为空决定显示“撤销”“提交报告”按钮
			cmreport = null;
			return ERROR;
		
				
			
		}

		return SUCCESS;
	}

	/**
	 * 前端传来：cmissionid
	 * 
	 * @return 传给前端： cmission对象，包括： mtype 任务类型 stype 提交方式 name 任务名称 deadline
	 *         截止时间 description 人物简介 aname 附件名称 apath 附件目录 页面显示用的String，包括：
	 *         cmgroupName 小组名称 cmgroupLeader 组长姓名 cmgroupMembers
	 *         组员姓名（这个是一个字符串，格式类似 “张xx 王xx 李xx" 名字之间以空格隔开
	 */
	public String viewCmreport4Std() {

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
			// 根据cstudentid和cmissionid 获取该登陆学生的cmstudent对象
			cmstudent = cmstudentService.getCmstudentByCondition(cstudentid,
					cmissionid);
			// 如果该学生是已分组学生，则需要给前端传该小组的信息，包括组名，组长名，组员名
			if (cmstudent.getIsgrouped()) {
				// 找到登陆学生所在的组id
				cmgroupid = cmstudent.getCmgroupid();
				cmgroup = cmgroupService.getCmgroupById(cmgroupid);
				cmgroupName = cmgroup.getName();
				cmgroupLeader = cmstudentService.getCmstudentById(
						cmgroup.getLeaderid()).getName();
				cmstudents = cmstudentService
						.getCmstudentListByCmgroupid(cmgroupid);

				// 组长 + 成员
				for (int i = 0; i < cmstudents.size(); i++) {
					cmgroupMembers += " " + cmstudents.get(i).getName();
				}

				// 这里要判断报告提交方式：1.小组提交，那么只有一份报告，学生查看组长提交的报告
				// 2.个人提交，那么有多份报告，学生查看自己提交的报告

				if (cmission.getStype().equalsIgnoreCase("按小组提交")) {
					// 加载学生报告:组长提交，仅一份
					cmreport = cmreportService.getCmreportByCondition(
							true, null, cmgroupid, cmissionid);
					if (cmreport==null) {
					} else {
						cmreportid = cmreport.getId();
					}
					// 判断该学生是不是组长确认是否显示提交按钮
					if (cmstudent.getId().equals(cmgroup.getLeaderid())) {
						isSubmitionShowed = true;
					} else {
						isSubmitionShowed = false;
					}

				} else {
					// 加载学生报告：个人提交，有多份，但只显示学生自己提交的那份
					cmreport = cmreportService.getCmreportByCondition(
							true, cstudentid, cmgroupid, cmissionid);
					if (cmreport==null) {
					} else {
						cmreportid = cmreport.getId();
					}
					// 一定要显示提交按钮
					isSubmitionShowed = true;
				}

			}
			// 如果该学生未分组，则任务为个人任务，提交方式也必定为按个人提交
			else {
				cmreport = cmreportService.getCmreportByCondition(false,
						cstudentid, null, cmissionid);
				if (cmreport==null) {
				} else {
					cmreportid = cmreport.getId();
				}
				isSubmitionShowed = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 撤销任务报告 
	 * 
	 * @return
	 */
	public String cancelCMreport() {

		try {
			
			// 获取学生提交的任务报告
			Cmreport temp = cmreportService.getCmreport(cmreportid);

			//获取该任务报告对应的课程任务
			cmissionid = temp.getCmissionid();
			
			String viewablepath = ServletActionContext.getServletContext()
					.getRealPath(temp.getViewablefilepath());
			// 删除viewable文件
			deleteFolder(viewablepath);

			cmissionid = temp.getCmissionid();

			// 删除数据库中的任务报告
			cmreportService.deleteCmreport(temp.getId());


		} catch (Exception e) {
			System.out.print(e);
			// TODO: handle exception
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 学生下载报告文件 
	 */
	public String downloadReport() {
		try {
			Cmreport temp = cmreportService.getCmreport(cmreportid);

			fileFileName = temp.getViewablefilename();

			downloadFilePath = temp.getViewablefilepath() + "/" + fileFileName;

			// 该附件的输入流
			InputStream inputStream = ServletActionContext.getServletContext()
					.getResourceAsStream(downloadFilePath);

			// 获取任务附件文件名:(支持中文)
			this.fileFileName = new String(temp.getViewablefilename()
					.getBytes(), "ISO-8859-1");

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

	
	public String downloadAttach() {
		try {
			Cmission temp = cmissionService.getCmission(cmissionid);

			this.fileFileName = temp.getOldname();

			downloadFilePath = temp.getApath() + file.separator
					+ temp.getRandomname();

			// 该附件的输入流
			InputStream inputStream = ServletActionContext.getServletContext()
					.getResourceAsStream(downloadFilePath);

			this.fileFileName = new String(this.fileFileName.getBytes(),
					"ISO-8859-1");

			// 如果课程附件不存在
			if (inputStream == null) {
				return ERROR;
			}

		} catch (Exception e) {
			// TODO: handle exception
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 输入文件路径，文件名 创建文件对象
	 * 
	 * @return
	 */

	public File createFile(String filePath, String fileName) {

		// 创建一个文件对象
		File saveFile = new File(new File(filePath), fileName);

		if (!saveFile.getParentFile().exists())
			saveFile.getParentFile().mkdirs();

		return saveFile;
	}

	/**
	 * 输入文件路径，文件名 删除单个文件对象
	 * 
	 * @return
	 */

	public void deleteFile(String filePath, String fileName) {

		File deleteFile = new File(new File(filePath), fileName);

		deleteFile.deleteOnExit();

	}

	/**
	 * 输入文件路径 删除整个文件夹
	 * 
	 * @return
	 */

	public void deleteFolder(String filePath) {

		File errorfile = new File(filePath);
		File filelist[] = errorfile.listFiles();
		if (filelist.length != 0) {
			for (int i = 0; i < filelist.length; i++)
				filelist[i].delete();
			errorfile.deleteOnExit();
		}
	}

	/*
	 * getters and setters
	 */

	public File getViewablefile() {
		return viewablefile;
	}

	public void setViewablefile(File viewablefile) {
		this.viewablefile = viewablefile;
	}

	public String getViewablefileFileName() {
		return viewablefileFileName;
	}

	public void setViewablefileFileName(String viewablefileFileName) {
		this.viewablefileFileName = viewablefileFileName;
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

	public Cmstudent getCmstudent() {
		return cmstudent;
	}

	public void setCmstudent(Cmstudent cmstudent) {
		this.cmstudent = cmstudent;
	}

	public List<Cmstudent> getCmstudents() {
		return cmstudents;
	}

	public void setCmstudents(List<Cmstudent> cmstudents) {
		this.cmstudents = cmstudents;
	}

	public Integer getCmstudentid() {
		return cmstudentid;
	}

	public void setCmstudentid(Integer cmstudentid) {
		this.cmstudentid = cmstudentid;
	}

	public Integer getCmgroupid() {
		return cmgroupid;
	}

	public void setCmgroupid(Integer cmgroupid) {
		this.cmgroupid = cmgroupid;
	}

	public Cmgroup getCmgroup() {
		return cmgroup;
	}

	public void setCmgroup(Cmgroup cmgroup) {
		this.cmgroup = cmgroup;
	}

	public String getCmgroupName() {
		return cmgroupName;
	}

	public void setCmgroupName(String cmgroupName) {
		this.cmgroupName = cmgroupName;
	}

	public String getCmgroupLeader() {
		return cmgroupLeader;
	}

	public void setCmgroupLeader(String cmgroupLeader) {
		this.cmgroupLeader = cmgroupLeader;
	}

	public String getCmgroupMembers() {
		return cmgroupMembers;
	}

	public void setCmgroupMembers(String cmgroupMembers) {
		this.cmgroupMembers = cmgroupMembers;
	}


	public String getViewableFilepath() {
		return viewableFilepath;
	}

	public void setViewableFilepath(String viewableFilepath) {
		this.viewableFilepath = viewableFilepath;
	}

	

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public Cmreport getCmreport() {
		return cmreport;
	}

	public void setCmreport(Cmreport cmreport) {
		this.cmreport = cmreport;
	}

	public CmstudentService getCmstudentService() {
		return cmstudentService;
	}

	public void setCmstudentService(CmstudentService cmstudentService) {
		this.cmstudentService = cmstudentService;
	}

	public CmgroupService getCmgroupService() {
		return cmgroupService;
	}

	public void setCmgroupService(CmgroupService cmgroupService) {
		this.cmgroupService = cmgroupService;
	}

	public CmissionService getCmissionService() {
		return cmissionService;
	}

	public void setCmissionService(CmissionService cmissionService) {
		this.cmissionService = cmissionService;
	}

	public CmreportService getCmreportService() {
		return cmreportService;
	}

	public void setCmreportService(CmreportService cmreportService) {
		this.cmreportService = cmreportService;
	}

	public Integer getCmreportid() {
		return cmreportid;
	}

	public void setCmreportid(Integer cmreportid) {
		this.cmreportid = cmreportid;
	}


	public Boolean getIsSubmitionShowed() {
		return isSubmitionShowed;
	}

	public void setIsSubmitionShowed(Boolean isSubmitionShowed) {
		this.isSubmitionShowed = isSubmitionShowed;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
