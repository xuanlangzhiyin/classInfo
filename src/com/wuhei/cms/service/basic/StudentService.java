package com.wuhei.cms.service.basic;

import java.util.List;


import com.wuhei.cms.model.Student;
import com.wuhei.cms.model.joint.StudentDetailView;
import com.wuhei.cms.model.deletedata.StudentDeleteResult;
import com.wuhei.cms.search.result.StudentPageResult;

/**
 * 供StudentService相关业务调用的service层
 * 
 */
public interface StudentService {
	/**
	 * 业务名称:学生列表
	 * 
	 * 业务描述:进行学生操作，列出所有学生最基本信息
	 * 
	 * @return List<Student>
	 */
	public List<Student> getStudentList();

	/**
	 * 业务名称:条件搜索学生列表
	 * 
	 * 业务描述:进行学生筛选，列出符合条件的学生最基本信息，筛选条件包括专业Id、年级、班级、姓名关键字
	 * 
	 * @param majorId
	 * @param grade
	 * @param stuclass
	 * @param keyword
	 * @return
	 */
	public List<Student> getStudentListByCondition(Integer majorId,String code,
			String grade, String stuclass, String keyword);

	/**
	 * 业务名称:新增学生
	 * 
	 * 业务描述:进入新增学生页面，填写学生信息，点击保存。
	 * 
	 * @param student
	 * @return
	 */
	public void newStudent(Student student);
	

	/**
	 * 业务名称:查看学生详细信息
	 * 
	 * 业务描述:点击进入学生详细信息页面
	 * 
	 * @param id
	 * @return
	 */
	public StudentDetailView getStudentDetailViewById(Integer id);

	/**
	 * 业务名称:删除学生
	 * 
	 * 业务描述:对某一条学生数据进行删除
	 * 
	 * @param id
	 * @return
	 */
	public void deleteStudent(Integer id);

	/**
	 * 业务名称:批量删除
	 * 
	 * 业务描述:删除多名学生信息
	 * 
	 * @return
	 */
	public void deleteStudents(Integer[] idList);

	/**
	 * 业务名称:修改学生信息
	 * 
	 * 业务描述:在修改页面完成修改后，点击保存修改
	 * 
	 * @param student
	 * @return
	 */
	public void updateStudent(Student student);
	
	/**
	 * 功能：根据条件返回学生的分页结构
	 * @param currentPage
	 * @param keyword
	 * @param grade
	 * @param stuclass
	 * @return
	 */
	public StudentPageResult listStudentByConditions(Integer currentPage,Integer majorid, String keyword,String grade,String stuclass);
	
	
	/**
	 * 批量删除学生
	 * 
	 * author:GongXiang
	 * @param studentsid
	 * @return
	 */
	public StudentDeleteResult deleteStudents(List<Integer> studentsId);


}
