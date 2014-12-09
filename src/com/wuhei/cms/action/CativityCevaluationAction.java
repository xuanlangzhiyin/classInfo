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
 * �γ̻���γ�����
 */
@SuppressWarnings("serial")
public class CativityCevaluationAction extends CactivityCourseAction{
	
	Logger logger = Logger.getLogger(CativityCevaluationAction.class);
	/**
	 * ѡ��ѧ���б� 
	 */
	private List<CstudentListView> cstudentListViews;
	/**
	 * ѧ��id
	 */
	private Integer studentid;
	
	/**
	 * ����γ�id
	 */
	private Integer csettingid;
	/**
	 * ѡ��ѧ����id
	 */
	private Integer cstudentid;
	
	/**
	 * �γ�����
	 */
	private CourseDetailView courseDetailView; 
	
	/**
	 * ���ſε�������ʦ
	 */
	private Teacher mainCteacher;
	
	/**
	 * ���ſεķ�������ʦ
	 */
	private List<Teacher> cteachers;
	
	/**
	 * ��ѧ������ϸ��Ϣ
	 */
	private CstudentDetailView cstudentDetailView;

	/**
	 * ��ѧ���μӵ������������ֵĵ÷����
	 */
	private List<CmcreditListView> cmcreditListView;
	
	/**
	 *��ѧ���μӵ�������δ�õ�����������
	 */
	private List<Cmission> unMarkedCmissions;
	
	/**
	 *���ſ����ڵ�רҵ
	 */
	private Integer majorid;
	
	/**
	 *������
	 */
	private Cevaluation cevaluation;
	/**
	 * ����������json
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
	 * �г����ſε�����ѧ��
	 * @return
	 */
	public String listCstudent4teacher()
	{
		try {
			//������ſεĻ�����Ϣ
			courseDetailView = courseService.getCourseDetailView(courseid);
			
			//��ȡ���ſ����ڵ�רҵ
			majorid=csettingService.getCsetting(courseDetailView.getCsettingid()).getMajorid();
			
			cstudentListViews=cstudentService.getCstudentListView(courseid);
		} catch (Exception e) {
			return ERROR;
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	/**
	 * ���۵���ѧ��������courseid��cstudentid
	 * ����courseDetailview��mainTeacher��cTeachers����������ʦ����studentDetailView��cmcreditListView
	 * 
	 */
    
	public String evaluateCourseStudent()
	{
		try {
			//��ø�ѡ��ѧ����id
			studentid=cstudentService.getCstudentById(cstudentid).getStudentid();
			//������ſεĻ�����Ϣ
			courseDetailView = courseService.getCourseDetailView(courseid);
			// ������ʾ��������ʦ
			mainCteacher = teacherService.getMainCteacher(courseid);
			// ������ʾ����������ʦ
			cteachers = teacherService.getTeacherListByCourseId(courseid);
			//������ʾѧ����Ϣ
			cstudentDetailView=cstudentService.getCstudentDetailViewById(cstudentid);
			//������ʾѧ���μ��������Ѿ����ֵ����
			cmcreditListView=cmcreditService.getCmcreditListViewsByCondition(cstudentid, courseid);
			//������ʾѧ���μ�������ʦ��δ���ֵ����
			unMarkedCmissions=cmissionService.getUnmarkedCmissionListByCondition4Student(cstudentid, courseid);
			//��ȡ���ſ����ڵ�רҵ
			majorid=csettingService.getCsetting(courseDetailView.getCsettingid()).getMajorid();
			//��ȡ�����ɼ�
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
			//��ѧ������������ֶ�
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
