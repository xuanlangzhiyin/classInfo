package com.wuhei.cms.model;

public class Department {
    /**
     * ��������
     */
    private Integer id;

    /**
     * Ժϵ����
     */
    private String code;

    /**
     * Ժϵ����
     */
    private String name;

    /**
     * ���������ѧУ��university
     */
    private Integer universityid;

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
     * ����Ժϵ����
     */
    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    /**
     * ����Ժϵ����
     */
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    /**
     * �������������ѧУ��university
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