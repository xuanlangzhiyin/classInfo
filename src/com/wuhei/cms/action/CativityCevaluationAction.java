package com.wuhei.cms.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wuhei.cms.model.Cevaluation;
import com.wuhei.cms.model.Cmission;
import com.wuhei.cms.model.Cstudent;
import com.wuhei.cms.model.Teacher;

import com.wuhei.cms.model.joint.CmcreditListView;

import com.wuhei.cms.model.joint.CourseDetailView;
import com.wuhei.cms.model.joint.CstudentDetailView;
import com.wuhei.cms.model.joint.CstudentListView;
import com.wuhei.cms.service.basic.CsettingService;
import com.wuhei.cms.service.basic.StudentService;
import com.wuhei.cms.service.basic.TeacherService;
import com.wuhei.cms.service.cactivities.CmcreditService;
import com.wuhei.cms.service.cactivities.CmissionService;
import com.wuhei.cms.service.cactivities.CourseEvaluationService;
import com.wuhei.cms.service.cactivities.CourseService;
import com.wuhei.cms.service.cactivities.CstudentService;

/**
 * 课程活动｜课程总评
 */
@SuppressWarnings("serial")
public class CativityCevaluationAction extends CactivityCourseAction{
	
	Logger logger = Logger.getLogger(CativityCevaluationAction.class);
	/**
	 * 选课学生列表 
	 */
	private List<CstudentListView> cstudentListViews;
	/**
	 * 学生id
	 */
	private Integer studentid;
	
	/**
	 * 开设课程id
	 */
	private Integer csettingid;
	/**
	 * 选课学生的id
	 */
	private Integer cstudentid;
	
	/**
	 * 课程详情
	 */
	private CourseDetailView courseDetailView; 
	
	/**
	 * 该门课的主导教师
	 */
	private Teacher mainCteacher;
	
	/**
	 * 该门课的非主导老师
	 */
	private List<Teacher> cteachers;
	
	/**
	 * 该学生的详细信息
	 */
	private CstudentDetailView cstudentDetailView;

	/**
	 * 该学生参加的任务并且已评分的得分情况
	 */
	private List<CmcreditListView> cmcreditListView;
	
	/**
	 *该学生参加的任务但尚未得到分数的任务
	 */
	private List<Cmission> unMarkedCmissions;
	
	/**
	 *该门课所在的专业
	 */
	private Integer majorid;
	
	/**
	 *总评价
	 */
	private Cevaluation cevaluation;
	/**
	 * 将结果构造成json
	 */
	protected Map<String, Object> results;
	
	
	private CourseService courseService;
	private CstudentService cstudentService;
    private TeacherService teacherService;
    private StudentService studentService;
    private CmcreditService cmcreditService;
    private CmissionService cmissionService;
    private CsettingService csettingService;
    private CourseEvaluationService courseEvaluationService;
    
