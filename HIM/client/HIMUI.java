package com.HIM.client;
import com.HIM.common.*;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;


public class HIMUI extends JFrame{
    JFileChooser chooser = new JFileChooser(config.chooserSource);
    JFileChooser chooser_upload = new JFileChooser(config.chooserUserImageSource);
    Server_API robot;
    ArrayList<Bean_friendinfo> friendList;
    ArrayList<Bean_quninfo> GroupList;
    String [] nameFriTable = {"HIM","昵称","个性签名","性别","年龄","生日","星座"};
    String [] nameGroupTable = {"群号","名字","创建时间"};
    DefaultTableModel modelFri = null;
    JTable tableInfoFri = new JTable(modelFri);
    Object [][] friInfo;
    DefaultTableModel modelGroup = null;
    JTable tableInfoGroup = new JTable(modelGroup);
    Object [][] GroupInfo;
    String filename;
    int qq;
    int old_X,old_Y;
    public ArrayList<DefaultMutableTreeNode> friList = new ArrayList <>();
    public ArrayList<DefaultMutableTreeNode> groupList = new ArrayList <>();
    //只能输入数字限制
    KeyListener listenerWrapLine = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };
    KeyListener listenerOnlyNum = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
            int keyChar = e.getKeyChar();
            if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

            } else {
                e.consume();//关键，屏蔽掉非法输入  
            }
        };

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    //注册
    public void  register (HIMUI miniQQ){
        JFrame frameRegister = new JFrame();
        frameRegister.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
        frameRegister.setTitle("注册");
        frameRegister.setVisible(true);
        frameRegister.setBounds(600, 100, 970, 817);
        JPanel panelRegister = new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(config.register.getImage(), 0, 0, null);
            }
        };
        JPanel panelContent = new JPanel();
        panelContent.setBounds(530, 250, 400, 300);
        panelRegister.setLayout(null);
        panelContent.setLayout(new GridLayout(8, 2));
        panelContent.setBackground(Color.WHITE);
        JLabel labelTitle = new JLabel("     HIM  注册界面");
        labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 25));
        labelTitle.setBounds(580 , 150, 300, 50);

