package com.wuhei.cms.service.cactivities.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuhei.cms.dao.basic.CsettingDAO;
import com.wuhei.cms.dao.basic.TeacherDAO;
import com.wuhei.cms.dao.cactivities.CourseDAO;
import com.wuhei.cms.dao.cactivities.CourseDetailViewDAO;
import com.wuhei.cms.dao.cactivities.CourseListViewDAO;
import com.wuhei.cms.dao.cactivities.CteacherDAO;
import com.wuhei.cms.model.Course;

import com.wuhei.cms.model.Cteacher;
import com.wuhei.cms.model.Teacher;
import com.wuhei.cms.model.deletedata.CourseDeleteResult;
import com.wuhei.cms.model.joint.CourseDetailView;
import com.wuhei.cms.model.joint.CourseListView;
import com.wuhei.cms.service.cactivities.CourseService;
import com.wuhei.cms.web.context.CmsWebContext;


public class CourseServiceImpl implements CourseService {

	Logger log = Logger.getLogger(CourseServiceImpl.class);

	private CourseDAO courseDAO;
	private CourseDetailViewDAO courseDetailViewDAO;
	private CourseListViewDAO courseListViewDAO;
	private TeacherDAO teacherDAO;
	private CsettingDAO csettingDAO;
	private CteacherDAO cteacherDAO;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<Course> getCourseList() {
		return courseDAO.listCourse();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void newCourse(Course course) {
		courseDAO.insertCourse(course);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Course getCourse(int courseid) {
		return courseDAO.getCourse(courseid);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteCourse(Integer courseid) {
		courseDAO.deleteCourse(courseid);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public CourseDeleteResult deleteCourses(List<Integer> coursesId) {

		int courseid;
		CourseDetailView courseDetailView = new CourseDetailView();
		CourseDeleteResult courseDeleteResult = new CourseDeleteResult();
		List<CourseDetailView> errorCourses = new ArrayList<CourseDetailView>();
		Integer successCount = Integer.valueOf(0);
		Integer errorCount = Integer.valueOf(0);

		for (int i = 0; i < coursesId.size(); i++) {
			courseid = coursesId.get(i);
			try {
				courseDetailView = courseDetailViewDAO
						.getCourseDetailView(courseid);
				if (courseDetailView == null)
					continue;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}

			if (courseDetailView.getIsdeletale() == false) {
				errorCourses.add(courseDetailView);
				errorCount++;
				continue;
			}

			else {
				try {
					courseDAO.deleteCourse(courseid);
				} catch (Exception e) {
					// TODO: handle exception
					errorCourses.add(courseDetailView);
					errorCount++;
					continue;
				}
				successCount++;

			}
		}
		courseDeleteResult.setSuccessCount(successCount);
		courseDeleteResult.setErrorCount(errorCount);
		courseDeleteResult.setErrorCourses(errorCourses);
		return courseDeleteResult;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateCourse(Course course) {

		courseDAO.updateCourse(course);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<Course> getCourseListByTeacherid(Integer teacherid) {
		List<Course> courseList = courseDAO.getCourseListByCondition(teacherid,
				null, null, null, null, null);
		courseList.addAll(courseDAO.getCourseListByCondition(null, null, null,
				null, teacherid, null));
		return courseList;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public CourseDetailView getCourseDetailView(Integer courseid) {
		return courseDetailViewDAO.getCourseDetailView(courseid);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<Course> getStuCourseList(Integer studentid) {
		return courseDAO.getCourseListByStudentid(studentid);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CourseDetailView> listCourseDetailViewByMajorId(Integer majorid) {
		return courseDetailViewDAO.listCourseDetailViewByMajorId(majorid);
	}


	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CourseListView> getCourseListViewByCondition(Integer teacherid,
			String year, String term) {

		try {
			List<CourseListView> courseListViews = courseListViewDAO
					.getCourseListViewByCondition(teacherid, year, term);
			List<Cteacher> cteachers = new ArrayList<Cteacher>();
			
			CourseListView courseListView = new CourseListView();
			for (int i = 0; i < courseListViews.size(); i++) {

				courseListView = courseListViews.get(i);
			
				cteachers = cteacherDAO.getCteacherByCourseid(courseListView
						.getId());

				List<Teacher> teachers = new ArrayList<Teacher>();

				
				teachers.add(teacherDAO.getTeacher(courseListView
						.getTeacherid()));

				
				for (int j = 0; j < cteachers.size(); j++) {
					if (cteachers.get(j).getIsdominate() == false) {
						Teacher teacher = teacherDAO.getTeacher(cteachers
								.get(j).getTeacherid());
						teachers.add(teacher);
					}
				}
				String allTeacherName = "";
				int number = teachers.size();
				for (int k = 0; k < number; k++) {
					

					if (k > 2) {
						allTeacherName += "µÈ";
						break;
					}
					
					if (k == number - 1) {
						allTeacherName += teachers.get(k).getName();
					}

					else {
						allTeacherName += teachers.get(k).getName() + "¡¢";
					}
				}
				courseListView.setAllTeacherName(allTeacherName);
				courseListViews.set(i, courseListView);
			}

			return courseListViews;
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

		return null;
	}

	public List<CourseListView> getCourseListViewByDepartmentId(
			Integer departmentid, String year, String term) {
		// TODO Auto-generated method stub

		List<CourseListView> courseListViews = courseListViewDAO
				.getCourseListViewByDepartmentId(departmentid, year, term);
		List<Cteacher> cteachers = new ArrayList<Cteacher>();

		
		CourseListView courseListView = new CourseListView();
		for (int i = 0; i < courseListViews.size(); i++) {
			courseListView = courseListViews.get(i);
			
			cteachers = cteacherDAO.getCteacherByCourseid(courseListView
					.getId());

			List<Teacher> teachers = new ArrayList<Teacher>();

			

			teachers.add(teacherDAO.getTeacher(courseListView.getTeacherid()));

		

			for (int j = 0; j < cteachers.size(); j++) {
				if (cteachers.get(j).getIsdominate() == false) {
					Teacher teacher = teacherDAO.getTeacher(cteachers.get(j)
							.getTeacherid());
					teachers.add(teacher);
				}
			}
			String allTeacherName = "";
			int number = teachers.size();
			for (int k = 0; k < number; k++) {
			

				if (k > 2) {
					allTeacherName += "µÈ";
					break;
				}
			
				if (k == number - 1) {
					allTeacherName += teachers.get(k).getName();
				}

				else {
					allTeacherName += teachers.get(k).getName() + "¡¢";
				}
			}
			courseListView.setAllTeacherName(allTeacherName);
			courseListViews.set(i, courseListView);
		}
		return courseListViews;
	}

	@Override
	public List<CourseListView> getCourseListView4stuByCondition(
			Integer studentid, String year, String term) {

		List<CourseListView> courseListViews = courseListViewDAO
				.getCourseListView4stuByCondition(studentid, year, term);
		List<Cteacher> cteachers = new ArrayList<Cteacher>();

	
		CourseListView courseListView = new CourseListView();
		for (int i = 0; i < courseListViews.size(); i++) {
			courseListView = courseListViews.get(i);
			
			cteachers = cteacherDAO.getCteacherByCourseid(courseListView
					.getId());

			List<Teacher> teachers = new ArrayList<Teacher>();

			

			teachers.add(teacherDAO.getTeacher(courseListView.getTeacherid()));

		

			for (int j = 0; j < cteachers.size(); j++) {
				if (cteachers.get(j).getIsdominate() == false) {
					Teacher teacher = teacherDAO.getTeacher(cteachers.get(j)
							.getTeacherid());
					teachers.add(teacher);
				}
			}
			String allTeacherName = "";
			int number = teachers.size();
			for (int k = 0; k < number; k++) {
				

				if (k > 2) {
					allTeacherName += "µÈ";
					break;
				}
			
				if (k == number - 1) {
					allTeacherName += teachers.get(k).getName();
				}

				else {
					allTeacherName += teachers.get(k).getName() + "¡¢";
				}
			}
			courseListView.setAllTeacherName(allTeacherName);
			courseListViews.set(i, courseListView);
		}
		return courseListViews;

	}



	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	public CsettingDAO getCsettingDAO() {
		return csettingDAO;
	}

	public void setCsettingDAO(CsettingDAO csettingDAO) {
		this.csettingDAO = csettingDAO;
	}

	public CourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public CourseDetailViewDAO getCourseDetailViewDAO() {
		return courseDetailViewDAO;
	}

	public void setCourseDetailViewDAO(CourseDetailViewDAO courseDetailViewDAO) {
		this.courseDetailViewDAO = courseDetailViewDAO;
	}

	public CourseListViewDAO getCourseListViewDAO() {
		return courseListViewDAO;
	}

	public void setCourseListViewDAO(CourseListViewDAO courseListViewDAO) {
		this.courseListViewDAO = courseListViewDAO;
	}

	public CteacherDAO getCteacherDAO() {
		return cteacherDAO;
	}

	public void setCteacherDAO(CteacherDAO cteacherDAO) {
		this.cteacherDAO = cteacherDAO;
	}

}
