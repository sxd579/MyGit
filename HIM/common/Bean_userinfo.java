package com.HIM.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Bean_userinfo implements Serializable
{
	private String passwd;
	private String nickname;
	private int sex;
	private String birthday;
	private int constellation;
	private String sign;
	private int qq;
	private int photoindex;
	private String applydate;
	
	//注册专用构造方法
	public Bean_userinfo(String passwd,String nickname,int sex,
						 String birthday,int constellation) 
	{
		this.passwd=passwd;
		this.nickname=nickname;
		this.sex=sex;
		this.birthday=birthday;
		this.constellation=constellation;
	}
	
	//更新个人信息专用
	public Bean_userinfo(int qq,String sign,int sex,String nickname,String birthday,int constellation)
	{
		this.qq=qq;
		this.sign=sign;
		this.sex=sex;
		this.nickname=nickname;
		this.birthday=birthday;
		this.constellation=constellation;
	}
	
	//获取个人信息专用
	public Bean_userinfo(int qq,int photoindex,String sign,int sex,String nickname,String birthday,int constellation,String applydate)
	{
		this.qq=qq;
		this.photoindex=photoindex;
		this.sign=sign;
		this.sex=sex;
		this.nickname=nickname;
		this.birthday=birthday;
		this.constellation=constellation;
		this.applydate=applydate;
	}
	
	public String getPasswd() {return passwd;}
	public String getNickname() {return nickname;}
	public int getSex() {return sex;}
	public String getBirthday() {return birthday;}
	public int getConstellation() {return constellation;}
	public String getSign() {return sign;}
	public int getqq() {return qq;}
	public int getPhotoindex() {return photoindex;}
	public String getApplydate() {return applydate;}
	
	public String toString()
	{
		return passwd+nickname;
	}

	
}
