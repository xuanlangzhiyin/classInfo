package com.wuhei.cms.test.dao.basic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.dao.basic.StudentUserListViewDAO;
import com.wuhei.cms.dao.basic.TeacherListViewDAO;
import com.wuhei.cms.model.joint.StudentUserListView;
import com.wuhei.cms.test.dao.DAOTestCase;

public class StudentUserListViewTestCase extends DAOTestCase {
	
	Logger logger = Logger.getLogger(StudentUserListViewTestCase.class);
	
	public void testCountStudentUserByConditions() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			StudentUserListViewDAO studentUserListViewDAO = sqlSession.getMapper(StudentUserListViewDAO.class);
			// ��ʼ������
//			String keyword="��";
			Integer intt=studentUserListViewDAO.countStudentUserByCondition("����");
            List<StudentUserListView> studentUserListViews=studentUserListViewDAO.listStudentUserByCondition("����",0,20);
			for(int i=0;i<studentUserListViews.size();i++)
			{

 			 System.out.println(studentUserListViews.get(i).getGrade());

			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			sqlSession.close();
		}
	}
	

}
