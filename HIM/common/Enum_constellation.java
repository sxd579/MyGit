package com.HIM.common;

public enum Enum_constellation
{
	Aries("白羊座",0),Taurus("金牛座",1),Gemini("双子座",2),
	Cancer("巨蟹座",3),Leo("狮子座",4),Virgo("处女座",5),
	Libra("天秤座",6),Scorpio("天蝎座",7),Sagittarius("射手座",8),
	Capricorn("摩羯座",9),Aquarius("水瓶座",10),Pisces("双鱼座",11);
	
	private String name;
	private int index;
	
	private Enum_constellation(String name,int index)
	{
		this.name=name;
		this.index=index;
	}
	
	public static String getName(int index)
	{
		for (Enum_constellation c : Enum_constellation.values())
			if (c.index == index)
				return c.name;
		return null;
	}
	
	public static int getIndex(String name)
	{
		for (Enum_constellation c : Enum_constellation.values())
			if (c.name.equals(name))
				return c.index;
		return -1;
	}
	
	public String getName() {return name;}
	public int getIndex() {return index;}
}
