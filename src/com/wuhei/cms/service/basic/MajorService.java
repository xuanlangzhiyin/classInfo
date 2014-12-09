package com.wuhei.cms.service.basic;

import java.util.List;

import com.wuhei.cms.model.Major;
import com.wuhei.cms.model.joint.MajorDetailView;
import com.wuhei.cms.model.joint.MajorListView;
import com.wuhei.cms.search.result.MajorPageResult;

public interface MajorService {

	/**
	 * 业务名称:获得该院系下的所有专业id、名称
	 * 业务描述:新增学生时，需要选择该学生所属的专业，使用联动下拉框
	 * 
	 * @param id
	 * @return
	 */
	public List<Major> getMajorListByDepartmentId(Integer id);
	
	public List<MajorListView> getMajorListViewListByDepartmentId(Integer departmentid);
	
	public MajorPageResult getMajorByConditions(Integer departmentid, Integer currentPage, String keyword);
	/**
	 * 通过excel表批量增加专业
	 */


	/**
	 * 系统用户：根据id返回专业的详细信息
	 *
	 */
	public MajorDetailView getMajorDetailViewByMajorid(Integer majorid);


}
