<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="side">
		<div id="left_menu">

		 </div>
		 <div id="left_menu_cnt">
		 	<div id="nav_module">
		 		<img src="images/common/module_2.png" width="210" height="58"/>
		 	</div>
		 	<div >
		 	<br/><br/><br/><br/>
		 		
		 	
		 	<c:if test="${manage.role=='管理员'}">
		 	&nbsp;&nbsp;&nbsp;&nbsp;	
		 	<a href="kcServlet/kclist" >课程管理</a>	
		 	<br/><br/>
		 	
		 	&nbsp;&nbsp;&nbsp;&nbsp;	
		 	<a href="banjiServlet/banjilist" >班级管理</a>
		 	<br/><br/>
		 	
		 	&nbsp;&nbsp;&nbsp;&nbsp;
		 	<a href="userServlet/userlist" >学生管理</a>	
		 	<br/><br/>
		 	
		 	&nbsp;&nbsp;&nbsp;&nbsp;
		 	<a href="userServlet/userlist2" >成绩管理</a>	
		 	<br/><br/>
		 
		 	</c:if>
		 	
		 	<c:if test="${manage.role=='学生用户'}">
		 	
		 	
		 	&nbsp;&nbsp;&nbsp;&nbsp;
		 	<a href="userServlet/userlist3" >个人信息查询</a>	
		 	<br/><br/>
		 	
		 	&nbsp;&nbsp;&nbsp;&nbsp;
		 	<a href="chengjiServlet/chengjilist2" >个人成绩查询</a>	
		 	<br/><br/>
		 
		 	</c:if>	
		 	
		 	
		 	</div>
		 </div>
	</div>
