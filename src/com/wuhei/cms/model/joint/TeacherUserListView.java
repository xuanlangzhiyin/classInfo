package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.User;

@SuppressWarnings("serial")
public class TeacherUserListView  extends User  
{
	   
    /**
     * �Ա�
     */
    private String sex;
    
    /**
     * ��ʦ�ĵ��������ַ
     */
    private String email;

    
    
    //getter and setter
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
  
}