	/**
	 * 列出该门课的所有学生
	 * @return
	 */
	public String listCstudent4teacher()
	{
		try {
			//获得这门课的基本信息
			courseDetailView = courseService.getCourseDetailView(courseid);
			
			//获取该门课所在的专业
			majorid=csettingService.getCsetting(courseDetailView.getCsettingid()).getMajorid();
			
			cstudentListViews=cstudentService.getCstudentListView(courseid);
		} catch (Exception e) {
			return ERROR;
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	/**
	 * 评价单个学生，传入courseid、cstudentid
	 * 返回courseDetailview、mainTeacher、cTeachers（非主导老师）、studentDetailView、cmcreditListView
	 * 
	 */
    
	public String evaluateCourseStudent()
	{
		try {
			//获得该选课学生的id
			studentid=cstudentService.getCstudentById(cstudentid).getStudentid();
			//获得这门课的基本信息
			courseDetailView = courseService.getCourseDetailView(courseid);
			// 用于显示主开课老师
			mainCteacher = teacherService.getMainCteacher(courseid);
			// 用于显示非主开课老师
			cteachers = teacherService.getTeacherListByCourseId(courseid);
			//用于显示学生信息
			cstudentDetailView=cstudentService.getCstudentDetailViewById(cstudentid);
			//用于显示学生参加任务并且已经评分的情况
			cmcreditListView=cmcreditService.getCmcreditListViewsByCondition(cstudentid, courseid);
			//用于显示学生参加任务但老师尚未评分的情况
			unMarkedCmissions=cmissionService.getUnmarkedCmissionListByCondition4Student(cstudentid, courseid);
			//获取该门课所在的专业
			majorid=csettingService.getCsetting(courseDetailView.getCsettingid()).getMajorid();
			//获取总评成绩
			cevaluation=courseEvaluationService.listEvaluationByCondition(studentid, courseid);
		} catch (Exception e) {
			System.out.println(e);
			return ERROR;
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	public String saveCevaluation()
	{
		try 
		{
			courseEvaluationService.updateCevaluation(cevaluation, studentid, courseid);
			cevaluation=courseEvaluationService.listEvaluationByCondition(studentid, courseid);
			//给学生添加已总评字段
			Cstudent cstudent=new Cstudent();
			cstudent.setId(cstudentid);
			cstudent.setIsevaluated(true);
			cstudentService.updateCstudent(cstudent);
		} 	
		catch (Exception e) 
		
		{
			System.out.println(e);
			return ERROR;
			// TODO: handle exception
		}
        	
		return SUCCESS;
	}
	
	public String viewCevaluation()
	{
		try {
			cevaluation=courseEvaluationService.listEvaluationByCondition(studentid, courseid);
			return SUCCESS;
		} catch (Exception e) {
			System.out.println(e);
			return ERROR;
			// TODO: handle exception
		}

	}
	
	

	/**
	 * getters and setters
	 * 
	 */
	
	
	public String getCstudentDetail() {		
		
		return SUCCESS;
		
	}
	
	public CmissionService getCmissionService() {
		return cmissionService;
	}

	public void setCmissionService(CmissionService cmissionService) {
		this.cmissionService = cmissionService;
	}

	public CourseDetailView getCourseDetailView() {
		return courseDetailView;
	}

	public void setCourseDetailView(CourseDetailView courseDetailView) {
		this.courseDetailView = courseDetailView;
	}

	public Teacher getMainCteacher() {
		return mainCteacher;
	}

	public void setMainCteacher(Teacher mainCteacher) {
		this.mainCteacher = mainCteacher;
	}

	public List<Teacher> getCteachers() {
		return cteachers;
	}

	public void setCteachers(List<Teacher> cteachers) {
		this.cteachers = cteachers;
	}

	

	public List<CmcreditListView> getCmcreditListView() {
		return cmcreditListView;
	}

	public void setCmcreditListView(List<CmcreditListView> cmcreditListView) {
		this.cmcreditListView = cmcreditListView;
	}

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public CmcreditService getCmcreditService() {
		return cmcreditService;
	}

	public void setCmcreditService(CmcreditService cmcreditService) {
		this.cmcreditService = cmcreditService;
	}

	public CstudentService getCstudentService() {
		return cstudentService;
	}

	public void setCstudentService(CstudentService cstudentService) {
		this.cstudentService = cstudentService;
	}


	public Integer getCstudentid() {
		return cstudentid;
	}

	public void setCstudentid(Integer cstudentid) {
		this.cstudentid = cstudentid;
	}

	public List<CstudentListView> getCstudentListViews() {
		return cstudentListViews;
	}

	public void setCstudentListViews(List<CstudentListView> cstudentListViews) {
		this.cstudentListViews = cstudentListViews;
	}

	public List<Cmission> getUnMarkedCmissions() {
		return unMarkedCmissions;
	}

	public void setUnMarkedCmissions(List<Cmission> unMarkedCmissions) {
		this.unMarkedCmissions = unMarkedCmissions;
	}


	public Integer getMajorid() {
		return majorid;
	}

	public CstudentDetailView getCstudentDetailView() {
		return cstudentDetailView;
	}



	public void setMajorid(Integer majorid) {
		this.majorid = majorid;
	}

	public CsettingService getCsettingService() {
		return csettingService;
	}

	public void setCsettingService(CsettingService csettingService) {
		this.csettingService = csettingService;
	}


	public CourseEvaluationService getCourseEvaluationService() {
		return courseEvaluationService;
	}

	public void setCourseEvaluationService(
			CourseEvaluationService courseEvaluationService) {
		this.courseEvaluationService = courseEvaluationService;
	}

	public Map<String, Object> getResults() {
		return results;
	}

	public void setResults(Map<String, Object> results) {
		this.results = results;
	}

	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public Integer getCsettingid() {
		return csettingid;
	}

	public void setCsettingid(Integer csettingid) {
		this.csettingid = csettingid;
	}




	public void setCstudentDetailView(CstudentDetailView cstudentDetailView) {
		this.cstudentDetailView = cstudentDetailView;
	}

	public Cevaluation getCevaluation() {
		return cevaluation;
	}

	public void setCevaluation(Cevaluation cevaluation) {
		this.cevaluation = cevaluation;
	}

	
	
}
