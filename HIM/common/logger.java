package com.HIM.common;

public class logger 
{

	public static void writelog(String who,String what)
	{
		System.out.println(tools.get_nowtime(true)+"("+who+"): "+what);
	}
	
}
