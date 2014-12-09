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
	 * �û���¼�ɹ�
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {

		
		LoginUser loginUser = (LoginUser) SpringSecurityUtils.getCurrentUser();
		
		// ��ʼ�� log4j ����
		String userName = loginUser.getUsername();
		String userIP = request.getRemoteAddr();
		Log4jPrefix.initialise(userName, userIP);
		
		// �ж��û��Ƿ��Ѿ��޸�Ĭ������
		String password = loginUser.getPassword();
		String defaultPassword0 = PasswordHelper.getSaltedSHAPassword(loginUser.getTeachercode(), null);// ѧ��Ĭ������Ϊѧ��
		String defaultPassword1 = PasswordHelper.getSaltedSHAPassword(loginUser.getStudentcode(), null); // ��ʦĬ������Ϊ�̹���
		String defaultAction = null;
		if (password.equals(defaultPassword0)
				|| password.equals(defaultPassword1)) {
			defaultAction = "/listNotice.action";
			//defaultAction = "/editPassword.action";// �û���δ�޸�Ĭ������
		} else {
			defaultAction = "/listNotice.action";// ��ҳ-��ʾ֪ͨ
		}
		
		// �������¼�û���صı���
		HttpSession session = request.getSession();
		session.setAttribute(Utils.UNIVERSITY_ID, loginUser.getUniversityid());
		session.setAttribute(Utils.DEPARTMENT_ID, loginUser.getDepartmentid());
		// Ϊ����Ա����Ĭ��רҵ�����ѧԺΪ��������̡�
		ConcurrentHashMap<Integer, Major> majorMap = CmsContext
				.getMajorMapByDepartmentid(loginUser.getDepartmentid());
		Major defaultMajor = (Major) majorMap.values().toArray()[0];
		session.setAttribute(Utils.MAJOR_ID, defaultMajor.getId());
		
		// ���ݽ�ɫ����ս����ͬ��ҳ�棬�ĳɿ����õ�ʵ�ְ汾
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
