package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.TeacherDetailView;



/**
 * ����ӿ�
 *
 */
public interface TeacherDetailViewDAO {
	
	/**
	 * ���н�ʦɸѡ�� �г����������Ľ�ʦ�������Ϣ�� ɸѡ��������ѧԺId�� �����ؼ���
	 * 
	 * @param departmentId
	 * @param majorid
	 * @return
	 */
	public List<TeacherDetailView> getTeacherDetailViewListByCondition(
			@Param(value = "departmentId") Integer departmentId,
			@Param(value = "majorId") Integer majorid);
	
	public TeacherDetailView getTeacherDetailViewById(Integer id);
}
