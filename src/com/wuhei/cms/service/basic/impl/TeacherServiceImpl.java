package com.wuhei.cms.service.basic.impl;


import java.util.ArrayList;
import java.util.List;

import com.wuhei.cms.dao.basic.TeacherDAO;
import com.wuhei.cms.dao.basic.TeacherDetailViewDAO;
import com.wuhei.cms.dao.basic.TeacherListViewDAO;
import com.wuhei.cms.dao.basic.UserDAO;
import com.wuhei.cms.dao.cactivities.CteacherDAO;
import com.wuhei.cms.model.Teacher;

import com.wuhei.cms.model.deletedata.TeacherDeleteResult;
import com.wuhei.cms.model.joint.TeacherDetailView;
import com.wuhei.cms.model.joint.TeacherListView;
import com.wuhei.cms.search.result.PageResult;
import com.wuhei.cms.search.result.TeacherPageResult;
import com.wuhei.cms.service.basic.TeacherService;

public class TeacherServiceImpl implements TeacherService {

	public TeacherDAO teacherDAO;
	public TeacherDetailViewDAO teacherDetailViewDAO;
	public TeacherListViewDAO teacherListViewDAO;
	public UserDAO userDAO;
	public CteacherDAO cteacherDAO;

	

	@Override
	public List<Teacher> getTeacherList() {
		return teacherDAO.getTeacherList();
	}
	
	@Override
	public Teacher getTeacherById(Integer id){
		return teacherDAO.getTeacher(id);
	}
	
	@Override
	public Teacher getMainCteacher(Integer courseid) {
		// TODO Auto-generated method stub
		
		return teacherDAO.getMainCteacher(courseid);
	}
	
	public List<Teacher> getTeacherListByCourseId(Integer courseid) {
		
		return teacherDAO.getTeacherListByCourseId(courseid);
	}
	
	public List<Teacher> getTeacherListByDepartmentid(Integer departmentid){
		return teacherDAO.getTeacherListByDepartmentid(departmentid);
	}
	@Override
	public TeacherPageResult getTeacherListByConditions(Integer departmentId,
			String keyword, Integer currentPage) {
		
		// �ܼ�¼����
		Integer totalCount = this.teacherListViewDAO.countTeacherByConditions(
				departmentId, keyword);
		
		// ��ȡ��ҳ��
		Integer totalPage = PageResult.computeTotalPage(totalCount,
				PageResult.PAGE_COUNT);

		// ��ȡ�Ϸ��ĵ�ǰҳ��
		currentPage = PageResult.getLegalCurrentPage(totalPage, currentPage);

		Integer start = (currentPage - 1) * PageResult.PAGE_COUNT;

		// ��ѯ��Ӧ��¼
		List<TeacherListView> teachers = this.teacherListViewDAO
				.listTeacherByConditions(departmentId, keyword, start,
						PageResult.PAGE_COUNT);

		TeacherPageResult teacherPageResult = new TeacherPageResult();

		teacherPageResult.setCurrentPage(currentPage);

		teacherPageResult.setTotalCount(totalCount);

		teacherPageResult.setTotalPage(totalPage);

		teacherPageResult.setTeachers(teachers);

		return teacherPageResult;

	}

	@Override
	public void newTeacher(Teacher teacher) {
		teacherDAO.insertTeacher(teacher);
	}

	@Override
	public TeacherDetailView getTeacherDetailViewById(Integer id) {
		return teacherDetailViewDAO.getTeacherDetailViewById(id);
	}

	@Override
	public List<TeacherDetailView> getTeacherDetailViewByCondition(
			Integer departmentid, Integer majorid) {
		return teacherDetailViewDAO.getTeacherDetailViewListByCondition(
				departmentid, majorid);
	}

	@Override
	public void deleteTeacher(Integer id) {
		teacherDAO.deleteTeacher(id);
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		teacherDAO.updateTeacher(teacher);
	}

	
	/**
	 * ����ҳ�淵�ؽ��-����ɾ���ʦ  
	 */
	public TeacherDeleteResult deleteTeachers(List<Integer> teachersId)
	{
		int teacherid;
		int teachernumber;
		Teacher teacher;
		TeacherDeleteResult teacherDeleteResult=new TeacherDeleteResult();
		List<Teacher> errorTeachers = new ArrayList<Teacher>();
		
		Integer successCount = Integer.valueOf(0);
		Integer errorCount = Integer.valueOf(0);
		for (int i = 0; i < teachersId.size(); i++) 
		{
			teacherid=teachersId.get(i);
			teachernumber=cteacherDAO.countCteacherByTeacherid(teacherid);

			if(teachernumber!=0)
			{
				teacher=teacherDAO.getTeacher(teacherid);
			    errorTeachers.add(teacher);
			    errorCount++;
	            continue;
			}
			else
			{
				try {
					deleteTeacher(teacherid);  // ��ݿ�user����teacherid��Ϊ����ɾ��
				} catch (Exception e) {
				    teacher=teacherDAO.getTeacher(teacherid);
				    errorTeachers.add(teacher);
				    errorCount++;
		            continue;
				}
				successCount++;
			}
			
			
		}
		teacherDeleteResult.setSuccessCount(successCount);
		teacherDeleteResult.setErrorCount(errorCount);
		teacherDeleteResult.setErrTeachers(errorTeachers);
		return teacherDeleteResult;
	}
	
	


	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	public TeacherDetailViewDAO getTeacherDetailViewDAO() {
		return teacherDetailViewDAO;
	}

	public void setTeacherDetailViewDAO(
			TeacherDetailViewDAO teacherDetailViewDAO) {
		this.teacherDetailViewDAO = teacherDetailViewDAO;
	}

	public TeacherListViewDAO getTeacherListViewDAO() {
		return teacherListViewDAO;
	}

	public void setTeacherListViewDAO(TeacherListViewDAO teacherListViewDAO) {
		this.teacherListViewDAO = teacherListViewDAO;
	}

	public CteacherDAO getCteacherDAO() {
		return cteacherDAO;
	}

	public void setCteacherDAO(CteacherDAO cteacherDAO) {
		this.cteacherDAO = cteacherDAO;
	}
	

	

}
