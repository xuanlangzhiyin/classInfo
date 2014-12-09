package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.StudentListView;

/** 学生列表视图DAO */
public interface StudentListViewDAO {
	/**
	 * 功能：根据条件返回记录总数
	 * 
	 * 用于分页时候统计记录总数和分页的数目
	 * 
	 * @param keyword
	 *            姓名关键字 为null时忽略该条件
	 * 
	 * @return 相关记录的数目
	 */
	public Integer countStudentByCondition(
			@Param(value = "majorid") Integer majorid,
			@Param(value = "grade") String grade,
			@Param(value = "stuclass") String stuclass,
			@Param(value = "keyword") String keyword);

	/**
	 * 功能：根据条件返回相关的Student记录
	 * 
	 * @param grade
	 * @param stuclass
	 * @param keyword
	 * @param start
	 * @param count
	 * @return
	 */
	public List<StudentListView> listStudentByCondition(
			@Param(value = "majorid") Integer majorid,
			@Param(value = "grade") String grade,
			@Param(value = "stuclass") String stuclass,
			@Param(value = "keyword") String keyword,
			@Param(value = "start") Integer start,
			@Param(value = "count") Integer count);
}
