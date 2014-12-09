package com.wuhei.cms.model;

public class Teacher {
    /**
     * ��������
     */
    private Integer id;

    /**
     * ��ʦ���
     */
    private String code;

    /**
     * ��ʦ����
     */
    private String name;

    /**
     * �������Ժϵ��department ����Ϊ��
     */
    private Integer departmentId;
    
    
    /**
     * �Ƿ���Ƹ��ʦ��0����|1���ǣ�Ĭ��Ϊ0
     */
    private Boolean isexternal;
    
    /**
     * �Ա�Ĭ��Ϊ��
     */
    private String sex;
    
    /**
     * ��ʦ�ĵ��������ַ
     */
    private String email;

    /**
     * ��ȡ��������
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ��ʦ���
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * ��ȡ��ʦ����
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * ��ȡ�������Ժϵ��department ����Ϊ��
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * ��ȡ�Ƿ���Ƹ��ʦ
     * @return �Ƿ���Ƹ��ʦ
     */
	public Boolean getIsexternal() {
		return isexternal;
	}

	public void setIsexternal(Boolean isexternal) {
		this.isexternal = isexternal;
	}

	/**
	 * ��ȡ�Ա�
	 * @return �Ա�
	 */
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * ��ȡ���������ַ
	 * @return ���������ַ
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", code=" + code + ", name=" + name
				+ ", departmentId=" + departmentId + ", isexternal="
				+ isexternal + ", sex=" + sex + ", email=" + email + "]";
	}
    
    
}