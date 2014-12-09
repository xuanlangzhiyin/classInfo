package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Cgroup;


public interface CgroupDAO {

	/**
	 * 插入一条课程分组
	 * @param cgroup 课程分组实例
	 */
    public void insertCgroup(Cgroup cgroup);
	
    /**
     * 更新一条课程分组
     * @param cgroup 课程分组实例
     */
	public void updateCgroup(Cgroup cgroup);
	
	/**
	 * 删除一条课程分组
	 * @param Cgroupid 课程分组id
	 */
	public void deleteCgroup(Integer id);
	
	/**
	 * 通过id获取课程分组
	 * @param id课程分组id
	 * @return Cgroup课程分组
	 */
	public Cgroup getCgroup(Integer id);
	
	/**
	 * 获取所有课程分组表
	 * @return 课程分组list
	 */
	public List<Cgroup> getCgroupList();	
	
	/**
	 * 返回指定courseid的课程分组列表
	 * @param courseid
	 * @return 课程分组list
	 */
	public List<Cgroup> getCgroupListByCondition(Integer courseid);
	
	/**
	 * 返回指定id的课程分组组长id
	 * @param id
	 * @return 组长id
	 */
	public int getCgroupLeader(Integer id);
	
	/**
	 * 修改课程分组人数
	 * @param id 要修改的分组id
	 * @param count 要修改的组员数
	 */
	public void updateCgroupCount(
			@Param(value = "id") Integer id ,
			@Param(value = "count") Integer count);
	
	/**
	 * 修改课程分组组长
	 * @param id 要修改组长的课程分组id
	 * @param studentid  组长id
	 */
	public void updateCgroupLeader(
			@Param(value = "id") Integer id ,
			@Param(value = "studentid") Integer studentid);
	
	/**
	 * 修改课程分组名称
	 * @param id 要修改组长的课程分组id
	 * @param name 分组名称
	 */
	public void updateCgroupName(
			@Param(value = "id") Integer id,
			@Param(value = "name") String name);

	


}