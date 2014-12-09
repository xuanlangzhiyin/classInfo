package com.wuhei.cms.model;

/**
 * �������
 *
 */
public class Cmgroup {
    /**
     * ��������
     * 
     */
    private Integer id;

    /**
     * ������
     * 
     */
    private String code;

    /**
     * �������
     * 
     */
    private String name;

    /**
     * ѧ������
     */
    private Short count;

    /**
     * �Ƿ��������1����������0������������
     */
    private Boolean isinvolved;

    /**
     * �������鳤��student

     */
    private Integer leaderid;

    /**
     * ��������γ�����cmission
     */
    private Integer cmissionid;

    /**
     * ��ȡ�������id
     *
     * @return the value of cmgroup.id
     */
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ������
     *
     * @return the value of cmgroup.code
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * ��ȡ�������
     *
     * @return the value of cmgroup.name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * ��ȡ��������
     *
     * @return the value of cmgroup.count
     */
    public Short getCount() {
        return count;
    }

    public void setCount(Short count) {
        this.count = count;
    }

    /**
     * ��ȡ�÷����Ƿ��������
     *
     * @return the value of cmgroup.isinvolved
     */
    public Boolean getIsinvolved() {
        return isinvolved;
    }

    public void setIsinvolved(Boolean isinvolved) {
        this.isinvolved = isinvolved;
    }

    /**
     * ��ȡ�鳤id
     *
     * @return the value of cmgroup.leaderid
     */
    public Integer getLeaderid() {
        return leaderid;
    }


    public void setLeaderid(Integer leaderid) {
        this.leaderid = leaderid;
    }

    /**
     * ��ȡ����id
     *
     * @return the value of cmgroup.cmissionid
     */
    public Integer getCmissionid() {
        return cmissionid;
    }

    
    public void setCmissionid(Integer cmissionid) {
        this.cmissionid = cmissionid;
    }


	@Override
	public String toString() {
		return "Cmgroup [id=" + id + ", code=" + code + ", name=" + name
				+ ", count=" + count + ", isinvolved=" + isinvolved
				+ ", leaderid=" + leaderid + ", cmissionid=" + cmissionid + "]";
	}
    
    
}