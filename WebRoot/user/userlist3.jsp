<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath %>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>学生成绩管理系统</title>
	<link href="style/authority/main_css.css" rel="stylesheet" type="text/css" />
	<link href="style/authority/zTreeStyle.css" rel="stylesheet" type="text/css">

</head>
<body >
    
    <%@ include file="../head.jsp" %>
    
    
    <!-- side menu start -->
	
	<%@ include file="../left.jsp" %>
	
    <!-- side menu start -->
   
    <div id="main">
   
      	

	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="d5d4d4" >
  <tr>
    <td height="22" colspan="4" background="images/bg.gif" bgcolor="#FFFFFF" class="STYLE3"><div align="center">${biaoti }</div></td>
  </tr>
  <tr>
    <td colspan="4" bgcolor="#FFFFFF" class="STYLE1">
    
    
</div>
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=1>
  	
  	<TR >
    <TD align="center" >用户名（学号）</TD>
    <TD align="center" >姓名</TD>
    <TD align="center" >密码</TD>
    <TD align="center" >班级</TD>
    <TD align="center" >手机号码</TD>
    <TD align="center" >性别</TD>
    <TD align="center" >操作</TD>
    
   
    </TR>
    <c:forEach items="${list}"  var="bean">

    <TR >
    <TD align="center" >
    ${bean.username }&nbsp;
    </TD>
    <TD align="center" >
    ${bean.truename }&nbsp;
    </TD>
    <TD align="center" >
    ${bean.password }&nbsp;
    </TD>
    <TD align="center" >
    ${bean.banji.bjname }&nbsp;
    </TD>
    <TD align="center" >
    ${bean.phone }&nbsp;
    </TD>
    <TD align="center" >
    ${bean.sex }&nbsp;
    </TD>
   
    <TD align="center" >
    
    <a href="${url2 }show?id=${bean.id }">查看</a> &nbsp; &nbsp; &nbsp;
    </TD>

    </TR>
    </c:forEach>
    
  
    
    </TABLE>
    
    </td>
  </tr>
</table>


    </div>

</body>
</html>
