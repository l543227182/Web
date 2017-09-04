<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8"%>
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
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>   
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/jquery.form.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <script type="text/javascript">
  
  	function upLoadPic(){
  		var options={				
  			url:"uploadPic.do",
  			dataType:"json",
  			type:"post", 			
  			success:function(data){
  				$("#imgHead").attr("src",data.url);
  				$("#path").attr("value",data.path);
  				//alert(data.path);
  			}	
  		};		
  		//jquery.form使用
  		$("#ModifyDataForm").ajaxSubmit (options);
  		//$.ajax(options);
  	}
  	
	function validateRegisterForm(){
		var $file=$("input[name='pic']");	
		var $realName=$("input[name='realname']");
		//密码验证	
		var $passwd1=$($("input[name='password']")[0]);
		var $passwd2=$("input[name='repassword']");
		if(!isNull($passwd1.val())){
			if ($passwd1.val().length < 6 || $passwd1.val().length > 14) {
				alert("您的密码少于6位或多于14位!");
				$passwd1.val("");
				$passwd1.focus();
				return false;
			}
			
			if(isNull($passwd2.val())){
				alert("重复密码不能为空！！");
				$passwd2.focus();
				return false;
			}
			if($passwd2.val()!=$passwd1.val()){
				$passwd1.val("");
				$passwd2.val("");
				$passwd1.focus();
				alert("两次密码不相同");
				return false;
			}
		} 	
		
		
		
		
		//邮箱验证
		var $email=$("input[name='email']");
		//alert($email.val());
		if(!isNull($email.val())&&!isEmail($email.val())){
			alert("你的Email不符合规范!");
			$email.val("");
			$email.focus();
			return false;
		}
			   if(isNull($email.val())
				&&isNull($passwd1.val())
				&&isNull($passwd2.val())
				&&isNull($realName.val())
				&&isNull($file.val())){
			alert("无修改");
			return false;
		}
		
		
		if(!confirm("确认提交吗？")){
			return false;
		}
		
		//提交表单
		var $form=$("#ModifyDataForm");
		   	$form.attr("action","ModifyData.do");
		  	$form.attr("method","post").submit();
	}
  </script>
  <body style="padding-top: 54px">
	<%@ include file="head/head.jsp" %>
	
 <div class="panel panel-default"  style="float: left;width: 24% ;height: 95% ;border: 0px;">

  <ul class="nav nav-pills nav-stacked" style="margin-left: 6px">
   <li><a href="index.do">主页</a></li>
   <li  class="active"><a href="ToModifyPage.do">修改资料</a></li>
	<lc:if PID="${user.PID}" value="4">
   <li><a href="file/filePage.do">文件管理</a></li>
   </lc:if>
   <lc:if PID="${user.PID}" value="2">
   <li><a href="article/toArticleCenter.do">发布文章</a></li>
   </lc:if>
   <lc:if PID="${user.PID}" value="64">
   <li><a href="data/DataCenter.do">数据管理</a></li>
   </lc:if>
   <lc:if PID="${user.PID}" value="8">
   <li><a href="notice/NoticeCenterPage.do">发布公告</a></li>
  </lc:if>
  <lc:if PID="${user.PID}" value="16">
   <li><a href="roles/listRoles.do">权限管理</a></li>
   </lc:if>
   <lc:if PID="${user.PID}" value="32">
   <li><a href="user/UserManager.do">用户管理</a></li>
   </lc:if>
   </ul>
   </div>
 	<div class="container" style="float:right ;width:76%;">
 	
 	
 	<div class="panel panel-primary">  
 	 <div class="panel-heading">    
 	  <h3 class="panel-title">資料修改</h3>
 	     </div> 
 	       <div class="panel-body">   
 	     
 				<div class="well well-lg"  style="height: 98%">
   				<form method="post"  id="ModifyDataForm" class="form-horizontal"  
   											enctype="multipart/form-data">
   				<div class="form-group" align="center" style="padding-left: 80px;"> 
   					<label class="col-sm-2 control-label">你的头像</label>
   					<img style="height: 100px;width: 80px;float: left; border-radius:50%; margin-left: 50px"
						alt="^-^" id="imgHead"  src="http://localhost:8088/image-web/${user.image.path}">
   				</div>
   				<div class="form-group" align="center" style="padding-left: 80px;">
   				   <label class="col-sm-2 control-label">更新头像</label>
   				   <input type="hidden" id="path" name="image.path">
   				   <input type="file" name="pic" onchange="upLoadPic()">
   				</div>
			   <div class="form-group" align="center" style="padding-left: 80px;">
			    <label class="col-sm-2 control-label">用戶名</label>
			    <div class="col-sm-10" style="height:42px;width:280px">
			      <p class="form-control-static">${user.username}</p>
			      <input type="hidden" name="id" value="${user.id}">
			    </div>
			  </div>
			   
			    <div class="form-group" style="padding-left: 80px;">
			      <label for="lastname" class="col-sm-2 control-label">姓名</label>
			      <div class="col-sm-10" style="height:42px;width:280px">
			         <input  type="text"   class="form-control" name="realname" 
			            placeholder="请输入姓名">
			      </div>
			   </div>
			   
			    <div class="form-group" style="padding-left: 80px;">
			      <label for="lastname" class="col-sm-2 control-label" >密码</label>
			      <div class="col-sm-10" style="height:42px;width:280px">
			         <input  type="password"   class="form-control" name="password" 
			            placeholder="请输入密码">
			      </div>
			   </div>
			   
			   <div class="form-group" >
			      <label  for="lastname" class="col-sm-2 control-label"style="margin-left: 67px;">重复密码</label>
			      <div class="col-sm-10" style="height:42px;width:280px">
			         <input  type="password"    class="form-control" name="repassword" 
			            placeholder="请输入重复密码">
			      </div>
			   </div>
			   
			    <div class="form-group" style="padding-left: 80px;">
			      <label for="lastname" class="col-sm-2 control-label">邮箱</label>
			      <div class="col-sm-10" style="height:42px;width:280px">
			         <input  type="text"   class="form-control" name="email" 
			            placeholder="请输入邮箱">
			      </div>
			   </div>
			   
			 	  <div class="form-group" style="padding-left: 280px;">   
				 <label>
      		 	<input type="radio" name="gender" id="optionsRadios1" 
        		 value="1" checked="checked"> 男   
        		 </label>
        		  &nbsp;   
        		      
   				<label>
         		 <input type="radio" name="gender" id="optionsRadios2" 
        		 value="0">
       		  	女  </label>
         
			 </div>
			 <div class="form-group" style="padding-left: 400px;">  
			 <button type="button" class="btn btn-primary" 
						 onclick="validateRegisterForm()"> 确认修改  </button>
			 </div>
			  </form>
			</div> 
			</div> 
	</div>
</div>
   
  
  </body>
</html>

