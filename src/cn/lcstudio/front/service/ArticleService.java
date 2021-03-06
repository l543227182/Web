package cn.lcstudio.front.service;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.Article;

import java.util.List;

public interface ArticleService {
	
	public void InsertAritcle(Article article);
	public Article GetArticleByID(String id);
	public void DelArticleByUserID(String UserID);
	public void DelArticleByID(String[] ids);
	public void UpdateArticle(Article aticle);
	public Pagination GetArticleListByUserID(String UserID,Integer pageNum);
	public List<Article> getThelastArticle();
	public Pagination getFrontArticle(Integer pageNo);
}
