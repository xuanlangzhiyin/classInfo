package com.wuhei.cms.model;

public class University {
    /**
     * ��������
     */
    private Integer id;

    /**
     *ѧУ����
     */
    private String code;

    /**
     *ѧУ����
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
     * ����ѧУ����
     */
    public String getCode() {
        return code;
    }

  
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * ����ѧУ����
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