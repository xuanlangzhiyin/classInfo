package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.model.Cmcredit;
import com.wuhei.cms.model.joint.CmcreditDetailView;
import com.wuhei.cms.model.joint.CmcreditListView;

public interface CmcreditService {

	/**
	 * 业务名称：根据cmreportid查找到该任务报告所对应的任务评分列表
	 * 
	 * 业务描述：根据任务报告的id查看该任务报告所属的个人或者小组的任务评分列表。
	 * 
	 * 实现方案：根据参数cmreportid，在"任务报告cmreport"表中，取出该cmreport,判断字段isgroup是否为空，
	 * 如果isgroup字段为空，则表明该任务报告是个人任务，
	 * 可调用DAO层的接口getCmcreditDetailViewsByCmreportidForPesonal,
	 * 得到该提交学生的任务评分cmcredit。 如果isgroup字段不为空，则表明该任务报告是小组任务，
	 * 可调用DAO层的接口getCmcreditDetailViewsByCmreportidForCmgroup,
	 * 得到提交该任务报告的任务分组的所有小组成员的cmredit列表。
	 * 
	 * @param cmgroupid任务小组的id
	 * @return list<CmcreditDetailView>任务评分列表
	 */
	public List<CmcreditDetailView> getCmcreditDetailViewsByCmreportid(
			Integer cmreportid);
	
	/**
	 * 业务名称：根据cmgroupid查找到该任务小组所对应的任务评分列表
	 * 
	 * 业务描述：根据任务小组的id查看该任务小组的任务评分列表。
	 * 
	 * 实现方案：根据参数cmgroupid，在"任务评分cmcredit"表中，取出cmcredit,
	 * 可调用DAO层的接口getCmcreditDetailViewsByCmgroupid,
	 * 得到该任务分组的所有小组成员的cmredit列表。
	 * 
	 * @param cmgroupid任务小组的id
	 * @return list<CmcreditDetailView>任务评分列表
	 */
	public List<CmcreditDetailView> getCmcreditDetailViewsByCmgroupid(
			Integer cmgroupid);

	/**
	 * 业务名称：根据任务评分id获取任务评分
	 * 
	 * 业务描述：需要通过某个任务评分的id获取该任务评分的信息
	 * 
	 * 实现方案：根据参数cmcreditid，在"任务评分cmcredit"表中，查询该任务评分cmcredit,并返回。
	 * 
	 * @param cmcreditid任务评分id
	 * @return Cmcredit返回的任务评分
	 */
	public Cmcredit getCmcreditById(Integer cmcreditid);

	/**
	 * 业务名称：更新Cmcredit
	 * 
	 * 业务描述：需要对某个任务评分的内容进行更新
	 * 
	 * 实现方案：根据参数cmcredit,在"任务评分cmcredit"表中，调用DAO层的updateCmcredit函数，更新该任务评分。
	 * 
	 * @param cmcredit所需更新的任务评分实例
	 */
	public void updateCmcredit(Cmcredit cmcredit);

	/**
	 * 业务名称：插入Cmcredit
	 * 
	 * 业务描述：需要在数据库中插入一条任务评分
	 * 
	 * 实现方案：根据参数：cmcredit,在"任务评分cmcredit"表中，调用DAO层的insertCmcredit函数，插入该任务评分。
	 * 
	 * @param cmcredit所需插入的任务评分实例
	 */
	public void insertCmcredit(Cmcredit cmcredit);
	
	/**
	 * 业务名称：通过cstudengid 和  cmissionid 查找学生某项任务的详细评分
	 * 
	 */
	public CmcreditDetailView getCmcreditDetailViewByCondition(Integer cstudentid, Integer cmissionid);
	
	/**
	 * 通过课程courseid和cstudentid获得一个学生在一门课程中参加的所有任务得分情况
	 * @param cstudentid
	 * @param courseid
	 * @return
	 */
	public List<CmcreditListView> getCmcreditListViewsByCondition(Integer cstudentid, Integer courseid);

}
