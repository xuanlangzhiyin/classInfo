package com.wuhei.cms.model;


public class Cmstudent {
	/**
	 * ��������
	 */
	private Integer id;

	/**
	 * ѧ������
	 */
	private String name;

	/**
	 * �����Ƿ������֣�0��δ���֣�1��������
	 */
	private Boolean ismarked;

	/**
	 * ��ʶ�Ƿ��ѷ��飬0δ��1��
	 */
	private Boolean isgrouped;

	/**
	 * �Ƿ��������1����������0������������
	 */
	private Boolean isinvolved;

	/**
	 * �����ѡ��ѧ����student
	 */
	private Integer cstudentid;

	/**
	 * �����������飺cmgroup
	 */
	private Integer cmgroupid;

	/**
	 * ������γ�����cmission
	 */
	private Integer cmissionid;

	/**
	 * �������� ��ȡ��������
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
	 * �����Ƿ������֣�0��δ���֣�1�������� ��ȡ�����Ƿ������֣�0��δ���֣�1��������
	 */
	public Boolean getIsmarked() {
		return ismarked;
	}

	public void setIsmarked(Boolean ismarked) {
		this.ismarked = ismarked;
	}

	/**
	 * ��ʶ�Ƿ��ѷ��顣 0��δ 1���� ��ȡ��ʶ�Ƿ��ѷ��飬0δ��1��
	 */
	public Boolean getIsgrouped() {
		return isgrouped;
	}

	public void setIsgrouped(Boolean isgrouped) {
		this.isgrouped = isgrouped;
	}

	/**
	 * �Ƿ��������1����������0������������ ��ȡ�Ƿ��������1����������0������������
	 */
	public Boolean getIsinvolved() {
		return isinvolved;
	}

	public void setIsinvolved(Boolean isinvolved) {
		this.isinvolved = isinvolved;
	}

	/**
	 * �����ѡ��ѧ����student
	 */
	public Integer getCstudentid() {
		return cstudentid;
	}

	public void setCstudentid(Integer cstudentid) {
		this.cstudentid = cstudentid;
	}

	/**
	 * �����������飺cmgroup
	 */
	public Integer getCmgroupid() {
		return cmgroupid;
	}

	public void setCmgroupid(Integer cmgroupid) {
		this.cmgroupid = cmgroupid;
	}

	/**
	 * ������γ�����cmission
	 */
	public Integer getCmissionid() {
		return cmissionid;
	}

	public void setCmissionid(Integer cmissionid) {
		this.cmissionid = cmissionid;
	}

	@Override
	public String toString() {
		return "Cmstudent [id=" + id + ", name=" + name + ", ismarked="
				+ ismarked + ", isgrouped=" + isgrouped + ", isinvolved="
				+ isinvolved + ", cstudentid=" + cstudentid + ", cmgroupid="
				+ cmgroupid + ", cmissionid=" + cmissionid + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cmstudent))
			return false;
		Cmstudent cmstudent = (Cmstudent) obj;
		if (this.id.equals(cmstudent.id)
				&& this.name.equals(cmstudent.getName())
				&& this.ismarked.equals(cmstudent.getIsmarked())
				&& this.isgrouped.equals(cmstudent.getIsgrouped())
				&& this.isinvolved.equals(cmstudent.getIsinvolved())
				&& this.cstudentid.equals(cmstudent.getCstudentid())
				&& this.cmgroupid.equals(cmstudent.getCmgroupid())
				&& this.cmissionid.equals(cmstudent.getCmissionid()))
			return true;
		return false;

	}

}