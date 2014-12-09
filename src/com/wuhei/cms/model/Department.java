package com.wuhei.cms.model;

public class Department {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 院系代码
     */
    private String code;

    /**
     * 院系名称
     */
    private String name;

    /**
     * 外键：所属学校，university
     */
    private Integer universityid;

    /**
     * 返回自增主键
     */
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 返回院系代码
     */
    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 返回院系名称
     */
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回外键：所属学校，university
     */
	public Integer getUniversityid() {
		return universityid;
	}


	public void setUniversityid(Integer universityid) {
		this.universityid = universityid;
	}


	public String toString() {
		return "Department [id=" + id + ", code=" + code + ", name=" + name
				+ ", universityid=" + universityid + "]";
	}

}