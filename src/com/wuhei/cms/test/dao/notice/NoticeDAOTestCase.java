package com.wuhei.cms.test.dao.notice;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.dao.notice.NoticeDAO;
import com.wuhei.cms.model.Notice;
import com.wuhei.cms.model.joint.NoticeListView;
import com.wuhei.cms.test.dao.DAOTestCase;

/**
 * mulan 2014-07-20
 */
public class NoticeDAOTestCase extends DAOTestCase {
	
	Logger log = Logger.getLogger(NoticeDAOTestCase.class);

	public void testInsertNotice() {

		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			// ��ʼ�����
			NoticeDAO noticeDAO = sqlSession.getMapper(NoticeDAO.class);

			Integer publisherid = 1;
			String name = "������̹���Ա";
			String title = "ϵͳ����ʱֹͣʹ��";
			String content = "���������ڲ������ʱͣ����Ʊ���ʱ����";
			Date releasetime = new Date(32141241);
			Date endtime = new Date(234332434);
			Integer universityid = 1;
			Boolean isoffice = true;
			Boolean isacamgr = true;
			Boolean isteacher = true;
			Boolean isstudent = true;

			Notice notice = new Notice();
			notice.setPublisherid(publisherid);
			notice.setName(name);
			notice.setTitle(title);
			notice.setContent(content);
			notice.setReleasetime(releasetime);
			notice.setEndtime(endtime);
			notice.setUniversityid(universityid);
			notice.setIsoffice(isoffice);
			notice.setIsacamgr(isacamgr);
			notice.setIsstudent(isstudent);
			notice.setIsteacher(isteacher);

			// ִ��DAO
			noticeDAO.insertNotice(notice);

			// commit����ݿ�����Ӧ�ı䡣
			sqlSession.commit();

			// ��֤���
			Assert.assertNotNull("����֪ͨ ����", notice.getId());

		} finally {
			sqlSession.close();
		}
	}

	/*public void testGetNoticeByConditions() {

		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			// ��ʼ�����
			NoticeDAO noticeDAO = sqlSession.getMapper(NoticeDAO.class);
			
			String keyword = "ϵͳ";
			Boolean isacamgr = Boolean.TRUE;
			Integer start = Integer.valueOf(0);
			Integer count = Integer.valueOf(20);
			List<NoticeListView> notices = noticeDAO.getNoticeByConditions(keyword, isacamgr, null, null, start, count);
			//log.info(notices.size());
			System.out.println(notices.size());
		} finally {
			sqlSession.close();
		}
	}*/

}
