package com.wuhei.cms.dao.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Major;

public interface MajorDAO {

	/**
	 * ����ݿ����רҵ��Ϣ
	 * @param major
	 *   רҵ��ʵ��
	 */
	public void insertMajor(Major major);


	/**
	 * ����ݿ����רҵ��Ϣ
	 * @param major
	 *     רҵ��ʵ��
	 */
	public void updateMajor(Major major);


	/**
	 * ����ݿ�ɾ��רҵ��Ϣ
	 * @param id
	 * Ҫɾ���רҵ��id
	 */
	public void deleteMajor(Integer id);


	/**
	 * ����ݿ��ѯרҵ��Ϣ
	 * @param id  Ҫ��ѯ��רҵ��Ϣ��id
	 * @return ���ز�ѯ��רҵ
	 */
	public Major getMajor(Integer id);

	/**
	 * 
	 * @param departmentid
	 *            Ժϵid
	 * @param keyword
	 *            �ؼ���
	 * @return
	 */
	public List<Major> getMajorListByConditions(
			@Param(value = "departmentid") Integer departmentid,
			@Param(value = "keyword") String keyword);

	/**
	 * ���ԺϵId:departmentId���ظ�Ժϵ��ӵ�е�רҵ�б�
	 * @param id 
	 * @return
	 */
	public List<Major> getMajorListByDepartmentId(Integer id);
	


	/**
	 * ���ԺϵId:departmentId���ظ�Ժϵ��ӵ�е�רҵ�б�
	 * @param��ݴ��뷵��רҵ�б�  
	 * @return
	 */
	public List<Major> getMajorListByCode(    //String code,Integer departmentid);
	@Param(value = "departmentid") Integer departmentid,
	@Param(value = "code") String code);
	
	
	/**
	 * ��ȡ���е�רҵ�б�
	 * @return
	 */
	public List<Major> getMajorList();
	
	public Integer countMajorByConditions(
			@Param(value = "departmentid")Integer departmentid,
			@Param(value = "keyword")String keyword);
	
}