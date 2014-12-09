package com.wuhei.cms.test.dao.cactivities;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.dao.cactivities.CmstudentDetailViewDAO;
import com.wuhei.cms.model.joint.CmstudentDetailView;
import com.wuhei.cms.test.dao.DAOTestCase;

public class CmstudentDetailViewDAOTestCase extends DAOTestCase{
	Logger logger = Logger.getLogger(CmstudentDetailViewDAOTestCase.class);
	public void testlistUnGroupedCmstudentDetailViewByCmissionid() {
		
	SqlSession sqlSession = sqlSessionFactory.openSession();		
	CmstudentDetailViewDAO cmstudentDetailViewDAO = sqlSession.getMapper(CmstudentDetailViewDAO.class);	
    List<CmstudentDetailView> cmstudentDetailViews=cmstudentDetailViewDAO.listUnGroupedCmstudentDetailViewByCmissionid(1);
	for(int i=0;i<cmstudentDetailViews.size();i++){
		System.out.println(cmstudentDetailViews.get(i).getName());
	}
	sqlSession.close();
	}
}