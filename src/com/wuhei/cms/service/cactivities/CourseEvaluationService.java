package com.wuhei.cms.service.cactivities;

import com.wuhei.cms.model.Cevaluation;

public interface CourseEvaluationService {
	
	/**
	 * ��ʦѡ��γ̺�ѧ���󣬲鿴ѧ����Ϣ�Լ����ڱ��γ̸�������ĵ÷�
	 * @param coursedid
	 * @param studentid
	 * @return
	 */
	public Cevaluation getCevaluation(Integer courseid,Integer studentid);
	
	/**
	 * ��ʦѡ��γ̺�ѧ���󣬶�ѧ����������
	 * @param courseid
	 * @param studentid
	 */
	public void newCevaluation(Cevaluation cevaluation) ;

	/**
	 * �޸Ŀγ�����
	 * @param id
	 * @param credit
	 */
	public void modifyCevalution(Integer id, Byte credit);
	
    /**
     * ���¿γ�������ָ����
     * @param cevaluationListViews
     * @param studentid
     * @param courseid
     */
    public void updateCevaluation(Cevaluation cevaluation,Integer studentid,Integer courseid);
    
    /**
     * ��ȡ��һ��ѧ���ڶ�Ӧ�γ̵ĵ÷����
     */
    public Cevaluation listEvaluationByCondition(Integer studentid,Integer courseid);

}
