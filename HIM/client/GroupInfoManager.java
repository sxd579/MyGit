package com.HIM.client;

import com.HIM.common.Bean_quninfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GroupInfoManager
{
	static ArrayList<Bean_quninfo> qunlist;
	static HashMap<Integer, Bean_quninfo> map;
	
	public static void Init(ArrayList<Bean_quninfo> qunlist)
	{
		GroupInfoManager.qunlist = qunlist;
		GroupInfoManager.map = new HashMap<>();
		for(Iterator<Bean_quninfo> it=qunlist.iterator();it.hasNext();)
		{
			Bean_quninfo qun = it.next();
			map.put(qun.getQunNum(), qun);
		}
	}
	
	public static Bean_quninfo getQunInfo(int qunnum)
	{
		return map.get(qunnum);
	}
	
}
