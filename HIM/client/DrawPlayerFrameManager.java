package com.HIM.client;

import java.util.HashMap;
import java.util.Map;

public class DrawPlayerFrameManager {
    static Map<Integer, DrawPlayerFrame> map = new HashMap();
    public static void initMap(int key, DrawPlayerFrame drawFrame){
        map.put(key,drawFrame);
    }
    public static DrawPlayerFrame getDrawPane(int key){
        if (map.get(key)==null){
            initMap(key,new DrawPlayerFrame());
        }
        return map.get(key);
    }
    public static void removeDraw(int key){
        map.remove(key);
    };

    public static void removeAll(){

    }
}
