package com.wuhei.cms.test.dao.basic;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.wuhei.cms.dao.basic.StudentDAO;
import com.wuhei.cms.model.Student;
import com.wuhei.cms.test.dao.DAOTestCase;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class StudentDAOTestCase extends DAOTestCase {

	Logger log = Logger.getLogger(StudentDAOTestCase.class);

	public void testInsertStudent() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {

			Student student = new Student();

			// student.setId(Integer.valueOf(1189));
			student.setCode("201030630091");
			student.setName("�´���");
			student.setGrade("2010");
			student.setStuclass("6");
			student.setSex("��");
			student.setLevel("����");
			student.setMajorId(Integer.valueOf(120));

			StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

			// studentDAO.updateStudent(student);
			//studentDAO.insertStudent(student);
         List<Student> students=studentDAO.getStudentListByCondition(120, "201230670258", null, null, null);
         log.info(students.get(0).toString());
			sqlSession.commit();

		} catch (Exception e) {

			System.out.println(e.getCause());

			if (e.getCause() instanceof MySQLIntegrityConstraintViolationException) {
				// ����ѧ��ʧ�ܣ�ԭ��Code �ֶ��ظ���˵����ѧ�����

				// TODO ������Ӧѧ��ĸ���
			}

		} finally {
			sqlSession.close();
		}

	}

}
