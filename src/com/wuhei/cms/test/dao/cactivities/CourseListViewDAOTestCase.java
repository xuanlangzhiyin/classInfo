package com.wuhei.cms.test.dao.cactivities;

import java.util.List;

import junit.framework.Assert;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.dao.cactivities.CevaluationListViewDAO;
import com.wuhei.cms.dao.cactivities.CmcreditDetailViewDAO;
import com.wuhei.cms.dao.cactivities.CmcreditListViewDAO;
import com.wuhei.cms.dao.cactivities.CmissionDAO;
import com.wuhei.cms.dao.cactivities.CmissionListViewDAO;
import com.wuhei.cms.dao.cactivities.CourseListViewDAO;
import com.wuhei.cms.model.Cmission;
import com.wuhei.cms.model.joint.CevaluationListView;
import com.wuhei.cms.model.joint.CmcreditDetailView;
import com.wuhei.cms.model.joint.CmcreditListView;
import com.wuhei.cms.model.joint.CmissionListView;
import com.wuhei.cms.model.joint.CourseListView;
import com.wuhei.cms.test.dao.DAOTestCase;

/**
 * 
 * @author ¬��
 * 
 */
public class CourseListViewDAOTestCase extends DAOTestCase {

	Logger logger = Logger.getLogger(CourseListViewDAOTestCase.class);

	/**
	 * ����Ϊѧ���г��γ̻ by¬��
	 */

	public void testGetCmissionList() {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		CevaluationListViewDAO cevaluationListViewDAO = sqlSession
				.getMapper(CevaluationListViewDAO.class);
		//List<CevaluationListView> cevaluationListViews = cevaluationListViewDAO
		//		.listEvaluationListViewByCondition(156453, 260, 732);
		sqlSession.close();

	}

	// public void testGetCourseListView4stuByCondition() {
	// SqlSession sqlSession = sqlSessionFactory.openSession();
	// try {
	// CourseListViewDAO courseListViewDAO =
	// sqlSession.getMapper(CourseListViewDAO.class);
	//
	// Integer studentId = 150497;
	// String year = "2014-2015";
	// String term = "2";
	// // List<CourseListView> courses = courseListViewDAO
	// // .getCourseListView4stuByCondition(studentId, year, term);
	// List<CourseListView> courses = courseListViewDAO
	// .getCourseListViewByDepartmentId(19, year, term);
	// // ������
	// Assert.assertNotNull("��ȡ�γ��б�Ϊ��", courses);
	// for (int i = 0; i < courses.size(); i++) {
	// System.out.println("ѧ��γ̣�"+courses.get(i).toString()+courses.get(i).getTeachername()+courses.get(i).getCcategoryname()+courses.get(i).getCoursetype());
	// }
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// Assert.fail("getCourseListByCondition ʧ�ܣ�");
	// }
	// finally {
	// sqlSession.close();
	// }
	// }
}
