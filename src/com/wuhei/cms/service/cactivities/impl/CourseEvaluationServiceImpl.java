package com.wuhei.cms.service.cactivities.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuhei.cms.dao.cactivities.CevaluationDAO;
import com.wuhei.cms.dao.cactivities.CevaluationListViewDAO;
import com.wuhei.cms.dao.cactivities.CourseDAO;
import com.wuhei.cms.model.Cevaluation;
import com.wuhei.cms.service.cactivities.CourseEvaluationService;

public class CourseEvaluationServiceImpl implements CourseEvaluationService{
	
	private CevaluationDAO cevaluationDAO;
	private CourseDAO courseDAO;

	private CevaluationListViewDAO cevaluationListViewDAO;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Cevaluation getCevaluation(Integer courseid, Integer studentid) {  
		
		return cevaluationDAO.getCevaluationByCondition(courseid, studentid);
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void newCevaluation(Cevaluation cevaluation) {	
		cevaluationDAO.insertCevaluation(cevaluation);		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void modifyCevalution(Integer id, Byte credit) {
		Cevaluation cevaluation=new Cevaluation();
		cevaluation.setId(id);
		cevaluation.setCredit(credit);
		cevaluationDAO.updateCevaluation(cevaluation);
		
	}

	 public void updateCevaluation(Cevaluation cevaluation,Integer studentid,Integer courseid)
	 {
		
			 Cevaluation tempCevaluation=cevaluationDAO.getCevaluationByCondition(courseid, studentid);
			
			 if(tempCevaluation==null)
			 {
				 cevaluationDAO.insertCevaluation(cevaluation);
			 }
			
			 else
			 {
				cevaluation.setId(tempCevaluation.getId());
				cevaluationDAO.updateCevaluation(cevaluation);
			 }			 
		
		 
	 }
	 
	 
	 public Cevaluation listEvaluationByCondition(Integer studentid,Integer courseid)
	 {
		
			 Cevaluation cevaluation=cevaluationDAO.getCevaluationByCondition(courseid, studentid);
			
		
		 return cevaluation;
	 }

	 
	 
	public CevaluationDAO getCevaluationDAO() {
		return cevaluationDAO;
	}

	public void setCevaluationDAO(CevaluationDAO cevaluationDAO) {
		this.cevaluationDAO = cevaluationDAO;
	}

	public CourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	

	public CevaluationListViewDAO getCevaluationListViewDAO() {
		return cevaluationListViewDAO;
	}

	public void setCevaluationListViewDAO(
			CevaluationListViewDAO cevaluationListViewDAO) {
		this.cevaluationListViewDAO = cevaluationListViewDAO;
	}
	
	

}
