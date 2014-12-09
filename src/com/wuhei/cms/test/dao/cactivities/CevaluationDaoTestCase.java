package com.wuhei.cms.test.dao.cactivities;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.dao.cactivities.CevaluationDAO;
import com.wuhei.cms.model.Cevaluation;
import com.wuhei.cms.test.dao.DAOTestCase;

public class CevaluationDaoTestCase extends DAOTestCase {
	Logger log = Logger.getLogger(CevaluationDaoTestCase.class);

	Byte attendance = Byte.valueOf((byte)100);
	Byte examcredit = Byte.valueOf((byte)100);
	Integer courseid = Integer.valueOf(761);
	Integer studentid = Integer.valueOf(156871);
	
	public void testInsertCevaluation() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {

			CevaluationDAO cevaluationDAO = sqlSession.getMapper(CevaluationDAO.class);

			Cevaluation cevaluation = new Cevaluation();
			cevaluation.setAttendance(attendance);
			cevaluation.setExamcredit(examcredit);
			cevaluation.setCourseid(courseid);
			cevaluation.setStudentid(studentid);
			
			cevaluationDAO.insertCevaluation(cevaluation);
			
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e);

		}
		sqlSession.close();

	}

}
