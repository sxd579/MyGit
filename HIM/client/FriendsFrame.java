package com.HIM.client;

import com.HIM.common.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class FriendsFrame {
    private int qq=0,fqq=0;
    private StyledDocument input_doc;
    private StyledDocument output_doc;
    private String filePath = null;
    private  String facePath = "C:/Users/zk/Desktop/MiniQQ资料/Face/";
    JFrame frameFriends;
    JTextPane textPaneSmall;
    JTextPane textPaneBig ;;
    //这个对象可以完成下列操作，很方便，实现图文混和显示
    int x=500,y=10,width=700,height=900;
    static boolean drawIsOpened = false;
    public void  InsertImg (ImageIcon imageIcon){
        textPaneSmall.insertIcon(imageIcon);
    }
    public FriendsFrame(){

    }
    public void music_GetMessage(){
        File FriMsg ;
        URI uri;
        URL url;
        try{
            FriMsg = new File("Resource/Music/msg_class.wav");
//            file文件直接转url会有bug(编译出错)故应file.toURI().toURL().
            uri = FriMsg.toURI();
            url = uri.toURL();
//            一遍
            AudioClip a;
            a = Applet.newAudioClip(url);
            a.play();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public FriendsFrame(HIMUI miniQQ,String name,int qq,int fqq){
        this.qq = qq;
        this.fqq = fqq;
        frameFriends = new JFrame();
        frameFriends.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
        frameFriends.setUndecorated(true);
        frameFriends.setBackground(new Color(0,0,100));
        frameFriends.setBounds(x, y, width, height);
        frameFriends.setVisible(false);
        frameFriends.setResizable(false);
        frameFriends.setLayout(null);
        JPanel panelBottom = new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(config.bottomImg.getImage(), 0, 0, null);
                g.drawLine(698,0,698,40);
            }
        };
        JPanel panelUp = new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(config.upImg.getImage(), 0, 0, null);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));
            }
        };
        JButton buttonName = new JButton(name.toString());
        buttonName.setBorderPainted(false);
        buttonName.setFont(new Font("正楷",Font.BOLD,30));
        buttonName.setBounds(90,10,500,65);
        buttonName.setBackground(new Color(0,0,0));
        buttonName.setOpaque(false);
        panelUp.add(buttonName);
        JButton buttonZoom = new JButton(config.miniSize);
        buttonName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   JFrame frameFriInfo = new JFrame();
                frameFriInfo.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
                  Bean_friendinfo friendinfo = Main.miniQQ.robot.queryUser_qq(fqq);
                   ImageIcon sourceImage = new ImageIcon("src/com/HIM/user_photos/" + friendinfo.getphotoindex());
                   Image photo_user = sourceImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                   ImageIcon friendPt = new ImageIcon(photo_user);
                   frameFriInfo.setTitle("好友资料");
                   frameFriInfo.setVisible(true);
                   frameFriInfo.setBounds(600,300,600,300);
                   JPanel panelFriInfo =new JPanel(){
                       public void paintComponent(Graphics g) {
                           super.paintComponent(g);
                           g.drawImage(friendPt.getImage(), 60, 80, null);
                           g.drawLine(698,0,698,40);
                       }
                   };
                   panelFriInfo.setLayout(null);
                   frameFriInfo.add(panelFriInfo);
                   panelFriInfo.setBackground(Color.white);
                   JPanel panelInfo =new JPanel();
                   panelInfo.setOpaque(false);
                   panelInfo.setBounds(250,10,400,240);
                   frameFriInfo.setResizable(false);
                   if (friendinfo.getsign()==null){
                       friendinfo.setsign(" ");
                   }
                   JLabel labelNickname =new JLabel("昵称:"+friendinfo.getnickname());
                   JLabel labelSign = new JLabel("个性签名:"+friendinfo.getsign());
                   JLabel labelAge = new JLabel("年龄:"+String.valueOf(Integer.valueOf(tools.get_nowtime(false).substring(0,4))-Integer.valueOf(friendinfo.getbirthday().substring(0,4))));
                   JLabel labelBir =new JLabel("生日："+friendinfo.getbirthday());
                   JLabel labelSex =new JLabel("性别:"+Enum_sex.getName(friendinfo.getsex()));
                   JLabel  labelXing = new JLabel("星座:" + Enum_constellation.getName(friendinfo.getconstellation()));
                   Font fontLabel = new Font("正楷",Font.BOLD,20);
                   labelNickname.setFont(fontLabel);
                   labelSign.setFont(fontLabel);
                   labelAge.setFont(fontLabel);
                   labelBir.setFont(fontLabel);
                   labelSex.setFont(fontLabel);
                   labelXing.setFont(fontLabel);
                   panelInfo.setLayout(new GridLayout(6,1));
                   panelInfo.add(labelNickname);
                   panelInfo.add(labelSign);
                   panelInfo.add(labelAge);
                   panelInfo.add(labelBir);
                   panelInfo.add(labelSex);
                   panelInfo.add(labelXing);
                   panelFriInfo.add(panelInfo);

            }
        });
        buttonZoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameFriends.setExtendedState(JFrame.ICONIFIED);
            }
        });
        JButton buttonClose = new JButton(config.closeChatFrame);
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FriendsFrameManager.removeFri(fqq);
                frameFriends.dispose();
            }
        });
        buttonZoom.setBounds(636,0,32,32);
        buttonClose.setBounds(668,0,32,32);
        panelUp.setLayout(null);
        panelBottom.setLayout(null);
        panelBottom.setBounds(0,770,700,40);
        panelUp.setBounds(0,0,700,75);
        panelUp.add(buttonZoom);
        panelUp.add(buttonClose);
        frameFriends.add(panelBottom);
        frameFriends.add(panelUp);
        JButton buttonFace = new JButton(config.face);
        buttonFace.setActionCommand("1");
        buttonFace.setBackground(new Color(0,0,0));
        buttonFace.setOpaque(false);
        buttonFace.setBounds(0,-3,50,43);
        JButton buttonUpload = new JButton(config.file);
        buttonUpload.setOpaque(false);
        buttonUpload.setBackground(new Color(0,0,0));
        buttonUpload.setBounds(50,0,50,40);
        JButton buttonChatDb = new JButton(config.chatLog);
        buttonChatDb.setBackground(new Color(0,0,0));
        buttonChatDb.setOpaque(false);
        buttonChatDb.setBounds(610,0,90,40);
        JButton buttonJitter = new JButton(config.jitter);
        buttonJitter.setBackground(new Color(0,0,0));
        buttonJitter.setOpaque(false);
        buttonJitter.setBounds(200,0,50,40);
        JButton buttonFont = new JButton(config.font);
        buttonFont.setActionCommand("1");
        buttonFont.setBackground(new Color(0,0,0));
        buttonFont.setOpaque(false);
        buttonFont.setBounds(150,0,50,40);
        JButton buttonDraw = new JButton(config.draw);
        buttonDraw.setBounds(100,0,50,40);
        buttonDraw.setOpaque(false);
        buttonDraw.setBackground(new Color(0,0,0));
        JButton buttonSendPicture = new JButton(config.sendPicture);
        buttonSendPicture.setBounds(250,0,50,40);
        buttonSendPicture.setBackground(new Color(0,0,0));
        buttonSendPicture.setOpaque(false);
        JButton buttonSend = new JButton("发送");
        buttonSend.setBackground(new Color(0,204,255));
        buttonSend.setFont(new Font("微软雅黑",Font.BOLD,20));
        buttonSend.setBounds(610,810,90,90);
        panelBottom.add(buttonFace);
        panelBottom.add(buttonUpload);
        panelBottom.add(buttonChatDb);
        panelBottom.add(buttonDraw);
        panelBottom.add(buttonFont);
        panelBottom.add(buttonJitter);
        panelBottom.add(buttonSendPicture);
        frameFriends.add(buttonSend);

        textPaneSmall = new JTextPane();
        textPaneBig = new JTextPane();
        this.input_doc = textPaneSmall.getStyledDocument();
        this.output_doc = textPaneBig.getStyledDocument();
