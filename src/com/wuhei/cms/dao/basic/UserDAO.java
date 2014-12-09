package com.wuhei.cms.dao.basic;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.User;

public interface UserDAO {
	
	

	/**
	 * 从数据库取得一个用户user
	 * 
	 * @param 前台传入user.id
	 * @return  返回对应id的user
	 */
	public User getUserByUsername(@Param(value = "username")String username);
	
	/**
	 * 
	 * @param userid
	 * @return
	 */
	public User getUserById(@Param(value = "userid")Integer userid);
	
	
}
