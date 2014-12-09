package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Course;


public interface CourseDAO {
	/**
	 * ���뿪��γ̼�¼
	 * 
	 * @param Course
	 */
	public void insertCourse(Course course);

	/**
	 * ���¿���γ̼�¼
	 * 
	 * @param Course
	 */
	public void updateCourse(Course course);

	/**
	 * ɾ����γ̼�¼
	 * 
	 * @param id
	 */
	public void deleteCourse(Integer id);

	/**
	 * ��ȡ����γ̼�¼
	 * 
	 * @param id
	 * @return
	 */
	public Course getCourse(Integer id);

	/**
	 * �õ����п���γ̼�¼��list
	 */
	public List<Course> listCourse();

	/**
	 * �����������course
	 * 
	 * @param teacherid
	 * @param year
	 * @param term
	 * @param code
	 * @param foreignteacherid
	 * @param csettingid
	 * @return
	 */
	public List<Course> getCourseListByCondition(
			@Param(value = "teacherid") Integer teacherid,
			@Param(value = "year") String year,
			@Param(value = "term") String term,
			@Param(value = "code") String code,
			@Param(value = "foreignteacherid") Integer foreignteacherid,
			@Param(value = "csettingid") Integer csettingid);

	/**
	 * ͨ��studentid �ҳ���ѧ����ѡ�Ŀ�
	 * 
	 * @param studentid
	 * @return
	 */
	public List<Course> getCourseListByStudentid(Integer studentid);

	/**
	 * ͨ��csettingid��ȡ����course���еĸ��� 
	 * 
	 * @param csettingid
	 * @return
	 */
	public int countCourseByCsettingid(Integer csettingid);
	
	/**
	 * ͨ��courseid���س�studentid���жϸ�ѧ���Ƿ�Ϊѡ��ѧ��
	 */
	
	public List<Integer> getStudentidsByCourseid(Integer courseid);
	
}
