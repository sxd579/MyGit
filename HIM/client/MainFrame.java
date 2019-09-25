package com.HIM.client;

import com.HIM.common.*;
import javafx.scene.text.FontBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainFrame {
    ArrayList<Bean_friendinfo> friendList;
    ArrayList<Bean_quninfo> GroupList;
    String [] nameFriTable = {"HIM","昵称","个性签名","性别","年龄","生日","星座"};.
    String [] nameGroupTable = {"群号","名字","创建时间"};
    DefaultTableModel modelFri = null;
    JTable tableInfoFri = new JTable(modelFri);
    Object [][] friInfo;
    DefaultTableModel modelGroup = null;
    JTable tableInfoGroup = new JTable(modelGroup);
    Object [][] GroupInfo;
    HIMUI miniQQ;
    int qq;
    Bean_userinfo userinfo;
    ImageIcon user_photo;
    Server_API robot;
    //ui组件
    JButton buttonInfo;
    JFrame mainFrame;
    JTextField textFieldName;
    JTextField textFieldSig;
    JTree tree;
    JTree tree2;
    public MainFrame(HIMUI miniQQ,int qq,Server_API robot) {
        this.miniQQ = miniQQ;
        this.qq = qq;
        this.robot = robot;
        userinfo = robot.queryOwnInfo(qq);
        mainFrame = new JFrame();
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 410, 750);
        mainPanel.setBackground(Color.white);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.setTitle("主界面");
        mainFrame.setBounds(1420, 0, 410, 750);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(null, new JLabel("<html><h2><font color='red'><font size=\"25\"> 确定关闭吗</font></h2></html>"), "温馨提示",
                        JOptionPane.YES_NO_OPTION);
                if (a == 0) {

                    robot.logout(qq);
                    logger.writelog("Client", "logout ok");
                    System.exit(0);  //关闭
                }
            }
        });

        //用户信息
        JPanel jPanelUser = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(config.mainSkin.getImage(), 0, 0, null);
                g.drawLine(0, 150, 410, 150);
            }
        };
        jPanelUser.setLayout(null);
        jPanelUser.setBounds(0, 0, 410, 151);
        ImageIcon sourceImage = new ImageIcon("src/com/HIM/user_photos/" + userinfo.getPhotoindex());
        Image photo_user = sourceImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        user_photo = new ImageIcon(photo_user);
        buttonInfo = new JButton(user_photo);
        buttonInfo.addActionListener(e -> buttonInfoClicked());
        buttonInfo.setBounds(20, 20, 100, 100);
        JPanel panelPerson = new JPanel();
        panelPerson.setBounds(150, 50, 200, 50);
        panelPerson.setLayout(new GridLayout(2, 2));
        JLabel labelName = new JLabel("昵称");
        labelName.setFont(new Font("微软雅黑", Font.BOLD, 20));
        labelName.setForeground(Color.white);
        JLabel labelSig = new JLabel("个性签名");
        labelSig.setFont(new Font("微软雅黑", Font.BOLD, 20));
        labelSig.setForeground(Color.white);
        textFieldName = new JTextField(25);
        textFieldName.setOpaque(false);
        textFieldName.setFont(new Font("微软雅黑", Font.BOLD, 15));
        textFieldName.setText(userinfo.getNickname());
        textFieldName.setEditable(false);
        textFieldName.setForeground(Color.white);
        textFieldSig = new JTextField(25);
        textFieldSig.setOpaque(false);
        textFieldSig.setFont(new Font("微软雅黑", Font.BOLD, 15));
        textFieldSig.setForeground(Color.white);
        textFieldSig.setText(userinfo.getSign());
        textFieldSig.setEditable(false);
        JButton buttonSpace = new JButton("HIM空间");
        buttonSpace.setFont(new Font("微软雅黑", Font.BOLD,20));
        buttonSpace.setBackground(new Color(0,0,0));
        buttonSpace.setOpaque(false);
        buttonSpace.setBounds(250,100,120,40);
        buttonSpace.setForeground(Color.WHITE);
        buttonSpace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SpaceFrame(qq,miniQQ,FriendInfoManager.list,userinfo.getNickname());

            }
        });
        panelPerson.setOpaque(false);
        panelPerson.add(labelName);
        panelPerson.add(textFieldName);
        panelPerson.add(labelSig);

        panelPerson.add(textFieldSig);
        mainPanel.add(jPanelUser);
        jPanelUser.add(buttonInfo);
        jPanelUser.add(panelPerson);
        jPanelUser.add(buttonSpace);
        //添加列表
        JButton buttonAddGroup = new JButton(config.addGroup);
        JButton buttonAddFriend = new JButton(config.addFri);
        JButton buttonCreateGroup = new JButton(config.creatGroup);
        buttonAddFriend.setBounds(0, 660, 139, 50);
        buttonAddGroup.setBounds(139, 660, 139, 50);
        buttonCreateGroup.setBounds(278, 660, 139, 50);
        mainPanel.add(buttonAddFriend);
        mainPanel.add(buttonAddGroup);
        mainPanel.add(buttonCreateGroup);


        //好友列表群列表等
        JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        jTabbedPane.setBackground(Color.white);
        jTabbedPane.setBounds(0, 150, 400, 510);
        jTabbedPane.setFont(new Font("微软雅黑", Font.BOLD, 20));
        jTabbedPane.setForeground(Color.gray);
        mainPanel.add(jTabbedPane);
        JPanel panelFriends = new JPanel();
        panelFriends.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel panelCrowd = new JPanel();
        panelCrowd.setLayout(new FlowLayout(FlowLayout.LEFT));
        JScrollPane jScrollPaneFriends = new JScrollPane(panelFriends);
        JScrollPane jScrollPaneCrowd = new JScrollPane(panelCrowd);
        jTabbedPane.addTab("   好友   ", jScrollPaneFriends);
        jTabbedPane.addTab("   群   ", jScrollPaneCrowd);
        panelFriends.setBackground(Color.WHITE);
        panelCrowd.setBackground(Color.WHITE);

        //好友列表
        MyTreeNode root = new MyTreeNode("根节点", true);
        //        DefaultMutableTreeNode node = new DefaultMutableTreeNode("我的好友");
