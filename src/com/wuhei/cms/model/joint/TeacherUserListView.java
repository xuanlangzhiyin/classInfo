package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.User;

@SuppressWarnings("serial")
public class TeacherUserListView  extends User  
{
	   
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 老师的电子邮箱地址
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
