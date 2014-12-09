package com.wuhei.cms.service.cactivities;

import java.util.List;

import com.wuhei.cms.model.Cgroup;

import com.wuhei.cms.model.joint.CgroupDetailView;
import com.wuhei.cms.model.joint.CstudentDetailView;

/**
 * �ṩ��"�γ̷���"���ҵ����õ�service�� 
 * 
 * 
 */
public interface CgroupService {

	/**
	 * ҵ�����ƣ��鿴�γ̷����б�
	 * 
	 * ҵ��������������γ̷��顱���γ̷���ͨ���б���ʾ���������б�������ͬһҳ����Բ鿴���飬ɾ�����飬����γ̷��顣
	 * 
	 * ʵ�ַ��������ݲ�������γ�id:courseid,�ӡ��γ̷���cgroup�����в�ѯ�ÿ���γ̵����пγ̷����б��г��γ̷����б�
	 * 
	 * @param Courseid����鿴�Ŀ���γ�id
	 * @return List<CgroupDetailView>�ÿ���γ̵����пγ̷���detailView�б�
	 */
	public List<CgroupDetailView> getCgroupDetailViewListByCourseid(Integer courseid);

	/**
	 * ҵ�����ƣ������γ̷���
	 * 
	 * ҵ��������������������顱����ʦ����������֣�Ĭ��Ϊ����+���ݿ��е��кţ������ѧ����ѡ���鳤��
	 * 
	 * ʵ�ַ�������"�γ̷���course"�в���γ̷���ʵ���� ���⣬��"ѡ��ѧ��cstudent"���У����±�ѡ������ѧ������Ϣ,
	 * ��isgrouped�ֶ���Ϊtrue����cgroupid��Ϊ�����γ̷���cgroup��id��
	 * 
	 * @param cgroup��Ҫ������Ŀγ̷���ʵ��
	 * @param studentidList��Ҫ��ӵ��÷����ѧ����id�б�
	 */
	public void newCgroup(Cgroup cgroup, List<Integer> cstudentidList);

	/**
	 * �Ե���ѧ�����з���
	 * @param cgroup   by zhuchengrong
	 * @param cstudentid
	 */
	public void newCgroupByOne(Cgroup cgroup,Integer cstudentid);
	
	/**
	 * ҵ�����ƣ��鿴�γ̷���ʱ�г����г�Ա��Ϣ
	 * 
	 * ҵ������������������б�����Ӧ�ķ��飬���Բ鿴�÷���Ļ�����Ϣ�����г�Ա������Ϣ���������������г�Ա������Ϣ
	 * 
	 * ʵ�ַ�����ѡ����飨cgroupid��������cgroupid�ӡ�ѡ��ѧ��cstudent�����в�ѯ�÷����е�ѧ����
	 * ��������鿴�γ̷��������С���Ա������Ϣ�б�
	 * 
	 * @param cgroupid����Ҫ�鿴�Ŀγ̷���id
	 * @return List<CstudentDetailView> ����鿴�γ̷��������С���Ա������Ϣ�б�
	 */
	public List<CstudentDetailView> getCstudentDetailViewListByCgroupid(
			Integer cgroupid);

	/**
	 * ҵ�����ƣ��鿴�γ̷���ʱ�г����������Ϣ
	 * 
	 * ҵ������������������б�����Ӧ�ķ��飬���Բ鿴�÷���Ļ�����Ϣ�����г�Ա������Ϣ�����������ظ÷��������Ϣ��
	 * 
	 * ʵ�ַ�����ѡ����飨cgroupid��������cgroupid�ӡ��γ̷���cgroup�����в�ѯ�÷����еĻ�����Ϣ��
	 * 
	 * @param cgroupid�γ̷���id
	 * @return Cgroup�γ̷��������Ϣ
	 */
	public CgroupDetailView getCgroupDetailViewByCgroupid(Integer cgroupid);

