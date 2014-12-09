package com.wuhei.cms.model;


/**
 * ��������model
 *
 */
public class Cmcredit {
	
    /**
     * �������ֵ���������
     */
    private Integer id;

    /**
     * �������֣�[0-100]
     */
    private Byte credit;

    /**
     * ���ֱ�ע
     */
    private String description;

    /**
     * ���ѡ��ѧ��student
     */
    private Integer cstudentid;

    /**
     * ���γ�����cmission
     */
    private Integer cmissionid;

    /**
     * ���ڻ�ȡ�������ֵ�id
     *
     * @return ��������idֵ
     */
    public Integer getId() {
        return id;
    }

    /**
     * ������������idֵ
     *
     * @param �������ֵ�id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ���ڻ�ȡ���������
     *
     * @return ���������
     */
    public Byte getCredit() {
        return credit;
    }

    /**
     * �������������
     *
     * @param ���������
     */
    public void setCredit(Byte credit) {
        this.credit = credit;
    }

    /**
     * ���ڻ�ȡ�������ֵı�ע
     *
     * @return �������ֵı�ע
     */
    public String getDescription() {
        return description;
    }

    /**
     * �����������ֵı�ע
     *
     * @param �������ֵı�ע
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ���ڻ�ȡҪ���ֵ�ѧ���id
     *
     * @return Ҫ���ֵ�ѧ���id
     */
    public Integer getCstudentid() {
        return cstudentid;
    }

    /**
     * ����Ҫ���ֵ�ѧ���id
     *
     * @param Ҫ���ֵ�ѧ���id
     */

    public void setCstudentid(Integer cstudentid) {
		this.cstudentid = cstudentid;
	}


	/**
     * ���ڻ�ȡҪ���ֵ�����
     *
     * @return Ҫ���ֵ�����
     */
    public Integer getCmissionid() {
        return cmissionid;
    }

    /**
     * ����Ҫ���ֵ������id
     *
     * @param Ҫ���ֵ������id
     */
    public void setCmissionid(Integer cmissionid) {
        this.cmissionid = cmissionid;
    }

	@Override
	public String toString() {
		return "Cmcredit [id=" + id + ", credit=" + credit + ", description="
				+ description + ", cstudentid=" + cstudentid + ", cmissionid="
				+ cmissionid + "]";
	}
}