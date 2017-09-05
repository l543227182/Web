package cn.lcstudio.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class headPhoto extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3754123735450283822L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
       /*    
            response.setHeader("content-tyep", "image/jpeg");
            response.setDateHeader("expries", -1);
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
           // System.out.println(u.getImage());
            BufferedImage image=ImageIO.read(new ByteArrayInputStream(newUser.getImage()));
            ImageIO.write(image, "jpg", response.getOutputStream());
            response.getOutputStream().flush();*/
          // System.out.println("我来了");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
