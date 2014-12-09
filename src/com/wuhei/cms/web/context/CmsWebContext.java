package com.wuhei.cms.web.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.wuhei.cms.context.CmsConfigs;
import com.wuhei.cms.context.CmsContext;
import com.wuhei.cms.login.LoginUser;
import com.wuhei.cms.login.SpringSecurityUtils;
import com.wuhei.cms.model.Csetting;
import com.wuhei.cms.model.Department;

import com.wuhei.cms.model.Major;

import com.wuhei.cms.model.University;
import com.wuhei.cms.utils.Utils;
import com.opensymphony.xwork2.ActionContext;

public class CmsWebContext {

	// ��ȡclassInfo��Ŀ������
	@SuppressWarnings("unused")
	private static CmsContext getCmsContext() {
		return CmsContext.getEveContext();
	}

	// classInfo��Ŀ������Ϣ
	public static CmsConfigs getCmsConfigs() {
		return CmsContext.getCmsConfigs();
	}

	// ��ȡclassInfo-web��Ŀ������Ϣ
	public static CmsWebConfigs getCmsWebConfigs() {
		return CmsWebConfigs.getCmsConfigs();
	}

	// e.g. "/usr/local/apache-tomcat-7.0.53/webapps"
	private static String realPath;
	
	// e.g. "/classInfo"
	private static String contextPath;

	public static final void initialise(Properties prop) {

		// ����classInfo��Ŀ�������ݳ�ʼ��
		CmsContext.initialise(prop);
		
		CmsWebConfigs.config(prop);

		// TODO ����classInfo��ĿWebContext��ʼ��
		// ���磬����ģ�鿪����رյȲ���
		String realPath = prop.getProperty("REAL_PATH");
		setRealPath(realPath);

		String contextPath = prop.getProperty("CONTEXT_PATH");
		setContextPath(contextPath);

	}

	/**
	 * 
	 * ��ȡ��ǰ��¼�û�����ѧУ��id
	 * 
	 * ��ǰ��httpsession�б����¼�û�����ѧУ��id
	 * 
	 * @return
	 */
	public static Integer getCurrentUniversityId() {
		return (Integer) ActionContext.getContext().getSession()
				.get(Utils.UNIVERSITY_ID);
	}

	/**
	 * 
	 * ��ȡ��ǰ��¼�û�����Ժϵ��id
	 * 
	 * ��ǰ��httpsession�б����¼�û�����Ժϵ��id
	 * 
	 * @return
	 */
	public static Integer getCurrentDepartmentId() {
		return (Integer) ActionContext.getContext().getSession()
				.get(Utils.DEPARTMENT_ID);
	}

	/**
	 * 
	 * ��ȡ��ǰ��¼�û�������רҵ��ѧ�����������ڲ�����רҵ������Ա����id
	 * 
	 * ��ǰ��httpsession�б����¼�û�������������רҵ��id
	 * 
	 * @return majorid or null
	 */
	public static Integer getCurrentMajorId() {
		try {
			Integer majorid = (Integer) ActionContext.getContext().getSession()
					.get(Utils.MAJOR_ID);
			// ����������û��Ѿ���¼
			if (majorid != null)
				return majorid;
		} catch (Exception e) {
			// Spring session or Action session is null
		}
		return null;

	}
	
	/**
	 * ��ȡϵͳ��ǰѧ��
	 * 
	 * @param 
	 * @return
	 */
	public static final String getCurrentYear() {
		return CmsContext.getCurrentYear();
	}
	
	
	
	/**
	 * ��ȡϵͳ��ǰѧ��
	 * 
	 * @param 
	 * @return
	 */
	public static final String getCurrentTerm() {
		return CmsContext.getCurrentTerm();
	}
	
	/**
	 * ��ȡϵͳ��ǰѧ���б�
	 * 
	 * @param 
	 * @return
	 */
	public static final List<String> getCurrentYearList() {
		return CmsContext.getCurrentYearList();
	}
	
	
	

	/**
	 * ��ȡ��ǰ��¼�û�����ѧУ
	 * 
	 * @return ��ǰ��¼�û�����ѧУ
	 */
	public static University getCurrentUniversity() {
		Integer universityId = getCurrentUniversityId();
		return CmsContext.getUniversity(universityId);
	}

