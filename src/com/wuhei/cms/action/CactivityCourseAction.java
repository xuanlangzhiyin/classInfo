package com.wuhei.cms.action;

import java.io.File;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.wuhei.cms.fileprocessing.FileHelper;
import com.wuhei.cms.fileprocessing.FileType;
import com.wuhei.cms.fileprocessing.FileTypeDetector;
import com.wuhei.cms.model.Course;
import com.wuhei.cms.model.Csetting;
import com.wuhei.cms.model.Cteacher;
import com.wuhei.cms.model.Major;
import com.wuhei.cms.model.Teacher;
import com.wuhei.cms.model.deletedata.CourseDeleteResult;
import com.wuhei.cms.model.deletedata.CstudentDeleteResult;
import com.wuhei.cms.utils.CourseCodeInfo;
import com.wuhei.cms.model.joint.CgroupDetailView;
import com.wuhei.cms.model.joint.CourseDetailView;
import com.wuhei.cms.model.joint.CourseListView;
import com.wuhei.cms.model.joint.CstudentDetailView;
import com.wuhei.cms.search.result.CoursePageResult;
import com.wuhei.cms.search.CourseSearchMeta;
import com.wuhei.cms.service.basic.CsettingService;
import com.wuhei.cms.service.basic.TeacherService;
import com.wuhei.cms.service.cactivities.CgroupService;
import com.wuhei.cms.service.cactivities.CourseService;
import com.wuhei.cms.service.cactivities.CstudentService;
import com.wuhei.cms.service.cactivities.CteacherService;
import com.wuhei.cms.web.context.CmsWebContext;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.opensymphony.xwork2.Action;

/**
 * �γ̻|����γ�
 * 
 * 
 */
@SuppressWarnings("serial")
public class CactivityCourseAction extends CactivityAction {

	Logger log = Logger.getLogger(CactivityCourseAction.class);

	/**
	 * �˴��ĸ���Ա��Ϊ��ʵ��update course���� ҳ����������γ�course�Ϳ�����ʦList<Teacher>
	 * teachers���ڸ���course��cteacher
	 */

	protected List<Teacher> teachers;

	protected List<Integer> teacherIDs;



	protected Integer csettingid;

	protected Cteacher cteacher;

	protected CteacherService cteacherService;

	protected CgroupService cgroupService;

	protected TeacherService teacherService;

	protected CsettingService csettingService;

	/**
	 * ���ڻ�ȡ���н�ʦ�б���ʾ�ڿ���γ̱༭ҳ��Ľ�ʦ�������� by�����
	 */
	protected List<Teacher> teachersList;

	/**
	 * ����ǰ̨��ʾ����Ҫ������ʦ by�����
	 */
	protected List<Teacher> cteachers;

	/**
	 * ��Ҫ������ʦ by �����
	 */
	protected Teacher mainCteacher;

	/**
	 * �洢�γ̸�����·�� classInfo/file/course/intro
	 */
	private String attachPath = File.separator + "file" + File.separator
			+ "course" + File.separator + "intro" + File.separator;

	/**
	 * ����ɾ��ʹ��cstudentService
	 */

	protected CstudentService cstudentService;

	/**
	 * ѡ��ѧ�������˵���ѡ��
	 */
	protected List<String> years;

	/**
	 * ��������רҵ�����˵���ѡ��
	 */
	protected List<Major> majors;

	/**
	 * ϵͳ�û�������γ��б� ǰ̨��ҳ ��ʱ��ʹ�� ��Ȼÿ��ѧ�ڿ���γ̲��࣬ϵͳ�ṩ�����ѧ�꣭ѧ�ڡ��Ŀ���γ�
	 */
	protected List<CourseListView> courses;

	/**
	 * ҳ�������������ѧ���ѧ�ڣ�
	 */
	private CourseSearchMeta courseSearchMeta;

	/**
	 * ����ҳ���������������Ŀ���γ� ��̨��ҳ
	 */
	private CoursePageResult coursePageResult;

	/**
	 * ����γ���ϸ��ͼ
	 */
	private CourseDetailView courseDetailView;

	/**
	 * ���õ�service 
	 */
	protected CourseService courseService;

	/**
	 * ǰ̨����Ҫ����ɾ���γ̵�id 
	 */
	protected List<Integer> coursesId;

	/**
	 * ����ҳ����-ɾ�����ɹ��Ŀγ̵���Ϣ
	 */
	protected CourseDeleteResult courseDeleteResult;

