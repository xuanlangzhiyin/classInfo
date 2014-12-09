package com.wuhei.cms.test.dao.basic;

import java.util.List;

import junit.framework.Assert;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.dao.basic.CsettingDetailViewDAO;
import com.wuhei.cms.dao.basic.CsettingListViewDAO;
import com.wuhei.cms.model.joint.CsettingDetailView;
import com.wuhei.cms.model.joint.CsettingListView;
import com.wuhei.cms.test.dao.DAOTestCase;


public class CsettingListViewDAOTestCase extends DAOTestCase {

Logger logger = Logger.getLogger(CsettingDetailViewDAOTestCase.class);
	
	/**
	 * 
	 * @author gwb
	 */
	public void TestGetCsettingDetailView() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			CsettingDetailViewDAO csettingDetailViewDAO = sqlSession.getMapper(CsettingDetailViewDAO.class);
			CsettingDetailView  csettingDetailView=csettingDetailViewDAO.getCsettingDetailView(6);
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * ����listCsettingListByCondition Ԥ�ڽ�����������������ȡgrade="2010",stuclass="4"��ѧ��
	 */
//	public void testgetCsettingListByConditions() {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		try {
//			CsettingListViewDAO csettingListViewDAO = sqlSession.getMapper(CsettingListViewDAO.class);
//			// ��ʼ�����
//			Integer majorId = 120;
//			String keyword = "C";
//			Integer start = 0;
//			Integer count = 5;
//			List<CsettingListView> csettings = csettingListViewDAO.listCsettingListByConditions(majorId,
//					keyword, start, count);
//			// ������
//			Assert.assertNotNull("��ȡ�γ��б�Ϊ��", csettings);
//			for (int i = 0; i < csettings.size(); i++) {
//				System.out.println(csettings.get(i).toString());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail("getCsettingListByCondition ʧ�ܣ�");
//		} finally {
//			sqlSession.close();
//		}
//	}
}
