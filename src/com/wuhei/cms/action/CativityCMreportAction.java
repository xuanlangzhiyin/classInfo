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
 * �γ̻���γ���������񱨸�
 * 
 * 
 */
@SuppressWarnings("serial")
public class CativityCMreportAction extends CactivityCmissionAction {

	Logger logger = Logger.getLogger(CativityCMreportAction.class);

	/**
	 * ѧ��id
	 */
	private Integer studentid;

	/**
	 * �γ�ѧ��id
	 */
	private Integer cstudentid;

	/**
	 * �γ�ѧ������
	 */
	private Cstudent cstudent;

	/**
	 * �γ�����ѧ������
	 */
	private Cmstudent cmstudent;

	/**
	 * �γ�����ѧ�������б�
	 */
	private List<Cmstudent> cmstudents;

	/**
	 * �γ�����ѧ��id
	 */
	private Integer cmstudentid;

	/**
	 * �γ�����С��id
	 */
	private Integer cmgroupid;

	/**
	 * ����˵��
	 */
	private String description;
	
	/**
	 * �γ�����С��
	 */
	private Cmgroup cmgroup;

	/**
	 * ������ʾ�����������
	 */
	private String cmgroupName;

	/**
	 * ������ʾ������鳤����
	 */
	private String cmgroupLeader;

	/**
	 * ������ʾ�������Ա���ƣ���ʽ���� ����xx ��xx ��xx" ����֮���Կո����
	 */
	private String cmgroupMembers = "";

	/**
	 * �Ƿ���ʾ�ύ
	 */

	private Boolean isSubmitionShowed;

	/**
	 * ���񱨸�������ڴ洢��ǰ�ύ�ı���
	 */
	private Integer cmreportid;

	private Cmreport cmreport;


	private CmstudentService cmstudentService;

	private CmgroupService cmgroupService;

	private CmissionService cmissionService;

	private CmreportService cmreportService;



	private CourseService courseService;

	

	/**
	 * �����洢ǰ�˴������ļ�
	 */
	private File viewablefile;

	/**
	 * ���ﶨ���fileFileNameһ��Ҫ��xxxFileName����ʽ, �����޷�ȡ���ļ����ļ���.
	 * ����xxx���������涨���File���͵ı���һ��, ������涨�����File img,��ô����� ����ΪString imgFileName
	 */
	private String viewablefileFileName;

	


	// �����ļ��洢·��
	
	private String viewableFilepath = File.separator + "cmreport" + File.separator +"viewable";




	public String saveCMreport() throws IOException {


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
			// ����cstudentid��cmissionid ��ȡ�õ�½ѧ����cmstudent����
			cmstudent = cmstudentService.getCmstudentByCondition(cstudentid,
					cmissionid);
			// ��ȡ�γ̴��ţ�����ļ��洢·��ʱʹ��
			String ccode = courseService.getCourse(courseid).getCode();

			
			viewableFilepath += File.separator + ccode + File.separator + cmissionid + File.separator+ cstudentid+File.separator;

		
			// ���񱨸治����Ϊ��
			if (viewablefileFileName == null) {
				return ERROR;
			}
			
			// ���������ϴ����ļ�����
			String[] allowsuffix = { "pdf", "doc", "docx","xls","xlsx",
					"rar", "zip" };

			// �ϴ�����������ʱ�������
			String randName = AttachProcessing.saveAttach(viewableFilepath, allowsuffix, viewablefileFileName, viewablefile);
						
			// ����null˵���ļ��ϴ����ͳ���
			if(randName == "error"){
					return ERROR;
						}

			// ����cmreport
			cmreport = new Cmreport();
			cmreport.setName(cmission.getName());
			cmreport.setCstudentid(cstudentid);
			cmreport.setCmissionid(cmissionid);
			cmreport.setOldfilename(viewablefileFileName);
			cmreport.setViewablefilename(randName);
			cmreport.setViewablefilepath(viewableFilepath);
			// �жϸ�����ΪС������or��������
			if (cmstudent.getIsgrouped()) {
				// �ҵ���½ѧ�����ڵ���id
				cmgroupid = cmstudent.getCmgroupid();
				cmreport.setIsgroup(true);
				cmreport.setCmgroupid(cmgroupid);
			} else {
				cmreport.setIsgroup(false);
			}

		// ����һ��cmreport
		cmreportService.insertCmreport(cmreport);
		
		// ��¼cmreportid������cmreport��ʱ��ʹ��
		cmreportid = cmreport.getId();

		} catch (Exception e) {
			// ��δ�޸����ݿ�Ͳ����ļ���ֱ�ӷ���ERROR
			System.out.print(e);
			// ǰ̨ͨ��cmreport�Ƿ�Ϊ�վ�����ʾ�����������ύ���桱��ť
			cmreport = null;
			return ERROR;
		
				
			
		}

