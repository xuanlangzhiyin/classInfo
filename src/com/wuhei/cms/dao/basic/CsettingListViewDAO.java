package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.joint.CsettingListView;

public interface CsettingListViewDAO {

	/**
	 * ����רҵ�γ�ɸѡ�� �г�����������רҵ�γ��������Ϣ�� ɸѡ��������רҵId�� רҵ���ƹؼ���
	 * @param majorId
	 * @param keyword
	 * @return
	 */
	public List<CsettingListView> listCsettingListByConditions(
			@Param(value = "majorId") Integer majorId,
			@Param(value = "keyword") String keyword,
	        @Param(value = "start") Integer start,
	        @Param(value = "count") Integer count);
	
	public Integer countCsettingByConditions(
			@Param(value = "majorId") Integer majorId,
			@Param(value = "keyword") String keyword);
}
