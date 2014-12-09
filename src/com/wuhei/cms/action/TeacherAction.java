package com.wuhei.cms.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.wuhei.cms.model.Teacher;
import com.wuhei.cms.model.deletedata.TeacherDeleteResult;
import com.wuhei.cms.model.joint.TeacherDetailView;
import com.wuhei.cms.search.result.TeacherPageResult;
import com.wuhei.cms.search.TeacherSearchMeta;
import com.wuhei.cms.service.basic.TeacherService;
import com.wuhei.cms.web.context.CmsWebContext;

/**
 * 
 * ��ʦ���������
 * 
 * 
 */
@SuppressWarnings("serial")
public class TeacherAction extends BaseAction {

	Logger log = Logger.getLogger(TeacherAction.class);

	/**
	 * ��ʦ����ҵ������ӿ�
	 */
	protected TeacherService teacherService;

	/**
	 * ҳ�������������ʦid
	 */
	protected Integer tearcherid;

	/**
	 * ҳ�������������ʦ
	 */
	protected Teacher teacher;



	/**
	 * ����ҳ��������ʦ��ϸ��ͼ
	 */
	protected TeacherDetailView teacherDetailView;
	
	
	/**
	 * ҳ�����������Ҫɾ���Ľ�ʦid
	 */
	protected List<Integer> teachersId;

	/**
	 * ����ҳ�������۱�ѧԺ�ģݽ�ʦ�б� ǰ̨��ҳʹ��
	 */
	protected List<Teacher> teachers;

	/**
	 * ҳ�������������ʦ�б��ѯ���� ��̨��ҳʹ��
	 */
	protected TeacherSearchMeta teacherSearchMeta;

	/**
	 * ����ҳ������������ҳ��Ϣ�Ľ�ʦ�б����� ��̨��ҳʹ��
	 */
	protected TeacherPageResult teacherPageResult;

	/**
	 * ����Ա���鿴��ѧԺ��ʦ�б� ������ҳ
	 * 
	 * @return /acamgr/listTeacher.jsp
	 */
	
	

	/**
	 * ����ҳ����-ɾ�����ɹ��Ľ�ʦ��Ϣ
	 */
	protected TeacherDeleteResult teacherDeleteResult;
	
	
	
	
	public String listTeacher4Acamgr() {

		internalGetTeachers();

		log.info("����Ա�û�����ѯ��ʦ�б�");

		return SUCCESS;
	}

	/**
	 * ѧ�����鿴��ѧԺ��ʦ�б� ��̨��ҳ
	 * 
	 * @return /student/listTeacher.jsp
	 */
	public String listTeacher4Student() {

		internalGetTeachers();

		log.info("ѧ���û�����ѯ��ʦ�б�");

		return SUCCESS;
	}

	/**
	 * ��ʦ���鿴��ѧԺ��ʦ�б� ��̨��ҳ
	 * 
	 * @return /teacher/listTeacher.jsp
	 */
	public String listTeacher4Teacher() {

		internalGetTeachers();

		log.info("��ʦ�û�����ѯ��ʦ�б�");

		return SUCCESS;
	}

	private void internalGetTeachers() {
		// Ĭ�ϲ�ѯ����
		Integer currentPage = 1;
		String keyword = null;

		// ��ȡҳ�������ѯ����
		if (teacherSearchMeta != null) {
			currentPage = teacherSearchMeta.getCurrentPage();
			keyword = this.getDefaultSearchValue(
					teacherSearchMeta.getKeyword(), null);
		}

		// ��ȡ��½�û�������Ժϵid
		Integer departmentId = CmsWebContext.getCurrentDepartmentId();

		// �����ݿ��ȡ����������ʦ
		teacherPageResult = teacherService.getTeacherListByConditions(
				departmentId, keyword, currentPage);
	}

	


	/**
	 * ����Ա��ɾ����ʦ
	 * 
	 */
	
	public String deleteTeacher() {
		//���û��ѡ��Ҫɾ������ʦ
		if(teachersId==null) {

		}
		else{
			teacherDeleteResult= teacherService.deleteTeachers(teachersId);
		    }
		return SUCCESS;
	}
	
	

	/*
	 * getters and setters
	 */

	public TeacherDeleteResult getTeacherDeleteResult() {
		return teacherDeleteResult;
	}

	public void setTeacherDeleteResult(TeacherDeleteResult teacherDeleteResult) {
		this.teacherDeleteResult = teacherDeleteResult;
	}
	public List<Integer> getTeachersId() {
		return teachersId;
	}

	public void setTeachersId(List<Integer> teachersId) {
		this.teachersId = teachersId;
	}
	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public TeacherPageResult getTeacherPageResult() {
		return teacherPageResult;
	}

	public void setTeacherPageResult(TeacherPageResult teacherPageResult) {
		this.teacherPageResult = teacherPageResult;
	}

	public Integer getTearcherid() {
		return tearcherid;
	}

	public void setTearcherid(Integer tearcherid) {
		this.tearcherid = tearcherid;
	}

	public TeacherDetailView getTeacherDetailView() {
		return teacherDetailView;
	}

	public void setTeacherDetailView(TeacherDetailView teacherDetailView) {
		this.teacherDetailView = teacherDetailView;
	}

	public TeacherSearchMeta getTeacherSearchMeta() {
		return teacherSearchMeta;
	}

	public void setTeacherSearchMeta(TeacherSearchMeta teacherSearchMeta) {
		this.teacherSearchMeta = teacherSearchMeta;
	}
}
