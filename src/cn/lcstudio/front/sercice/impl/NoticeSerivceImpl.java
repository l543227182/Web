package cn.lcstudio.front.sercice.impl;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.Notice;
import cn.lcstudio.front.mapper.NoticeDao;
import cn.lcstudio.front.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class NoticeSerivceImpl implements NoticeService {

	@Resource
	private NoticeDao noticeDao;
	
	@Override
	public void addNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.addNotice(notice);
	}

	@Override
	public void delNotice(String[] ids) {
		// TODO Auto-generated method stub
		noticeDao.delNotice(ids);
	}

	@Override
	public void updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.updateNotice(notice);
	}

	@Override
	public Notice GetNoticeByID(String id) {
		// TODO Auto-generated method stub
		return noticeDao.GetNoticeByID(id);
	}

	@Override
	public Pagination GetNoticeList(String UserID, Integer pageNum) {
		int fileRecord=noticeDao.GetNoticeCountByUserID(UserID);
		Notice article=new Notice();
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
		List<Notice> list=noticeDao.GetNoticeList(article);
		p.setList(list);		
		return p;
	}

	@Override
	public void updateOpenTofalse() {
		// TODO Auto-generated method stub
		noticeDao.updateOpenTofalse();
	}

	@Override
	public Pagination GetNoticeListByAdmin(String UserID, Integer pageNum) {
		int fileRecord=noticeDao.GetNoticeCount();
		Notice article=new Notice();
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
		List<Notice> list=noticeDao.GetNoticeListByAdmin(article);
		p.setList(list);		
		return p;
	}

	@Override
	public Notice getOpenNotice() {
			return noticeDao.getOpenNotice();
	}

}
