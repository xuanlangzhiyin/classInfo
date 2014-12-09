package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.CmstudentDetailView;



public interface CmstudentDetailViewDAO {

	/**
	
	 * @param cstudentid
	 * @param courseid
	 */
	public List<CmstudentDetailView> getCmstudentDetailListByCondition(
			@Param(value = "cstudentid") Integer cstudentid,
			@Param(value = "courseid") Integer courseid,
			@Param(value = "cmgroupid") Integer cmgroupid,
			@Param(value = "cmissionid") Integer cmissionid,
			@Param(value = "isinvolved") Boolean isinvolved,
			@Param(value = "isgrouped") Boolean isgrouped);

	/**
	 * 
	 * @param id
	 * @param cmissionid
	 * @return CmstudentDetailView
	 */
	public CmstudentDetailView getCmstudentDetailViewById(
			@Param(value = "cmstudentid") Integer cmstudentid);
	
	public List<CmstudentDetailView> listUnGroupedCmstudentDetailViewByCmissionid(
			@Param(value = "cmissionid")Integer cmissionid);
	
}
