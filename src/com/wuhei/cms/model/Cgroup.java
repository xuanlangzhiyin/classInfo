package com.wuhei.cms.model;

/**
 * 课程分组
 *
 */
public class Cgroup {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     *分组编号
     */
    private String code;

    /**
     *分组名称
     */
    private String name;

    /**
     * 小组人数
     */
    private Short count;

    /**
     * 组长id:cstudent id
     */
    private Integer leaderid;

    /**
     * 外键，所属开设课程：course
     */
    private Integer courseid;

    /**
     * 获取主键id
     */
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *获取分组编号
     */
    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取分组名称
     */
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分组人数
     */
    public Short getCount() {
        return count;
    }


    public void setCount(Short count) {
        this.count = count;
    }

    /**
     * 组长id:cstudent id
     */
    public Integer getLeaderid() {
        return leaderid;
    }


    public void setLeaderid(Integer leaderid) {
        this.leaderid = leaderid;
    }

    /**
     * 获取所属开设课程id
     */
    public Integer getCourseid() {
        return courseid;
    }


    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }


	@Override
	public String toString() {
		return "Cgroup [id=" + id + ", code=" + code + ", name=" + name
				+ ", count=" + count + ", leaderid=" + leaderid + ", courseid="
				+ courseid + "]";
	}
    
}