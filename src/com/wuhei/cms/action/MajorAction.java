package com.wuhei.cms.action;

import java.util.List;
import org.apache.log4j.Logger;
import com.wuhei.cms.model.Major;
import com.wuhei.cms.model.joint.MajorDetailView;
import com.wuhei.cms.model.joint.MajorListView;
import com.wuhei.cms.search.MajorSearchMeta;
import com.wuhei.cms.search.result.MajorPageResult;
import com.wuhei.cms.service.basic.MajorService;
import com.wuhei.cms.web.context.CmsWebContext;

/**
 * 
 * 专业管理｜基类
 * 
 * 
 */
@SuppressWarnings("serial")
public class MajorAction extends BaseAction {

	Logger log = Logger.getLogger(MajorAction.class);

	protected MajorService majorService;

	/**
	 * 页面输入参数－专业id
	 */
	protected Integer majorid;

	/**
	 * 页面输入参数-专业
	 */
	protected Major major;

	/**
	 * 返回页面结果－专业详细视图
	 */
	protected MajorDetailView majorDetailView;

	/**
	 * 返回页面结果－专业列表 前台分页
	 */
	protected List<MajorListView> majors;

	/**
	 * 页面输入参数－专业列表查询条件 暂不使用
	 */
	protected MajorSearchMeta majorSearchMeta;

	/**
	 * 返回页面结果－包含分页信息的学生列表数据 暂不使用
	 */
	protected MajorPageResult majorPageResult;

	/**
	 * 教员怨用户：查看本学院专业列表 后台分页
	 * 
	 * @return
	 */
	public String listMajor4Acamgr() {
		
		internalGetMajor();
		
		log.info("教务员用户:查询专业列表");
		
		return SUCCESS;

	}

	/**
	 * 教师用户：查看本学院专业列表 后台分页
	 * 
	 * @return
	 */
	public String listMajor4Teacher() {
		
		internalGetMajor();
		
		log.info("教师用户:查询专业列表");
		
		return SUCCESS;

	}

	/**
	 * 学生用户：查看本学院专业列表 后台分页
	 * 
	 * @return
	 */
	public String listMajor4Student() {

		internalGetMajor();
		
		log.info("学生用户:查询专业列表");

		return SUCCESS;

	}

	private void internalGetMajor() {
		// 获取当前用户的学院id
		Integer departmentid = CmsWebContext.getCurrentDepartmentId();
		
		// 默认查询条件
		Integer currentPage = 1;
		String keyword = null;
		// 获取页面输入查询条件
		if (majorSearchMeta != null) {
			currentPage = majorSearchMeta.getCurrentPage();
			keyword = this.getDefaultSearchValue(majorSearchMeta.getKeyword(),
					null);
		}

		// 获取查询结果
		majorPageResult = majorService.getMajorByConditions(departmentid,
				currentPage, keyword);

	}

	

	
	/*
	 * getters and setters
	 */

	public Integer getMajorid() {
		return majorid;
	}

	public void setMajorid(Integer majorid) {
		this.majorid = majorid;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public MajorDetailView getMajorDetailView() {
		return majorDetailView;
	}

	public void setMajorDetailView(MajorDetailView majorDetailView) {
		this.majorDetailView = majorDetailView;
	}

	public List<MajorListView> getMajors() {
		return majors;
	}

	public void setMajors(List<MajorListView> majors) {
		this.majors = majors;
	}

	public MajorSearchMeta getMajorSearchMeta() {
		return majorSearchMeta;
	}

	public void setMajorSearchMeta(MajorSearchMeta majorSearchMeta) {
		this.majorSearchMeta = majorSearchMeta;
	}

	public MajorPageResult getMajorPageResult() {
		return majorPageResult;
	}

	public void setMajorPageResult(MajorPageResult majorPageResult) {
		this.majorPageResult = majorPageResult;
	}

	public MajorService getMajorService() {
		return majorService;
	}

	public void setMajorService(MajorService majorService) {
		this.majorService = majorService;
	}

}
