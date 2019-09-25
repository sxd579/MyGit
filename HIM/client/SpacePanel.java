package com.HIM.client;

import com.HIM.common.Bean_mood;
import com.HIM.common.SubgroupManagerUtils;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpacePanel extends JPanel{
    public SpacePanel(Bean_mood moods){
//        JFrame frameSpace = new JFrame();
//        frameSpace.setBounds(700,400,450,430);
        setLayout(new BorderLayout());
        setSize(550,110);
        setBackground(new java.awt.Color(135,206,250));
//        String name = "马飞";
        String name = SubgroupManagerUtils.getAFriendNotename(moods.getPoster());
        if (name == null) {
            name = SpaceFrame.name;

        };
        String time = moods.getPosttime();
        String Content = moods.getContent();
        JPanel panelHead = new JPanel();
        panelHead.setBackground(new java.awt.Color(135,206,250));
        panelHead.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelName = new JLabel(name);
        labelName.setFont(new Font("微软雅黑",Font.BOLD,20));
//        labelName.setBounds(10,-5,200,40);
        JLabel labelTime = new JLabel(time);
//        labelTime.setBounds(210,10,250,20);
        labelTime.setFont(new Font("微软雅黑",Font.BOLD,15));
        labelTime.setForeground(java.awt.Color.gray);
        JTextArea textAreaContent = new JTextArea();
        textAreaContent.setWrapStyleWord(true);
        textAreaContent.setLineWrap(true);
        textAreaContent.setFont(new Font("正楷",Font.BOLD,20));
        textAreaContent.setSize(510,50);
        JScrollPane scrollPaneContent = new JScrollPane(textAreaContent);

        textAreaContent.setEditable(false);
        textAreaContent.setText(Content);
        panelHead.add(labelName);
        panelHead.add(labelTime);
//        add(labelName);
//        add(labelTime);
        add(panelHead,BorderLayout.NORTH);
        add(scrollPaneContent,BorderLayout.CENTER);
//        frameSpace.add(this);
//        frameSpace.setVisible(true);



    }

    public static void main(String[] args) {
        new SpacePanel(new Bean_mood(10000,"2018-5-8","难受啊马飞"));
    }
}
