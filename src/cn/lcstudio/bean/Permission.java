package cn.lcstudio.bean;

/*
 create table  Permission(
    ID varchar(10) primary key,
    name varchar(30),
    value long
 )character set utf8 collate utf8_general_ci;
 */
public class Permission {

	private String ID;
	private String name;
	private long value;
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}		
}
