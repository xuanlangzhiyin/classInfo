package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.model.Cmcredit;
import com.wuhei.cms.model.joint.CmcreditDetailView;
import com.wuhei.cms.model.joint.CmcreditListView;

public interface CmcreditService {

	/**
	 * ҵ�����ƣ�����cmreportid���ҵ������񱨸�����Ӧ�����������б�
	 * 
	 * ҵ���������������񱨸��id�鿴�����񱨸������ĸ��˻���С������������б�
	 * 
	 * ʵ�ַ��������ݲ���cmreportid����"���񱨸�cmreport"���У�ȡ����cmreport,�ж��ֶ�isgroup�Ƿ�Ϊ�գ�
	 * ���isgroup�ֶ�Ϊ�գ�����������񱨸��Ǹ�������
	 * �ɵ���DAO��Ľӿ�getCmcreditDetailViewsByCmreportidForPesonal,
	 * �õ����ύѧ������������cmcredit�� ���isgroup�ֶβ�Ϊ�գ�����������񱨸���С������
	 * �ɵ���DAO��Ľӿ�getCmcreditDetailViewsByCmreportidForCmgroup,
	 * �õ��ύ�����񱨸��������������С���Ա��cmredit�б�
	 * 
	 * @param cmgroupid����С���id
	 * @return list<CmcreditDetailView>���������б�
	 */
	public List<CmcreditDetailView> getCmcreditDetailViewsByCmreportid(
			Integer cmreportid);
	
	/**
	 * ҵ�����ƣ�����cmgroupid���ҵ�������С������Ӧ�����������б�
	 * 
	 * ҵ����������������С���id�鿴������С������������б�
	 * 
	 * ʵ�ַ��������ݲ���cmgroupid����"��������cmcredit"���У�ȡ��cmcredit,
	 * �ɵ���DAO��Ľӿ�getCmcreditDetailViewsByCmgroupid,
	 * �õ���������������С���Ա��cmredit�б�
	 * 
	 * @param cmgroupid����С���id
	 * @return list<CmcreditDetailView>���������б�
	 */
	public List<CmcreditDetailView> getCmcreditDetailViewsByCmgroupid(
			Integer cmgroupid);

	/**
	 * ҵ�����ƣ�������������id��ȡ��������
	 * 
	 * ҵ����������Ҫͨ��ĳ���������ֵ�id��ȡ���������ֵ���Ϣ
	 * 
	 * ʵ�ַ��������ݲ���cmcreditid����"��������cmcredit"���У���ѯ����������cmcredit,�����ء�
	 * 
	 * @param cmcreditid��������id
	 * @return Cmcredit���ص���������
	 */
	public Cmcredit getCmcreditById(Integer cmcreditid);

	/**
	 * ҵ�����ƣ�����Cmcredit
	 * 
	 * ҵ����������Ҫ��ĳ���������ֵ����ݽ��и���
	 * 
	 * ʵ�ַ��������ݲ���cmcredit,��"��������cmcredit"���У�����DAO���updateCmcredit���������¸��������֡�
	 * 
	 * @param cmcredit������µ���������ʵ��
	 */
	public void updateCmcredit(Cmcredit cmcredit);

	/**
	 * ҵ�����ƣ�����Cmcredit
	 * 
	 * ҵ����������Ҫ�����ݿ��в���һ����������
	 * 
	 * ʵ�ַ��������ݲ�����cmcredit,��"��������cmcredit"���У�����DAO���insertCmcredit������������������֡�
	 * 
	 * @param cmcredit����������������ʵ��
	 */
	public void insertCmcredit(Cmcredit cmcredit);
	
	/**
	 * ҵ�����ƣ�ͨ��cstudengid ��  cmissionid ����ѧ��ĳ���������ϸ����
	 * 
	 */
	public CmcreditDetailView getCmcreditDetailViewByCondition(Integer cstudentid, Integer cmissionid);
	
	/**
	 * ͨ���γ�courseid��cstudentid���һ��ѧ����һ�ſγ��вμӵ���������÷����
	 * @param cstudentid
	 * @param courseid
	 * @return
	 */
	public List<CmcreditListView> getCmcreditListViewsByCondition(Integer cstudentid, Integer courseid);

}
