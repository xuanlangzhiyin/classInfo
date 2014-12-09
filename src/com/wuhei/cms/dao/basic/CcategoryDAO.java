package com.wuhei.cms.dao.basic;

import com.wuhei.cms.model.Ccategory;



import java.util.List;

public interface CcategoryDAO {
	

	/**
	 * �����ݿ����γ������Ϣ
	 * @param ccategory
	 *    �γ������ʵ��
	 */
	public void insertCcategory(Ccategory ccategory);


	/**
	 * �����ݿ���¿γ������Ϣ
	 * @param ccategory
	 * �γ������ʵ��
	 */
	public void updateCcategory(Ccategory ccategory);


	/**
	 * �����ݿ�ɾ���γ������Ϣ
	 * @param id Ҫɾ���Ŀγ�����id
	 */
	public void deleteCcategory(Integer id);

 
	/**
	 * �����ݿ��ѯ�γ������Ϣ
	 * @param id  Ҫ��ѯ�Ŀγ�����id
	 * @return ���ؿγ�������ʵ��
	 */
	public Ccategory getCcategory(Integer id);
	
	/**
	 * �����ݿ��ѯ���пγ����
	 * @return
	 */
	public List<Ccategory> getCcategoryList();
	
	public Integer getCcategoryIdByName(String name);
}