		return SUCCESS;
	}

	/**
	 * ǰ�˴�����cmissionid
	 * 
	 * @return ����ǰ�ˣ� cmission���󣬰����� mtype �������� stype �ύ��ʽ name �������� deadline
	 *         ��ֹʱ�� description ������ aname �������� apath ����Ŀ¼ ҳ����ʾ�õ�String��������
	 *         cmgroupName С������ cmgroupLeader �鳤���� cmgroupMembers
	 *         ��Ա�����������һ���ַ�������ʽ���� ����xx ��xx ��xx" ����֮���Կո����
	 */
	public String viewCmreport4Std() {

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
			// ����cstudentid��cmissionid ��ȡ�õ�½ѧ����cmstudent����
			cmstudent = cmstudentService.getCmstudentByCondition(cstudentid,
					cmissionid);
			// �����ѧ�����ѷ���ѧ��������Ҫ��ǰ�˴���С�����Ϣ�������������鳤������Ա��
			if (cmstudent.getIsgrouped()) {
				// �ҵ���½ѧ�����ڵ���id
				cmgroupid = cmstudent.getCmgroupid();
				cmgroup = cmgroupService.getCmgroupById(cmgroupid);
				cmgroupName = cmgroup.getName();
				cmgroupLeader = cmstudentService.getCmstudentById(
						cmgroup.getLeaderid()).getName();
				cmstudents = cmstudentService
						.getCmstudentListByCmgroupid(cmgroupid);

				// �鳤 + ��Ա
				for (int i = 0; i < cmstudents.size(); i++) {
					cmgroupMembers += " " + cmstudents.get(i).getName();
				}

				// ����Ҫ�жϱ����ύ��ʽ��1.С���ύ����ôֻ��һ�ݱ��棬ѧ���鿴�鳤�ύ�ı���
				// 2.�����ύ����ô�ж�ݱ��棬ѧ���鿴�Լ��ύ�ı���

				if (cmission.getStype().equalsIgnoreCase("��С���ύ")) {
					// ����ѧ������:�鳤�ύ����һ��
					cmreport = cmreportService.getCmreportByCondition(
							true, null, cmgroupid, cmissionid);
					if (cmreport==null) {
					} else {
						cmreportid = cmreport.getId();
					}
					// �жϸ�ѧ���ǲ����鳤ȷ���Ƿ���ʾ�ύ��ť
					if (cmstudent.getId().equals(cmgroup.getLeaderid())) {
						isSubmitionShowed = true;
					} else {
						isSubmitionShowed = false;
					}

				} else {
					// ����ѧ�����棺�����ύ���ж�ݣ���ֻ��ʾѧ���Լ��ύ���Ƿ�
					cmreport = cmreportService.getCmreportByCondition(
							true, cstudentid, cmgroupid, cmissionid);
					if (cmreport==null) {
					} else {
						cmreportid = cmreport.getId();
					}
					// һ��Ҫ��ʾ�ύ��ť
					isSubmitionShowed = true;
				}

			}
			// �����ѧ��δ���飬������Ϊ���������ύ��ʽҲ�ض�Ϊ�������ύ
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
	 * �������񱨸� 
	 * 
	 * @return
	 */
	public String cancelCMreport() {

		try {
			
			// ��ȡѧ���ύ�����񱨸�
			Cmreport temp = cmreportService.getCmreport(cmreportid);

			//��ȡ�����񱨸��Ӧ�Ŀγ�����
			cmissionid = temp.getCmissionid();
			
			String viewablepath = ServletActionContext.getServletContext()
					.getRealPath(temp.getViewablefilepath());
			// ɾ��viewable�ļ�
			deleteFolder(viewablepath);

			cmissionid = temp.getCmissionid();

			// ɾ�����ݿ��е����񱨸�
			cmreportService.deleteCmreport(temp.getId());


		} catch (Exception e) {
			System.out.print(e);
			// TODO: handle exception
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * ѧ�����ر����ļ� 
	 */
	public String downloadReport() {
		try {
			Cmreport temp = cmreportService.getCmreport(cmreportid);

			fileFileName = temp.getViewablefilename();

			downloadFilePath = temp.getViewablefilepath() + "/" + fileFileName;

			// �ø�����������
			InputStream inputStream = ServletActionContext.getServletContext()
					.getResourceAsStream(downloadFilePath);

			// ��ȡ���񸽼��ļ���:(֧������)
			this.fileFileName = new String(temp.getViewablefilename()
					.getBytes(), "ISO-8859-1");

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

	
	public String downloadAttach() {
		try {
			Cmission temp = cmissionService.getCmission(cmissionid);

			this.fileFileName = temp.getOldname();

			downloadFilePath = temp.getApath() + file.separator
					+ temp.getRandomname();

			// �ø�����������
			InputStream inputStream = ServletActionContext.getServletContext()
					.getResourceAsStream(downloadFilePath);

			this.fileFileName = new String(this.fileFileName.getBytes(),
					"ISO-8859-1");

			// ����γ̸���������
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
	 * �����ļ�·�����ļ��� �����ļ�����
	 * 
	 * @return
	 */

	public File createFile(String filePath, String fileName) {

		// ����һ���ļ�����
		File saveFile = new File(new File(filePath), fileName);

		if (!saveFile.getParentFile().exists())
			saveFile.getParentFile().mkdirs();

		return saveFile;
	}

	/**
	 * �����ļ�·�����ļ��� ɾ�������ļ�����
	 * 
	 * @return
	 */

	public void deleteFile(String filePath, String fileName) {

		File deleteFile = new File(new File(filePath), fileName);

		deleteFile.deleteOnExit();

	}

	/**
	 * �����ļ�·�� ɾ�������ļ���
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