//                       JTextArea textAreaSmall = new JTextArea(2,20);
        JScrollPane scrollPaneSmall = new JScrollPane(textPaneSmall);
        scrollPaneSmall.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textPaneSmall.setFont(new Font("微软雅黑",Font.BOLD,30));
//                       textAreaSmall.setLineWrap(true);
//                       textAreaSmall.setWrapStyleWord(true);
//                       textAreaSmall.setFont(new Font("微软雅黑",Font.BOLD,30));

        JScrollPane scrollPaneBig = new JScrollPane(textPaneBig);
        textPaneBig.setEditable(false);
        textPaneBig.setFont(new Font("微软雅黑",Font.BOLD,30));
        scrollPaneBig.setBounds(0,75,700,695);
        scrollPaneSmall.setBounds(0,810,610,90);
        frameFriends.add(scrollPaneSmall);
        frameFriends.add(scrollPaneBig);
        FaceWindow faceWindow = new FaceWindow(frameFriends,this);
        faceWindow.setVisible(false);
        FontWindow fontWindow = new FontWindow(frameFriends,this);
        ChatLogWindow chatLogWindow = new ChatLogWindow(frameFriends,this);
        chatLogWindow.setVisible(false);
        Point point1MouseOld = java.awt.MouseInfo.getPointerInfo().getLocation();
        miniQQ.old_X = point1MouseOld.x;
        miniQQ.old_Y = point1MouseOld.y;
        frameFriends.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {
                miniQQ.old_X = e.getX();
                miniQQ.old_Y = e.getY();
                faceWindow.setVisible(false);
                fontWindow.setVisible(false);
                chatLogWindow.setVisible(false);

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

        frameFriends.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frameFriends.setLocation(frameFriends.getX()+e.getX()-miniQQ.old_X,frameFriends.getY()+e.getY()-miniQQ.old_Y);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        textPaneBig.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                faceWindow.setVisible(false);
                fontWindow.setVisible(false);

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
        textPaneSmall.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                faceWindow.setVisible(false);
                fontWindow.setVisible(false);

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




        //发送图片
        buttonSendPicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyFileFilter fileFilter = new MyFileFilter();
                miniQQ.chooser_upload.setFileFilter(fileFilter);
                int result = miniQQ.chooser_upload.showDialog(miniQQ,"发送");
                miniQQ.filename = miniQQ.chooser_upload.getSelectedFile().getPath();
                filePath = miniQQ.filename;
                insertPicture(filePath);
            }
        });
        //聊天记录
        buttonChatDb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatLogWindow.setVisible(true);
                Point point = frameFriends.getLocationOnScreen();
                chatLogWindow.setBounds(point.x+700,point.y+0,500,900);
                ArrayList<Bean_message> messages1 = miniQQ.robot.queryChatRecords(qq, fqq);
                for(Bean_message message : messages1){
                    String content =chatLogWindow.textPane.getText()+ message.getSender() + '\t'+message.getTime()+'\n'+message.getContent()+'\n';
                    chatLogWindow.textPane.setText(content);
                }

            }
        });
        //抖动
        buttonJitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bean_message message = new Bean_message(qq,fqq,Bean_message.TYPE_Jitter,tools.get_nowtime(true),null);
                miniQQ.robot.sendMessage(message);
                for (int i = 0; i < 4; i++) {
                    frameFriends.setLocation(frameFriends.getX() + 4,frameFriends.getY());
                    frameFriends.setLocation(frameFriends.getX(),frameFriends.getY() - 4);
                    try {
                        Thread.sleep(30);
                        frameFriends.setLocation(frameFriends.getX() - 8,frameFriends.getY());
                        frameFriends.setLocation(frameFriends.getX() ,frameFriends.getY());
                        Thread.sleep(30);
                        frameFriends.setLocation(frameFriends.getX() ,frameFriends.getY());
                        frameFriends.setLocation(frameFriends.getX() ,frameFriends.getY()+4);
                        Thread.sleep(30);
                        frameFriends.setLocation(frameFriends.getX()+4 ,frameFriends.getY());
                        frameFriends.setLocation(frameFriends.getX() ,frameFriends.getY());
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        buttonDraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //发送请求
                YouDrawMeGuessComm drawer = new YouDrawMeGuessComm(config.SEVER_IP, config.port);
                DrawOwnerFrame drawFrame =DrawOwnerFrameManager.getDrawPane(fqq,qq,drawer);
                drawFrame.frameDraw.setVisible(true);
                if (!drawIsOpened) {
                    drawer.InvitePlayer(qq, fqq); //发送邀请函个人
                    drawIsOpened = true;

                }

            }
        });

        buttonFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand()=="1"){
                    Point point = buttonFace.getLocationOnScreen();
                    fontWindow.setBounds(point.x, point.y - 40, 700, 40);
                    fontWindow.setVisible(true);
                    buttonFont.setActionCommand("2");
                }else {
                    fontWindow.setVisible(false);
                    buttonFont.setActionCommand("1");
                }

            }
        });
        buttonUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int result = miniQQ.chooser.showDialog(miniQQ,"上传");
                    miniQQ.filename = miniQQ.chooser.getSelectedFile().getPath();
