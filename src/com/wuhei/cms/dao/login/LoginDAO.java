package com.wuhei.cms.dao.login;

import com.wuhei.cms.login.LoginUser;

/**
 * ����ʵ�ֵ�½
 * 
 * 
 */
public interface LoginDAO {
	
	/**
	 * ����û����¼�˻������ظ��û����û�����ҪȫϵͳΨһ
	 * @param username �û����¼�˻���
	 * @return 
	 */
	public LoginUser loadUserByUsername(String username);
	
}
