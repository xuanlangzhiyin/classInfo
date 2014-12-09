package com.wuhei.cms.model;

import java.util.Date;


public class Student {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 学生学号
     */
    private String code;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生年级
     */
    private String grade;

    /**
     * 学生班级
     */
    private String stuclass;

    /**
     * 外键，所属专业：major
     */
    private Integer majorId;

    /**
     * 性别，默认为男
     */
    private String sex;
    
    /**
     * 层级（如本科，硕士等）
     */
    private String level;
    
    /**
     * 学生毕业日期
     */
    private Date gdate;
    
    
    /**
     * 获取自增主键
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取学生学号
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取学生姓名
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取学生年级
     */
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**获取学生班级
     */
    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    /**
     * 获取外键，所属专业：major
     */
    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    /**
     * 获取性别
     * @return 性别
     */
	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	/**
	 * 获取层级
	 * @return 层级
	 */
	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	/**
	 * 获取毕业日期
	 * @return 毕业日期
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