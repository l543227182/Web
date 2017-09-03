package cn.lcstudio.bean;


import java.util.Date;
import java.util.List;

public class UserArticle {

	private String id;
	 private String realname;
	 private String username;
	 private String password;
	 private int gender;
	 private String email;
	 private String ROLEID;
	 private String remark;
	 private Date regDate;
	 private long PID;
	 private String NAME;
	 
	 
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getROLEID() {
		return ROLEID;
	}
	public void setROLEID(String rOLEID) {
		ROLEID = rOLEID;
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
	public long getPID() {
		return PID;
	}
	public void setPID(long pID) {
		PID = pID;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	private List<Article> articles;

	public List<Article> getList() {
		return articles;
	}
	public void setList(List<Article> articles) {
		this.articles = articles;
	}
}
