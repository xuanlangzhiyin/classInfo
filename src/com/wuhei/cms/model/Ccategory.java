package com.wuhei.cms.model;

public class Ccategory {
	/**
	 * ��������
	 */
	private Integer id;

	/**
	 * �γ�������
	 */
	private String code;

	/**
	 * �γ��������
	 */
	private String name;

	/**
	 * ������������
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ���ؿγ�������
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * ���ؿγ��������
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