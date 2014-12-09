package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Csetting;




public interface CsettingDAO {

	/**
	 * 向数据库插入课程设置
	 * @param csetting 要插入的课程设置实例
	 */
	public void insertCsetting(Csetting csetting);


	/**
	 * 在数据库更新课程设置
	 * @param csetting 要更新的课程设置实例
	 */
	public void updateCsetting(Csetting csetting);


	/**
	 * 从数据库删除课程设置
	 * @param id 要删除的课程设置id
	 */
	public void deleteCsetting(Integer id);

	/**
	 * 从数据库查询课程设置
	 * @param id 要查询的课程设置id
	 * @return 返回的课程设置实例
	 */
	public Csetting getCsetting(Integer id);

	/**
	 * 按条件查找专业课程列表
	 * 
	 * @param type
	 *            专业课程类型 0：必修|1：选修
	 * @param keyword
	 *            按专业课程名搜索
	 * @return 专业课程list
	 */
	public List<Csetting> getCsettingListByConditions(
			@Param(value = "type") String type,
			@Param(value = "keyword") String keyword);

	/**
	 * 按编码查找专业课程列表
	 * 为了批量批量导入Ceindex 而写的接口
	 * @param code
	 *            专业课程编码
	 * @return
	 */
	public List<Csetting> getCsettingListByCode(@Param(value = "code")String code);
	
	/**
	 * 根据专业id获取该专业的所以课程设置
	 * @param csettingId
	 * @return
	 */
	public List<Csetting> getCsettingListByMajorId(Integer csettingId);
	
	/**
	 * 专业课程列表
	 * 进行专业课程操作，返回所有专业课程最基本信息
	 * @return 返回的专业课程信息列表
	 */
	public List<Csetting> getCsettingList();
	
	public Csetting getCsettingByCondition(
			@Param(value="code")String code,
			@Param(value="majorid")Integer majorid);
}