//                    Bean_fileinfo afile = new Bean_fileinfo("file",new File(miniQQ.filename));
//                    int fileindex = miniQQ.robot.uploadFile(afile);
//                    logger.writelog("Client", "uploadFile ok: " + fileindex);
                    JFrame frameUpload = new JFrame("选择");
                    JPanel panelUpload = new JPanel();
                    panelUpload.setLayout(new FlowLayout(FlowLayout.CENTER));
                    frameUpload.setBounds(700,450,400,100);
                    frameUpload.setVisible(true);
                    JButton buttonOnLine = new JButton("在线传输");
                    buttonOnLine.setFont(new Font("微软雅黑",Font.BOLD,25));
                    buttonOnLine.setActionCommand("1");
                    JButton buttonOffLine = new JButton("离线传输");
                    buttonOffLine.setActionCommand("2");
                    buttonOffLine.setFont(new Font("微软雅黑",Font.BOLD,25));
                    frameUpload.add(panelUpload);
                    panelUpload.add(buttonOnLine);
                    panelUpload.add(buttonOffLine);
                    ActionListener listenerUpload = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frameUpload.dispose();
                            FileTransfer fileTransfer = new FileTransfer(config.SEVER_IP, config.port);
                            fileTransfer.sendFile(new File(miniQQ.filename),qq,fqq,e.getActionCommand()=="1");

                        }
                    };
                    buttonOnLine.addActionListener(listenerUpload);
                    buttonOffLine.addActionListener(listenerUpload);



                } catch (Exception e2) {
                    System.out.println("没有选择文件");
                }
            }
        });
        buttonFace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand()=="1") {
                    Point point = buttonFace.getLocationOnScreen();
                    faceWindow.setBounds(point.x, point.y - 300, 700, 300);
                    faceWindow.setVisible(true);
                    buttonFace.setActionCommand("2");
                }else {
                    faceWindow.setVisible(false);
                    buttonFace.setActionCommand("1");
                }
            }
        });
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSend.setEnabled(false);
                Bean_message message = new Bean_message(qq,fqq,Bean_message.TYPE_SendMessage, tools.get_nowtime(true),textPaneSmall.getText());
                String content =textPaneBig.getText()+ message.getSender() + '\t'+message.getTime()+'\n'+message.getContent()+'\n';
                if (!String.valueOf(message.getContent()).equals("")) {
//                    textPaneBig.setText(content);
                    sendMessage();
                    miniQQ.robot.sendMessage(message);
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            new JLabel("<html><h2><font color='blue'><font size=\"25\"> 发送信息不能为空</font></h2></html>"),
                            "消息",
                            JOptionPane.ERROR_MESSAGE);
                }
                textPaneSmall.setText(null);
                buttonSend.setEnabled(true);
            }
        });

    }

    public void sendMessage()
    {
        System.out.println(qq);
        String content = qq +"    "+tools.get_nowtime(true)+'\n'+this.textPaneSmall.getText()+'\n';
        if (!content.equals(""))
        {
            //把输出改为包装成   robot.sendMessage(message);
            System.out.println(content);
            this.textPaneSmall.setText("");
            OutputMessage(content);
            this.textPaneSmall.requestFocusInWindow();
        }
    }




    public void insertIcon(int value)
    {
        Style style = input_doc.addStyle("face", null);
        StyleConstants.setIcon(style, new ImageIcon(facePath + value + ".gif"));
        try
        {
            //加个希腊字母魔法值作表情分割
            input_doc.insertString(this.textPaneSmall.getCaretPosition(), "φ" + "α" + value + "φ" , style);
            this.textPaneSmall.requestFocusInWindow();
        } catch (BadLocationException e)
        {
            e.printStackTrace();
        }
    }

    public void insertPicture(String path)
    {
        //先发送到服务器得到文件编号
        Bean_fileinfo file = new Bean_fileinfo("picture", new File(path));
        file.setName(file.getMD5());
        int index = Main.miniQQ.robot.uploadFile(file);
        if (index == -1) return;
        Style style = input_doc.addStyle("picture", null);
        StyleConstants.setIcon(style, new ImageIcon(path));
        try
        {
            //加个希腊字母魔法值作表情分割
            input_doc.insertString(this.textPaneSmall.getCaretPosition(), "φ" + "β" + file.getMD5() + "φ" , style);
            this.textPaneSmall.requestFocusInWindow();
        } catch (BadLocationException e)
        {
            e.printStackTrace();
        }
    }
    public void OutputMessage(String content)
    {
        String strs[] = content.split("φ");
        for(String str : strs)
        {
            if (!str.equals(""))
            {
                try
                {
                    if (str.charAt(0) == 'α') //自带表情
                    {
                        String index = str.substring(1);
                        Style style = output_doc.addStyle("face", null);
                        StyleConstants.setIcon(style, new ImageIcon(facePath + index + ".gif"));
                        output_doc.insertString(output_doc.getLength(), "φ" + "α" + index + "φ" , style);
                    }else if (str.charAt(0) == 'β') //图片
                    {
                        String md5 = str.substring(1);
                        File picture = new File(filePath + md5);
                        if (!picture.exists())
                        {
                            Main.miniQQ.robot.downloadFile_md5(md5, picture);
                        }
                        if (picture.exists())
                        {
                            Style style = output_doc.addStyle("picture", null);
                            StyleConstants.setIcon(style, new ImageIcon(filePath + md5));//填下载了的表情目录
                            output_doc.insertString(output_doc.getLength(), "φ" + "β" + md5 + "φ" , style);
                        }
                    }else
                    {
                        output_doc.insertString(output_doc.getLength(), str , null);
                    }
                }catch (BadLocationException e)
                {
                    e.printStackTrace();
                }
            }
        }
        //输出一个换行
        try
        {
            output_doc.insertString(output_doc.getLength(), "\n" , null);
        } catch (BadLocationException e)
        {
            e.printStackTrace();
        }
    }
}
