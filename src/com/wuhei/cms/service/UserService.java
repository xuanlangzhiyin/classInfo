package com.wuhei.cms.service;

import com.wuhei.cms.model.User;
import com.wuhei.cms.search.result.UserPageResult;

public interface UserService {

	
	/**
	 * ���ܣ���ȡ�û���ϸ��Ϣ
	 * @param username
	 * 
	 */
	
	public User getUserByUsername(String username);
	
	/**
	 * ���ܣ���ȡ�û���ϸ��Ϣ
	 * @param username
	 * 
	 */
	
	public User getUserById(Integer userid);
	
	/**
	 * ���ܣ��������������û��ķ�ҳ�ṹ
	 * @param currentPage
	 * @param keyword
	 * @param rolecode
	 * @return
	 */
	
	public UserPageResult listUserByConditions(Integer currentPage,
			String keyword, String rolecode);
}
