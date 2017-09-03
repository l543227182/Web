package cn.lcstudio.bean;

import java.io.Serializable;

/*
 create table  IteyeBean(
  id int primary key,
  title varchar(50),
  body mediumtext,
  author varchar(30),
  srcUrl varchar(80),
  date  varchar(15),
  summary varchar(50),
  viewtimes int
  )character set utf8 collate utf8_general_ci;
 */
public class IteyeBean extends PageBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3244158245912637379L;
	private String title;
	private String body;
	private String author;
	private String srcUrl;
	private String date;
	private String summary;
	private int id;
	private int viewtimes;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getViewtimes() {
		return viewtimes;
	}
	public void setViewtimes(int viewtimes) {
		this.viewtimes = viewtimes;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSrcUrl() {
		return srcUrl;
	}
	public void setSrcUrl(String srcUrl) {
		this.srcUrl = srcUrl;
	}
	@Override
	public String toString() {
		return "IteyeBean [title=" + title + ", body=" + body + ", author="
				+ author + ", srcUrl=" + srcUrl + ", date=" + date
				+ ", News_tag="  + "]";
	}
	
	
}
