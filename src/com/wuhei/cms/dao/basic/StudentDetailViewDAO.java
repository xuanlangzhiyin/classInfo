package com.wuhei.cms.dao.basic;

import com.wuhei.cms.model.joint.StudentDetailView;

public interface StudentDetailViewDAO {

	/**
	 * ��ȡѧ����ϸ��ϢStudentDetailView
	 * 
	 * @param idҪ��ѯ��ϸ��Ϣ��ѧ��id
	 * @return ����ѧ����ϸ��Ϣ��ʵ��
	 */
	public StudentDetailView getStudentDetailViewById(Integer id);

}