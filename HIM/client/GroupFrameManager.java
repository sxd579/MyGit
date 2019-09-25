package com.HIM.client;

import com.HIM.common.SubgroupManagerUtils;

import java.util.HashMap;
import java.util.Map;

public class GroupFrameManager {
    static Map <Integer, GroupsFrame> map = new HashMap();
    public static void initMap(int key, GroupsFrame groupsFrame){
        map.put(key,groupsFrame);
    }
    public static GroupsFrame getGroupPane(int key,String nickname,int qq){
        if (map.get(key)==null){
            initMap(key,new GroupsFrame(Main.miniQQ,nickname+"  ("+key+")",qq,key));
        }
        return map.get(key);
    }
    public static void removeGroup(int key){
        map.remove(key);
    };
}
