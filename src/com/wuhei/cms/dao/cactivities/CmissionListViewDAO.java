package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.CmissionListView;

public interface CmissionListViewDAO {

	public List<CmissionListView> getCmissionListView4Teacher(
			@Param(value = "courseid") Integer courseid);
	
	public List<CmissionListView> getCmissionListViewByCondition(
			@Param(value = "courseid") Integer courseid,
			@Param(value = "mtype") String mtype);		

	/**
	 * @param courseid
	 * @param studentId
	 * @return
	 */
	public List<CmissionListView> getCmissionListView4Student(
			@Param(value = "courseid") Integer courseid,
			@Param(value = "cstudentId") Integer cstudentId);
	
}
