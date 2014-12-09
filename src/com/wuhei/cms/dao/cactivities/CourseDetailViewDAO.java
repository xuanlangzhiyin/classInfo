package com.wuhei.cms.dao.cactivities;

import java.util.List;

import com.wuhei.cms.model.joint.CourseDetailView;


public interface CourseDetailViewDAO {

	/**
	 * �鿴����γ���ϸ��Ϣ
	 * 
	 * @param courseid
	 * @return CourseDetailView
	 */
	public CourseDetailView getCourseDetailView(Integer courseid);

	/**
	 * ���רҵ��id:majorId��ѯ��רҵ�µ�����CourseDetailView�б�
	 * @param majorIdרҵid
	 * @return List<CourseDetailView>���ص�CourseDetailView�б�
	 */
	public List<CourseDetailView> listCourseDetailViewByMajorId(Integer majorid);
}
