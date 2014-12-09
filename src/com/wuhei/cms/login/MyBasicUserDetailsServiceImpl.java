package com.wuhei.cms.login;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.common.collect.Sets;
import com.wuhei.cms.dao.login.LoginDAO;
import com.wuhei.cms.utils.Utils;

public class MyBasicUserDetailsServiceImpl implements UserDetailsService {

	private LoginDAO loginDAO;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		//�����ݿ��ȡ�û�
		LoginUser loginUser = loginDAO.loadUserByUsername(username);
		//�û�������
		if (loginUser == null) {
			throw new UsernameNotFoundException("�û�" + username + " ������!");
		}
		//��Ҫlog�û��ĵ�¼����
		//System.out.println(loginUser.toString());
		String rolecode = loginUser.getRolecode();
		// Ĭ�Ͻ���һ�ֽ�ɫ�������Ҫ���ֶ��ֽ�ɫ����Ҫ��չ
		Set<GrantedAuthority>  authorities = Sets.newHashSet();
		if (rolecode.equalsIgnoreCase(Utils.ROLE_ACAMGR)) {
			authorities.add(new SimpleGrantedAuthority(Utils.ROLE_ACAMGR));
		} else if (rolecode.equalsIgnoreCase(Utils.ROLE_STD)) {
			authorities.add(new SimpleGrantedAuthority(Utils.ROLE_STD));
		} else if (rolecode.equalsIgnoreCase(Utils.ROLE_TCH)) {
			authorities.add(new SimpleGrantedAuthority(Utils.ROLE_TCH));
		} 
		
		//���õ�¼�û���Ȩ��
		loginUser.setAuthorities(authorities);
		//���ص�¼�û�
		return loginUser;
	}

	/*
	 * Getter and Setter
	 */

	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

}
