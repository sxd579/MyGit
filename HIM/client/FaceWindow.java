package com.HIM.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FaceWindow extends JWindow {
     JFrame owner;
    public ArrayList<JButton> faceListBt = new ArrayList <>();
    public FaceWindow(JFrame owner,FriendsFrame friendsFrame) {
        super(owner);
        this.owner = owner;
        this.setVisible(false);
        JPanel panelFace = new JPanel();
        this.add(panelFace);
        panelFace.setLayout(new GridLayout(8,28));

        for (int i = 0;i<=213;i++){
            int num = i;
            JButton buttonFace = new JButton();
            ImageIcon imageIconFace = new ImageIcon("C:/Users/zk/Desktop/MiniQQ资料/Face/"+i+".gif");
            buttonFace.setBackground(new Color(255,255,255));
            buttonFace.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    friendsFrame.insertIcon(num);
//                    friendsFrame.InsertImg(imageIconFace);
//                    friendsFrame.textPaneSmall.insertIcon(imageIconFace);
                }
            });
            buttonFace.setIcon(imageIconFace);
            faceListBt.add(buttonFace);
        }
        for (int i=0;i<faceListBt.size();i++){
         panelFace.add(faceListBt.get(i));
        }

    }

    public FaceWindow(JFrame owner,GroupsFrame groupsFrame) {
        super(owner);
        this.owner = owner;
        this.setVisible(false);
        JPanel panelFace = new JPanel();
        this.add(panelFace);
        panelFace.setLayout(new GridLayout(8,28));

        for (int i = 0;i<=213;i++){
            int num = i;
            JButton buttonFace = new JButton();
            ImageIcon imageIconFace = new ImageIcon("C:/Users/zk/Desktop/MiniQQ资料/Face/"+i+".gif");
            buttonFace.setBackground(new Color(255,255,255));
            buttonFace.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    groupsFrame.insertIcon(num);
                }
            });
            buttonFace.setIcon(imageIconFace);
            faceListBt.add(buttonFace);
        }
        for (int i=0;i<faceListBt.size();i++){
            panelFace.add(faceListBt.get(i));
        }

    }

}
