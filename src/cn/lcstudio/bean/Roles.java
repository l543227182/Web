package cn.lcstudio.bean;


/*
 create table ROLES(
  ID int primary key ,
  NAME varchar(20) not null unique,
  PID bigint not null ,
  REMARK varchar(100)  
 )character set utf8 collate utf8_general_ci; 
 */
public class Roles {

	private int  ID;	
	private String NAME;
	private long PID;
	private String REMARK;
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
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
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	@Override
	public String toString() {
		return "Roles [ID=" + ID + ", NAME=" + NAME + ", PID=" + PID
				+ ", REMARK=" + REMARK + "]";
	}
	
}
