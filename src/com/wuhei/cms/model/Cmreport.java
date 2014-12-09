package com.wuhei.cms.model;

/**
 * 任务报告
 * 
 * 
 */
public class Cmreport {

	/**
	 * 自增主键
	 */
	private Integer id;

	/**
	 * 任务报告名称
	 */
	private String name;

	/**
	 * 文件名称
	 */
	private String oldfilename;
	
	/**
	 * 任务报告说明
	 */
	private String description;

	/**
	 * 报告文件名称
	 */
	private String viewablefilename;

	/**
	 * 报告文件文件路径
	 */
	private String viewablefilepath;


	/**
	 * 是否小组报告，0：个人报告，1：小组报告
	 */
	private Boolean isgroup;



	/**
	 * 外键，提交学生：student
	 */
	private Integer cstudentid;

	/**
	 * 外键，提交小组：cmgroup
	 */
	private Integer cmgroupid;

	/**
	 * 外键，课程任务：cmission
	 */
	private Integer cmissionid;

	/**
	 * 获取自增主键
	 * 
	 * @return 自增主键
	 * 
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取任务报告名称
	 * 
	 * @return 任务报告名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取任务报告说明
	 * 
	 * @return 任务报告说明
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取报告名称
	 * 
	 * @return String
	 */
	public String getViewablefilename() {
		return viewablefilename;
	}

	public void setViewablefilename(String viewablefilename) {
		this.viewablefilename = viewablefilename;
	}

	/**
	 * 获取报告路径
	 * 
	 * @return String
	 */
	public String getViewablefilepath() {
		return viewablefilepath;
	}

	public void setViewablefilepath(String viewablefilepath) {
		this.viewablefilepath = viewablefilepath;
	}

	/**
	 * 获取是否小组报告，0：个人报告，1：小组报告
	 * 
	 * @return 是否小组报告，0：个人报告，1：小组报告
	 * 
	 * @mbggenerated Fri Jun 27 09:40:03 GMT+08:00 2014
	 */
	public Boolean getIsgroup() {
		return isgroup;
	}

	public void setIsgroup(Boolean isgroup) {
		this.isgroup = isgroup;
	}

	
	/**
	 * 获取外键，提交学生：student
	 * 
	 * @return 外键，提交学生：student
	 */
	public Integer getCstudentid() {
		return cstudentid;
	}

	public void setCstudentid(Integer cstudentid) {
		this.cstudentid = cstudentid;
	}

	/**
	 * 获取外键，提交小组：cmgroup
	 * 
	 * @return 外键，提交小组：cmgroup
	 */
	public Integer getCmgroupid() {
		return cmgroupid;
	}

	public void setCmgroupid(Integer cmgroupid) {
		this.cmgroupid = cmgroupid;
	}

	/**
	 * 获取外键，课程任务：cmission
	 * 
	 * @return 外键，课程任务：cmission
	 */
	public Integer getCmissionid() {
		return cmissionid;
	}

	public void setCmissionid(Integer cmissionid) {
		this.cmissionid = cmissionid;
	}
	
	public String getOldfilename() {
		return oldfilename;
	}

	public void setOldfilename(String oldfilename) {
		this.oldfilename = oldfilename;
	}

	@Override
	public String toString() {
		return "Cmreport [id=" + id + ", name=" + name + ", description="
				+ description + "oldfilename=" + oldfilename 
				+ ", viewablefilename=" + viewablefilename
				+ ", viewablefilepath=" + viewablefilepath + ", isgroup="
				+ ", cmgroupid=" + cmgroupid + ", cmissionid=" + cmissionid
				+ "]";
	}

}