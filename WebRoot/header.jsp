<%@page import="com.wuhei.cms.web.context.CmsWebContext"
	import="com.wuhei.cms.login.LoginUser"
	import="com.wuhei.cms.web.utils.*" 
	import="com.wuhei.cms.utils.Utils"  
	import="com.opensymphony.xwork2.util.ValueStack"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
  
<%
	//http://terryjs.iteye.com/blog/767699
	//获取封装输出信息的 ValueStack 对象  
	ValueStack valueStack = (ValueStack) request
			.getAttribute("struts.valueStack");
%>

<%
	LoginUser loginUser = CmsWebContext.getCurrentUser();
	String roleCode = "ROLE_NULL";
	if (loginUser != null) {
		roleCode = loginUser.getRolecode();
	}
	Navigation navigation = (Navigation) request
			.getAttribute("navigation");
	String currentHeaderTab = navigation.getCurrentHeaderTab();
	// "http://127.0.0.1:8080/classInfo/"
	String basePath = navigation.getBasePath();
	// e.g. "http://127.0.0.1:8080/classInfo/acamgr/"
	String baseUrl = navigation.getBaseUrl();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<base href="<%=basePath%>">

<title>课程信息管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<s:url value="css/common.css"/>" rel="stylesheet">
<link href="<s:url value="css/base.css"/>" rel="stylesheet">
<link href="<s:url value="css/page.css"/>" rel="stylesheet">
<link href="<s:url value="css/theme.blue.css"/>" rel="stylesheet">
<link href="<s:url value="css/theme.default.css"/>" rel="stylesheet">
<link href="<s:url value="css/richEditor/font-awesome.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<s:url value="css/richEditor/simditor.css"/>"
	rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="images/icon.ico">

</head>

<script type="text/javascript">
    var basePath = '<%=basePath%>';
	var baseUrl = '<%=baseUrl%>';
</script>

<body>
	<div id="wapper">
		<header>
		<div id="header">
			<div class="w1200 bc">
				<a href="#" ><img src="images/logo.png" > </a>

				<div id="logout" class="fr f14">
					<p>
						欢迎<span class="m10"><%=loginUser.getShowname()%></span>&nbsp;<span
							class="ml10"><a href="">退出</a> </span>
					</p>
				</div>
			</div>

		</div>
		<div id="menu">
			<ul class="w1200 tl bc f16">
				<s:url id="listNoticeUrl" action="listNotice.action" />
				<li
					<%if (currentHeaderTab.equalsIgnoreCase(PageUtils.HEADER_TAB_MAIN)) {%>
					class="active" <%}%>><s:a href="%{listNoticeUrl}">通知管理</s:a></li>
				<s:url id="listTeacherUrl" action="listTeacher.action" />
				<li
					<%if (currentHeaderTab.equalsIgnoreCase(PageUtils.HEADER_TAB_TEACHER)) {%>
					class="active" <%}%>><s:a href="%{listTeacherUrl}">教师管理</s:a>
				</li>
				<s:url id="listStudentUrl" action="listStudent.action" />
				<li
					<%if (currentHeaderTab.equalsIgnoreCase(PageUtils.HEADER_TAB_STUDENT)) {%>
					class="active" <%}%>><s:a href="%{listStudentUrl}">学生管理</s:a>
				</li>
				<s:url id="listMajorUrl" action="listMajor.action" />
				<li
					<%if (currentHeaderTab.equalsIgnoreCase(PageUtils.HEADER_TAB_MAJOR)) {%>
					class="active" <%}%>><s:a href="%{listMajorUrl}">专业管理</s:a></li>
				<s:url id="listCourseUrl" action="listCourse.action" />
				<li
					<%if (currentHeaderTab
					.equalsIgnoreCase(PageUtils.HEADER_TAB_CACTIVITY)) {%>
					class="active" <%}%>><s:a href="%{listCourseUrl}">课程活动</s:a></li>
				
				<%
					if (roleCode.equalsIgnoreCase(Utils.ROLE_ACAMGR)) { // 如果是管理员
				%>			
				<s:url id="listUserUrl" action="listUser.action" />
				<li
					<%if (currentHeaderTab.equalsIgnoreCase(PageUtils.HEADER_TAB_USERLIST)) {%>
					class="active" <%}%>><s:a href="%{listUserUrl}">用户列表</s:a>
				</li>
				
				<%
					}
				%>
				<s:url id="onlineUrl" action="coming.action" />
				<li
					<%if (currentHeaderTab
						.equalsIgnoreCase(PageUtils.HEADER_TAB_ONLINE)) {%>
					class="active" <%}%>><s:a href="%{onlineUrl}">网上答疑</s:a>
				</li>
				<s:url id="helpUrl" action="coming.action" />
				<li><s:a href="%{helpUrl}">用户手册</s:a>
				</li>
			</ul>
		</div>
		</header>