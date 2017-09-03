package cn.lcstudio.bean;

import java.util.Date;


/*
  create table articles(
   id varchar(50) primary key,
   title varchar(25),
   content varchar(5000),
   lable varchar(30),
   open bool,
   type varchar(20),
   UserID varchar(50),
   constraint fk_UserID_Article foreign key(UserID)  references Users(id)
   )character set utf8  collate utf8_general_ci ;
 */
public class Article  extends PageBean{
	private String id;
	private String title;
	private String content;
	private String label;
	private boolean open;
	private String type;	
	private Date  CreateTime;
	private String UserID;
	private String author;
	
	
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
}
