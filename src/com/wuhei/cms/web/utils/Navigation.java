package com.wuhei.cms.web.utils;

/**
 * 保存导航信息
 * 
 *
 */
public class Navigation {
	
	/**
	 * 
	 */
	public String contextPath;
	/**
	 * 
	 */
	public String basePath;
	/**
	 * 
	 */
	public String actionNamespace;
	/**
	 * 
	 */
	public String baseUrl;
	/**
	 * 
	 */
	public String currentHeaderTab;

	/*
	 * Getter and Setter
	 */

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getActionNamespace() {
		return actionNamespace;
	}

	public void setActionNamespace(String actionNamespace) {
		this.actionNamespace = actionNamespace;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getCurrentHeaderTab() {
		return currentHeaderTab;
	}

	public void setCurrentHeaderTab(String currentHeaderTab) {
		this.currentHeaderTab = currentHeaderTab;
	}
}
