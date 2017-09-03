package cn.lcstudio.admin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.Article;
import cn.lcstudio.bean.Users;
import cn.lcstudio.front.service.ArticleService;
import cn.lcstudio.utils.ControlerUtils;
import cn.lcstudio.utils.privilegeValue;

@Controller
@RequestMapping("/admin/article")
@privilegeValue(2)
public class ArticleController {

	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/toArticleCenter")
	public String ToArticleCenter(Integer pageNum,HttpServletRequest request){
		pageNum=pageNum==null?1:pageNum;
		Users u=(Users) request.getSession().getAttribute("user");
		Pagination p=articleService.GetArticleListByUserID(u.getId(), pageNum);
		if(p.getList().size()==0){
			p=null;
		}
		request.setAttribute("p", p);
		return "/admin/article/ArticleCenter";
	}
	
	@RequestMapping("/toWritePage")
	public String toWritePage(){		
		return "/admin/article/WritePage";
	}
	
	@RequestMapping("/addArticle")
	public String addArticle(Article article , HttpServletRequest request){
	    //System.out.println(article);
		if(article!=null){
			Users u=(Users) request.getSession().getAttribute("user");
			article.setCreateTime(new Date());
			article.setId(ControlerUtils.CreateUUID());
			article.setUserID(u.getId());
			articleService.InsertAritcle(article);
		}
		return "redirect:toArticleCenter.do";
	}
	
	@RequestMapping("/showArticle")
	public String showArticle(String id,HttpServletRequest request){
		if(id!=null && !id.trim().equals("")){
			Article article=articleService.GetArticleByID(id);
			request.setAttribute("a", article);
			return "/admin/article/ShowArticle";
		}else
		return "redirect:toArticleCenter.do";
	}
	
	@RequestMapping("/delArticles")
	public String delArticles(String[] fileids){
		System.out.println(fileids);
		articleService.DelArticleByID(fileids);
		return "redirect:toArticleCenter.do";
	}
	
	@RequestMapping("/ModifyArticlePage")
	public String ModifyArticle(String id ,HttpServletRequest request){
		Article article=articleService.GetArticleByID(id);
		request.setAttribute("a", article);
		return "/admin/article/ModifyPage";
	}
	
	@RequestMapping("/ModifyArticle")
	public String ModifyArticle(Article article,HttpServletRequest request){
		if(article!=null){
			articleService.UpdateArticle(article);		
		}
		return "redirect:toArticleCenter.do";		
	}
	
	
}
