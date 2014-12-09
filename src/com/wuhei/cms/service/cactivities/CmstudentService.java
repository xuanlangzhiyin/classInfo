package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.exception.AccessDeniedException;
import com.wuhei.cms.model.Cmstudent;
import com.wuhei.cms.model.joint.CmstudentDetailView;


/**
 * 提供对Cstudent部分业务操作调用的service层
 * 
 * 
 */
public interface CmstudentService {

	/**
	 * 业务名称：根跟任务参与学生id获取任务参与学生
	 * 
	 * 业务描述：通过任务参与学生id，获取该任务参与学生的实例，并返回。
	 * 
	 * 实现方案：根据参数:任务参与学生id,在"任务参与学生cmstudent"表中查找对应的cmstudent实例，并返回。
	 * 
	 * @param id任务参与学生id
	 * @return Cmstudent返回的任务分组学生实例
	 */
	public Cmstudent getCmstudentById(Integer id);

	/**
	 * 业务名称：根据任务参与学生id获取CmstudentDetailView实例
	 * 
	 * 业务描述：根据任务参与学生id,获取该任务参与学生所对应的任务参与学生具体信息CmstudentDetailView，并返回
	 * 
	 * 实现方案：根据参数任务参与学生id:cmstudentid，查找该cmstudentid所对应的cmstudentDetailView，并返回
	 * 
	 * @param id任务参与学生id
	 * @return CmstudentDetailView返回的CmstudentDetailView实例
	 */
	public CmstudentDetailView getCmstudentDetailViewByIdAndCmissionId(Integer id);

	/**
	 * 业务名称：根据任务分组id:cmgroupid获得该分组的Cmstudent的list
	 * 
	 * 
	 * 
	 * @param cmgroupid
	 * @return
	 */
	public List<Cmstudent> getCmstudentListByCmgroupid(Integer cmgroupid);

	/**
	 * 业务名称：根据选课学生id:cstudentid 和 课程任务id:cmissionid，获取参与该任务的相应cmstudent对象
	 * 
	 * 业务描述： 用以获取登录用户在该任务中的cmstudent对象,
	 * 在用户登录后，通过这个业务获取该学生用户在某cmissionid课程任务下的cmstudent实例。
	 * 
	 * 实现方案：根据参数:cstudentid和cmissionid，在"任务参与学生cmstudent"表中，
	 * 查询cstudentid字段值以及cmissionid字段值均与参数相等的cmstudent记录，并返回。
	 * 由于一个选课学生cstudente在一个课程任务cmission下只有一个实例cmstudent,正常情况下课保证查询结果唯一。
	 * 
	 * @param cstudentid选课学生id
	 * @param cmissionid课程任务id
	 * @return Cmstudent该学生用户所对应的cmstudent实例
	 */
	public Cmstudent getCmstudentByCondition(Integer cstudentid,
			Integer cmissionid) throws AccessDeniedException;
	
	/**
	 * 根据cmissionid  选出参加该课的所有选课学生
	 */
	public List<Cmstudent> getCmstudentListByCmissionid(Integer cmissionid);
	
	
	/**
	 * 添加一个参与任务的学生
	 */
	public void addCmstudent(Cmstudent cmstudent);
	
	/**
	 * 更新课程任务学生信息
	 * author：zhuchengrong
	 */
	public void updateCmstudent(Cmstudent cmstudent);
	
	/**
	 * 选出参与个人任务的学生
	 * @param cmissionid
	 * @param isinvolved
	 */
	public List<Cmstudent> getCmstudentListByCmissionidAndIsinvolved(Integer cmissionid,Boolean isinvolved);
	
	
	/**
	 * 判断参与课程任务的学生是否包含当前学生
	 */

	public Boolean isStudentAuthorizedOnCmission(Integer cmissionid);
}