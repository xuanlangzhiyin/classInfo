package com.wuhei.cms.service.cactivities;


import java.util.List;


import com.wuhei.cms.model.Course;
import com.wuhei.cms.model.joint.CourseDetailView;
import com.wuhei.cms.model.joint.CourseListView;
import com.wuhei.cms.model.deletedata.CourseDeleteResult;


public interface CourseService {


	public List<Course> getCourseList();
	

	public void newCourse(Course course);
	

	public Course getCourse(int courseid);
	

	public void deleteCourse(Integer courseid);
	

	public CourseDeleteResult deleteCourses(List<Integer>coursesId);
	

	public void updateCourse(Course course);
	

	public List<Course> getCourseListByTeacherid(Integer teacherid);
	

	public CourseDetailView getCourseDetailView(Integer courseid);

	public List<Course> getStuCourseList(Integer studentid);
	

	public List<CourseDetailView> listCourseDetailViewByMajorId(Integer majorid);
	

	public List<CourseListView> getCourseListViewByCondition(Integer teacherid, String year, String term);
	

	/**
	 * 
	 * @param studentid
	 * @param year
	 * @param term
	 * @return
	 */
	public List<CourseListView> getCourseListView4stuByCondition(Integer studentid, String year, String term);
		
	
	public List<CourseListView> getCourseListViewByDepartmentId(Integer departmentid,String year,String term);
	

}
