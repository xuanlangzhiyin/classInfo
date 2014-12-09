package com.wuhei.cms.service.basic;

import java.util.List;

import com.wuhei.cms.model.Teacher;

import com.wuhei.cms.model.deletedata.TeacherDeleteResult;
import com.wuhei.cms.model.joint.TeacherDetailView;
import com.wuhei.cms.search.result.TeacherPageResult;

public interface TeacherService {

	/**
	 * ��ʦ�б�
	 * ���н�ʦ�������������н�ʦ�������Ϣ
	 * @return ���صĽ�ʦ��Ϣ�б�
	 */
	public List<Teacher> getTeacherList();
	
	/**
	 * 
	 * @param id
	 * @return ��ʦ  by zhuchengrong
	 */
	public Teacher getTeacherById(Integer id);
	
	/**
	 * ��ȡ����Ҫ������ʦ
	 * @param courseid
	 * @return
	 */
	public List<Teacher> getTeacherListByCourseId(Integer courseid);
	
	/**
	 * ����������ʦ�б�
	 * ���н�ʦɸѡ���г����������Ľ�ʦ�������Ϣ��ɸѡ��������ѧԺId�������ؼ���
	 * @param departmentId Ժϵid ����ɸѡ������ʦ
	 * @param keyword  ��ʦ�����ؼ���
	 * @return ���ط��������Ľ�ʦ��Ϣ�б�
	 */
	public TeacherPageResult getTeacherListByConditions(
			Integer departmentId, String keyword,Integer currentPage);
	
	/**
	 * ������ʦ
	 * ����������ʦҳ�棬��д��ʦ��Ϣ��������档
	 * @param teacher Ҫ�������ݿ�Ľ�ʦ��ʵ��
	 * @return
	 */
	public void newTeacher(Teacher teacher);
	
	
	/**
	 * �鿴��ʦ��ϸ��Ϣ
	 * ��������ʦ��ϸ��Ϣҳ��
	 * @return ���ؽ�ʦ��ϸ��Ϣ��ʵ��
	 */
	public TeacherDetailView getTeacherDetailViewById(Integer id);
	
	/**
	 * ���������鿴��ʦ��ϸ��Ϣ
	 * @param departmentid
	 * @param majorid
	 * @return
	 */
	public List<TeacherDetailView> getTeacherDetailViewByCondition(Integer departmentid,Integer majorid);
	
	/**
	 * 
	 * @param departmentid
	 * @return ����departmentid�����ҽ�ʦ�б�
	 */
	
	public List<Teacher> getTeacherListByDepartmentid(Integer departmentid);
	
	/**
	 * ɾ����ʦ
	 * ��ĳһ����ʦ���ݽ���ɾ��
	 * @param id Ҫɾ���Ľ�ʦid
	 */
	public void deleteTeacher(Integer id);
	
	/**
	 * �޸Ľ�ʦ��Ϣ
	 * ���޸�ҳ������޸ĺ󣬵�������޸�
	 * @param teacher ��ʦ��ʵ��������Ҫ�޸ĵ���Ϣ
	 * @return
	 */
	
	/**
	 * ����ɾ����ʦ
	 * 
	 * @param ǰ̨������Ҫ����ɾ���Ľ�ʦ��id�б�
	 * @return
	 */
	public TeacherDeleteResult deleteTeachers(List<Integer> teachersId);
	

	public void updateTeacher(Teacher teacher);
	

	/**
	 * ��ȡ��������ʦ
	 */
    public Teacher getMainCteacher(Integer courseid);
}
