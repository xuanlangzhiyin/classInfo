package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.exception.AccessDeniedException;
import com.wuhei.cms.exception.FileProcessException;
import com.wuhei.cms.model.Cmgroup;

import com.wuhei.cms.model.Cmstudent;
import com.wuhei.cms.model.joint.CmgroupDetailView;
import com.wuhei.cms.model.joint.CmstudentDetailView;

/**
 * �ṩ��"�������"���ҵ����õ�service��
 * 
 * 
 */
public interface CmgroupService {

	/**
	 * ҵ�����ƣ���ʾ�ɲ�ѡĳ�γ������������������б�
	 * 
	 * ҵ���������ڷ��������ʱ�������пγ̵ķ����б���ѡ����������С�飬Ҳ���Զ�С����е�����
	 * �γ̷����������Ӧ��֮����ʽȷ��������飬ȷ���ÿγ����������������Ҫ���Ŀγ������Ӧ����������б���ʾ����
	 * 
	 * ʵ�ַ�����������ѡ���ķֿγ�����id��cmissionid,�ڡ��������cmgroup�����в�ѯ�ɲ�ѡ�ÿγ��������������б���������������б�
	 * 
	 * @param cmissionid�γ�����id
	 * @return List<Cmgroup>��ʾ����������б�
	 */
	public List<Cmgroup> getCmgrouplist(Integer cmissionid);

	/**
	 * ҵ�����ƣ������������
	 * 
	 * ҵ��������������������顱����ʦ����������֣�Ĭ��Ϊ����+���ݿ��е��кţ���
	 * 
	 * ʵ�ַ����������������cgroup�����в����������ʵ��cmgroup�����⣬��"�������ѧ��cmstudent"���У����±�ѡ�е�ѧ������Ϣ��
	 * ��isgrouped�ֶ���Ϊtrue����cmgroupid��Ϊ�����������cmgroup��id��
	 * 
	 * @param cmgroup��Ҫ������������ʵ��
	 * @param cmstudentidList��Ҫ��ӵ�����������ѧ����id�б�
	 */
	public void newCmgroup(Cmgroup cmgroup, List<Integer> cmstudentidList);

	
	/**
	 * ��һ��ѧ�����Էֳ�һ��С��  by zhuchengrong
	 * @param cmgroup
	 * @param cmstudentid
	 */
	public void newCmgroupByOne(Cmgroup cmgroup, Integer cmstudentid);

	
	/**
	 * ҵ�����ƣ��鿴�������ʱ�г����г�Ա��Ϣ
	 * 
	 * ҵ������������������б�����Ӧ�ķ��飬���Բ鿴�÷���Ļ�����Ϣ�����г�Ա������Ϣ�����������ظ÷������г�Ա������Ϣ��
	 * 
	 * ʵ�ַ����������������id��cmgroupid,��"�������ѧ��cmstudent"���в�ѯ�÷��������ѧ����
	 * ��������鿴������������С���Ա������Ϣ�б�
	 * 
	 * @param cmgroupid����Ҫ�鿴���������id
	 * @return List<CmstudentDetailView>����鿴������������С���Ա������Ϣ�б�
	 */
	public List<CmstudentDetailView> getCmstudentDetailViewListByCmgroupid(
			Integer cmgroupid);

	/**
	 * ҵ�����ƣ��鿴�������ʱ�г�������������Ϣ
	 * 
	 * ҵ������������������б�����Ӧ�ķ��飬���Բ鿴�÷���Ļ�����Ϣ�����г�Ա������Ϣ�����������ظ÷��������Ϣ��
	 * 
	 * ʵ�ַ����������������id:cmgroupid���ӡ��������cmgroup�����в�ѯ�÷������Ϣ���ӡ��������ѧ�����в�ѯ�÷����е�ѧ����
	 * 
	 * @param cmgroupid��Ҫ�鿴����������id
	 * @return CmgroupDetailView���������ľ�����Ϣ
	 */
	public CmgroupDetailView getCmgroupDetailView(Integer cmgroupid);

