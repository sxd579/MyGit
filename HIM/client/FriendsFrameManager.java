package com.HIM.client;

import com.HIM.common.SubgroupManagerUtils;

import java.util.HashMap;
import java.util.Map;

public class FriendsFrameManager {
   static Map <Integer, FriendsFrame> map = new HashMap();
    public static void initMap(int key, FriendsFrame friendsFrame){
        map.put(key,friendsFrame);
    }
    public static FriendsFrame getFriPane(int key,int qq){
        String nick_name = SubgroupManagerUtils.getAFriendNotename(key);
        if (map.get(key)==null){
            initMap(key,new FriendsFrame(Main.miniQQ,nick_name+"  ("+key+")",qq,key));
        }
        return map.get(key);
    }
    public static void removeFri(int key){
        map.remove(key);
    };
}
