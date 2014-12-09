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
		
		//从数据库获取用户
		LoginUser loginUser = loginDAO.loadUserByUsername(username);
		//用户不存在
		if (loginUser == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在!");
		}
		//需要log用户的登录尝试
		//System.out.println(loginUser.toString());
		String rolecode = loginUser.getRolecode();
		// 默认仅有一种角色，如果需要主持多种角色，需要扩展
		Set<GrantedAuthority>  authorities = Sets.newHashSet();
		if (rolecode.equalsIgnoreCase(Utils.ROLE_ACAMGR)) {
			authorities.add(new SimpleGrantedAuthority(Utils.ROLE_ACAMGR));
		} else if (rolecode.equalsIgnoreCase(Utils.ROLE_STD)) {
			authorities.add(new SimpleGrantedAuthority(Utils.ROLE_STD));
		} else if (rolecode.equalsIgnoreCase(Utils.ROLE_TCH)) {
			authorities.add(new SimpleGrantedAuthority(Utils.ROLE_TCH));
		} 
		
		//设置登录用户的权限
		loginUser.setAuthorities(authorities);
		//返回登录用户
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
