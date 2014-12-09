package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.model.Cgroup;

import com.wuhei.cms.model.joint.CgroupDetailView;
import com.wuhei.cms.model.joint.CstudentDetailView;

/**
 * 提供给"课程分组"相关业务调用的service层 
 * 
 * 
 */
public interface CgroupService {

	/**
	 * 业务名称：查看课程分组列表
	 * 
	 * 业务描述：点击“课程分组”，课程分组通过列表显示（“分组列表”）。在同一页面可以查看分组，删除分组，保存课程分组。
	 * 
	 * 实现方案：根据参数开设课程id:courseid,从“课程分组cgroup”表中查询该开设课程的所有课程分组列表。列出课程分组列表。
	 * 
	 * @param Courseid所需查看的开设课程id
	 * @return List<CgroupDetailView>该开设课程的所有课程分组detailView列表
	 */
	public List<CgroupDetailView> getCgroupDetailViewListByCourseid(Integer courseid);

	/**
	 * 业务名称：新增课程分组
	 * 
	 * 业务描述：点击“新增分组”，老师输入分组名字（默认为分组+数据库中的行号），添加学生，选择组长。
	 * 
	 * 实现方案：往"课程分组course"中插入课程分组实例。 另外，在"选课学生cstudent"表中，更新被选入该组的学生的信息,
	 * 将isgrouped字段置为true，将cgroupid置为参数课程分组cgroup的id。
	 * 
	 * @param cgroup需要被插入的课程分组实例
	 * @param studentidList需要添加到该分组的学生的id列表
	 */
	public void newCgroup(Cgroup cgroup, List<Integer> cstudentidList);

	/**
	 * 对单个学生进行分组
	 * @param cgroup   by zhuchengrong
	 * @param cstudentid
	 */
	public void newCgroupByOne(Cgroup cgroup,Integer cstudentid);
	
	/**
	 * 业务名称：查看课程分组时列出所有成员信息
	 * 
	 * 业务描述：点击“分组列表”中相应的分组，可以查看该分组的基本信息和所有成员具体信息，本函数返回所有成员具体信息
	 * 
	 * 实现方案：选择分组（cgroupid），根据cgroupid从“选课学生cstudent”表中查询该分组中的学生。
	 * 返回所需查看课程分组的所有小组成员具体信息列表
	 * 
	 * @param cgroupid所需要查看的课程分组id
	 * @return List<CstudentDetailView> 所需查看课程分组的所有小组成员具体信息列表
	 */
	public List<CstudentDetailView> getCstudentDetailViewListByCgroupid(
			Integer cgroupid);

	/**
	 * 业务名称：查看课程分组时列出分组基本信息
	 * 
	 * 业务描述：点击“分组列表”中相应的分组，可以查看该分组的基本信息和所有成员具体信息，本函数返回该分组基本信息。
	 * 
	 * 实现方案：选择分组（cgroupid），根据cgroupid从“课程分组cgroup”表中查询该分组中的基本信息。
	 * 
	 * @param cgroupid课程分组id
	 * @return Cgroup课程分组基本信息
	 */
	public CgroupDetailView getCgroupDetailViewByCgroupid(Integer cgroupid);

	/**
	 * 业务名称：获取未分组的选课学生DetailView列表
	 * 
	 * 业务描述：往被选择的分组中添加成员时，添加的成员从“未分组”中挑选。需查询出未分组的选课学生列表
	 * 
	 * 实现方案：从“选课学生cstudent”中查询isgrouped=0的未分组选课学生,并通过连表查询获取这些学生的具体信息，
	 * 返回未分组的学生的DetailView列表。
	 * 
	 * @param courseid开设课程id
	 * @returns List<StudentDetailView>未分组的学生的DetailView列表
	 */
	public List<CstudentDetailView> getUngroupedCstudentDetailViewListByCourseid(
			Integer courseid);

	/**
	 * 业务名称：添加课程分组小组成员
	 * 
	 * 业务描述：往被选择的课程分组中添加小组成员。添加的成员从“未分组”的选课学生中挑选。
	 * 
	 * 实现方案：根据需要被添加的小组成员id列表cstudentidList,修改“选课学生cstudent”表中被加入课程分组的小组成员信息，
	 * 将isgrouped字段置为true，将cgroupid置为参数课程分组id的值：cgroupid。
	 * 另外，需要根据课程分组的id:cgroupid,更新"课程分组cgroup"表中的课程分组信息，更新学生人数count字段的值。
	 * 如果该"课程分组"的leaderid为null，加进去的学生列表中，第一个默认选为组长，设置为leaderid
	 * 
	 * @param cstudentidList添加进入课程分组的学生id列表
	 * @param cgroupid课程分组id
	 */
	public void addCgroupMember(List<Integer> cstudentidList, Integer cgroupid);

