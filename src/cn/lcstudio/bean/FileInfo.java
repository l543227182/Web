package cn.lcstudio.bean;

import java.util.Date;

/*
   create table FileInfo(
   	id varchar(50) primary key ,
     filename varchar(20),
     path varchar(45),
     createTime date,
     open bool,
     UserID varchar(50),
     constraint UserID_fk_file foreign key(UserID)  references Users(id)
   )character set utf8  collate utf8_general_ci;
 */
public class FileInfo extends PageBean{
	private String id;
	private String filename ;
	private String path;	
	private Date createTime;
	private String realname;
	private String UserID;
	private boolean open;
	private int size;
	
	
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	
	//ËÑË÷¹Ø¼ü×Ö
	private String keyWords;
	
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}


}
