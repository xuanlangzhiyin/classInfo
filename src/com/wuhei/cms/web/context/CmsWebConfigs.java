package com.wuhei.cms.web.context;

import java.util.Properties;

public class CmsWebConfigs {  

	/** 
	 * 单例模式
	 */
	private static CmsWebConfigs cmsConfigs = new CmsWebConfigs();

	public static CmsWebConfigs getCmsConfigs() {
		return cmsConfigs;
	}

	/**
	 * 配置参数
	 * 
	 * @param prop
	 */
	public static final void config(Properties prop) {
		// TODO 
	}
}
