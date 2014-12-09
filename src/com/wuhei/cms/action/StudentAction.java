package com.wuhei.cms.action;

import java.util.List;
import org.apache.log4j.Logger;
import com.wuhei.cms.model.Major;
import com.wuhei.cms.model.Student;
import com.wuhei.cms.model.deletedata.StudentDeleteResult;
import com.wuhei.cms.model.joint.StudentDetailView;
import com.wuhei.cms.model.joint.StudentListView;
import com.wuhei.cms.search.result.StudentPageResult;
import com.wuhei.cms.search.StudentSearchMeta;
import com.wuhei.cms.service.basic.StudentService;
import com.wuhei.cms.web.context.CmsWebContext;

/**
 * 
 * ѧ�����������
 * 
 * 
 */
@SuppressWarnings("serial")
public class StudentAction extends BaseAction {

	Logger log = Logger.getLogger(StudentAction.class);

	protected StudentService studentService;

	/**
	 * ҳ�����������ѧ��id
	 */
	protected Integer studentid;

	/**
	 * ҳ�����������ѧ��
	 */
	protected Student student;

	/**
	 * ѧ����ϸ��ͼ
	 */
	protected StudentDetailView studentDetailView;

	/**
	 * ����ҳ������ѧ���б�
	 */
	protected List<StudentListView> students;

	/**
	 * ҳ�����������ѧ���б��ѯ����
	 */
	protected StudentSearchMeta studentSearchMeta;

	/**
	 * ����ҳ������������ҳ��Ϣ��ѧ���б�����
	 */
	protected StudentPageResult studentPageResult;

	/**
	 * ҳ���ӡרҵ��������
	 */
	protected List<Major> majors;
	
	/**
	 * ҳ���������-Ҫɾ����ѧ��id
	 */
	protected List<Integer> studentsId;
	
	/**
	 * ����ҳ����-ɾ��ʧ�ܵ�ѧ����Ϣ
	 */
	protected StudentDeleteResult studentDeleteResult;

	/**
	 * ����Ա���鿴��ѧԺѧ���б� ��̨��ҳ
	 * 
	 * @return /acamgr/listStudent.jsp
	 */
	public String listStudent4Acamgr() {
		
		internalGetStudents();

		log.info("����Ա�û�����ѯѧ���б�");

		// ����רҵ�б�ҳ����������ѧ����Ҫ
		Integer departmentid = CmsWebContext.getCurrentDepartmentId();
		majors = CmsWebContext.getCurrentMajorList();
		
		return SUCCESS;
	}

	/**
	 * ��ʦ���鿴��ѧԺѧ���б� ��̨��ҳ
	 * 
	 * @return /teacher/listStudent.jsp
	 */
	public String listStudent4Teacher() {

		internalGetStudents();
		
		log.info("��ʦ�û�����ѯѧ���б�");

		return SUCCESS;
	}

	/**
	 * ѧ�����鿴��ѧԺѧ���б� ��̨��ҳ
	 * 
	 * @return /student/listStudent.jsp
	 */
	public String listStudent4Student() {
		
		internalGetStudents();
		
		log.info("ѧ���û�����ѯѧ���б�");
		
		return SUCCESS;
	}

	private void internalGetStudents() {
		// strutsĬ��converter�Ὣǰ��value=""��string����ֵΪ""
		// ��<input name="keyword" value="">����<input name="keyword">
		// ���ύ��ʱ��̨�õ���keywordΪ""������null

		// Ĭ�ϲ�ѯ����
		Integer currentPage = 1;
		String keyword = null;
		String grade = null;
		String stuclass = null;
		Integer majorid = null;
		// ��ȡҳ�������ѯ����
		if (studentSearchMeta != null) {
			majorid = studentSearchMeta.getMajorid();
			currentPage = studentSearchMeta.getCurrentPage();
			keyword = this.getDefaultSearchValue(
					studentSearchMeta.getKeyword(), null);
			grade = this.getDefaultSearchValue(studentSearchMeta.getStdgrade(),
					null);
			stuclass = this.getDefaultSearchValue(
					studentSearchMeta.getStdclass(), null);
		}
		
		// ��ȡ��ѯ���
		studentPageResult = studentService.listStudentByConditions(currentPage, majorid,
				keyword, grade, stuclass);
	}
	
	
	/**
	 * ����ɾ��ѧ�� 
	 * 
	 * @return
	 */
	public String deleteStudent() {
		if (studentsId == null){
			
		}
		else {
			studentDeleteResult = studentService.deleteStudents(studentsId);

		}
		return SUCCESS;
	}


	/*
	 * getters and setters
	 */

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentSearchMeta getStudentSearchMeta() {
		return studentSearchMeta;
	}

	public void setStudentSearchMeta(StudentSearchMeta studentSearchMeta) {
		this.studentSearchMeta = studentSearchMeta;
	}

	public List<Integer> getStudentsId() {
		return studentsId;
	}

	public void setStudentsId(List<Integer> studentsId) {
		this.studentsId = studentsId;
	}

	public StudentDeleteResult getStudentDeleteResult() {
		return studentDeleteResult;
	}

	public void setStudentDeleteResult(StudentDeleteResult studentDeleteResult) {
		this.studentDeleteResult = studentDeleteResult;
	}

	public List<StudentListView> getStudents() {
		return students;
	}

	public void setStudents(List<StudentListView> students) {
		this.students = students;
	}

	public StudentPageResult getStudentPageResult() {
		return studentPageResult;
	}

	public void setStudentPageResult(StudentPageResult studentPageResult) {
		this.studentPageResult = studentPageResult;
	}

	public StudentDetailView getStudentDetailView() {
		return studentDetailView;
	}

	public void setStudentDetailView(StudentDetailView studentDetailView) {
		this.studentDetailView = studentDetailView;
	}

	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}
}
