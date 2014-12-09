package com.wuhei.cms.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.wuhei.cms.model.Ccategory;
import com.wuhei.cms.model.Csetting;
import com.wuhei.cms.model.deletedata.CsettingDeleteResult;
import com.wuhei.cms.model.joint.CsettingDetailView;
import com.wuhei.cms.model.joint.CsettingListView;
import com.wuhei.cms.search.CsettingSearchMeta;
import com.wuhei.cms.search.result.CsettingPageResult;
import com.wuhei.cms.service.basic.CcategoryService;
import com.wuhei.cms.service.basic.CsettingService;

/**
 * 专业管理｜专业课程
 * 
 * Major Course Setting Action
 * 
 * 
 */
@SuppressWarnings("serial")
public class McsettingAction extends MajorAction {

	Logger log = Logger.getLogger(McsettingAction.class);

	/**
	 * 专业课程管理－业务操作接口
	 */
	protected CsettingService csettingService;

	protected CcategoryService ccategoryService;

	/**
	 * 页面输入参数－专业课程id
	 */
	protected Integer csettingid;

	/**
	 * 页面输入参数－专业课程
	 */
	protected Csetting csetting;

	/**
	 * 返回页面结果－专业课程详细视图
	 */
	protected CsettingDetailView csettingDetailView;

	/**
	 * 返回页面结果－专业课程列表 前台分页
	 */
	protected List<CsettingListView> csettings;

	/**
	 * 页面输入参数－专业课程列表查询条件 条件： 后台分页使用
	 */
	protected CsettingSearchMeta csettingSearchMeta;

	/**
	 * 返回页面结果－包含分页信息的专业课程列表数据 后台分页使用
	 */
	protected CsettingPageResult csettingPageResult;
	/**
	 * 返回给前台课程类型，供前台选择
	 */
	protected List<Ccategory> ccategories;

	/**
	 * 页面输入参数-要删除的专业课程的id
	 */
	protected List<Integer> csettingId;

	/**
	 * 返回页面结果-删除失败的课程的信息
	 */
	protected CsettingDeleteResult csettingDeleteResult;

	/**
	 * 系统用户: 查看本专业的专业课程 (教务员) 后台分页
	 * 
	 * @return /acamgr/listCsetting.jsp
	 */
	public String listCsetting4Acamgr() {

		internalGetCsettings();

		log.info("教务员用户：查询专业课程列表");

		return SUCCESS;
	}

	/**
	 * 系统用户: 查看本专业的专业课程 (教师) 后台分页
	 * 
	 * @return /teacher/listCsetting.jsp
	 */
	public String listCsetting4Teacher() {

		internalGetCsettings();

		log.info("教师用户：查询专业课程列表");

		return SUCCESS;
	}

	/**
	 * 系统用户: 查看本专业的专业课程 (学生) 后台分页
	 * 
	 * @return /student/listCsetting.jsp
	 */
	public String listCsetting4Student() {

		internalGetCsettings();

		log.info("学生用户：查询专业课程列表");

		return SUCCESS;
	}

	private void internalGetCsettings() {
		// 默认查询条件
		Integer currentPage = 1;
		String keyword = null;

		// 获取页面输入查询条件
		if (csettingSearchMeta != null) {
			currentPage = csettingSearchMeta.getCurrentPage();
			keyword = this.getDefaultSearchValue(
					csettingSearchMeta.getKeyword(), null);
		}

		// majorid: 页面传递参数－MajorAction.majorid
		// 从数据库获取符合条件专业课程
		csettingPageResult = csettingService.listCsettingListByConditions(
				majorid, keyword, currentPage);
	}

	/**
	 * 系统用户：查看专业课程
	 * 
	 * @return
	 */
	public String viewCsetting() {
		try {

			csettingDetailView = csettingService
					.getCsettingDetailView(csettingid);

			ccategories = ccategoryService.getCcategoryList();

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

		return SUCCESS;
	}

	

	/**
	 * 教务员: 进入编辑专业课程
	 * @return
	 */
	public String editCsetting4Acamgr() {
		try {

			csettingDetailView = csettingService
					.getCsettingDetailView(csettingid);

			ccategories = ccategoryService.getCcategoryList();

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

		return SUCCESS;
	}

	/**
	 * 教务员: 保存专业课程
	 * 
	 * @return
	 */
	public String saveCsetting() {
		try {
			// 页面输入csettingDetailview--csetting
			csettingService.updateCsetting(csetting);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 教务员: 删除专业课程 
	 * 
	 * @return
	 */
	public String deleteCsetting() {
		if (csettingId == null){
		}
		else {
			csettingDeleteResult = csettingService.deleteCsetting(csettingId);
		}
		return SUCCESS;
	}

	
	/*
	 * getters and setters
	 */



	public CsettingService getCsettingService() {
		return csettingService;
	}

	public void setCsettingService(CsettingService csettingService) {
		this.csettingService = csettingService;
	}

	public Integer getCsettingid() {
		return csettingid;
	}

	public void setCsettingid(Integer csettingid) {
		this.csettingid = csettingid;
	}

	public Csetting getCsetting() {
		return csetting;
	}

	public void setCsetting(Csetting csetting) {
		this.csetting = csetting;
	}

	public CsettingDetailView getCsettingDetailView() {
		return csettingDetailView;
	}

	public void setCsettingDetailView(CsettingDetailView csettingDetailView) {
		this.csettingDetailView = csettingDetailView;
	}

	public List<CsettingListView> getCsettings() {
		return csettings;
	}

	public void setCsettings(List<CsettingListView> csettings) {
		this.csettings = csettings;
	}

	public CsettingSearchMeta getCsettingSearchMeta() {
		return csettingSearchMeta;
	}

	public void setCsettingSearchMeta(CsettingSearchMeta csettingSearchMeta) {
		this.csettingSearchMeta = csettingSearchMeta;
	}

	public CsettingPageResult getCsettingPageResult() {
		return csettingPageResult;
	}

	public void setCsettingPageResult(CsettingPageResult csettingPageResult) {
		this.csettingPageResult = csettingPageResult;
	}

	public CcategoryService getCcategoryService() {
		return ccategoryService;
	}

	public void setCcategoryService(CcategoryService ccategoryService) {
		this.ccategoryService = ccategoryService;
	}

	public List<Ccategory> getCcategories() {
		return ccategories;
	}

	public void setCcategories(List<Ccategory> ccategories) {
		this.ccategories = ccategories;
	}

	public List<Integer> getCsettingId() {
		return csettingId;
	}

	public void setCsettingId(List<Integer> csettingId) {
		this.csettingId = csettingId;
	}

	public CsettingDeleteResult getCsettingDeleteResult() {
		return csettingDeleteResult;
	}

	public void setCsettingDeleteResult(
			CsettingDeleteResult csettingDeleteResult) {
		this.csettingDeleteResult = csettingDeleteResult;
	}

}
