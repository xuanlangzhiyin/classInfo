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

	// 获取classInfo项目上下文
	@SuppressWarnings("unused")
	private static CmsContext getCmsContext() {
		return CmsContext.getEveContext();
	}

	// classInfo项目配置信息
	public static CmsConfigs getCmsConfigs() {
		return CmsContext.getCmsConfigs();
	}

	// 获取classInfo-web项目配置信息
	public static CmsWebConfigs getCmsWebConfigs() {
		return CmsWebConfigs.getCmsConfigs();
	}

	// e.g. "/usr/local/apache-tomcat-7.0.53/webapps"
	private static String realPath;
	
	// e.g. "/classInfo"
	private static String contextPath;

	public static final void initialise(Properties prop) {

		// 进行classInfo项目基础数据初始化
		CmsContext.initialise(prop);
		
		CmsWebConfigs.config(prop);

		// TODO 进行classInfo项目WebContext初始化
		// 比如，功能模块开启与关闭等参数
		String realPath = prop.getProperty("REAL_PATH");
		setRealPath(realPath);

		String contextPath = prop.getProperty("CONTEXT_PATH");
		setContextPath(contextPath);

	}

	/**
	 * 
	 * 获取当前登录用户所属学校的id
	 * 
	 * 当前的httpsession中保存登录用户所属学校的id
	 * 
	 * @return
	 */
	public static Integer getCurrentUniversityId() {
		return (Integer) ActionContext.getContext().getSession()
				.get(Utils.UNIVERSITY_ID);
	}

	/**
	 * 
	 * 获取当前登录用户所属院系的id
	 * 
	 * 当前的httpsession中保存登录用户所属院系的id
	 * 
	 * @return
	 */
	public static Integer getCurrentDepartmentId() {
		return (Integer) ActionContext.getContext().getSession()
				.get(Utils.DEPARTMENT_ID);
	}

	/**
	 * 
	 * 获取当前登录用户所属的专业（学生）或者正在操作的专业（教务员）的id
	 * 
	 * 当前的httpsession中保存登录用户操作或者所属专业的id
	 * 
	 * @return majorid or null
	 */
	public static Integer getCurrentMajorId() {
		try {
			Integer majorid = (Integer) ActionContext.getContext().getSession()
					.get(Utils.MAJOR_ID);
			// 正常情况，用户已经登录
			if (majorid != null)
				return majorid;
		} catch (Exception e) {
			// Spring session or Action session is null
		}
		return null;

	}
	
	/**
	 * 获取系统当前学年
	 * 
	 * @param 
	 * @return
	 */
	public static final String getCurrentYear() {
		return CmsContext.getCurrentYear();
	}
	
	
	
	/**
	 * 获取系统当前学期
	 * 
	 * @param 
	 * @return
	 */
	public static final String getCurrentTerm() {
		return CmsContext.getCurrentTerm();
	}
	
	/**
	 * 获取系统当前学年列表
	 * 
	 * @param 
	 * @return
	 */
	public static final List<String> getCurrentYearList() {
		return CmsContext.getCurrentYearList();
	}
	
	
	

	/**
	 * 获取当前登录用户所属学校
	 * 
	 * @return 当前登录用户所属学校
	 */
	public static University getCurrentUniversity() {
		Integer universityId = getCurrentUniversityId();
		return CmsContext.getUniversity(universityId);
	}

	/**
	 * 获取当前登录用户所属的院系
	 * 
	 * @return 当前登录用户所属的院系
	 */
	public static Department getCurrentDepartment() {
		Integer universityId = getCurrentUniversityId();
		Integer departmentId = getCurrentDepartmentId();
		return CmsContext.getDepartment(universityId, departmentId);
	}

	/**
	 * 获取当前登录用户所属的专业（学生）或者正在操作的专业（教务员）
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
	 * 返回当前登录用户所属的学院的所有专业-Map
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
	 * 获取某专业课程，如果majorId 为 null，取当前默认majorId
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
	 * 获取当前登录用户 or null
	 * 
	 * @return
	 */
	public static LoginUser getCurrentUser() {
		LoginUser loginUser = null;
		try {
			// 正常情况，用户通过Spring Security登录，从Spring Context中获取
			loginUser = (LoginUser) SpringSecurityUtils.getCurrentUser();
			if (loginUser != null)
				return loginUser;
			// Junit-Action 测试，登录用户没有经过Spring Security，从HttpSession里获取
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
	 * 改变当前操作的专业
	 * 
	 * @param majorid
	 */
	public static void setCurrentMajorid(Integer majorid) {
		ActionContext.getContext().getSession().put(Utils.MAJOR_ID, majorid);
	}

	/**
	 * 获取当前majorList
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
