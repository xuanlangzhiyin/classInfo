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
 * �γ̻���γ�����
 * 
 * 
 */
@SuppressWarnings("serial")
public class CactivityCmissionAction extends CactivityAction {

	Logger logger = Logger.getLogger(CactivityCmissionAction.class);

	/**
	 * �洢���񸽼���·�� classInfo/file/course/intro
	 */
	private String attachPath = File.separator + "file" + File.separator
			+ "course" + File.separator + "mission"
			+ File.separator + "intro" + File.separator;
	
	/**
	 * ����id
	 */
	protected Integer cmissionid;
	
	/**
	 * ����
	 */
	protected Cmission cmission;
	
		
	protected Integer studentid;

	protected Integer cstudentid;

	protected Cstudent cstudent;


	public CmissionService cmissionService;
	
	
	/**
	 * ����ҳ�����ݣ��γ�����List
	 */
	public List<CmissionListView> cmissionList;
	
	
	private List<CmstudentDetailView> involvedCmstudentDetailViewList; 

	private CmstudentDetailViewService cmstudentDetailViewService;



	/**
	 * �γ������б�4��ʦ 
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
			//��ȡ��ǰ��¼ѧ��id
			studentid = CmsWebContext.getCurrentUser().getStudentid();
			//����studentid��courseid���õ�Ψһ��cstudent����Ҳ���Ǹõ�½ѧ�������ſ���γ��е�cstudent����
			cstudent = cmissionService.getCStudentByCondition(studentid, courseid);
			//��ȡcstudentid
			cstudentid = cstudent.getId();
			//����courseid+cstudentidΨһȷ����ǰѡ��ѧ���Ŀγ�����
			cmissionList = cmissionService.getCmissionList4Student(courseid, cstudentid);
	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return ERROR;
		}
			
		return SUCCESS;
	};
	
	/**
	 * ����С��γ�����
	 */
	
	public String addGroupMission() {
		
		return SUCCESS;
		
	}
	
	/**
	 *  ������������
	 */
	public String addPersonMission() {
		
		return SUCCESS;
		
	}
	
	/**
	 * ��������С��γ�����
	 * 
	 */
	public String saveGroupMission() {
		
		cmission.setTeacherid(CmsWebContext.getCurrentUser().getTeacherid());
		cmission.setCourseid(courseid);
		
		try {
			// ���ϴ�����ֱ�ӱ���cmission
			if(fileFileName == null){
				cmission.setMtype("С������");
				cmissionService.addCmission(cmission);
				cmissionid = cmission.getId();
				return SUCCESS;
			}
			
			/*
			 * �����ϴ�
			 */
			// �趨�������ϴ��ĸ�������
			String[] allowsuffix = {"doc", "docx", "pdf", "zip", "rar"};
			
			// �ϴ�����������ʱ�������
			String randName = AttachProcessing.saveAttach(attachPath, allowsuffix, fileFileName, file);
			
			// ����null˵���ļ��ϴ����ͳ���
			if(randName == "error"){
				return ERROR;
			}
			
			/*
			 * ����cmission��������
			 */
			cmission.setRandomname(randName);
			cmission.setOldname(FileHelper.setOldname(fileFileName));
			
			/*
			 * ����cmission��������
			 */
			cmission.setMtype("С������");
			cmission.setApath(attachPath);
			
			/*
			 * ����cmission
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
	 * �鿴С������
	 *
	 * ��ǰ̨����cmissionid��courseid
	 * @return
	 */
	public String viewGroupMission() {
		cmission=cmissionService.getCmission(cmissionid);
		//��ȡ�ÿγ̵�����С������
		cmissionList=cmissionService.getCmissionListViewByCondition(courseid, "С������");
		return SUCCESS;
	}	
	
	/**
	 * �鿴С������
	 * by: Ganwenbin
	 * ��ǰ̨����cmissionid
	 * @return
	 */
	public String changeIsActive() {
		// ����ǰ̨������cmissionid��ȡС��������Ϣ
		cmission = cmissionService.getCmission(cmissionid);
		
		// ����С�������isActive�Ƿ�����ύ״̬
		try{
			if(cmission.getIsactive().equals("���ύ")) {
				cmission.setIsactive("�����ύ");
				cmissionService.updateCmission(cmission);
			}
			else{
				cmission.setIsactive("���ύ");
				cmissionService.updateCmission(cmission);
			}
		}catch (Exception e) {
			
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/*
	 * ������������
	 * @return
	 */
	public String addPersonalMission() {
		
		return SUCCESS;
		
	}
	
	/**
	 * ����������������
	 * @return
	 */
	public String savePersonalMission() {
		
		
		
		cmission.setTeacherid(CmsWebContext.getCurrentUser().getTeacherid());
		cmission.setCourseid(courseid);
		
		try {
			// ���ϴ�����ֱ�ӱ���cmission
			if(fileFileName == null){
				cmission.setMtype("��������");
				cmissionService.addCmission4person(cmission);
				courseid = cmission.getCourseid();
				cmissionid = cmission.getId();
				involvedCmstudentDetailViewList = 
						cmstudentDetailViewService.getCmstudentDetailListByCondition(null, courseid, null, cmission.getId(), true);
				
				return SUCCESS;
			}
			
			/*
			 * �����ϴ�
			 */
			// �趨�������ϴ��ĸ�������
			String[] allowsuffix = {"doc", "docx", "pdf", "zip", "rar"};
			
			// �ϴ�����������ʱ�������
			String randName = AttachProcessing.saveAttach(attachPath, allowsuffix, fileFileName, file);
			
			// ����null˵���ļ��ϴ����ͳ���
			if(randName == "error"){
			
				return ERROR;
			}
			
			/*
			 * ����cmission��������
			 */
			cmission.setRandomname(randName);
			cmission.setOldname(FileHelper.setOldname(fileFileName));
			
			/*
			 * ����cmission
			 */
			cmission.setMtype("��������");
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
	 * ����������Ϣ����
	 * @return
	 */
	public String downloadAttach() {
		
		try {
			// ��ȡ����
			Cmission temp = cmissionService.getCmission(cmissionid);

			// ��ȡ���񸽼��ļ���:(֧������)
			this.fileFileName = new String(temp.getRandomname().getBytes(),
					"ISO-8859-1");
			
			downloadFilePath = attachPath + fileFileName;
			// �ø�����������
			InputStream inputStream = ServletActionContext.getServletContext()
					.getResourceAsStream(downloadFilePath);
			
			// ����γ̸���������
			if (inputStream == null) {
			
				return ERROR;
			}
		} catch (Exception e) {
		
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * �༭����С������
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
	 * �༭��������
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
	 * ����༭��Ŀγ�����
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
		 * �鿴��û���ϴ����� 
		 */
		if(file == null) {
			
	    	// ֱ�Ӹ���newcmission
			cmissionService.modifyCmission(newcmission);
			
	    	return SUCCESS;
	    }
		
		/*
		 * ������µĸ����ϴ�
		 */
		
		// �趨�������ϴ��ĸ�������
		String[] allowsuffix = {"doc", "docx", "pdf", "zip", "rar"};
		
		// �ϴ�����������ʱ�������
		String randName = AttachProcessing.saveAttach(attachPath, allowsuffix, fileFileName, file);
		
		// ����null˵���ļ��ϴ����ͳ���
		if(randName == "error"){
			
			return ERROR;
		}
		if(newcmission.getRandomname() != null) {
			try {
				File deletefile = new File(realpath + newcmission.getRandomname());
				// ɾ��֪ͨ�ļ�
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
	 * ɾ���γ����� 
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
