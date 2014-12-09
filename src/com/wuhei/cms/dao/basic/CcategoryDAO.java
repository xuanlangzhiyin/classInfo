package com.wuhei.cms.dao.basic;

import com.wuhei.cms.model.Ccategory;



import java.util.List;

public interface CcategoryDAO {
	

	/**
	 * 向数据库插入课程类别信息
	 * @param ccategory
	 *    课程类别类实例
	 */
	public void insertCcategory(Ccategory ccategory);


	/**
	 * 在数据库更新课程类别信息
	 * @param ccategory
	 * 课程类别类实例
	 */
	public void updateCcategory(Ccategory ccategory);


	/**
	 * 从数据库删除课程类别信息
	 * @param id 要删除的课程类别的id
	 */
	public void deleteCcategory(Integer id);

 
	/**
	 * 从数据库查询课程类别信息
	 * @param id  要查询的课程类别的id
	 * @return 返回课程类别类的实例
	 */
	public Ccategory getCcategory(Integer id);
	
	/**
	 * 从数据库查询所有课程类别
	 * @return
	 */
	public List<Ccategory> getCcategoryList();
	
	public Integer getCcategoryIdByName(String name);
}