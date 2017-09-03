package cn.lcstudio.bean;

import java.util.Date;
/*
 create table notice(
  title varchar(25),
  content varchar(1000),
  time date,
  author varchar(10),
  UserID varchar(50),
  constraint FK_UserID_notice foreign key(UserID) references users(id)
 )character set utf8 collate utf8_general_ci;
 */
public class Notice extends PageBean{
	
	private String id;
	private String title;
	private String content;
	private Date time;
	private String author;
	private String UserID;
	private boolean open;
	
	
	
	
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
	

	
}
