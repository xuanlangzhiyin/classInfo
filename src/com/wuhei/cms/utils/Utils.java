package com.wuhei.cms.utils;


import org.apache.commons.lang.StringUtils;

public class Utils {

	
	public static final String LOGIN_USER = "loginuser";

	public static final String UNIVERSITY_ID = "university_id";

	public static final String DEPARTMENT_ID = "department_id";

	public static final String MAJOR_ID = "major_id";

	/**
	 * 学院教务员角色
	 */
	public static final String ROLE_ACAMGR = "ROLE_ACAMGR";

	/**
	 * 教师角色
	 */
	public static final String ROLE_TCH = "ROLE_TCH";

	/**
	 * 学生角色
	 */
	public static final String ROLE_STD = "ROLE_STD";

	
	public static final boolean isNonRequiredStringParamOK(String param, int max) {
		if(StringUtils.isEmpty(param)){
			return true;
		}
		if (StringUtils.isNotBlank(param)) {
			int length = param.length();
			if (length <= max)
				return true;
		}
		return false;
	}
	
	public static final boolean isRequiredStringParamOK(String param, int min, int max) {
		if (StringUtils.isNotBlank(param)) {
			int length = param.length();
			if (length >= min && length <= max)
				return true;
		}
		return false;
	}
	
	

	

}
