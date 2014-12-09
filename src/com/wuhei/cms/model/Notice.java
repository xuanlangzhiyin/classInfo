package com.wuhei.cms.model;

import java.util.Date;

/**
 * ֪ͨ��model
 */
public class Notice {
	
    /**
     * ��������
     */
    protected Integer id;

    /**
     * ������id  ��� user.id
     */
    protected Integer publisherid;

    /**
     * ����������
     */
    protected String name;

    /**
     * ֪ͨ��Ŀ
     */
    protected String title;

    /**
     * ֪ͨ����
     */
    protected String content;

    /**
     * ����ʱ��
     */
    protected Date releasetime;

    /**
     * ����ʱ��
     */
    protected Date endtime;

    /**
     * �������
     */
    protected String filename;

    /**
     * ����·��
     */
    protected String filepath;

    /**
     * ��֪ͨ��Ӧ��ѧУ�����university.Id
     */
    protected Integer universityid;

    /**
     * ��֪ͨ��Ӧ��ְ�ܲ��ţ����office.id
     */
    protected Integer officeid;

    /**
     * ���֪ͨ��Ӧ��ѧԺid�����department.id
     */
    protected Integer departmentid;

    /**
     * ���֪ͨ��ص�רҵ�����major.id
     */
    protected Integer majorid;

    /**
     * ��֪ͨ��صĿγ̣����courseid
     */
    protected Integer courseid;

    /**
     * �Ƿ�ְ�ܲ�����Ա�ɼ�   	0�����ɼ�|1���ɼ�
     */
    protected Boolean isoffice;

    /**
     * �Ƿ����Ա�ɼ�    0�����ɼ�|1���ɼ�
     */
    protected Boolean isacamgr;

    /**
     * �Ƿ��ʦ�ɼ�    0�����ɼ�|1���ɼ�
     */
    protected Boolean isteacher;

    /**
     * �Ƿ�ѧ��ɼ�    0�����ɼ�|1���ɼ�
     */
    protected Boolean isstudent;

    /**
     * ��ȡ��֪ͨ��id
     * @return ֪ͨ��id
     */
    public Integer getId() {
        return id;
    }

   
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ������id  
     * @return ������id  
     */
    public Integer getPublisherid() {
        return publisherid;
    }


    public void setPublisherid(Integer publisherid) {
        this.publisherid = publisherid;
    }

    /**
     * ��ȡ����������
     * @return ����������
     *
     * @mbggenerated Mon Jul 07 15:09:57 CST 2014
     */
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    /**
     * ��ȡ֪ͨ��Ŀ
     * @return ֪ͨ��Ŀ
     */
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * ��ȡ֪ͨ����
     * @return ֪ͨ����
     *
     */
    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }

    /**
     * ��ȡ����ʱ��
     * @return ����ʱ��
     */
    public Date getReleasetime() {
        return releasetime;
    }

    
    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    /**
     * ��ȡ����ʱ��
     * @return ����ʱ��
     */
    public Date getEndtime() {
        return endtime;
    }

    
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * ��ȡ�������
     * @return �������
   
     */
    public String getFilename() {
        return filename;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * ��ȡ����·��
     * @return ����·��
     */
    public String getFilepath() {
        return filepath;
    }



    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * ��ȡ��֪ͨ��Ӧ��ѧУ
     * @return ��֪ͨ��Ӧ��ѧУ
     
     */
    public Integer getUniversityid() {
        return universityid;
    }


    public void setUniversityid(Integer universityid) {
        this.universityid = universityid;
    }

    /**
     * ��ȡ��֪ͨ��Ӧ��ְ�ܲ���
     * @param ��֪ͨ��Ӧ��ְ�ܲ���
     */
    public Integer getOfficeid() {
        return officeid;
    }

   
    public void setOfficeid(Integer officeid) {
        this.officeid = officeid;
    }

    /**
     * ��ȡ���֪ͨ��Ӧ��ѧԺ
     * @return ���֪ͨ��Ӧ��ѧԺ
     */
    public Integer getDepartmentid() {
        return departmentid;
    }



    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * ��ȡ���֪ͨ��ص�רҵ
     * @return ���֪ͨ��ص�רҵ
     */
    public Integer getMajorid() {
        return majorid;
    }

  
    
    public void setMajorid(Integer majorid) {
        this.majorid = majorid;
    }

    /**
     * ��ȡ��֪ͨ��صĿγ�
     * @return ��֪ͨ��صĿγ�
     */
    public Integer getCourseid() {
        return courseid;
    }



    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    /**
     * ��ȡ��֪ͨ�Ƿ�ְ�ܲ�����Ա�ɼ�
     * @return �Ƿ�ְ�ܲ�����Ա�ɼ�
     *
     * @mbggenerated Mon Jul 07 15:09:57 CST 2014
     */
    public Boolean getIsoffice() {
        return isoffice;
    }

    
    public void setIsoffice(Boolean isoffice) {
        this.isoffice = isoffice;
    }

    /**
     * ��ȡ��֪ͨ�Ƿ����Ա�ɼ�
     * @return �Ƿ����Ա�ɼ�
     *
     * @mbggenerated Mon Jul 07 15:09:57 CST 2014
     */
    public Boolean getIsacamgr() {
        return isacamgr;
    }

    
    public void setIsacamgr(Boolean isacamgr) {
        this.isacamgr = isacamgr;
    }

    /**
     * ��ȡ��֪ͨ�Ƿ��ʦ�ɼ�
     * @return �Ƿ��ʦ�ɼ�
     */
    public Boolean getIsteacher() {
        return isteacher;
    }


    
    public void setIsteacher(Boolean isteacher) {
        this.isteacher = isteacher;
    }

    /**
     * ��ȡ��֪ͨ�Ƿ�ѧ��ɼ�
     * @return �Ƿ�ѧ��ɼ�
     */
    public Boolean getIsstudent() {
        return isstudent;
    }

   
    public void setIsstudent(Boolean isstudent) {
        this.isstudent = isstudent;
    }
    
    
    public void set4All(){
    	this.isacamgr = Boolean.TRUE;
    	this.isoffice = Boolean.TRUE;
    	this.isstudent = Boolean.TRUE;
    	this.isteacher = Boolean.TRUE;
    }
    
    public void set4Department(){
    	this.isacamgr = Boolean.TRUE;
    	this.isstudent = Boolean.TRUE;
    	this.isteacher = Boolean.TRUE;
    }
    
    public void set4TCHandSTD(){
    	this.isacamgr = Boolean.TRUE; //��ģ����
    	this.isstudent = Boolean.TRUE;
    	this.isteacher = Boolean.TRUE;
    }

    public void set4TCH(){
    	this.isacamgr = Boolean.TRUE;//��ģ����
    	this.isteacher = Boolean.TRUE;
    }
    
    public void set4STD(){
    	this.isacamgr = Boolean.TRUE;
    	this.isstudent = Boolean.TRUE;
    }
    
	@Override
	public String toString() {
		return "Notice [id=" + id + ", publisherid=" + publisherid + ", name="
				+ name + ", title=" + title + ", content=" + content
				+ ", releasetime=" + releasetime + ", endtime=" + endtime
				+ ", filename=" + filename + ", filepath=" + filepath
				+ ", universityid=" + universityid + ", officeid=" + officeid
				+ ", departmentid=" + departmentid + ", majorid=" + majorid
				+ ", courseid=" + courseid + ", isoffice=" + isoffice
				+ ", isacamgr=" + isacamgr + ", isteacher=" + isteacher
				+ ", isstudent=" + isstudent + "]";
	}
    
    
    
}