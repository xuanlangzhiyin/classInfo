package com.wuhei.cms.service.basic.impl;

import java.util.List;

import com.wuhei.cms.dao.basic.MajorDAO;
import com.wuhei.cms.dao.basic.MajorDetailViewDAO;
import com.wuhei.cms.dao.basic.MajorListViewDAO;
import com.wuhei.cms.model.Major;
import com.wuhei.cms.model.joint.MajorDetailView;
import com.wuhei.cms.model.joint.MajorListView;
import com.wuhei.cms.search.result.MajorPageResult;
import com.wuhei.cms.search.result.PageResult;
import com.wuhei.cms.service.basic.MajorService;



public class MajorServiceImpl implements MajorService {

	private MajorDAO majorDAO;
	private MajorDetailViewDAO majorDetailViewDAO;
	private MajorListViewDAO majorListViewDAO;

	/**
	 * 系统用户：根据id返回专业的详细信息
	 * 
	 * 
	 */
	
	@Override
	public MajorPageResult getMajorByConditions(Integer departmentid,
			Integer currentPage, String keyword) {
		// TODO Auto-generated method stub
		Integer totalCount = this.majorDAO.countMajorByConditions(departmentid,
				keyword);

		Integer totalPage = PageResult.computeTotalPage(totalCount,
				PageResult.PAGE_COUNT);

		currentPage = PageResult.getLegalCurrentPage(totalPage, currentPage);

		Integer start = (currentPage - 1) * PageResult.PAGE_COUNT;

		List<MajorListView> majors = this.majorListViewDAO
				.getMajorListViewListByConditions(departmentid, keyword, start,
						PageResult.PAGE_COUNT);
		MajorPageResult majorPageResult = new MajorPageResult();

		majorPageResult.setCurrentPage(currentPage);

		majorPageResult.setTotalCount(totalCount);

		majorPageResult.setTotalPage(totalPage);

		majorPageResult.setMajors(majors);

		return majorPageResult;
	}
	@Override
	public List<Major> getMajorListByDepartmentId(Integer id) {
		return majorDAO.getMajorListByDepartmentId(id);
	}

	@Override
	public List<MajorListView> getMajorListViewListByDepartmentId(
			Integer departmentid) {
		// TODO Auto-generated method stub
		return this.majorListViewDAO
				.getMajorListViewListByDepartmentId(departmentid);
	}

	public MajorDetailView getMajorDetailViewByMajorid(Integer majorid) {
		return this.majorDetailViewDAO.getMajorDetailViewByMajorId(majorid);
	}

	public MajorDAO getMajorDAO() {
		return majorDAO;
	}

	public MajorDetailViewDAO getMajorDetailViewDAO() {
		return majorDetailViewDAO;
	}

	public void setMajorDetailViewDAO(MajorDetailViewDAO majorDetailViewDAO) {
		this.majorDetailViewDAO = majorDetailViewDAO;
	}

	public void setMajorDAO(MajorDAO majorDAO) {
		this.majorDAO = majorDAO;
	}

	public MajorListViewDAO getMajorListViewDAO() {
		return majorListViewDAO;
	}
	public void setMajorListViewDAO(MajorListViewDAO majorListViewDAO) {
		this.majorListViewDAO = majorListViewDAO;
	}
}
