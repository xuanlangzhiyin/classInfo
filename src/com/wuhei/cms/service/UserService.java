package com.wuhei.cms.service;

import com.wuhei.cms.model.User;
import com.wuhei.cms.search.result.UserPageResult;

public interface UserService {

	
	/**
	 * 功能：获取用户详细信息
	 * @param username
	 * 
	 */
	
	public User getUserByUsername(String username);
	
	/**
	 * 功能：获取用户详细信息
	 * @param username
	 * 
	 */
	
	public User getUserById(Integer userid);
	
	/**
	 * 功能：根据条件返回用户的分页结构
	 * @param currentPage
	 * @param keyword
	 * @param rolecode
	 * @return
	 */
	
	public UserPageResult listUserByConditions(Integer currentPage,
			String keyword, String rolecode);
}
