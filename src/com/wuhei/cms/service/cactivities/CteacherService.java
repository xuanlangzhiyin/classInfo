package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.model.Cteacher;

public interface CteacherService {


	public void insertCteacher(Cteacher cteacher);
	

	public void updateCteacher(Cteacher cteacher);
	

	public void deleteCteacher(Integer courseid);
	

	public List<Cteacher> getCteacherByCourseid(Integer courseid);
	

	/**
    * �жϵ�ǰ�û�����ʦ���Ƿ�Ϊ�ÿγ̵Ŀ��ν�ʦ
    */
	 public Boolean isTeacherAuthorizedOnCourse(Integer courseid);
}
