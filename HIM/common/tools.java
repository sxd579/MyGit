package com.HIM.common;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class tools 
{
	private static DecimalFormat df = null;  
    static {  
        // 设置数字格式，保留一位有效小数  
        df = new DecimalFormat("#0.0");  
        df.setRoundingMode(RoundingMode.HALF_UP);  
        df.setMinimumFractionDigits(1);  
        df.setMaximumFractionDigits(1);  
    }  
	
	//获取东八区的当前时间
	public static String get_nowtime(boolean specific)
	{
		Date time = new Date();
		SimpleDateFormat sdf;
		if (specific) sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		else sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(time);
	}
	
	//自动补0日期法
	public static String formatBir(String year,String month,String day){
    	String Bir  = null;
		if (Integer.valueOf(month)<10&&Integer.valueOf(day)<10){
			Bir = year+"-"+"0"+month+"-"+"0"+day;
		}
		else if (Integer.valueOf(month)>=10&&Integer.valueOf(day)<10){
			Bir = year+"-"+month+"-"+"0"+day;
		}
		else if (Integer.valueOf(month)<10&&Integer.valueOf(day)>=10){
			Bir = year+"-"+"0"+month+"-"+day;
		}
		else {
			Bir = year+"-"+month+"-"+day;
		}
    	return Bir;
	}
	//格式化输出文件size函数
	public static String getFormatFileSize(long length) {  
        double size = ((double) length) / (1 << 30);  
        if(size >= 1) {  
            return df.format(size) + "GB";  
        }  
        size = ((double) length) / (1 << 20);  
        if(size >= 1) {  
            return df.format(size) + "MB";  
        }  
        size = ((double) length) / (1 << 10);  
        if(size >= 1) {  
            return df.format(size) + "KB";  
        }  
        return length + "B";  
    }  
	
	//获取文件md5
	public static String getFileMD5(File file)
	{
		FileInputStream fis = null;
		BigInteger bi = null;
		try
		{	
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			fis = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ( (length = fis.read(buffer)) != -1 )
			{
				MD5.update(buffer,0,length);
				
			}
			byte[] b = MD5.digest();
			bi = new BigInteger(1, b);
		} catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
		return bi.toString(16);
	}
	
	//得到文件的后缀名，包括‘.’
	public static String getFileExtensionName(String filename) 
	{   
        if ((filename != null) && (filename.length() > 0)) 
        {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length() - 1))) 
            {   
                return filename.substring(dot);   
            }   
        }   
        return null;   
    }   
	
}
