package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Cstudent;


public interface CstudentDAO {
	/**
	 * ����ѡ��ѧ���¼
	 * 
	 * @param Cstudent
	 */
	public void insertCstudent(Cstudent cstudent);

	/**
	 * ����ѡ��ѧ���¼ ,�����cstudent��cgroupid==-1,��cgroupid��Ϊnull
	 * 
	 * @param Cstudent
	 */
	public void updateCstudent(Cstudent cstudent);

	/**
	 * ɾ��ѡ��ѧ���¼
	 * 
	 * @param id
	 */
	public void deleteCstudent(Integer id);

	/**
	 * ��ȡѡ��ѧ���¼
	 * 
	 * @param id
	 * @return
	 */
	public Cstudent getCstudent(Integer id);

	/**
	 * �õ�����ѡ��ѧ���¼��list
	 */
	public List<Cstudent> listCstudent();

	/**
	 * ���isgrouped,courseid,cgroupid�õ�List<Cstudent>
	 */
	public List<Cstudent> getCStudentListByCondition(
			@Param(value = "isgrouped") Boolean isgrouped,
			@Param(value = "courseid") Integer courseid,
			@Param(value = "cgroupid") Integer cgroupid,
			@Param(value = "studentid") Integer studentid);

	/**
	 * ��ݿγ̷����id�����¸ÿγ̷����е�����ѡ��ѧ����Ϣ������isgrouped�ֶ���Ϊfasle������cgroupid��Ϊnull
	 * ��ɾ��γ̷���ʱ����Ҫ�ı�ÿγ̷����С���Ա������Ӧ�ֶΣ���ʱ���õ�����ӿڣ��Ըı�cstudent����Ӧ�ֶ�
	 * 
	 * @param cgroupid
	 */
	public void updateCstudentToUngroupedByCgroupId(Integer cgroupid);

	/**
	 * ͨ��studentid��ȡ����cstudent���еĸ��� author:GongXiang
	 */
	public int countCstudentByStudentid(Integer cstudentid);

}
