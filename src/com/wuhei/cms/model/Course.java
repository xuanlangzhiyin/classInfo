package com.wuhei.cms.model;


public class Course {

	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	private Integer id;

	/**
	 * 锟斤拷锟斤拷纬檀锟斤拷锟�
	 */
	private String code;

	/**
	 * 锟斤拷锟斤拷纬锟斤拷锟斤拷锟�
	 */
	private String name;

	/**
	 * 学锟斤拷
	 */
	private String year;

	/**
	 * 学锟斤拷
	 */
	private String term;

	/**
	 * 锟斤拷师锟斤拷锟斤拷[0,127]
	 */
	private Byte tnum;

	/**
	 * 学锟斤拷锟斤拷锟斤拷[0,32767]
	 */
	private Short snum;

	/**
	 * 锟角凤拷锟斤拷锟矫课程凤拷锟介，0锟斤拷未锟斤拷锟矫课程凤拷锟介，1锟斤拷锟斤拷锟斤拷锟矫课程凤拷锟斤拷
	 */
	private Boolean isgrouped;

	/**
	 * 锟斤拷志锟角凤拷锟斤拷锟斤拷锟斤拷锟斤拷郏锟�斤拷未锟斤拷桑锟�斤拷锟斤拷锟斤拷锟�
	 */
	private Boolean isevaluated;

	/**
	 * 锟斤拷志锟角凤拷锟缴撅拷锟�斤拷锟斤拷锟斤拷删锟斤拷1锟斤拷锟斤拷删锟斤拷
	 */
	private Boolean isdeletale;

	/**
	 * 锟斤拷锟矫课程碉拷锟节匡拷锟斤拷师锟斤拷teahcer
	 */
	private Integer teacherid;

	/**
	 * 锟斤拷锟矫课程碉拷锟斤拷聘锟斤拷师锟斤拷teacher 锟斤拷锟斤拷聘锟轿程ｏ拷锟斤拷锟斤拷为锟斤拷
	 */
	private Integer foreignteacherid;

	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷纬锟斤拷锟�
	 */
	private Integer csettingid;

	/**
	 * 锟轿筹拷锟斤拷锟斤拷
	 */
	private String description;

	/**
	 * 锟轿筹拷锟斤拷锟斤拷锟侥革拷锟斤拷原锟斤拷
	 */
	private String oldname;

	/**
	 * 锟轿筹拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷位锟斤拷
	 */
	private String attachlocation;

	/**
	 * author:chenzefeng
	 */

	private String cclass;

	/**
	 * 锟轿筹拷锟斤拷锟斤拷锟侥革拷锟斤拷锟斤拷锟斤拷锟�
	 */
	private String randomname;

	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 锟斤拷锟斤拷纬檀锟斤拷锟�
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 锟斤拷锟斤拷纬锟斤拷锟斤拷锟�
	 * 
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 学锟斤拷
	 */
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * 学锟斤拷
	 */
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * 锟斤拷师锟斤拷锟斤拷[0,127]
	 */
	public Byte getTnum() {
		return tnum;
	}

	public void setTnum(Byte tnum) {
		this.tnum = tnum;
	}

	/**
	 * 学锟斤拷锟斤拷锟斤拷[0,32767]
	 */
	public Short getSnum() {
		return snum;
	}

	public void setSnum(Short snum) {
		this.snum = snum;
	}

	/**
	 * 锟角凤拷锟斤拷锟矫课程凤拷锟介，0锟斤拷未锟斤拷锟矫课程凤拷锟介，1锟斤拷锟斤拷锟斤拷锟矫课程凤拷锟斤拷
	 */
	public Boolean getIsgrouped() {
		return isgrouped;
	}

	public void setIsgrouped(Boolean isgrouped) {
		this.isgrouped = isgrouped;
	}

	/**
	 * 锟斤拷志锟角凤拷锟斤拷锟斤拷锟斤拷锟斤拷郏锟�斤拷未锟斤拷桑锟�斤拷锟斤拷锟斤拷锟�
	 */
	public Boolean getIsevaluated() {
		return isevaluated;
	}

	public void setIsevaluated(Boolean isevaluated) {
		this.isevaluated = isevaluated;
	}

	/**
	 * 锟斤拷志锟角凤拷锟缴撅拷锟�斤拷锟斤拷锟斤拷删锟斤拷1锟斤拷锟斤拷删锟斤拷
	 */
	public Boolean getIsdeletale() {
		return isdeletale;
	}

	public void setIsdeletale(Boolean isdeletale) {
		this.isdeletale = isdeletale;
	}

	/**
	 * 锟斤拷锟矫课程碉拷锟节匡拷锟斤拷师锟斤拷teahcer
	 */
	public Integer getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	/**
	 * 锟斤拷锟矫课程碉拷锟斤拷聘锟斤拷师锟斤拷teacher 锟斤拷锟斤拷聘锟轿程ｏ拷锟斤拷锟斤拷为锟斤拷
	 */
	public Integer getForeignteacherid() {
		return foreignteacherid;
	}

	public void setForeignteacherid(Integer foreignteacherid) {
		this.foreignteacherid = foreignteacherid;
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷纬锟斤拷锟�
	 */
	public Integer getCsettingid() {
		return csettingid;
	}

	public void setCsettingid(Integer csettingid) {
		this.csettingid = csettingid;
	}

	/**
	 * 锟斤拷取锟轿筹拷锟斤拷锟斤拷
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
	 * 锟斤拷取锟轿筹拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷原锟斤拷
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
	 * 锟斤拷取锟轿筹拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
	 */
	public String getRandomname() {
		return randomname;
	}

	public void setRandomname(String randomname) {
		this.randomname = randomname;
	}

	/**
	 * 锟斤拷取锟轿筹拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷位锟斤拷
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