package com.wuhei.cms.test.dao.basic;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.wuhei.cms.dao.basic.TeacherListViewDAO;
import com.wuhei.cms.test.dao.DAOTestCase;

public class TeacherListViewDAOTestCase extends DAOTestCase {
	
	
	Logger logger = Logger.getLogger(TeacherListViewDAOTestCase.class);
	
	/**
	 * ����count 
	 * 
	 * @author zhiyu,zwx
	 */
	public void testCountTeacherByConditions() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			TeacherListViewDAO teacherListViewDAO = sqlSession.getMapper(TeacherListViewDAO.class);
			// ��ʼ������
			
			Integer departmentId = 19;
            String keyword="��";
            Integer i=teacherListViewDAO.countTeacherByConditions(departmentId, keyword);
			logger.info(i.toString());
		} finally {
			sqlSession.close();
		}
	}

	

}
