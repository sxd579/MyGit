package com.HIM.client;

import com.HIM.common.Bean_fileinfo;
import com.HIM.common.Bean_message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

public class GroupFileWindow extends Window{
    JFrame owner;
    static Vector<String> files  = new Vector <>();
    static MyList listFile;
    public GroupFileWindow(JFrame owner, GroupsFrame groupsFrame,int quns) {
        super(owner);
        this.owner = owner;
        this.setVisible(false);
        JPanel panelFile = new JPanel();
        panelFile.setLayout(new BorderLayout());
        JLabel labelTop = new JLabel("群文件列表");
        labelTop.setFont(new Font("微软雅黑",Font.BOLD,25));
        panelFile.add(labelTop,BorderLayout.NORTH);
        this.add(panelFile);
        ArrayList<Bean_fileinfo> fileinfos = Main.miniQQ.robot.queryQunfiles(quns);
        listFile = new MyList();
		for(Bean_fileinfo fileinfo : fileinfos) {
		    listFile.getIndex().add(fileinfo.getMD5());
            files.add(files.size()+1+"."+fileinfo.getName());
            System.out.println(fileinfo);
        }
		listFile.setListData(files);
        JScrollPane scrollPane = new JScrollPane(listFile);
        panelFile.add(scrollPane,BorderLayout.CENTER);
        scrollPane.setBackground(Color.white);
        listFile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2){
                    JFrame frameConfirm = new JFrame("确认");


                    JPanel panelConfirm = new JPanel();
                    panelConfirm.setLayout(new FlowLayout(FlowLayout.CENTER));
                    frameConfirm.setBounds(700, 450, 400, 100);
                    frameConfirm.setVisible(true);
                    JButton buttonOK = new JButton("下载");

                    buttonOK.setFont(new Font("微软雅黑", Font.BOLD, 25));
                    buttonOK.setActionCommand("1");
                    JButton buttonNo = new JButton("取消");
                    buttonNo.setActionCommand("2");
                    buttonNo.setFont(new Font("微软雅黑", Font.BOLD, 25));
                    frameConfirm.add(panelConfirm);
                    panelConfirm.add(buttonOK);
                    panelConfirm.add(buttonNo);

                    buttonOK.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Main.miniQQ.robot.downloadFile_md5(listFile.getIndex().get(listFile.getSelectedIndex()),new File(config.downloadPath+listFile.getSelectedValue().toString()));
                            frameConfirm.dispose();
                        }
                    });
                    buttonNo.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        frameConfirm.dispose();
                        }
                    });
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        listFile.setFont(new Font("正楷",Font.BOLD,15));
    }
}
