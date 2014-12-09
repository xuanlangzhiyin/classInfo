package com.wuhei.cms.service.cactivities;


import com.wuhei.cms.exception.AccessDeniedException;
import com.wuhei.cms.exception.FileProcessException;
import com.wuhei.cms.model.Cmreport;

public interface CmreportService {

	
	/**
	 * ����id��ѯ���񱨸�
	 * 
	 * @param id
	 * @return
	 */
	public Cmreport getCmreport(Integer id) throws FileProcessException;

	

	/**
	 * ����idɾ��һ��ָ�������񱨸�
	 * 
	 * @param id
	 */
	public void deleteCmreport(Integer id);

	/**
	 * ѧ���ύ���񱨸�
	 * 
	 * @param cmreport
	 */
	public void submitCmreport(Cmreport cmreport)
			throws AccessDeniedException;

	/**
	 * ѧ������id���񱨸�
	 * 
	 * @param id
	 * @return
	 */
	public Cmreport stuGetCmreport(Integer id);

	/**
	 * ���������õ�Cmreport��������ѧ���ϴ��ļ�ʱ��ȷ���Ƿ񸲸�ԭ�е�cmreport��¼��
	 * @param isgroup
	 * @param cstudentid
	 * @param cmgroupid
	 * @param cmissionid
	 * @return
	 */
	public Cmreport getCmreportByCondition(Boolean isgroup, Integer cstudentid, Integer cmgroupid, Integer cmissionid);
	

	/**
	 * ����Cmreport
	 * 
	 * @param cmreport
	 */
	public void updateCmreport(Cmreport cmreport);

	

	/**
	 * ҵ�����ƣ�����cmreport
	 * 
	 * ҵ�������������ݿ��в���һ��cmreport��¼
	 * 
	 * ʵ�ַ��������ݲ���cmreport,��"���񱨸�cmreport"���У�����cmreportʵ��
	 * 
	 * 
	 * @param cmreport��������cmreportʵ��
	 */
	public void insertCmreport(Cmreport cmreport);

	/**
	 * 
	 * �жϸñ����ѧ���Ƿ�Ϊ��ǰѧ��
	 */
	
	public Boolean isStudentAuthorizedOnCmreport(Integer cmreportid);
	
}
