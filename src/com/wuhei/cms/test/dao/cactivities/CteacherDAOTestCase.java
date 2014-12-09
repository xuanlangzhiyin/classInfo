package com.wuhei.cms.test.dao.cactivities;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.dao.cactivities.CteacherDAO;
import com.wuhei.cms.model.Cteacher;
import com.wuhei.cms.test.dao.DAOTestCase;

public class CteacherDAOTestCase extends DAOTestCase {

	Logger log = Logger.getLogger(CteacherDAOTestCase.class);

	Integer courseid = Integer.valueOf(550);
	Integer teacherid = Integer.valueOf(778);
	
	public void testInsertCteacher() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {

			CteacherDAO cteacherDAO = sqlSession.getMapper(CteacherDAO.class);

			Cteacher cteacher = new Cteacher();
			boolean isdominate = false;
			cteacher.setCourseid(courseid);
			cteacher.setTeacherid(teacherid);
			cteacher.setIsdominate(isdominate);
			
			cteacherDAO.insertCteacher(cteacher);
			
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e);

		}
		sqlSession.close();

	}
	
	public void testUpdateCteacher() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {

			CteacherDAO cteacherDAO = sqlSession.getMapper(CteacherDAO.class);

			Cteacher cteacher = new Cteacher();
			boolean isdominate = true;
			cteacher.setCourseid(courseid);
			cteacher.setTeacherid(teacherid);
			cteacher.setIsdominate(isdominate);
			
			cteacherDAO.updateCteacherByCourseid(cteacher);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e);

		}
		sqlSession.close();

	}
	
	public void testDeleteCteacher() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {

			CteacherDAO cteacherDAO = sqlSession.getMapper(CteacherDAO.class);
			
			
		    cteacherDAO.deleteCteacherByCourseid(courseid);
		    sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e);

		}
		sqlSession.close();

	}
	
	public void testGetCteacher() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {

			CteacherDAO cteacherDAO = sqlSession.getMapper(CteacherDAO.class);

			List<Cteacher> cteacher = new ArrayList<Cteacher>();
			
			cteacher = cteacherDAO.getCteacherByCourseid(530);
			
			for(int i=0;i<cteacher.size();i++){
				System.out.println(cteacher.get(i).getTeacherid());
				
			}
		} catch (Exception e) {
			System.out.println(e);

		}
		sqlSession.close();

	}
	
	//author:yangxuan
	public void testcountCteacherByTeacherid() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {

			CteacherDAO cteacherDAO = sqlSession.getMapper(CteacherDAO.class);

			Integer num=cteacherDAO.countCteacherByTeacherid(657);
			System.out.println(num);
		
		} catch (Exception e) {
			System.out.println(e);

		}
		sqlSession.close();

	}
	
	
	
	
	
	
	
}