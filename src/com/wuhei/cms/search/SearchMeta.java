package com.wuhei.cms.search;

public class SearchMeta {
	
	/**
	 * ��ǰҳ, URL�����ҳ�����
	 */
	protected Integer currentPage;

	/**
	 * �ؼ���, ��ѯ�ؼ��ֲ��������ڴ�
	 * 
	 */
	protected String keyword;

	/**
	 * getters and setters
	 */

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
