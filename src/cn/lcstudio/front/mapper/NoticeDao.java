package cn.lcstudio.front.mapper;

import cn.lcstudio.bean.Notice;

import java.util.List;

public interface NoticeDao {
	
	public void addNotice(Notice notice);
	public void delNotice(String[] id);
	public void updateNotice(Notice notice);
	public Notice GetNoticeByID(String id);
	public int GetNoticeCount();
	public int GetNoticeCountByUserID(String UserID);
	public List<Notice> GetNoticeList(Notice article);
	public void updateOpenTofalse();
	public Notice getOpenNotice();
	public List<Notice> GetNoticeListByAdmin(Notice notice);
}
