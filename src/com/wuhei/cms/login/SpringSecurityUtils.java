package com.wuhei.cms.login;

import java.util.Collection;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class SpringSecurityUtils {

	/**
	 * ȡ�õ�ǰ�û�, ����ֵΪUserInfo���������, �����ǰ�û�δ��¼�򷵻�null.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends LoginUser> T getCurrentUser() {
		Authentication authentication = getAuthentication();

		if (authentication == null) {
			return null;
		}

		Object principal = authentication.getPrincipal();
		if (!(principal instanceof LoginUser)) {
			return null;
		}

		return (T) principal;
	}

	/**
	 * ȡ�õ�ǰ�û���¼IP, �����ǰ�û�δ��¼�򷵻ؿ��ַ���.
	 */
	public static String getCurrentUserIp() {
		Authentication authentication = getAuthentication();

		if (authentication == null) {
			return "IP_UNKNOWN";
		}

		Object details = authentication.getDetails();
		if (!(details instanceof WebAuthenticationDetails)) {
			return "IP_UNKNOWN";
		}

		WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
		return webDetails.getRemoteAddress();
	}

	/**
	 * �ж��û��Ƿ�ӵ�н�ɫ, ����û�ӵ�в����е�����һ����ɫ�򷵻�true.
	 */
	public static boolean hasAnyRole(String[] roles) {
		Authentication authentication = getAuthentication();

		if (authentication == null) {
			return false;
		}

		Collection<GrantedAuthority> grantedAuthorityList = (Collection<GrantedAuthority>) authentication
				.getAuthorities();
		for (String role : roles) {
			for (GrantedAuthority authority : grantedAuthorityList) {
				if (role.equals(authority.getAuthority())) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * ȡ��Authentication, �統ǰSecurityContextΪ��ʱ����null.
	 */
	private static Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return null;
		}
		return context.getAuthentication();
	}
}