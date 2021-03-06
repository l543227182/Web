<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/LC"	prefix="lc" %>
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
   <script type="text/javascript">      
  function selectAll(check){
	  $("input[id='chBox']").attr("checked",check);
  }
  function delFile(){
	  var dels=$("input[id='chBox']:checked");
	   	if(dels.size()<=0){
	   		alert("请选择一个");
	   		return;
	   }
	   	if(!confirm("确认删除?!")){
	   		return;
	   	}
	   	$("#deleteArticleForm").attr("action","delData.do");
	   	$("#deleteArticleForm").attr("method","post").submit(); 
  } 
  function PostData(){
	  window.location.href="rePostData.do";
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
	  window.location.href="DataCenter.do?pageNo="+$pageNO;
  }
  
  function removeDuplicateData(){
	//window.location.href="SearchFile.do?keyWord="+$val;
      if(!confirm("确认去重?!")){
          return;
      }
      $("#deleteArticleForm").attr("action","removeDuplicateBean.do");
      $("#deleteArticleForm").attr("method","post").submit();
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
   <li  class="active"><a href="DataCenter.do">数据管理</a></li>
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
 	<div class="panel panel-primary" >  
 	 <div class="panel-heading">    
 	  <h3 class="panel-title">数据管理</h3>
 	     </div> 
 	       <div class="panel-body">   	     
 				<div class="well well-lg"  >  
 		<form id="deleteArticleForm">
		   <table class="table table-hover">
		     <caption>
	
			<div style="float: right; margin-left: 18px">
	        <button class="btn btn-default"  onclick="removeDuplicateData()">数据去重</button>
	        </div>
	  <%--      <div class="form-group" style="float: right;">
		     
		          <input type="text"  id="SearchText" class="form-control" placeholder="关键字"  name="keyWord" style="width: 120px">
		       
	        </div>        
      	--%>
			</caption>
		   <input type="hidden" name="pageNo" value="${p.pageNo}"/>
		   <thead>
		      <tr>
		      	<th>
		      	<input type="checkbox" onclick="selectAll(this.checked)">		      	
		      	<a href="javascript:delFile()"> 删除&nbsp;</a>
		      	</th>
		         <th>id</th>		
		         <th>名称</th>		                  
		         <th>时间</th>
		         <th>作者</th>
		         <th>原链接</th>
		         <th>
		         <button type="button" class="btn btn-primary btn-sm" onclick="PostData()" >发布 
		         </button>
		         </th>		                
		      </tr>
		   </thead>
		   <c:if test="${p!=null}">
   <tbody>
   
     <c:forEach items="${p.list}" var="page" >
     
     	<tr class="warning">
         <td><input type="checkbox" id="chBox" name="ids" value="${page.id}"></td>
         <td>${page.id}</td>
         <td> <a href="../../html/${page.id}">${page.title}</a></td>      
         <td>
         	 ${page.date}           
         </td>
         <td>
          ${page.author}
         </td>
         <td>
         	<a href="http://www.iteye.com${page.srcUrl}">${page.srcUrl}</a>
         </td>
         <td>
			
		</td>
      </tr>
     </c:forEach>
      
   </tbody>
   
   </c:if>
   </table> 
   <c:if test="${p!=null}">
  <ul class="pager">
  <c:if test="${p.pageNo==1}">
  <li class="disabled"><a >上一页</a></li>
  </c:if>
   <c:if test="${p.pageNo-1>=1}">
  <li><a href="DataCenter.do?pageNo=${p.pageNo-1}">上一页</a></li>
  </c:if>
  <c:if test="${(p.pageNo-2)>=1}">
  	<li><a href="DataCenter.do?pageNo=${p.pageNo-2}">${p.pageNo-2}</a></li>
  </c:if> 
  
 <c:if test="${(p.pageNo-1)>=1}">
  	<li><a href="DataCenter.do?pageNo=${p.pageNo-1}">${p.pageNo-1}</a></li>
  </c:if>   
  	<li ><a href="DataCenter.do?pageNo=${p.pageNo}" ><font size="6px">${p.pageNo}</font></a></li>
  <c:if test="${p.totalPage>=(p.pageNo+1)}">
  	<li><a href="DataCenter.do?pageNo=${p.pageNo+1}">${p.pageNo+1}</a></li>
  </c:if>
  
  <c:if test="${p.totalPage>=(p.pageNo+2)}">
  	<li><a href="DataCenter.do?pageNo=${p.pageNo+2}">${p.pageNo+2 }</a></li>
  </c:if>
  <c:if test="${p.pageNo+1<=p.totalPage}">
  <li><a href="DataCenter.do?pageNo=${p.pageNo+1}">下一页</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
  </c:if>
   <c:if test="${p.pageNo+1>p.totalPage}">
  <li class="disabled"><a>下一页</a></li>&nbsp;&nbsp;&nbsp;&nbsp;
  </c:if>
  <li>共${p.totalPage}页&nbsp;&nbsp;
 <li>第<a ><input id="pageNumInput" type="text" size="6px"></a>页</li>&nbsp;&nbsp;
 <li><a href="javascript:pageTO()">GO</a></li>
</ul>
</c:if>
<c:if test="${p==null }">
	<div align="center" > 
	  <p style="font-size: 20px;">你还没写过文章</p>
	</div>
</c:if>
   </form>
   
   </div>
   </div>
   </div>
   </div>  
  </body>
</html>

