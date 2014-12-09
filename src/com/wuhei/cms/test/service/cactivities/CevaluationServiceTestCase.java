package com.wuhei.cms.test.service.cactivities;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.model.Cevaluation;
import com.wuhei.cms.service.cactivities.CourseEvaluationService;
import com.wuhei.cms.test.dao.DAOTestCase;

public class CevaluationServiceTestCase extends DAOTestCase{
	Logger log = Logger.getLogger(CevaluationServiceTestCase.class);

	public CourseEvaluationService cevaluationService;
	
	Byte attendance = Byte.valueOf((byte)100);
	Byte examcredit = Byte.valueOf((byte)100);
	Byte credit = Byte.valueOf((byte)100);
	Integer courseid = Integer.valueOf(761);
	Integer studentid = Integer.valueOf(156871);
	
	public void testInsertCevaluation() {
		try {
			
			Cevaluation cevaluation = new Cevaluation();
			cevaluation.setAttendance(attendance);
			cevaluation.setExamcredit(examcredit);
			cevaluation.setCredit(credit);
			cevaluation.setCourseid(courseid);
			cevaluation.setStudentid(studentid);
			
			cevaluationService.updateCevaluation(cevaluation,studentid,courseid);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
