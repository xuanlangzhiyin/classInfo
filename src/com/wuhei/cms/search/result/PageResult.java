package com.wuhei.cms.search.result;

import java.io.Serializable;

/**
 * ��ѯ�������ʾ��webҳ��
 * 
 *
 */
public abstract class PageResult implements Serializable{

	/**
	 * ������ÿҳ�ļ�¼����
	 */
	public static final int PAGE_COUNT = 10;
	
	

	/**
	 * ��ǰҳ��
	 */
	private Integer currentPage;
	
	/**
	 * �ܼ�¼����
	 */
	private Integer totalCount;
	
	/**
	 * ��ҳ��
	 */
	private Integer totalPage;
	
	
	/**
	 * ���ܣ������ܼ�¼����ÿҳ��¼��������ҳ��
	 * 
	 * @param totalCount �ܼ�¼��
	 * 
	 * @param pageCount  ÿҳ��¼��
	 * 
	 * @return Integer ��ҳ��
	 */
	public static Integer computeTotalPage(Integer totalCount,Integer pageCount){
		if (totalCount ==0 ) return 0;
		return (totalCount-1)/pageCount + 1;
	}
	
	/**
	 * ���ܣ�������ҳ���͵�ǰҳ�루δ֪�Ƿ�Ϸ�����ȡ�Ϸ��ĵ�ǰҳ��
	 * 
	 * @param totalPage ��ҳ��
	 * 
	 * @param currentPage ��ǰҳ��
	 * 
	 * @return Integer �Ϸ��ĵ�ǰҳ��
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
