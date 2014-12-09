package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.exception.AccessDeniedException;
import com.wuhei.cms.model.Cmcredit;
import com.wuhei.cms.model.Cmission;
import com.wuhei.cms.model.Cmstudent;
import com.wuhei.cms.model.Cstudent;
import com.wuhei.cms.model.joint.CmissionListView;

public interface CmissionService {

	/**
	 * 根据授课教师id和课程id查询所有该老师在该课程的任务
	 * 
	 * @param teacherid
	 * @param Courseid
	 * @return
	 */
	public List<Cmission> getCmissionList(Integer teacherid, Integer courseid);

	/**
	 * 教师查询课程活动列表
	 * 
	 * @param courseid
	 * @return
	 */
	public List<CmissionListView> getCmissionList4Teacher(Integer courseid);

	/**
	 * 学生查询cmission列表
	 */

	public List<CmissionListView> getCmissionList4Student(Integer courseid,Integer cstudentId);

	/***
	 * 通过courseid和cstuentId找到学生参加但老师未给分的任务列表
	 * @param courseid
	 * @param cstudentId
	 * @return
	 */
	public List<Cmission>getUnmarkedCmissionListByCondition4Student(Integer cstudentId,Integer courseid);
	
	
	/**
	 * 业务名称：新建一个课程任务cmission
	 * 
	 * 业务描述：教师需要新建一个课程任务，写完任务简介后提交。这个课程任务应该是小组任务。
	 * 
	 * 实现方案：
	 * 
	 * 往"课程任务cmission"表中插入课程任务cmission。
	 * 
	 * 根据参数课程任务实例：cmission,取出其中的courseid,
	 * 根据courseid在"选课学生cstudent"表中查询该课程的所有选课学生列表cstudentList,
	 * 根据courseid在"课程分组cgroup"表中查询该课程的所有课程分组cgroupList。
	 * 
	 * 由于已分组的cmstudent中含有cmgroupid外键依赖，所以cmgroupid字段先不要设置到cmstudent中，
	 * 然后往cmstudent表中插入对应的cstudentList列表中的数据。
	 * 另一方面，由于cmgroup中含有leaderid外键依赖，所以leaderid字段先不要设置到cmgroup中，
	 * 然后往cmgroup表中插入对应的cgroupList列表中的数据。
	 * 
	 * 最后，根据一定的操作，可查找到对应的cmgroupid，
	 * 填入cmstudent表中的cmgroupid字段，即可完善cmstudent表中的信息。
	 * 
	 * 同理，根据一定的操作之后，可查找到对应的目标字段值leaderid，
	 * 填入cmgroup表中的leaderid字段，即可完善cmgroup表中的信息。
	 * 
	 * 
	 * @param cmission所需新建的课程任务cmission实例
	 */
	public void addCmission(Cmission cmission);
	
	/**
	 * 新增个人Cmission，需将cstudent添加到cmstudent
	 */
	
	public void addCmission4person(Cmission cmission);
	
	
	/**
	 * 删除一条任务
	 * 
	 * @param id
	 */
	public void deleteCmission(Integer id) throws AccessDeniedException;

	/**
	 * 修改一条任务
	 * 
	 * @param cmission
	 */
	public void modifyCmission(Cmission cmission);

	/**
	 * 根据student.id，isinvolved, courseid在cmstudent cmission中查询该学生参与的课程任务cmission
	 * 在表(cmcredit)中查看该任务评分
	 * 
	 * @param cmstudentid
	 * @return
	 */
	public List<Cmission> stuGetCmissionList(Integer cmstudentid);

	/**
	 * 根据id查询课程任务信息
	 * 
	 * @param id
	 * @return
	 */
	public Cmission getCmission(Integer cmissionid);

	/**
	 * 批量更新任务学生 用于更改学生的参与状态
	 * 
	 * @param id
	 */
	public void update(List<Cmstudent> cmstudentList);

	/**
	 * 根据学生studentid和课程courseid 获取该学生在课程中的对象 Cstudent
	 * 
	 * @param studentid
	 *            学生在student表中的主键
	 * @param courseid
	 *            开设课程 course表中的主键
	 * @return
	 */
	public Cstudent getCStudentByCondition(Integer studentid, Integer courseid);
	
	
	public List<CmissionListView> getCmissionListViewByCondition(Integer courseid,String mtype);
	
	/**
	 * 将现在的cmissionid和想要切换的任务的wantedCmissionid传入，将想要切换的任务的小组模版切换到当前任务小组
	 * @param cmissionid
	 * @param wantedCmission
	 * @return
	 */
	
	public void changeGroupToMissionGroup(Integer cmissionid,Integer wantedCmission);
	
	public void changeGroupToCourseGroup (Integer cmissionid,Integer courseid);
	
	/**
	 * 更新任务
	 * @param cmission  by zhuchengrong
	 */
	public void updateCmission(Cmission cmission);
	
	/**
	 * 业务名称：提交学生个人任务分数
	 * 
	 * 业务描述：需要往cmcredit中插入一条记录,更新cmstudent,cmission信息
	 * 
	 * by zhuchengrong
	 * @param cmstudent
	 * @param cmcredit
	 */
	public void savePersonalMissionScore(Cmstudent cmstudent,Cmcredit cmcredit);
	
	
	
}
