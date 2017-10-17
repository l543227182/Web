package cn.lcstudio.interceptor;

import cn.lcstudio.bean.Users;
import cn.lcstudio.front.service.rolesService;
import cn.lcstudio.utils.privilegeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LimitInterceptor implements HandlerInterceptor{

	 
	@Autowired
	private rolesService rolesService;
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
	// TODO Auto-generated method stub	   	
	String path=request.getRequestURI();
	//如果请求为.do
	 if(path.contains(".do")){
		Class<? extends Object>  clazz=obj.getClass();
			 //遍历有 RequestMapping 和 AnnotationLimit方法 
		if(clazz.isAnnotationPresent(RequestMapping.class)
    		&&clazz.isAnnotationPresent(privilegeValue.class)){		
		       RequestMapping  methodinfo=clazz.getAnnotation(RequestMapping.class);
		       privilegeValue limitValue=clazz.getAnnotation(privilegeValue.class);
		      
		       for(String value : methodinfo.value()) {
		    	   //如果请求url 包含requestmapping中的value 表示请求的就是这个用户
				  if(path.contains(value)){
					  //在session中得到登陆的用户
			 		  Users u=(Users) request.getSession().getAttribute("user");
					
					  //为空表示还没有登录， 放行
					  if(u==null){
						  return true;
					  }
					  long pid=u.getPID();
					  //操作权限值 与 当前用户角色的权限值  与操作 如果结果等于操作权限值表示用户有权限访问 
					  //如果不相等 表示无权限访问 
					  boolean b=(pid & limitValue.value())==limitValue.value()
							  ?true:false;
					  
					  System.out.println("注释名称:"+value);
				      System.out.println("请求路径:"+path);
					  if(b){
						   System.out.println("有访问权限");
						  return true;
					  }else{
						  System.out.println("么有访问权限");
						  response.setContentType("text/html;charset=UTF-8");
						  response.getWriter().write("<html><head><script>"
						  		+ "alert(\"你没有访问权限\");history.go(-1);"
						  		+ "</script></head><body></body></html>");
						  return false;
					  }
				  	}
		    	}
    		}
   	}
		return true;
		
}

}
