<%@ page language="java" import="java.util.*" isELIgnored="false"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script type="text/javascript" src="../js/base.js" ></script>
 	<script type="text/javascript">
 	  
 	  
 	  function searchButton(){
 		 $val= $("#SearchText").val();
 		 if(isNull($val)){
 			 alert("请输入关键字");
 			 return ;
 		 }	
 		//window.location.href="SearchFile.do?keyWord="+$val;
 		$form=$("form").prepend($("#SearchText"));
 		$form.attr("action","SearchData.do");
 		$form.attr("method","post").submit(); 
 	  }
 	$(function(){
 		 var $meg=${message}+"";
		  if($meg==1){
			 alert("用户名或者密码错误!!!");
			 
		  }
 	});
 	
 	  function validateLogin(){
 		  var $username=$("input[name='username']");
 		  if(isNull($username.val())){
 			  alert("用户名不能为空");
 			  $username.focus();
 			  return false;
 		  }
 		
 		 var $password=$("input[name='password']");
 		 if(isNull($password.val())){
			  alert("密码不能为空");
			  $password.focus();
			  return false;
		  }
 		  
 		var $loginForm=$("form[name='loginForm']");
 		$loginForm.attr("method","post");
 		$loginForm.attr("action","loginUser.do").submit();
 	  }
 	</script>
 	
 	<script type="text/javascript">
	var flag=true;
	function loginNameCheck(){
		//alert("login");		
		 var loginName = $("input[name='username']")[1];
		 //alert($(loginName).val());
			loginName= $(loginName).val();
		 if(isNull(loginName)){		
			 return ;
		 }		 
		
		$.ajax({
			   type: "GET",
			   url: "index/UserNameExist.do",
			   async:false,//修改為同步
			   scriptCharset:"utf-8",
			   contentType: "application/x-www-form-urlencoded; charset=utf-8",
			   data: "loginName="+encodeURI(encodeURI(loginName)),
			   success: function(b){
				 
				   if(b=="没有被注册"){
					   $($(".col-sm-10")[3]).removeClass("has-error");
					   document.getElementById("info").innerHTML ="";
					   flag=true;
				   }else if(b=="用户存在"){
					   $($(".col-sm-10")[3]).addClass("has-error");
					   document.getElementById("info").innerHTML = b;					   
					   flag=false;			   
				   }	
				   return ;
			   }
			   }); 
		
		return flag;
	}	
	
	
	function validateRegisterForm(){
		
		//用戶名是否為空
		//LOGIN_NAME
		var $loginName=$($("input[name='username']")[1]);
		if(isNull($loginName.val())){
		     alert("用戶名為空"); 
		    $loginName.focus();
		    return false;
		}
		 if(!loginNameCheck()){
	    	  alert("用户名已存在!!");	    	
	    	  $loginName.focus();
	    	  return false;
	    	 } 
		//姓名驗證
		
		var $realName=$("input[name='realname']");
		if(isNull($realName.val())){
			alert("请填写姓名!!");
			$realName.focus();
			return false;
		}
		//密码验证	
		var $passwd1=$($("input[name='password']")[1]);
		if(isNull($passwd1.val())){
			alert("密码不能为空！！");
			$passwd1.focus();
			return false;
		}else{
			if ($passwd1.val().length < 6 || $passwd1.val().length > 14) {
				alert("您的密码少于6位或多于14位!");
				$passwd1.val("");
				$passwd1.focus();
				return false;
			}
		} 	
		var $passwd2=$("input[name='repassword']");
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
		
		//邮箱验证
		var $email=$("input[name='email']");
		//alert($email.val());
		if(isNull($email.val())||!isEmail($email.val())){
			alert("你的Email不符合规范!");
			$email.val("");
			$email.focus();
			return false;
		}
		
		
		if(!confirm("确认提交吗？")){
			return false;
		}
		
		//提交表单
		var $form=$("form[name='registerForm']");
		   $form.attr("action","RegisterUser.do");
		   $form.attr("method","post").submit();
	}
 	</script>
 	<style type="text/css">
 	.keyword{
     color: red;      
 	}
 	</style>
  </head>
  <body style="padding-top: 54px">
