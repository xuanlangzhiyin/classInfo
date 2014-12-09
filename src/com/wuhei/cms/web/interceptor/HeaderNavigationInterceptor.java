package com.wuhei.cms.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.wuhei.cms.action.CactivityAction;
import com.wuhei.cms.action.MajorAction;
import com.wuhei.cms.action.StudentAction;
import com.wuhei.cms.action.TeacherAction;
import com.wuhei.cms.action.UserAction;
import com.wuhei.cms.web.utils.Navigation;
import com.wuhei.cms.web.utils.PageUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * 
 * 用于设置页面中导航栏
 * 
 * 
 */
@SuppressWarnings("serial")
public class HeaderNavigationInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		invocation.addPreResultListener(new PreResultListener() {
			public void beforeResult(ActionInvocation invocation,
					String resultCode) {

				ActionProxy proxy = invocation.getProxy();
				Object action = proxy.getAction();

				HttpServletRequest request = ServletActionContext.getRequest();
				
				String contextPath = request.getContextPath();
				
				String basePath = request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort() + contextPath;
				// e.g. "/acamgr"
				String actionNamespace = proxy.getNamespace(); 
				// e.g. "http://127.0.0.1:8080/classInfo/acamgr/"
				String baseUrl = basePath + actionNamespace+"/";
				// "http://127.0.0.1:8080/classInfo/"
				basePath = basePath+"/";
				
				Navigation navigation = new Navigation();
				navigation.setContextPath(contextPath);
				navigation.setBasePath(basePath);
				navigation.setActionNamespace(actionNamespace);
				navigation.setBaseUrl(baseUrl);
				
				String currentHeaderTab = PageUtils.HEADER_TAB_MAIN;
				if (action instanceof TeacherAction) {
					currentHeaderTab = PageUtils.HEADER_TAB_TEACHER; // 教师
				} else if (action instanceof StudentAction) {
					currentHeaderTab = PageUtils.HEADER_TAB_STUDENT; // 学生
				} else if (action instanceof MajorAction) {
					currentHeaderTab = PageUtils.HEADER_TAB_MAJOR; // 专业
				} else if (action instanceof CactivityAction) {
					currentHeaderTab = PageUtils.HEADER_TAB_CACTIVITY; // 课程活动
				} else if (action instanceof UserAction) {
					currentHeaderTab = PageUtils.HEADER_TAB_USERLIST; // 用户列表
				} 
				navigation.setCurrentHeaderTab(currentHeaderTab);
				
				request.setAttribute("navigation", navigation);
			}
		});

		return invocation.invoke();
	}

}
