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
import java.util.List;
import java.util.Vector;

public class GroupsFrame {
    private int qq=0;
    private StyledDocument input_doc;
    private StyledDocument output_doc;
    private String filePath = null;
    private  String facePath = "C:/Users/zk/Desktop/MiniQQ资料/Face/";
    JFrame frameGroups;
    JTextPane textPaneSmall;
    JTextPane textPaneBig ;

    public void  InsertImg (ImageIcon imageIcon){
        textPaneSmall.insertIcon(imageIcon);
    }

    public GroupsFrame(){

    }
    public void music_GetMessage(){
        File FriMsg ;
        URI uri;
        URL url;
        try{
            FriMsg = new File("Resource/Music/msg_g.wav");
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
    public GroupsFrame(HIMUI miniQQ,String name,int qq,int qunnum){
        this.qq = qq;

        frameGroups = new JFrame();
        frameGroups.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
        frameGroups.setUndecorated(true);
        frameGroups.setBackground(new Color(0,0,100));
        frameGroups.setBounds(500, 10, 900, 900);
        frameGroups.setVisible(false);
        frameGroups.setResizable(false);
        frameGroups.setLayout(null);
        frameGroups.setBackground(Color.WHITE);
        JButton buttonFile  = new JButton("文件");
        buttonFile.setFont(new Font("微软雅黑",Font.BOLD,25));
        buttonFile.setBackground(new Color(0,0,0));
        buttonFile.setOpaque(false);
        buttonFile.setBounds(700,30,200,45);
        frameGroups.add(buttonFile);
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
                g.setFont(new Font("正楷",Font.BOLD,30));
                g.drawString(name.toString(),230,50);
            }
        };
        JPanel panelListTop = new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont(new Font("微软雅黑",Font.BOLD,20));
                g.drawLine(0,39,200,39);
                g.drawString("群成员",10,25);
            }
        };
        //群成员列表
        JPanel panelList = new JPanel();
        ArrayList<Bean_friendinfo> members = miniQQ.robot.queryQunMembers(qunnum);
        String member[] = new String[members.size()];
        MyList listMember;
        for (int i = 0;i<members.size();i++){
            member[i] = ""+(i+1)+" "+members.get(i).getnickname()+"  ("+members.get(i).getfqq()+")";
            ListManager.initMap(i,members.get(i).getfqq());
        }
        GroupFileWindow groupFileWindow = new GroupFileWindow(frameGroups,this,qunnum);
        panelList.setLayout(new BorderLayout());
        listMember = new MyList(member);
        buttonFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Bean_fileinfo> fileinfos = Main.miniQQ.robot.queryQunfiles(qunnum);
                if (fileinfos!=null) {
                    groupFileWindow.files = new Vector <>();
                    for (Bean_fileinfo fileinfo : fileinfos) {
                        groupFileWindow.files.add(groupFileWindow.files.size() + 1 + "." + fileinfo.getName());
                        System.out.println(fileinfo);
                    }
                    groupFileWindow.listFile.setListData(groupFileWindow.files);
                    Point point = frameGroups.getLocationOnScreen();
                    groupFileWindow.setBounds(point.x + 200, point.y + 200, 500, 500);
                    groupFileWindow.setVisible(true);
                }
            }
        });
        listMember.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (e.getClickCount() == 2) {
                        if (ListManager.getIndex(listMember.getSelectedIndex()) == qq) {

                        } else {
//                            FriendsFrame friendsFrame = FriendsFrameManager.getFriPane(ListManager.getIndex(listMember.getSelectedIndex()));
//                            friendsFrame.frameFriends.setVisible(true);
                        }
                    }
                }catch (Exception e1){
                    Bean_friendinfo userinfo = miniQQ.robot.queryUser_qq(ListManager.getIndex(listMember.getSelectedIndex()));
                    FriendsFrame friendsFrame = new FriendsFrame(miniQQ,userinfo.getnickname()+"  ("+ListManager.getIndex(listMember.getSelectedIndex())+")",qq,ListManager.getIndex(listMember.getSelectedIndex()));
                    friendsFrame.frameFriends.setVisible(true);
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
        listMember.setFont(new Font("微软雅黑",Font.BOLD,20));
        JScrollPane scrollPaneList = new JScrollPane(listMember);
        panelList.setBounds(700,115,200,785);
        panelList.add(scrollPaneList);
        panelListTop.setBackground(Color.WHITE);
        panelListTop.setBounds(700,75,200,40);
        JButton buttonZoom = new JButton(config.miniSize);
        buttonZoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameGroups.setExtendedState(JFrame.ICONIFIED);
            }
        });
        JButton buttonClose = new JButton(config.closeChatFrame);
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupFrameManager.removeGroup(qunnum);
                frameGroups.dispose();
            }
        });
        buttonZoom.setBounds(836,0,32,32);
        buttonClose.setBounds(868,0,32,32);
        panelUp.setLayout(null);
        panelBottom.setLayout(null);
        panelBottom.setBounds(0,770,700,40);
        panelUp.setBounds(0,0,900,75);
        panelUp.add(buttonZoom);
        panelUp.add(buttonClose);
        frameGroups.add(panelBottom);
        frameGroups.add(panelUp);
        frameGroups.add(panelListTop);
        frameGroups.add(panelList);
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
        JButton buttonShake = new JButton(config.jitter);
        buttonShake.setBackground(new Color(0,0,0));
        buttonShake.setOpaque(false);
        buttonShake.setBounds(200,0,50,40);
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
        buttonSendPicture.setBounds(200,0,50,40);
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
        panelBottom.add(buttonSendPicture);
        frameGroups.add(buttonSend);

        textPaneSmall = new JTextPane();
        textPaneBig = new JTextPane();
        input_doc =textPaneSmall.getStyledDocument();
        output_doc = textPaneBig.getStyledDocument();
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
        frameGroups.add(scrollPaneSmall);
        frameGroups.add(scrollPaneBig);
        FaceWindow faceWindow = new FaceWindow(frameGroups,this);
        faceWindow.setVisible(false);
        FontWindow fontWindow = new FontWindow(frameGroups,this);
        ChatLogWindow chatLogWindow  =new ChatLogWindow(frameGroups,this);
        chatLogWindow.setVisible(false);
        Point point1MouseOld = java.awt.MouseInfo.getPointerInfo().getLocation();
        miniQQ.old_X = point1MouseOld.x;
        miniQQ.old_Y = point1MouseOld.y;
        frameGroups.addMouseListener(new MouseListener() {
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
                groupFileWindow.setVisible(false);

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

        frameGroups.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frameGroups.setLocation(frameGroups.getX()+e.getX()-miniQQ.old_X,frameGroups.getY()+e.getY()-miniQQ.old_Y);
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
                chatLogWindow.setVisible(false);
                groupFileWindow.setVisible(false);
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
                chatLogWindow.setVisible(false);
                groupFileWindow.setVisible(false);

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
                Point point = frameGroups.getLocationOnScreen();
                chatLogWindow.setBounds(point.x+900,point.y+0,500,900);
                ArrayList<Bean_message> messages1 = miniQQ.robot.queryQunChatRecords(qq, qunnum);
                for(Bean_message message : messages1){
                    String content =chatLogWindow.textPane.getText()+ message.getSender() + '\t'+message.getTime()+'\n'+message.getContent()+'\n';
                    chatLogWindow.textPane.setText(content);
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
                    Bean_fileinfo afile = new Bean_fileinfo("file",new File(miniQQ.filename));
                    int fileindex = miniQQ.robot.uploadFile(afile);
                    logger.writelog("Client", "uploadFile ok: " + fileindex);
                    boolean addQunfileOK = Main.miniQQ.robot.addQunfile(qq, qunnum, fileindex);
                    System.out.println(addQunfileOK);



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
                Bean_message message = new Bean_message(qq,qunnum,Bean_message.TYPE_Send2Qun, tools.get_nowtime(true),textPaneSmall.getText());
                String content =textPaneBig.getText()+ message.getSender() + '\t'+message.getTime()+'\n'+message.getContent()+'\n';
                if (!String.valueOf(message.getContent()).equals("")) {
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
