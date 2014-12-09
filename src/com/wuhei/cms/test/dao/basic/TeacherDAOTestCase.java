package com.wuhei.cms.test.dao.basic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.wuhei.cms.dao.basic.TeacherDAO;
import com.wuhei.cms.model.Teacher;
import com.wuhei.cms.test.dao.DAOTestCase;

public class TeacherDAOTestCase extends DAOTestCase{

	Logger logger = Logger.getLogger(TeacherListViewDAOTestCase.class);
	
	/**
	 * 
	 * 
	 * @author zhiyu,zwx
	 */
	public void testTeacherListByCourseId() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			TeacherDAO teacherDAO = sqlSession.getMapper(TeacherDAO.class);
			
			Integer courseid = 532;
            Integer isdominate = 0;
            List<Teacher> tea=teacherDAO.getTeacherListByCourseId(courseid);
            for(int i=0;i<tea.size();i++)
			{

 			 System.out.println(tea.get(i).getCode());

			}
			//logger.info(i.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			sqlSession.close();
		}
	}

}
