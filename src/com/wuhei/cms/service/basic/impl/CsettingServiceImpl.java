package com.wuhei.cms.service.basic.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.wuhei.cms.dao.basic.CcategoryDAO;
import com.wuhei.cms.dao.basic.CsettingDAO;
import com.wuhei.cms.dao.basic.CsettingDetailViewDAO;
import com.wuhei.cms.dao.basic.CsettingListViewDAO;
import com.wuhei.cms.dao.cactivities.CourseDAO;
import com.wuhei.cms.model.Csetting;
import com.wuhei.cms.model.deletedata.CsettingDeleteResult;
import com.wuhei.cms.model.joint.CsettingDetailView;
import com.wuhei.cms.model.joint.CsettingListView;
import com.wuhei.cms.search.result.CsettingPageResult;
import com.wuhei.cms.search.result.PageResult;
import com.wuhei.cms.service.BaseServiceImpl;
import com.wuhei.cms.service.basic.CsettingService;

public class CsettingServiceImpl extends BaseServiceImpl implements
		CsettingService {

	Logger log = Logger.getLogger(CsettingServiceImpl.class);

	private CsettingDAO csettingDAO;
	private CsettingListViewDAO csettingListViewDAO;
	private CsettingDetailViewDAO csettingDetailViewDAO;

	
	private CcategoryDAO ccategoryDAO;

	private CourseDAO courseDAO;

	@Override
	public Csetting getCsetting(Integer csettingid) {
		return csettingDAO.getCsetting(csettingid);
	}

	@Override
	public List<Csetting> getCsettingList() {

		return csettingDAO.getCsettingListByConditions(null, null);
	}

	@Override
	public List<Csetting> getCsettingListByConditions(String type,
			String keyword) {

		return csettingDAO.getCsettingListByConditions(type, keyword);
	}


	@Override
	public CsettingPageResult listCsettingListByConditions(Integer majorId,
			String keyword, Integer currentPage) {
		// �ܼ�¼����
		Integer totalCount = this.csettingListViewDAO
				.countCsettingByConditions(majorId, keyword);

		// ��ȡ��ҳ��
		Integer totalPage = PageResult.computeTotalPage(totalCount,
				PageResult.PAGE_COUNT);

		// ��ȡ�Ϸ��ĵ�ǰҳ��
		currentPage = PageResult.getLegalCurrentPage(totalPage, currentPage);

		Integer start = (currentPage - 1) * PageResult.PAGE_COUNT;

		// ����ݿ��в���
		List<CsettingListView> csettings = this.csettingListViewDAO
				.listCsettingListByConditions(majorId, keyword, start,
						PageResult.PAGE_COUNT);

		// ���÷��ؽ��
		CsettingPageResult csettingPageResult = new CsettingPageResult();

		csettingPageResult.setCurrentPage(currentPage);

		csettingPageResult.setTotalCount(totalCount);

		csettingPageResult.setTotalPage(totalPage);

		// ����֪ͨ���
		csettingPageResult.setCsettings(csettings);

		return csettingPageResult;

	}

	@Override
	public void insertCsetting(Csetting csetting) {

		csettingDAO.insertCsetting(csetting);
	}



	@Override
	public void deleteCsetting(Integer id) {

		csettingDAO.deleteCsetting(id);
	}

	

	@Override
	public void updateCsetting(Csetting csetting) {

		csettingDAO.updateCsetting(csetting);
	}

	
	@Override
	public CsettingDetailView getCsettingDetailView(Integer id) {
		CsettingDetailView csettingDetailView = csettingDetailViewDAO
				.getCsettingDetailView(id);
		return csettingDetailView;
	}

	@Override
	public List<CsettingDetailView> getCsettingDetailViewList(Integer majorId) {
		return csettingDetailViewDAO.getCsettingDetailViewList(majorId);
	}

	// getter and setter

	public CcategoryDAO getCcategoryDAO() {
		return ccategoryDAO;
	}

	public void setCcategoryDAO(CcategoryDAO ccategoryDAO) {
		this.ccategoryDAO = ccategoryDAO;
	}

	public CsettingDAO getCsettingDAO() {
		return csettingDAO;
	}

	public void setCsettingDAO(CsettingDAO csettingDAO) {
		this.csettingDAO = csettingDAO;
	}


	public CsettingDetailViewDAO getCsettingDetailViewDAO() {
		return csettingDetailViewDAO;
	}

	public void setCsettingDetailViewDAO(
			CsettingDetailViewDAO csettingDetailViewDAO) {
		this.csettingDetailViewDAO = csettingDetailViewDAO;
	}

	public CsettingListViewDAO getCsettingListViewDAO() {
		return csettingListViewDAO;
	}

	public void setCsettingListViewDAO(CsettingListViewDAO csettingListViewDAO) {
		this.csettingListViewDAO = csettingListViewDAO;
	}


	/**
	 * ����ɾ��רҵ�γ� 
	 */
	public CsettingDeleteResult deleteCsetting(List<Integer> csettingId) {
		int csettingid;
		int csettingcount;
		Csetting csetting;
		CsettingDeleteResult csettingDeleteResult = new CsettingDeleteResult();
		List<Csetting> errorCsetting = new ArrayList<Csetting>();

		Integer errCount = Integer.valueOf(0);
		Integer successCount = Integer.valueOf(0);

		for (int i = 0; i < csettingId.size(); i++) {
			csettingid = csettingId.get(i);
			csettingcount = courseDAO.countCourseByCsettingid(csettingid);

			if (csettingcount != 0) {
				csetting = csettingDAO.getCsetting(csettingid);
				errorCsetting.add(csetting);
				errCount++;
				continue;
			} else {
				try {
					csettingDAO.deleteCsetting(csettingid);
				} catch (Exception e) {
					csetting = csettingDAO.getCsetting(csettingid);
					errorCsetting.add(csetting);
					errCount++;
					continue;
				}
			}
			successCount++;
		}
		csettingDeleteResult.setErrorCount(errCount);
		csettingDeleteResult.setSuccessCount(successCount);
		csettingDeleteResult.setErrorCsetting(errorCsetting);
		return csettingDeleteResult;
	}

	public CourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	// getter and setter

}
