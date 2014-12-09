package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.CmcreditListView;

public interface CmcreditListViewDAO 
{
	List<CmcreditListView> getCmcreditListViewsByCondition(
			@Param(value = "cstudentid")Integer cstudentid,
			@Param(value = "courseid")Integer courseid
);
}