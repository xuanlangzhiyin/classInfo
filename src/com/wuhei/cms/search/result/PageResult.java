package com.wuhei.cms.search.result;

import java.io.Serializable;

/**
 * 查询结果－显示在web页面
 * 
 *
 */
public abstract class PageResult implements Serializable{

	/**
	 * 常量，每页的记录条数
	 */
	public static final int PAGE_COUNT = 10;
	
	

	/**
	 * 当前页数
	 */
	private Integer currentPage;
	
	/**
	 * 总记录条数
	 */
	private Integer totalCount;
	
	/**
	 * 总页数
	 */
	private Integer totalPage;
	
	
	/**
	 * 功能：根据总记录数和每页记录数计算总页数
	 * 
	 * @param totalCount 总记录数
	 * 
	 * @param pageCount  每页记录数
	 * 
	 * @return Integer 总页数
	 */
	public static Integer computeTotalPage(Integer totalCount,Integer pageCount){
		if (totalCount ==0 ) return 0;
		return (totalCount-1)/pageCount + 1;
	}
	
	/**
	 * 功能：根据总页数和当前页码（未知是否合法）获取合法的当前页码
	 * 
	 * @param totalPage 总页码
	 * 
	 * @param currentPage 当前页码
	 * 
	 * @return Integer 合法的当前页码
	 */
	public static Integer getLegalCurrentPage(Integer totalPage,Integer currentPage){
		if (currentPage == null) return 1;
		if (currentPage<1 || currentPage > totalPage) return 1;
		return currentPage;
	}
	
	/**
	 * getters and setters
	 *  
	 */
	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	public static int getPageCount() {
		return PAGE_COUNT;
	}

}
