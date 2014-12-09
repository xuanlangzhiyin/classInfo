package com.wuhei.cms.service.basic;

import java.util.List;

import com.wuhei.cms.model.Major;
import com.wuhei.cms.model.joint.MajorDetailView;
import com.wuhei.cms.model.joint.MajorListView;
import com.wuhei.cms.search.result.MajorPageResult;

public interface MajorService {

	/**
	 * ҵ������:��ø�Ժϵ�µ�����רҵid������
	 * ҵ������:����ѧ��ʱ����Ҫѡ���ѧ��������רҵ��ʹ������������
	 * 
	 * @param id
	 * @return
	 */
	public List<Major> getMajorListByDepartmentId(Integer id);
	
	public List<MajorListView> getMajorListViewListByDepartmentId(Integer departmentid);
	
	public MajorPageResult getMajorByConditions(Integer departmentid, Integer currentPage, String keyword);
	/**
	 * ͨ��excel����������רҵ
	 */


	/**
	 * ϵͳ�û�������id����רҵ����ϸ��Ϣ
	 *
	 */
	public MajorDetailView getMajorDetailViewByMajorid(Integer majorid);


}
