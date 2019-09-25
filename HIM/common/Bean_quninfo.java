package com.HIM.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Bean_quninfo implements Serializable
{
	private int qunnum;
	private String qunname;
	private int photoindex;
	private String createtime;
	
	//查询专用构造方法
	public Bean_quninfo(int qunnum,String qunname,int photoindex,String createtime) 
	{
		this.qunnum = qunnum;
		this.qunname = qunname;
		this.photoindex = photoindex;
		this.createtime = createtime;
	}
	
	//更新专用构造方法
	public Bean_quninfo(int qunnum,String qunname,int photoindex) 
	{
		this.qunnum = qunnum;
		this.qunname = qunname;
		this.photoindex = photoindex;
	}
	
	public void setQunNum(int qunnum) {this.qunnum= qunnum;}
	public void setQunName(String qunname) {this.qunname = qunname;}
	public void setPhotoIndex(int photoindex) {this.photoindex = photoindex;}
	public void setCreateTime(String createtime) {this.createtime = createtime;}
	
	public int getQunNum() {return this.qunnum;}
	public String getQunName() {return this.qunname;}
	public int getPhotoIndex() {return this.photoindex;}
 	public String getCreateTime() {return this.createtime;}
 	
 	public String toString()
 	{
 		return "qunnum:"+qunnum+",qunname:"+qunname+",qunphotoindex"
 				+photoindex+",creatime:"+createtime;
 	}
	
}
