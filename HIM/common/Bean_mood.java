package com.HIM.common;

import java.io.Serializable;

public class Bean_mood implements Serializable,Comparable<Bean_mood>
{
	private static final long serialVersionUID = 1L;
	private int index;
	private int poster;
	private String posttime;
	private String content;
	
	public Bean_mood(int poster,String posttime,String content)
	{
		this.poster = poster;
		this.posttime = posttime;
		this.content = content;
	}
	
	public Bean_mood(int index,int poster,String posttime,String content)
	{
		this(poster, posttime, content);
		this.index = index;
	}
	
	public final int getPoster()
	{
		return poster;
	}
	
	public final String getPosttime()
	{
		return posttime;
	}
	
	public final String getContent()
	{
		return content;
	}
	
	public final void setPoster(int poster)
	{
		this.poster = poster;
	}
	
	public final void setPosttime(String posttime)
	{
		this.posttime = posttime;
	}
	
	public final void setContent(String content)
	{
		this.content = content;
	}
	
	public final int getIndex()
	{
		return index;
	}

	public final void setIndex(int index)
	{
		this.index = index;
	}

	public String toString()
	{
		return index+"."+poster+"("+posttime+"):"+content;
	}

	@Override
	public int compareTo(Bean_mood m)
	{
		if (this.index == m.getIndex()) return 0;
		return (this.index < m.getIndex())?1:-1;
	}
	
	
}
