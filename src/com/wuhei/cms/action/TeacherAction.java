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
 * 教师管理｜基类
 * 
 * 
 */
@SuppressWarnings("serial")
public class TeacherAction extends BaseAction {

	Logger log = Logger.getLogger(TeacherAction.class);

	/**
	 * 教师管理－业务操作接口
	 */
	protected TeacherService teacherService;

	/**
	 * 页面输入参数－教师id
	 */
	protected Integer tearcherid;

	/**
	 * 页面输入参数－教师
	 */
	protected Teacher teacher;



	/**
	 * 返回页面结果－教师详细视图
	 */
	protected TeacherDetailView teacherDetailView;
	
	
	/**
	 * 页面输入参数－要删除的教师id
	 */
	protected List<Integer> teachersId;

	/**
	 * 返回页面结果－［本学院的］教师列表 前台分页使用
	 */
	protected List<Teacher> teachers;

	/**
	 * 页面输入参数－教师列表查询条件 后台分页使用
	 */
	protected TeacherSearchMeta teacherSearchMeta;

	/**
	 * 返回页面结果－包含分页信息的教师列表数据 后台分页使用
	 */
	protected TeacherPageResult teacherPageResult;

	/**
	 * 教务员：查看本学院教师列表 后他分页
	 * 
	 * @return /acamgr/listTeacher.jsp
	 */
	
	

	/**
	 * 返回页面结果-删除不成功的教师信息
	 */
	protected TeacherDeleteResult teacherDeleteResult;
	
	
	
	
	public String listTeacher4Acamgr() {

		internalGetTeachers();

		log.info("教务员用户：查询教师列表");

		return SUCCESS;
	}

	/**
	 * 学生：查看本学院教师列表 后台分页
	 * 
	 * @return /student/listTeacher.jsp
	 */
	public String listTeacher4Student() {

		internalGetTeachers();

		log.info("学生用户：查询教师列表");

		return SUCCESS;
	}

	/**
	 * 教师：查看本学院教师列表 后台分页
	 * 
	 * @return /teacher/listTeacher.jsp
	 */
	public String listTeacher4Teacher() {

		internalGetTeachers();

		log.info("教师用户：查询教师列表");

		return SUCCESS;
	}

	private void internalGetTeachers() {
		// 默认查询条件
		Integer currentPage = 1;
		String keyword = null;

		// 获取页面输入查询条件
		if (teacherSearchMeta != null) {
			currentPage = teacherSearchMeta.getCurrentPage();
			keyword = this.getDefaultSearchValue(
					teacherSearchMeta.getKeyword(), null);
		}

		// 获取登陆用户所属的院系id
		Integer departmentId = CmsWebContext.getCurrentDepartmentId();

		// 从数据库获取符合条件教师
		teacherPageResult = teacherService.getTeacherListByConditions(
				departmentId, keyword, currentPage);
	}

	


	/**
	 * 教务员：删除老师
	 * 
	 */
	
	public String deleteTeacher() {
		//如果没有选择要删除的老师
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
