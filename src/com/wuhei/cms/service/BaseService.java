package com.wuhei.cms.service;

import java.util.List;

import com.wuhei.cms.model.Ccategory;
import com.wuhei.cms.model.Department;
import com.wuhei.cms.model.Major;

/**
 * Service���common�ӿ�
 * 
 *
 */
public interface BaseService {
	
	/**
	 * �γ�����б� �������γ�����ʱ����Ҫ����ѡ��γ�����б�
	 * @return
	 */
	public List<Ccategory> getCcategoryList();

	/**
	 * �������Ժϵid������
	 * ������ʦʱ����Ҫѡ��ý�ʦ������Ժϵ��ʹ��������
	 * @return
	 */
	public List<Department> getDepartment();
	
	/**
	 * רҵ�б� �������γ�����ʱ����Ҫ����ѡ��רҵ
	 * @param id
	 * @return
	 */
	public List<Major> getMajorListByDepartmentId(Integer id);

	
}
