package com.wuhei.cms.model;

public class Csetting {
	
	/**
	 * 自增主键
	 */
    private Integer id;

    /**
     * 课程设置代码
     */
    private String code;

    /**
     * 课程设置名称
     */
    private String name;

    /**
     * 课程设置类型:0：必修|1：选修
     */
    private String type;

    /**
     * 课程设置简介，不超过200个中文字符
     */
    private String description;
    
    /**
     * 附件名称，不超过30个中文字符/90个英文字符
     */
    private String attachname;
    
    /**
     * 附件目录，不超过90个英文字符
     */
    private String attachlocation;
    
    /**
     * 外键，所属课程类别：ccategory
     */
    private Integer ccategoryid;

    /**
     * 外键，所属专业：major
     */
    private Integer majorid;
    
    /**
     * 层级（如本科、硕士）
     */
    private String level;

    /**
     * 返回自增主键
     * @return
     */


    /**
     * 返回课程设置代码
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
     * 课程设置名称
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
     * 返回外键，所属课程类别：ccategory
     * @return
     */
    public Integer getCcategoryid() {
        return ccategoryid;
    }

    public void setCcategoryid(Integer ccategoryid) {
        this.ccategoryid = ccategoryid;
    }

    /**
     * 返回外键，所属专业：major
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
	 * 获得层级
	 * @return 层级
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