package com.wuhei.cms.service.cactivities;

import java.util.List;


import com.wuhei.cms.model.joint.CmstudentDetailView;



public interface CmstudentDetailViewService {

	/**
	 * 
	 * ��ȡѧ������������ϸ��Ϣ
	 * @param cstudentid ѧ��id
	 * @param courseid  �γ�id
	 * @param cmgroupid ��id
	 * @param cmissionid ����id
	 * @param isinvolved �Ƿ��������
	 * @return
	 */
	public List<CmstudentDetailView> getCmstudentDetailListByCondition(
			Integer cstudentid, Integer courseid, Integer cmgroupid, Integer cmissionid, Boolean isinvolved);
}
