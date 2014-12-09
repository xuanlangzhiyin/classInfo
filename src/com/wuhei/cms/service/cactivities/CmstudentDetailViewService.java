package com.wuhei.cms.service.cactivities;

import java.util.List;


import com.wuhei.cms.model.joint.CmstudentDetailView;



public interface CmstudentDetailViewService {

	/**
	 * 
	 * 获取学生参与任务详细信息
	 * @param cstudentid 学生id
	 * @param courseid  课程id
	 * @param cmgroupid 组id
	 * @param cmissionid 任务id
	 * @param isinvolved 是否参与任务
	 * @return
	 */
	public List<CmstudentDetailView> getCmstudentDetailListByCondition(
			Integer cstudentid, Integer courseid, Integer cmgroupid, Integer cmissionid, Boolean isinvolved);
}
