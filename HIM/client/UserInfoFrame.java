package com.HIM.client;

import com.HIM.common.Bean_userinfo;
import com.HIM.common.logger;
import com.HIM.common.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class UserInfoFrame {
    public  UserInfoFrame (HIMUI miniQQ, int qq, Bean_userinfo userinfo, ImageIcon photo_user, MainFrame mainFrame, Server_API robot){
        JFrame userInfoFrame = new JFrame();
        userInfoFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
        JPanel panelUserInfo = new JPanel();
        panelUserInfo.setLayout(null);
        panelUserInfo.setBackground(Color.WHITE);
        userInfoFrame.add(panelUserInfo);
        userInfoFrame.setTitle("HIM 用户资料");
        userInfoFrame.setBounds(600,200,400,600);
        userInfoFrame.setVisible(true);
        JLabel labelUser = new JLabel("HIM 用户资料界面");
        labelUser.setFont(new Font("微软雅黑",Font.BOLD,25));
        labelUser.setBounds(80,0,300,80);
        panelUserInfo.add(labelUser);
        JLabel labelPt= new JLabel(photo_user);
        labelPt.setBounds(50,80,100,100);
        panelUserInfo.add(labelPt);

        JButton upload = new JButton("上传头像");
        upload.setBounds(200,110,140,50);
        upload.setFont(new Font("微软夜黑",Font.BOLD,25));
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MyFileFilter fileFilter = new MyFileFilter();
                    miniQQ.chooser_upload.setFileFilter(fileFilter);
//                    miniQQ.chooser_upload.addChoosableFileFilter();
                    int result = miniQQ.chooser_upload.showDialog(miniQQ,"上传");
                    miniQQ.filename = miniQQ.chooser_upload.getSelectedFile().getPath();
                    int photoindex = robot.uploadPhoto(new File(miniQQ.filename));
                    logger.writelog("Client", "uploadphoto ok: " + photoindex);
                    robot.updatePhotoindex(qq,photoindex);
                    ImageIcon sourceImage = new ImageIcon(miniQQ.filename);
                    Image photo_user = sourceImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                    mainFrame.buttonInfo.setIcon(new ImageIcon(photo_user));
                    labelPt.setIcon(new ImageIcon(photo_user));
                } catch (Exception e2) {
                    System.out.println("没有选择文件");
                }
            }
        });
        panelUserInfo.add(upload);
        JPanel panelUser = new JPanel();
        panelUser.setLayout(new GridLayout(5,2));

        JLabel labelName = new JLabel("昵称");
        labelName.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JTextField textFieldName = new JTextField(15);
        textFieldName.setText(userinfo.getNickname());
        textFieldName.setFont(new Font("微软雅黑", Font.BOLD, 15));
        textFieldName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textFieldName.getText().length()>10){
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

        JLabel labelSign = new JLabel("个性签名");
        labelSign.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JTextField textFieldSign = new JTextField(15);
        textFieldSign.setText(userinfo.getSign());
        textFieldSign.setFont(new Font("微软雅黑", Font.BOLD, 15));
        textFieldSign.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textFieldName.getText().length()>19){
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

        JLabel labelAge = new JLabel("年龄");
        labelAge.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JTextField textFieldAge = new JTextField(15);
        textFieldAge.setOpaque(false);
        textFieldAge.setText(String.valueOf(Integer.valueOf(tools.get_nowtime(false).substring(0,4))-Integer.valueOf(userinfo.getBirthday().substring(0,4))));
        textFieldAge.setEditable(false);
        textFieldAge.setFont(new Font("微软雅黑", Font.BOLD, 15));
        textFieldAge.addKeyListener(miniQQ.listenerOnlyNum);

        JLabel labelBir = new JLabel("生日");
        labelBir.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JPanel panelBir = new JPanel();
        panelBir.setLayout(new FlowLayout(FlowLayout.CENTER));
        JTextField textFieldYear = new JTextField(4);
        textFieldYear.setText(userinfo.getBirthday().substring(0,4));
        textFieldYear.setFont(new Font("微软雅黑", Font.BOLD, 12));
        textFieldYear.addKeyListener(miniQQ.listenerOnlyNum);
        JTextField textFieldMonth = new JTextField(2);
        textFieldMonth.setText(userinfo.getBirthday().substring(5,7));
        textFieldMonth.setFont(new Font("微软雅黑", Font.BOLD, 12));
        textFieldMonth.addKeyListener(miniQQ.listenerOnlyNum);
        JTextField textFieldDay = new JTextField(2);
        textFieldDay.setText(userinfo.getBirthday().substring(9));
        textFieldDay.setFont(new Font("微软雅黑", Font.BOLD, 12));
        textFieldDay.addKeyListener(miniQQ.listenerOnlyNum);
        JLabel labelYear = new JLabel("年");
        labelYear.setFont(new Font("微软雅黑", Font.BOLD, 12));
        JLabel labelMonth = new JLabel("月");
        labelMonth.setFont(new Font("微软雅黑", Font.BOLD, 12));
        JLabel labelDay = new JLabel("日");
        labelDay.setFont(new Font("微软雅黑", Font.BOLD, 12));
        panelBir.setOpaque(false);
        panelBir.add(textFieldYear);
        panelBir.add(labelYear);
        panelBir.add(textFieldMonth);
        panelBir.add(labelMonth);
        panelBir.add(textFieldDay);
        panelBir.add(labelDay);


        JLabel labelSex = new JLabel("性别");
        labelSex.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JComboBox comboBoxSex = new JComboBox();
        comboBoxSex.setFont(new Font("微软雅黑", Font.BOLD, 15));
        comboBoxSex.setBackground(new Color(255, 255, 255));
        comboBoxSex.addItem("女");
        comboBoxSex.addItem("男");
        comboBoxSex.setSelectedIndex(userinfo.getSex());

        JLabel labelStar = new JLabel("星座");
        labelStar.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JComboBox comboBoxStar = new JComboBox();
        comboBoxStar.setFont(new Font("微软雅黑", Font.BOLD, 15));
        comboBoxStar.setBackground(new Color(255, 255, 255));
        comboBoxStar.addItem("白羊座");
        comboBoxStar.addItem("金牛座");
        comboBoxStar.addItem("双子座");
        comboBoxStar.addItem("巨蟹座");
        comboBoxStar.addItem("狮子座");
        comboBoxStar.addItem("处女座");
        comboBoxStar.addItem("天秤座");
        comboBoxStar.addItem("天蝎座");
        comboBoxStar.addItem("射手座");
        comboBoxStar.addItem("摩羯座");
        comboBoxStar.addItem("水瓶座");
        comboBoxStar.addItem("双鱼座");
        comboBoxStar.setSelectedIndex(userinfo.getConstellation());

        JButton buttonOK = new JButton("保存");
        buttonOK.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JButton buttonExit = new JButton("完成");
        buttonExit.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JPanel panelUserInfo0 = new JPanel();
        panelUserInfo0.setOpaque(false);
        panelUserInfo0.setLayout(new GridLayout(7,2));
        panelUserInfo0.add(labelName);
        panelUserInfo0.add(textFieldName);
        panelUserInfo0.add(labelSign);
        panelUserInfo0.add(textFieldSign);
        panelUserInfo0.add(labelAge);
        panelUserInfo0.add(textFieldAge);
        panelUserInfo0.add(labelBir);
        panelUserInfo0.add(panelBir);
        panelUserInfo0.add(labelSex);
        panelUserInfo0.add(comboBoxSex);
        panelUserInfo0.add(labelStar);
        panelUserInfo0.add(comboBoxStar);
        panelUserInfo0.add(buttonOK);
        panelUserInfo0.add(buttonExit);
        panelUserInfo0.setBounds(20,180,350,300);
        panelUserInfo.add(panelUserInfo0);

        //用户界面关闭
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userInfoFrame.dispose();
            }
        });

        //用户资料修改
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Bean_userinfo newUser = new Bean_userinfo(qq,textFieldSign.getText(),comboBoxSex.getSelectedIndex(),textFieldName.getText(),tools.formatBir(textFieldYear.getText(),textFieldMonth.getText(),textFieldDay.getText()),comboBoxStar.getSelectedIndex());
                boolean updateOwninfoOK = robot.updateOwnInfo(newUser);
                mainFrame.textFieldName.setText(textFieldName.getText());
                mainFrame.textFieldSig.setText(textFieldSign.getText());
                SpaceFrame.name = textFieldName.getText();
                System.out.println(updateOwninfoOK);

            }
        });



    }
}
