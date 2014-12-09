package com.wuhei.cms.service.notice;

import com.wuhei.cms.model.Notice;
import com.wuhei.cms.search.result.NoticePageResult;

public interface NoticeService {

	/**
	 * 新发布一条通知
	 * 
	 * @param notice
	 *            要发布的通知
	 */
	public void insertNotice(Notice notice);

	/**
	 * 修改一条通知的内容
	 * 
	 * @param notice
	 *            要修改的通知的内容
	 */
	public void modifyNotice(Notice notice);

	/**
	 * 查看一条通知的详细信息
	 * 
	 * @param noticeid
	 *            要查看的通知的id
	 * @return 通知的详细信息
	 */
	public Notice getNotice(Integer noticeid);

	/**
	 * 删除一条通知
	 * 
	 * @param noticeid
	 *            要删除的通知的id
	 */
	public void deleteNotice(Integer noticeid);

	
	/**
	 * 根据条件查询通知
	 * 
	 * @param isacamgr 关键字
	 * @param isacamgr 是否教务员可见
	 * @param isteacher 是否教师可见
	 * @param isstudent 是否学生可见
	 * @return 符合条件的通知列表
	 */
	public NoticePageResult getNoticeByConditions(Integer currentPage, String keyword, Boolean isacamgr,
			Boolean isteacher, Boolean isstudent);

}
