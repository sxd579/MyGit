package com.HIM.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontWindow extends JWindow {
    JFrame owner;
    public FontWindow(JFrame owner , FriendsFrame friendsFrame){
        super(owner);
        this.owner = owner;
        this.setVisible(false);
        JLabel labelSize = new JLabel("字号");
        labelSize.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JLabel labelColor = new JLabel("颜色");
        labelColor.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JLabel labelShape = new JLabel("字形");
        labelShape.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JComboBox comboBoxSize = new JComboBox();
        comboBoxSize.addItem("16");
        comboBoxSize.addItem("18");
        comboBoxSize.addItem("20");
        comboBoxSize.addItem("22");
        comboBoxSize.addItem("24");
        comboBoxSize.addItem("25");

        comboBoxSize.setSelectedIndex(5);
        comboBoxSize.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JComboBox comboBoxColor = new JComboBox();
        comboBoxColor.addItem("黑色");
        comboBoxColor.addItem("红色");
        comboBoxColor.addItem("橙色");
        comboBoxColor.addItem("黄色");
        comboBoxColor.addItem("绿色");
        comboBoxColor.addItem("蓝色");
        comboBoxColor.setSelectedIndex(0);


        comboBoxColor.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JComboBox comboBoxShape = new JComboBox();
        comboBoxShape.addItem("宋体");
        comboBoxShape.addItem("隶书");
        comboBoxShape.addItem("楷体");
        comboBoxShape.addItem("微软雅黑");
        comboBoxShape.addItem("华文彩云");
        comboBoxShape.setSelectedIndex(3);
        comboBoxShape.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JPanel panelFont = new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("C:/Users/zk/Desktop/MiniQQ资料/字体框.jpg").getImage(), 0, 0, null);

            }
        };
        panelFont.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelFont.add(labelSize);
        panelFont.add(comboBoxSize);
        panelFont.add(labelColor);
        panelFont.add(comboBoxColor);
        panelFont.add(labelShape);
        panelFont.add(comboBoxShape);
        this.add(panelFont);
        ActionListener listenerFont = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String shape = String.valueOf(comboBoxShape.getSelectedItem());
                int size = 16;
                Color color = Color.BLACK;
              if (comboBoxSize.getSelectedIndex()==0){
                  size = 16;
              }
                if (comboBoxSize.getSelectedIndex()==1){
                    size = 18;
                }
                if (comboBoxSize.getSelectedIndex()==2){
                    size = 20;
                }
                if (comboBoxSize.getSelectedIndex()==3){
                    size = 22;
                }
                if (comboBoxSize.getSelectedIndex()==4){
                    size = 24;
                }
                if (comboBoxSize.getSelectedIndex()==5){
                    size = 25;
                }

                if (String.valueOf(comboBoxColor.getSelectedItem()) == "黑色"){
                    color = Color.BLACK;
                }
                if (String.valueOf(comboBoxColor.getSelectedItem()) == "红色"){
                    color = Color.RED;
                }
                if (String.valueOf(comboBoxColor.getSelectedItem()) == "橙色"){
                    color = Color.ORANGE;
                }
                if (String.valueOf(comboBoxColor.getSelectedItem()) == "黄色"){
                    color = Color.YELLOW;
                }
                if (String.valueOf(comboBoxColor.getSelectedItem()) == "绿色"){
                    color = Color.GREEN;
                }
                if (String.valueOf(comboBoxColor.getSelectedItem()) == "蓝色"){
                    color = Color.BLUE;
                }
                friendsFrame.textPaneSmall.setFont(new Font(shape,Font.BOLD,size));
                friendsFrame.textPaneSmall.setForeground(color);
            }

        };
        comboBoxShape.addActionListener(listenerFont);
        comboBoxSize.addActionListener(listenerFont);
        comboBoxColor.addActionListener(listenerFont);
    }

    public FontWindow(JFrame owner , GroupsFrame groupsFrame){
        super(owner);
        this.owner = owner;
        this.setVisible(false);
        JLabel labelSize = new JLabel("字号");
        labelSize.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JLabel labelColor = new JLabel("颜色");
        labelColor.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JLabel labelShape = new JLabel("字形");
        labelShape.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JComboBox comboBoxSize = new JComboBox();
        comboBoxSize.addItem("16");
        comboBoxSize.addItem("18");
        comboBoxSize.addItem("20");
        comboBoxSize.addItem("22");
        comboBoxSize.addItem("24");
        comboBoxSize.addItem("25");

        comboBoxSize.setSelectedIndex(5);
        comboBoxSize.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JComboBox comboBoxColor = new JComboBox();
        comboBoxColor.addItem("黑色");
        comboBoxColor.addItem("红色");
        comboBoxColor.addItem("橙色");
        comboBoxColor.addItem("黄色");
        comboBoxColor.addItem("绿色");
        comboBoxColor.addItem("蓝色");
        comboBoxColor.setSelectedIndex(0);


        comboBoxColor.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JComboBox comboBoxShape = new JComboBox();
        comboBoxShape.addItem("宋体");
        comboBoxShape.addItem("隶书");
        comboBoxShape.addItem("楷体");
        comboBoxShape.addItem("微软雅黑");
        comboBoxShape.addItem("华文彩云");
        comboBoxShape.setSelectedIndex(3);
        comboBoxShape.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JPanel panelFont = new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("C:/Users/zk/Desktop/MiniQQ资料/字体框.jpg").getImage(), 0, 0, null);

            }
        };
        panelFont.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelFont.add(labelSize);
        panelFont.add(comboBoxSize);
        panelFont.add(labelColor);
        panelFont.add(comboBoxColor);
        panelFont.add(labelShape);
        panelFont.add(comboBoxShape);
        this.add(panelFont);
        ActionListener listenerFont = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String shape = String.valueOf(comboBoxShape.getSelectedItem());
                int size = 16;
                Color color = Color.BLACK;
                if (comboBoxSize.getSelectedIndex()==0){
                    size = 16;
                }
                if (comboBoxSize.getSelectedIndex()==1){
                    size = 18;
                }
                if (comboBoxSize.getSelectedIndex()==2){
                    size = 20;
                }
                if (comboBoxSize.getSelectedIndex()==3){
                    size = 22;
                }
                if (comboBoxSize.getSelectedIndex()==4){
                    size = 24;
                }
                if (comboBoxSize.getSelectedIndex()==5){
                    size = 25;
                }

                if (String.valueOf(comboBoxColor.getSelectedItem()) == "黑色"){
                    color = Color.BLACK;
                }
                if (String.valueOf(comboBoxColor.getSelectedItem()) == "红色"){
                    color = Color.RED;
                }
                if (String.valueOf(comboBoxColor.getSelectedItem()) == "橙色"){
                    color = Color.ORANGE;
                }
                if (String.valueOf(comboBoxColor.getSelectedItem()) == "黄色"){
                    color = Color.YELLOW;
                }
                if (String.valueOf(comboBoxColor.getSelectedItem()) == "绿色"){
                    color = Color.GREEN;
                }
                if (String.valueOf(comboBoxColor.getSelectedItem()) == "蓝色"){
                    color = Color.BLUE;
                }
                groupsFrame.textPaneSmall.setFont(new Font(shape,Font.BOLD,size));
                groupsFrame.textPaneSmall.setForeground(color);
            }

        };
        comboBoxShape.addActionListener(listenerFont);
        comboBoxSize.addActionListener(listenerFont);
        comboBoxColor.addActionListener(listenerFont);
    }

}

