package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Cmission;



public interface CmissionDAO {

	/**
	 * ����γ�����
	 * @param cmission  ����γ�����ʵ��
	 */
	public void insertCmission(Cmission cmission);
	

	/**
	 * ���¿γ�����
	 * @param cmission ���¿γ�����ʵ��
	 */
	public void updateCmission(Cmission cmission);
	
	/**
	 * ɾ��γ�����
	 * @param id  Ҫɾ��Ŀγ�����id
	 */
	public void deleteCmission(Integer id);
	
	/**
	 * ��ȡ�γ�����
	 * @return Ҫ��ѯ�Ŀγ�����ʵ��
	 */
	public Cmission getCmission(Integer id);
	
	/**
	 * ��ѯ�����б�
	 * @param teacherid �����������ʦ
	 * @param courseid ���������Ŀγ�
	 * @return �����б�
	 */
	public List<Cmission> getCmissionListByCondition(
			@Param(value = "teacherid")Integer teacherid,
			@Param(value = "courseid")Integer courseid);
	
	public  List<Cmission> getUnmarkedCmissionListByCondition4Student(
			@Param(value = "cstudentId")Integer cstudentId,
			@Param(value = "courseid")Integer courseid);
	
	
}