	/**
	 * 业务名称：删除课程分组小组成员
	 * 
	 * 业务描述：往被选择选择的课程分组中删除成员。删除的成员进入“未分组”状态。
	 * 
	 * 实现方案：根据需要被删除的小组成员id列表cstudentidList,修改“选课学生cstudent”表中被删除的小组成员的信息，
	 * 将isgrouped字段置为fasle，将cgroupid置为null。
	 * 另外，需要根据课程分组的id:cgroupid,更新"课程分组cgroup"表中的课程分组信息，更新学生人数count字段的值。
	 * 删除的成员id数组中不能包含组长id，因此剩下的成员不会是0。
	 * 
	 * @param cstudentidList需要从课程分组中删除的学生id列表
	 * @param cgroupid课程分组id
	 */
	public void deleteCgroupMembers(List<Integer> cstudentidList, Integer cgroupid);

	/**
	 * 业务名称：删除课程分组小组成员
	 * 
	 * 业务描述：往被选择选择的课程分组中删除成员。删除的成员进入“未分组”状态。
	 * 
	 * 实现方案：根据需要被删除的小组成员id列表cstudentidList,修改“选课学生cstudent”表中被删除的小组成员的信息，
	 * 将isgrouped字段置为fasle，将cgroupid置为null。
	 * 另外，需要根据课程分组的id:cgroupid,更新"课程分组cgroup"表中的课程分组信息，更新学生人数count字段的值。
	 * 删除的成员id数组中不能包含组长id，因此剩下的成员不会是0。
	 *  
	 * @param cstudentid
	 * @param cgroupid
	 */
	public void deleteCgroupMember(Integer cstudentid, Integer cgroupid);
	
	
	/**
	 * 业务名称：获取选课学生列表
	 * 
	 * 业务描述：列出该开设课程的所有选课学生列表
	 * 
	 * 实现方案：通过开设课程id，在"选课学生cstudent"表中，获取该开设课程的所有选课学生DetailView列表
	 * 
	 * @param courseid开设课程id
	 * @return List<CstudentDetailView>所有选课学生列表
	 */
	public List<CstudentDetailView> getCstudentDetailViewListByCourseid(Integer courseid);

	/**
	 * 业务名称：分组命名
	 * 
	 * 业务描述：对被选择的分组命名
	 * 
	 * 实现方案：根据需要被改名的课程分组id,修改“课程分组cgroup”表中的分组名称name字段。
	 * 
	 * @param id需要被改名的课程分组id
	 * @param name新的课程分组名字
	 */
	public void modifyCgroupName(Integer id, String name);

	/**
	 * 业务名称：设置课程分组的组长
	 * 
	 * 业务描述：选择特定分组的课程分组，设置该课程分组的组长，组长只能从该分组的组员中选择。
	 * 
	 * 实现方案：根据cgroupid，查找课程分组，Update"课程分组cgroup"表中的leaderid字段
	 * 
	 * @param cgroupid 课程分组id
	 * @param leaderid 选择的组长的id
	 */
	public void modifyCgroupLeader(Integer cgroupid, Integer leaderid);

	/**
	 * 业务名称：删除课程分组
	 * 
	 * 业务描述：选择“分组列表”中的分组，点击删除，清除和该分组相关的信息，该分组中的学生自动进入“未分组”状态
	 * 
	 * 实现方案：根据要删除的课程分组（id）,将“选课学生cstudent”表中isgrouped置为false,cgroupid=null。
	 * 另外，在"课程分组cgroup"表中删除该分组。
	 * 
	 * @param id所需要删除的课程分组的id
	 */
	public void deleteCgroup(Integer id);

	/**
	 * 业务名称：批量删除课程分组
	 * 
	 * 业务描述：选择“分组列表”中的多个分组，点击删除，清除和该分组相关的信息，该分组中的学生自动进入“未分组”状态
	 * 
	 * 实现方案：根据每个要删除的课程分组（id）,将“选课学生cstudent”表中isgrouped置为false,cgroupid=null。
	 * 另外，在"课程分组cgroup"表中删除该分组。
	 * 
	 * @param List<Integer> 
	 *        idList所需要删除的课程分组的id
	 */
	public void deleteCgroups(List<Integer> idList);
	
	/**
	 * 业务名称：自动生成单人课程分组
	 * 
	 * 业务描述：将未分组的学生分成一人一组
	 * 
	 * 实现方案：根据开设课程id的值courseid,在"选课学生cstudent"表中查询该开设课程的所有选课学生列表。
	 * 对于该开设课程的所有选课学生列表每个选课学生，
	 * 往“课程分组cgroup”中插入一个新分组，将其组名name设为该学生的名字"name+组",count设为1,
	 * 组长leaderid设为该选课学生id,courseid设为参数courseid
	 * 并修改“选课学生cstudent”表,将该选课学生，isgrouped置为true，cgroupid置为插入新分组时所获取到的自增id。
	 * 
	 * @param courseid需要进行字段分组的开设课程id
	 */
	public void autoGrouping(Integer courseid);

}