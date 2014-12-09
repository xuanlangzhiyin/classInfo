package com.wuhei.cms.service.basic;

import java.util.List;


import com.wuhei.cms.model.Student;
import com.wuhei.cms.model.joint.StudentDetailView;
import com.wuhei.cms.model.deletedata.StudentDeleteResult;
import com.wuhei.cms.search.result.StudentPageResult;

/**
 * ��StudentService���ҵ����õ�service��
 * 
 */
public interface StudentService {
	/**
	 * ҵ������:ѧ���б�
	 * 
	 * ҵ������:����ѧ���������г�����ѧ���������Ϣ
	 * 
	 * @return List<Student>
	 */
	public List<Student> getStudentList();

	/**
	 * ҵ������:��������ѧ���б�
	 * 
	 * ҵ������:����ѧ��ɸѡ���г�����������ѧ���������Ϣ��ɸѡ��������רҵId���꼶���༶�������ؼ���
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
	 * ҵ������:����ѧ��
	 * 
	 * ҵ������:��������ѧ��ҳ�棬��дѧ����Ϣ��������档
	 * 
	 * @param student
	 * @return
	 */
	public void newStudent(Student student);
	

	/**
	 * ҵ������:�鿴ѧ����ϸ��Ϣ
	 * 
	 * ҵ������:�������ѧ����ϸ��Ϣҳ��
	 * 
	 * @param id
	 * @return
	 */
	public StudentDetailView getStudentDetailViewById(Integer id);

	/**
	 * ҵ������:ɾ��ѧ��
	 * 
	 * ҵ������:��ĳһ��ѧ�����ݽ���ɾ��
	 * 
	 * @param id
	 * @return
	 */
	public void deleteStudent(Integer id);

	/**
	 * ҵ������:����ɾ��
	 * 
	 * ҵ������:ɾ������ѧ����Ϣ
	 * 
	 * @return
	 */
	public void deleteStudents(Integer[] idList);

	/**
	 * ҵ������:�޸�ѧ����Ϣ
	 * 
	 * ҵ������:���޸�ҳ������޸ĺ󣬵�������޸�
	 * 
	 * @param student
	 * @return
	 */
	public void updateStudent(Student student);
	
	/**
	 * ���ܣ�������������ѧ���ķ�ҳ�ṹ
	 * @param currentPage
	 * @param keyword
	 * @param grade
	 * @param stuclass
	 * @return
	 */
	public StudentPageResult listStudentByConditions(Integer currentPage,Integer majorid, String keyword,String grade,String stuclass);
	
	
	/**
	 * ����ɾ��ѧ��
	 * 
	 * author:GongXiang
	 * @param studentsid
	 * @return
	 */
	public StudentDeleteResult deleteStudents(List<Integer> studentsId);


}