<nav class="navbar  navbar-inverse  navbar-fixed-top " role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">lc-studio</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
       <ul class="nav navbar-nav">
        <li class="active"><a href="../index.do"><span class="glyphicon glyphicon-home">&nbsp;数据中心</span></a></li>
        <li><a href="http://www.lc-studio.cn/blog"><span class="glyphicon glyphicon-eye-open">&nbsp;博客</span></a></li>
        <li><a href="files.do"><span class="glyphicon glyphicon-cloud-download">&nbsp;文件下载</span></a></li>
       <li><a href="#"><span class=" glyphicon glyphicon-book">&nbsp;所有文章</span></a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-hand-right">&nbsp;学习</span><span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">java</a></li>
            <li><a href="#">c++</a></li>
            <li><a href="#">python</a></li>
            <li class="divider"></li>
            <li><a href="#">java框架</a></li>
            <li class="divider"></li>
            <li><a href="#">其他知识</a></li>
          </ul>
        </li> 
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-globe">&nbsp;友情链接</span> <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="http://www.baidu.com">百度</a></li>
            <li><a href="http://www.iteye.com">ITeye</a></li>
            <li><a href="http://www.importnew.com">ImportNew</a></li>
            <li class="divider"></li>
            <li><a href="http://www.csdn.com">csdn</a></li>
            <li><a href="http://www.weibo.com">微博</a></li>
            <li><a href="http://www.lc-studio.cn/MYWEB/index.do">旧站</a></li>
          </ul>
        </li>     
      </ul>
      <div>
    

   </div>
   <c:if test="${user==null}">
      <ul class="nav navbar-nav navbar-right">
        <li><a  data-toggle="modal"  href="#myModal">登录</a></li>      
        <li><a  data-toggle="modal"  href="#registerModal">注册</a></li>
        <li><b> &nbsp;&nbsp; </b></li>      
        <li><b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </b></li>
      </ul>
    </c:if>
    <c:if test="${user!=null }">
     		<ul class="nav navbar-nav navbar-right">
     		  <li>
     		   	<%-- <button type="button" class="btn btn-default btn-sm" 
        		style="margin-right:35px;margin-top: 15px"
        		 onclick="window.location.href='admin/index.do'">
  				<span class="glyphicon glyphicon-user">
 	 			</span>&nbsp;&nbsp;&nbsp;${user.realname}&nbsp;&nbsp;后台
			 	</button> --%>
			 	
			 	 <div class="btn-group" >
				   <button type="button" class="btn btn-default dropdown-toggle btn-sm"   
				   		style="margin-right:35px;margin-top: 15px"
				        data-toggle="dropdown">
				    <span class="glyphicon glyphicon-user">
 	 			</span>&nbsp;&nbsp;&nbsp;${user.realname}&nbsp;&nbsp;
				   </button>
				   <ul class="dropdown-menu" role="menu" style="width: 20px; ">
				      <li><a href="../admin/index.do"><span class="glyphicon glyphicon-cog">&nbsp;后台</span></a></li>
				       <li class="divider"></li>
				      <li><a href="../index/logoutUser.do"><span class="glyphicon glyphicon-send">&nbsp;登出</span></a></li>				    
				   </ul>
				</div>
     		  </li>
     		  
     		  
     		</ul>
    </c:if>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
 
 
  <!--登录模态窗口 -->
 
    <div   class="modal fade" id="myModal" tabindex="-1" role="dialog" 
      aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false">
   <div class="modal-dialog" style="width: 450px">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;            </button>
            <h4 class="modal-title" id="myModalLabel">
            		  登录用户
			</h4>
         </div>
         <div class="modal-body">
         <script type="text/javascript">
          function NoPost(){
        	  return false;
          }
         </script>
       <!-- 登录表单 -->  		
				<form name="loginForm" onsubmit="NoPost()" class="form-horizontal" role="form">
				
			   
   				<div class="form-group">
     			 <label for="firstname"  class="col-sm-2 control-label">用户名</label>
			      <div class="col-sm-10" >
			         <input type="text" style="height:42px;width:220px"  class="form-control" name="username" 
			            placeholder="请输入用户名">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="lastname" class="col-sm-2 control-label">密码</label>
			      <div class="col-sm-10">
			         <input  type="password"  style="height:42px;width:220px"  class="form-control" name="password" 
			            placeholder="请输入密码">
			      </div>
			   </div>
			   <div class="form-group">
			      <div class="col-sm-offset-2 col-sm-10">
			         <div class="checkbox">
			            <label>
			               <input type="checkbox" name="remeberMe"> 请记住我   </label>
			         </div>
			      </div>
			   </div>
			   <input type="submit"  style="margin-left: 300px;" class="btn btn-primary"   onclick="validateLogin()" value="确认登录 ">          
			  </form>
	<!-- 登录表单 end--> 			
           </div>
         
           
          
           
      	</div><!-- /.modal-content -->
      	</div><!-- /.modal -->
      	</div>
 
 
