package com.wuhei.cms.model;

/**
 * ���񱨸�
 * 
 * 
 */
public class Cmreport {

	/**
	 * ��������
	 */
	private Integer id;

	/**
	 * ���񱨸�����
	 */
	private String name;

	/**
	 * �ļ�����
	 */
	private String oldfilename;
	
	/**
	 * ���񱨸�˵��
	 */
	private String description;

	/**
	 * �����ļ�����
	 */
	private String viewablefilename;

	/**
	 * �����ļ��ļ�·��
	 */
	private String viewablefilepath;


	/**
	 * �Ƿ�С�鱨�棬0�����˱��棬1��С�鱨��
	 */
	private Boolean isgroup;



	/**
	 * ������ύѧ����student
	 */
	private Integer cstudentid;

	/**
	 * ������ύС�飺cmgroup
	 */
	private Integer cmgroupid;

	/**
	 * ������γ�����cmission
	 */
	private Integer cmissionid;

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
	 * ��ȡ���񱨸�����
	 * 
	 * @return ���񱨸�����
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ��ȡ���񱨸�˵��
	 * 
	 * @return ���񱨸�˵��
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return String
	 */
	public String getViewablefilename() {
		return viewablefilename;
	}

	public void setViewablefilename(String viewablefilename) {
		this.viewablefilename = viewablefilename;
	}

	/**
	 * ��ȡ����·��
	 * 
	 * @return String
	 */
	public String getViewablefilepath() {
		return viewablefilepath;
	}

	public void setViewablefilepath(String viewablefilepath) {
		this.viewablefilepath = viewablefilepath;
	}

	/**
	 * ��ȡ�Ƿ�С�鱨�棬0�����˱��棬1��С�鱨��
	 * 
	 * @return �Ƿ�С�鱨�棬0�����˱��棬1��С�鱨��
	 * 
	 * @mbggenerated Fri Jun 27 09:40:03 GMT+08:00 2014
	 */
	public Boolean getIsgroup() {
		return isgroup;
	}

	public void setIsgroup(Boolean isgroup) {
		this.isgroup = isgroup;
	}

	
	/**
	 * ��ȡ������ύѧ����student
	 * 
	 * @return ������ύѧ����student
	 */
	public Integer getCstudentid() {
		return cstudentid;
	}

	public void setCstudentid(Integer cstudentid) {
		this.cstudentid = cstudentid;
	}

	/**
	 * ��ȡ������ύС�飺cmgroup
	 * 
	 * @return ������ύС�飺cmgroup
	 */
	public Integer getCmgroupid() {
		return cmgroupid;
	}

	public void setCmgroupid(Integer cmgroupid) {
		this.cmgroupid = cmgroupid;
	}

	/**
	 * ��ȡ������γ�����cmission
	 * 
	 * @return ������γ�����cmission
	 */
	public Integer getCmissionid() {
		return cmissionid;
	}

	public void setCmissionid(Integer cmissionid) {
		this.cmissionid = cmissionid;
	}
	
	public String getOldfilename() {
		return oldfilename;
	}

	public void setOldfilename(String oldfilename) {
		this.oldfilename = oldfilename;
	}

	@Override
	public String toString() {
		return "Cmreport [id=" + id + ", name=" + name + ", description="
				+ description + "oldfilename=" + oldfilename 
				+ ", viewablefilename=" + viewablefilename
				+ ", viewablefilepath=" + viewablefilepath + ", isgroup="
				+ ", cmgroupid=" + cmgroupid + ", cmissionid=" + cmissionid
				+ "]";
	}

}