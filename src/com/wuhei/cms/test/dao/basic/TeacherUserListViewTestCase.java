package com.wuhei.cms.test.dao.basic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.dao.basic.StudentUserListViewDAO;
import com.wuhei.cms.dao.basic.TeacherListViewDAO;
import com.wuhei.cms.dao.basic.TeacherUserListViewDAO;
import com.wuhei.cms.model.joint.StudentUserListView;
import com.wuhei.cms.model.joint.TeacherUserListView;
import com.wuhei.cms.test.dao.DAOTestCase;

public class TeacherUserListViewTestCase extends DAOTestCase {
	
	Logger logger = Logger.getLogger(TeacherUserListViewTestCase.class);
	
	public void testCountTeacherUserByConditions() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			TeacherUserListViewDAO teacherUserListViewDAO= sqlSession.getMapper(TeacherUserListViewDAO.class);
			// ��ʼ������
//			String keyword="��";
			Integer intt=teacherUserListViewDAO.countTeacherUserByCondition("G");
            List<TeacherUserListView> teacherUserListViews=teacherUserListViewDAO.listTeacherUserByCondition("G", 0, 20);
			for(int i=0;i<teacherUserListViews.size();i++)
			{
			 System.out.println(teacherUserListViews.get(i).getShowname());
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
