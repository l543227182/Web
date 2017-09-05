<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8"%>
  

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
        <li class="active"><a href="../../index.do"><span class="glyphicon glyphicon-home">&nbsp;数据中心</span></a></li>
        <li><a href="http://blog.lc-studio.cn"><span class="glyphicon glyphicon-eye-open">&nbsp;博客</span></a></li>
        <li><a href="../index/files.do"><span class="glyphicon glyphicon-cloud-download">&nbsp;文件下载</span></a></li>
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
    

     <ul class="nav navbar-nav navbar-right">
      <li>
       <div class="btn-group" >
				   <button type="button" class="btn btn-default dropdown-toggle btn-sm"   
				   		style="margin-right:35px;margin-top: 15px"
				        data-toggle="dropdown">
				    <span class="glyphicon glyphicon-user">
 	 			</span>&nbsp;&nbsp;&nbsp;${user.realname}&nbsp;&nbsp;
				   </button>
				   <ul class="dropdown-menu" role="menu" style="width: 20px; ">
				      <li><a href="../../index.do"><span class="glyphicon glyphicon-cog">&nbsp;主页</span></a></li>
				       <li class="divider"></li>
				      <li><a href="../../index/logoutUser.do"><span class="glyphicon glyphicon-send">&nbsp;登出</span></a></li>				    
				   </ul>
				</div>
      </li> 
      </ul>
   </div>
   
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
 