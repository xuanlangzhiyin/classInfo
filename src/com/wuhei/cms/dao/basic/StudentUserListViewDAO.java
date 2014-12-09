package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.wuhei.cms.model.joint.StudentUserListView;

public interface StudentUserListViewDAO {
	
	

	/**
	 * ѧ���û���̨��ҳ
	 * @param keyword
	 * @param start
	 * @param count
	 * @return
	 */
	
	public List<StudentUserListView> listStudentUserByCondition(
			@Param(value = "keyword") String keyword,
			@Param(value = "start") Integer start,
			@Param(value = "count") Integer count);

	/** 
	 * ��ȡ����ؼ���ѧ���û�������
	 * @param keyword
	 * @return
	 */
	
	public Integer countStudentUserByCondition(
			@Param(value = "keyword") String keyword);
}
