package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Cmcredit;


public interface CmcreditDAO {
	/**
	 * ������������
	 * 
	 * @param cmcredit������������ʵ��
	 */
	public void insertCmcredit(Cmcredit cmcredit);

	/**
	 * ������������
	 * 
	 * @param cmcredit�����������ֵ�ʵ��
	 */
	public void updateCmcredit(Cmcredit cmcredit);

	/**
	 * ɾ����������
	 * 
	 * @param idҪɾ����������ֵ�id
	 */
	public void deleteCmcredit(Integer id);

	/**
	 * ��ȡ��������
	 * 
	 * @param idҪ��ѯ���������ֵ�id
	 * @return Cmcredit���ص���������ʵ��
	 */
	public Cmcredit getCmcredit(Integer id);

	/**
	 * ͨ��cmissionid���ҷ�����������������б�
	 * 
	 * @param cmissionid�γ�����id
	 * @return List<Cmcredit>������������������б�
	 */
	public List<Cmcredit> getCmcreditListByCondition(
			@Param(value = "cstudentid") Integer cstudentid,
			@Param(value = "cmissionid") Integer cmissionid);

	/**
	 * ���id����һ���������ֵĵ÷�:credit
	 * 
	 * @param id��Ҫ���µ���������id
	 * @param credit�µĵ÷�
	 */
	void updateCmcredit_credit(@Param(value = "id") Integer id,
			@Param(value = "credit") Byte credit);

}
