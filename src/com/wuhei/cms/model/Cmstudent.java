package com.wuhei.cms.model;


public class Cmstudent {
	/**
	 * 自增主键
	 */
	private Integer id;

	/**
	 * 学生名字
	 */
	private String name;

	/**
	 * 任务是否已评分，0：未评分，1：已评分
	 */
	private Boolean ismarked;

	/**
	 * 标识是否已分组，0未，1已
	 */
	private Boolean isgrouped;

	/**
	 * 是否参与任务，1：参与任务，0：不参与任务
	 */
	private Boolean isinvolved;

	/**
	 * 外键，选课学生：student
	 */
	private Integer cstudentid;

	/**
	 * 外键，任务分组：cmgroup
	 */
	private Integer cmgroupid;

	/**
	 * 外键，课程任务：cmission
	 */
	private Integer cmissionid;

	/**
	 * 自增主键 获取自增主键
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 学生名字
	 * 
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 任务是否已评分，0：未评分，1：已评分 获取任务是否已评分，0：未评分，1：已评分
	 */
	public Boolean getIsmarked() {
		return ismarked;
	}

	public void setIsmarked(Boolean ismarked) {
		this.ismarked = ismarked;
	}

	/**
	 * 标识是否已分组。 0：未 1：已 获取标识是否已分组，0未，1已
	 */
	public Boolean getIsgrouped() {
		return isgrouped;
	}

	public void setIsgrouped(Boolean isgrouped) {
		this.isgrouped = isgrouped;
	}

	/**
	 * 是否参与任务，1：参与任务，0：不参与任务 获取是否参与任务，1：参与任务，0：不参与任务
	 */
	public Boolean getIsinvolved() {
		return isinvolved;
	}

	public void setIsinvolved(Boolean isinvolved) {
		this.isinvolved = isinvolved;
	}

	/**
	 * 外键，选课学生：student
	 */
	public Integer getCstudentid() {
		return cstudentid;
	}

	public void setCstudentid(Integer cstudentid) {
		this.cstudentid = cstudentid;
	}

	/**
	 * 外键，任务分组：cmgroup
	 */
	public Integer getCmgroupid() {
		return cmgroupid;
	}

	public void setCmgroupid(Integer cmgroupid) {
		this.cmgroupid = cmgroupid;
	}

	/**
	 * 外键，课程任务：cmission
	 */
	public Integer getCmissionid() {
		return cmissionid;
	}

	public void setCmissionid(Integer cmissionid) {
		this.cmissionid = cmissionid;
	}

	@Override
	public String toString() {
		return "Cmstudent [id=" + id + ", name=" + name + ", ismarked="
				+ ismarked + ", isgrouped=" + isgrouped + ", isinvolved="
				+ isinvolved + ", cstudentid=" + cstudentid + ", cmgroupid="
				+ cmgroupid + ", cmissionid=" + cmissionid + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cmstudent))
			return false;
		Cmstudent cmstudent = (Cmstudent) obj;
		if (this.id.equals(cmstudent.id)
				&& this.name.equals(cmstudent.getName())
				&& this.ismarked.equals(cmstudent.getIsmarked())
				&& this.isgrouped.equals(cmstudent.getIsgrouped())
				&& this.isinvolved.equals(cmstudent.getIsinvolved())
				&& this.cstudentid.equals(cmstudent.getCstudentid())
				&& this.cmgroupid.equals(cmstudent.getCmgroupid())
				&& this.cmissionid.equals(cmstudent.getCmissionid()))
			return true;
		return false;

	}

}