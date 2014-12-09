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
 * 课程活动|开设课程
 * 
 * 
 */
@SuppressWarnings("serial")
public class CactivityCourseAction extends CactivityAction {

	Logger log = Logger.getLogger(CactivityCourseAction.class);

	/**
	 * 此处四个成员均为了实现update course操作 页面输入参数课程course和开课老师List<Teacher>
	 * teachers用于更新course和cteacher
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
	 * 用于获取所有教师列表，显示在开设课程编辑页面的教师下拉框中 by朱承荣
	 */
	protected List<Teacher> teachersList;

	/**
	 * 传给前台显示非主要开课老师 by朱承荣
	 */
	protected List<Teacher> cteachers;

	/**
	 * 主要开课老师 by 朱承荣
	 */
	protected Teacher mainCteacher;

	/**
	 * 存储课程附件的路径 classInfo/file/course/intro
	 */
	private String attachPath = File.separator + "file" + File.separator
			+ "course" + File.separator + "intro" + File.separator;

	/**
	 * 批量删除使用cstudentService
	 */

	protected CstudentService cstudentService;

	/**
	 * 选择学年下拉菜单的选项
	 */
	protected List<String> years;

	/**
	 * 批量导入专业下拉菜单的选项
	 */
	protected List<Major> majors;

	/**
	 * 系统用户：开设课程列表 前台分页 暂时不使用 虽然每个学期开设课程不多，系统提供多个“学年－学期”的开设课程
	 */
	protected List<CourseListView> courses;

	/**
	 * 页面输入参数－［学年｜学期］
	 */
	private CourseSearchMeta courseSearchMeta;

	/**
	 * 返回页面结果－符合条件的开设课程 后台分页
	 */
	private CoursePageResult coursePageResult;

	/**
	 * 开设课程详细视图
	 */
	private CourseDetailView courseDetailView;

	/**
	 * 所用的service 
	 */
	protected CourseService courseService;

	/**
	 * 前台传来要批量删除课程的id 
	 */
	protected List<Integer> coursesId;

	/**
	 * 返回页面结果-删除不成功的课程的信息
	 */
	protected CourseDeleteResult courseDeleteResult;

	/**
	 * 前台传来要批量删除选课学生的id 
	 */

	protected List<Integer> cstudentId;

	/**
	 * 返回页面结果－删除不成功的选课学生的信息
	 */

	protected CstudentDeleteResult cstudentDeleteResult;

	/**
	 * 返回页面结果－查看选课学生的信息
	 */

	protected List<CstudentDetailView> cstudentDetailViewList ;

	/**
	 * 返回页面结果－查看选课学生的信息
	 */

	/**
	 * 返回给前台已分组的小组;
	 */
	protected List<CgroupDetailView> cgroupDetailViews;

	/**
	 * 为指标显示提供majorid;
	 */
	protected Integer majorid;

	/**
	 * 课程 小组内学生list
	 */
	private List<CstudentDetailView> cstudentDetailViews;

	/**
	 * 课程组长id
	 */
	private Integer leaderid;