	/**
	 * ҵ�����ƣ���ȡδ�����ѡ��ѧ��DetailView�б�
	 * 
	 * ҵ������������ѡ��ķ�������ӳ�Աʱ����ӵĳ�Ա�ӡ�δ���顱����ѡ�����ѯ��δ�����ѡ��ѧ���б�
	 * 
	 * ʵ�ַ������ӡ�ѡ��ѧ��cstudent���в�ѯisgrouped=0��δ����ѡ��ѧ��,��ͨ�������ѯ��ȡ��Щѧ���ľ�����Ϣ��
	 * ����δ�����ѧ����DetailView�б�
	 * 
	 * @param courseid����γ�id
	 * @returns List<StudentDetailView>δ�����ѧ����DetailView�б�
	 */
	public List<CstudentDetailView> getUngroupedCstudentDetailViewListByCourseid(
			Integer courseid);

	/**
	 * ҵ�����ƣ���ӿγ̷���С���Ա
	 * 
	 * ҵ������������ѡ��Ŀγ̷��������С���Ա����ӵĳ�Ա�ӡ�δ���顱��ѡ��ѧ������ѡ��
	 * 
	 * ʵ�ַ�����������Ҫ����ӵ�С���Աid�б�cstudentidList,�޸ġ�ѡ��ѧ��cstudent�����б�����γ̷����С���Ա��Ϣ��
	 * ��isgrouped�ֶ���Ϊtrue����cgroupid��Ϊ�����γ̷���id��ֵ��cgroupid��
	 * ���⣬��Ҫ���ݿγ̷����id:cgroupid,����"�γ̷���cgroup"���еĿγ̷�����Ϣ������ѧ������count�ֶε�ֵ��
	 * �����"�γ̷���"��leaderidΪnull���ӽ�ȥ��ѧ���б��У���һ��Ĭ��ѡΪ�鳤������Ϊleaderid
	 * 
	 * @param cstudentidList��ӽ���γ̷����ѧ��id�б�
	 * @param cgroupid�γ̷���id
	 */
	public void addCgroupMember(List<Integer> cstudentidList, Integer cgroupid);

	/**
	 * ҵ�����ƣ�ɾ���γ̷���С���Ա
	 * 
	 * ҵ������������ѡ��ѡ��Ŀγ̷�����ɾ����Ա��ɾ���ĳ�Ա���롰δ���顱״̬��
	 * 
	 * ʵ�ַ�����������Ҫ��ɾ����С���Աid�б�cstudentidList,�޸ġ�ѡ��ѧ��cstudent�����б�ɾ����С���Ա����Ϣ��
	 * ��isgrouped�ֶ���Ϊfasle����cgroupid��Ϊnull��
	 * ���⣬��Ҫ���ݿγ̷����id:cgroupid,����"�γ̷���cgroup"���еĿγ̷�����Ϣ������ѧ������count�ֶε�ֵ��
	 * ɾ���ĳ�Աid�����в��ܰ����鳤id�����ʣ�µĳ�Ա������0��
	 * 
	 * @param cstudentidList��Ҫ�ӿγ̷�����ɾ����ѧ��id�б�
	 * @param cgroupid�γ̷���id
	 */
	public void deleteCgroupMembers(List<Integer> cstudentidList, Integer cgroupid);

	/**
	 * ҵ�����ƣ�ɾ���γ̷���С���Ա
	 * 
	 * ҵ������������ѡ��ѡ��Ŀγ̷�����ɾ����Ա��ɾ���ĳ�Ա���롰δ���顱״̬��
	 * 
	 * ʵ�ַ�����������Ҫ��ɾ����С���Աid�б�cstudentidList,�޸ġ�ѡ��ѧ��cstudent�����б�ɾ����С���Ա����Ϣ��
	 * ��isgrouped�ֶ���Ϊfasle����cgroupid��Ϊnull��
	 * ���⣬��Ҫ���ݿγ̷����id:cgroupid,����"�γ̷���cgroup"���еĿγ̷�����Ϣ������ѧ������count�ֶε�ֵ��
	 * ɾ���ĳ�Աid�����в��ܰ����鳤id�����ʣ�µĳ�Ա������0��
	 *  
	 * @param cstudentid
	 * @param cgroupid
	 */
	public void deleteCgroupMember(Integer cstudentid, Integer cgroupid);
	
	
	/**
	 * ҵ�����ƣ���ȡѡ��ѧ���б�
	 * 
	 * ҵ���������г��ÿ���γ̵�����ѡ��ѧ���б�
	 * 
	 * ʵ�ַ�����ͨ������γ�id����"ѡ��ѧ��cstudent"���У���ȡ�ÿ���γ̵�����ѡ��ѧ��DetailView�б�
	 * 
	 * @param courseid����γ�id
	 * @return List<CstudentDetailView>����ѡ��ѧ���б�
	 */
	public List<CstudentDetailView> getCstudentDetailViewListByCourseid(Integer courseid);

