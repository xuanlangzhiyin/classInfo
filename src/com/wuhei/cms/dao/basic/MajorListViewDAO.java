package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.MajorListView;

public interface MajorListViewDAO {
	/**
	 * ���ԺϵId:departmentId���ظ�Ժϵ��ӵ�е�רҵ�б�
	 * @param id 
	 * @return
	 */
	public List<MajorListView> getMajorListViewListByDepartmentId(Integer departmentid);
	
	public List<MajorListView> getMajorListViewListByConditions(
			@Param(value = "departmentid")Integer departmentid,
			@Param(value = "keyword") String keyword,
			@Param(value = "start")Integer start, 
			@Param(value = "count")Integer count);
}
