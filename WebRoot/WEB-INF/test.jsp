<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
   <p class="w800 mt50 bc h50" onclick="test();">���</p>
  </body>
  
  <script src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
	alert("haha");
	
	/*
	**AJAX����
	*/
	function test() {
	//var params=$("#newReport input").serialize();
		//alert(params);
		var cid=1;
		$.ajax({
			cache : true,
			type : "POST",
			url : "<s:url value="/ajax/viewIndex.action"/>",//�˴�����ʹ�õ����Ŵ���˫����
			dataType : 'json',
			data : {
			"csettingid" : cid
			},

			error : function(request) {
				alert("Ajax ����");
			},

			success : function(data) {				
				alert(data);
				console.log(data);
			}
		});
		
	}

</script>
</html>