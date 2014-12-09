<%@page import="com.wuhei.cms.web.context.CmsWebContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>  
<meta charset="utf-8">
<title>课程管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Loading css -->
<link href="<s:url value="css/base.css"/>" rel="stylesheet">
<link href="<s:url value="css/common.css"/>" rel="stylesheet">
<link href="<s:url value="css/page.css"/>" rel="stylesheet">


<link rel="shortcut icon" href="images/icon.ico">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<![endif]-->
</head>
<body>
	<div id="wapper">
		<div id="loginMain">

			<div id="loginBg"></div>
			<div id="loginLogo"></div>
			<div id="loginBox">
				<div class="title"></div>
				<div class="loginInput">
					<form action="<s:url value="/basic_j_spring_security_check" />"
						method="post" onsubmit=" " autocomplete="off">
						<input id="username" name="j_username" type="text" class="inputBox active" placeholder="请输入用户名">
						<input id="password" name="j_password" type="password" class="inputBox" placeholder="请输入密码">
						<p id=""></p>
						<input type="submit" class="btnBL2 f16" value="登 陆">
					</form>
				</div>
			</div>
			<footer>
				<div id="footer"></div>
			</footer>
		</div>
	</div>
</body>
<script src="<s:url value="js/jquery-1.8.3.min.js"/>"></script>
<script type="text/javascript">
	/**
	 * 获取窗口大小
	 * 写入容器类
	 */
	$(document).ready(function() {
		var height = $(window).height();
		$("#wapper").css("min-height", height);
	})

	$(document).ready(function() {
		$("#username").focus();
		$("input").focus(function() {
			$(this).addClass("active");
		});
		$("input").blur(function() {
			$(this).removeClass("active");
		});
	})
</script>
</html>