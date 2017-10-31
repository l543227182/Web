<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>message</title>
    
	
  </head>
  <script type="text/javascript">
	window.onload = function() {
	redirection();
	};
  function redirection()
	{
		    var se=document.getElementById("second");	
			se.innerText=3;
			window.setTimeout("next()", 1000);
	};
	function next()
	{

		   var se=document.getElementById("second");	
			se.innerText=2;
			window.setTimeout("next2()", 1000);
	};
	function next2()
	{

		    var se=document.getElementById("second");	
			se.innerText=1;
			//window.setTimeout("next2()", 1000);
	};
	</script>
  <body style="text-align: center;">
  <br><br><br><br><br><br><br>
  <b style="color: red;font-size: 18;">
       操作错误，回到主页请点击<a href='index.do'>这里</a>
  </b><br>
    <input type="button" name="Submit" value="返回上一页" onclick="javascript:history.back(-1);">
  </body>
</html>
