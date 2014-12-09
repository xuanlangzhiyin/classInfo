package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.StudentListView;

/** ѧ���б���ͼDAO */
public interface StudentListViewDAO {
	/**
	 * ���ܣ������������ؼ�¼����
	 * 
	 * ���ڷ�ҳʱ��ͳ�Ƽ�¼�����ͷ�ҳ����Ŀ
	 * 
	 * @param keyword
	 *            �����ؼ��� Ϊnullʱ���Ը�����
	 * 
	 * @return ��ؼ�¼����Ŀ
	 */
	public Integer countStudentByCondition(
			@Param(value = "majorid") Integer majorid,
			@Param(value = "grade") String grade,
			@Param(value = "stuclass") String stuclass,
			@Param(value = "keyword") String keyword);

	/**
	 * ���ܣ���������������ص�Student��¼
	 * 
	 * @param grade
	 * @param stuclass
	 * @param keyword
	 * @param start
	 * @param count
	 * @return
	 */
	public List<StudentListView> listStudentByCondition(
			@Param(value = "majorid") Integer majorid,
			@Param(value = "grade") String grade,
			@Param(value = "stuclass") String stuclass,
			@Param(value = "keyword") String keyword,
			@Param(value = "start") Integer start,
			@Param(value = "count") Integer count);
}