//        root.add(node);
        int groupnum = SubgroupManagerUtils.getGroupNum();
        for (int i = 0; i < groupnum; i++) {
            MyTreeNode node = new MyTreeNode(SubgroupManagerUtils.getGroupName(i), true);
            int fnum = SubgroupManagerUtils.getFriendNum(i);
            for (int j = 0; j < fnum; j++) {
                MyTreeNode nodeChild = new MyTreeNode(SubgroupManagerUtils.getAFriendNotename(i, j)+" （"+ SubgroupManagerUtils.getAFriendqq(i, j)+")"
                        ,false);
                nodeChild.setIndex(SubgroupManagerUtils.getAFriendqq(i, j));
                System.out.println(nodeChild.getUserObject().toString());
                node.add(nodeChild);
            }
            root.add(node);
        }
        DefaultTreeModel jMode = new DefaultTreeModel(root, true);
        tree = new JTree(jMode);
        tree.setCellRenderer(new MyDefaultTreeCellRenderer());
        MyDefaultTreeCellRenderer renderer = (MyDefaultTreeCellRenderer) tree.getCellRenderer();
        renderer.setOpenIcon(config.openImg);
        renderer.setClosedIcon(config.closeImg);
        renderer.setLeafIcon(config.friendsImg);
        tree.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        tree.setRowHeight(50);//行高
        tree.setToggleClickCount(1);//点击一次给出响应
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);//设置一次只能选中一个节点
        tree.setRootVisible(false);//设置根节点不可见
        tree.putClientProperty("JTree.lineStyle", "None");//设置线条隐藏
        panelFriends.add(tree);
        MouseListener listenerFriends = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    MyTreeNode selectionNode = (MyTreeNode) tree.getLastSelectedPathComponent();
                    if (selectionNode.isLeaf() && selectionNode.getLevel() == 2) {
                        FriendsFrame friendsFrame = FriendsFrameManager.getFriPane(selectionNode.getIndex(), qq);
                        friendsFrame.frameFriends.setVisible(true);
                    }

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton()==3){
                    MyTreeNode selectionNode = (MyTreeNode) tree.getLastSelectedPathComponent();
                    if (selectionNode.isLeaf()){
                    JFrame frameChange =new JFrame("更改好友："+selectionNode.getIndex());
                    frameChange.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
                    frameChange.setLocation(800,400);
                    frameChange.setVisible(true);
                    frameChange.setResizable(false);
                    frameChange.setLayout(new FlowLayout(FlowLayout.LEFT));
                    Font font = new Font("正楷",Font.BOLD,20);
                    JTextField textFieldChange =new JTextField(10);
                    textFieldChange.addKeyListener(new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            if (textFieldName.getText().length() > 9) {
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
                    JButton buttonChange = new JButton("修改备注");
                    JButton buttonDelete =new JButton("删除好友");
                    textFieldChange.setFont(font);
                    buttonChange.setFont(font);
                    buttonDelete.setFont(font);
                    frameChange.add(textFieldChange);
                    frameChange.add(buttonChange);
                    frameChange.add(buttonDelete);
                    frameChange.pack();
                    buttonChange.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(selectionNode.getIndex());
                            SubgroupManagerUtils.UpdateAFriendNotename(selectionNode.getIndex(),textFieldChange.getText());
                            DefaultTreeModel jModeNew = new DefaultTreeModel(root,true);
                            tree.setModel(jModeNew);
                            frameChange.dispose();
                        }
                    });
                    buttonDelete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            SubgroupManagerUtils.DeleteAFriend(selectionNode.getIndex());
                            DefaultTreeModel jModeNew = new DefaultTreeModel(root,true);
                            tree.setModel(jModeNew);
                            frameChange.dispose();
                        }
                    });

                    mainFrame.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                           frameChange.dispose();
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
                    jTabbedPane.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            frameChange.dispose();
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
                    panelFriends.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            frameChange.dispose();
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
                    tree.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            frameChange.dispose();
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
                }

            }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e){

                  }
        };
        //群列表
        DefaultMutableTreeNode root2 = new DefaultMutableTreeNode("根节点", true);
        ArrayList <Bean_quninfo> quns = robot.queryUserQuns(qq);
        GroupInfoManager.Init(quns);
        MyTreeNode node = new MyTreeNode("我的HIM群", true);
        if (quns != null) {
            int gnum = quns.size();

            for (int j = 0; j < gnum; j++) {
                MyTreeNode nodeChild = new MyTreeNode(quns.get(j).getQunName() + " （" + quns.get(j).getQunNum() + ")"
                        , false);
                nodeChild.setIndex(quns.get(j).getQunNum());
                nodeChild.setInfo(quns.get(j).getQunName());
                System.out.println(nodeChild.getUserObject().toString());
                node.add(nodeChild);
            }
            root2.add(node);
        }
        else {
            node.setAllowsChildren(false);
            root2.add(node);
        }


        DefaultTreeModel jMode2 = new DefaultTreeModel(root2, true);
        tree2 = new JTree(jMode2);
        DefaultTreeCellRenderer renderer2 = (DefaultTreeCellRenderer) tree2.getCellRenderer();
        renderer2.setOpenIcon(config.openImg);
        renderer2.setClosedIcon(config.closeImg);
        renderer2.setLeafIcon(config.groupImg);

        tree2.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        tree2.setRowHeight(50);//行高
        tree2.setToggleClickCount(1);//点击一次给出响应
        tree2.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);//设置一次只能选中一个节点
        tree2.setRootVisible(false);//设置根节点不可见
        tree2.putClientProperty("JTree.lineStyle", "None");//设置线条隐藏
        panelCrowd.add(tree2);
        MouseListener listenerGroups = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    MyTreeNode selectionNode = (MyTreeNode) tree2.getLastSelectedPathComponent();
                    if (selectionNode.isLeaf() && selectionNode.getLevel() == 2) {
                        GroupsFrame groupsFrame = GroupFrameManager.getGroupPane(selectionNode.getIndex(), selectionNode.getInfo(), qq);
                        groupsFrame.frameGroups.setVisible(true);
                    }
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
        };
        tree.addMouseListener(
                listenerFriends);
        tree2.addMouseListener(listenerGroups);
        ActionListener listenerAddFrid = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameAddFrid = new JFrame();
                frameAddFrid.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
                JPanel panelAddFrid = new JPanel() ;
                panelAddFrid.setLayout(null);
                panelAddFrid.setBackground(Color.WHITE);
                frameAddFrid.add(panelAddFrid);
                frameAddFrid.setTitle("添加好友");
                frameAddFrid.setBounds(500, 300, 900, 500);
                frameAddFrid.setVisible(true);
                frameAddFrid.setLayout(null);
                modelFri = new DefaultTableModel(new Object[0][7], nameFriTable);
                tableInfoFri.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        super.componentResized(e);
                        System.out.println(tableInfoFri.getSelectedColumn());
                    }
                });
                tableInfoFri.setBackground(Color.WHITE);
                tableInfoFri.setOpaque(false);
                tableInfoFri.getTableHeader().setPreferredSize(new Dimension(50, 20));
                tableInfoFri.getTableHeader().setReorderingAllowed(false);
                tableInfoFri.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tableInfoFri.getTableHeader().setResizingAllowed(true);
                tableInfoFri.setModel(modelFri);
                tableInfoFri.setRowHeight(20);
                tableInfoFri.setSelectionForeground(Color.GREEN);
                tableInfoFri.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                JScrollPane scrollPaneTableFri = new JScrollPane(tableInfoFri);
                scrollPaneTableFri.setOpaque(false);
                JPanel panelTable = new JPanel();
                panelTable.setBackground(Color.WHITE);
                panelTable.setBounds(400, 0, 500, 500);
                panelTable.add(scrollPaneTableFri);
                panelAddFrid.add(panelTable);
                JLabel labelFridInfo = new JLabel("昵称/HIM号:");
                labelFridInfo.setBounds(100, 10, 200, 50);
                labelFridInfo.setFont(new Font("微软雅黑", Font.BOLD, 25));
                JTextField textFieldFridInfo = new JTextField(10);
                textFieldFridInfo.setBounds(250, 10, 200, 50);
                textFieldFridInfo.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (textFieldName.getText().length() > 9) {
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

                textFieldFridInfo.setFont(new Font("微软雅黑", Font.BOLD, 20));
                JButton buttonResearch = new JButton("查询");
                buttonResearch.setBounds(450, 10, 100, 50);
                buttonResearch.setFont(new Font("微软雅黑", Font.BOLD, 25));

                JLabel labelFridAdd = new JLabel("确认HIM号");
                labelFridAdd.setFont(new Font("微软雅黑", Font.BOLD, 25));
                JTextField textFieldFridAdd = new JTextField(10);
                textFieldFridAdd.setFont(new Font("微软雅黑", Font.BOLD, 20));
                JLabel labelFridGName = new JLabel("备注");
                labelFridGName.setFont(new Font("微软雅黑", Font.BOLD, 25));
                JLabel labelFridGroup = new JLabel("分组");
                labelFridGroup.setFont(new Font("微软雅黑", Font.BOLD, 25));
                JTextField textFieldFqqName = new JTextField(10);
                textFieldFqqName.setFont(new Font("微软雅黑", Font.BOLD, 20));
                JComboBox comboBoxFriGroup = new JComboBox();
                comboBoxFriGroup.setBackground(Color.WHITE);
                comboBoxFriGroup.setOpaque(false);
                comboBoxFriGroup.setFont(new Font("微软雅黑", Font.BOLD, 20));

                for (int i = 0; i < SubgroupManagerUtils.getGroupNum(); i++) {
                    comboBoxFriGroup.addItem(SubgroupManagerUtils.getGroupName(i));
                }

                JPanel panelOK = new JPanel();
                panelOK.setOpaque(false);
                panelOK.setLayout(new GridLayout(5, 2));
                panelOK.add(labelFridInfo);
                panelOK.add(textFieldFridInfo);
                panelOK.add(labelFridAdd);
                panelOK.add(textFieldFridAdd);
                panelOK.add(labelFridGName);
                panelOK.add(textFieldFqqName);
                panelOK.add(labelFridGroup);
                panelOK.add(comboBoxFriGroup);
                panelOK.add(buttonResearch);

                textFieldFridInfo.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (textFieldName.getText().length() > 9) {
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
                JButton buttonAdd = new JButton("确认添加");
                buttonAdd.setFont(new Font("微软雅黑", Font.BOLD, 25));
                panelOK.add(buttonAdd);
                panelAddFrid.add(panelOK);
                panelOK.setBounds(20, 100, 350, 150);
                buttonResearch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        friendList = robot.queryUser_nickname(textFieldFridInfo.getText());
                        try {
                            friendList.add(robot.queryUser_qq(Integer.valueOf(textFieldFridInfo.getText())));
                        } catch (Exception e1) {
                            System.out.println("非纯数字");
                        }
                        int friNum = friendList.size();
                        friInfo = new Object[friNum][7];
                        for (int i = 0; i < friNum; i++) {
                            friInfo[i][0] = friendList.get(i).getfqq();
                            friInfo[i][1] = friendList.get(i).getnickname();
                            friInfo[i][2] = friendList.get(i).getsign();
                            friInfo[i][3] = Enum_sex.getName(friendList.get(i).getsex());
                            friInfo[i][4] = String.valueOf(Integer.valueOf(tools.get_nowtime(false).substring(0, 4)) - Integer.valueOf(friendList.get(i).getbirthday().substring(0, 4)));
                            friInfo[i][5] = friendList.get(i).getbirthday();
                            friInfo[i][6] = Enum_constellation.getName(friendList.get(i).getconstellation());
                        }
                        modelFri = new DefaultTableModel(friInfo, nameFriTable);
                        tableInfoFri.setModel(modelFri);

                    }
                });

                buttonAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            SubgroupManagerUtils.AddAFriend(Integer.valueOf(textFieldFridAdd.getText()), textFieldFqqName.getText(), comboBoxFriGroup.getSelectedIndex());
                            boolean ok = robot.updateSubgroup(qq);
                            if (ok) {
                                JOptionPane.showMessageDialog(null,
                                        new JLabel("<html><h2><font color='blue'><font size=\"25\"> 添加成功</font></h2></html>"),
                                        "消息",
                                        JOptionPane.INFORMATION_MESSAGE);
                                MyTreeNode newNode =  (MyTreeNode) root.getChildAt(comboBoxFriGroup.getSelectedIndex());
                                MyTreeNode nodeChild = new MyTreeNode(textFieldFqqName.getText()+" （"+ textFieldFridAdd.getText()+")"
                                        ,false);
                                nodeChild.setIndex(Integer.valueOf(textFieldFridAdd.getText()));
                                newNode.add(nodeChild);
                                DefaultTreeModel jModeNew = new DefaultTreeModel(root,true);
                                tree.setModel(jModeNew);

                            } else {
                                JOptionPane.showMessageDialog(null,
                                        new JLabel("<html><h2><font color='blue'><font size=\"25\"> 信息填写有误 请检查</font></h2></html>"),
                                        "消息",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null,
                                    new JLabel("<html><h2><font color='blue'><font size=\"25\"> 信息填写有误 请检查</font></h2></html>"),
                                    "消息",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });

            }
        };
        buttonAddFriend.addActionListener(listenerAddFrid);
        //添加群
        ActionListener listenerAddGroup = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameAddGroup = new JFrame();
                frameAddGroup.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
                frameAddGroup.setTitle("添加群");
                frameAddGroup.setBounds(500, 300, 950, 400);
                frameAddGroup.setVisible(true);
                JPanel panelAddGroup = new JPanel();
                panelAddGroup.setLayout(null);
                panelAddGroup.setBackground(Color.WHITE);
                frameAddGroup.add(panelAddGroup);


                modelGroup = new DefaultTableModel(new Object[0][3], nameGroupTable);
                tableInfoGroup.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        super.componentResized(e);
                        System.out.println(tableInfoGroup.getSelectedColumn());
                    }
                });
                tableInfoGroup.getTableHeader().setPreferredSize(new Dimension(50, 20));
                tableInfoGroup.getTableHeader().setReorderingAllowed(false);
                tableInfoGroup.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tableInfoGroup.getTableHeader().setResizingAllowed(true);
                tableInfoGroup.setModel(modelGroup);
                tableInfoGroup.setRowHeight(20);
                tableInfoGroup.setSelectionForeground(Color.GREEN);
                tableInfoGroup.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                JScrollPane scrollPaneTableGroup = new JScrollPane(tableInfoGroup);
                scrollPaneTableGroup.setOpaque(false);
                JPanel panelTable = new JPanel();
                panelTable.setBackground(Color.WHITE);
                panelTable.setBounds(400, 0, 500, 300);
                panelTable.setLayout(new BorderLayout());
                panelTable.add(scrollPaneTableGroup);
                panelAddGroup.add(panelTable);
                JLabel labelGroupInfo = new JLabel("HIM 群号/群名:");
                labelGroupInfo.setBounds(100, 10, 200, 50);
                labelGroupInfo.setFont(new Font("微软雅黑", Font.BOLD, 25));
                JTextField textFieldGroupInfo = new JTextField(10);
                textFieldGroupInfo.setBounds(250, 10, 200, 50);
                textFieldGroupInfo.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (textFieldGroupInfo.getText().length() > 9) {
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

                textFieldGroupInfo.setFont(new Font("微软雅黑", Font.BOLD, 20));
                JButton buttonResearch = new JButton("查询");
                buttonResearch.setBounds(450, 10, 100, 50);
                buttonResearch.setFont(new Font("微软雅黑", Font.BOLD, 25));

                JLabel labelGroupAdd = new JLabel("确认 HIM 群号");
                labelGroupAdd.setFont(new Font("微软雅黑", Font.BOLD, 25));
                JTextField textFieldGroupAdd = new JTextField(10);
                textFieldGroupAdd.setFont(new Font("微软雅黑", Font.BOLD, 20));


                JPanel panelOK = new JPanel();
                panelOK.setOpaque(false);
                panelOK.setLayout(new GridLayout(5, 2));
                panelOK.add(labelGroupInfo);
                panelOK.add(textFieldGroupInfo);
                panelOK.add(labelGroupAdd);
                panelOK.add(textFieldGroupAdd);
                panelOK.add(buttonResearch);
                JButton buttonAdd = new JButton("确认添加");
                buttonAdd.setFont(new Font("微软雅黑", Font.BOLD, 25));
                panelOK.add(buttonAdd);
                panelAddGroup.add(panelOK);
                panelOK.setBounds(20, 100, 350, 150);
                buttonResearch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        GroupList = robot.queryQunInfo_qunname(textFieldGroupInfo.getText());
                        try {
                            GroupList.add(robot.queryQunInfo_qunnum(Integer.valueOf(textFieldGroupInfo.getText())));
                        } catch (Exception e1) {
                            System.out.println("非纯数字");
                        }
                        int GroupNum = GroupList.size();
                        GroupInfo = new Object[GroupNum][3];
                        for (int i = 0; i < GroupNum; i++) {
                            GroupInfo[i][0] = GroupList.get(i).getQunNum();
                            GroupInfo[i][1] = GroupList.get(i).getQunName();
                            GroupInfo[i][2] = GroupList.get(i).getCreateTime();
                        }
                        modelGroup = new DefaultTableModel(GroupInfo, nameGroupTable);
                        tableInfoGroup.setModel(modelGroup);

                    }
                });

                buttonAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {

                            boolean ok = robot.addQun(qq, Integer.valueOf(textFieldGroupAdd.getText()));
                            if (ok) {
                                JOptionPane.showMessageDialog(null,
                                        new JLabel("<html><h2><font color='blue'><font size=\"25\"> 添加成功</font></h2></html>"),
                                        "消息",
                                        JOptionPane.INFORMATION_MESSAGE);
                                MyTreeNode newNode = new MyTreeNode(robot.queryQunInfo_qunnum(Integer.valueOf(textFieldGroupAdd.getText())).getQunName() + " （" + textFieldGroupAdd.getText() + ")",false);
                                newNode.setIndex(Integer.valueOf(textFieldGroupAdd.getText()));
                                MyTreeNode nodeRoot = (MyTreeNode) root2.getChildAt(0);
                                nodeRoot.add(newNode);
                                DefaultTreeModel jModeNew = new DefaultTreeModel(root2,true);
                                tree2.setModel(jModeNew);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        new JLabel("<html><h2><font color='blue'><font size=\"25\"> 信息填写有误 请检查</font></h2></html>"),
                                        "消息",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null,
                                    new JLabel("<html><h2><font color='blue'><font size=\"25\"> 信息填写有误 请检查</font></h2></html>"),
                                    "消息",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });

            }
        };
        buttonAddGroup.addActionListener(listenerAddGroup);
        //创建群
        ActionListener listenerCreateGroup = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameCreateGroup = new JFrame("创建群");
                frameCreateGroup.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
                JPanel panelCreateGroup = new JPanel();
                panelCreateGroup.setLayout(new FlowLayout(FlowLayout.CENTER));
                panelCreateGroup.setBackground(Color.white);
                frameCreateGroup.add(panelCreateGroup);
                frameCreateGroup.setBounds(600, 400, 470, 100);
                frameCreateGroup.setResizable(false);
                JLabel labelGroup = new JLabel("创建群名");
                labelGroup.setFont(new Font("微软雅黑", Font.BOLD, 25));
                JTextField textFieldGroupName = new JTextField(10);
                textFieldGroupName.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (textFieldGroupName.getText().length() > 9) {
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
                textFieldGroupName.setFont(new Font("微软雅黑", Font.BOLD, 25));
                JButton buttonCreate = new JButton("创建");
                buttonCreate.setFont(new Font("微软雅黑", Font.BOLD, 25));
                panelCreateGroup.add(labelGroup);
                panelCreateGroup.add(textFieldGroupName);
                panelCreateGroup.add(buttonCreate);
                frameCreateGroup.setVisible(true);
                buttonCreate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int qunnum = robot.createQun(qq, textFieldGroupName.getText());
                        frameCreateGroup.dispose();
                        try {
                            JOptionPane.showMessageDialog(null,
                                    new JLabel("<html><h2><font color='blue'><font size=\"25\"> 创建成功 您的HIM群号为:" + qunnum + "</font></h2></html>"),
                                    "消息",
                                    JOptionPane.INFORMATION_MESSAGE);
                            MyTreeNode newNode = new MyTreeNode(textFieldGroupName.getText() + " （" + qunnum + ")",false);
                            newNode.setIndex(qunnum);
                            MyTreeNode nodeRoot = (MyTreeNode) root2.getChildAt(0);
                            nodeRoot.add(newNode);
                            DefaultTreeModel jModeNew = new DefaultTreeModel(root2,true);
                            tree2.setModel(jModeNew);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                });

            }
        };
        buttonCreateGroup.addActionListener(listenerCreateGroup);
    }
    private void buttonInfoClicked(){
        new UserInfoFrame(miniQQ, qq, userinfo, user_photo,this,robot);
    }
}