	/**
	 * ҵ�����ƣ���ȡδ������������ѧ���б�
	 * 
	 * ҵ������������ѡ��ķ�������ӳ�Ա����ӵĳ�Ա�ӡ�δ���顱����ѡ��
	 * 
	 * ʵ�ַ���������Cmissionid�ڡ��������ѧ��cmstudent�����У� ��ѯ����δ�����ѧ���б�
	 * 
	 * @param cmissionid�γ�����id
	 * @return List<Cmstudent>����δ������������ѧ���б�
	 */
	public List<Cmstudent> getUngroupedStudent(Integer cmissionid);

	/**
	 * ҵ�����ƣ�����ογ�����Ĳ���ѧ��
	 * 
	 * ҵ������������ѡ��ķ�������ӳ�Ա����ӵĳ�Ա�ӡ�δ���顱����ѡ
	 * 
	 * ʵ�ַ�����ѡ��Ҫ��ӵ�ѧ�����޸ġ��������ѧ�����У�Ҫ��������ѧ����cmgroupid��
	 * 
	 * @param cmstudentid��Ҫ��ӵ��������ѧ��id
	 * @param cmgroupid�������id
	 * @throws FileProcessException
	 */
	public void addCmissionMember(List<Cmstudent> cmstudentList)
			throws AccessDeniedException;

	/**
	 * ҵ�����ƣ�ɾ���������С���Ա
	 * 
	 * ҵ������������ѡ��ѡ��ķ�����ɾ����Ա��ɾ���ĳ�Ա���롰δ���顱״̬��
	 * 
	 * ʵ�ַ������ڡ��������ѧ��cmstudent�����У�Ҫ�߳������ѧ��cmstudent��isgrouped�ֶ���Ϊfalse��
	 * cmgroupid�ֶ���Ϊnull��isinvolved�ֶ���Ϊfalse��
	 * ��"�������cmgroup"���У����¸�cmstudent������������count�ֶ�Ϊcount-1�� ��������У����ѧ�����鳤������ɾ��
	 * 
	 * @param cmstudentid��Ҫ���߳���������ѧ��id
	 */
	public void deleteCmgroupMember(Integer cmstudentid)
			throws AccessDeniedException;

	/**
	 * ҵ�����ƣ��޸��������
	 * 
	 * ҵ������������ѡ�е�������飬�޸����������Ϣ��
	 * 
	 * ʵ�ַ��������ݲ�����cmgroup,��"�������cmgroup"���У��޸���Ӧ����������¼
	 * 
	 * @param cmgroup�����޸ĵ��������
	 */
	public void modifyCmgroup(Cmgroup cmgroup)
			throws AccessDeniedException;

	/**
	 * ҵ�����ƣ�ɾ���������
	 * 
	 * ҵ��������ѡ�񡰷����б��еķ��飬���ɾ��������͸÷�����ص���Ϣ���÷����е�ѧ���Զ����롰δ���顱״̬
	 * 
	 * ʵ�ַ�����������Ҫ��ɾ�����������id,�����������ѧ��cmstudent�����е�isgrouped�ֶ�ֵ��Ϊfalse,
	 * cmgroupid�ֶ�ֵ��Ϊnull,isinvolved�ֶ�ֵ��Ϊfasle��
	 * ���⣬���ݲ���id��ɾ�����������cgroup�����еĶ�Ӧ��������¼��
	 * 
	 * @param id��Ҫɾ�����������id
	 */
	public void deleteCmgroup(Integer id) throws AccessDeniedException;

	/**
	 * ҵ�����ƣ�ȷ������γ�������������
	 * 
	 * ҵ��������ȷ�������������ӳ���ϵ,�޸Ĳ�����߲���������,ѡ��Ҫ��������ķ��飬ѡ����ϲ���ȷ�Ϻ��ڡ�������顱�У������������� �ķ���
	 * ���־λ��
	 * 
	 * ʵ�ַ��������ݲ�����������б�cmgroupList�������б��е�ÿ���������cmgroup: {����"�������ѧ��"�е�isinvolved
	 * 
	 * @param Cmgroup
	 *            [] cmgroupArray
	 */
	public void confirmCmgroup(List<Cmgroup> cmgroupList);

	/**
	 * ����cmgroupid����cmgroup����
	 * 
	 * @param cmgroupid
	 *            cmgroup.id
	 * @return
	 */
	public Cmgroup getCmgroupById(Integer cmgroupid);

