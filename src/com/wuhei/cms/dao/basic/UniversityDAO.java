package com.wuhei.cms.dao.basic;


import com.wuhei.cms.model.University;


import java.util.List;

public interface UniversityDAO {

	/**
	 * 向数据库插入学校信息
	 * @param university
	 *   要插入的学校信息实例
	 */
	public void insertUniversity(University university);

	/**
	 * 向数据库更新学校信息
	 * @param university
	 *   要更新的学校信息实例
	 */
	public void updateUniversity(University university);

	/**
	 * 从数据库删除学校信息
	 * @param university
	 *   要删除的学校信息实例
	 */
	public void deleteUniversity(Integer id);


	/**
	 * 从数据库查询学校信息
	 * @param id 要查询的学校id
	 * @return  返回的查询结果
	 */
	public University getUniversity(Integer id);
	
	/**
	 * 获取所有学校列表
	 * 
	 * @return
	 */
	public List<University> getUniversityList();
}