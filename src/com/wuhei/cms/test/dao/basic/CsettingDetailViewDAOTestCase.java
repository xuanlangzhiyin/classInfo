package com.wuhei.cms.test.dao.basic;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.dao.basic.CsettingDetailViewDAO;
import com.wuhei.cms.model.joint.CsettingDetailView;
import com.wuhei.cms.test.dao.DAOTestCase;

public class CsettingDetailViewDAOTestCase extends DAOTestCase{
	Logger logger = Logger.getLogger(CsettingDetailViewDAOTestCase.class);
	public void testGetCsettingDetailView()
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			CsettingDetailViewDAO csettingDetailViewDAO = sqlSession.getMapper(CsettingDetailViewDAO.class);
			CsettingDetailView  csettingDetailView=csettingDetailViewDAO.getCsettingDetailView(6);
			System.out.println(csettingDetailView.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	  
		finally {
			sqlSession.close();
		}
		
		
		
		
 	}
	
}
