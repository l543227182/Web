package cn.lcstudio.front.mapper;

import cn.lcstudio.bean.Article;

import java.util.List;

public interface ArticleDao {
	public void InsertAritcle(Article article);
	public Article GetArticleByID(String id);
	public void DelArticleByUserID(String UserID);
	public void DelArticleByID(String[] ids);
	public void UpdateArticle(Article aticle);
	public List<Article> GetArticleListByUserID(Article article);
	public int GetCountByUserID(String UserID);
	public List<Article> getThelastArticle();	
	public List<Article> getFrontArticles(Article article);
	public int getFrontArticleCount();
}
