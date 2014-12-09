package com.wuhei.cms.dao.cactivities;

import com.wuhei.cms.model.Cmgroup;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * ��������dao��ӿ�
 *
 */
public interface CmgroupDAO {
	
	/**
	 * ����ݿ�������������Ϣ
	 * @param cmgroup ��Ҫ��������������Ϣ
	 */
	public void insertCmgroup(Cmgroup cmgroup);
	
	
	/**
	 * �����ݿ��е����������Ϣ
	 * @param cmgroup ��Ҫ���µ����������Ϣ
	 */
	public void updateCmgroup(Cmgroup cmgroup);
	
	
	/**
	 * ����ݿ�ɾ�����������Ϣ
	 * @param cmgroupid ��Ҫɾ�����������id
	 */
	public void deleteCmgroup(Integer cmgroupid);
	
	
	/**
	 * ��ѯһ���ض������������Ϣ
	 * @param cmgroupid ��Ҫ��ѯ����������id
	 */
	public Cmgroup getCmgroup(Integer cmgroupid);
	
	/**
	 * ���������ѯ�����б�
	 * @param isinvolved �Ƿ��������
	 * @param cmissionid Ҫ�鿴���������
	 * @return �����б�
	 */
	List<Cmgroup> getCmgroupListByCondtion(
			@Param(value = "isinvolved")Boolean isinvolved,
			@Param(value = "cmissionid")Integer cmissionid);
	
	
	/**
	 * ��ѯĳ����鳤id
	 * @param id Ҫ�����
	 * @return �����鳤
	 */
	Integer getCmgroup_Leader(
			@Param(value = "id")Integer id);
	
	/**
	 * ��������С������
	 * @param id  Ҫ���µ�С��
	 * @param count  С������
	 */
	void updateCmgroup_Count(
			@Param(value = "id")Integer id,
			@Param(value = "count")Short count);
	
	/**
	 * �������С�鳤
	 * @param id Ҫ���鳤��С��
	 * @param studentid Ҫ���鳤��ѧ��
	 */
	void updateCmgroup_Leader(
			@Param(value = "id")Integer id,
			@Param(value = "studentid")Integer studentid);
	
	/**
	 * ɾ���cmissionid��ص���������С��
	 * @param id Ҫ���鳤��С��
	 * @param studentid Ҫ���鳤��ѧ��
	 */
	void deleteCmgroupByCmissionid(Integer cmissionid);
	
	/**
	 * ��ѯ��ǰcmission����ѧ�������
	 */

	public Integer countCMgroupedStudent(
			@Param(value = "cmissionid") Integer cmissionid);
	
}