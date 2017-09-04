package cn.lcstudio.front.service;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.Notice;

public interface NoticeService {
	
	public void addNotice(Notice notice);
	public void delNotice(String[] id);
	public void updateNotice(Notice notice);
	public Notice GetNoticeByID(String id);
	public Pagination GetNoticeList(String UserID,Integer pageNum);
	public void updateOpenTofalse();
	public Pagination GetNoticeListByAdmin(String UserID,Integer pageNum);
	public Notice getOpenNotice();
}
