package com.HIM.client;

import java.util.HashMap;
import java.util.Map;

public class ListManager {
    static Map<Integer, Integer> map = new HashMap();
    public static void initMap(int key, int qq){
        map.put(key,qq);
    }
    public static int getIndex(int key){
        return map.get(key);
    }
    public static void removeGroup(int key){
        map.remove(key);
    };
}
