package com.wuhei.cms.service.notice;

import com.wuhei.cms.model.Notice;
import com.wuhei.cms.search.result.NoticePageResult;

public interface NoticeService {

	/**
	 * �·���һ��֪ͨ
	 * 
	 * @param notice
	 *            Ҫ������֪ͨ
	 */
	public void insertNotice(Notice notice);

	/**
	 * �޸�һ��֪ͨ������
	 * 
	 * @param notice
	 *            Ҫ�޸ĵ�֪ͨ������
	 */
	public void modifyNotice(Notice notice);

	/**
	 * �鿴һ��֪ͨ����ϸ��Ϣ
	 * 
	 * @param noticeid
	 *            Ҫ�鿴��֪ͨ��id
	 * @return ֪ͨ����ϸ��Ϣ
	 */
	public Notice getNotice(Integer noticeid);

	/**
	 * ɾ��һ��֪ͨ
	 * 
	 * @param noticeid
	 *            Ҫɾ����֪ͨ��id
	 */
	public void deleteNotice(Integer noticeid);

	
	/**
	 * ����������ѯ֪ͨ
	 * 
	 * @param isacamgr �ؼ���
	 * @param isacamgr �Ƿ����Ա�ɼ�
	 * @param isteacher �Ƿ��ʦ�ɼ�
	 * @param isstudent �Ƿ�ѧ���ɼ�
	 * @return ����������֪ͨ�б�
	 */
	public NoticePageResult getNoticeByConditions(Integer currentPage, String keyword, Boolean isacamgr,
			Boolean isteacher, Boolean isstudent);

}
