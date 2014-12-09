package com.wuhei.cms.dao.basic;

import org.apache.ibatis.annotations.Param;

import com.wuhei.cms.model.User;

public interface UserDAO {
	
	

	/**
	 * �����ݿ�ȡ��һ���û�user
	 * 
	 * @param ǰ̨����user.id
	 * @return  ���ض�Ӧid��user
	 */
	public User getUserByUsername(@Param(value = "username")String username);
	
	/**
	 * 
	 * @param userid
	 * @return
	 */
	public User getUserById(@Param(value = "userid")Integer userid);
	
	
}
