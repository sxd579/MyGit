package com.HIM.client;

import com.HIM.common.Bean_mood;
import com.HIM.common.SubgroupManagerUtils;
import com.HIM.common.tools;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class SpaceFrame {
    ArrayList<Bean_mood> moods;
    static String name;
    public SpaceFrame(int qq,HIMUI miniQQ,ArrayList<Integer> list,String name){
        this.name = name;
        moods = miniQQ.robot.queryMoods(list);
        JFrame frameSpace = new JFrame();
        frameSpace.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
        frameSpace.setLayout(new BorderLayout());
        frameSpace.setTitle("HIM空间");
        frameSpace.setVisible(true);
        frameSpace.setBounds(700,300,560,630);

        JPanel panelSpace = new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(0,480,490,480);
            }
        };
        panelSpace.setBackground(new Color(135,206,250));
        panelSpace.setLayout(null);


        JPanel panelSayings  =new JPanel();
//        JPanel panelContain = new JPanel();
        panelSayings.setLayout(new FlowLayout(FlowLayout.LEFT));
        /////卡片结构
        panelSayings.setBackground(new Color(135,206,250));
//        panelContain.setOpaque(false);
//        panelContain.setLayout(new BorderLayout());
//        panelContain.setBounds(0,10,540,470);
        JScrollPane scrollPaneSayings = new JScrollPane(panelSayings);
        scrollPaneSayings.setBounds(0,10,540,470);
        int height = 0 ;
//        scrollPaneSayings.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);//总是显示
        for (int i = 0;i<moods.size();i++){
            SpacePanel panel = new SpacePanel(moods.get(i));
            panelSayings.add(panel);
            panelSayings.revalidate();
            height += panel.getHeight();
        }
        panelSayings.setPreferredSize(new Dimension(scrollPaneSayings.getWidth(),height));

        JPanel panelSend = new JPanel();
        panelSend.setLayout(null);
        panelSend.setBounds(0,480,540,100);

        JTextArea textAreaSend = new JTextArea();
        textAreaSend.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (String.valueOf(textAreaSend.getText()).length()>254){
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        textAreaSend.setFont(new Font("正楷",Font.BOLD,20));
        textAreaSend.setWrapStyleWord(true);
        textAreaSend.setLineWrap(true);
        JScrollPane scrollPaneTextArea = new JScrollPane(textAreaSend);
        scrollPaneTextArea.setBounds(0,0,440,100);

        JButton buttonSend = new JButton("发表");
        buttonSend.setBounds(440,0,100,100);
        buttonSend.setFont(new Font("微软雅黑",Font.BOLD,20));
        buttonSend.setBackground(new Color(0,204,255));

        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miniQQ.robot.postMood(new Bean_mood(qq, tools.get_nowtime(true),textAreaSend.getText()));
                textAreaSend.setText(null);
            }
        });
        frameSpace.setVisible(true);



        frameSpace.add(panelSpace);
        panelSpace.add(scrollPaneSayings);
        panelSpace.add(panelSend);
//        panelContain.add(scrollPaneSayings);
        panelSend.add(scrollPaneTextArea);
        panelSend.add(buttonSend);

    }

    public static void main(String[] args) {

    }
}
