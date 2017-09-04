<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8"%>
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
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>   
    <script type="text/javascript" src="../../js/base.js"></script>
    <script type="text/javascript" src="../../js/jquery.js"></script>
    <script type="text/javascript" src="../../ckeditor/ckeditor.js"></script>
   <script type="text/javascript" src="../../ckfinder/ckfinder.js"></script>  
   <script type="text/javascript">  
   function postForm(){
	   
	   $title =$("input[name='title']");
	    if(isNull($title.val())){
	    	alert("标题为空");
	    	$title.focus();
	    	return ;
	    }
	   $label =$("input[name='label']");
	   if(isNull($label.val())){
		   alert("标签为空");
	    	$label.focus();
	    	return ;
	    }
	   $form=$("form[name='ArticleForm']");
	   $form.attr("action","addNotice.do");
	   $form.attr("method","post").submit();
	   
	   
   }

</script>  
  </head>
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
 	  <h3 class="panel-title">文章管理</h3>
 	     </div> 
 	       <div class="panel-body">   	 
 	       <form name="ArticleForm">
 				<div class="well well-lg"  >  
					 			<div class="form-group">
							      <label for="name">公告名称</label>
							      <input  type="text" name="title" class="form-control" id="name" 
							         placeholder="请输入名称">
							         <input type="hidden" name="author" value="${user.realname}">
							         <input type="hidden" name="UserID" value="${user.id}">
							   </div>
							   
							    <div class="form-group">
							    <label for="name">文章内容</label>
							    <textarea class="form-control" name="content" rows="20" name="articleContent"  id="articleContent">							   
							    </textarea>
							    	<script type="text/javascript">
							   	     CKEDITOR.replace("articleContent");
							     	</script>
				 				 </div>				
						 <ul class="nav nav-pills nav-stacked" style="margin-left: 6px">
    					<li class="active" style="text-align: center;"><a href="javascript:postForm()"><span class=" glyphicon glyphicon-save ">&nbsp;发布公告</span></a></li>
    					</ul>			
		 		</div>
		 	</form>    
		   </div>
	 </div>
	</div>  
  </body>
</html>