	/**
	 * ҵ�����ƣ��鿴�������
	 * 
	 * ҵ��������ѧ���鿴�Լ�ѡ�������ķ������
	 * 
	 * ʵ�ַ������ڡ��������ѧ�������У���ѯ�͸�ѧ������ID��ͬ��ѧ������"�������"�У�select �÷������Ϣ
	 * 
	 * @param cmgroupid
	 *            ����鿴���������id
	 * @return List<Cmstudent> ��������������ѧ���б�
	 */
	public List<Cmstudent> getCmgroup(Integer cmgroupid);

	/**
	 * ҵ�����ƣ��鿴ָ��������δ����γ������С��
	 * 
	 * ҵ����������ʦ�ڽ����γ�����ġ�С������ʱ����һЩ������鱻ѡ�вμӣ�����һЩ�������δ�μӣ���ҵ���г���Щδ�μӵ���������б�
	 * 
	 * ʵ�ַ��������ݲ����γ�����id:cmissionid����"�������cmgroup"���У����Ҹ�cmissionid��Ӧ�µ���������б�
	 * ����������isinvolved = false��
	 * 
	 * @param cmissionid����Ҫ�鿴������id
	 * @return List<CmgroupDetailView>����鿴���������δ��������С�������Ϣ�б�
	 */
	public List<CmgroupDetailView> getNotinMCmgroupDetailViewListByCmissionid(
			Integer cmissionid);

	/**
	 * ҵ�����ƣ��鿴ָ�������²���γ������С��
	 * 
	 * ҵ����������ʦ�ڽ����γ�����ġ�С������ʱ����һЩ������鱻ѡ�вμӣ�����һЩ�������δ�μӣ���ҵ���г���Щ��ѡ�вμӵ���������б�
	 * 
	 * ʵ�ַ��������ݲ����γ�����id:cmissionid����"�������cmgroup"���У����Ҹ�cmissionid��Ӧ�µ���������б�
	 * ����������isinvolved = true��
	 * 
	 * @param cmissionid����Ҫ�鿴������id
	 * @return List<CmgroupDetailView>����鿴��������в�������С�������Ϣ�б�
	 */
	public List<CmgroupDetailView> getinMCmgroupDetailViewListByCmissionid(
			Integer cmissionid);

	/**
	 * by zhuchengrong
	 * @param cmissionid
	 * @return ��������б�
	 */
	public List<CmgroupDetailView> getCmgroupDetailViewListByCmissionid(
			Integer cmissionid);
	
	/**
	 * by zhuchengrong
	 * @param cmissionid
	 * @return ��������б�
	 */
	public List<CmgroupDetailView> getCmgroupDetailViewListByCmissionidIsmarked(
			Integer cmissionid);
	
	/**
	 * ҵ�����ƣ�ָ������С�����γ�����
	 * 
	 * ҵ����������ʦ�ڽ����γ�����ġ�С������ʱ����һЩ������鱻ѡ�вμӸ�С�����񣬱�ҵ��ʵ�ֽ���Щ��ѡ�е����������ӵ��γ�����
	 * 
	 * ʵ�ַ��������ݲ����������id�б�cmgroupids,����ÿ���������id:cmgroupid:
	 * {��"�������cmgroup"���в�ѯ��������飬���÷����isinvolved�ֶ���Ϊtrue��
	 * ���⣬Ҳ��Ҫ��"�������ѧ��cmstudent"���У�����cmgroupid����ֶ�
	 * �������ڸ���������ѧ���б����б��е�ÿ��ѧ����isinvolved�ֶ���Ϊtrue��}
	 * 
	 * @param cmgroupids�������id�б�
	 */
	public void addCmgrouptoMission(List<Integer> cmgroupids)
			throws AccessDeniedException;

