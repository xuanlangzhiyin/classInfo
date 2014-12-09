package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.exception.AccessDeniedException;
import com.wuhei.cms.exception.FileProcessException;
import com.wuhei.cms.model.Cmgroup;

import com.wuhei.cms.model.Cmstudent;
import com.wuhei.cms.model.joint.CmgroupDetailView;
import com.wuhei.cms.model.joint.CmstudentDetailView;

/**
 * 提供给"任务分组"相关业务调用的service层
 * 
 * 
 */
public interface CmgroupService {

	/**
	 * 业务名称：显示可参选某课程任务的所有任务分组列表
	 * 
	 * 业务描述：在分配任务的时候，在所有课程的分组列表中选择参与任务的小组，也可以对小组进行调整。
	 * 课程分组与任务对应上之后，正式确立任务分组，确立该课程任务的任务分组后，需要将改课程任务对应的任务分组列表显示出来
	 * 
	 * 实现方案：根据已选定的分课程任务id：cmissionid,在“任务分组cmgroup”表中查询可参选该课程任务的任务分组列表，并返回任务分组列表
	 * 
	 * @param cmissionid课程任务id
	 * @return List<Cmgroup>显示的任务分组列表
	 */
	public List<Cmgroup> getCmgrouplist(Integer cmissionid);

	/**
	 * 业务名称：新增任务分组
	 * 
	 * 业务描述：点击“新增分组”，老师输入分组名字（默认为分组+数据库中的行号）。
	 * 
	 * 实现方案：往“任务分组cgroup”表中插入任务分组实例cmgroup。另外，在"任务参与学生cmstudent"表中，更新被选中的学生的信息，
	 * 将isgrouped字段置为true，将cmgroupid置为参数任务分组cmgroup的id。
	 * 
	 * @param cmgroup需要插入的任务分组实例
	 * @param cmstudentidList需要添加到该任务分组的学生的id列表
	 */
	public void newCmgroup(Cmgroup cmgroup, List<Integer> cmstudentidList);

	
	/**
	 * 将一个学生独自分成一个小组  by zhuchengrong
	 * @param cmgroup
	 * @param cmstudentid
	 */
	public void newCmgroupByOne(Cmgroup cmgroup, Integer cmstudentid);

	
	/**
	 * 业务名称：查看任务分组时列出所有成员信息
	 * 
	 * 业务描述：点击“分组列表”中相应的分组，可以查看该分组的基本信息和所有成员具体信息，本函数返回该分组所有成员具体信息。
	 * 
	 * 实现方案：根据任务分组id：cmgroupid,在"任务参与学生cmstudent"表中查询该分组的所有学生，
	 * 返回所需查看任务分组的所有小组成员具体信息列表
	 * 
	 * @param cmgroupid所需要查看的任务分组id
	 * @return List<CmstudentDetailView>所需查看任务分组的所有小组成员具体信息列表
	 */
	public List<CmstudentDetailView> getCmstudentDetailViewListByCmgroupid(
			Integer cmgroupid);

	/**
	 * 业务名称：查看任务分组时列出任务分组具体信息
	 * 
	 * 业务描述：点击“分组列表”中相应的分组，可以查看该分组的基本信息和所有成员具体信息，本函数返回该分组基本信息。
	 * 
	 * 实现方案：根据任务分组id:cmgroupid，从“任务分组cmgroup”表中查询该分组的信息。从“任务参与学生”中查询该分组中的学生。
	 * 
	 * @param cmgroupid需要查看的任务分组的id
	 * @return CmgroupDetailView该任务分组的具体信息
	 */
	public CmgroupDetailView getCmgroupDetailView(Integer cmgroupid);

	/**
	 * 业务名称：获取未分组的任务分组学生列表
	 * 
	 * 业务描述：往被选择的分组中添加成员。添加的成员从“未分组”中挑选。
	 * 
	 * 实现方案：根据Cmissionid在“任务参与学生cmstudent”表中， 查询所有未分组的学生列表。
	 * 
	 * @param cmissionid课程任务id
	 * @return List<Cmstudent>返回未分组的任务参与学生列表
	 */
	public List<Cmstudent> getUngroupedStudent(Integer cmissionid);

	/**
	 * 业务名称：添加任课程任务的参与学生
	 * 
	 * 业务描述：往被选择的分组中添加成员。添加的成员从“未分组”中挑选
	 * 
	 * 实现方案：选择要添加的学生，修改“任务参与学生”中，要进入分组的学生的cmgroupid。
	 * 
	 * @param cmstudentid需要添加的任务参与学生id
	 * @param cmgroupid任务分组id
	 * @throws FileProcessException
	 */
	public void addCmissionMember(List<Cmstudent> cmstudentList)
			throws AccessDeniedException;

