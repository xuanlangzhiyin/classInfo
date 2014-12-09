package com.wuhei.cms.test.service.cactivities;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuhei.cms.model.Course;
import com.wuhei.cms.model.joint.CourseDetailView;
import com.wuhei.cms.model.joint.CourseListView;
import com.wuhei.cms.service.cactivities.CourseService;
import com.wuhei.cms.test.service.ServiceTestCase;

/**
 * 
 * @author ����
 * 
 */
public class CourseServiceTestCase extends ServiceTestCase {
	@Autowired
	private CourseService courseService;

	@Test
	public void testgetCourseListView4stuByCondition() {
		
		Integer studentid = 153579;
		
	    List<CourseListView> courseListView;
		
		courseListView=courseService.getCourseListView4stuByCondition(studentid, null, null);
				
		for(int i=0;i<courseListView.size();i++){
			System.out.println(courseListView.get(i).toString());
			
		}
		
	}
	
	  
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void testgetCourseListViewByCondition() {
		
		Integer teacherid = 810;
		
		List<CourseListView> courseListView;
		
		courseListView=courseService.getCourseListViewByCondition(teacherid, "2014-2015", "1");
		
		
		for(int i=0;i<courseListView.size();i++){
		//	System.out.println(courseListView.get(i).toString());
			System.out.println(courseListView.get(i).getIsdeletale());
		}
		
	}
	
	@Test
	public void testgetCourseDetailView(){
		Integer courseid=528;
		CourseDetailView courseDetailView=courseService.getCourseDetailView(courseid);
		System.out.println(courseDetailView.getCode());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void testupdatecourse(){
		Course course=new Course();
		course.setCode("(2014-2015-1)-G03031-1)");
		course.setYear("2014-2015)");
		course.setTerm("1");
		courseService.updateCourse(course);
		
	}
}
