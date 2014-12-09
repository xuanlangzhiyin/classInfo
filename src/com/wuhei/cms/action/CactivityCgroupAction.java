package com.wuhei.cms.action;

import java.util.List;
import com.wuhei.cms.model.Cgroup;
import com.wuhei.cms.model.Cstudent;
import com.wuhei.cms.model.joint.CgroupDetailView;
import com.wuhei.cms.model.joint.CstudentDetailView;
import com.wuhei.cms.service.cactivities.CgroupService;
import com.wuhei.cms.service.cactivities.CstudentService;


/**
 * 课程分组管理
 * 
 * 
 */
@SuppressWarnings("serial")
public class CactivityCgroupAction extends CactivityCourseAction {

	/**
	 * 课程 小组内学生list
	 */
	private List<CstudentDetailView> cstudentDetailViews;

	/**
	 * 未分组学生list
	 */
	private List<CstudentDetailView> ungroupedCstudentDetailViews;

	/**
	 * 课程小组id
	 */
	private Integer cgroupid;

	/**
	 * 需要批量删除的课程小组id列表
	 */
	private List<Integer> cgroupids;

	/**
	 * 课程小组
	 */
	private Cgroup cgroup;

	/**
	 * 课程分组信息
	 */
	private List<CgroupDetailView> cgroupDetailViews;

	/**
	 * 课程分组信息
	 */
	private CgroupDetailView cgroupDetailView;

	/**
	 * 新建的课程分组信息(用于newgroup返回)
	 */
	private CgroupDetailView addedCgroupDetailView;

	/**
	 * 新建分组名称
	 */
	private String cgroupName;

	/**
	 * 课程学生id list
	 */
	private List<Integer> cstudentids;

	/**
	 * 课程学生的id
	 */
	private Integer cstudentid;

	/**
	 * 课程组长id
	 */
	private Integer leaderid;
	/**
	 * 每个Ajax请求，都需要返回的参数 后台操作的结果是否成功。
	 * 
	 * private String ajaxResult;
	 */
	private CgroupService cgroupService;

	private CstudentService cstudentService;

