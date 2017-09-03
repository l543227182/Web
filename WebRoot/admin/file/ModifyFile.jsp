<%@ page language="java" import="java.util.*"  isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script type="text/javascript" src="../../js/jquery.form.js"></script>


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body style="padding-top: 54px">
<%@ include file="../head.jsp" %>
  <div class="panel panel-default"  style="float: left;width: 24% ;height: 95% ;border: 0px;">

 
   <ul class="nav nav-pills nav-stacked" style="margin-left: 6px">
   <li ><a href="../index.do">主页</a></li>
   <li><a href="../ToModifyPage.do">修改资料</a></li>
	<lc:if PID="${user.PID}" value="4">
   <li  class="active"><a href="../file/filePage.do">文件管理</a></li>
   </lc:if>
   <lc:if PID="${user.PID}" value="2">
   <li><a href="../article/toArticleCenter.do">发布文章</a></li>
   </lc:if>
   <lc:if PID="${user.PID}" value="64">
   <li><a href="../data/DataCenter.do">数据管理</a></li>
   </lc:if>
   <lc:if PID="${user.PID}" value="8">
   <li><a href="../notice/NoticeCenterPage.do">发布公告</a></li>
  </lc:if>
  <lc:if PID="${user.PID}" value="16">
   <li><a href="../roles/listRoles.do">权限管理</a></li>
   </lc:if>
   <lc:if PID="${user.PID}" value="32">
   <li><a href="../user/UserManager.do">用户管理</a></li>
   </lc:if>
   </ul>
   </div>
  <div class="container" style="float:right ;width:76%;">
  <div class="panel panel-primary">  
 	 <div class="panel-heading">    
 	  <h3 class="panel-title">文件管理中心</h3>
 	     </div> 
 	       <div class="panel-body">   
 	       <form id="modifyForm" action="ModifyFileInfo.do" method="post">
			  <table class="table table-hover">	  
				   <tbody>
				      <tr>
				         <td>
				         
				          <div class="form-group">
						      <label for="name">名称</label>
						      <input type="text" class="form-control" id="name" name="FileName"
						      		value="${file.filename}"
						         placeholder="请输入名称">
						   </div>
				         
				         
				         </td>
				    </tr>
				         
				      <tr>
				         <td>
				         <label class="checkbox-inline">
						      <input type="radio" name="open"  value="1"
								<c:if test="${file.open==true}">
								checked =checked
								</c:if>
								> 公开   </label>
						   <label class="checkbox-inline">
						      <input type="radio" name="open" value="0"
						      <c:if test="${file.open==false}">
								checked =checked
								</c:if>
						       > 不公开   </label>
						</td>
				         
				     </tr>	
				     
				     <tr>
				     	<td>
				     		<input type="hidden" name="fileID" value="${file.id}">
				     		<input type="submit" value="确认修改">
				     	</td>
				     </tr>      
				   </tbody>
			   </table> 
			   </form>   
      </div>
      
   </div>   
   </div>


  </body>
</html>

