package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.TeacherUserListView;

public interface TeacherUserListViewDAO {
	/**
	 * 教师用户后台分页
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
	 * 获取满足关键字教师用户的数量
	 * @param keyword
	 * @return
	 */

	public Integer countTeacherUserByCondition(
			@Param(value = "keyword") String keyword);
}
