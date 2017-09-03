package cn.lcstudio.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * ClassName: ResponseUtils
 * @Description: TODO
 * @author lc
 * @date 2016年10月22日
 */
public class ResponseUtils {

	/**
	 * 
	 * @Description: TODO
	 * @param @param response
	 * @param @param contentType
	 * @param @param text   
	 * @return void  
	 * @throws
	 * @author lc
	 * @date 2016年10月22日 下午8:00:23
	 */
	public static void render(HttpServletResponse response,String contentType,String text){
		response.setContentType(contentType);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void renderJson(HttpServletResponse response,String text){
		render(response, "application/json;charset=UTF-8", text);
	}

	public static void renderXml(HttpServletResponse response,String text){
		render(response, "text/xml;charset=UTF-8", text);
	}
	
	public static void renderText(HttpServletResponse response,String text){
		render(response, "text/plain;charset=UTF-8", text);
	}
	
	
}
