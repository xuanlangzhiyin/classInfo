package com.wuhei.cms.dao.basic;

import java.util.List;

import com.wuhei.cms.model.Department;

public interface DepartmentDAO {

   /**
    *  �����ݿ����Ժϵ��Ϣ
    * @param department
    *       Ժϵ��ʵ��
    */
	public void insertDepartment(Department department);


	/**
	 * �����ݿ����Ժϵ��Ϣ
	 * @param department
	 *       Ժϵ��ʵ��
	 */
	public void updateDepartment(Department department);


	/**
	 * �����ݿ�ɾ��Ժϵ��Ϣ
	 * @param id
	 *   Ҫɾ����Ժϵid
	 */
	public void deleteDepartment(Integer id);

    /**
     * �����ݿ��ѯԺϵ��Ϣ
	 * @param id
	 *   Ҫ��ѯ��Ժϵid
     */
	public Department getDepartment(Integer id);
	

	/**
	 * �����ݿⷵ������Ժϵ��Ϣ
	 * @return Ժϵ��list
	 */
	public List<Department> getDepartmentList();
	
	/**
	 * ����ѧУid���ظ�ѧУ��ӵ�е�ѧԺ�б�
	 * 
	 * @param unversityid
	 * @return
	 */
	public List<Department> getDepartmentListByUniversityId(Integer unversityid);

}