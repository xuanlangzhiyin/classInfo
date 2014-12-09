package com.wuhei.cms.dao.basic;


import com.wuhei.cms.model.University;


import java.util.List;

public interface UniversityDAO {

	/**
	 * �����ݿ����ѧУ��Ϣ
	 * @param university
	 *   Ҫ�����ѧУ��Ϣʵ��
	 */
	public void insertUniversity(University university);

	/**
	 * �����ݿ����ѧУ��Ϣ
	 * @param university
	 *   Ҫ���µ�ѧУ��Ϣʵ��
	 */
	public void updateUniversity(University university);

	/**
	 * �����ݿ�ɾ��ѧУ��Ϣ
	 * @param university
	 *   Ҫɾ����ѧУ��Ϣʵ��
	 */
	public void deleteUniversity(Integer id);


	/**
	 * �����ݿ��ѯѧУ��Ϣ
	 * @param id Ҫ��ѯ��ѧУid
	 * @return  ���صĲ�ѯ���
	 */
	public University getUniversity(Integer id);
	
	/**
	 * ��ȡ����ѧУ�б�
	 * 
	 * @return
	 */
	public List<University> getUniversityList();
}