package com.wuhei.cms.test.dao.login;

import org.apache.ibatis.session.SqlSession;

import com.wuhei.cms.dao.login.LoginDAO;
import com.wuhei.cms.login.LoginUser;
import com.wuhei.cms.test.dao.DAOTestCase;

public class LoginDAOTestCase extends DAOTestCase {

	public void testLoadUserByUsername() {

		String username = "201130635364";
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			LoginDAO loginDAO = (LoginDAO) sqlSession.getMapper(LoginDAO.class);
			LoginUser loginUser = loginDAO.loadUserByUsername(username);
			System.out.println(loginUser.toString());
		} finally {
			sqlSession.close();
		}
	}
}
