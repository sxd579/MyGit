package com.HIM.client;

import com.HIM.common.*;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FriendInfoManager  {
    static ArrayList<Integer> list;
    static ArrayList <Bean_friendinfo> friendList = new ArrayList <Bean_friendinfo>();
    static HashMap<Integer,Bean_friendinfo> mapFrined = new HashMap <>();
    static public Bean_friendinfo getFriendInfo(int key){
        return mapFrined.get(key);
    }
    public FriendInfoManager (int qq,Server_API robot)
    {
        list=SubgroupManagerUtils.getAllfqq();
        list.add(qq);
        friendList = robot.queryUsers(list);
        for(Iterator<Bean_friendinfo> it = friendList.iterator(); it.hasNext();){
            Bean_friendinfo bean = it.next();
            mapFrined.put(bean.getfqq(),bean);
            int photoindex = bean.getphotoindex();

            File file = new File("D:/Software/HIM/src/com/HIM/user_photos/" + photoindex);
            if (!file.exists()){
                boolean photodownOK = robot.downloadPhoto(photoindex, new File("D:/Software/HIM/src/com/HIM/user_photos"));
                logger.writelog("Client", "downloadphoto : " + (photodownOK ? "ok" : "fail"));
            }
        }
    }


}
