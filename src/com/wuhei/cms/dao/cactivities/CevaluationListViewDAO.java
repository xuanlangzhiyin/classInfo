package com.wuhei.cms.dao.cactivities;

import java.util.List;

import com.wuhei.cms.model.joint.CevaluationListView;

public interface CevaluationListViewDAO 
{
	public List<CevaluationListView>listEvaluationListViewByCsettingid(Integer csettingid);
}
