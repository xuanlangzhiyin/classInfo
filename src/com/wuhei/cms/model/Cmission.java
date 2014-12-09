package com.wuhei.cms.model;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * 课程任务
 * 
 * 
 */
public class Cmission {

	/**
	 * 自增主键
	 */
	private Integer id;

	/**
	 * 任务名称
	 */
	private String name;

	/**
	 * 任务要求
	 */
	private String requirement;

	/**
	 * 附件原名称，oldname
	 */
	private String oldname;

	/**
	 * 附件新名称，randomname
	 */
	private String randomname;

	/**
	 * 附件路径，AttachmentPath
	 */
	private String apath;

	/**
	 * 学生人数
	 */
	private Short scount;

	/**
	 * 任务类型，个人任务，分组任务
	 */
	private String mtype;

	/**
	 * 提交方式，按个人提交，按小组提交
	 */
	private String stype;

	/**
	 * 是否评分完毕，未评分，已评分
	 */
	private String ismarked;

	/**
	 * 是否可删除，0：不可删除，1：可删除
	 */
	private Boolean isdeletable;

	/**
	 * 是否可修改，0：不可修改，1：可修改
	 */
	private Boolean iseditable;

	/**
	 * 是否可提交，不可提交，可提交
	 */
	private String isactive;

	/**
	 * 外键，所属开设课程：Course
	 */
	private Integer courseid;

	/**
	 * 外键，授课教师：teacher
	 */
	private Integer teacherid;

	/**
	 * 任务提交的截止时间
	 */
	private Date deadline;

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
	 * 获取任务名称
	 * 
	 * @return 任务名称
	 * 
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取任务要求
	 * 
	 * @return 任务要求
	 */
	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	/**
	 * 获取附件路径，AttachmentPath
	 * 
	 * @return 附件路径，AttachmentPath
	 */
	public String getApath() {
		return apath;
	}

	public void setApath(String apath) {
		this.apath = apath;
	}

	/**
	 * 获取学生人数
	 * 
	 * @return 学生人数
	 */
	public Short getScount() {
		return scount;
	}

	public void setScount(Short scount) {
		this.scount = scount;
	}

	/**
	 * 获取任务类型
	 * 
	 * @return 任务类型
	 */

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	/**
	 * 获取提交方式，0：按个人提交，1：按小组提交
	 * 
	 * @return 提交方式，0：按个人提交，1：按小组提交
	 */

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	/**
	 * 获取是否评分完毕，0：未评分，1：已评分
	 * 
	 * @return 是否评分完毕，0：未评分，1：已评分
	 */
	public String getIsmarked() {
		return ismarked;
	}

	public void setIsmarked(String ismarked) {
		this.ismarked = ismarked;
	}

	/**
	 * 是否可删除，0：不可删除，1：可删除
	 * 
	 * @return 是否可删除，0：不可删除，1：可删除
	 */
	public Boolean getIsdeletable() {
		return isdeletable;
	}

	public void setIsdeletable(Boolean isdeletable) {
		this.isdeletable = isdeletable;
	}

	/**
	 * 获取是否可修改，0：不可修改，1：可修改
	 * 
	 * @return 是否可修改，0：不可修改，1：可修改
	 * 
	 * @mbggenerated Fri Jun 27 09:40:03 GMT+08:00 2014
	 */
	public Boolean getIseditable() {
		return iseditable;
	}

	public void setIseditable(Boolean iseditable) {
		this.iseditable = iseditable;
	}

	/**
	 * 获取是否可提交，0：不可提交，1：可提交
	 * 
	 * @return 是否可提交，0：不可提交，1：可提交
	 * 
	 * @mbggenerated Fri Jun 27 09:40:03 GMT+08:00 2014
	 */

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	/**
	 * 获取外键，所属开设课程：Course
	 * 
	 * @return 外键，所属开设课程：Course
	 */
	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	/**
	 * 获取外键，授课教师：teacher
	 * 
	 * @return 外键，授课教师：teacher
	 * 
	 * @mbggenerated Fri Jun 27 09:40:03 GMT+08:00 2014
	 */
	public Integer getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	/**
	 * 获得提交任务的截止时间
	 * 
	 * @return 提交任务的截止时间
	 */
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * 获取原附件名称，oldname
	 */

	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

	/**
	 * 获取附件新名称，randomname
	 */

	public String getRandomname() {
		return randomname;
	}

	public void setRandomname(String randomname) {
		this.randomname = randomname;
	}

	@Override
	public String toString() {
		return "Cmission [id=" + id + ", name=" + name + ", requirement="
				+ requirement + ", oldname=" + oldname+", randomname=" + randomname + ", apath=" + apath
				+ ", scount=" + scount + ", mtype=" + mtype + ", stype="
				+ stype + ", ismarked=" + ismarked + ", isdeletable="
				+ isdeletable + ", iseditable=" + iseditable + ", isactive="
				+ isactive + ", courseid=" + courseid + ", teacherid="
				+ teacherid + ", deadline=" + deadline + "]";
	}

}