<!-- 模态窗口end -->
 
 <!-- 注册模态窗口 -->
 
     <div   class="modal fade" id="registerModal" tabindex="-1" role="dialog" 
      aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false">
   <div class="modal-dialog" style="width: 550px">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;            </button>
            <h4 class="modal-title" id="myModalLabel">
            		 新用户注册
			</h4>
         </div>
         <div class="modal-body" align="center">
       <!--注册表单 -->  		
				<form  name="registerForm" class="form-horizontal" role="form">
   				<div class="form-group" align="center" style="padding-left: 80px;">
     			 <label for="firstname"  class="col-sm-2 control-label" >用户名</label>
			      <div class="col-sm-10" style="height:42px;width:280px">
			         <input type="text"   class="form-control" name="username" 
			            placeholder="请输入用户名" onblur="loginNameCheck()">      
			      </div>
			         <label id="info" style="width: 90px; text-align: left;" 
			         class="col-sm-2 control-label" for="inputError">
        			 </label>
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
			   
			 	   
			 <label>
      		 <input type="radio" name="gender" id="optionsRadios1" 
        		 value="1" checked="checked"> 男   </label>
        		  &nbsp;&nbsp;&nbsp;   
        		      
   			<label>
         	 <input type="radio" name="gender" id="optionsRadios2" 
        		 value="0">
       		  	女  </label>
         
			 
			  </form>
	<!-- 注册表单 end--> 			
           </div>
           <div class="modal-footer"  align="center" style="padding-right: 40px;">
			<button style="margin-left: 200px" type="button" class="btn btn-default"  data-dismiss="modal">关闭  </button>
			<button type="button" class="btn btn-primary" onclick="validateRegisterForm()"> 确认注册  </button>
           </div>
      	</div><!-- /.modal-content -->
      	</div><!-- /.modal -->
      	</div>
 
 <!-- end 注册模态窗口 -->
 
 

  <div class="panel panel-default"  style="float: left;width: 18% ;height: 95% ;border: 0px;">
   <!-- 最新公告区 -->
<div class="panel panel-default">
      <div class="panel-heading">
         <h4 class="panel-title">
            <a data-toggle="collapse" data-parent="#accordion" 
               href="#collapseOne">
             最新公告   </a>
         </h4>
      </div>
      <div id="collapseOne" class="panel-collapse collapse in">
         <div class="panel-body">
         <center>${n.title}</center>
         <br>
         ${n.content}
         </div>
      </div>
   </div>
   
   
   <a href="#" class="list-group-item active" style="background: #101010; border: #101010;">
   最新文章
</a>
<c:forEach items="${a}" var="a" varStatus="article">
<c:if test="${article.index<=3}">
<a href="index/article.do?id=${a.id }" class="list-group-item">
<span class="badge">新</span>
 ${a.title}</a>
