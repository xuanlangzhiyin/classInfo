package com.wuhei.cms.model;
/**
 * 
 *
 */

public class Major {
    /**
     * ��������
     */
    private Integer id;

    /**
     * רҵ����
     */
    private String code;

    /**
     * רҵ���
     */
    private String name;

    /**
     * �������Ժϵ��department
     */
    private Integer departmentid;
    /**
     * �Ƿ�Ĭ��רҵ��isdefault
     */
    private Boolean isdefault;
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
     * ����רҵ����
     */
    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    /**
     * ����רҵ���
     */
    public String getName() {
        return name;
    }

 
    public void setName(String name) {
        this.name = name;
    }

    /**
     * �����������Ժϵ��department
     */
	public Integer getDepartmentid() {
		return departmentid;
	}


	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}

	/**
	 * �Ƿ�isdefault by¬��
	 * @return
	 */
	public Boolean getIsdefault() {
		return isdefault;
	}


	public void setIsdefault(Boolean isdefault) {
		this.isdefault = isdefault;
	}
	/**
     * toString������ӡ���ֶ�ֵ
     */
	@Override
	public String toString() {
		return "Major [id=" + id + ", code=" + code + ", name=" + name
				+ ", departmentid=" + departmentid + ", isdefault"+ isdefault+"]";
	}


}