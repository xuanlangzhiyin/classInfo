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
 * 学生管理｜基类
 * 
 * 
 */
@SuppressWarnings("serial")
public class StudentAction extends BaseAction {

	Logger log = Logger.getLogger(StudentAction.class);

	protected StudentService studentService;

	/**
	 * 页面输入参数－学生id
	 */
	protected Integer studentid;

	/**
	 * 页面输入参数－学生
	 */
	protected Student student;

	/**
	 * 学生详细视图
	 */
	protected StudentDetailView studentDetailView;

	/**
	 * 返回页面结果－学生列表
	 */
	protected List<StudentListView> students;

	/**
	 * 页面输入参数－学生列表查询条件
	 */
	protected StudentSearchMeta studentSearchMeta;

	/**
	 * 返回页面结果－包含分页信息的学生列表数据
	 */
	protected StudentPageResult studentPageResult;

	/**
	 * 页面打印专业下拉框用
	 */
	protected List<Major> majors;
	
	/**
	 * 页面输入参数-要删除的学生id
	 */
	protected List<Integer> studentsId;
	
	/**
	 * 返回页面结果-删除失败的学生信息
	 */
	protected StudentDeleteResult studentDeleteResult;

	/**
	 * 教务员：查看本学院学生列表 后台分页
	 * 
	 * @return /acamgr/listStudent.jsp
	 */
	public String listStudent4Acamgr() {
		
		internalGetStudents();

		log.info("教务员用户：查询学生列表");

		// 加载专业列表，页面批量导入学生需要
		Integer departmentid = CmsWebContext.getCurrentDepartmentId();
		majors = CmsWebContext.getCurrentMajorList();
		
		return SUCCESS;
	}

	/**
	 * 教师：查看本学院学生列表 后台分页
	 * 
	 * @return /teacher/listStudent.jsp
	 */
	public String listStudent4Teacher() {

		internalGetStudents();
		
		log.info("教师用户：查询学生列表");

		return SUCCESS;
	}

	/**
	 * 学生：查看本学院学生列表 后台分页
	 * 
	 * @return /student/listStudent.jsp
	 */
	public String listStudent4Student() {
		
		internalGetStudents();
		
		log.info("学生用户：查询学生列表");
		
		return SUCCESS;
	}

	private void internalGetStudents() {
		// struts默认converter会将前端value=""的string对象赋值为""
		// 如<input name="keyword" value="">或者<input name="keyword">
		// 则提交表单时后台拿到的keyword为""而不是null

		// 默认查询条件
		Integer currentPage = 1;
		String keyword = null;
		String grade = null;
		String stuclass = null;
		Integer majorid = null;
		// 获取页面输入查询条件
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
		
		// 获取查询结果
		studentPageResult = studentService.listStudentByConditions(currentPage, majorid,
				keyword, grade, stuclass);
	}
	
	
	/**
	 * 批量删除学生 
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
