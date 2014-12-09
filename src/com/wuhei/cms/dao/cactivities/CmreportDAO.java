package com.wuhei.cms.dao.cactivities;

import com.wuhei.cms.model.Cmreport;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 任务报告的dao层接口
 * 
 */
public interface CmreportDAO {

	/**
	 * 向数据库插入任务报告信息
	 * 
	 * @param cmreport
	 *            ：要插入的任务报告信息
	 */
	public void insertCmreport(Cmreport cmreport);

	/**
	 * 更改数据库中的任务报告信息
	 * 
	 * @param cmreport
	 *            ：要更新的任务报告信息
	 */
	public void updateCmreport(Cmreport cmreport);

	/**
	 * 从数据库删除任务报告信息
	 * 
	 * @param cmreport
	 *            ：要删除的任务报告的id
	 */
	public void deleteCmreport(Integer cmreportid);

	/**
	 * 查询一条特定的任务分组信息
	 * 
	 * @param cmreport
	 *            ：要查询的任务报告的id
	 */
	public Cmreport getCmreport(Integer cmreportid);

	/**
	 * 查询任务报告
	 * 
	 * @param isgroup
	 *            是否小组报告
	 * @param cmgroupid
	 *            提交报告的组
	 * @param cstudentid
	 *            提交报告的学生
	 * @param cmissionid
	 *            报告所属的任务
	 * @return 任务报告
	 */
	public Cmreport getCmreportByCondition(
			@Param(value = "isgroup") Boolean isgroup,
			@Param(value = "cmgroupid") Integer cmgroupid,
			@Param(value = "cstudentid") Integer cstudentid,
			@Param(value = "cmissionid") Integer cmissionid);
	
	public List<Cmreport> getCmreportListByCondition(
			@Param(value = "isgroup") Boolean isgroup,
			@Param(value = "cmgroupid") Integer cmgroupid,
			@Param(value = "cstudentid") Integer cstudentid,
			@Param(value = "cmissionid") Integer cmissionid);

	/**
	 * 查询当前cmission提交报告学生数量
	 */

	public Integer countReportedStudent(
			@Param(value = "cmissionid") Integer cmissionid);

	/**
	 * author:czf
	 * 判断报告提交学生是否为当前学生
	 */
	public Integer getStudentidByCMreportid(Integer cmreportid);
	
}