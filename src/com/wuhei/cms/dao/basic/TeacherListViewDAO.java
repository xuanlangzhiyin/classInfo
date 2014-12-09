package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.TeacherListView;

public interface TeacherListViewDAO {
	/**
	 * ���н�ʦɸѡ�� �г����������Ľ�ʦ�������Ϣ�� ɸѡ��������ѧԺId�� �����ؼ���
	 * @param departmentId
	 * @param keyword
	 * @return
	 */
	public List<TeacherListView> listTeacherByConditions(
			@Param(value = "departmentId") Integer departmentId,
			@Param(value = "keyword") String keyword,
	        @Param(value = "start") Integer start,
	        @Param(value = "count") Integer count);
	
	public Integer countTeacherByConditions(
			@Param(value = "departmentId") Integer departmentId,
			@Param(value = "keyword") String keyword);
}
