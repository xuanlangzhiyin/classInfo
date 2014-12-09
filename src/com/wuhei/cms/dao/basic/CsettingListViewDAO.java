package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.CsettingListView;

public interface CsettingListViewDAO {

	/**
	 * 进行专业课程筛选， 列出符合条件的专业课程最基本信息， 筛选条件包括专业Id、 专业名称关键字
	 * @param majorId
	 * @param keyword
	 * @return
	 */
	public List<CsettingListView> listCsettingListByConditions(
			@Param(value = "majorId") Integer majorId,
			@Param(value = "keyword") String keyword,
	        @Param(value = "start") Integer start,
	        @Param(value = "count") Integer count);
	
	public Integer countCsettingByConditions(
			@Param(value = "majorId") Integer majorId,
			@Param(value = "keyword") String keyword);
}
