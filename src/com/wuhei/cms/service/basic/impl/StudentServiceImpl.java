package com.wuhei.cms.service.basic.impl;

import java.util.ArrayList;
import java.util.List;

import com.wuhei.cms.dao.basic.StudentDAO;
import com.wuhei.cms.dao.basic.StudentDetailViewDAO;
import com.wuhei.cms.dao.basic.StudentListViewDAO;
import com.wuhei.cms.dao.basic.UserDAO;
import com.wuhei.cms.dao.cactivities.CstudentDAO;
import com.wuhei.cms.model.Student;
import com.wuhei.cms.model.joint.StudentDetailView;
import com.wuhei.cms.model.deletedata.StudentDeleteResult;
import com.wuhei.cms.model.joint.StudentListView;
import com.wuhei.cms.search.result.PageResult;
import com.wuhei.cms.search.result.StudentPageResult;
import com.wuhei.cms.service.basic.StudentService;

/**
 * ��ѧ����õ�service�� ʵ�� 
 * 
 * 
 */
public class StudentServiceImpl implements StudentService {

	private StudentDAO studentDAO;
	private StudentDetailViewDAO studentDetailViewDAO;
	private StudentListViewDAO studentListViewDAO;
	private UserDAO userDAO;
	private CstudentDAO cstudentDAO;

	@Override
	public List<Student> getStudentList() {
		return studentDAO.getStudentList();
	}

	@Override
	public List<Student> getStudentListByCondition(Integer majorId,
			String code, String grade, String stuclass, String keyword) {
		return studentDAO.getStudentListByCondition(majorId, code, grade,
				stuclass, keyword);
	}

	@Override
	public void newStudent(Student student) {
		studentDAO.insertStudent(student);

	}

	@Override
	public StudentDetailView getStudentDetailViewById(Integer id) {
		return studentDetailViewDAO.getStudentDetailViewById(id);
	}

	@Override
	public void deleteStudent(Integer id) {
		studentDAO.deleteStudent(id);

	}

	@Override
	public void deleteStudents(Integer[] idList) {
		for (int idCount = 0; idCount < idList.length; idCount++) {
			studentDAO.deleteStudent(idList[idCount]);
		}

	}

	@Override
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}

	@Override
	public StudentPageResult listStudentByConditions(Integer currentPage,
			Integer majorid, String keyword, String grade, String stuclass) {
		StudentPageResult result = new StudentPageResult();

		// �ܼ�¼����
		Integer totalCount = studentListViewDAO.countStudentByCondition(
				majorid, grade, stuclass, keyword);

		// ��ȡ��ҳ��
		Integer totalPage = PageResult.computeTotalPage(totalCount,
				PageResult.PAGE_COUNT);

		// ��ȡ�Ϸ��ĵ�ǰҳ��
		currentPage = PageResult.getLegalCurrentPage(totalPage, currentPage);

		Integer start = (currentPage - 1) * PageResult.PAGE_COUNT;

		// ��ѯ��Ӧ��¼
		List<StudentListView> studentList = studentListViewDAO
				.listStudentByCondition(majorid, grade, stuclass, keyword,
						start, PageResult.PAGE_COUNT);

		result.setCurrentPage(currentPage);
		result.setStudents(studentList);
		result.setTotalCount(totalCount);
		result.setTotalPage(totalPage);

		return result;
	}


	public StudentDeleteResult deleteStudents(List<Integer> studentsId) {

		int studentsid;
		int studentscount = 0;
		Student student;
		StudentDeleteResult studentDeleteResult = new StudentDeleteResult();
		List<Student> errorStudent = new ArrayList<Student>();

		Integer successCount = Integer.valueOf(0);
		Integer errorCount = Integer.valueOf(0);

		for (int i = 0; i < studentsId.size(); i++) {

			studentsid = studentsId.get(i);

			try {
				studentscount = cstudentDAO
						.countCstudentByStudentid(studentsid);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}

			if (studentscount != 0) {
				student = studentDAO.getStudent(studentsid);
				errorStudent.add(student);
				errorCount++;
				continue;
			} else {
				try {
					deleteStudent(studentsid); // ��ݿ�user����studentid��Ϊ����ɾ��
				} catch (Exception e) {
					student = studentDAO.getStudent(studentsid);
					errorStudent.add(student);
					errorCount++;
					continue;
				}
				successCount++;
			}
		}
		studentDeleteResult.setErrorCount(errorCount);
		studentDeleteResult.setSuccessCount(successCount);
		studentDeleteResult.setErrorStudents(errorStudent);
		return studentDeleteResult;
	}

	/**
	 * getter and setter
	 * 
	 * @return
	 */
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public StudentDetailViewDAO getStudentDetailViewDAO() {
		return studentDetailViewDAO;
	}

	public void setStudentDetailViewDAO(
			StudentDetailViewDAO studentDetailViewDAO) {
		this.studentDetailViewDAO = studentDetailViewDAO;
	}

	public StudentListViewDAO getStudentListViewDAO() {
		return studentListViewDAO;
	}

	public void setStudentListViewDAO(StudentListViewDAO studentListViewDAO) {
		this.studentListViewDAO = studentListViewDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public CstudentDAO getCstudentDAO() {
		return cstudentDAO;
	}

	public void setCstudentDAO(CstudentDAO cstudentDAO) {
		this.cstudentDAO = cstudentDAO;
	}

}
