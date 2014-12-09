package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.CstudentDetailView;



public interface CstudentDetailViewDAO {

	/**
	 * ����ĳ��course���ѷ����CstudentDetailViewByCourseid
	 * 
	 * @param courseid
	 *            ����γ�id
	 * @return
	 */
	public List<CstudentDetailView> listGroupedCstudentDetailViewByCourseid(
			Integer courseid);

	/**
	 * ����ĳ��course��δ�����CstudentDetailViewByCourseid
	 * 
	 * @param courseid
	 *            ����γ�id
	 * @return
	 */
	public List<CstudentDetailView> listUnGroupedCstudentDetailViewByCourseid(
			Integer courseid);

	/**
	 * ����ĳ���γ̷����CstudentDetailView�б�
	 * 
	 * @param cgroupid�γ̷���id
	 * @return List<CstudentDetailView> �ÿγ̷����CstudentDetailView�б�
	 */
	public List<CstudentDetailView> listStudentDetailByCgroupid(
			@Param(value = "cgroupid") Integer cgroupid);

	/**
	 * ����ĳ����γ̵�����ѡ��ѧ��CstudentDetailView�б�
	 * 
	 * @param courseid����γ�id
	 * @return List<CstudentDetailView>ѡ��ѧ��CstudentDetailView�б�
	 */
	public List<CstudentDetailView> listCstudentDetailViewByCourseid(
			Integer courseid);

	/**
	 * ��ݿγ̷���ѧ��id��ȡ�γ̷���ѧ�������ϢCstudentDetailView
	 * 
	 * @param id�γ̷���ѧ��id
	 * @return CstudentDetailView�γ̷���ѧ�������ϢCstudentDetailView
	 */
	public CstudentDetailView getCstudentDetailViewById(Integer id);
}