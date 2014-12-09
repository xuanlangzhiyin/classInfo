package com.wuhei.cms.dao.cactivities;

import com.wuhei.cms.model.Cmreport;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * ���񱨸��dao��ӿ�
 * 
 */
public interface CmreportDAO {

	/**
	 * �����ݿ�������񱨸���Ϣ
	 * 
	 * @param cmreport
	 *            ��Ҫ��������񱨸���Ϣ
	 */
	public void insertCmreport(Cmreport cmreport);

	/**
	 * �������ݿ��е����񱨸���Ϣ
	 * 
	 * @param cmreport
	 *            ��Ҫ���µ����񱨸���Ϣ
	 */
	public void updateCmreport(Cmreport cmreport);

	/**
	 * �����ݿ�ɾ�����񱨸���Ϣ
	 * 
	 * @param cmreport
	 *            ��Ҫɾ�������񱨸��id
	 */
	public void deleteCmreport(Integer cmreportid);

	/**
	 * ��ѯһ���ض������������Ϣ
	 * 
	 * @param cmreport
	 *            ��Ҫ��ѯ�����񱨸��id
	 */
	public Cmreport getCmreport(Integer cmreportid);

	/**
	 * ��ѯ���񱨸�
	 * 
	 * @param isgroup
	 *            �Ƿ�С�鱨��
	 * @param cmgroupid
	 *            �ύ�������
	 * @param cstudentid
	 *            �ύ�����ѧ��
	 * @param cmissionid
	 *            ��������������
	 * @return ���񱨸�
	 */
	public Cmreport getCmreportByCondition(
			@Param(value = "isgroup") Boolean isgroup,
			@Param(value = "cmgroupid") Integer cmgroupid,
			@Param(value = "cstudentid") Integer cstudentid,
			@Param(value = "cmissionid") Integer cmissionid);
	
	public List<Cmreport> getCmreportListByCondition(
			@Param(value = "isgroup") Boolean isgroup,
			@Param(value = "cmgroupid") Integer cmgroupid,
			@Param(value = "cstudentid") Integer cstudentid,
			@Param(value = "cmissionid") Integer cmissionid);

	/**
	 * ��ѯ��ǰcmission�ύ����ѧ������
	 */

	public Integer countReportedStudent(
			@Param(value = "cmissionid") Integer cmissionid);

	/**
	 * author:czf
	 * �жϱ����ύѧ���Ƿ�Ϊ��ǰѧ��
	 */
	public Integer getStudentidByCMreportid(Integer cmreportid);
	
}