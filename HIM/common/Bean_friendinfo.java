package com.HIM.common;

import java.io.Serializable;



@SuppressWarnings("serial")
public class Bean_friendinfo implements Serializable
{
	private int qq;
	private int fqq;
	private int photoindex;
	private String nickname;
	private String notename;
	private int sex;
	private String birthday;
	private int constellation;
	private String applydate;
	private String sign;
	private int fstatus = 0;
	private String ip;
	private int port;
	private int GameNum;
	
	//加好友专用构造
	public Bean_friendinfo(int fqq,String notename,int qq)
	{
		this.fqq=fqq;
		this.notename=notename;
		this.qq=qq;
	}
	
	
	//查询好友列表专用构造
	public Bean_friendinfo(int qq,int fqq,int photoindex,String sign,String nickname,
						   String notename,int sex,String birthday,int constellation,String applydate)
	{
		this.qq=qq;
		this.fqq=fqq;
		this.photoindex=photoindex;
		this.sign=sign;
		this.nickname=nickname;
		this.notename=notename;
		this.sex=sex;
		this.birthday=birthday;
		this.constellation=constellation;
		this.applydate=applydate;	
	}
	
	//查询用户构造
	public Bean_friendinfo(int fqq,int photoindex,String sign,String nickname,
					int sex,String birthday,int constellation,String applydate)
	{
		this.fqq=fqq;
		this.photoindex=photoindex;
		this.sign=sign;
		this.nickname=nickname;
		this.sex=sex;
		this.birthday=birthday;
		this.constellation=constellation;
		this.applydate=applydate;
	}
	
	public void setnet(String ip,int port)
	{
		this.ip=ip;
		this.port=port;
		this.fstatus=1;
	}
	
	public int getqq() {return qq;}
	public int getfqq() {return fqq;}
	public int getphotoindex() {return photoindex;}
	public String getnickname() {return nickname;}
	public String getnotename() {return notename;}
	public int getfstatus() {return fstatus;}
	public int getsex() {return sex;}
	public String getbirthday() {return birthday;}
	public int getconstellation() {return constellation;}
	public String getapplydate() {return applydate;}
	public String getsign() { return sign;}
	public String getip() { return ip;}
	public int getport() {return port;}
	public int getGameNum() { return GameNum; }


	public void setGameNum(int gameNum) { GameNum = gameNum; }
	public void setqq(int qq) {this.qq=qq;}
	public void setfqq(int fqq) {this.fqq=fqq;}
	public void setphotoindex(int photoindex) {this.photoindex=photoindex;}
	public void setnickname(String nickname) {this.nickname=nickname;}
	public void setnotename(String notename) {this.notename=notename;}
	public void setsex(int sex) {this.sex=sex;}
	public void setbirthday(String birthday) {this.birthday=birthday;}
	public void setconstellation(int constellation) {this.constellation=constellation;}
	public void setsign(String sign) {this.sign=sign;}
	
	public String toString()
	{
		return fqq+","+qq+","+photoindex+","+notename+","+nickname+","
				+sign+","+sex+","+birthday+","+constellation+","+applydate+","
				+fstatus+","+ip+","+port;
	}
	
	
}
