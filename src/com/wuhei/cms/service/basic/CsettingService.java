package com.wuhei.cms.service.basic;

import java.util.List;

import com.wuhei.cms.model.Csetting;
import com.wuhei.cms.model.deletedata.CsettingDeleteResult;
import com.wuhei.cms.model.joint.CsettingDetailView;
import com.wuhei.cms.search.result.CsettingPageResult;

public interface CsettingService {

	/**
	 * 根据id 找出所有课程设置基本信息
	 * 
	 * @param csettingid
	 * @return
	 */
	public Csetting getCsetting(Integer csettingid);

	/**
	 * 课程设置列表
	 * 
	 * 进行课程设置操作，列出所有课程设置基本信息
	 * 
	 * @return
	 */
	public List<Csetting> getCsettingList();

	/**
	 * 条件搜索专业课程列表 进行专业课程筛选，列出符合条件的专业课程最基本信息，筛选条件包括专业Id、名称关键字
	 * 
	 * @param MajorId
	 *            专业id 用来筛选所属专业课程
	 * @param keyword
	 *            专业课程名称关键字
	 * @return 返回符合条件的专业课程信息列表
	 */
	public List<Csetting> getCsettingListByConditions(String type,
			String keyword);

	/**
	 * 条件搜索专业课程列表 进行专业课程筛选，列出符合条件的专业课程最基本信息，筛选条件包括专业Id、名称关键字
	 * 
	 * @param MajorId
	 *            专业id 用来筛选所属专业课程
	 * @param keyword
	 *            专业课程名称关键字
	 * @return 返回符合条件的专业课程信息列表
	 */
	public CsettingPageResult listCsettingListByConditions(Integer majorId,
			String keyword, Integer currentPage);

	/**
	 * 新增课程设置
	 * 
	 * @param csetting
	 */
	public void insertCsetting(Csetting csetting);

	

	/**
	 * 删除课程设置 
	 * 
	 * 删除一个课程设置
	 * 
	 * @param id
	 */
	public void deleteCsetting(Integer id);

	
	/**
	 * 修改课程设置 对课程设置进行修改
	 * 
	 * @param csetting
	 */
	public void updateCsetting(Csetting csetting);

	

	/**
	 * 查看课程设置详细信息
	 * 
	 * @param id
	 * @return
	 */
	public CsettingDetailView getCsettingDetailView(Integer id);

	/**
	 * 查看某专业下的所有课程设置详细信息
	 * 
	 * @param majorId
	 * @return
	 */
	public List<CsettingDetailView> getCsettingDetailViewList(Integer majorId);


	/**
	 * 批量删除专业课程 
	 * 
	 * @param csettingid
	 * @return
	 */
	public CsettingDeleteResult deleteCsetting(List<Integer> csettingId);

}
