package cn.lcstudio.bean;

import java.util.Date;
/*
 create table User
(
  id varchar(50) primary key,
  realname varchar(10) not null,
  username varchar(16) not null,
  password varchar(50) not null,
  gender int,
  email varchar(20),
  ROLEID       int ,
  constraint ROLEID_user_FK foreign key (ROLEID) references roles(ID)
	
)character set utf8 
collate utf8_general_ci;
 */
public class Users extends PageBean{
	
	 private String id;
	 private String realname;
	 private String username;
	 private String password;
	 private int gender;
	 private String email;
	 private String ROLEID;
	 private String remark;
	 private Date regDate;
	 private UserImage image;
	 private long PID;
	 private String NAME;
	 
	 
	
	
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public long getPID() {
		return PID;
	}
	public void setPID(long pID) {
		PID = pID;
	}
	public UserImage getImage() {
		return image;
	}
	public void setImage(UserImage image) {
		this.image = image;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getROLEID() {
		return ROLEID;
	}
	public void setROLEID(String rOLEID) {
		ROLEID = rOLEID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	private String remeberMe;
	 
	 public String getRemeberMe() {
		return remeberMe;
	}
	public void setRemeberMe(String remeberMe) {
		this.remeberMe = remeberMe;
	}



}
