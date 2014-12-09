package com.wuhei.cms.context;

/**
 * classInfo项目配置信息
 * 
 * 
 */
public class CmsConfigs {

	
	protected static String courseFilePath;

	/**
	 * 单例模式
	 */

	private static CmsConfigs CmsConfigs = new CmsConfigs();

	public static CmsConfigs getCmsConfigs() {
		return CmsConfigs;
	}

	

	

}