//                JLabel labelUser = new JLabel("注册账号");
//                labelUser.setFont(new Font("微软雅黑",Font.BOLD,25));
//                JTextField textFieldUser = new JTextField(15);
//                textFieldUser.setFont(new Font("微软雅黑",Font.BOLD,25));

        JLabel labelPassword = new JLabel("密码");
        labelPassword.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JPasswordField textFieldPassword = new JPasswordField(15);
        textFieldPassword.setFont(new Font("微软雅黑", Font.BOLD, 25));
        textFieldPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (String.valueOf(textFieldPassword.getPassword()).length()>15){
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

        JLabel labelPassword1 = new JLabel("确认密码");
        labelPassword1.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JPasswordField textFieldPassword1 = new JPasswordField(15);
        textFieldPassword1.setFont(new Font("微软雅黑", Font.BOLD, 25));
        textFieldPassword1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (String.valueOf(textFieldPassword1.getPassword()).length()>15){
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

        JLabel labelName = new JLabel("昵称");
        labelName.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JTextField textFieldName = new JTextField(15);
        textFieldName.setFont(new Font("微软雅黑", Font.BOLD, 25));
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

        JLabel labelAge = new JLabel("年龄");
        labelAge.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JTextField textFieldAge = new JTextField(15);
        textFieldAge.setFont(new Font("微软雅黑", Font.BOLD, 25));
        textFieldAge.addKeyListener(listenerOnlyNum);

        JLabel labelBir = new JLabel("生日");
        labelBir.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JPanel panelBir = new JPanel();
        panelBir.setBackground(Color.WHITE);
        panelBir.setLayout(new FlowLayout(FlowLayout.CENTER));
        JTextField textFieldYear = new JTextField(4);
        textFieldYear.setFont(new Font("微软雅黑", Font.BOLD, 12));
        textFieldYear.addKeyListener(listenerOnlyNum);
        JTextField textFieldMonth = new JTextField(2);
        textFieldMonth.setFont(new Font("微软雅黑", Font.BOLD, 12));
        textFieldMonth.addKeyListener(listenerOnlyNum);
        JTextField textFieldDay = new JTextField(2);
        textFieldDay.setFont(new Font("微软雅黑", Font.BOLD, 12));
        textFieldDay.addKeyListener(listenerOnlyNum);
        JLabel labelYear = new JLabel("年");
        labelYear.setFont(new Font("微软雅黑", Font.BOLD, 12));
        JLabel labelMonth = new JLabel("月");
        labelMonth.setFont(new Font("微软雅黑", Font.BOLD, 12));
        JLabel labelDay = new JLabel("日");
        labelDay.setFont(new Font("微软雅黑", Font.BOLD, 12));
        panelBir.add(textFieldYear);
        panelBir.add(labelYear);
        panelBir.add(textFieldMonth);
        panelBir.add(labelMonth);
        panelBir.add(textFieldDay);
        panelBir.add(labelDay);


        JLabel labelSex = new JLabel("性别");
        labelSex.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JComboBox comboBoxSex = new JComboBox();
        comboBoxSex.setFont(new Font("微软雅黑", Font.BOLD, 25));
        comboBoxSex.setBackground(new Color(255, 255, 255));
        comboBoxSex.addItem("女");
        comboBoxSex.addItem("男");

        JLabel labelStar = new JLabel("星座");
        labelStar.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JComboBox comboBoxStar = new JComboBox();
        comboBoxStar.setFont(new Font("微软雅黑", Font.BOLD, 25));
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

        JButton buttonOK = new JButton("注册");
        buttonOK.setBackground(new Color(0,0,0));
        buttonOK.setOpaque(false);
        buttonOK.setFont(new Font("微软雅黑", Font.BOLD, 25));
        JButton buttonExit = new JButton("完成");
        buttonExit.setBackground(new Color(0,0,0));
        buttonExit.setOpaque(false);
        buttonExit.setFont(new Font("微软雅黑", Font.BOLD, 25));

        frameRegister.setResizable(false);
        frameRegister.add(panelRegister);
        panelRegister.add(labelTitle);
        panelRegister.add(panelContent);

//                panelContent.add(labelUser);
//                panelContent.add(textFieldUser);
        panelContent.add(labelPassword);
        panelContent.add(textFieldPassword);
        panelContent.add(labelPassword1);
        panelContent.add(textFieldPassword1);
        panelContent.add(labelName);
        panelContent.add(textFieldName);
//                panelContent.add(labelAge);
//                panelContent.add(textFieldAge);
        panelContent.add(labelBir);
        panelContent.add(panelBir);
        panelContent.add(labelSex);
        panelContent.add(comboBoxSex);
        panelContent.add(labelStar);
        panelContent.add(comboBoxStar);
        panelContent.add(buttonOK);
        panelContent.add(buttonExit);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (String.valueOf(textFieldPassword.getPassword()).equals("") || String.valueOf(textFieldPassword1.getPassword()).equals("") || String.valueOf(textFieldName.getText()).equals("") ){

                            JOptionPane.showMessageDialog(null,
                                    new JLabel("<html><h2><font color='blue'><font size=\"25\"> 注册信息不完整，请确认</font></h2></html>"),
                                    "消息",
                                    JOptionPane.INFORMATION_MESSAGE);

                    }
                    else if (!(String.valueOf(textFieldPassword.getPassword()).equals(String.valueOf(textFieldPassword1.getPassword())))) {
                        JOptionPane.showMessageDialog(null,
                                new JLabel("<html><h2><font color='blue'><font size=\"25\"> 前后密码输入不一致，请确认</font></h2></html>"),
                                "消息",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else if (Integer.parseInt(textFieldYear.getText()) >= 2020 || Integer.parseInt(textFieldYear.getText()) <= 1900 ||
                            Integer.parseInt(textFieldMonth.getText()) > 12 || Integer.parseInt(textFieldMonth.getText()) < 1 ||
                            Integer.parseInt(textFieldDay.getText()) >= 32 || Integer.parseInt(textFieldDay.getText()) < 1) {
                        JOptionPane.showMessageDialog(null,
                                new JLabel("<html><h2><font color='blue'><font size=\"25\"> 日期错误，请确认</font></h2></html>"),
                                "消息",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else if (Integer.parseInt(textFieldYear.getText()) % 4 == 0) {
                        if (Integer.parseInt(textFieldMonth.getText()) == 2) {
                            if (Integer.parseInt(textFieldDay.getText()) >= 30) {
                                JOptionPane.showMessageDialog(null,
                                        new JLabel("<html><h2><font color='blue'><font size=\"25\"> 日期错误，请确认</font></h2></html>"),
                                        "消息",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                
                                Bean_userinfo newUser = new Bean_userinfo(String.valueOf(textFieldPassword.getPassword()),textFieldName.getText(),comboBoxSex.getSelectedIndex(),textFieldYear.getText()+"-"+"0"+textFieldMonth.getText()+"-"+"0"+textFieldDay.getText(),comboBoxStar.getSelectedIndex());
                                int HIMNumber = robot.register(newUser);
                                logger.writelog("API",HIMNumber+"");
                                JOptionPane.showMessageDialog(null,
                                        new JLabel("<html><h2><font color='blue'><font size=\"25\"> 注册成功 您的HIM账号为:"+HIMNumber+"</font></h2></html>"),
                                        "消息",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                            
                        } else if (Integer.parseInt(textFieldMonth.getText()) == 4 || Integer.parseInt(textFieldMonth.getText()) == 6 || Integer.parseInt(textFieldMonth.getText()) == 9 || Integer.parseInt(textFieldMonth.getText()) == 11) {
                            if (Integer.parseInt(textFieldDay.getText()) >= 31) {
                                JOptionPane.showMessageDialog(null,
                                        new JLabel("<html><h2><font color='blue'><font size=\"25\"> 日期错误，请确认</font></h2></html>"),
                                        "消息",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                
                                Bean_userinfo newUser = new Bean_userinfo(String.valueOf(textFieldPassword.getPassword()),textFieldName.getText(),comboBoxSex.getSelectedIndex(),textFieldYear.getText()+"-"+"0"+textFieldMonth.getText()+"-"+"0"+textFieldDay.getText(),comboBoxStar.getSelectedIndex());
                                int HIMNumber = robot.register(newUser);
                                logger.writelog("API",HIMNumber+"");
                                JOptionPane.showMessageDialog(null,
                                        new JLabel("<html><h2><font color='blue'><font size=\"25\"> 注册成功 您的HIM账号为:"+HIMNumber+"</font></h2></html>"),
                                        "消息",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                            
                        }
                        else {
                            
                            Bean_userinfo newUser = new Bean_userinfo(String.valueOf(textFieldPassword.getPassword()),textFieldName.getText(),comboBoxSex.getSelectedIndex(),textFieldYear.getText()+"-"+"0"+textFieldMonth.getText()+"-"+"0"+textFieldDay.getText(),comboBoxStar.getSelectedIndex());
                            int HIMNumber = robot.register(newUser);
                            logger.writelog("API",HIMNumber+"");
                            JOptionPane.showMessageDialog(null,
                                    new JLabel("<html><h2><font color='blue'><font size=\"25\"> 注册成功</font></h2></html>"+HIMNumber),
                                    "消息",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        

                    }
                    else if (Integer.parseInt(textFieldYear.getText()) % 4 != 0) {
                        if (Integer.parseInt(textFieldMonth.getText()) == 2) {
                            if (Integer.parseInt(textFieldDay.getText()) >= 29) {
                                JOptionPane.showMessageDialog(null,
                                        new JLabel("<html><h2><font color='blue'><font size=\"25\"> 日期错误，请确认</font></h2></html>"),
                                        "消息",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                
                                Bean_userinfo newUser = new Bean_userinfo(String.valueOf(textFieldPassword.getPassword()),textFieldName.getText(),comboBoxSex.getSelectedIndex(),textFieldYear.getText()+"-"+"0"+textFieldMonth.getText()+"-"+"0"+textFieldDay.getText(),comboBoxStar.getSelectedIndex());
                                int HIMNumber = robot.register(newUser);
                                logger.writelog("API",HIMNumber+"");
                                JOptionPane.showMessageDialog(null,
                                        new JLabel("<html><h2><font color='blue'><font size=\"25\"> 注册成功 您的HIM账号为:"+HIMNumber+"</font></h2></html>"),
                                        "消息",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                            
                        }
                        else if (Integer.parseInt(textFieldMonth.getText()) == 4 || Integer.parseInt(textFieldMonth.getText()) == 6 || Integer.parseInt(textFieldMonth.getText()) == 9 || Integer.parseInt(textFieldMonth.getText()) == 11) {
                            if (Integer.parseInt(textFieldDay.getText()) >= 31) {
                                JOptionPane.showMessageDialog(null,
                                        new JLabel("<html><h2><font color='blue'><font size=\"25\"> 日期错误，请确认</font></h2></html>"),
                                        "消息",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                
                                Bean_userinfo newUser = new Bean_userinfo(String.valueOf(textFieldPassword.getPassword()),textFieldName.getText(),comboBoxSex.getSelectedIndex(),textFieldYear.getText()+"-"+"0"+textFieldMonth.getText()+"-"+"0"+textFieldDay.getText(),comboBoxStar.getSelectedIndex());
                                int HIMNumber = robot.register(newUser);
                                logger.writelog("API",HIMNumber+"");
                                JOptionPane.showMessageDialog(null,
                                        new JLabel("<html><h2><font color='blue'><font size=\"25\"> 注册成功 您的HIM账号为:"+HIMNumber+"</font></h2></html>"),
                                        "消息",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                            
                        }
                        else {
                            
                            Bean_userinfo newUser = new Bean_userinfo(String.valueOf(textFieldPassword.getPassword()),textFieldName.getText(),comboBoxSex.getSelectedIndex(),textFieldYear.getText()+"-"+"0"+textFieldMonth.getText()+"-"+"0"+textFieldDay.getText(),comboBoxStar.getSelectedIndex());
                            int HIMNumber = robot.register(newUser);
                            logger.writelog("API",HIMNumber+"");
                            JOptionPane.showMessageDialog(null,
                                    new JLabel("<html><h2><font color='blue'><font size=\"25\"> 注册成功 您的HIM账号为:"+HIMNumber+"</font></h2></html>"),
                                    "消息",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                    }

                }catch (Exception e1){
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            new JLabel("<html><h2><font color='blue'><font size=\"25\"> 信息填写不完整，请确认</font></h2></html>"),
                            "消息",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameRegister.dispose();
            }
        });
    }

    //登入界面
    public void loginPane(HIMUI miniQQ,Server_API robot) {
        this.robot = robot;
        setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MiniQQ");
        setBounds(700, 300, 450, 300);
        setResizable(false);
        JPanel panelLogin = new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(config.login.getImage(), 0, 0, null);
            }
        };
        panelLogin.setBackground(Color.WHITE);
        JPanel panelUser = new JPanel();
        JPanel panelPassword = new JPanel();
        JPanel panelBt = new JPanel();
        JLabel labelUser = new JLabel("账号:");
        JLabel labelPassword = new JLabel("密码");
        JTextField textFieldUser = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setEchoChar('*');
        JButton buttonLogin = new JButton("登入");
        buttonLogin.setMnemonic(KeyEvent.VK_ENTER);
        JButton buttonRegister = new JButton("注册账号");
        panelLogin.setLayout(null);
        panelUser.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelUser.setOpaque(false);
        panelUser.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelPassword.setOpaque(false);
        panelPassword.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBt.setOpaque(false);
        panelBt.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelUser.setBounds(0, 111, 450, 40);
        panelPassword.setBounds(0, 161, 450, 40);
        panelBt.setBounds(0, 210, 450, 40);
        labelUser.setFont(new Font("微软雅黑", Font.BOLD, 20));
        labelPassword.setFont(new Font("微软雅黑", Font.BOLD, 20));

        textFieldUser.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        textFieldUser.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textFieldUser.getText().length()>9){
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
        textFieldUser.addKeyListener(listenerOnlyNum);
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        buttonLogin.setFont(new Font("微软雅黑", Font.BOLD, 20));
        buttonRegister.setFont(new Font("微软雅黑", Font.BOLD, 20));
        add(panelLogin);
        panelLogin.add(panelPassword);
        panelLogin.add(panelUser);
        panelLogin.add(panelBt);
        panelUser.add(labelUser);
        panelUser.add(textFieldUser);
        panelPassword.add(labelPassword);
        panelPassword.add(passwordField);
        panelBt.add(buttonLogin);
        panelBt.add(buttonRegister);
        setVisible(true);
        textFieldUser.setText("10000");
        passwordField.setText("12345");
        //登入
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    if (String.valueOf(textFieldUser.getText()).equals("") || String.valueOf(passwordField.getPassword()).equals("")) {
                        JOptionPane.showMessageDialog(null,
                                new JLabel("<html><h2><font color='red'><font size=\"25\"> 输入账号密码为空，请重新输入</font></h2></html>"),
                                "逗我玩？",
                                JOptionPane.ERROR_MESSAGE);

                    }
                    else {
                        System.out.println(robot.login(Integer.valueOf(textFieldUser.getText()), String.valueOf(passwordField.getPassword())));
                        if (robot.login(Integer.valueOf(textFieldUser.getText()),String.valueOf(passwordField.getPassword()))) {
                            qq = Integer.valueOf(textFieldUser.getText());
                            com.HIM.common.logger.writelog("Client", "login ok");
                            robot.InitSubgroup(qq);
                            FriendInfoManager photos = new FriendInfoManager(qq,robot);
                            new MainFrame(miniQQ,qq,robot);
                            dispose();


                        } else {

                            JOptionPane.showMessageDialog(null,
                                    new JLabel("<html><h2><font color='red'><font size=\"25\"> 输入账号密码错误，请重新输入</font></h2></html>"),
                                    "消息",
                                    JOptionPane.ERROR_MESSAGE);
                            passwordField.setText("");

                        }
                    }

                }catch(Exception e0){
                    e0.printStackTrace();
                }


            }
        });

        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                 miniQQ.register(miniQQ);
            }
        });
    }



}