	/**
	 * 列出所有未分组学生
	 * 
	 * 前端传来 courseid |cstudent.courseid
	 * 
	 * 根据cstudent.isgrouped判断学生是否已经有分组
	 * 
	 * 返回未分组的学生 List<cStudentDetialView>
	 * 
	 * @return
	 */
	public String listUngroupedStudents() {

		// ActionContext.getContext().getValueStack().findValue("courseid");

		// 这里应该返回一个List<CstudentDetailView> 等待service改正确后完成。
		try {
			ungroupedCstudentDetailViews = cgroupService
					.getUngroupedCstudentDetailViewListByCourseid(courseid);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 列出所有组的基本信息（不展开分组，不显示组内成员的详细信息）
	 * 
	 * 前端传来 courseid |cgroup.courseid
	 * 
	 * 返回List<cGroupDetialView>
	 * 
	 * 返回的是每个分组的基本信息 ps：需要同时满足json请求
	 */
	public String getGroups() {
		try {
			cgroupDetailViews = cgroupService
					.getCgroupDetailViewListByCourseid(courseid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * 列出某一个分组的所有成员的详细信息
	 * 
	 * 根据cgroupid |cstudent.cgroupid
	 * 
	 * 返回该组的学生List<CstudentDetialView> json
	 * 
	 * @return
	 */
	public String getGroupMemberDetial() {
		
		try {
			cstudentDetailViews = cgroupService
					.getCstudentDetailViewListByCgroupid(cgroupid);
			CgroupDetailView tempDetailView = cgroupService
					.getCgroupDetailViewByCgroupid(cgroupid);
			// TODO cgroupid = tempDetailView.getId();
			leaderid = tempDetailView.getLeaderid();
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * 
	 * 新建一个分组
	 * 
	 * 传入一个新课程分组名|cgroup.name、所属课程id|cgroup.courseid
	 * 
	 * 传入一个学生list|list<>
	 * 
	 * 新建一个课程分组Cgroup， 并插入学生List<cstudentid>
	 * 
	 * 返回一条新建的分组
	 * 
	 * 
	 * @return
	 */
	public String newGroup() {
		try {
			// 将传回来的数据构造成一个cgroup
			cgroup = new Cgroup();
			List<CgroupDetailView> tempCgroupDetailViews=cgroupService.getCgroupDetailViewListByCourseid(courseid);
			for(int i=0;i<tempCgroupDetailViews.size();i++){
				if(cgroupName.equals(tempCgroupDetailViews.get(i).getName()))
				{
				    ajaxResult = ERROR;
					return ERROR;
				}
			}
			// cgroup.setCode(code);
			if(cgroupName.equals("")){
				Cstudent tempCstudent = cstudentService
						.getCstudentById(cstudentids.get(0));
				cgroup.setName(tempCstudent.getName());
			}
			else{
				cgroup.setName(cgroupName);
			}
			
			cgroup.setCourseid(courseid);
			// 小组人数
			cgroup.setCount((short) cstudentids.size());
			// 组长
			cgroup.setLeaderid(cstudentids.get(0));
			// 使用List<Integer> cstudentids 需要修改service接口
			cgroupService.newCgroup(cgroup, cstudentids);
			// 传到前端的addedCgroupDetailView
			addedCgroupDetailView = cgroupService
					.getCgroupDetailViewByCgroupid(cgroup.getId());
		} catch (Exception e) {

			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * 添加学生到某一个分组
	 * 
	 * 传入一个学生list|List<cstudentid>
	 * 
	 * 传入List<cstudentid> cstudentids
	 * 
	 * 将学生添加到组，返回新组cgroupdetialview
	 * 
	 * @return
	 */
	public String addToGroup() {
		try {
			// 使用List<Integer> cstudentids 需要修改service接口
			cgroupService.addCgroupMember(cstudentids, cgroupid);
			// 传到前端的addedCgroupDetailView
			addedCgroupDetailView = cgroupService
					.getCgroupDetailViewByCgroupid(cgroupid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * 将学生设置为未分组
	 * 
	 * 前端传来cstudentid，groupid
	 * 
	 * 将此学生设置成为未分组
	 * 
	 * 返回所有分组
	 * 
	 * @return
	 */
	public String cancelStudentGrouped() {
		try {
			Integer cstudentId=cstudentids.get(0);
			// 使用List<Integer> cstudentids 需要修改service接口
			cgroupService.deleteCgroupMember(cstudentId, cgroupid);
			ungroupedCstudentDetailViews = cgroupService
					.getUngroupedCstudentDetailViewListByCourseid(courseid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * 删除一个组
	 * 
	 * 前端传来groupid， courseid
	 * 
	 * 小组中的学生都成为未分组，
	 * 
	 * 删除这个group，
	 * 
	 * 返回list<UngroupedStudent>
	 * 
	 * @return
	 */
	public String cancelcGroupGrouped() {
		try {
			cgroupService.deleteCgroup(cgroupid);
			ungroupedCstudentDetailViews = cgroupService
					.getUngroupedCstudentDetailViewListByCourseid(courseid);
	
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * 批量删除多个组 前端传来groupid，
	 * 
	 * courseid 小组中的学生都成为未分组，
	 * 
	 * 删除这个group，
	 * 
	 * 返回list<UngroupedStudent>
	 * 
	 * @return
	 */
	public String cancelcGroupsGrouped() {
		try {
			cgroupService.deleteCgroups(cgroupids);
			ungroupedCstudentDetailViews = cgroupService
					.getUngroupedCstudentDetailViewListByCourseid(courseid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	/**
	 * 设置新的组长
	 * 
	 * 前端传回需要修改的组id：cgroupid、组长id：cstudentid
	 * 
	 * 设置新的组长
	 * 
	 * @return
	 */
	public String setGroupLeader() {
		try {
			cgroupService.modifyCgroupLeader(cgroupid, cstudentid);
			cgroupDetailView = cgroupService
					.getCgroupDetailViewByCgroupid(cgroupid);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	
	/**
	 * 
	 * 对未分组的学生进行分组，每个学生一个小组
	 * 
	 * 所属课程id|cgroup.courseid
	 * 
	 * 传入一个学生idlist|list<Integer>
	 * 
	 * 新建一个课程分组Cgroup
	 * 
	 * 
	 * @return
	 */
	public String setUngroupedStudents2newGroup() {
		try {

			for (int i = 0; i < cstudentids.size(); i++) {

				Cstudent tempCstudent = cstudentService
						.getCstudentById(cstudentids.get(i));
				// 将传回来的数据构造成一个cgroup
				cgroup = new Cgroup();
				// 学生名字做组名
				cgroup.setName(tempCstudent.getName());

				cgroup.setCourseid(courseid);
				// 小组人数
				cgroup.setCount((short) 1);
				// 组长
				cgroup.setLeaderid(cstudentids.get(i));

				cgroupService.newCgroupByOne(cgroup, cstudentids.get(i));
			}
		} catch (Exception e) {

			e.printStackTrace();
			ajaxResult = ERROR;
			return ERROR;
		}
		cgroupDetailViews = cgroupService
				.getCgroupDetailViewListByCourseid(courseid);
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	// getter and setter

	public List<CstudentDetailView> getCstudentDetailViews() {
		return cstudentDetailViews;
	}

	public void setCstudentDetailViews(
			List<CstudentDetailView> cstudentDetailViews) {
		this.cstudentDetailViews = cstudentDetailViews;
	}

	public List<CstudentDetailView> getUngroupedCstudentDetailViews() {
		return ungroupedCstudentDetailViews;
	}

	public void setUngroupedCstudentDetailViews(
			List<CstudentDetailView> ungroupedCstudentDetailViews) {
		this.ungroupedCstudentDetailViews = ungroupedCstudentDetailViews;
	}

	public Integer getCgroupid() {
		return cgroupid;
	}

	public void setCgroupid(Integer cgroupid) {
		this.cgroupid = cgroupid;
	}

	public List<Integer> getCgroupids() {
		return cgroupids;
	}

	public void setCgroupids(List<Integer> cgroupids) {
		this.cgroupids = cgroupids;
	}

	public Cgroup getCgroup() {
		return cgroup;
	}

	public void setCgroup(Cgroup cgroup) {
		this.cgroup = cgroup;
	}

	public List<CgroupDetailView> getCgroupDetailViews() {
		return cgroupDetailViews;
	}

	public void setCgroupDetailViews(List<CgroupDetailView> cgroupDetailViews) {
		this.cgroupDetailViews = cgroupDetailViews;
	}

	public CgroupDetailView getCgroupDetailView() {
		return cgroupDetailView;
	}

	public void setCgroupDetailView(CgroupDetailView cgroupDetailView) {
		this.cgroupDetailView = cgroupDetailView;
	}

	public CgroupDetailView getAddedCgroupDetailView() {
		return addedCgroupDetailView;
	}

	public void setAddedCgroupDetailView(CgroupDetailView addedCgroupDetailView) {
		this.addedCgroupDetailView = addedCgroupDetailView;
	}

	public String getCgroupName() {
		return cgroupName;
	}

	public void setCgroupName(String cgroupName) {
		this.cgroupName = cgroupName;
	}

	public List<Integer> getCstudentids() {
		return cstudentids;
	}

	public void setCstudentids(List<Integer> cstudentids) {
		this.cstudentids = cstudentids;
	}

	public Integer getCstudentid() {
		return cstudentid;
	}

	public void setCstudentid(Integer cstudentid) {
		this.cstudentid = cstudentid;
	}

	public Integer getLeaderid() {
		return leaderid;
	}

	public void setLeaderid(Integer leaderid) {
		this.leaderid = leaderid;
	}

	public CgroupService getCgroupService() {
		return cgroupService;
	}

	public void setCgroupService(CgroupService cgroupService) {
		this.cgroupService = cgroupService;
	}

	public CstudentService getCstudentService() {
		return cstudentService;
	}

	public void setCstudentService(CstudentService cstudentService) {
		this.cstudentService = cstudentService;
	}

}
