package com.wuhei.cms.dao.basic;

import java.util.List;

import com.wuhei.cms.model.joint.CsettingDetailView;



public interface CsettingDetailViewDAO {

	/**
	 * �鿴�γ�������ϸ��Ϣ
	 * @param id
	 * @return
	 */
	public CsettingDetailView getCsettingDetailView(Integer id);
	
	public List<CsettingDetailView> getCsettingDetailViewList(Integer majorId);
}
