package com.wuhei.cms.model;

/**
 * �γ�����
 */
public class Cevaluation {
	/**
	 * ��������
	 */
	private Integer id;

	/**
	 * �γ�������[0-100]
	 */
	private Byte credit;

	/**
	 * ƽʱ���ڣ�[0-100]
	 */
	private Byte attendance;
	
	/**
	 * ���Գɼ���[0-100]
	 */
	private Byte examcredit;

	/**
	 * ���ѡ��ѧ��student
	 */
	private Integer studentid;

	/**
	 * ���γ�����course
	 */
	private Integer courseid;

	/**
	 * ��ȡ���� id
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ��ȡ�γ�������[0-100]
	 * 
	 * @return �γ�����
	 */
	public Byte getCredit() {
		return credit;
	}

	public void setCredit(Byte credit) {
		this.credit = credit;
	}

	/**
	 * ��ȡƽʱ���ڣ�[0-100]
	 * 
	 * @return ƽʱ����
	 */
	public Byte getAttendance() {
		return attendance;
	}

	public void setAttendance(Byte attendance) {
		this.attendance = attendance;
	}

	/**
	 * ��ȡ���Գɼ���[0-100]
	 * 
	 * @return ���Գɼ�
	 */
	public Byte getExamcredit() {
		return examcredit;
	}

	public void setExamcredit(Byte examcredit) {
		this.examcredit = examcredit;
	}

	/**
	 * ���ѡ��ѧ��id
	 * 
	 * @return ѡ��ѧ��id
	 */
	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	/**
	 * ��ȡ���γ�����
	 * 
	 * @return courseid
	 */
	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}
}