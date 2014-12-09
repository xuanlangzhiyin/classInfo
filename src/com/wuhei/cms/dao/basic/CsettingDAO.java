package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Csetting;




public interface CsettingDAO {

	/**
	 * �����ݿ����γ�����
	 * @param csetting Ҫ����Ŀγ�����ʵ��
	 */
	public void insertCsetting(Csetting csetting);


	/**
	 * �����ݿ���¿γ�����
	 * @param csetting Ҫ���µĿγ�����ʵ��
	 */
	public void updateCsetting(Csetting csetting);


	/**
	 * �����ݿ�ɾ���γ�����
	 * @param id Ҫɾ���Ŀγ�����id
	 */
	public void deleteCsetting(Integer id);

	/**
	 * �����ݿ��ѯ�γ�����
	 * @param id Ҫ��ѯ�Ŀγ�����id
	 * @return ���صĿγ�����ʵ��
	 */
	public Csetting getCsetting(Integer id);

	/**
	 * ����������רҵ�γ��б�
	 * 
	 * @param type
	 *            רҵ�γ����� 0������|1��ѡ��
	 * @param keyword
	 *            ��רҵ�γ�������
	 * @return רҵ�γ�list
	 */
	public List<Csetting> getCsettingListByConditions(
			@Param(value = "type") String type,
			@Param(value = "keyword") String keyword);

	/**
	 * ���������רҵ�γ��б�
	 * Ϊ��������������Ceindex ��д�Ľӿ�
	 * @param code
	 *            רҵ�γ̱���
	 * @return
	 */
	public List<Csetting> getCsettingListByCode(@Param(value = "code")String code);
	
	/**
	 * ����רҵid��ȡ��רҵ�����Կγ�����
	 * @param csettingId
	 * @return
	 */
	public List<Csetting> getCsettingListByMajorId(Integer csettingId);
	
	/**
	 * רҵ�γ��б�
	 * ����רҵ�γ̲�������������רҵ�γ��������Ϣ
	 * @return ���ص�רҵ�γ���Ϣ�б�
	 */
	public List<Csetting> getCsettingList();
	
	public Csetting getCsettingByCondition(
			@Param(value="code")String code,
			@Param(value="majorid")Integer majorid);
}