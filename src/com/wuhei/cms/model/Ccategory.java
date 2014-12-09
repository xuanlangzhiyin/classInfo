package com.wuhei.cms.model;

public class Ccategory {
	/**
	 * 自增主键
	 */
	private Integer id;

	/**
	 * 课程类别代码
	 */
	private String code;

	/**
	 * 课程类别名称
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
	 * 返回课程类别代码
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 返回课程类别名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Ccategory [id=" + id + ", code=" + code + ", name=" + name
				+ "]";
	}
	
	
}