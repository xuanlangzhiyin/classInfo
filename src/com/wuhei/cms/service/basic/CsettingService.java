package com.wuhei.cms.service.basic;

import java.util.List;

import com.wuhei.cms.model.Csetting;
import com.wuhei.cms.model.deletedata.CsettingDeleteResult;
import com.wuhei.cms.model.joint.CsettingDetailView;
import com.wuhei.cms.search.result.CsettingPageResult;

public interface CsettingService {

	/**
	 * ����id �ҳ����пγ����û�����Ϣ
	 * 
	 * @param csettingid
	 * @return
	 */
	public Csetting getCsetting(Integer csettingid);

	/**
	 * �γ������б�
	 * 
	 * ���пγ����ò������г����пγ����û�����Ϣ
	 * 
	 * @return
	 */
	public List<Csetting> getCsettingList();

	/**
	 * ��������רҵ�γ��б� ����רҵ�γ�ɸѡ���г�����������רҵ�γ��������Ϣ��ɸѡ��������רҵId�����ƹؼ���
	 * 
	 * @param MajorId
	 *            רҵid ����ɸѡ����רҵ�γ�
	 * @param keyword
	 *            רҵ�γ����ƹؼ���
	 * @return ���ط���������רҵ�γ���Ϣ�б�
	 */
	public List<Csetting> getCsettingListByConditions(String type,
			String keyword);

	/**
	 * ��������רҵ�γ��б� ����רҵ�γ�ɸѡ���г�����������רҵ�γ��������Ϣ��ɸѡ��������רҵId�����ƹؼ���
	 * 
	 * @param MajorId
	 *            רҵid ����ɸѡ����רҵ�γ�
	 * @param keyword
	 *            רҵ�γ����ƹؼ���
	 * @return ���ط���������רҵ�γ���Ϣ�б�
	 */
	public CsettingPageResult listCsettingListByConditions(Integer majorId,
			String keyword, Integer currentPage);

	/**
	 * �����γ�����
	 * 
	 * @param csetting
	 */
	public void insertCsetting(Csetting csetting);

	

	/**
	 * ɾ���γ����� 
	 * 
	 * ɾ��һ���γ�����
	 * 
	 * @param id
	 */
	public void deleteCsetting(Integer id);

	
	/**
	 * �޸Ŀγ����� �Կγ����ý����޸�
	 * 
	 * @param csetting
	 */
	public void updateCsetting(Csetting csetting);

	

	/**
	 * �鿴�γ�������ϸ��Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public CsettingDetailView getCsettingDetailView(Integer id);

	/**
	 * �鿴ĳרҵ�µ����пγ�������ϸ��Ϣ
	 * 
	 * @param majorId
	 * @return
	 */
	public List<CsettingDetailView> getCsettingDetailViewList(Integer majorId);


	/**
	 * ����ɾ��רҵ�γ� 
	 * 
	 * @param csettingid
	 * @return
	 */
	public CsettingDeleteResult deleteCsetting(List<Integer> csettingId);

}
