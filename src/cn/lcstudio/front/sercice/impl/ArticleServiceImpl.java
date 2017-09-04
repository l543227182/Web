package cn.lcstudio.front.sercice.impl;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.Article;
import cn.lcstudio.front.mapper.ArticleDao;
import cn.lcstudio.front.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl  implements ArticleService{

	@Resource
	private ArticleDao articleDao;
	
	@Override
	public void InsertAritcle(Article article) {
		// TODO Auto-generated method stub
		articleDao.InsertAritcle(article);
	}

	@Override
	public Article GetArticleByID(String id) {
		// TODO Auto-generated method stub
		return articleDao.GetArticleByID(id);
	}

	@Override
	public void DelArticleByUserID(String UserID) {
		// TODO Auto-generated method stub
		articleDao.DelArticleByUserID(UserID);
	}

	@Override
	public void DelArticleByID(String[] ids) {
		// TODO Auto-generated method stub
		articleDao.DelArticleByID(ids);
	}

	@Override
	public void UpdateArticle(Article aticle) {
		// TODO Auto-generated method stub
		articleDao.UpdateArticle(aticle);
	}

	@Override
	public Pagination GetArticleListByUserID(String UserID,Integer pageNum) {
		int fileRecord=articleDao.GetCountByUserID(UserID);
		Article article=new Article();
		article.setUserID(UserID);
		article.setPageNo(pageNum);
		article.setRecords(fileRecord);
		article.setPageSize(8);
		Pagination p=new Pagination();		
		p.setPageNo(pageNum);
		p.setPageSize(8);
		p.setTotalCount(fileRecord);
		if(p.getTotalPage()<pageNum){
			article.setPageNo(1);
		}
		List<Article> list=articleDao.GetArticleListByUserID(article);
		p.setList(list);		
		return p;
	}

	@Override
	public List<Article> getThelastArticle() {
		// TODO Auto-generated method stub
		return articleDao.getThelastArticle();
	}

	@Override
	public Pagination getFrontArticle(Integer pageNo) {
		int fileRecord=articleDao.getFrontArticleCount();
		Article article=new Article();
	
		article.setPageNo(pageNo);
		article.setRecords(fileRecord);
		article.setPageSize(8);
		Pagination p=new Pagination();	
		
		p.setPageNo(pageNo);
		p.setPageSize(8);
		p.setTotalCount(fileRecord);
		
		if(p.getTotalPage()<pageNo){
			article.setPageNo(1);
		}
		
		List<Article> list=articleDao.getFrontArticles(article);
		p.setList(list);
		
		return p;
	}
}
