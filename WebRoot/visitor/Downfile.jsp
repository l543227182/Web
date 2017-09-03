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
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>   
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/jquery.js"></script>
   
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]--> 
  </head>
  
  <script type="text/javascript">
  function downloadFile(id){
	  window.location.href="downloadFile.do?fileID="+id;
  }
  
  function pageTO(){
	  $pageNO=$("#pageNumInput").val();
	  if(isNull($pageNO)){
		  alert("请输入页码");
		  return ;
	  }
	  if($pageNO<1||$pageNO>${p.totalPage}){
		  alert("不存在的页码");
		  return ;
	  }
	  window.location.href="files.do?pageNo="+$pageNO;
  }
  
  function searchButton(){
	 $val= $("#SearchText").val();
	 if(isNull($val)){
		 alert("请输入关键字");
		 return ;
	 }	
	//window.location.href="SearchFile.do?keyWord="+$val;
	$form=$("form").prepend($("#SearchText"));
	$form.attr("action","SearchFiles.do");
	$form.attr("method","post").submit(); 
  }
  </script>
  <body style="padding-top: 54px">

  

<nav class="navbar   navbar-default  navbar-fixed-top " role="navigation">
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
        <li><a href="../index/files.do"><span class="glyphicon glyphicon-cloud-download">&nbsp;文件下载</span></a></li>
       <li><a href="../index/articles.do"><span class=" glyphicon glyphicon-book">&nbsp;所有文章</span></a></li>
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
   
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
 

  <div class="container" >     
 	<div class="panel panel-primary" >  
 	 <div class="panel-heading">    
 	  <h3 class="panel-title">文件下载区</h3>
 	     </div> 
 	       <div class="panel-body">   	     
 				<div class="well well-lg" >  
 						<form id="deleteFileForm">
		   <table class="table table-hover">
		     <caption>
	
			<div style="float: right; margin-left: 18px">
	        <button class="btn btn-default"  onclick="searchButton()">文件搜索</button>
	        </div>
	        <div class="form-group" style="float: right;">
		     
		          <input type="text"  id="SearchText" class="form-control" placeholder="关键字"  name="keyWord" style="width: 120px">
		       
	        </div>        
      	
			</caption>
		   
		   <thead>
		      <tr>
		      	 <th></th>
		         <th>文件名</th>	
		         <th>文件大小</th>	         
		         <th>上传用户</th>
		         <th>上传日期</th>		        
		         <th>操作	</th>		       
		      </tr>
		   </thead>
   <tbody>
   <c:if test="${p!=null}">
  
     <c:forEach items="${p.list}" var="page" varStatus="index">
     
     	<tr class="success">
          <td> 
          ${index.index+1}  
      	 </td>
         <td>${page.filename}</td>
         <td>${page.size}KB</td>
         
       	 <td>
       	 <c:if test="${page.realname==''}">
       	 		${user.realname}
       	 </c:if>
       	
       	  <c:if test="${page.realname!=''}">
       	 		${page.realname}
       	 </c:if>
       	 </td>
         <td>
         	<fmt:formatDate value="${page.createTime}" pattern="yyyy年MM月dd日 "/>        
         </td>
         
         <td>
			<button type="button" class="btn btn-primary btn-sm" onclick="downloadFile('${page.id}')" >下载 </button>
		</td>
      </tr>
     </c:forEach>
   </c:if>
   </tbody>
   </table> 
   <c:if test="${p!=null }">
  <ul class="pager">
  <c:if test="${p.pageNo==1}">
  <li><a href="#">上一页</a></li>
  </c:if>
   <c:if test="${p.pageNo-1>=1}">
  <li><a href="files.do?pageNo=${p.pageNo-1}">上一页</a></li>
  </c:if>
  <c:if test="${(p.pageNo-2)>=1}">
  	<li><a href="files.do?pageNo=${p.pageNo-2}">${p.pageNo-2}</a></li>
  </c:if> 
  
 <c:if test="${(p.pageNo-1)>=1}">
  	<li><a href="files.do?pageNo=${p.pageNo-1}">${p.pageNo-1}</a></li>
  </c:if>   
  	<li><a href="files.do?pageNo=${p.pageNo}">${p.pageNo}</a></li>
  <c:if test="${p.totalPage>=(p.pageNo+1)}">
  	<li><a href="files.do?pageNo=${p.pageNo+1}">${p.pageNo+1}</a></li>
  </c:if>
  
  <c:if test="${p.totalPage>=(p.pageNo+2)}">
  	<li><a href="files.do?pageNo=${p.pageNo+2}">${p.pageNo+2 }</a></li>
  </c:if>
  <c:if test="${p.pageNo+1<=p.totalPage}">
  <li><a href="files.do?pageNo=${p.pageNo+1}">下一页</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
  </c:if>
   <c:if test="${p.pageNo+1>p.totalPage}">
  <li><a href="#">下一页</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
  </c:if>
  <li>共${p.totalPage}页&nbsp;&nbsp;
 <li>第<a ><input id="pageNumInput" type="text" size="6px"></a>页</li>&nbsp;&nbsp;
 <li><a href="javascript:pageTO()">GO</a></li>
</ul>
</c:if>

   </form>
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

