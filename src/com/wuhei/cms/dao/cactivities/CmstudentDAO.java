package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Cmstudent;


public interface CmstudentDAO {
	/**
	 * �����������ѧ���¼
	 * 
	 * @param Cmstudent
	 */
	public void insertCmstudent(Cmstudent cmstudent);

	/**
	 * �����������ѧ���¼,�����Ҫ��cmgroupid�ֶ���Ϊnull����Ҫ��cmstudent�����е�cmgroupid=-1
	 * 
	 * @param Cmstudent
	 */
	public void updateCmstudent(Cmstudent cmstudent);

	/**
	 * ɾ���������ѧ���¼
	 * 
	 * @param id
	 */
	public void deleteCmstudent(Integer id);

	/**
	 * ��ȡ�������ѧ���¼
	 * 
	 * @param id
	 * @return
	 */
	public Cmstudent getCmstudent(Integer id);

	/**
	 * �õ������������ѧ���¼��list
	 */
	public List<Cmstudent> listCmstudent();

	/**
	 * ���isgrouped,cmissionid,cmgroupid�õ������������ѧ���¼��list
	 */
	public List<Cmstudent> getCmstudentListByCondition(
			@Param(value = "isgrouped") Boolean isgrouped,
			@Param(value = "cmissionid") Integer cmissionid,
			@Param(value = "cmgroupid") Integer cmgroupid,
			@Param(value = "cstudentid") Integer cstudentid);

	/**
	 * ��ݲ���ѡ��������������ѧ�� by zhuchengrong 
	 * @param isinvolved
	 * @param cmissionid
	 * @return
	 */
	public List<Cmstudent> getCmstudentListByCmissionidAndIsinvolved(@Param(value = "cmissionid") Integer cmissionid,
			                                                         @Param(value = "isinvolved") Boolean isinvolved);
	/**
	 * ��ɾ���������ʱ,��Ҫ������������ѧ��isgroup�ֶ�ֵ��Ϊfalse,cmgroupid�ֶ�ֵ��Ϊnull,
	 * isinvolved�ֶ�ֵ��Ϊfasle�� �ýӿڽ�ʵ�ָ��±�ɾ����������С���Ա�������Ϣ
	 * 
	 * @param cmgroupid�������id
	 */
	public void updateCmstudentToUngroupedByCmgroupId(Integer cmgroupid);
	
	/**
	 * ɾ��cmstudent���к�cmissionid��ص�����ѡ��ѧ��
	 */
	public void deleteCmstudentByCmissionid(Integer cmissionid);
	
	/**
	 * ͨ��cmissionid���س�studentid���жϲ�������ѧ���Ƿ�Ϊ��ǰѧ��
	 */

	public List<Integer> getStudentidsByCmissionid(Integer Cmissionid);
}
