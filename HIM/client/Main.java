package com.HIM.client;
import javax.swing.*;
import java.awt.*;

public class Main {
    static HIMUI miniQQ;
    public static void main(String[] args) {
//        if(UIManager.getLookAndFeel().isSupportedLookAndFeel()){
//            final String platform = UIManager.getSystemLookAndFeelClassName();
//            // If the current Look & Feel does not match the platform Look & Feel,
//            // change it so it does.
//            if (!UIManager.getLookAndFeel().getName().equals(platform)) {
//                try {
//                    UIManager.setLookAndFeel(platform);
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            }
//        }
        miniQQ = new HIMUI();
        Thread_receiver thread_receiver = new Thread_receiver(config.SEVER_IP,config.port);
        new Thread(thread_receiver).start();
        Server_API robot = new Server_API(config.SEVER_IP,config.port,thread_receiver);
        miniQQ.loginPane(miniQQ,robot);

    }
}
