package com.wuhei.cms.test.dao.basic;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.dao.basic.MajorDAO;
import com.wuhei.cms.model.Major;
import com.wuhei.cms.test.dao.DAOTestCase;

public class MajorDAOTestCase extends DAOTestCase {

	Logger log = Logger.getLogger(MajorDAOTestCase.class);

	public void testUpdateMajor() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {

			Major major = new Major();
			major.setId(Integer.valueOf(120));
			major.setCode("083500");
			major.setName("Ӳ������");

			MajorDAO majorDAO = sqlSession.getMapper(MajorDAO.class);

			majorDAO.updateMajor(major);

		} catch (Exception e) {
			log.error(e.getCause());
			log.error(e.getMessage());
			
		} finally {
			sqlSession.close();
		}

	}

}
