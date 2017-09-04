package cn.lcstudio.front.controller;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.front.service.ArticleService;
import cn.lcstudio.front.service.FileService;
import cn.lcstudio.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/index")
public class ShowController {
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/files")
	public String FileShow(Integer pageNo,HttpServletRequest request){
		pageNo=pageNo==null?1:pageNo;
		Pagination p=fileService.getFileAtFront(pageNo);
		request.setAttribute("p", p);
		return "/visitor/Downfile";
	}
	
	@RequestMapping("/downloadFile")
	public  void download(HttpServletRequest request,HttpServletResponse response,String fileID) throws Exception {	
		FileUtils.DownloadFile(request, response, fileService, fileID);
	}
	
	@Autowired
	private ArticleService articleService;
	@RequestMapping("/articles")
	public String ArticlesShow(Integer pageNo,HttpServletRequest request){
		pageNo=pageNo==null?1:pageNo;
		Pagination p=articleService.getFrontArticle(pageNo);
		request.setAttribute("p", p);
		return "/visitor/AllArticle";
	}
	
}
