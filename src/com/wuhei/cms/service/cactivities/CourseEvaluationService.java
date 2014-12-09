package com.wuhei.cms.service.cactivities;

import com.wuhei.cms.model.Cevaluation;

public interface CourseEvaluationService {
	
	/**
	 * 老师选择课程和学生后，查看学生信息以及他在本课程各项任务的得分
	 * @param coursedid
	 * @param studentid
	 * @return
	 */
	public Cevaluation getCevaluation(Integer courseid,Integer studentid);
	
	/**
	 * 老师选择课程和学生后，对学生进行总评
	 * @param courseid
	 * @param studentid
	 */
	public void newCevaluation(Cevaluation cevaluation) ;

	/**
	 * 修改课程总评
	 * @param id
	 * @param credit
	 */
	public void modifyCevalution(Integer id, Byte credit);
	
    /**
     * 更新课程总评的指标树
     * @param cevaluationListViews
     * @param studentid
     * @param courseid
     */
    public void updateCevaluation(Cevaluation cevaluation,Integer studentid,Integer courseid);
    
    /**
     * 获取到一名学生在对应课程的得分情况
     */
    public Cevaluation listEvaluationByCondition(Integer studentid,Integer courseid);

}
