package com.wuhei.cms.service.basic;

import java.util.List;

import com.wuhei.cms.model.Teacher;

import com.wuhei.cms.model.deletedata.TeacherDeleteResult;
import com.wuhei.cms.model.joint.TeacherDetailView;
import com.wuhei.cms.search.result.TeacherPageResult;

public interface TeacherService {

	/**
	 * 教师列表
	 * 进行教师操作，返回所有教师最基本信息
	 * @return 返回的教师信息列表
	 */
	public List<Teacher> getTeacherList();
	
	/**
	 * 
	 * @param id
	 * @return 老师  by zhuchengrong
	 */
	public Teacher getTeacherById(Integer id);
	
	/**
	 * 获取非主要开课老师
	 * @param courseid
	 * @return
	 */
	public List<Teacher> getTeacherListByCourseId(Integer courseid);
	
	/**
	 * 条件搜索教师列表
	 * 进行教师筛选，列出符合条件的教师最基本信息，筛选条件包括学院Id、姓名关键字
	 * @param departmentId 院系id 用来筛选所属教师
	 * @param keyword  教师姓名关键字
	 * @return 返回符合条件的教师信息列表
	 */
	public TeacherPageResult getTeacherListByConditions(
			Integer departmentId, String keyword,Integer currentPage);
	
	/**
	 * 新增教师
	 * 进入新增教师页面，填写教师信息，点击保存。
	 * @param teacher 要插入数据库的教师类实例
	 * @return
	 */
	public void newTeacher(Teacher teacher);
	
	
	/**
	 * 查看教师详细信息
	 * 点击进入教师详细信息页面
	 * @return 返回教师详细信息类实例
	 */
	public TeacherDetailView getTeacherDetailViewById(Integer id);
	
	/**
	 * 根据条件查看教师详细信息
	 * @param departmentid
	 * @param majorid
	 * @return
	 */
	public List<TeacherDetailView> getTeacherDetailViewByCondition(Integer departmentid,Integer majorid);
	
	/**
	 * 
	 * @param departmentid
	 * @return 根据departmentid来查找教师列表
	 */
	
	public List<Teacher> getTeacherListByDepartmentid(Integer departmentid);
	
	/**
	 * 删除教师
	 * 对某一条教师数据进行删除
	 * @param id 要删除的教师id
	 */
	public void deleteTeacher(Integer id);
	
	/**
	 * 修改教师信息
	 * 在修改页面完成修改后，点击保存修改
	 * @param teacher 教师类实例，传入要修改的信息
	 * @return
	 */
	
	/**
	 * 批量删除教师
	 * 
	 * @param 前台传入需要批量删除的教师的id列表
	 * @return
	 */
	public TeacherDeleteResult deleteTeachers(List<Integer> teachersId);
	

	public void updateTeacher(Teacher teacher);
	

	/**
	 * 获取主开课老师
	 */
    public Teacher getMainCteacher(Integer courseid);
}
