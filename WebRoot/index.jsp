<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>学生成绩管理系统</title>
	<link href="style/authority/main_css.css" rel="stylesheet" type="text/css" />
	<link href="style/authority/zTreeStyle.css" rel="stylesheet" type="text/css">
	
	

</head>
<body >
    
    <%@ include file="head.jsp" %>
    
    
    <!-- side menu start -->
	
	<%@ include file="left.jsp" %>
	
    <!-- side menu start -->
   
    <div id="main">
   
      	
    <div style="font-size: 50px;" align="center">

		
	欢迎${manage.username }使用本系统
			
	</div>

    </div>

</body>
</html>