	/**
	 * ��ȡ��ǰ��¼�û�������Ժϵ
	 * 
	 * @return ��ǰ��¼�û�������Ժϵ
	 */
	public static Department getCurrentDepartment() {
		Integer universityId = getCurrentUniversityId();
		Integer departmentId = getCurrentDepartmentId();
		return CmsContext.getDepartment(universityId, departmentId);
	}

	/**
	 * ��ȡ��ǰ��¼�û�������רҵ��ѧ�����������ڲ�����רҵ������Ա��
	 * 
	 * @return
	 */
	public static Major getCurrentMajor() {
		Integer departmentId = getCurrentDepartmentId();
		Integer majorId = getCurrentMajorId();
		return CmsContext.getMajor(departmentId, majorId);
	}

	public static Major getMajor(Integer majorId) {
		Integer departmentId = getCurrentDepartmentId();
		if (majorId == null)
			majorId = getCurrentMajorId();
		return CmsContext.getMajor(departmentId, majorId);
	}

	/**
	 * 
	 * ���ص�ǰ��¼�û�������ѧԺ������רҵ-Map
	 * 
	 * @param departmentid
	 * @return
	 */
	public static final ConcurrentHashMap<Integer, Major> getCurrentMajorMap(
			Integer departmentid) {
		Integer departmentId = getCurrentDepartmentId();
		return CmsContext.getMajorMapByDepartmentid(departmentid);
	}

	/**
	 * ��ȡĳרҵ�γ̣����majorId Ϊ null��ȡ��ǰĬ��majorId
	 * 
	 * @param csettingId
	 * @param majorId
	 * @return
	 */
	public static Csetting getCsetting(Integer csettingId, Integer majorId) {
		if (majorId == null)
			majorId = getCurrentMajorId();
		return CmsContext.getCsetting(majorId, csettingId);
	}

	public static ConcurrentHashMap<Integer, Csetting> getCsettingMap(
			Integer majorId) {
		if (majorId == null)
			majorId = getCurrentMajorId();
		return CmsContext.getCsettingMap(majorId);
	}


	/**
	 * ��ȡ��ǰ��¼�û� or null
	 * 
	 * @return
	 */
	public static LoginUser getCurrentUser() {
		LoginUser loginUser = null;
		try {
			// ����������û�ͨ��Spring Security��¼����Spring Context�л�ȡ
			loginUser = (LoginUser) SpringSecurityUtils.getCurrentUser();
			if (loginUser != null)
				return loginUser;
			// Junit-Action ���ԣ���¼�û�û�о���Spring Security����HttpSession���ȡ
			loginUser = (LoginUser) ActionContext.getContext().getSession()
					.get(Utils.LOGIN_USER);
			if (loginUser != null)
				return loginUser;
		} catch (Exception e) {
			// Spring session or Action session is null
		}
		return null;
	}

	/**
	 * 
	 * �ı䵱ǰ������רҵ
	 * 
	 * @param majorid
	 */
	public static void setCurrentMajorid(Integer majorid) {
		ActionContext.getContext().getSession().put(Utils.MAJOR_ID, majorid);
	}

	/**
	 * ��ȡ��ǰmajorList
	 * 
	 * @return
	 */
	public static List<Major> getCurrentMajorList() {

		Integer departmentid = getCurrentDepartmentId();
		ConcurrentHashMap<Integer, Major> map = CmsContext
				.getMajorMapByDepartmentid(departmentid);
		// map-->list
		List<Major> majorList = new ArrayList<Major>();

		Iterator<Entry<Integer, Major>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, Major> entry = iterator.next();
			majorList.add(entry.getValue());
		}
		return majorList;
	}

	/*
	 * getters and setters
	 */

	public static String getRealPath() {
		return realPath;
	}

	public static void setRealPath(String realPath) {
		CmsWebContext.realPath = realPath;
	}

	public static String getContextPath() {
		return contextPath;
	}

	public static void setContextPath(String contextPath) {
		CmsWebContext.contextPath = contextPath;
	}

}
