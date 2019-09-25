package com.HIM.common;

public enum Enum_sex
{
	FEMALE("女",0),MALE("男",1);
	private String name;
	private int index;
	
	private Enum_sex(String name,int index)
	{
		this.name=name;
		this.index=index;
	}
	
	public static String getName(int index) 
	{
		for (Enum_sex s : Enum_sex.values())
			if (s.index == index)
				return s.name;
		return null;
	}
	
	public static int getIndex(String name)
	{
		for (Enum_sex s : Enum_sex.values())
			if (s.name.equals(name))
				return s.index;
		return -1;
	}
	
	public String getName() {return name;}
	public int getIndex() {return index;}
}