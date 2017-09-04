<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/LC"  prefix="lc"%>
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
    <script type="text/javascript" src="../../js/jquery.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
     <script type="text/javascript">  
    i = 1;  
    j = 1;
	
    $(document).ready(function(){  
          
        $("#btn_add").click(function(){  
            	$tr= $("#trDemo").clone();
            	$trCh=$tr.children();
            //	alert($trCh.size());
            	$($trCh[0]).children("input").attr("name","filename"+i);
            	$($trCh[1]).children("input").attr("name","filename"+i);
            	$($trCh[2]).children("input").attr("name","open_filename"+i);
            	$($trCh[3]).children("input").removeAttr("disabled");
				
             	i++;  
             	$temp=$("#fileTable").children("tbody").children(":last-child").before($tr);             
        });           
    });  
  
    function del_1(node){     	
    	$(node).parent().parent().remove();  
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
  
   <!-- <h1>springMVC字节流输入上传文件</h1>   
    	<form name="userForm1" action="/springMVC7/file/upload" enctype="multipart/form-data" method="post">  
        <div id="newUpload1">  
            <input type="file" name="file">  
        </div>        
        <input type="button" id="btn_add1" value="增加一行" >  
        <input type="submit" value="上传" >  
    </form>   --> 
   
 	<div class="panel panel-primary">  
 	 <div class="panel-heading">    
 	  <h3 class="panel-title">文件管理中心</h3>
 	  </div> 
 	       <div class="panel-body">   	     
 			 <div class="well well-lg"  style="height: 98%">  
 			 <form method="post"  id="upLoadFile" action="upLoadFiles.do" class="form-horizontal"  
   											enctype="multipart/form-data">   
   											<input type="hidden" name="UserId" value="${user.id}"> 	
	<table class="table" id="fileTable">
   		<tbody>	  											
	      <tr>
	         <td>
	          <div class="form-group">
			      <label for="name">文件名称</label>		      
	  		 </div>
	  		 </td> 
	  		 <td>
	         	上传
	  		 </td>
	  		 <td>
	  		 	不公开
	  		 </td>
	  		 <td>
	         		<input type="button" class="btn btn-default" id="btn_add" value="添加一个文件 " > 
	  		 </td>  
	      </tr>
	      
	      <tr id="trDemo">
	     
	      		<td>	     
			      <input type="text" class="form-control" id="name"  style="width:220px"
			         placeholder="请输入名称" name="filename">
	      		</td>
	         	<td>				  			  				 	       
			      <input type="file" name="filename" id="inputfile"/>				           			   	
				</td>  
				
				<td>
						
      			<input type="checkbox" id="inlineCheckbox2" name="open_filename" >  
				</td>
			  <td>
			 <input type='button' class='btn btn-default'
					id='btn_add' onclick='del_1(this)'  value='删除' disabled="disabled" >
			  </td>     
	     </tr>     	    	
	     	 <tr>
	     	  <td></td>
	     	  <td></td>
	     	  <td></td>
		         <td>	         	
	     		 	<button type="submit" class="btn btn-default">提交</button>
		         </td>
	         </tr>
        
		   </tbody>   
		   </table>
		 </form> 
		 
    	
		   </div>
		   </div>
		   </div>
		   </div>  
 


  </body>
</html>

