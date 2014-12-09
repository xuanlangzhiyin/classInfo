package com.wuhei.cms.search;

public class SearchMeta {
	
	/**
	 * 当前页, URL请求的页码参数
	 */
	protected Integer currentPage;

	/**
	 * 关键字, 查询关键字参数保存于此
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
