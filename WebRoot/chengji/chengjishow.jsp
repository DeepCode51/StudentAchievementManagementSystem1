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
    
   
   <script language="javascript" type="text/javascript">

function checkform()
{
	 
	
	return true;
	
}


</script>
    
    <!-- side menu start -->
	
	<%@ include file="../left.jsp" %>
	
    <!-- side menu start -->
   
    <div id="main">
   
      	

	<div id="container">
		<div id="nav_links" align="center">
			当前位置：${biaoti }
			
		</div>
		<br/>
		<div class="ui_content">
			
			<form action="${url }" method="post" onsubmit="return checkform()">
<TABLE cellSpacing=0 cellPadding=0 width="60%" align=center border=1>
  	
  	<TR height=>
    <TD align="center" >
    学号:
    </TD>
    <TD align="center"> 
    <input  type="text" name="username"    size="30" value="${bean.user.username }" readonly="readonly" />

    </TD>
    </TR>
    
    <TR height=>
    <TD align="center" >
     姓名:
    </TD>
    <TD align="center"> 
  <input  type="text" name="truename"   size="30" value="${bean.user.truename }" readonly="readonly" />

    </TD>
    </TR>
  	
    
    <TR height=>
    <TD align="center" >
    课程:
    </TD>
    <TD align="center"> 
    <input  type="text" name="kcname"    size="30" value="${bean.kc.kcname }" readonly="readonly" />

    </TD>
    </TR>
    
    <TR height=>
    <TD align="center" >
  分数:
    </TD>
    <TD align="center"> 
    <input  type="text" name="fenshu"    size="30" value="${bean.fenshu }" readonly="readonly" />
    </TD>
    </TR>
    
    
    


    <TR height=>
    <TD align="center" > 操作：</TD>
    <TD align="center"> 

	 <input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" />
    
    </TD>
    </TR>
    
    </TABLE>
    </form>
		
		</div>
	</div>


    </div>

</body>
</html>
