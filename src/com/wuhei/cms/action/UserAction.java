package com.wuhei.cms.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.wuhei.cms.model.User;
import com.wuhei.cms.model.joint.UserDetailView;
import com.wuhei.cms.model.joint.UserListView;
import com.wuhei.cms.search.UserSearchMeta;
import com.wuhei.cms.search.result.UserPageResult;
import com.wuhei.cms.service.UserService;


/**
 * 
 * 用户管理｜基类
 * 
 * 
 */
@SuppressWarnings("serial")
public class UserAction extends BaseAction {

	Logger logger = Logger.getLogger(UserAction.class);

	protected String default_rolecode = "ROLE_TCH";

	protected UserService userService;

	/**
	 * 页面输入参数－系统用户id
	 */
	protected Integer userid;

	/**
	 * 页面输入参数－系统用户
	 */
	protected User user;

	/**
	 * 返回页面结果－系统用户详细视图
	 */
	protected UserDetailView userDetailView;

	/**
	 * 返回页面结果－系统用户列表 前台分页
	 */
	protected List<UserListView> users;

	/**
	 * 页面输入参数－系统用户列表查询条件 条件： 后台分页使用
	 */
	protected UserSearchMeta userSearchMeta;

	/**
	 * 返回页面结果－包含分页信息的系统用户列表数据 后台分页使用
	 */
	protected UserPageResult userPageResult;

	/**
	 * 教务员：返回本学院用户列表 使用后台分页
	 * 
	 * @return
	 */
	public String listUser4Acamgr() {

		// struts默认converter会将前端value=""的string对象赋值为""
		// 如<input name="keyword" value="">或者<input name="keyword">
		// 则提交表单时后台拿到的keyword为""而不是null
		Integer currentPage = 1;
		String keyword = null;
		String rolecode = default_rolecode;

		if (userSearchMeta != null) {
			currentPage = userSearchMeta.getCurrentPage();
			keyword = this.getDefaultSearchValue(userSearchMeta.getKeyword(),
					null);
			rolecode = this.getDefaultSearchValue(userSearchMeta.getRolecode(),
					default_rolecode);
		}

		try {

			// 从数据库获取符合条件的用户
			userPageResult = userService.listUserByConditions(currentPage,
					keyword, rolecode);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR;
		}

		return SUCCESS;
	}


	
	/*
	 * getters and setters
	 */

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public UserDetailView getUserDetailView() {
		return userDetailView;
	}

	public void setUserDetailView(UserDetailView userDetailView) {
		this.userDetailView = userDetailView;
	}

	public List<UserListView> getUsers() {
		return users;
	}

	public void setUsers(List<UserListView> users) {
		this.users = users;
	}

	public UserSearchMeta getUserSearchMeta() {
		return userSearchMeta;
	}

	public void setUserSearchMeta(UserSearchMeta userSearchMeta) {
		this.userSearchMeta = userSearchMeta;
	}

	public UserPageResult getUserPageResult() {
		return userPageResult;
	}

	public void setUserPageResult(UserPageResult userPageResult) {
		this.userPageResult = userPageResult;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
