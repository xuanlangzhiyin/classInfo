package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.model.Cstudent;

import com.wuhei.cms.model.deletedata.CstudentDeleteResult;
import com.wuhei.cms.model.joint.CstudentDetailView;
import com.wuhei.cms.model.joint.CstudentListView;

/**
 * 提供对Cstudent部分业务操作调用的service层
 * 
 */
public interface CstudentService {

	/**
	 * 业务名称：根跟课程分组学生id获取课程分组学生
	 * 
	 * @param id课程分组学生id
	 * @return Cstudent返回的课程分组学生实例
	 */
	public Cstudent getCstudentById(Integer id);

	/**
	 * 业务名称：根据课程分组学生id获取课程分组学生具体信息CstudentDetailView实例
	 * 
	 * @param id课程分组学生id
	 * @return CstudentDetailView返回的CstudentDetailView实例
	 */
	public CstudentDetailView getCstudentDetailViewById(Integer id);
	
	/**
	 * 已选定开设的课程id 从选课学生表中找到选了该课程的学生
	 * @param courseid
	 * @return
	 */
	public List<Cstudent> getCstudentList(int courseid);
	
	/**
	 * 已选定开设的课程id 找到选了该课程的学生详细信息
	 * @param courseid
	 * @return
	 */
	public List<CstudentDetailView> getCstudentDetailViewList(int courseid);
	
	/**
	 * 已选定开设的课程id 找到选了该课程的学生详细信息
	 * @param courseid
	 * @return
	 */
	public List<CstudentListView> getCstudentListView(int courseid);
	
	
	
	
	
	/**
	 * 新增选课学生
	 * @param cstudent
	 */
	public void newCstudent(Cstudent cstudent);
	

	/**
	 * 删除选课学生
	 * @param cstudentid
	 */
	public void deleteCstudent(Integer cstudentid);
	
	/**
	 * 更新选课学生
	 * @param cstudentid
	 */
	public void updateCstudent(Cstudent cstudent);
	
	
	/**
	 * 根据选课学生的id列表批量删除
	 * @return
	 */
	public CstudentDeleteResult deleteCstudentByCondition(List<Integer> sourceCstudentid);
	

}