package com.wuhei.cms.test.dao;

import java.io.FileNotFoundException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Ignore;
import org.springframework.util.Log4jConfigurer;

import com.wuhei.cms.log.Log4jPrefix;

import junit.framework.TestCase;

/**
 * DAO�����������࣬����DAO���������̳��������
 * 
 * @author mulan
 * 
 */


@Ignore
public class DAOTestCase extends TestCase {
	
	static{
		// http://blog.csdn.net/wangpeng047/article/details/20375413
		// ����Log4j
		// ��web��Ŀ��Log4j������web.xml�У���
		// org.springframework.web.util.Log4jConfigListener
		try {
			Log4jConfigurer.initLogging("classpath:log4j-test.xml");
			String userName = "USER_TEST";
			String userIP = "127.0.0.1";
			Log4jPrefix.initialise(userName, userIP);
		} catch (FileNotFoundException ex) {
			System.err.println("FATAL ERROR: Cannot Initialize Log4j");
		}
	}
	
	public static SqlSessionFactory sqlSessionFactory;

	/**
	 * ��ʼ������: ����MyBatis��Mapper���õ�
	 */
	@Override
	protected void setUp() throws Exception {

		super.setUp();

		// ʵ�� SqlSessionFactory
		Reader reader = Resources
				.getResourceAsReader("configs/mybatis/mybatis-config-test.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();
	}
	
}
