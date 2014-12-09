package com.wuhei.cms.service.impl;

import java.util.List;

import com.wuhei.cms.dao.basic.StudentUserListViewDAO;
import com.wuhei.cms.dao.basic.TeacherUserListViewDAO;
import com.wuhei.cms.dao.basic.UserDAO;
import com.wuhei.cms.model.User;
import com.wuhei.cms.model.joint.StudentUserListView;
import com.wuhei.cms.model.joint.TeacherUserListView;
import com.wuhei.cms.search.result.PageResult;
import com.wuhei.cms.search.result.UserPageResult;
import com.wuhei.cms.service.UserService;

public class UserServiceImpl implements UserService {

	private StudentUserListViewDAO studentUserListViewDAO;
	private TeacherUserListViewDAO teacherUserListViewDAO;
	private UserDAO userDAO;


	/**
	 * ���ܣ���ȡ�û���ϸ��Ϣ
	 * 
	 * @param username
	 * 
	 */

	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}
	
	
	@Override
	public User getUserById(Integer userid) {
		return userDAO.getUserById(userid);
	}

	@Override
	public UserPageResult listUserByConditions(Integer currentPage,String keyword, String rolecode) {

		UserPageResult result = new UserPageResult();

		if (rolecode.equals("ROLE_STD")) {

			// �ܼ�¼����
			Integer totalCount = studentUserListViewDAO
					.countStudentUserByCondition(keyword);
			// ��ȡ��ҳ��
			Integer totalPage = PageResult.computeTotalPage(totalCount,
					PageResult.PAGE_COUNT);

			// ��ȡ�Ϸ��ĵ�ǰҳ��
			currentPage = PageResult
					.getLegalCurrentPage(totalPage, currentPage);

			Integer start = (currentPage - 1) * PageResult.PAGE_COUNT;

			// ��ѯ��Ӧ��¼
			List<StudentUserListView> studentUserListView = studentUserListViewDAO
					.listStudentUserByCondition(keyword, start,
							PageResult.PAGE_COUNT);

			result.setCurrentPage(currentPage);
			result.setStudents(studentUserListView);
			result.setTotalCount(totalCount);
			result.setTotalPage(totalPage);


		} else if (rolecode .equals("ROLE_TCH") ) {


			// �ܼ�¼����
			Integer totalCount = teacherUserListViewDAO
					.countTeacherUserByCondition(keyword);
			// ��ȡ��ҳ��
			Integer totalPage = PageResult.computeTotalPage(totalCount,
					PageResult.PAGE_COUNT);

			// ��ȡ�Ϸ��ĵ�ǰҳ��
			currentPage = PageResult
					.getLegalCurrentPage(totalPage, currentPage);

			Integer start = (currentPage - 1) * PageResult.PAGE_COUNT;

			// ��ѯ��Ӧ��¼
			List<TeacherUserListView> teacherUserListView = teacherUserListViewDAO
					.listTeacherUserByCondition(keyword, start,
							PageResult.PAGE_COUNT);

			result.setCurrentPage(currentPage);
			result.setTeachers(teacherUserListView);
			result.setTotalCount(totalCount);
			result.setTotalPage(totalPage);

		}

		return result;
	}

	/*
	 * getter and setter
	 */
	public StudentUserListViewDAO getStudentUserListViewDAO() {
		return studentUserListViewDAO;
	}

	public void setStudentUserListViewDAO(
			StudentUserListViewDAO studentUserListViewDAO) {
		this.studentUserListViewDAO = studentUserListViewDAO;
	}

	public TeacherUserListViewDAO getTeacherUserListViewDAO() {
		return teacherUserListViewDAO;
	}

	public void setTeacherUserListViewDAO(
			TeacherUserListViewDAO teacherUserListViewDAO) {
		this.teacherUserListViewDAO = teacherUserListViewDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}



}