	/**
	 * 课程小组id
	 */
	private Integer cgroupid;

	
	/**
	 * 按条件为教务员获取开设课程列表 条件：当前操作专业：软件工程｜学年：2014-2015｜学期：1
	 * 
	 * struts:/acamgr/listCourse.action
	 * 
	 * @return 开设课程列表
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
		// 第一次点击课程活动则按照当前系统学年和学期来搜索
		else {
			courseSearchMeta = new CourseSearchMeta();
			courseSearchMeta.setTerm(term);
			courseSearchMeta.setYear(year);
		}

		try {
			// 获取登陆教务员id
			Integer teacherId = null;
			// 从数据库获取教务员开设的所有课程
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
	 * 按条件为教师获取开设课程列表 条件：学年：2014-2015｜学期：1
	 * 
	 * struts:/tch/listCourse.action
	 * 
	 * @return 开设课程列表
	 */
	public String listCourse4Tch() {
		// 获取查询条件
		String year = CmsWebContext.getCurrentYear();
		String term = CmsWebContext.getCurrentTerm();

		if (courseSearchMeta != null) {
			year = courseSearchMeta.getYear();
			year = this.getDefaultSearchValue(year, null);
			term = courseSearchMeta.getTerm();
			term = this.getDefaultSearchValue(term, null);
		}
		// 第一次点击课程活动则按照当前系统学年和学期来搜索
		else {
			courseSearchMeta = new CourseSearchMeta();
			courseSearchMeta.setTerm(term);
			courseSearchMeta.setYear(year);
		}

		try {
			// 获取登陆教师的id
			Integer teacherId = CmsWebContext.getCurrentUser().getTeacherid();

			// 从数据库获取教师所参与的课程
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
	 * 按条件为学生获取开设课程列表 条件：学年：2014-2015｜学期：1
	 * 
	 * struts:/std/listCourse.action
	 * 
	 * @return 开设课程列表
	 */
	public String listCourse4Std() {

		// 获取查询条件
		String year = CmsWebContext.getCurrentYear();
		String term = CmsWebContext.getCurrentTerm();
		
		if (courseSearchMeta != null) {
			year = courseSearchMeta.getYear();
			year = this.getDefaultSearchValue(year, null);
			term = courseSearchMeta.getTerm();
			term = this.getDefaultSearchValue(term, null);
		}
		// 第一次点击课程活动则按照当前系统学年和学期来搜索
		else {
			courseSearchMeta = new CourseSearchMeta();
			courseSearchMeta.setTerm(term);
			courseSearchMeta.setYear(year);
		}
		try {
			// 获取登陆学生的id
			Integer studentId = CmsWebContext.getCurrentUser().getStudentid();
			// 从数据库获取学生参与的课程
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

	// 用于判断传入的老师是否冲突
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

	// 重置开设课程代码
	public Course resetCourseCode(String oldCourseCode, Course course) {

		// 切割
		CourseCodeInfo courseCodeInfo = new CourseCodeInfo(oldCourseCode);
		String csettingcode = courseCodeInfo.getCsettingcode();
		String classMark = courseCodeInfo.getClassMark();
		String teachercode = courseCodeInfo.getTeachercode();
		String coursecode;

		// 如果旧的teacherid和前台传入的相同则使用旧的teachercode
		if (courseDetailView.getTeacherid().equals(course.getTeacherid())) {
			coursecode = "(" + course.getYear() + "-" + course.getTerm() + ")-"
					+ csettingcode + "-" + teachercode + "-" + classMark;
		}
		// 如果和原来不同则通过新传入的teacherid 找到相应的teachercode
		else {
			Teacher teacher = teacherService.getTeacherById(course
					.getTeacherid());
			coursecode = "(" + course.getYear() + "-" + course.getTerm() + ")-"
					+ csettingcode + "-" + teacher.getCode() + "-" + classMark;
		}
		// 填充course的code属性
		course.setCode(coursecode);
		return course;
	}

	/**
	 * 前台传来 course.id course.term course.year course.teacherid teacherIDs
	 * 教务员更新course和cteacher by zhuchengrong
	 */
	public String saveCourse4Acamgr() {

		// 前台传来的主导老师id
		Integer mainteacherid = course.getTeacherid();

		// 判断老师列表是否有重复
		if (isConflicts(mainteacherid, teacherIDs) == false) {
			return Action.ERROR;
		}

		// 通过前台传来的courseid拿到旧的开始课程的信息
		courseDetailView = courseService.getCourseDetailView(course.getId());
		// 非主导老师个数
		Integer teacherIDsSize;
		// 如果没有主导老师
		if (teacherIDs.get(0).equals(0))
			teacherIDsSize = 0;
		else
			teacherIDsSize = teacherIDs.size();

		// 课程总老师数
		Byte tnum = (byte) (1 + teacherIDsSize);

		// 填充course老师的数目
		course.setTnum(tnum);
		course = resetCourseCode(courseDetailView.getCode(), course);
		// 更新开设课程信息
		try {
			courseService.updateCourse(course);
		} catch (Exception insertException) {
			if (insertException.getCause() instanceof MySQLIntegrityConstraintViolationException) {
				return Action.ERROR;
			}

		}
		// 更新开课老师信息
		Cteacher cteacher = new Cteacher();
		// 先删除原先开课的教师
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
	 *  教师更新course
	 */

	public String saveCourse4Tch() {

		Course newcourse = courseService.getCourse(course.getId());
		
		newcourse.setDescription(course.getDescription());


		/*
		 * 如果没有上传附件，直接保存course
		 */
		if (file == null) {
			courseService.updateCourse(newcourse);
			return SUCCESS;
		}

		// 写日志
		log.info("教师用户：更新课程信息，上传附件");

		try {

			// 文件的保存路径是WebContent/file目录下
			String realpath = ServletActionContext.getServletContext()
					.getRealPath(attachPath);

			String oldname = FileHelper.setOldname(fileFileName);

			/*
			 * 判断文件类型
			 */
			Boolean type = false; // 判断文件类型是否符合要求

			FileType fileType = FileTypeDetector.getType(file);
			if (fileType == null) {
				log.error("教师用户：更新课程信息失败，上传文件类型错误");
				return ERROR;
			}
			String suffix = fileType.getSuffix();

			/*
			 * 课程附件，仅允许上传：doc、docx、xls、xlsx、pdf、zip、rar
			 */
			String[] allowsuffix = { "doc", "docx", "pdf", "zip", "rar" };
			for (String n : allowsuffix)
				if (suffix == n) {
					type = true;
					break;
				}

			// 上传文件类型错误
			if (type == false) {
				log.error("教师用户：更新课程信息失败，上传文件类型错误");
				return ERROR;
			}

			/*
			 * 如果之前已存在附件，先删除附件
			 */
			if (newcourse.getRandomname() != null) {
				try {
					File deletefile = new File(realpath
							+ newcourse.getRandomname());
					// 删除通知文件
					if (deletefile.isFile() && deletefile.exists()) {
						deletefile.delete();
					}
				} catch (Exception e) {
					// do nothing
				}
			}

			// 如果是doc或docx无法判断时，直接从后缀名中获取
			if ((suffix == "docx") || (suffix == "doc"))
				suffix = fileFileName.substring(fileFileName.lastIndexOf("."));

			/*
			 * 重新命名文件，保存到目标路径下
			 */
			newFileName = FileHelper.randFileName() + suffix;
			File savefile = new File(new File(realpath), newFileName);

			// 如果保存的路径不存在,则新建
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();

			// 拷贝文件
			FileUtils.copyFile(file, savefile);

			/*
			 * 设置Course中的attachname
			 */
			newcourse.setRandomname(newFileName);
			newcourse.setOldname(oldname);
			courseService.updateCourse(newcourse);

		} catch (Exception e) {
			log.error("教师用户：更新课程信息失败");
			return ERROR;
		}

		return SUCCESS;

	}

	/**
	 *  下载课程信息附件
	 * 
	 * @return
	 */
	public String downloadAttach() {
		// 写日志
		log.info("系统用户:下载课程信息附件");

		try {
			// 获取课程
			Course temp = courseService.getCourse(courseid);

			// 获取课程附件文件名:(支持中文)
			this.fileFileName = new String(temp.getRandomname().getBytes(),
					"ISO-8859-1");

			downloadFilePath = attachPath + fileFileName;
			// 该附件的输入流
			InputStream inputStream = ServletActionContext.getServletContext()
					.getResourceAsStream(downloadFilePath);

			// 如果课程附件不存在
			if (inputStream == null) {
				return ERROR;
			}
		} catch (Exception e) {
			// 获取过程发生错误
			log.error(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 删除附件 
	 */
	public String deleteAttach() {

		Course newcourse = courseService.getCourse(courseid);

		try {
			String realpath = ServletActionContext.getServletContext()
					.getRealPath(attachPath);
			File deletefile = new File(realpath + newcourse.getRandomname());

			/**
			 * 如果附件存在并且是文件，执行删除操作
			 */
			if (deletefile.exists() && deletefile.isFile()) {
				deletefile.delete();
			} else {
				return ERROR;
			}

			/**
			 * 更新数据库
			 */
			newcourse.setRandomname(null);
			newcourse.setOldname(null);
			courseService.updateCourse(newcourse);

		} catch (Exception e) {

			return ERROR;

		}

		return SUCCESS;
	}

	// by 朱承荣 用于显示开设课程信息
	public String viewCourse4Acamgr() {
		try {

			courseDetailView = courseService.getCourseDetailView(courseid);
			// 用于显示主开课老师
			mainCteacher = teacherService.getMainCteacher(courseid);
			// 用于显示非主开课老师
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
			// 获得这门课的基本信息
			courseDetailView = courseService.getCourseDetailView(courseid);
			// 用于显示主开课老师
			mainCteacher = teacherService.getMainCteacher(courseid);
			// 用于显示非主开课老师
			cteachers = teacherService.getTeacherListByCourseId(courseid);

			// 获取选课学生
			cstudentDetailViewList = cstudentService
					.getCstudentDetailViewList(courseid);

			// 通过csettingid获得majorid

			Csetting csetting = csettingService.getCsetting(csettingid);
			majorid = csetting.getMajorid();

			// 返回课程分组的信息

			cgroupDetailViews = cgroupService
					.getCgroupDetailViewListByCourseid(courseid);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 列出某一个分组的所有成员的详细信息
	 * 
	 * 根据cgroupid |cstudent.cgroupid
	 * 
	 * 返回该组的学生List<CstudentDetialView> json
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
			// 用于显示主开课老师
			mainCteacher = teacherService.getMainCteacher(courseid);
			// 用于显示非主开课老师
			cteachers = teacherService.getTeacherListByCourseId(courseid);
			// 用于老师下拉菜单的显示
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

			// 截取课程编号
			CourseCodeInfo courseCodeInfo = new CourseCodeInfo(
					courseDetailView.getCode());
			courseDetailView.setCode(courseCodeInfo.getCsettingcode());

			// 获取主导老师
			mainCteacher = teacherService.getMainCteacher(courseid);

			// 获取非主导老师
			cteachers = teacherService.getTeacherListByCourseId(courseid);

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return SUCCESS;
	}

	/**
	 *输入参数:courseid删除开设课程
	 */

	public String deleteCourse() {

		if (coursesId != null){
			courseDeleteResult = courseService.deleteCourses(coursesId);
		}
		return SUCCESS;
	}

	/**
	 * 输入参数：cstudentid列表删除选课学生
	 * 
	 * @return
	 */

	public String deleteCstudent() {
		// 若未勾选学生，前台输入cstudent为空
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
