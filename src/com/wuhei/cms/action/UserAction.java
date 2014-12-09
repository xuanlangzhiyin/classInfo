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
 * �û����������
 * 
 * 
 */
@SuppressWarnings("serial")
public class UserAction extends BaseAction {

	Logger logger = Logger.getLogger(UserAction.class);

	protected String default_rolecode = "ROLE_TCH";

	protected UserService userService;

	/**
	 * ҳ�����������ϵͳ�û�id
	 */
	protected Integer userid;

	/**
	 * ҳ�����������ϵͳ�û�
	 */
	protected User user;

	/**
	 * ����ҳ������ϵͳ�û���ϸ��ͼ
	 */
	protected UserDetailView userDetailView;

	/**
	 * ����ҳ������ϵͳ�û��б� ǰ̨��ҳ
	 */
	protected List<UserListView> users;

	/**
	 * ҳ�����������ϵͳ�û��б��ѯ���� ������ ��̨��ҳʹ��
	 */
	protected UserSearchMeta userSearchMeta;

	/**
	 * ����ҳ������������ҳ��Ϣ��ϵͳ�û��б����� ��̨��ҳʹ��
	 */
	protected UserPageResult userPageResult;

	/**
	 * ����Ա�����ر�ѧԺ�û��б� ʹ�ú�̨��ҳ
	 * 
	 * @return
	 */
	public String listUser4Acamgr() {

		// strutsĬ��converter�Ὣǰ��value=""��string����ֵΪ""
		// ��<input name="keyword" value="">����<input name="keyword">
		// ���ύ��ʱ��̨�õ���keywordΪ""������null
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

			// �����ݿ��ȡ�����������û�
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
