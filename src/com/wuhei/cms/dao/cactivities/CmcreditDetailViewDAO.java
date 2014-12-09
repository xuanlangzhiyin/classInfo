package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.wuhei.cms.model.joint.CmcreditDetailView;

public interface CmcreditDetailViewDAO {

	/**
	 * �ڸ��������У�������񱨸�cmreport��id,��ȡ�ύ�ߵ���������cmctedit�����ء�
	 * 
	 * @param cmreportid���񱨸�cmreport��id
	 * @return CmcreditDetailVie>��ȡ����������credit
	 */
	public List<CmcreditDetailView> getCmcreditDetailViewsByCmreportidForPesonal(
			Integer cmreportid);

	/**
	 * ��С�������У�������񱨸�cmreport��id,��ȡ��С�����������cmctedit�б?���ء�
	 * 
	 * @param cmreportid���񱨸�cmreport��id
	 * @return CmcreditDetailVie>��ȡ����������credit�б�
	 */
	public List<CmcreditDetailView> getCmcreditDetailViewsByCmreportidForCmgroup(
			Integer cmreportid);
	
	/**
	 * ��С�������У�������񱨸�cmgroup��id,��ȡ��С�����������cmctedit�б?���ء�
	 * 
	 * @param cmgroupid����С��cmgroup��id
	 * @return <CmcreditDetailView>��ȡ����������credit�б�
	 */
	public List<CmcreditDetailView> getCmcreditDetailViewsByCmgroupid(
			Integer cmgroupid);
	
	/**
	 * ͨ��cstudentid �� cmissioid���ظ�ѧ��ķ���
	 * @param cstudentid
	 * @param cmissionid
	 * @return
	 */
	public CmcreditDetailView getCmcreditDetailViewByCondition(
			@Param(value = "cstudentid")Integer cstudentid,
			@Param(value = "cmissionid")Integer cmissionid);
}
