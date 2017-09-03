<%@ page language="java" import="java.util.*"  isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/LC" prefix="lc" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>lc-studio</title>

       <!-- Bootstrap -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>   
    <script type="text/javascript" src="../../js/base.js"></script>
    <script type="text/javascript" src="../../js/jquery.js"></script>
   
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]--> 
  </head>
  <script type="text/javascript">
  	function postNotice(id){
  		window.location.href="postNotice.do?id="+id;
  	}
  </script>
  <body style="padding-top: 54px">

  

<%@ include file="../head.jsp" %>
  <div class="panel panel-default"  style="float: left;width: 24% ;height: 95% ;border: 0px;">

   <ul class="nav nav-pills nav-stacked" style="margin-left: 6px">
   <li ><a href="../index.do">主页</a></li>
   <li><a href="../ToModifyPage.do">修改资料</a></li>
	<lc:if PID="${user.PID}" value="4">
   <li><a href="../file/filePage.do">文件管理</a></li>
   </lc:if>
   <lc:if PID="${user.PID}" value="2">
   <li><a href="../article/toArticleCenter.do">发布文章</a></li>
   </lc:if>
   <lc:if PID="${user.PID}" value="64">
   <li><a href="../data/DataCenter.do">数据管理</a></li>
   </lc:if>
   <lc:if PID="${user.PID}" value="8">
   <li  class="active"><a href="../notice/NoticeCenterPage.do">发布公告</a></li>
  </lc:if>
  <lc:if PID="${user.PID}" value="16">
   <li ><a href="../roles/listRoles.do">权限管理</a></li>
   </lc:if>
   <lc:if PID="${user.PID}" value="32">
   <li><a href="../user/UserManager.do">用户管理</a></li>
   </lc:if>
   </ul>
   </div>
  <div class="container" style="float:right ;width:76%;">     
 	<div class="panel panel-primary" >  
 	 <div class="panel-heading">    
 	  <h3 class="panel-title">公告管理</h3>
 	     </div> 
 	       <div class="panel-body">   	     
 				<div class="well well-lg" >  
 				<button type="button" class="btn btn-primary" onclick="postNotice('${n.id}')">发布此公告</button>
				
 					<h2 align="center">${n.title}</h2>
 					
 					<address style="text-align: center;">
 					<abbr >创建日期:
					<fmt:formatDate value="${n.time}" pattern="yyyy年MM月dd日hh时 "/> 
						&nbsp;&nbsp;&nbsp;作者:&nbsp;${n.author}</abbr>
						
					</address>
					<br>
 					<div style="padding-left: 10px">
 					${n.content}
 					</div>
   				 <ul class="nav nav-pills nav-stacked" style="margin-left: 6px">
    			 <li class="active" style="text-align: center;">
    			 <a href="javascript:history.go(-1)">
    			 <span class=" glyphicon glyphicon-pencil">&nbsp;返回</span></a></li>
                </ul>
   </div>
   </div>
   </div>
   </div>  
  </body>
</html>

