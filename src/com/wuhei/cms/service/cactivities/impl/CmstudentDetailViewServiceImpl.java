package com.wuhei.cms.service.cactivities.impl;

import java.util.List;

import com.wuhei.cms.dao.cactivities.CmreportDAO;
import com.wuhei.cms.dao.cactivities.CmstudentDetailViewDAO;

import com.wuhei.cms.model.Cmreport;
import com.wuhei.cms.model.joint.CmstudentDetailView;
import com.wuhei.cms.service.cactivities.CmstudentDetailViewService;

public class CmstudentDetailViewServiceImpl implements CmstudentDetailViewService {

	CmstudentDetailViewDAO cmstudentDetailViewDAO;
    CmreportDAO cmreportDAO;
	@Override
	public List<CmstudentDetailView> getCmstudentDetailListByCondition(
			Integer cstudentid,
			Integer courseid,
			Integer cmgroupid,
			Integer cmissionid,
			Boolean isinvolved)
 {
		List<CmstudentDetailView> CmstudentDetailViewList =  cmstudentDetailViewDAO.getCmstudentDetailListByCondition(cstudentid, courseid, cmgroupid, cmissionid, isinvolved,null);
		//设置用于前端显示的isinvolvedChinese
		for(int i=0;i<CmstudentDetailViewList.size();i++){
			if(CmstudentDetailViewList.get(i).getIsinvolved()){
				CmstudentDetailViewList.get(i).setIsinvolvedChinese("参与任务");
			}
			else{
				CmstudentDetailViewList.get(i).setIsinvolvedChinese("不参与任务");
			}
		}
		
		for(int i=0;i<CmstudentDetailViewList.size();i++){
			Cmreport cmreport=cmreportDAO.getCmreportByCondition(null,null, CmstudentDetailViewList.get(i).getCstudentid(), cmissionid);
			
			if(cmreport==null){
				CmstudentDetailViewList.get(i).setIsSubmitChinese("否");
			}
			else{
				CmstudentDetailViewList.get(i).setIsSubmitChinese("是");
			}
		}
		return CmstudentDetailViewList;
	}
	
	//getters and setters
	public CmstudentDetailViewDAO getCmstudentDetailViewDAO() {
		return cmstudentDetailViewDAO;
	}

	public void setCmstudentDetailViewDAO(
			CmstudentDetailViewDAO cmstudentDetailViewDAO) {
		this.cmstudentDetailViewDAO = cmstudentDetailViewDAO;
	}

	public CmreportDAO getCmreportDAO() {
		return cmreportDAO;
	}

	public void setCmreportDAO(CmreportDAO cmreportDAO) {
		this.cmreportDAO = cmreportDAO;
	}

}
