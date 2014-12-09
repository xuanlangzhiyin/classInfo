package com.wuhei.cms.model;

public class Cstudent {
    /**
     * ��������
     */
    private Integer id;

    /**
     * ѧ������
     */
    private String name;
    
    /**
     * ��־�Ƿ��ѱ����ۣ�0��δ��ɣ�1�������
     */
    private Boolean isevaluated;

    /**
     * ��ʶ�Ƿ��ѷ��顣
     *0��δ 1����
     */
    private Boolean isgrouped;

    /**
     * ���ѧ��student
     */
    private Integer studentid;

    /**
     * �����������γ�: Course
     */
    private Integer courseid;

    /**
     * ���ѧ�������γ̷���
     */
    private Integer cgroupid;
    
    /**
     * ѧ��ע
     */
    private String description;

    /**
     * ��������
     */
    public Integer getId() {
        return id;
    }

   
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * ѧ������
     * 
     */
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    /**
     * ��־�Ƿ��ѱ����ۣ�0��δ��ɣ�1�������
     */
    public Boolean getIsevaluated() {
        return isevaluated;
    }

    public void setIsevaluated(Boolean isevaluated) {
        this.isevaluated = isevaluated;
 	  
    }

    /**
     *��ʶ�Ƿ��ѷ��顣 0��δ 1����
     *
     */
    public Boolean getIsgrouped() {
        return isgrouped;
    }

    public void setIsgrouped(Boolean isgrouped) {
        this.isgrouped = isgrouped;
    }

    /**
     * ���ѧ��student
     */
    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    /**
     * �����������γ�: Course
     */
    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    /**
     * ���ѧ�������γ̷���
     */
    public Integer getCgroupid() {
        return cgroupid;
    }

    public void setCgroupid(Integer cgroupid) {
        this.cgroupid = cgroupid;
    }


    /**
     * ��ȡѧ��ע
     * @return ѧ��ע
     */
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Cstudent [id=" + id + ", name=" + name + ", isevaluated="
				+ isevaluated + ", isgrouped=" + isgrouped + ", studentid="
				+ studentid + ", courseid=" + courseid + ", cgroupid="
				+ cgroupid + ", description=" + description + "]";
	}


	

    
}