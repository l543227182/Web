package cn.lcstudio.admin.controller;

import cn.itcast.common.page.Pagination;
import cn.lcstudio.bean.Notice;
import cn.lcstudio.bean.Users;
import cn.lcstudio.front.service.NoticeService;
import cn.lcstudio.utils.ControlerUtils;
import cn.lcstudio.utils.privilegeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/admin/notice")
@privilegeValue(8)
public class NoticeController {
	 @Autowired
	 private  NoticeService noticeService;
	@RequestMapping("/addNotice")
	public String addNotice(Notice notice,String author,String UserID){
		 if(notice!=null){			 
			 notice.setAuthor(author);
			 notice.setUserID(UserID);
			 notice.setId(ControlerUtils.CreateUUID());
			 notice.setTime(new Date());
			 notice.setOpen(false);
			 noticeService.addNotice(notice);
		 }		
		return "redirect:NoticeCenterPage.do";
	}
	
	@RequestMapping("/toAddNoticePage")
	public String toAddNoticePage(){		
		return "/admin/notice/AddPage";
	}	
	@RequestMapping("/NoticeCenterPage")
	public String NoticeCenterPage(Integer pageNum,HttpServletRequest request){
		pageNum=pageNum==null?1:pageNum;
		Users u=(Users) request.getSession().getAttribute("user");
		Pagination  p=null;
		if(u.getROLEID().equals("2")){
			p=noticeService.GetNoticeListByAdmin(u.getId(), pageNum);
		}
		else{
			p=noticeService.GetNoticeList(u.getId(), pageNum);
		}
		
		if(p.getList().size()==0){
			p=null;
		}
		request.setAttribute("p", p);
		return "/admin/notice/NoticeCenter";
	}
	
	@RequestMapping("/showNotice")
	public String showNotice(String id,HttpServletRequest request){
		if(id!=null){
		  Notice notice=noticeService.GetNoticeByID(id);
		  request.setAttribute("n", notice);
		}
		return "/admin/notice/ShowNotice";
	}
	
	@RequestMapping("/postNotice")
	public String postNotice(String id,HttpServletRequest request){
		if(id!=null){
			noticeService.updateOpenTofalse();
			Notice notice=new Notice();
			notice.setOpen(true);
			notice.setId(id);
			noticeService.updateNotice(notice);
		}
		return "redirect:NoticeCenterPage.do";
	}
	
	@RequestMapping("/delNotices")
	public String DelNotices(String fileids[],HttpServletRequest request){
		noticeService.delNotice(fileids);
		return "redirect:NoticeCenterPage.do";
	}
}
