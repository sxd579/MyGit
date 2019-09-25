package com.HIM.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatLogWindow extends Window{
    JFrame owner ;
    JTextPane textPane;
    public ChatLogWindow(JFrame owner,FriendsFrame friendsFrame) {
        super(owner);
        this.owner = owner;
        this.setVisible(false);
        JPanel panelChatDb = new JPanel();
        panelChatDb.setLayout(new BorderLayout());
        this.add(panelChatDb);
        textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);
        panelChatDb.add(scrollPane);
        textPane.setBackground(Color.white);
        textPane.setFont(new Font("微软雅黑",Font.BOLD,25));
        }
    public ChatLogWindow(JFrame owner,GroupsFrame groupsFrame) {
        super(owner);
        this.owner = owner;
        this.setVisible(false);
        JPanel panelChatDb = new JPanel();
        panelChatDb.setLayout(new BorderLayout());
        this.add(panelChatDb);
        textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);
        panelChatDb.add(scrollPane);
        textPane.setBackground(Color.white);
        textPane.setFont(new Font("微软雅黑",Font.BOLD,25));
    }

    }

