package com.wuhei.cms.model;

public class University {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     *学校代码
     */
    private String code;

    /**
     *学校名称
     */
    private String name;

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
     * 返回学校代码
     */
    public String getCode() {
        return code;
    }

  
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 返回学校名称
     */
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


	@Override
	public String toString() {
		return "University [id=" + id + ", code=" + code + ", name=" + name
				+ "]";
	}
    
    
}