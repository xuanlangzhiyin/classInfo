package com.wuhei.cms.web.context;

import java.util.Properties;

public class CmsWebConfigs {  

	/** 
	 * ����ģʽ
	 */
	private static CmsWebConfigs cmsConfigs = new CmsWebConfigs();

	public static CmsWebConfigs getCmsConfigs() {
		return cmsConfigs;
	}

	/**
	 * ���ò���
	 * 
	 * @param prop
	 */
	public static final void config(Properties prop) {
		// TODO 
	}
}
