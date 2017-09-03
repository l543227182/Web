package cn.lcstudio.bean;

/*
   create table Image(
    id varchar(50),
    UserID varchar(50),
    image mediumblob,
    constraint UserID_FK foreign key (UserID) references users(id)
   )character set utf8 collate utf8_general_ci;
 */
public class UserImage {

	private String id;
	private String path;
	private String UserID;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
}
