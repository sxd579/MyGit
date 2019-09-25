package com.HIM.common;

import java.io.File;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Bean_fileinfo implements Serializable 
{
	public static final String TYPE_File = "file";
	public static final String TYPE_Picture = "picture";
	public static final String TYPE_Video = "video";
	
	private String name;
	private String md5;
	private File file;
	private String type;// "picture" or "video" or "file"
	private String size;
	private long length;
	private String path;
	
	private String uploadTime;
	private int uploader;
	
	//客户端上传
	public Bean_fileinfo(String type,File file)
	{
		this.name = file.getName();
		this.type = type;
		setFile(file);
		logger.writelog("Bean_fileinfo", "正在计算文件MD5");
		this.md5 = tools.getFileMD5(this.file);
		logger.writelog("Bean_fileinfo", "文件MD5计算完毕:"+this.md5);
	}
	
	//客户端下载 (不需要具体的文件file)
	public Bean_fileinfo(String type,String size,String md5,String name,String path)
	{
		this.type = type;
		this.size = size;
		this.md5=md5;
		this.name = name;
		setFile(new File(path));
	}
	
	public void setFile(File file) 
	{
		this.file = file;
		this.size = tools.getFormatFileSize(file.length());
		this.length = file.length();
		this.path = file.getAbsolutePath();
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public final void setName(String name)
	{
		this.name = name;
	}

	public String getSize() {return size;}
	public String getName() {return name;}
	public String getMD5() {return md5;}
	public String getType( ) {return type;}
	
	//客户端存在的文件实际上服务端不存在，不能用来取大小之类的
	//public File getFile()  {return file;} 
	
	public long getLength() {return length;}
	public String getPath() {return path;}
	
	public final String getUploadTime()
	{
		return uploadTime;
	}

	public final int getUploader()
	{
		return uploader;
	}

	public final void setUploadTime(String uploadTime)
	{
		this.uploadTime = uploadTime;
	}

	public final void setUploader(int uploader)
	{
		this.uploader = uploader;
	}

	public String toString()
	{
		return name+","+type+","+size+","+md5+",";		
	}
	
	
}
