package com.HIM.common;

import java.io.Serializable;

public class Bean_picture implements Serializable
{
	private static final long serialVersionUID = 1L;
	
    private int x;
    private int y;
    private int x1;
    private int y1;
    private String result;
    private int color;
    private int drawType;

    public Bean_picture(int x,int y,int color,int drawType){
        this.x = x;
        this.y = y;
        this.color = color;
        this.drawType = drawType;

    }
    public Bean_picture(int x,int y,int x1,int y1,int color,int drawType){
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.color = color;
        this.drawType = drawType;

    }
    //加一个构造方法传输结果字符串
    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String  result) {
        this.result = result;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDrawType() {
        return drawType;
    }

    public void setDrawType(int drawType) {
        this.drawType = drawType;
    }

    public  void send_Picture (Bean_picture picture){

    }
    public void send_Result (String result){

    }
    public void  send_SuccessOrNot(boolean win){

    }
    public boolean refuse_Game(){
        return false;
    }
    
    public String toString()
    {
    	return x+","+y+","+x1+","+y1+","+color+","+drawType;
    }


}
