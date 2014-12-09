package com.wuhei.cms.service.cactivities.impl;

import java.util.ArrayList;
import java.util.List;

import com.wuhei.cms.dao.cactivities.CteacherDAO;
import com.wuhei.cms.model.Cteacher;
import com.wuhei.cms.service.cactivities.CteacherService;
import com.wuhei.cms.web.context.CmsWebContext;

public class CteacherServiceImpl implements CteacherService {

	public CteacherDAO cteacherDAO;

	
	public void insertCteacher(Cteacher cteacher) {

		cteacherDAO.insertCteacher(cteacher);
	}


	public void updateCteacher(Cteacher cteacher) {
		cteacherDAO.updateCteacherByCourseid(cteacher);
	}

	
	public void deleteCteacher(Integer courseid) {
		cteacherDAO.deleteCteacherByCourseid(courseid);
	}

	public List<Cteacher> getCteacherByCourseid(Integer courseid) {

		return cteacherDAO.getCteacherByCourseid(courseid);

	}

	/**
	 *判断当前用户（教师）是否为该课程的开课教师
	 */

	public Boolean isTeacherAuthorizedOnCourse(Integer courseid) {
		
		Boolean isAuthorized = false;
		
		Integer teacherid = CmsWebContext.getCurrentUser().getTeacherid();
				
		List<Integer> teacherids = new ArrayList<Integer>();
		teacherids = cteacherDAO.getTeacheridsByCourseid(courseid);
				
		if (teacherids == null || teacherids.isEmpty()) {
			return isAuthorized;
		}
		
		for(int i=0;i<teacherids.size();i++){
			if(teacherid.equals(teacherids.get(i))){
				isAuthorized = true;
			    return isAuthorized;
			}
		}
		return isAuthorized;
	}

	/*
	 * getter and setter
	 */
	public CteacherDAO getCteacherDAO() {
		return cteacherDAO;
	}

	public void setCteacherDAO(CteacherDAO cteacherDAO) {
		this.cteacherDAO = cteacherDAO;
	}
}
