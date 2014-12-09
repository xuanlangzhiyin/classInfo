package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.exception.AccessDeniedException;
import com.wuhei.cms.model.Cmcredit;
import com.wuhei.cms.model.Cmission;
import com.wuhei.cms.model.Cmstudent;
import com.wuhei.cms.model.Cstudent;
import com.wuhei.cms.model.joint.CmissionListView;

public interface CmissionService {

	/**
	 * �����ڿν�ʦid�Ϳγ�id��ѯ���и���ʦ�ڸÿγ̵�����
	 * 
	 * @param teacherid
	 * @param Courseid
	 * @return
	 */
	public List<Cmission> getCmissionList(Integer teacherid, Integer courseid);

	/**
	 * ��ʦ��ѯ�γ̻�б�
	 * 
	 * @param courseid
	 * @return
	 */
	public List<CmissionListView> getCmissionList4Teacher(Integer courseid);

	/**
	 * ѧ����ѯcmission�б�
	 */

	public List<CmissionListView> getCmissionList4Student(Integer courseid,Integer cstudentId);

	/***
	 * ͨ��courseid��cstuentId�ҵ�ѧ���μӵ���ʦδ���ֵ������б�
	 * @param courseid
	 * @param cstudentId
	 * @return
	 */
	public List<Cmission>getUnmarkedCmissionListByCondition4Student(Integer cstudentId,Integer courseid);
	
	
	/**
	 * ҵ�����ƣ��½�һ���γ�����cmission
	 * 
	 * ҵ����������ʦ��Ҫ�½�һ���γ�����д����������ύ������γ�����Ӧ����С������
	 * 
	 * ʵ�ַ�����
	 * 
	 * ��"�γ�����cmission"���в���γ�����cmission��
	 * 
	 * ���ݲ����γ�����ʵ����cmission,ȡ�����е�courseid,
	 * ����courseid��"ѡ��ѧ��cstudent"���в�ѯ�ÿγ̵�����ѡ��ѧ���б�cstudentList,
	 * ����courseid��"�γ̷���cgroup"���в�ѯ�ÿγ̵����пγ̷���cgroupList��
	 * 
	 * �����ѷ����cmstudent�к���cmgroupid�������������cmgroupid�ֶ��Ȳ�Ҫ���õ�cmstudent�У�
	 * Ȼ����cmstudent���в����Ӧ��cstudentList�б��е����ݡ�
	 * ��һ���棬����cmgroup�к���leaderid�������������leaderid�ֶ��Ȳ�Ҫ���õ�cmgroup�У�
	 * Ȼ����cmgroup���в����Ӧ��cgroupList�б��е����ݡ�
	 * 
	 * ��󣬸���һ���Ĳ������ɲ��ҵ���Ӧ��cmgroupid��
	 * ����cmstudent���е�cmgroupid�ֶΣ���������cmstudent���е���Ϣ��
	 * 
	 * ͬ������һ���Ĳ���֮�󣬿ɲ��ҵ���Ӧ��Ŀ���ֶ�ֵleaderid��
	 * ����cmgroup���е�leaderid�ֶΣ���������cmgroup���е���Ϣ��
	 * 
	 * 
	 * @param cmission�����½��Ŀγ�����cmissionʵ��
	 */
	public void addCmission(Cmission cmission);
	
	/**
	 * ��������Cmission���轫cstudent��ӵ�cmstudent
	 */
	
	public void addCmission4person(Cmission cmission);
	
	
	/**
	 * ɾ��һ������
	 * 
	 * @param id
	 */
	public void deleteCmission(Integer id) throws AccessDeniedException;

	/**
	 * �޸�һ������
	 * 
	 * @param cmission
	 */
	public void modifyCmission(Cmission cmission);

	/**
	 * ����student.id��isinvolved, courseid��cmstudent cmission�в�ѯ��ѧ������Ŀγ�����cmission
	 * �ڱ�(cmcredit)�в鿴����������
	 * 
	 * @param cmstudentid
	 * @return
	 */
	public List<Cmission> stuGetCmissionList(Integer cmstudentid);

	/**
	 * ����id��ѯ�γ�������Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public Cmission getCmission(Integer cmissionid);

	/**
	 * ������������ѧ�� ���ڸ���ѧ���Ĳ���״̬
	 * 
	 * @param id
	 */
	public void update(List<Cmstudent> cmstudentList);

	/**
	 * ����ѧ��studentid�Ϳγ�courseid ��ȡ��ѧ���ڿγ��еĶ��� Cstudent
	 * 
	 * @param studentid
	 *            ѧ����student���е�����
	 * @param courseid
	 *            ����γ� course���е�����
	 * @return
	 */
	public Cstudent getCStudentByCondition(Integer studentid, Integer courseid);
	
	
	public List<CmissionListView> getCmissionListViewByCondition(Integer courseid,String mtype);
	
	/**
	 * �����ڵ�cmissionid����Ҫ�л��������wantedCmissionid���룬����Ҫ�л��������С��ģ���л�����ǰ����С��
	 * @param cmissionid
	 * @param wantedCmission
	 * @return
	 */
	
	public void changeGroupToMissionGroup(Integer cmissionid,Integer wantedCmission);
	
	public void changeGroupToCourseGroup (Integer cmissionid,Integer courseid);
	
	/**
	 * ��������
	 * @param cmission  by zhuchengrong
	 */
	public void updateCmission(Cmission cmission);
	
	/**
	 * ҵ�����ƣ��ύѧ�������������
	 * 
	 * ҵ����������Ҫ��cmcredit�в���һ����¼,����cmstudent,cmission��Ϣ
	 * 
	 * by zhuchengrong
	 * @param cmstudent
	 * @param cmcredit
	 */
	public void savePersonalMissionScore(Cmstudent cmstudent,Cmcredit cmcredit);
	
	
	
}