	/**
	 * 业务名称：删除任务分组小组成员
	 * 
	 * 业务描述：往被选择选择的分组中删除成员。删除的成员进入“未分组”状态。
	 * 
	 * 实现方案：在“任务参与学生cmstudent”表中，要踢出分组的学生cmstudent的isgrouped字段置为false，
	 * cmgroupid字段置为null，isinvolved字段置为false。
	 * 在"任务分组cmgroup"表中，更新该cmstudent所在任务分组的count字段为count-1。 任务分组中，如果学生是组长，不能删。
	 * 
	 * @param cmstudentid需要被踢出任务分组的学生id
	 */
	public void deleteCmgroupMember(Integer cmstudentid)
			throws AccessDeniedException;

	/**
	 * 业务名称：修改任务分组
	 * 
	 * 业务描述：对于选中的任务分组，修改任务分组信息。
	 * 
	 * 实现方案：根据参数：cmgroup,在"任务分组cmgroup"表中，修改相应的任务分组记录
	 * 
	 * @param cmgroup所需修改的任务分组
	 */
	public void modifyCmgroup(Cmgroup cmgroup)
			throws AccessDeniedException;

	/**
	 * 业务名称：删除任务分组
	 * 
	 * 业务描述：选择“分组列表”中的分组，点击删除，清除和该分组相关的信息，该分组中的学生自动进入“未分组”状态
	 * 
	 * 实现方案：根据需要被删除的任务分组id,将“任务参与学生cmstudent”表中的isgrouped字段值置为false,
	 * cmgroupid字段值置为null,isinvolved字段值置为fasle。
	 * 另外，根据参数id，删除“任务分组cgroup”表中的对应任务分组记录。
	 * 
	 * @param id需要删除的任务分组id
	 */
	public void deleteCmgroup(Integer id) throws AccessDeniedException;

	/**
	 * 业务名称：确定参与课程任务的任务分组
	 * 
	 * 业务描述：确定分组与任务的映射关系,修改参与或者不参与任务,选择要参与任务的分组，选择完毕并且确认后，在“任务分组”中，将不参与任务 的分组
	 * 设标志位。
	 * 
	 * 实现方案：根据参数任务分组列表cmgroupList，对于列表中的每个任务分组cmgroup: {设置"任务参与学生"中的isinvolved
	 * 
	 * @param Cmgroup
	 *            [] cmgroupArray
	 */
	public void confirmCmgroup(List<Cmgroup> cmgroupList);

	/**
	 * 根据cmgroupid返回cmgroup对象
	 * 
	 * @param cmgroupid
	 *            cmgroup.id
	 * @return
	 */
	public Cmgroup getCmgroupById(Integer cmgroupid);

	/**
	 * 业务名称：查看任务分组
	 * 
	 * 业务描述：学生查看自己选择的任务的分组情况
	 * 
	 * 实现方案：在“任务参与学生”表中，查询和该学生分组ID相同的学生。在"任务分组"中，select 该分组的信息
	 * 
	 * @param cmgroupid
	 *            所需查看的任务分组id
	 * @return List<Cmstudent> 该任务的任务参与学生列表
	 */
	public List<Cmstudent> getCmgroup(Integer cmgroupid);

	/**
	 * 业务名称：查看指定任务下未参与课程任务的小组
	 * 
	 * 业务描述：教师在建立课程任务的“小组任务”时，有一些任务分组被选中参加，而另一些任务分组未参加，本业务列出那些未参加的任务分组列表
	 * 
	 * 实现方案：根据参数课程任务id:cmissionid，在"任务分组cmgroup"表中，查找该cmissionid对应下的任务分组列表，
	 * 并且条件是isinvolved = false。
	 * 
	 * @param cmissionid所需要查看的任务id
	 * @return List<CmgroupDetailView>所需查看任务的所有未参与任务小组具体信息列表
	 */
	public List<CmgroupDetailView> getNotinMCmgroupDetailViewListByCmissionid(
			Integer cmissionid);

	/**
	 * 业务名称：查看指定任务下参与课程任务的小组
	 * 
	 * 业务描述：教师在建立课程任务的“小组任务”时，有一些任务分组被选中参加，而另一些任务分组未参加，本业务列出那些被选中参加的任务分组列表
	 * 
	 * 实现方案：根据参数课程任务id:cmissionid，在"任务分组cmgroup"表中，查找该cmissionid对应下的任务分组列表，
	 * 并且条件是isinvolved = true。
	 * 
	 * @param cmissionid所需要查看的任务id
	 * @return List<CmgroupDetailView>所需查看任务的所有参与任务小组具体信息列表
	 */
	public List<CmgroupDetailView> getinMCmgroupDetailViewListByCmissionid(
			Integer cmissionid);

	/**
	 * by zhuchengrong
	 * @param cmissionid
	 * @return 任务分组列表
	 */
	public List<CmgroupDetailView> getCmgroupDetailViewListByCmissionid(
			Integer cmissionid);
	
	/**
	 * by zhuchengrong
	 * @param cmissionid
	 * @return 任务分组列表
	 */
	public List<CmgroupDetailView> getCmgroupDetailViewListByCmissionidIsmarked(
			Integer cmissionid);
	
