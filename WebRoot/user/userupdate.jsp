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
	 
	

	
	if (document.getElementById('truenameid').value=="")
	{
		alert("姓名不能为空");
		return false;
	}
	
	if (document.getElementById('passwordid').value=="")
	{
		alert("密码不能为空");
		return false;
	}
	
	if (document.getElementById('phoneid').value=="")
	{
		alert("手机号码不能为空");
		return false;
	}
	valid = /^0?1[3,4,5,6,7,8,9][0,1,2,3,4,5,6,7,8,9]\d{8}$/;
	
	if(!valid.test(document.getElementById("phoneid").value)){
		
		alert('请输入正确的手机格式');
		return false;
	}
	

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
     用户名(学号):
    </TD>
    <TD align="center"> 
    <input  type="text" name="username"  id='usernameid'  size="30" value="${bean.username }" readonly="readonly" />

    </TD>
    </TR>
  	
    
    <TR height=>
    <TD align="center" >
    姓名:
    </TD>
    <TD align="center"> 
    <input  type="text" name="truename"  id='truenameid'  size="30" value="${bean.truename }" />
    </TD>
    </TR>
    
    <TR height=>
    <TD align="center" >
    班级:
    </TD>
    <TD align="center"> 
    <select name="banjiid">
    <c:forEach items="${banjilist}" var="bj">
    <option value="${bj.id }" <c:if test="${bean.banji.bjname==bj.bjname }">selected</c:if> >${bj.bjname }</option>
    </c:forEach>
    
    </select>
    </TD>
    </TR>
    
    
    <TR height=>
    <TD align="center" >
    手机号码:
    </TD>
    <TD align="center"> 
    <input  type="text" name="phone"  id='phoneid'  size="30" value="${bean.phone }" />
    </TD>
    </TR>
    
    <TR height=>
    <TD align="center" >
  密码
    </TD>
    <TD align="center"> 
    <input  type="text" name="password"  id='passwordid'  size="30" value="${bean.password }" />
    </TD>
    </TR>
    
    
    
    <TR height=>
    <TD align="center" >
    性别:
    </TD>
    <TD align="center"> 
    <select name="sex">
    <option value="男" <c:if test="${bean.sex=='男' }">selected</c:if> >男</option>
    <option value="女" <c:if test="${bean.sex=='女' }">selected</c:if> >女</option>
    </select>
    </TD>
    </TR>
    
   


    <TR height=>
    <TD align="center" > 操作：</TD>
    <TD align="center"> 
     <input type="submit" value="提交" style="width: 60px" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
