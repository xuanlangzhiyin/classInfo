package com.wuhei.cms.service.notice.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.wuhei.cms.dao.notice.NoticeDAO;
import com.wuhei.cms.model.Notice;
import com.wuhei.cms.model.joint.NoticeListView;
import com.wuhei.cms.search.result.NoticePageResult;
import com.wuhei.cms.search.result.PageResult;
import com.wuhei.cms.service.notice.NoticeService;


public class NoticeServiceImpl implements NoticeService {

	// =========================================================================
	// DAO����
	private NoticeDAO noticeDAO;

	// =========================================================================
	// ʵ�ֵĽӿ�
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertNotice(Notice notice) {
		this.noticeDAO.insertNotice(notice);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void modifyNotice(Notice notice) {

		this.noticeDAO.updateNotice(notice);
	}

	@Override
	public void deleteNotice(Integer noticeid) {

		this.noticeDAO.deleteNotice(noticeid);
	}

	@Override
	public Notice getNotice(Integer noticeid) {

		return this.noticeDAO.getNotice(noticeid);
	}

	@Override
	public NoticePageResult getNoticeByConditions(Integer currentPage,
			String keyword, Boolean isacamgr, Boolean isteacher,
			Boolean isstudent) {

		// �ܼ�¼����
		Integer totalCount = this.noticeDAO.countNoticeByConditions(keyword,
				isacamgr, isteacher, isstudent);

		// ��ȡ��ҳ��
		Integer totalPage = PageResult.computeTotalPage(totalCount,
				PageResult.PAGE_COUNT);

		// ��ȡ�Ϸ��ĵ�ǰҳ��
		currentPage = PageResult.getLegalCurrentPage(totalPage, currentPage);

		Integer start = (currentPage - 1) * PageResult.PAGE_COUNT;

		// �����ݿ��в���
		List<NoticeListView> notices = this.noticeDAO.getNoticeByConditions(
				keyword, isacamgr, isteacher, isstudent, start,
				PageResult.PAGE_COUNT);

		// ���÷��ؽ��
		NoticePageResult noticePageResult = new NoticePageResult();

		noticePageResult.setCurrentPage(currentPage);

		noticePageResult.setTotalCount(totalCount);

		noticePageResult.setTotalPage(totalPage);

		// ����֪ͨ���
		noticePageResult.setNotices(notices);

		return noticePageResult;
	}

	/*
	 * getters and setters
	 */

	public NoticeDAO getNoticeDAO() {
		return noticeDAO;
	}

	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

}
