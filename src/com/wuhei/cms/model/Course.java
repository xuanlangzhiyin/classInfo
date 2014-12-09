package com.wuhei.cms.model;


public class Course {

	/**
	 * ��������
	 */
	private Integer id;

	/**
	 * ����γ̴��ￄ1�7
	 */
	private String code;

	/**
	 * ����γ����ￄ1�7
	 */
	private String name;

	/**
	 * ѧ��
	 */
	private String year;

	/**
	 * ѧ��
	 */
	private String term;

	/**
	 * ��ʦ����[0,127]
	 */
	private Byte tnum;

	/**
	 * ѧ������[0,32767]
	 */
	private Short snum;

	/**
	 * �Ƿ����ÿγ̷��飬0��δ���ÿγ̷��飬1�������ÿγ̷���
	 */
	private Boolean isgrouped;

	/**
	 * ��־�Ƿ���������ۣￄ1�7��δ��ɣￄ1�7������ￄ1�7
	 */
	private Boolean isevaluated;

	/**
	 * ��־�Ƿ��ɾ�ￄ1�7������ɾ��1����ɾ��
	 */
	private Boolean isdeletale;

	/**
	 * ���ÿγ̵��ڿ���ʦ��teahcer
	 */
	private Integer teacherid;

	/**
	 * ���ÿγ̵���Ƹ��ʦ��teacher ����Ƹ�γ̣�����Ϊ��
	 */
	private Integer foreignteacherid;

	/**
	 * ��������γ��ￄ1�7
	 */
	private Integer csettingid;

	/**
	 * �γ�����
	 */
	private String description;

	/**
	 * �γ������ĸ���ԭ��
	 */
	private String oldname;

	/**
	 * �γ�����������λ��
	 */
	private String attachlocation;

	/**
	 * author:chenzefeng
	 */

	private String cclass;

	/**
	 * �γ������ĸ�������ￄ1�7
	 */
	private String randomname;

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
	 * ����γ̴��ￄ1�7
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * ����γ����ￄ1�7
	 * 
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ѧ��
	 */
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * ѧ��
	 */
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * ��ʦ����[0,127]
	 */
	public Byte getTnum() {
		return tnum;
	}

	public void setTnum(Byte tnum) {
		this.tnum = tnum;
	}

	/**
	 * ѧ������[0,32767]
	 */
	public Short getSnum() {
		return snum;
	}

	public void setSnum(Short snum) {
		this.snum = snum;
	}

	/**
	 * �Ƿ����ÿγ̷��飬0��δ���ÿγ̷��飬1�������ÿγ̷���
	 */
	public Boolean getIsgrouped() {
		return isgrouped;
	}

	public void setIsgrouped(Boolean isgrouped) {
		this.isgrouped = isgrouped;
	}

	/**
	 * ��־�Ƿ���������ۣￄ1�7��δ��ɣￄ1�7������ￄ1�7
	 */
	public Boolean getIsevaluated() {
		return isevaluated;
	}

	public void setIsevaluated(Boolean isevaluated) {
		this.isevaluated = isevaluated;
	}

	/**
	 * ��־�Ƿ��ɾ�ￄ1�7������ɾ��1����ɾ��
	 */
	public Boolean getIsdeletale() {
		return isdeletale;
	}

	public void setIsdeletale(Boolean isdeletale) {
		this.isdeletale = isdeletale;
	}

	/**
	 * ���ÿγ̵��ڿ���ʦ��teahcer
	 */
	public Integer getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	/**
	 * ���ÿγ̵���Ƹ��ʦ��teacher ����Ƹ�γ̣�����Ϊ��
	 */
	public Integer getForeignteacherid() {
		return foreignteacherid;
	}

	public void setForeignteacherid(Integer foreignteacherid) {
		this.foreignteacherid = foreignteacherid;
	}

	/**
	 * ��������γ��ￄ1�7
	 */
	public Integer getCsettingid() {
		return csettingid;
	}

	public void setCsettingid(Integer csettingid) {
		this.csettingid = csettingid;
	}

	/**
	 * ��ȡ�γ�����
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ��ȡ�γ�����������ԭ��
	 * 
	 * @return
	 */

	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

	/**
	 * ��ȡ�γ���������������ￄ1�7
	 */
	public String getRandomname() {
		return randomname;
	}

	public void setRandomname(String randomname) {
		this.randomname = randomname;
	}

	/**
	 * ��ȡ�γ�����������λ��
	 * 
	 * @return
	 */
	public String getAttachlocation() {
		return attachlocation;
	}

	public void setAttachlocation(String attachlocation) {
		this.attachlocation = attachlocation;
	}

	public String getCclass() {
		return cclass;
	}

	public void setCclass(String cclass) {
		this.cclass = cclass;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", code=" + code + ", name=" + name
				+ ", year=" + year + ", term=" + term + ", tnum=" + tnum
				+ ", snum=" + snum + ", cclass=" + cclass + ", isgrouped="
				+ isgrouped + ", isevaluated=" + isevaluated + ", isdeletale="
				+ isdeletale + ", teacherid=" + teacherid
				+ ", foreignteacherid=" + foreignteacherid + ", csettingid="
				+ csettingid + ", description=" + description + ", oldname="
				+ oldname + ", attachlocation=" + attachlocation + ", randomname=" + randomname+ "]";
	}

}