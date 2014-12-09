package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Cgroup;


public interface CgroupDAO {

	/**
	 * ����һ���γ̷���
	 * @param cgroup �γ̷���ʵ��
	 */
    public void insertCgroup(Cgroup cgroup);
	
    /**
     * ����һ���γ̷���
     * @param cgroup �γ̷���ʵ��
     */
	public void updateCgroup(Cgroup cgroup);
	
	/**
	 * ɾ��һ���γ̷���
	 * @param Cgroupid �γ̷���id
	 */
	public void deleteCgroup(Integer id);
	
	/**
	 * ͨ��id��ȡ�γ̷���
	 * @param id�γ̷���id
	 * @return Cgroup�γ̷���
	 */
	public Cgroup getCgroup(Integer id);
	
	/**
	 * ��ȡ���пγ̷����
	 * @return �γ̷���list
	 */
	public List<Cgroup> getCgroupList();	
	
	/**
	 * ����ָ��courseid�Ŀγ̷����б�
	 * @param courseid
	 * @return �γ̷���list
	 */
	public List<Cgroup> getCgroupListByCondition(Integer courseid);
	
	/**
	 * ����ָ��id�Ŀγ̷����鳤id
	 * @param id
	 * @return �鳤id
	 */
	public int getCgroupLeader(Integer id);
	
	/**
	 * �޸Ŀγ̷�������
	 * @param id Ҫ�޸ĵķ���id
	 * @param count Ҫ�޸ĵ���Ա��
	 */
	public void updateCgroupCount(
			@Param(value = "id") Integer id ,
			@Param(value = "count") Integer count);
	
	/**
	 * �޸Ŀγ̷����鳤
	 * @param id Ҫ�޸��鳤�Ŀγ̷���id
	 * @param studentid  �鳤id
	 */
	public void updateCgroupLeader(
			@Param(value = "id") Integer id ,
			@Param(value = "studentid") Integer studentid);
	
	/**
	 * �޸Ŀγ̷�������
	 * @param id Ҫ�޸��鳤�Ŀγ̷���id
	 * @param name ��������
	 */
	public void updateCgroupName(
			@Param(value = "id") Integer id,
			@Param(value = "name") String name);

	


}