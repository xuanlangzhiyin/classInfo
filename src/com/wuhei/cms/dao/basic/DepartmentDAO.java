package com.wuhei.cms.dao.basic;

import java.util.List;

import com.wuhei.cms.model.Department;

public interface DepartmentDAO {

   /**
    *  向数据库插入院系信息
    * @param department
    *       院系类实例
    */
	public void insertDepartment(Department department);


	/**
	 * 在数据库更新院系信息
	 * @param department
	 *       院系类实例
	 */
	public void updateDepartment(Department department);


	/**
	 * 从数据库删除院系信息
	 * @param id
	 *   要删除的院系id
	 */
	public void deleteDepartment(Integer id);

    /**
     * 从数据库查询院系信息
	 * @param id
	 *   要查询的院系id
     */
	public Department getDepartment(Integer id);
	

	/**
	 * 从数据库返回所有院系信息
	 * @return 院系的list
	 */
	public List<Department> getDepartmentList();
	
	/**
	 * 根据学校id返回该学校所拥有的学院列表
	 * 
	 * @param unversityid
	 * @return
	 */
	public List<Department> getDepartmentListByUniversityId(Integer unversityid);

}