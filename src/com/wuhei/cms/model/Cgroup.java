package com.wuhei.cms.model;

/**
 * �γ̷���
 *
 */
public class Cgroup {
    /**
     * ��������
     */
    private Integer id;

    /**
     *������
     */
    private String code;

    /**
     *��������
     */
    private String name;

    /**
     * С������
     */
    private Short count;

    /**
     * �鳤id:cstudent id
     */
    private Integer leaderid;

    /**
     * �������������γ̣�course
     */
    private Integer courseid;

    /**
     * ��ȡ����id
     */
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *��ȡ������
     */
    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    /**
     * ��ȡ��������
     */
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    /**
     * ��ȡ��������
     */
    public Short getCount() {
        return count;
    }


    public void setCount(Short count) {
        this.count = count;
    }

    /**
     * �鳤id:cstudent id
     */
    public Integer getLeaderid() {
        return leaderid;
    }


    public void setLeaderid(Integer leaderid) {
        this.leaderid = leaderid;
    }

    /**
     * ��ȡ��������γ�id
     */
    public Integer getCourseid() {
        return courseid;
    }


    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }


	@Override
	public String toString() {
		return "Cgroup [id=" + id + ", code=" + code + ", name=" + name
				+ ", count=" + count + ", leaderid=" + leaderid + ", courseid="
				+ courseid + "]";
	}
    
}