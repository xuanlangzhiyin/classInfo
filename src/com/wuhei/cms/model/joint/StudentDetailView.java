package com.wuhei.cms.model.joint;


import com.wuhei.cms.model.Student;


public class StudentDetailView extends Student {

	/**
	 * 学生id
	 */
	private Integer studentId;

	/**
	 * 学生学号
	 */
	private String studentCode;

	/**
	 * 学生姓名
	 */
	private String studentName;
	
	/**
     * 学生年级
     */
    private String studentGrade;


	
	/**
	 * 专业代码
	 */
	private String majorCode;

	/**
	 * 专业名称
	 */
	private String majorName;

	/**
	 * 外键：所属院系，department
	 */
	private Integer departmentId;

	/**
	 * 院系代码
	 */
	private String departmentCode;

	/**
	 * 院系名称
	 */
	private String departmentName;

	/**
	 * 外键：所属学校，university
	 */
	private Integer universityId;

	/**
	 * 学校代码
	 */
	private String universityCode;

	/**
	 * 学校名称
	 */
	private String universityName;

  

    
	/**
	 * 获取学生id
	 * 
	 * @return
	 */
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/**
	 * 获取学生学号
	 * 
	 * @return
	 */
	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	/**
	 * 获取学生姓名
	 * 
	 * @return
	 */
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	/**
	 * 获取学生年级
	 * @return
	 */
	public String getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}

	
	
	/**
	 * 获取专业代码
	 * 
	 * @return
	 */
	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	/**
	 * 获取专业名称
	 * 
	 * @return
	 */
	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	/**
	 * 获取外键：所属院系，department
	 * 
	 * @return
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 获取院系代码
	 * 
	 * @return
	 */
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	/**
	 * 获取院系名称
	 * 
	 * @return
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * 获取外键：所属学校，university
	 * 
	 * @return
	 */
	public Integer getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Integer universityId) {
		this.universityId = universityId;
	}

	/**
	 * 获取学校代码
	 * 
	 * @return
	 */
	public String getUniversityCode() {
		return universityCode;
	}

	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}

	/**
	 * 获取学校名称
	 * 
	 * @return
	 */
	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	



	@Override
	public String toString() {
		return "StudentDetailView [studentId=" + studentId + ", studentCode="
				+ studentCode + ", studentName=" + studentName
				+ ", studentGrade=" + studentGrade + ",majorCode=" + majorCode
				+ ", majorName=" + majorName + ", departmentId=" + departmentId
				+ ", departmentCode=" + departmentCode + ", departmentName="
				+ departmentName + ", universityId=" + universityId
				+ ", universityCode=" + universityCode + ", universityName="
				+ universityName + "]";
	}
	
	

	
}
