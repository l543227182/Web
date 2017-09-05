package cn.lcstudio.interceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginFilter implements Filter {
	//private static Logger logger = Logger.getLogger(LoginFilter.class); 
	private List<String> PassUrl=new ArrayList<String>();
	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		PassUrl.add("/Web/css");
		PassUrl.add("/Web/index");	
		PassUrl.add("/Web/css");
		PassUrl.add("/Web/js");		
		PassUrl.add("/Web/html");
		PassUrl.add("/Web/fonts");
		PassUrl.add("/Web/userfiles/images");
		PassUrl.add("/Web/photos.jsp");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		String path=request.getRequestURI();					
		System.out.println(path);
		//首页放行  paramValue.trim().getBytes("ISO-8859-1")
		for(int i=0;i<PassUrl.size();i++){
			if(path.startsWith(PassUrl.get(i))){ 
				chain.doFilter(request, response);
				return;
			};
		}
		//非首页验证是否登录
		if(request.getSession().getAttribute("user")==null){
			System.out.println("请登录再访问");		
		    response.sendRedirect("/Web/index.do");
		    return ;
		}
		else{
			System.out.println("登录访问的用户:" +"  "+request.getRemoteHost()+" 访问"+path);
			//logger.info("登录访问的用户:" +"  "+request.getRemoteHost()+" 访问"+path);
			chain.doFilter(request, response);
		}
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
