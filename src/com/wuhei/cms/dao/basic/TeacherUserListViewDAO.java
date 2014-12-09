package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.TeacherUserListView;

public interface TeacherUserListViewDAO {
	/**
	 * ��ʦ�û���̨��ҳ
	 * @param keyword
	 * @param start
	 * @param count
	 * @return
	 */

	public List<TeacherUserListView> listTeacherUserByCondition(
			@Param(value = "keyword") String keyword,
			@Param(value = "start") Integer start,
			@Param(value = "count") Integer count);

	/** 
	 * ��ȡ����ؼ��ֽ�ʦ�û�������
	 * @param keyword
	 * @return
	 */

	public Integer countTeacherUserByCondition(
			@Param(value = "keyword") String keyword);
}