</c:if>
 <c:if test="${article.index>3}">
 <a href="index/article.do?${a.id }" class="list-group-item">
 	${a.title}</a>
 </c:if>
</c:forEach>
   </div>


 <div class="panel panel-default" style="float: right; width: 80%;">
    <div class="panel-heading">数据中心</div>     
   <table class="table table-hover">
   <caption>
       <form class="navbar-form navbar-right" id="dataSearch">
        <div class="form-group">
          <input type="text" id="SearchText" name="keyWords" class="form-control" placeholder="关键字">
        </div>
        <button type="submit" onclick="searchButton()" class="btn btn-default">数据搜索</button>
      </form>
 	</caption>
    <c:if test="${p!=null}">
   <tbody>
  
     <c:forEach items="${p.list}" var="page" >    
      <tr >
         <td >
	<div class="panel panel-default">
      <div class="panel-heading">
         <h4 class="panel-title">   
			<span >
				<a href="../html/${page.id}" title="${page.title}"
				 	>${page.title}</a>		
            	<a data-toggle="collapse" data-parent="#accordion" 
             	   href="#_${page.id}" style="float: right; margin-right:50px">
          			收起
			 	</a>
			 </span>
         </h4>
      </div>
     <div id="_${page.id}" class="panel-collapse collapse in" >
         <div class="panel-body">
        		
<div class="content">

		<div>
		<div class="logo">
			
		</div>
		  ${page.summary }
		</div>
	<br>
		
		<div>
			<div id="d2335594" >
				<a href="" target="_blank" title="${page.author}">${page.author}</a>
				<span class="view">有${page.viewtimes}人浏览</span>
				<span class="date">${page.date}</span>
			</div>
         </div>
 	</div> 

   </div>
   </div>
   </div>
		</td>        
      </tr>   
      </c:forEach>    
            
   </tbody>
    </c:if>   
  </table>
  <br>
   <c:if test="${p!=null}">
  <ul class="pager">
  <c:if test="${p.pageNo==1}">
  <li class="disabled"><a >上一页</a></li>
  </c:if>
   <c:if test="${p.pageNo-1>=1}">
  <li><a href="index.do?pageNo=${p.pageNo-1}">上一页</a></li>
  </c:if>
  <c:if test="${(p.pageNo-2)>=1}">
  	<li><a href="index.do?pageNo=${p.pageNo-2}">${p.pageNo-2}</a></li>
  </c:if> 
  
 <c:if test="${(p.pageNo-1)>=1}">
  	<li><a href="index.do?pageNo=${p.pageNo-1}">${p.pageNo-1}</a></li>
  </c:if>   
  	<li ><a href="index.do?pageNo=${p.pageNo}" ><font size="6px">${p.pageNo}</font></a></li>
  <c:if test="${p.totalPage>=(p.pageNo+1)}">
  	<li><a href="index.do?pageNo=${p.pageNo+1}">${p.pageNo+1}</a></li>
  </c:if>
  
  <c:if test="${p.totalPage>=(p.pageNo+2)}">
  	<li><a href="index.do?pageNo=${p.pageNo+2}">${p.pageNo+2 }</a></li>
  </c:if>
  <c:if test="${p.pageNo+1<=p.totalPage}">
  <li><a href="index.do?pageNo=${p.pageNo+1}">下一页</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
  </c:if>
   <c:if test="${p.pageNo+1>p.totalPage}">
  <li class="disabled"><a>下一页</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
  </c:if>
  <li>共${p.totalPage}页&nbsp;&nbsp;
 <li>第<a ><input id="pageNumInput" type="text" size="6px"></a>页</li>&nbsp;&nbsp;
 <li><a href="javascript:pageTO()">GO</a></li>
</ul>
</c:if>
<c:if test="${p==null}">
<span style="font: 15px;margin-left: 40% ;color:red;">
搜索到0条数据
</span>
<br>
</c:if>
 <br>
   </div>
   
  
   
 
  </body>
</html>

