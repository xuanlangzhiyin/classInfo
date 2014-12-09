package com.wuhei.cms.test.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Log4jConfigurer;

import com.wuhei.cms.log.Log4jPrefix;
import com.wuhei.cms.web.context.CmsWebContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:configs/spring/application-context-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false) 
@Transactional
public class ServiceTestCase {
	
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

		// ��ʼ�� CmsWebContext
		Properties prop = new Properties();
		InputStream inStream = prop.getClass().getResourceAsStream(
				"/CmsConfigs.properties");
		try {
			prop.load(inStream);
			System.out.println(prop.getProperty("PROJECT_NAME").trim());
			
			CmsWebContext.initialise(prop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