	/**
	 * ҵ�����ƣ�ɾ������С�����γ�����
	 * 
	 * ҵ����������ʦ�ڽ����γ�����ġ�С������ʱ����һЩ������鱻ѡ�вμӸ�С������
	 * ����ʦ��Ҫ��ĳЩС��ȡ���������������Ҫ���Ѿ�����γ��������������Ϊδ�����������顣
	 * ��ҵ��ʵ�ֽ���Щ��Ҫ��ȡ���������������Ϊδ����ÿγ������״̬��
	 * 
	 * ʵ�ַ��������ݲ����������id�б�cmgroupids,����ÿ���������id:cmgroupid:
	 * {��"�������cmgroup"���в�ѯ��������飬���÷����isinvolved�ֶ���Ϊfalse��
	 * ���⣬Ҳ��Ҫ��"�������ѧ��cmstudent"���У�����cmgroupid����ֶ�
	 * �������ڸ���������ѧ���б����б��е�ÿ��ѧ����isinvolved�ֶ���Ϊfalse��}
	 * 
	 * @param cmgroupids�������id�б�
	 */
	public void removeCmgroupfromMission(List<Integer> cmgroupids)
			throws AccessDeniedException;

	/**
	 * ҵ�����ƣ�����Ϊ����ѧ���б�
	 * 
	 * ҵ������������cmissioid����δ������������ѧ��DetailView�б�
	 * 
	 * ʵ�ַ��������ݲ���cmissionid����"�������ѧ��cmstudent"���У���ѯcmissionid�ֶ��������ȣ�
	 * ��isgrouped�ֶ�Ϊfalse��ѧ���б���ȡ��DetailView�б����ء�
	 * 
	 * @param cmission�γ�����id
	 * 
	 * @return List<CmstudentDetailView>���ص�CmstudentDetailView�б�
	 */
	public List<CmstudentDetailView> getUngroupedCmstudentDetailViewListByCmissionid(
			Integer cmissionid);

	/**
	 * ҵ�����ƣ����ѧ����cmgroup
	 * 
	 * ҵ����������ʦ����ѡ��һ��cmgroup��Ȼ����cmgroup�����һЩ�������ѧ����
	 * 
	 * ʵ�ַ��������ݲ���cmgroupid����"�������cmgroup"���в�ѯ��Ӧ��cmgroup��
	 * �����cmgroup��iseditable�ֶε�ֵΪfalse�����׳��쳣��
	 * ���iseditable=true,����ڲ���cmstudentids�е�ÿ��ѧ����
	 * {��"�������ѧ��cmstudent"����,��isgrouped�ֶ���Ϊtrue,��isinvolved�ֶ���Ϊtrue��
	 * ��cmgroupid��Ϊ����cmgroupid,��cmissionid�ֶ���Ϊcmgroup�е�cmissionid}
	 * 
	 * @param cmstudentids��Ҫ����ӵ�cmstudentid�б�
	 * @param cmgroupid��Ҫ���ѧ����cmgroup
	 */
	public void addCmgroupMember(List<Integer> cmstudentids, Integer cmgroupid);

	/**
	 * ҵ�����ƣ���cmgrou�Ƴ�ѧ��
	 * 
	 * ҵ����������ʦ����ѡ��һ��cmgroup��Ȼ���Ƴ���cmgroup�еĲ���cmstudent��
	 * 
	 * ʵ�ַ��������ݲ���cmgroupid����"�������cmgroup"���в�ѯ��Ӧ��cmgroup��
	 * �����cmgroup��iseditable�ֶε�ֵΪfalse�����׳��쳣��
	 * ���iseditable=true,���ж�Ҫɾ������Ա���Ƿ����鳤��������鳤�����׳��鳤���ܱ�ɾ�����쳣��
	 * ����������鳤������ڲ���cmstudentids�е�ÿ��ѧ����
	 * {��"�������ѧ��cmstudent"����,��isgrouped�ֶ���Ϊfalse,��isinvolved�ֶ���Ϊfalse��
	 * ��cmgroupid��Ϊnull��}
	 * 
	 * @param cmstudentids��Ҫ���Ƴ���cmstudentid�б�
	 * @param cmgroupid��Ҫ�Ƴ�ѧ����cmgroup
	 */
	public void deleteCmgroupMembers(List<Integer> cmstudentids,
			Integer cmgroupid);

	/**
	 * ҵ�����ƣ�ɾ������  by zhuchengrong
	 * 
	 * @param cmgroupids
	 */
	public void deleteCmgroups(List<Integer> cmgroupids);
	
	/**
	 * ҵ�����ƣ���Ӳ�������С��
	 * 
	 * @param cmgroupids
	 */
	public void addCmgroup (Cmgroup cmgroup);
}