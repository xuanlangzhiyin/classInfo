package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.exception.AccessDeniedException;
import com.wuhei.cms.model.Cmstudent;
import com.wuhei.cms.model.joint.CmstudentDetailView;


/**
 * �ṩ��Cstudent����ҵ��������õ�service��
 * 
 * 
 */
public interface CmstudentService {

	/**
	 * ҵ�����ƣ������������ѧ��id��ȡ�������ѧ��
	 * 
	 * ҵ��������ͨ���������ѧ��id����ȡ���������ѧ����ʵ���������ء�
	 * 
	 * ʵ�ַ��������ݲ���:�������ѧ��id,��"�������ѧ��cmstudent"���в��Ҷ�Ӧ��cmstudentʵ���������ء�
	 * 
	 * @param id�������ѧ��id
	 * @return Cmstudent���ص��������ѧ��ʵ��
	 */
	public Cmstudent getCmstudentById(Integer id);

	/**
	 * ҵ�����ƣ������������ѧ��id��ȡCmstudentDetailViewʵ��
	 * 
	 * ҵ�������������������ѧ��id,��ȡ���������ѧ������Ӧ���������ѧ��������ϢCmstudentDetailView��������
	 * 
	 * ʵ�ַ��������ݲ����������ѧ��id:cmstudentid�����Ҹ�cmstudentid����Ӧ��cmstudentDetailView��������
	 * 
	 * @param id�������ѧ��id
	 * @return CmstudentDetailView���ص�CmstudentDetailViewʵ��
	 */
	public CmstudentDetailView getCmstudentDetailViewByIdAndCmissionId(Integer id);

	/**
	 * ҵ�����ƣ������������id:cmgroupid��ø÷����Cmstudent��list
	 * 
	 * 
	 * 
	 * @param cmgroupid
	 * @return
	 */
	public List<Cmstudent> getCmstudentListByCmgroupid(Integer cmgroupid);

	/**
	 * ҵ�����ƣ�����ѡ��ѧ��id:cstudentid �� �γ�����id:cmissionid����ȡ������������Ӧcmstudent����
	 * 
	 * ҵ�������� ���Ի�ȡ��¼�û��ڸ������е�cmstudent����,
	 * ���û���¼��ͨ�����ҵ���ȡ��ѧ���û���ĳcmissionid�γ������µ�cmstudentʵ����
	 * 
	 * ʵ�ַ��������ݲ���:cstudentid��cmissionid����"�������ѧ��cmstudent"���У�
	 * ��ѯcstudentid�ֶ�ֵ�Լ�cmissionid�ֶ�ֵ���������ȵ�cmstudent��¼�������ء�
	 * ����һ��ѡ��ѧ��cstudente��һ���γ�����cmission��ֻ��һ��ʵ��cmstudent,��������¿α�֤��ѯ���Ψһ��
	 * 
	 * @param cstudentidѡ��ѧ��id
	 * @param cmissionid�γ�����id
	 * @return Cmstudent��ѧ���û�����Ӧ��cmstudentʵ��
	 */
	public Cmstudent getCmstudentByCondition(Integer cstudentid,
			Integer cmissionid) throws AccessDeniedException;
	
	/**
	 * ����cmissionid  ѡ���μӸÿε�����ѡ��ѧ��
	 */
	public List<Cmstudent> getCmstudentListByCmissionid(Integer cmissionid);
	
	
	/**
	 * ���һ�����������ѧ��
	 */
	public void addCmstudent(Cmstudent cmstudent);
	
	/**
	 * ���¿γ�����ѧ����Ϣ
	 * author��zhuchengrong
	 */
	public void updateCmstudent(Cmstudent cmstudent);
	
	/**
	 * ѡ��������������ѧ��
	 * @param cmissionid
	 * @param isinvolved
	 */
	public List<Cmstudent> getCmstudentListByCmissionidAndIsinvolved(Integer cmissionid,Boolean isinvolved);
	
	
	/**
	 * �жϲ���γ������ѧ���Ƿ������ǰѧ��
	 */

	public Boolean isStudentAuthorizedOnCmission(Integer cmissionid);
}