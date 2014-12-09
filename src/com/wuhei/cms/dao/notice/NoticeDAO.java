package com.wuhei.cms.dao.notice;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Notice;
import com.wuhei.cms.model.joint.NoticeListView;

/**
 * ֪ͨ��DAO
 * 
 */
public interface NoticeDAO {

	/**
	 * ����һ��֪ͨ
	 * 
	 * @param notice
	 *            Ҫ�����֪ͨ
	 */
	public void insertNotice(Notice notice);

	/**
	 * �޸�һ��֪ͨ
	 * 
	 * @param notice
	 *            Ҫ�޸ĵ�֪ͨ����
	 */
	public void updateNotice(Notice notice);

	/**
	 * ɾ��һ��֪ͨ
	 * 
	 * @param id
	 *            Ҫɾ���֪ͨ��id
	 */
	public void deleteNotice(Integer id);

	/**
	 * ��ȡһ��֪ͨ
	 * 
	 * @param id
	 *            Ҫ��ȡ��֪ͨ��id
	 * @return
	 */
	public Notice getNotice(Integer id);

	/**
	 * ���������ѯ֪ͨ
	 * 
	 * @param isacamgr
	 *            �ؼ���
	 * @param isacamgr
	 *            �Ƿ����Ա�ɼ�
	 * @param isteacher
	 *            �Ƿ��ʦ�ɼ�
	 * @param isstudent
	 *            �Ƿ�ѧ��ɼ�
	 * @return ���������֪ͨ�б�
	 */
	public List<NoticeListView> getNoticeByConditions(
			@Param(value = "keyword") String keyword,
			@Param(value = "isacamgr") Boolean isacamgr,
			@Param(value = "isteacher") Boolean isteacher,
			@Param(value = "isstudent") Boolean isstudent,
			@Param(value = "start") Integer start,
			@Param(value = "count") Integer count);

	/**
	 * ������������������
	 * 
	 * @param isacamgr
	 *            �ؼ���
	 * @param isacamgr
	 *            �Ƿ����Ա�ɼ�
	 * @param isteacher
	 *            �Ƿ��ʦ�ɼ�
	 * @param isstudent
	 *            �Ƿ�ѧ��ɼ�
	 * @return
	 */
	public Integer countNoticeByConditions(
			@Param(value = "keyword") String keyword,
			@Param(value = "isacamgr") Boolean isacamgr,
			@Param(value = "isteacher") Boolean isteacher,
			@Param(value = "isstudent") Boolean isstudent);

}
