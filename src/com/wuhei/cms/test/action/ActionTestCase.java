package com.wuhei.cms.test.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Log4jConfigurer;

import com.wuhei.cms.dao.login.LoginDAO;
import com.wuhei.cms.log.Log4jPrefix;
import com.wuhei.cms.login.LoginUser;
import com.wuhei.cms.model.Major;
import com.wuhei.cms.utils.Utils;
import com.wuhei.cms.context.CmsContext;
import com.wuhei.cms.web.context.CmsWebContext;
import com.opensymphony.xwork2.ActionContext;

//https://gist.github.com/maurizio-cucchiara/2357648
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:configs/spring/application-context.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
@WebAppConfiguration
public class ActionTestCase extends StrutsSpringJUnit4TestCase {

	static {
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
			CmsWebContext.initialise(prop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * HttpSession
	 */
	public HttpSession session;

	/**
	 * ʵ���û���½
	 */
	public LoginDAO loginDAO;

	@Override
	public void setUp() throws Exception {

		super.setUp();

		// ���Mock���͵�servlet�����ʵ�����MockHttpSession
		// ���У�MockHttpRequest��StrutsSpringJUnit4TestCase������
		String sessionId = UUID.randomUUID().toString();
		this.session = new MockHttpSession(servletContext, sessionId);
		request.setSession(session);

	}

	public void initSession(ActionContext actionContext) {
		actionContext.setSession(this.getSessionMap());
	}

	/**
	 * ��HttpSession�ṹת����Map�ṹ
	 * 
	 * @return session map
	 */
	public Map<String, Object> getSessionMap() {
		Map map = new HashMap();
		Enumeration enu = this.session.getAttributeNames();
		String key = null;
		Object value = null;
		while (enu.hasMoreElements()) {
			key = (String) enu.nextElement();
			value = this.session.getAttribute(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * ��¼�û�
	 * 
	 * @param username
	 */
	public void loginUser(String username) {
		// ��ʼ��loginDAO
		loginDAO = (LoginDAO) this.applicationContext.getBean("loginDAO");
		// ����ݿ��ȡ�û�
		LoginUser loginUser = loginDAO.loadUserByUsername(username);
		// System.out.println(loginUser.toString());
		// ΪAction��εĲ��ԣ���session�����õ�½������
		session.setAttribute(Utils.LOGIN_USER, loginUser);
		// ���õ�½�û�����
		session.setAttribute(Utils.UNIVERSITY_ID, loginUser.getUniversityid());
		session.setAttribute(Utils.DEPARTMENT_ID, loginUser.getDepartmentid());
		// Ϊ����Ա����Ĭ��רҵ�����ѧԺΪ��������̡�
		ConcurrentHashMap<Integer, Major> majorMap = CmsContext
				.getMajorMapByDepartmentid(loginUser.getDepartmentid());
		Major defaultMajor = (Major) majorMap.values().toArray()[0];
		session.setAttribute(Utils.MAJOR_ID, defaultMajor.getId());
	}

	/**
	 * ��¼����Ա��������
	 */
	public void loginDefaultACAMGR() {
		loginUser("G03031");
	}

	/**
	 * ��¼��ʦ���´���
	 */
	public void loginDefaultTCH() {
		loginUser("G03067");
	}

	/**
	 * ��¼ѧ����˹��
	 */
	public void loginDefaultsSTD() {
		loginUser("201130633285");
	}

	/**
	 * getters and setters
	 */

	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
}
