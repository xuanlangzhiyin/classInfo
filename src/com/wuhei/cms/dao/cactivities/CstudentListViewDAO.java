package com.wuhei.cms.dao.cactivities;

import java.util.List;

//import javax.persistence.criteria.CriteriaBuilder.In;

import com.wuhei.cms.model.joint.CstudentListView;

public interface CstudentListViewDAO 
{
	public List<CstudentListView>getCstudentListViewByCourseid(Integer courseid);
}
