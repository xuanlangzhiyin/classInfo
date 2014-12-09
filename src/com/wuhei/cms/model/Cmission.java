package com.wuhei.cms.model;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * �γ�����
 * 
 * 
 */
public class Cmission {

	/**
	 * ��������
	 */
	private Integer id;

	/**
	 * ��������
	 */
	private String name;

	/**
	 * ����Ҫ��
	 */
	private String requirement;

	/**
	 * ����ԭ���ƣ�oldname
	 */
	private String oldname;

	/**
	 * ���������ƣ�randomname
	 */
	private String randomname;

	/**
	 * ����·����AttachmentPath
	 */
	private String apath;

	/**
	 * ѧ������
	 */
	private Short scount;

	/**
	 * �������ͣ��������񣬷�������
	 */
	private String mtype;

	/**
	 * �ύ��ʽ���������ύ����С���ύ
	 */
	private String stype;

	/**
	 * �Ƿ�������ϣ�δ���֣�������
	 */
	private String ismarked;

	/**
	 * �Ƿ��ɾ����0������ɾ����1����ɾ��
	 */
	private Boolean isdeletable;

	/**
	 * �Ƿ���޸ģ�0�������޸ģ�1�����޸�
	 */
	private Boolean iseditable;

	/**
	 * �Ƿ���ύ�������ύ�����ύ
	 */
	private String isactive;

	/**
	 * �������������γ̣�Course
	 */
	private Integer courseid;

	/**
	 * ������ڿν�ʦ��teacher
	 */
	private Integer teacherid;

	/**
	 * �����ύ�Ľ�ֹʱ��
	 */
	private Date deadline;

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 * 
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 * 
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ��ȡ����Ҫ��
	 * 
	 * @return ����Ҫ��
	 */
	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	/**
	 * ��ȡ����·����AttachmentPath
	 * 
	 * @return ����·����AttachmentPath
	 */
	public String getApath() {
		return apath;
	}

	public void setApath(String apath) {
		this.apath = apath;
	}

	/**
	 * ��ȡѧ������
	 * 
	 * @return ѧ������
	 */
	public Short getScount() {
		return scount;
	}

	public void setScount(Short scount) {
		this.scount = scount;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	/**
	 * ��ȡ�ύ��ʽ��0���������ύ��1����С���ύ
	 * 
	 * @return �ύ��ʽ��0���������ύ��1����С���ύ
	 */

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	/**
	 * ��ȡ�Ƿ�������ϣ�0��δ���֣�1��������
	 * 
	 * @return �Ƿ�������ϣ�0��δ���֣�1��������
	 */
	public String getIsmarked() {
		return ismarked;
	}

	public void setIsmarked(String ismarked) {
		this.ismarked = ismarked;
	}

	/**
	 * �Ƿ��ɾ����0������ɾ����1����ɾ��
	 * 
	 * @return �Ƿ��ɾ����0������ɾ����1����ɾ��
	 */
	public Boolean getIsdeletable() {
		return isdeletable;
	}

	public void setIsdeletable(Boolean isdeletable) {
		this.isdeletable = isdeletable;
	}

	/**
	 * ��ȡ�Ƿ���޸ģ�0�������޸ģ�1�����޸�
	 * 
	 * @return �Ƿ���޸ģ�0�������޸ģ�1�����޸�
	 * 
	 * @mbggenerated Fri Jun 27 09:40:03 GMT+08:00 2014
	 */
	public Boolean getIseditable() {
		return iseditable;
	}

	public void setIseditable(Boolean iseditable) {
		this.iseditable = iseditable;
	}

	/**
	 * ��ȡ�Ƿ���ύ��0�������ύ��1�����ύ
	 * 
	 * @return �Ƿ���ύ��0�������ύ��1�����ύ
	 * 
	 * @mbggenerated Fri Jun 27 09:40:03 GMT+08:00 2014
	 */

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	/**
	 * ��ȡ�������������γ̣�Course
	 * 
	 * @return �������������γ̣�Course
	 */
	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	/**
	 * ��ȡ������ڿν�ʦ��teacher
	 * 
	 * @return ������ڿν�ʦ��teacher
	 * 
	 * @mbggenerated Fri Jun 27 09:40:03 GMT+08:00 2014
	 */
	public Integer getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	/**
	 * ����ύ����Ľ�ֹʱ��
	 * 
	 * @return �ύ����Ľ�ֹʱ��
	 */
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * ��ȡԭ�������ƣ�oldname
	 */

	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

	/**
	 * ��ȡ���������ƣ�randomname
	 */

	public String getRandomname() {
		return randomname;
	}

	public void setRandomname(String randomname) {
		this.randomname = randomname;
	}

	@Override
	public String toString() {
		return "Cmission [id=" + id + ", name=" + name + ", requirement="
				+ requirement + ", oldname=" + oldname+", randomname=" + randomname + ", apath=" + apath
				+ ", scount=" + scount + ", mtype=" + mtype + ", stype="
				+ stype + ", ismarked=" + ismarked + ", isdeletable="
				+ isdeletable + ", iseditable=" + iseditable + ", isactive="
				+ isactive + ", courseid=" + courseid + ", teacherid="
				+ teacherid + ", deadline=" + deadline + "]";
	}

}