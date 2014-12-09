package com.wuhei.cms.dao.cactivities;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.Cevaluation;

/**
 *
 */
public interface CevaluationDAO {
	
	/**
	 * ����һ���γ�����
	 * @param cevaluation �γ�����
	 */
    public void insertCevaluation(Cevaluation cevaluation);
	
    /**
     *����һ���γ�����
     * @param cevaluation �γ�����
     */
	public void updateCevaluation(Cevaluation cevaluation);
	
	/**
	 * ɾ��һ���γ�����
	 * @param Cevaluationid �γ�����
	 */
	public void deleteCevaluation(Integer id);
	
	/**
	 * �������пγ�����
	 * @return List<Cevaluation> ���пγ�����
	 */
	public List<Cevaluation> getCevaluationList();
	
	/**
	 * ���ָ���������ҿγ�������
	 * @param courseid �γ�id
	 * @param studentid ѧ��id
	 * @param ceindexid ����ָ����
	 * @return
	 */
	public Cevaluation getCevaluationByCondition(
			@Param(value = "courseid") Integer courseid,
			@Param(value = "studentid") Integer studentid);

	/**
	 * �޸�ָ���γ����۵÷�
	 * @param id Ҫ�޸ĵĿγ�����
	 * @param credit Ҫ�޸ĵĵ÷�
	 */
	public void updateCevaluationCredit(
			@Param(value = "id") Integer id,
			@Param(value = "credit") Byte credit);

}