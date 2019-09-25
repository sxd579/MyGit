package com.HIM.common;

import java.io.Serializable;



public class Bean_message implements Serializable {
	private static final long serialVersionUID = 1L;
	private int sender;
    private int receiver;
    private int qunnum;
    private String content;
    private String time;
    private String type;

    public static final String TYPE_SendMessage = "SendMessage";
    public static final String TYPE_SendOffLineFile = "SendOffLineFile";
    public static final String TYPE_SendOnLineFile = "SendOnLineFile";
    public static final String TYPE_SendPicture = "SendPicture";
    public static final String TYPE_SendVideo = "SendVideo";
    public static final String TYPE_Jitter = "Jitter";
    
    public static final String TYPE_YouDrawMeGuess = "YouDrawMeGuess";

    public static final String TYPE_Send2Qun = "Send2Qun";
    public static final String TYPE_Send2Qun_Picture = "Send2Qun_Picture";
    public static final String TYPE_Send2Qun_Video = "Send2Qun_Video";
    public static final String TYPE_Send2Qun_YouDrawMeGuess = "Send2Qun_YouDrawMeGuess";


    public Bean_message() {}
    
    public Bean_message(int sender,int ReceiverOrQunnum,String type,String time,String content) 
    {
    	this.sender = sender;
    	if (type.equals(Bean_message.TYPE_Send2Qun)
		  ||type.equals(Bean_message.TYPE_Send2Qun_Picture)
		  ||type.equals(Bean_message.TYPE_Send2Qun_Video))
		{
			this.receiver = 0;
			this.qunnum = ReceiverOrQunnum;
		}else
		{
			this.receiver = ReceiverOrQunnum;
			this.qunnum = 0;
		}
    	this.type = type;
    	this.time = time;
    	this.content = content;
	}
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        time = tools.get_nowtime(true);
        this.time = time;
    }

    public final int getQunnum()
	{
		return qunnum;
	}

	public final void setQunnum(int qunnum)
	{
		this.qunnum = qunnum;
	}

	public String toString()
    {
    	return this.time+" "+this.sender+"->"+this.receiver+"("+this.type+"):"
    			+this.content;
    }

}
