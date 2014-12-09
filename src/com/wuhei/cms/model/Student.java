package com.wuhei.cms.model;

import java.util.Date;


public class Student {
    /**
     * ��������
     */
    private Integer id;

    /**
     * ѧ��ѧ��
     */
    private String code;

    /**
     * ѧ������
     */
    private String name;

    /**
     * ѧ���꼶
     */
    private String grade;

    /**
     * ѧ���༶
     */
    private String stuclass;

    /**
     * ���������רҵ��major
     */
    private Integer majorId;

    /**
     * �Ա�Ĭ��Ϊ��
     */
    private String sex;
    
    /**
     * �㼶���籾�ƣ�˶ʿ�ȣ�
     */
    private String level;
    
    /**
     * ѧ����ҵ����
     */
    private Date gdate;
    
    
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
     * ��ȡѧ��ѧ��
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * ��ȡѧ������
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * ��ȡѧ���꼶
     */
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**��ȡѧ���༶
     */
    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    /**
     * ��ȡ���������רҵ��major
     */
    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
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
	 * ��ȡ�㼶
	 * @return �㼶
	 */
	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	/**
	 * ��ȡ��ҵ����
	 * @return ��ҵ����
	 */
	public Date getGdate() {
		return gdate;
	}


	public void setGdate(Date gdate) {
		this.gdate = gdate;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", code=" + code + ", name=" + name
				+ ", grade=" + grade + ", stuclass=" + stuclass + ", majorId="
				+ majorId + ", sex=" + sex + ", level=" + level + ", gdate="
				+ gdate + "]";
	}
	
	
   
    
    
}