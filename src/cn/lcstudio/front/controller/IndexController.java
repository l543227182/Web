package cn.lcstudio.front.controller;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.*;
import cn.lcstudio.front.service.*;
import cn.lcstudio.lucene.Dao.luceneDao;
import cn.lcstudio.utils.ControlerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private IteyeBeanService beanService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/loginUser")
	public String loginUser(Users user,HttpServletRequest request){
		System.out.println(user);
		if(ControlerUtils.validateUser(user)){
			user.setPassword(ControlerUtils.MD5(user.getPassword()));
			System.out.println(user.getPassword());
			user=usersService.loginUser(user);
			if(user!=null){
				user=usersService.getUser(user.getUsername());			
				request.getSession().setAttribute("user", user);
			}
			else{						
				return "redirect:/index.do?message=1";
			}
		}	
		//记住客户端	
	   //Cookie cookie=new Cookie("user", arg1)
		return "redirect:/index.do";
	}
	
	@RequestMapping("/logoutUser")
	public String logoutUser(HttpServletRequest request){
		Users user=(Users)request.getSession().getAttribute("user");
		if(user!=null)
			request.getSession().invalidate();
		return "redirect:/index.do";
	}
	
	/**
	 * 
	 * @Description: TODO
	 * @param @param user
	 * @param @param request
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lc
	 * @date 2016年10月19日 下午10:14:35
	 */
	@RequestMapping("/RegisterUser")
	public String RegisterUser(Users user,HttpServletRequest request){
		
		System.out.println("注册的新用户:"+user.getPassword());
		 
		if(ControlerUtils.validateRegUser(user)){
		user.setUsername(user.getUsername().trim());
		user.setId(ControlerUtils.CreateUUID());
		String temp=ControlerUtils.MD5(user.getPassword());
		user.setPassword(temp);
		System.out.println(temp);
		user.setRegDate(new Date());
		user.setROLEID("6884341");
		usersService.addUser(user);
		//为用户创建一个头像
		UserImage ui=new UserImage();
		ui.setId(ControlerUtils.CreateUUID());
		ui.setPath(null);
		ui.setUserID(user.getId());
		imageService.insertImage(ui);
		user=usersService.getUser(user.getUsername());			
		request.getSession().setAttribute("user", user);
		
		}			
		return "redirect:/index.do";
	}
	

	@RequestMapping("/UserNameExist")
	public @ResponseBody String UserNameExist(String loginName,HttpServletResponse response) 
														throws UnsupportedEncodingException{
		loginName=URLDecoder.decode(loginName.trim(), "UTF-8");
		if(loginName==null||loginName.isEmpty()){
			return "";
		}
		 Users user=usersService.getUser(loginName);
		 response.setContentType("text/xml;charset=utf-8");  
	     response.setHeader("Cache-Control", "no-cache");   
		if(user==null){
			return "没有被注册";
		}
		else{
			return "用户存在";
		}	
	}
	
	@RequestMapping
	public String indexPage(Integer pageNo,HttpServletRequest request){
		//System.out.println("index");
		pageNo=pageNo==null?1:pageNo;
		Pagination p=beanService.getListBean(pageNo, false,5);
		String meg=(String)request.getParameter("message");		
		if(meg!=null&&meg!="")
				request.setAttribute("message", meg);
		Notice notice=noticeService.getOpenNotice();
		List<Article> articles=articleService.getThelastArticle();
		request.setAttribute("n",notice);
		request.setAttribute("a",articles);
		request.setAttribute("p",p);
		return "visitor/index";
	}
	
	@RequestMapping("/article")
	public String article(String id,HttpServletRequest request){
		Article article=articleService.GetArticleByID(id);
		request.setAttribute("a",article);
		return "/visitor/ShowArticle";
	}
	@Autowired
	private luceneDao dao;
	
	@RequestMapping("/SearchData")
	public String SearchData(String keyWords ,Integer pageNum,HttpServletRequest request){			
		pageNum=pageNum==null?0:pageNum;
		try {
			if(keyWords!=null&&!keyWords.equals("")){
			List<IteyeBean> list=dao.SearchIteyeBean(
										keyWords, pageNum*5, 5);
			Pagination p=new Pagination();			
			p.setList(list);			
			Notice notice=noticeService.getOpenNotice();
			List<Article> articles=articleService.getThelastArticle();
			request.setAttribute("n",notice);
			request.setAttribute("a",articles);
			request.setAttribute("p", list.size()==0?null:p);		
			return "visitor/SearchResult";			
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return "redirect:/index.do";
	}
	
	@RequestMapping("/test")
	public String Test(){
		File f=new File("config/log4j.properties");
		System.out.println(f.getAbsolutePath()+"  ");
		System.out.println();
		return "redirect:/index.do";
	}
}
