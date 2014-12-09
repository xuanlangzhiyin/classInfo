package com.wuhei.cms.dao.basic;

import com.wuhei.cms.model.Student;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * �б����
 */
public interface StudentDAO {
	/**
	 * ����ѧ��
	 * 
	 * @param student����ѧ����Ϣʵ��
	 */
	public void insertStudent(Student student);

	/**
	 * ����ѧ��
	 * 
	 * @param student����ѧ����Ϣ��ʵ��
	 */
	public void updateStudent(Student student);

	/**
	 * ɾ��ѧ��
	 * 
	 * @param idҪɾ���ѧ���id
	 */
	public void deleteStudent(Integer id);

	/**
	 * ��ȡѧ��
	 * 
	 * @param idҪ��ѯ��ѧ���id
	 * @return ���ص�ѧ��ʵ��
	 */
	public Student getStudent(Integer id);

	/**
	 * �г�����ѧ�������Ϣ
	 * 
	 * @return ��������ѧ���б�
	 */
	public List<Student> getStudentList();

	/**
	 * ����ѧ��ɸѡ���г����������ѧ�������Ϣ��ɸѡ��������רҵId���꼶���༶������ؼ���
	 * 
	 * @param majorIdרҵid
	 * @param ѧ�� code
	 * @param grade�꼶
	 * @param stuclass�༶
	 * @param keyword����ؼ���
	 * @return
	 */
	public List<Student> getStudentListByCondition(
			@Param(value = "majorId") Integer majorId,
			@Param(value = "code") String code,
			@Param(value = "grade") String grade,
			@Param(value = "stuclass") String stuclass,
			@Param(value = "keyword") String keyword);
	
	/**
	 * ���ܣ�����������ؼ�¼����
	 * 
	 * ���ڷ�ҳʱ��ͳ�Ƽ�¼����ͷ�ҳ����Ŀ
	 * 
	 * @param keyword ����ؼ��� Ϊnullʱ���Ը�����
	 * 
	 * @return ��ؼ�¼����Ŀ
	 */
	public Integer countStudentByCondition(
			@Param(value="grade") String grade,
			@Param(value="stuclass") String stuclass,
			@Param(value="keyword") String keyword);
	
	/**
	 * ���ܣ��������������ص�Student��¼
	 * @param grade
	 * @param stuclass
	 * @param keyword
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Student> listStudentByCondition(
			@Param(value="grade") String grade,
			@Param(value="stuclass") String stuclass,
			@Param(value="keyword") String keyword,
			@Param(value="start") Integer start,
			@Param(value="count") Integer count);

}