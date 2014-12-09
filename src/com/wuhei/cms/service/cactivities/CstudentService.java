package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.model.Cstudent;

import com.wuhei.cms.model.deletedata.CstudentDeleteResult;
import com.wuhei.cms.model.joint.CstudentDetailView;
import com.wuhei.cms.model.joint.CstudentListView;

/**
 * �ṩ��Cstudent����ҵ��������õ�service��
 * 
 */
public interface CstudentService {

	/**
	 * ҵ�����ƣ������γ̷���ѧ��id��ȡ�γ̷���ѧ��
	 * 
	 * @param id�γ̷���ѧ��id
	 * @return Cstudent���صĿγ̷���ѧ��ʵ��
	 */
	public Cstudent getCstudentById(Integer id);

	/**
	 * ҵ�����ƣ����ݿγ̷���ѧ��id��ȡ�γ̷���ѧ��������ϢCstudentDetailViewʵ��
	 * 
	 * @param id�γ̷���ѧ��id
	 * @return CstudentDetailView���ص�CstudentDetailViewʵ��
	 */
	public CstudentDetailView getCstudentDetailViewById(Integer id);
	
	/**
	 * ��ѡ������Ŀγ�id ��ѡ��ѧ�������ҵ�ѡ�˸ÿγ̵�ѧ��
	 * @param courseid
	 * @return
	 */
	public List<Cstudent> getCstudentList(int courseid);
	
	/**
	 * ��ѡ������Ŀγ�id �ҵ�ѡ�˸ÿγ̵�ѧ����ϸ��Ϣ
	 * @param courseid
	 * @return
	 */
	public List<CstudentDetailView> getCstudentDetailViewList(int courseid);
	
	/**
	 * ��ѡ������Ŀγ�id �ҵ�ѡ�˸ÿγ̵�ѧ����ϸ��Ϣ
	 * @param courseid
	 * @return
	 */
	public List<CstudentListView> getCstudentListView(int courseid);
	
	
	
	
	
	/**
	 * ����ѡ��ѧ��
	 * @param cstudent
	 */
	public void newCstudent(Cstudent cstudent);
	

	/**
	 * ɾ��ѡ��ѧ��
	 * @param cstudentid
	 */
	public void deleteCstudent(Integer cstudentid);
	
	/**
	 * ����ѡ��ѧ��
	 * @param cstudentid
	 */
	public void updateCstudent(Cstudent cstudent);
	
	
	/**
	 * ����ѡ��ѧ����id�б�����ɾ��
	 * @return
	 */
	public CstudentDeleteResult deleteCstudentByCondition(List<Integer> sourceCstudentid);
	

}