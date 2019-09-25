package com.HIM.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class SubgroupManagerUtils
{
	//改成单例模式管理分组
	class group
	{
		private String name;
		private ArrayList<Integer> fqq;
		private ArrayList<String> fnotename;
		
		public group(String name)
		{
			this.name = name;
			this.fqq = new ArrayList<>();
			this.fnotename = new ArrayList<>();
		}
		
		public group newAGroup(String name)
		{
			return new group(name);
		}
		
		public String getName() {return name;}
		public void setName(String name) {this.name=name;}
		
		public int getFriendNum() {return fqq.size();}
		
		public ArrayList<Integer> getfqq() {return fqq;}
		public ArrayList<String> getfnotename() {return fnotename;}
		
		public int getAFriendqq(int index) {return fqq.get(index);}
		public String getAFriendNotename(int index) {return fnotename.get(index);}
		public int getFriendIndex(Integer fqq) {return this.fqq.indexOf(fqq);}
		public void AddAFriend(int fqq,String notename) 
		{
			this.fqq.add(fqq);
			this.fnotename.add(notename);
		}
		
		public boolean DeleteAFriend(int fqq)
		{
			int index = getFriendIndex(fqq);
			if (index == -1) return false;;
			this.fqq.remove(index);
			this.fnotename.remove(index);
			return true;
		}
		
		public boolean UpdateAFriendNotename(int fqq,String newname)
		{
			int index = getFriendIndex(fqq);
			if (index == -1) return false;
			this.fnotename.set(index, newname);
			return true;
		}
		
		public String toString()
		{
			String str = "name:" + name + ",fqq:";
			for(int i=0;i<fqq.size();i++)
				str += "[" + fqq.get(i)+","+fnotename.get(i)+"],";
			return str;
		}
	}
	
	private static ArrayList<group> subgroups = null;
	
	//fqq到组数的映射
	private static HashMap<Integer, Integer> MAP = null;
	
	public static boolean InitSubgroup(String json)
	{
		if (json == null) return false;
		try
		{
			subgroups = JsonToArrayList(json);
			InitMAP();
		} catch (Exception e) 
		{
			logger.writelog("Bean_subgroup", "json结构数据出错");
			return false;
		}
		return true;
	}
	
	// main方法当测试
	public static void main (String args[])
	{
		String str_json = "[ {index:1,name:\"我的好友\",fqq:[10000,10001],fnotename:[\"好友1\",\"好友2\"]},\r\n" + 
							"{index:2,name:\"我的家人\",fqq:[],fnotename:[]}]";
		InitSubgroup(str_json);
		
		System.out.println("测试输出");
		for(int i=0;i<subgroups.size();i++)
			System.out.println(subgroups.get(i));
		
		System.out.println("测试加好友");
		AddAFriend(10011, "新好友", 1);
		for(int i=0;i<subgroups.size();i++)
			System.out.println(subgroups.get(i));
		
		System.out.println("测试删好友");
		DeleteAFriend(10001);
		for(int i=0;i<subgroups.size();i++)
			System.out.println(subgroups.get(i));
		
		System.out.println("测试移动好友");
		MoveAFriend(10000, 1);
		for(int i=0;i<subgroups.size();i++)
			System.out.println(subgroups.get(i));
		
		System.out.println("测试移动分组");
		MoveAGroup(0, 1);
		for(int i=0;i<subgroups.size();i++)
			System.out.println(subgroups.get(i));
		
		System.out.println("测试好友改名");
		UpdateAFriendNotename(10011, "新朋友的新备注");
		for(int i=0;i<subgroups.size();i++)
			System.out.println(subgroups.get(i));
		
		System.out.println("测试分组改名");
		UpdateAGroupName(0, "测试一下改名嘛");
		for(int i=0;i<subgroups.size();i++)
			System.out.println(subgroups.get(i));
		
		System.out.println("测试增加新分组");
		AddAGroup("新分组哦！");
		for(int i=0;i<subgroups.size();i++)
			System.out.println(subgroups.get(i));
		
		System.out.println("测试删除分组");
		DeleteAGroup(1);
		for(int i=0;i<subgroups.size();i++)
			System.out.println(subgroups.get(i));
		
	}

	public static ArrayList<Integer> getAllfqq()
	{
		ArrayList<Integer> fqqs = new ArrayList<>();
		fqqs.addAll(MAP.keySet());
		return fqqs;
	}
	
	
	public static String getGroupName(int index){
		if (subgroups == null || index >= subgroups.size())
		{
			return null;
		}
		else{
			return subgroups.get(index).getName();
		}
	}

	public static int getGroupNum()
	{
		if (subgroups == null) return -1;
		return subgroups.size();
	}
	
	public static int getFriendNum(int GroupIndex)
	{
		if (subgroups == null || GroupIndex >= subgroups.size()) return -1;
		return subgroups.get(GroupIndex).getfqq().size();
	}
	
	public static int getAFriendqq(int GroupIndex,int findex)
	{
		if (subgroups == null || GroupIndex >= subgroups.size() 
			|| findex >= subgroups.get(GroupIndex).getFriendNum()) 
			return -1;
		return subgroups.get(GroupIndex).getAFriendqq(findex);
	}
	
	
	public static String getAFriendNotename(int GroupIndex,int findex)
	{
		if (subgroups == null || GroupIndex >= subgroups.size() 
				|| findex >= subgroups.get(GroupIndex).getFriendNum()) 
			return null;
		return subgroups.get(GroupIndex).getAFriendNotename(findex);
	}
	
	public static String getAFriendNotename(int FriendQQ)
	{
		for(Iterator<group> it=subgroups.iterator();it.hasNext();)
		{
			group g = it.next();
			int index = g.getFriendIndex(FriendQQ);
			if (index != -1)
				return g.getAFriendNotename(index);
		}
		return null;
	}
	
	public static boolean AddAFriend(int fqq,String notename,int GroupIndex)
	{
		if (subgroups == null || MAP == null) return false;
		if (MAP.containsKey(fqq)) return false;
		if (GroupIndex >= subgroups.size()) return false;
		subgroups.get(GroupIndex).AddAFriend(fqq, notename);
		MAP.put(fqq, GroupIndex);
		return true;
	}
	
	public static boolean DeleteAFriend(int fqq)
	{
		if (subgroups == null || MAP == null) return false;
		if (!MAP.containsKey(fqq)) return false;
		subgroups.get(MAP.get(fqq)).DeleteAFriend(fqq);
		MAP.remove(fqq);
		return true;
	}
	
	public static boolean MoveAFriend(int fqq,int NextGroupIndex)
	{
		if (subgroups == null || MAP == null) return false;
		if (!MAP.containsKey(fqq)) return false;
		if (NextGroupIndex >= subgroups.size()) return false;
		int groupindex = MAP.get(fqq);
		group g = subgroups.get(groupindex);
		int findex = g.getFriendIndex(fqq);
		String notename = g.getAFriendNotename(findex);
		g.DeleteAFriend(fqq);
		MAP.remove(fqq);
		SubgroupManagerUtils.AddAFriend(fqq,notename,NextGroupIndex);
		MAP.put(fqq, NextGroupIndex);
		return true;
	}
	
	public static boolean MoveAGroup(int OldGroupIndex,int NextGroupIndex)
	{
		if (subgroups == null) return false;
		int num = subgroups.size();
		if (OldGroupIndex >= num || NextGroupIndex >= num) return false;
		group g = subgroups.get(OldGroupIndex);
		subgroups.remove(OldGroupIndex);
		subgroups.add(NextGroupIndex, g);
		InitMAP();
		return true;
	}
	
	public static void AddAGroup(String name)
	{
		//默认加在最后，内部类不好实例化，利用内部类的实例拿到一个新的内部类实例
		if (subgroups == null) return;
		subgroups.add(subgroups.get(0).newAGroup(name));
	}
	
	public static void DeleteAGroup(int index)
	{
		if (subgroups == null) return;
		if (subgroups.size() == 1) return; //还有一个分组不允许删了啊
		subgroups.remove(index);
		InitMAP();
	}
	
	public static boolean UpdateAFriendNotename(int fqq,String newname)
	{
		if (subgroups == null) return false;
		if (!MAP.containsKey(fqq)) return false;
		return subgroups.get(MAP.get(fqq)).UpdateAFriendNotename(fqq, newname);
	}
	
	public static boolean UpdateAGroupName(int index,String newname)
	{
		if (subgroups == null || index >= subgroups.size()) return false;
		subgroups.get(index).setName(newname);
		return true;
	}
	
	public static String ToJsonString()
	{
		if (subgroups == null) return null;
		Gson gson = new Gson();
		return gson.toJson(subgroups);
	}
	
	private static ArrayList<group> JsonToArrayList (String json)
	{
		/* 仅解析以下结构
		[ {index:1,name:"我的好友",fqq:[10000,10001],fnotename:["好友1","好友2"]},
		  {index:2,name:"我的家人",fqq:[10002,10003],fnotename:["家人1","家人2"]}] ]
		*/
		JsonParser parser = new JsonParser();
		JsonArray jsonArray = parser.parse(json).getAsJsonArray();
		Gson gson = new Gson();
		ArrayList<group> groups = new ArrayList<>();
		for(JsonElement g : jsonArray)
		{
			groups.add(gson.fromJson(g, group.class));
		}
		return groups;
	}
	
	private static void InitMAP()
	{
		if (subgroups == null) 
		{
			logger.writelog("Bean_subgroup", "未初始化");
			return;
		}
		MAP = new HashMap<>();
		for (int i=0;i<subgroups.size();i++)
		{
			ArrayList<Integer> fqq = subgroups.get(i).getfqq();
			for(Iterator<Integer> item = fqq.iterator();item.hasNext();)
				MAP.put(item.next(), i);
		}
		
	}
	
	
	
}
