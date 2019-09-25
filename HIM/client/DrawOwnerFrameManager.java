package com.HIM.client;

import java.util.HashMap;
import java.util.Map;

public class DrawOwnerFrameManager {
    static Map<Integer, DrawOwnerFrame> map = new HashMap();
    public static void initMap(int key, DrawOwnerFrame drawFrame){
        map.put(key,drawFrame);
    }
    public static DrawOwnerFrame getDrawPane(int key,int qq,YouDrawMeGuessComm drawer){
        if (map.get(key)==null){
            initMap(key,new DrawOwnerFrame(Main.miniQQ,qq,key,drawer));
        }
        return map.get(key);
    }
    public static void removeDraw(int key){
        map.remove(key);
    };
}
