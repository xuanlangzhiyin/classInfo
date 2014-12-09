package com.wuhei.cms.service.cactivities;


import com.wuhei.cms.exception.AccessDeniedException;
import com.wuhei.cms.exception.FileProcessException;
import com.wuhei.cms.model.Cmreport;

public interface CmreportService {

	
	/**
	 * 根据id查询任务报告
	 * 
	 * @param id
	 * @return
	 */
	public Cmreport getCmreport(Integer id) throws FileProcessException;

	

	/**
	 * 根据id删除一条指定的任务报告
	 * 
	 * @param id
	 */
	public void deleteCmreport(Integer id);

	/**
	 * 学生提交任务报告
	 * 
	 * @param cmreport
	 */
	public void submitCmreport(Cmreport cmreport)
			throws AccessDeniedException;

	/**
	 * 学生根据id任务报告
	 * 
	 * @param id
	 * @return
	 */
	public Cmreport stuGetCmreport(Integer id);

	/**
	 * 根据条件拿到Cmreport对象，用于学生上传文件时，确定是否覆盖原有的cmreport记录。
	 * @param isgroup
	 * @param cstudentid
	 * @param cmgroupid
	 * @param cmissionid
	 * @return
	 */
	public Cmreport getCmreportByCondition(Boolean isgroup, Integer cstudentid, Integer cmgroupid, Integer cmissionid);
	

	/**
	 * 更新Cmreport
	 * 
	 * @param cmreport
	 */
	public void updateCmreport(Cmreport cmreport);

	

	/**
	 * 业务名称：新增cmreport
	 * 
	 * 业务描述：往数据库中插入一条cmreport记录
	 * 
	 * 实现方案：根据参数cmreport,在"任务报告cmreport"表中，插入cmreport实例
	 * 
	 * 
	 * @param cmreport所需插入的cmreport实例
	 */
	public void insertCmreport(Cmreport cmreport);

	/**
	 * 
	 * 判断该报告的学生是否为当前学生
	 */
	
	public Boolean isStudentAuthorizedOnCmreport(Integer cmreportid);
	
}