	/**
	 * ǰ̨����Ҫ����ɾ��ѡ��ѧ����id 
	 */

	protected List<Integer> cstudentId;

	/**
	 * ����ҳ������ɾ�����ɹ���ѡ��ѧ������Ϣ
	 */

	protected CstudentDeleteResult cstudentDeleteResult;

	/**
	 * ����ҳ�������鿴ѡ��ѧ������Ϣ
	 */

	protected List<CstudentDetailView> cstudentDetailViewList ;

	/**
	 * ����ҳ�������鿴ѡ��ѧ������Ϣ
	 */

	/**
	 * ���ظ�ǰ̨�ѷ����С��;
	 */
	protected List<CgroupDetailView> cgroupDetailViews;

	/**
	 * Ϊָ����ʾ�ṩmajorid;
	 */
	protected Integer majorid;

	/**
	 * �γ� С����ѧ��list
	 */
	private List<CstudentDetailView> cstudentDetailViews;

	/**
	 * �γ��鳤id
	 */
	private Integer leaderid;

	/**
	 * �γ�С��id
	 */
	private Integer cgroupid;

	
	/**
	 * ������Ϊ����Ա��ȡ����γ��б� ��������ǰ����רҵ��������̣�ѧ�꣺2014-2015��ѧ�ڣ�1
	 * 
	 * struts:/acamgr/listCourse.action
	 * 
	 * @return ����γ��б�
	 */
	public String listCourse4Acamgr() {

		String year = CmsWebContext.getCurrentYear();
		String term = CmsWebContext.getCurrentTerm();
		if (courseSearchMeta != null) {
			year = courseSearchMeta.getYear();
			year = this.getDefaultSearchValue(year, null);
			term = courseSearchMeta.getTerm();
			term = this.getDefaultSearchValue(term, null);
		}
		// ��һ�ε���γ̻���յ�ǰϵͳѧ���ѧ��������
		else {
			courseSearchMeta = new CourseSearchMeta();
			courseSearchMeta.setTerm(term);
			courseSearchMeta.setYear(year);
		}

		try {
			// ��ȡ��½����Աid
			Integer teacherId = null;
			// �����ݿ��ȡ����Ա��������пγ�
			courses = courseService.getCourseListViewByDepartmentId(
					CmsWebContext.getCurrentDepartment().getId(), year, term);

		} catch (Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
		majors = CmsWebContext.getCurrentMajorList();
		years = CmsWebContext.getCurrentYearList();
		return Action.SUCCESS;
	}

	/**
	 * ������Ϊ��ʦ��ȡ����γ��б� ������ѧ�꣺2014-2015��ѧ�ڣ�1
	 * 
	 * struts:/tch/listCourse.action
	 * 
	 * @return ����γ��б�
	 */
	public String listCourse4Tch() {
		// ��ȡ��ѯ����
		String year = CmsWebContext.getCurrentYear();
		String term = CmsWebContext.getCurrentTerm();

		if (courseSearchMeta != null) {
			year = courseSearchMeta.getYear();
			year = this.getDefaultSearchValue(year, null);
			term = courseSearchMeta.getTerm();
			term = this.getDefaultSearchValue(term, null);
		}
		// ��һ�ε���γ̻���յ�ǰϵͳѧ���ѧ��������
		else {
			courseSearchMeta = new CourseSearchMeta();
			courseSearchMeta.setTerm(term);
			courseSearchMeta.setYear(year);
		}

		try {
			// ��ȡ��½��ʦ��id
			Integer teacherId = CmsWebContext.getCurrentUser().getTeacherid();

			// �����ݿ��ȡ��ʦ������Ŀγ�
			courses = courseService.getCourseListViewByCondition(teacherId,
					year, term);

		} catch (Exception e) {
			System.out.println(e);
			log.error(e.getMessage());
			return ERROR;
		}
		years = CmsWebContext.getCurrentYearList();
		return SUCCESS;
	}
	
	/**
	 * ������Ϊѧ����ȡ����γ��б� ������ѧ�꣺2014-2015��ѧ�ڣ�1
	 * 
	 * struts:/std/listCourse.action
	 * 
	 * @return ����γ��б�
	 */
	public String listCourse4Std() {

		// ��ȡ��ѯ����
		String year = CmsWebContext.getCurrentYear();
		String term = CmsWebContext.getCurrentTerm();
		
		if (courseSearchMeta != null) {
			year = courseSearchMeta.getYear();
			year = this.getDefaultSearchValue(year, null);
			term = courseSearchMeta.getTerm();
			term = this.getDefaultSearchValue(term, null);
		}
		// ��һ�ε���γ̻���յ�ǰϵͳѧ���ѧ��������
		else {
			courseSearchMeta = new CourseSearchMeta();
			courseSearchMeta.setTerm(term);
			courseSearchMeta.setYear(year);
		}
		try {
			// ��ȡ��½ѧ����id
			Integer studentId = CmsWebContext.getCurrentUser().getStudentid();
			// �����ݿ��ȡѧ������Ŀγ�
			courses = courseService.getCourseListView4stuByCondition(studentId,
					year, term);

		} catch (Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
		years = CmsWebContext.getCurrentYearList();
		return Action.SUCCESS;
	}

	private void saveCourse() {
		// TODO Auto-generated method stub
		courseService.updateCourse(course);

	}

	// �����жϴ������ʦ�Ƿ��ͻ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean isConflicts(Integer mainteacherid, List<Integer> teacherIDs) {
		Integer teacherIDsSize;
		if (teacherIDs.get(0).equals(0))
			return true;
		else
			teacherIDsSize = teacherIDs.size();
		for (int i = 0; i < teacherIDsSize; i++) {
			if (mainteacherid.equals(teacherIDs.get(i))) {
				return false;
			}
		}

		HashSet set = new HashSet();
		for (Integer i : teacherIDs)
			set.add(i);
		if (!(set.size() == teacherIDs.size()))
			return false;

		return true;
	}

	// ���ÿ���γ̴���
	public Course resetCourseCode(String oldCourseCode, Course course) {

		// �и�
		CourseCodeInfo courseCodeInfo = new CourseCodeInfo(oldCourseCode);
		String csettingcode = courseCodeInfo.getCsettingcode();
		String classMark = courseCodeInfo.getClassMark();
		String teachercode = courseCodeInfo.getTeachercode();
		String coursecode;

		// ����ɵ�teacherid��ǰ̨�������ͬ��ʹ�þɵ�teachercode
		if (courseDetailView.getTeacherid().equals(course.getTeacherid())) {
			coursecode = "(" + course.getYear() + "-" + course.getTerm() + ")-"
					+ csettingcode + "-" + teachercode + "-" + classMark;
		}
		// �����ԭ����ͬ��ͨ���´����teacherid �ҵ���Ӧ��teachercode
		else {
			Teacher teacher = teacherService.getTeacherById(course
					.getTeacherid());
			coursecode = "(" + course.getYear() + "-" + course.getTerm() + ")-"
					+ csettingcode + "-" + teacher.getCode() + "-" + classMark;
		}
		// ���course��code����
		course.setCode(coursecode);
		return course;
	}

	/**
	 * ǰ̨���� course.id course.term course.year course.teacherid teacherIDs
	 * ����Ա����course��cteacher by zhuchengrong
	 */
	public String saveCourse4Acamgr() {

		// ǰ̨������������ʦid
		Integer mainteacherid = course.getTeacherid();

		// �ж���ʦ�б��Ƿ����ظ�
		if (isConflicts(mainteacherid, teacherIDs) == false) {
			return Action.ERROR;
		}

		// ͨ��ǰ̨������courseid�õ��ɵĿ�ʼ�γ̵���Ϣ
		courseDetailView = courseService.getCourseDetailView(course.getId());
		// ��������ʦ����
		Integer teacherIDsSize;
		// ���û��������ʦ
		if (teacherIDs.get(0).equals(0))
			teacherIDsSize = 0;
		else
			teacherIDsSize = teacherIDs.size();

		// �γ�����ʦ��
		Byte tnum = (byte) (1 + teacherIDsSize);

		// ���course��ʦ����Ŀ
		course.setTnum(tnum);
		course = resetCourseCode(courseDetailView.getCode(), course);
		// ���¿���γ���Ϣ
		try {
			courseService.updateCourse(course);
		} catch (Exception insertException) {
			if (insertException.getCause() instanceof MySQLIntegrityConstraintViolationException) {
				return Action.ERROR;
			}

		}
		// ���¿�����ʦ��Ϣ
		Cteacher cteacher = new Cteacher();
		// ��ɾ��ԭ�ȿ��εĽ�ʦ
		cteacherService.deleteCteacher(course.getId());

		cteacher.setCourseid(course.getId());
		cteacher.setTeacherid(mainteacherid);

		cteacher.setIsdominate(true);

		try {
			cteacherService.insertCteacher(cteacher);
		} catch (Exception e) {
			System.out.println(e);
		}
		for (int i = 0; i < teacherIDsSize; i++) {
			Integer teacherid = teacherIDs.get(i);
			cteacher.setCourseid(course.getId());
			cteacher.setTeacherid(teacherid);
			cteacher.setIsdominate(false);
			try {
				cteacherService.insertCteacher(cteacher);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return Action.SUCCESS;
	}

	/**
	 *  ��ʦ����course
	 */

	public String saveCourse4Tch() {

		Course newcourse = courseService.getCourse(course.getId());
		
		newcourse.setDescription(course.getDescription());


		/*
		 * ���û���ϴ�������ֱ�ӱ���course
		 */
		if (file == null) {
			courseService.updateCourse(newcourse);
			return SUCCESS;
		}

		// д��־
		log.info("��ʦ�û������¿γ���Ϣ���ϴ�����");

		try {

			// �ļ��ı���·����WebContent/fileĿ¼��
			String realpath = ServletActionContext.getServletContext()
					.getRealPath(attachPath);

			String oldname = FileHelper.setOldname(fileFileName);

			/*
			 * �ж��ļ�����
			 */
			Boolean type = false; // �ж��ļ������Ƿ����Ҫ��

			FileType fileType = FileTypeDetector.getType(file);
			if (fileType == null) {
				log.error("��ʦ�û������¿γ���Ϣʧ�ܣ��ϴ��ļ����ʹ���");
				return ERROR;
			}
			String suffix = fileType.getSuffix();

			/*
			 * �γ̸������������ϴ���doc��docx��xls��xlsx��pdf��zip��rar
			 */
			String[] allowsuffix = { "doc", "docx", "pdf", "zip", "rar" };
			for (String n : allowsuffix)
				if (suffix == n) {
					type = true;
					break;
				}

			// �ϴ��ļ����ʹ���
			if (type == false) {
				log.error("��ʦ�û������¿γ���Ϣʧ�ܣ��ϴ��ļ����ʹ���");
				return ERROR;
			}

			/*
			 * ���֮ǰ�Ѵ��ڸ�������ɾ������
			 */
			if (newcourse.getRandomname() != null) {
				try {
					File deletefile = new File(realpath
							+ newcourse.getRandomname());
					// ɾ��֪ͨ�ļ�
					if (deletefile.isFile() && deletefile.exists()) {
						deletefile.delete();
					}
				} catch (Exception e) {
					// do nothing
				}
			}

			// �����doc��docx�޷��ж�ʱ��ֱ�ӴӺ�׺���л�ȡ
			if ((suffix == "docx") || (suffix == "doc"))
				suffix = fileFileName.substring(fileFileName.lastIndexOf("."));

			/*
			 * ���������ļ������浽Ŀ��·����
			 */
			newFileName = FileHelper.randFileName() + suffix;
			File savefile = new File(new File(realpath), newFileName);

			// ��������·��������,���½�
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();

			// �����ļ�
			FileUtils.copyFile(file, savefile);

			/*
			 * ����Course�е�attachname
			 */
			newcourse.setRandomname(newFileName);
			newcourse.setOldname(oldname);
			courseService.updateCourse(newcourse);

		} catch (Exception e) {
			log.error("��ʦ�û������¿γ���Ϣʧ��");
			return ERROR;
		}

		return SUCCESS;

	}

	/**
	 *  ���ؿγ���Ϣ����
	 * 
	 * @return
	 */
	public String downloadAttach() {
		// д��־
		log.info("ϵͳ�û�:���ؿγ���Ϣ����");

		try {
			// ��ȡ�γ�
			Course temp = courseService.getCourse(courseid);

			// ��ȡ�γ̸����ļ���:(֧������)
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
			// ��ȡ���̷�������
			log.error(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * ɾ������ 
	 */
	public String deleteAttach() {

		Course newcourse = courseService.getCourse(courseid);

		try {
			String realpath = ServletActionContext.getServletContext()
					.getRealPath(attachPath);
			File deletefile = new File(realpath + newcourse.getRandomname());

			/**
			 * ����������ڲ������ļ���ִ��ɾ������
			 */
			if (deletefile.exists() && deletefile.isFile()) {
				deletefile.delete();
			} else {
				return ERROR;
			}

			/**
			 * �������ݿ�
			 */
			newcourse.setRandomname(null);
			newcourse.setOldname(null);
			courseService.updateCourse(newcourse);

		} catch (Exception e) {

			return ERROR;

		}

		return SUCCESS;
	}

	// by ����� ������ʾ����γ���Ϣ
	public String viewCourse4Acamgr() {
		try {

			courseDetailView = courseService.getCourseDetailView(courseid);
			// ������ʾ��������ʦ
			mainCteacher = teacherService.getMainCteacher(courseid);
			// ������ʾ����������ʦ
			cteachers = teacherService.getTeacherListByCourseId(courseid);

			
			cstudentDetailViewList = cstudentService
					.getCstudentDetailViewList(courseid);

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String viewCourse4Tch() {
		try {
			// ������ſεĻ�����Ϣ
			courseDetailView = courseService.getCourseDetailView(courseid);
			// ������ʾ��������ʦ
			mainCteacher = teacherService.getMainCteacher(courseid);
			// ������ʾ����������ʦ
			cteachers = teacherService.getTeacherListByCourseId(courseid);

			// ��ȡѡ��ѧ��
			cstudentDetailViewList = cstudentService
					.getCstudentDetailViewList(courseid);

			// ͨ��csettingid���majorid

			Csetting csetting = csettingService.getCsetting(csettingid);
			majorid = csetting.getMajorid();

			// ���ؿγ̷������Ϣ

			cgroupDetailViews = cgroupService
					.getCgroupDetailViewListByCourseid(courseid);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * �г�ĳһ����������г�Ա����ϸ��Ϣ
	 * 
	 * ����cgroupid |cstudent.cgroupid
	 * 
	 * ���ظ����ѧ��List<CstudentDetialView> json
	 * 
	 * @return
	 */
	public String getGroupMemberDetial() {
		try {
			cstudentDetailViews = cgroupService
					.getCstudentDetailViewListByCgroupid(cgroupid);
			CgroupDetailView tempDetailView = cgroupService
					.getCgroupDetailViewByCgroupid(cgroupid);
			// TODO cgroupid = tempDetailView.getId();
			leaderid = tempDetailView.getLeaderid();
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	public String editCourse4Acamgr() {
		try {

			courseDetailView = courseService.getCourseDetailView(courseid);
			// ������ʾ��������ʦ
			mainCteacher = teacherService.getMainCteacher(courseid);
			// ������ʾ����������ʦ
			cteachers = teacherService.getTeacherListByCourseId(courseid);
			// ������ʦ�����˵�����ʾ
			teachersList = teacherService
					.getTeacherListByDepartmentid(CmsWebContext
							.getCurrentDepartmentId());

			// chenzefeng
			cstudentDetailViewList = cstudentService
					.getCstudentDetailViewList(courseid);

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String editCourse4Tch() {
		try {

			courseDetailView = courseService.getCourseDetailView(courseid);

			// ��ȡ�γ̱��
			CourseCodeInfo courseCodeInfo = new CourseCodeInfo(
					courseDetailView.getCode());
			courseDetailView.setCode(courseCodeInfo.getCsettingcode());

			// ��ȡ������ʦ
			mainCteacher = teacherService.getMainCteacher(courseid);

			// ��ȡ��������ʦ
			cteachers = teacherService.getTeacherListByCourseId(courseid);

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return SUCCESS;
	}

	/**
	 *�������:courseidɾ������γ�
	 */

	public String deleteCourse() {

		if (coursesId != null){
			courseDeleteResult = courseService.deleteCourses(coursesId);
		}
		return SUCCESS;
	}

	/**
	 * ���������cstudentid�б�ɾ��ѡ��ѧ��
	 * 
	 * @return
	 */

	public String deleteCstudent() {
		// ��δ��ѡѧ����ǰ̨����cstudentΪ��
		if (cstudentId != null){
			
			cstudentDeleteResult = cstudentService
					.deleteCstudentByCondition(cstudentId);
		}
		return SUCCESS;
	}

	/*
	 * getters and setters
	 */
	public List<Integer> getCoursesId() {
		return coursesId;
	}

	public void setCoursesId(List<Integer> coursesId) {
		this.coursesId = coursesId;
	}

	public CourseDeleteResult getCourseDeleteResult() {
		return courseDeleteResult;
	}

	public void setCourseDeleteResult(CourseDeleteResult courseDeleteResult) {
		this.courseDeleteResult = courseDeleteResult;
	}

	public CourseSearchMeta getCourseSearchMeta() {
		return courseSearchMeta;
	}

	public void setCourseSearchMeta(CourseSearchMeta courseSearchMeta) {
		this.courseSearchMeta = courseSearchMeta;
	}

	public CoursePageResult getCoursePageResult() {
		return coursePageResult;
	}

	public void setCoursePageResult(CoursePageResult coursePageResult) {
		this.coursePageResult = coursePageResult;
	}

	public CourseDetailView getCourseDetailView() {
		return courseDetailView;
	}

	public void setCourseDetailView(CourseDetailView courseDetailView) {
		this.courseDetailView = courseDetailView;
	}

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public List<CourseListView> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseListView> courses) {
		this.courses = courses;
	}

	/*
	 * remove by mulan
	 * 
	 * public Course getCourse() { return course; }
	 * 
	 * public void setCourse(Course course) { this.course = course; }
	 */

	public List<Teacher> getCteachers() {
		return cteachers;
	}

	public void setCteachers(List<Teacher> cteachers) {
		this.cteachers = cteachers;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}

	public CteacherService getCteacherService() {
		return cteacherService;
	}

	public void setCteacherService(CteacherService cteacherService) {
		this.cteacherService = cteacherService;
	}

	public Cteacher getCteacher() {
		return cteacher;
	}

	public void setCteacher(Cteacher cteacher) {
		this.cteacher = cteacher;
	}

	public CstudentDeleteResult getCstudentDeleteResult() {
		return cstudentDeleteResult;
	}

	public void setCstudentDeleteResult(
			CstudentDeleteResult cstudentDeleteResult) {
		this.cstudentDeleteResult = cstudentDeleteResult;
	}

	public List<Integer> getCstudentId() {
		return cstudentId;
	}

	public void setCstudentId(List<Integer> cstudentId) {
		this.cstudentId = cstudentId;
	}

	public CstudentService getCstudentService() {
		return cstudentService;
	}

	public void setCstudentService(CstudentService cstudentService) {
		this.cstudentService = cstudentService;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public List<Teacher> getTeachersList() {
		return teachersList;
	}

	public void setTeachersList(List<Teacher> teachersList) {
		this.teachersList = teachersList;
	}

	/*
	 * remove by mulan
	 * 
	 * public Integer getCourseid() { return courseid; }
	 * 
	 * public void setCourseid(Integer courseid) { this.courseid = courseid; }
	 */



	public List<Integer> getTeacherIDs() {
		return teacherIDs;
	}

	public List<CstudentDetailView> getCstudentDetailViewList() {
		return cstudentDetailViewList;
	}

	public void setCstudentDetailViewList(
			List<CstudentDetailView> cstudentDetailViewList) {
		this.cstudentDetailViewList = cstudentDetailViewList;
	}

	public void setTeacherIDs(List<Integer> teacherIDs) {
		this.teacherIDs = teacherIDs;
	}

	public Teacher getMainCteacher() {
		return mainCteacher;
	}

	public void setMainCteacher(Teacher mainCteacher) {
		this.mainCteacher = mainCteacher;
	}

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public List<CgroupDetailView> getCgroupDetailViews() {
		return cgroupDetailViews;
	}

	public void setCgroupDetailViews(List<CgroupDetailView> cgroupDetailViews) {
		this.cgroupDetailViews = cgroupDetailViews;
	}

	public CgroupService getCgroupService() {
		return cgroupService;
	}

	public void setCgroupService(CgroupService cgroupService) {
		this.cgroupService = cgroupService;
	}

	public Integer getMajorid() {
		return majorid;
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

	public Integer getCsettingid() {
		return csettingid;
	}

	public void setCsettingid(Integer csettingid) {
		this.csettingid = csettingid;
	}
	public Integer getCgroupid() {
		return cgroupid;
	}

	public void setCgroupid(Integer cgroupid) {
		this.cgroupid = cgroupid;
	}

	public Integer getLeaderid() {
		return leaderid;
	}

	public void setLeaderid(Integer leaderid) {
		this.leaderid = leaderid;
	}

	public List<CstudentDetailView> getCstudentDetailViews() {
		return cstudentDetailViews;
	}

	public void setCstudentDetailViews(
			List<CstudentDetailView> cstudentDetailViews) {
		this.cstudentDetailViews = cstudentDetailViews;
	}

}
