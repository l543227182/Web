package cn.lcstudio.admin.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.lcstudio.bean.UserImage;
import cn.lcstudio.bean.Users;
import cn.lcstudio.front.sercice.impl.UsersServiceImpl;
import cn.lcstudio.front.service.ImageService;
import cn.lcstudio.front.service.UsersService;
import cn.lcstudio.utils.ResponseUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@RequestMapping("/admin")
@Controller
public class adminController {

	private static Logger logger = Logger.getLogger(adminController.class); 
	@Autowired
	private UsersService us;
	
	@Autowired
	private ImageService imageService;
	
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request){			
		Users user= (Users)request.getSession().getAttribute("user");		
		if(user==null){
			return "redirect:/index.do";
		}else{	
			request.setAttribute("user", user);
			return "admin/admin";
		}
	}
	
	@RequestMapping("/ToModifyPage")
	public String ModifyData(HttpServletRequest request){
		request.setAttribute("user", request.getAttribute("user"));
		return "/admin/DataModify";
	}
	//feather ,features
	/**
	 * @Description: TODO
	 * @param @param user
	 * @param @param request
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lc
	 * @date 2016��10��21�� ����9:44:08
	 */
	@RequestMapping("/ModifyData")
	public String ModifyData(Users user,HttpServletRequest request){
		if(user.getImage().getPath().contains("upload")){
			UserImage ui=user.getImage();
			ui.setUserID(user.getId());
			
			imageService.updateHeadPhoto(ui);
		}
			if(user.getPassword()!=null)
			logger.info("�û�����"+"����  Ϊ:"+user.getPassword());
			us.updateUser(user);	
			//����user�е�����			
		
		user=us.getUserByID(user.getId());
		request.getSession().setAttribute("user", user);
		return "redirect:/index.do";
	}
	
	/**
	 * 
	 * @Description: TODO
	 * @param @param pic
	 * @param @param response
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author lc
	 * @date 2016��10��23�� ����12:39:35
	 */
	@RequestMapping("/uploadPic")
	public void uploadPic(@RequestParam(required=false)  MultipartFile pic,HttpServletResponse response) throws IOException{
		String ext=FilenameUtils.getExtension(pic.getOriginalFilename());
		//����ͼƬ����
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String str=df.format(new Date());
		//ʹ��jersey������һ̨����������
		Client client=new Client();	
		String sqlPath="upload/"+str+"."+ext;		
		String url="http://localhost:8088/image-web/"+sqlPath;
		WebResource resource=client.resource(url);
		resource.put(String.class,pic.getBytes());
		logger.info("�ϴ�ͷ��-success");
		
		
		JSONObject jb=new JSONObject();
		jb.put("url", url);
		jb.put("path", sqlPath);
		
		ResponseUtils.renderJson(response, jb.toString());
		System.out.println(pic.getOriginalFilename());
	}
	
	
}
