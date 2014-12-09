package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.User;

public class StudentUserListView extends User
{

    /**
     * ѧ���꼶
     */
    private String grade;

    /**
     * ѧ��༶
     */
    private String stuclass;
    /**
     * �Ա�Ĭ��Ϊ��
     */
    private String sex;
	/**
     * �㼶���籾�ƣ�˶ʿ�ȣ�
     */
    private String level;
    
    public String toString()
    {
    	String string=super.toString();
    	//ȡ�����һ����]��
    	string.substring(0,string.length()-1);
    	string+=" grade "+grade+" stuclass "+stuclass+" sex "+sex+" level "+level;
    	return string;
    }
    
    //getter and setter
    
    
    public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getStuclass() {
		return stuclass;
	}
	public void setStuclass(String stuclass) {
		this.stuclass = stuclass;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}


}