	/**
	 * ҵ�����ƣ���������
	 * 
	 * ҵ���������Ա�ѡ��ķ�������
	 * 
	 * ʵ�ַ�����������Ҫ�������Ŀγ̷���id,�޸ġ��γ̷���cgroup�����еķ�������name�ֶΡ�
	 * 
	 * @param id��Ҫ�������Ŀγ̷���id
	 * @param name�µĿγ̷�������
	 */
	public void modifyCgroupName(Integer id, String name);

	/**
	 * ҵ�����ƣ����ÿγ̷�����鳤
	 * 
	 * ҵ��������ѡ���ض�����Ŀγ̷��飬���øÿγ̷�����鳤���鳤ֻ�ܴӸ÷������Ա��ѡ��
	 * 
	 * ʵ�ַ���������cgroupid�����ҿγ̷��飬Update"�γ̷���cgroup"���е�leaderid�ֶ�
	 * 
	 * @param cgroupid �γ̷���id
	 * @param leaderid ѡ����鳤��id
	 */
	public void modifyCgroupLeader(Integer cgroupid, Integer leaderid);

	/**
	 * ҵ�����ƣ�ɾ���γ̷���
	 * 
	 * ҵ��������ѡ�񡰷����б��еķ��飬���ɾ��������͸÷�����ص���Ϣ���÷����е�ѧ���Զ����롰δ���顱״̬
	 * 
	 * ʵ�ַ���������Ҫɾ���Ŀγ̷��飨id��,����ѡ��ѧ��cstudent������isgrouped��Ϊfalse,cgroupid=null��
	 * ���⣬��"�γ̷���cgroup"����ɾ���÷��顣
	 * 
	 * @param id����Ҫɾ���Ŀγ̷����id
	 */
	public void deleteCgroup(Integer id);

	/**
	 * ҵ�����ƣ�����ɾ���γ̷���
	 * 
	 * ҵ��������ѡ�񡰷����б��еĶ�����飬���ɾ��������͸÷�����ص���Ϣ���÷����е�ѧ���Զ����롰δ���顱״̬
	 * 
	 * ʵ�ַ���������ÿ��Ҫɾ���Ŀγ̷��飨id��,����ѡ��ѧ��cstudent������isgrouped��Ϊfalse,cgroupid=null��
	 * ���⣬��"�γ̷���cgroup"����ɾ���÷��顣
	 * 
	 * @param List<Integer> 
	 *        idList����Ҫɾ���Ŀγ̷����id
	 */
	public void deleteCgroups(List<Integer> idList);
	
	/**
	 * ҵ�����ƣ��Զ����ɵ��˿γ̷���
	 * 
	 * ҵ����������δ�����ѧ���ֳ�һ��һ��
	 * 
	 * ʵ�ַ��������ݿ���γ�id��ֵcourseid,��"ѡ��ѧ��cstudent"���в�ѯ�ÿ���γ̵�����ѡ��ѧ���б�
	 * ���ڸÿ���γ̵�����ѡ��ѧ���б�ÿ��ѡ��ѧ����
	 * �����γ̷���cgroup���в���һ���·��飬��������name��Ϊ��ѧ��������"name+��",count��Ϊ1,
	 * �鳤leaderid��Ϊ��ѡ��ѧ��id,courseid��Ϊ����courseid
	 * ���޸ġ�ѡ��ѧ��cstudent����,����ѡ��ѧ����isgrouped��Ϊtrue��cgroupid��Ϊ�����·���ʱ����ȡ��������id��
	 * 
	 * @param courseid��Ҫ�����ֶη���Ŀ���γ�id
	 */
	public void autoGrouping(Integer courseid);

}