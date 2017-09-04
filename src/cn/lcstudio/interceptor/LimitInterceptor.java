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
	//�������Ϊ.do
	 if(path.contains(".do")){
		Class<? extends Object>  clazz=obj.getClass();
			 //������ RequestMapping �� AnnotationLimit���� 
		if(clazz.isAnnotationPresent(RequestMapping.class)
    		&&clazz.isAnnotationPresent(privilegeValue.class)){		
		       RequestMapping  methodinfo=clazz.getAnnotation(RequestMapping.class);
		       privilegeValue limitValue=clazz.getAnnotation(privilegeValue.class);
		      
		       for(String value : methodinfo.value()) {
		    	   //�������url ����requestmapping�е�value ��ʾ����ľ�������û�
				  if(path.contains(value)){
					  //��session�еõ���½���û�
					  Users u=(Users) request.getSession().getAttribute("user");
					
					  //Ϊ�ձ�ʾ��û�е�¼�� ����
					  if(u==null){
						  return true;
					  }
									 
					  long pid=u.getPID();
					  //����Ȩ��ֵ �� ��ǰ�û���ɫ��Ȩ��ֵ  ����� ���������ڲ���Ȩ��ֵ��ʾ�û���Ȩ�޷��� 
					  //�������� ��ʾ��Ȩ�޷��� 
					  boolean b=(pid & limitValue.value())==limitValue.value()
							  ?true:false;
					  
					  System.out.println("ע������:"+value);
				      System.out.println("����·��:"+path);
					  if(b){
						   System.out.println("�з���Ȩ��");
						  return true;
					  }else{
						  System.out.println("ô�з���Ȩ��");
						  response.setContentType("text/html;charset=UTF-8");
						  response.getWriter().write("<html><head><script>"
						  		+ "alert(\"��û�з���Ȩ��\");history.go(-1);"
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
