package com.wuhei.cms.model;

public class Csetting {
	
	/**
	 * ��������
	 */
    private Integer id;

    /**
     * �γ����ô���
     */
    private String code;

    /**
     * �γ���������
     */
    private String name;

    /**
     * �γ���������:0������|1��ѡ��
     */
    private String type;

    /**
     * �γ����ü�飬������200�������ַ�
     */
    private String description;
    
    /**
     * �������ƣ�������30�������ַ�/90��Ӣ���ַ�
     */
    private String attachname;
    
    /**
     * ����Ŀ¼��������90��Ӣ���ַ�
     */
    private String attachlocation;
    
    /**
     * ����������γ����ccategory
     */
    private Integer ccategoryid;

    /**
     * ���������רҵ��major
     */
    private Integer majorid;
    
    /**
     * �㼶���籾�ơ�˶ʿ��
     */
    private String level;

    /**
     * ������������
     * @return
     */


    /**
     * ���ؿγ����ô���
     * @return
     */
    public String getCode() {
        return code;
    }


    public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setCode(String code) {
        this.code = code;
    }

    /**
     * �γ���������
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 

    public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	/**
     * ��������������γ����ccategory
     * @return
     */
    public Integer getCcategoryid() {
        return ccategoryid;
    }

    public void setCcategoryid(Integer ccategoryid) {
        this.ccategoryid = ccategoryid;
    }

    /**
     * �������������רҵ��major
     * @return
     */
    public Integer getMajorid() {
        return majorid;
    }

    public void setMajorid(Integer majorid) {
        this.majorid = majorid;
    }

	public String getDescription() {
		return description;
	}

	public String getAttachname() {
		return attachname;
	}

	public String getAttachlocation() {
		return attachlocation;
	}

	public void setDescription(
			String description) {
		this.description = description;
	}

	public void setAttachname(
			String attachname) {
		this.attachname = attachname;
	}

	public void setAttachlocation(
			String attachlocation) {
		this.attachlocation = attachlocation;
	}

	/**
	 * ��ò㼶
	 * @return �㼶
	 */
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Csetting [id=" + id + ", code=" + code + ", name=" + name
				+ ", type=" + type + ", description=" + description
				+ ", attachname=" + attachname + ", attachlocation="
				+ attachlocation + ", ccategoryid=" + ccategoryid
				+ ", majorid=" + majorid + ", level=" + level + "]";
	}

	
    
}