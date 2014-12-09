package com.wuhei.cms.test.dao.cactivities;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.dao.cactivities.CourseDetailViewDAO;
import com.wuhei.cms.model.joint.CourseDetailView;
import com.wuhei.cms.test.dao.DAOTestCase;
/**
 * 
 * @author ¬��
 *
 */
public class CourseDetailViewDAOTestCase extends DAOTestCase{
	
	Logger logger = Logger.getLogger(CourseDetailViewDAOTestCase.class);
	

	public void testgetCourseDetailView() {
		SqlSession sqlSession = sqlSessionFactory.openSession();		
			CourseDetailViewDAO courseDetailViewDAO = sqlSession.getMapper(CourseDetailViewDAO.class);			
			CourseDetailView courseDetailView=new CourseDetailView();
			List<CourseDetailView> courseDetailViews=new ArrayList<CourseDetailView>();
			courseDetailViews=courseDetailViewDAO.listCourseDetailViewByMajorId(120);
			
			
			sqlSession.close();
			
			
			//	public CourseDetailView getCourseDetailView(Integer courseid);
				//public List<CourseDetailView> listCourseDetailViewByMajorId(Integer majorid);
	}
}