	/**
	 * 业务名称：指定任务小组参与课程任务
	 * 
	 * 业务描述：教师在建立课程任务的“小组任务”时，有一些任务分组被选中参加该小组任务，本业务实现将这些被选中的任务分组添加到课程任务。
	 * 
	 * 实现方案：根据参数任务分组id列表：cmgroupids,对于每个任务分组id:cmgroupid:
	 * {在"任务分组cmgroup"表中查询该任务分组，将该分组的isinvolved字段置为true。
	 * 另外，也需要在"任务参与学生cmstudent"表中，根据cmgroupid外键字段
	 * ，查找在该任务分组的学生列表，把列表中的每个学生的isinvolved字段置为true。}
	 * 
	 * @param cmgroupids任务分组id列表
	 */
	public void addCmgrouptoMission(List<Integer> cmgroupids)
			throws AccessDeniedException;

	/**
	 * 业务名称：删除任务小组参与课程任务
	 * 
	 * 业务描述：教师在建立课程任务的“小组任务”时，有一些任务分组被选中参加该小组任务。
	 * 若教师需要将某些小组取消参与该任务，则需要将已经参与课程任务的任务分组变为未参与的任务分组。
	 * 本业务实现将这些需要被取消参与的任务分组变为未参与该课程任务的状态。
	 * 
	 * 实现方案：根据参数任务分组id列表：cmgroupids,对于每个任务分组id:cmgroupid:
	 * {在"任务分组cmgroup"表中查询该任务分组，将该分组的isinvolved字段置为false。
	 * 另外，也需要在"任务参与学生cmstudent"表中，根据cmgroupid外键字段
	 * ，查找在该任务分组的学生列表，把列表中的每个学生的isinvolved字段置为false。}
	 * 
	 * @param cmgroupids任务分组id列表
	 */
	public void removeCmgroupfromMission(List<Integer> cmgroupids)
			throws AccessDeniedException;

	/**
	 * 业务名称：返回为分组学生列表
	 * 
	 * 业务描述：根据cmissioid返回未分组的任务参与学生DetailView列表。
	 * 
	 * 实现方案：根据参数cmissionid，在"任务参与学生cmstudent"表中，查询cmissionid字段与参数相等，
	 * 且isgrouped字段为false的学生列表，获取其DetailView列表并返回。
	 * 
	 * @param cmission课程任务id
	 * 
	 * @return List<CmstudentDetailView>返回的CmstudentDetailView列表
	 */
	public List<CmstudentDetailView> getUngroupedCmstudentDetailViewListByCmissionid(
			Integer cmissionid);

	/**
	 * 业务名称：添加学生到cmgroup
	 * 
	 * 业务描述：教师可以选择一个cmgroup，然后往cmgroup中添加一些任务参与学生。
	 * 
	 * 实现方案：根据参数cmgroupid，在"任务分组cmgroup"表中查询对应的cmgroup，
	 * 如果该cmgroup的iseditable字段的值为false，则抛出异常，
	 * 如果iseditable=true,则对于参数cmstudentids中的每个学生：
	 * {在"任务参与学生cmstudent"表中,将isgrouped字段置为true,将isinvolved字段置为true，
	 * 将cmgroupid置为参数cmgroupid,将cmissionid字段置为cmgroup中的cmissionid}
	 * 
	 * @param cmstudentids需要被添加的cmstudentid列表
	 * @param cmgroupid需要添加学生的cmgroup
	 */
	public void addCmgroupMember(List<Integer> cmstudentids, Integer cmgroupid);

	/**
	 * 业务名称：从cmgrou移除学生
	 * 
	 * 业务描述：教师可以选择一个cmgroup，然后移除该cmgroup中的部分cmstudent。
	 * 
	 * 实现方案：根据参数cmgroupid，在"任务分组cmgroup"表中查询对应的cmgroup，
	 * 如果该cmgroup的iseditable字段的值为false，则抛出异常，
	 * 如果iseditable=true,则判断要删除的组员中是否有组长，如果有组长，则抛出组长不能被删除的异常。
	 * 如果不包含组长，则对于参数cmstudentids中的每个学生：
	 * {在"任务参与学生cmstudent"表中,将isgrouped字段置为false,将isinvolved字段置为false，
	 * 将cmgroupid置为null。}
	 * 
	 * @param cmstudentids需要被移除的cmstudentid列表
	 * @param cmgroupid需要移除学生的cmgroup
	 */
	public void deleteCmgroupMembers(List<Integer> cmstudentids,
			Integer cmgroupid);

	/**
	 * 业务名称：删除分组  by zhuchengrong
	 * 
	 * @param cmgroupids
	 */
	public void deleteCmgroups(List<Integer> cmgroupids);
	
	/**
	 * 业务名称：添加参与任务小组
	 * 
	 * @param cmgroupids
	 */
	public void addCmgroup (Cmgroup cmgroup);
}