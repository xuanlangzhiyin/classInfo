package com.wuhei.cms.login;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.wuhei.cms.log.Log4jPrefix;
import com.wuhei.cms.model.Major;
import com.wuhei.cms.systools.PasswordHelper;
import com.wuhei.cms.utils.Utils;
import com.wuhei.cms.context.CmsContext;

public class MyBasicAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	/**
	 * 用户登录成功
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {

		
		LoginUser loginUser = (LoginUser) SpringSecurityUtils.getCurrentUser();
		
		// 初始化 log4j 配置
		String userName = loginUser.getUsername();
		String userIP = request.getRemoteAddr();
		Log4jPrefix.initialise(userName, userIP);
		
		// 判断用户是否已经修改默认密码
		String password = loginUser.getPassword();
		String defaultPassword0 = PasswordHelper.getSaltedSHAPassword(loginUser.getTeachercode(), null);// 学生默认密码为学号
		String defaultPassword1 = PasswordHelper.getSaltedSHAPassword(loginUser.getStudentcode(), null); // 教师默认密码为教工号
		String defaultAction = null;
		if (password.equals(defaultPassword0)
				|| password.equals(defaultPassword1)) {
			defaultAction = "/listNotice.action";
			//defaultAction = "/editPassword.action";// 用户还未修改默认密码
		} else {
			defaultAction = "/listNotice.action";// 主页-显示通知
		}
		
		// 设置与登录用户相关的变量
		HttpSession session = request.getSession();
		session.setAttribute(Utils.UNIVERSITY_ID, loginUser.getUniversityid());
		session.setAttribute(Utils.DEPARTMENT_ID, loginUser.getDepartmentid());
		// 为教务员设置默认专业，软件学院为“软件工程”
		ConcurrentHashMap<Integer, Major> majorMap = CmsContext
				.getMajorMapByDepartmentid(loginUser.getDepartmentid());
		Major defaultMajor = (Major) majorMap.values().toArray()[0];
		session.setAttribute(Utils.MAJOR_ID, defaultMajor.getId());
		
		// 根据角色，挑战到不同的页面，改成可配置的实现版本
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication
				.getAuthorities());
		if (roles.contains(Utils.ROLE_ACAMGR)) {
			getRedirectStrategy().sendRedirect(request, response,
					"/acamgr" + defaultAction);
		} else if (roles.contains(Utils.ROLE_TCH)) {
			getRedirectStrategy().sendRedirect(request, response,
					"/tch" + defaultAction);
		} else if (roles.contains(Utils.ROLE_STD)) {
			getRedirectStrategy().sendRedirect(request, response,
					"/std" + defaultAction);
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}
