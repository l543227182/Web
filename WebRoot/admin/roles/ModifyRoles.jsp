<%@ page language="java" import="java.util.*"  isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/LC"	 prefix="lc" %>

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
   function validateForm(){
		var $name=$("input[name='NAME']");
		if(isNull($name.val())){
			alert("请输入角色名!!");
			$name.focus();
			return false;
		}
		if(!confirm("确认提交？")){
			return;
		}
		var $form=$("form[name='addRoleForm']");
		$form.attr("action","ModifyRoles.do").attr("method","post").submit();
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
   <li><a href="../notice/NoticeCenterPage.do">发布公告</a></li>
  </lc:if>
  <lc:if PID="${user.PID}" value="16">
   <li  class="active"><a href="../roles/listRoles.do">权限管理</a></li>
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
 				<div class="well well-lg"  >  
 			<form name="addRoleForm" >
    <table width="776" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td align="left" style="border-bottom:1px solid #E5E5E5"></td>
            </tr>
			<tr> 
              <td height="25" align="left">            
	            	请填写角色信息	
	          </td>
            </tr>
      </table>
		   
	       <!--书签插入位置-->
		   <table width="776" border="0" cellspacing="0" cellpadding="0">
         	<tr>
         		<td id="card1" width="776" align="center" bgcolor="#3D84CD" style="cursor:hand; color:#ffffff; font-size:14px; font-weight:bold">
         			基本信息</td>			
            </tr>   
          </table>
	<table width="776" border="0" cellspacing="0" cellpadding="0" style="border:1px solid #E5E5E5">
      <tr> 
        <td align="center"> 
         	
            <table width="776" border="0" cellspacing="0" cellpadding="0">	         
         	<tr>
         		<td colspan="4" align="left">
         			<font style="color:#FAAD45">*</font> 为必填信息:
         		</td>
            </tr>   
          </table>
          <table width="776" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="20" align="center" style="color:#FAAD45">*</td>
                <td width="100" height="35" align="center">角色名称：</td>
                <td width="180" height="35" align="left" valign="middle">
                  <span class="table_k_bg1"> 
                  	<input type="hidden" name="ID" value="${role.ID }" />
                  	<input type="text" name="NAME" maxlength="100" size="20" value="${role.NAME }" />
                  </span>
                </td>
                <td align="left" valign="middle">&nbsp;</td>
              </tr>
            </table>
            <table width="776" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="20" align="center" style="color:#FAAD45">*</td>
                <td width="100" height="35" align="center">添加权限：</td>
                <td width="180" height="35" align="left" valign="middle">
                  <span class="table_k_bg1"> 
                  	<input type="checkbox" id="selectPermission" name="selectPermission" onclick="createText()"/>
                  </span>
                </td>
                <td align="left" valign="middle">&nbsp;</td>
              </tr>
            </table>
            <div id="divTable" style="display:none">
            <table width="776" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
              	<td width="100">&nbsp;</td>
              	<td align="left" valign="middle" width="30">允许</td>
                <td width="200" height="35" align="left">权限：</td>
                <td align="left" valign="middle" width="30">允许</td>
                <td width="200" height="35" align="left">权限：</td>            
              </tr>
              
              <c:forEach items="${p}" step="2"  var="per" varStatus="pp">
               <tr> 
              	<td width="100">&nbsp;</td>
              	 <td align="left" valign="middle" width="30"> 
              	 <input type="checkbox" name="addPermission" 
              	  <c:forEach items="${permission}" var="pm">
              	    <c:if test="${pm==per.value}">
              	      checked="checked"
              	    </c:if>
              	 </c:forEach>
              	  value="${per.value }"/> </td>
              	
                <td width="200" height="35" align="left"> ${per.name} </td>
               
                <td align="left" valign="middle" width="30">
                 <input type="checkbox" name="addPermission"
                  <c:forEach items="${permission}" var="pm">
              	    <c:if test="${pm==p[pp.index+1].value}">
              	      checked="checked"
              	    </c:if>
              	 </c:forEach>
                  value="${p[pp.index+1].value}"/>
                </td>
                <td width="200" height="35" align="left">
                	${p[pp.index+1].name}
                </td>               
             	
               </tr>
              </c:forEach>
              <tr>
              	<td colspan="5" align="center">
              		<input type="button" value="全选" onclick="selectAll();"/>
              		<input type="button" value="清除" onclick="clearAll();"/>
              	</td>
              </tr>                                    
            </table>
            <input id ="pid" type="hidden" value="2199023255551" />
            </div><br><br><br>
           
           <table width="776" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="20" align="center" style="color:#FAAD45">&nbsp;</td>
                <td width="100" height="35" align="center">备注：</td>
                <td width="180" height="35" align="left" valign="middle">
                  <span class="table_k_bg1"> 
                  	<textarea name="REMARK" rows="8" cols="32" onKeyPress="if (event.keyCode ==39||event.keyCode ==34) event.returnValue = false;">${role.REMARK}  </textarea>
                  </span>
                </td>
                <td align="left" valign="middle">&nbsp;（请勿输入含有“<font color="#FF0000">'</font>”和“<font color="#FF0000">"</font>”符号的字符）</td>
              </tr>
            </table>
				<!--各个书签页的公共操作按钮-->
          <table width="776" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>             
              <td width="500" height="35" align="center" valign="middle">                            
			  <input type="button"  value="提交" onclick="validateForm()"/>
			  <input type="reset" value="重置" />  
			  </td>                            
              <td width="282" align="center" valign="middle">&nbsp;                      	
              </td>
            </tr>
          </table>
          </td></tr></table>  
		</form>              
 				
			  
  		 </div>
   		</div>
  	 </div>
   </div>  
  </body>
</html>

