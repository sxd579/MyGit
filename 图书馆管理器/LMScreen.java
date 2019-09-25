import javafx.scene.control.TabPane;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class LMScreen {
    Server_API robot = new Server_API();

    String [] nameBooksTable_Client = {"书号","书名","著作者","现存量"};
    DefaultTableModel modelBooks_Client = null;
    JTable tableInfoBook_Client = new JTable(modelBooks_Client);
    Node_Bookinfo []node_bookinfos_Client = null;
    Object [][] BooksInfo_Client;
    Object [][] BooksInfoSome_Client;

    String [] Log_Client =  {"书号","书名","著作者","借阅时间","归还时间"};
    DefaultTableModel modelLog_Client = null;
    JTable TableLog_Client = new JTable(modelLog_Client);
    Object [][] LogInfo_Client;

    String  [] nameBooksTable_Server = {"书号","书名","著作者","现存量","库存量"};
    DefaultTableModel modelBooks_Server = null;
    JTable tableInfoBook_Server = new JTable(modelBooks_Server);
    Node_Bookinfo []node_bookinfos_Server = null;
    Object [][] BooksInfo_Server;
    Object [][] BooksInfoSome_Server;

    String [] Log_Server =  {"用户","书号","书名","著作者","操作"};
    DefaultTableModel modelLog_Server = null;
    JTable TableLog_Server = new JTable(modelLog_Server);
    Object [][] LogInfo_Server;




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

    //管理员界面
    public void LM_Master_Screen(LMScreen robot_UI,String user){
            JFrame frameMaster = new JFrame("管理员主界面");
            frameMaster.setBounds(100,100,1500,600);
            frameMaster.setResizable(false);
            frameMaster.setIconImage(Toolkit.getDefaultToolkit().getImage(Config.logoPath));

            JPanel panelMaster = new JPanel();
            panelMaster.setLayout(null);
            panelMaster.setBackground(Color.WHITE);

            JLabel labelMaster = new JLabel("欢迎你，管理员!");
            labelMaster.setFont(new Font("微软雅黑", Font.BOLD, 25));
            labelMaster.setBounds(30,30,300,60);


            //信息
            JPanel panelFunction = new JPanel();
            panelFunction.setOpaque(false);
            panelFunction.setLayout(new GridLayout(7,1));
            panelFunction.setBounds(50,150,400,300);

            JPanel panelID = new JPanel();
            panelID.setOpaque(false);
            panelID.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel labelID = new JLabel("书号");
            labelID.setFont(new Font("微软雅黑", Font.BOLD, 25));
            JTextField textFieldID = new JTextField(10);
            textFieldID.setFont(new Font("微软雅黑", Font.BOLD, 25));
            textFieldID.addKeyListener(listenerOnlyNum);
            textFieldID.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (textFieldID.getText().length()>3){
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



            JPanel panelName = new JPanel();
            panelName.setOpaque(false);
            panelName.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel labelName = new JLabel("书名");
            labelName.setFont(new Font("微软雅黑", Font.BOLD, 25));
            JTextField textFieldName = new JTextField(10);
            textFieldName.setFont(new Font("微软雅黑", Font.BOLD, 25));


            JPanel panelAuthor = new JPanel();
            panelAuthor.setOpaque(false);
            panelAuthor.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel labelAuthor = new JLabel("作者");
            labelAuthor.setFont(new Font("微软雅黑", Font.BOLD, 25));
            JTextField textFieldAuthor = new JTextField(10);
            textFieldAuthor.setFont(new Font("微软雅黑", Font.BOLD, 25));

            JPanel panelCount = new JPanel();
            panelCount.setOpaque(false);
            panelCount.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel labelCount = new JLabel("数量");
            labelCount.setFont(new Font("微软雅黑", Font.BOLD, 25));
            JTextField textFieldCount = new JTextField(10);
            textFieldCount.addKeyListener(listenerOnlyNum);
            textFieldCount.setFont(new Font("微软雅黑", Font.BOLD, 25));
            textFieldCount.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (textFieldCount.getText().length()>3){
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

            JButton buttonStorage = new JButton("批量入库（已有书籍不需书名作者）");
            buttonStorage.setFont(new Font("微软雅黑", Font.BOLD, 20));
            buttonStorage.setBackground(new Color(0,0,0));
            buttonStorage.setOpaque(false);
            buttonStorage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        Tree_Bookinfo tree_bookinfo = robot.initialize_BooksTree();
                        Node_Bookinfo node_bookinfo = tree_bookinfo.search(Integer.parseInt(textFieldID.getText()), tree_bookinfo.getRoot());
                        if (node_bookinfo != null && !textFieldID.getText().equals("")) {
                            node_bookinfo.setBooksNum(node_bookinfo.getBooksNum() + Integer.parseInt((textFieldCount.getText())));
                            node_bookinfo.setAllBooksNum(node_bookinfo.getAllBooksNum() + Integer.parseInt((textFieldCount.getText())));
                            robot.changeBookInfo(node_bookinfo.getRow() , node_bookinfo.output(), tree_bookinfo.getSize());
                            JOptionPane.showMessageDialog(null,
                                    new JLabel("<html><h2><font color='blue'><font size=\"25\"> 进书成功</font></h2></html>"),
                                    "消息",
                                    JOptionPane.INFORMATION_MESSAGE);

                        } else if (node_bookinfo == null && !textFieldID.getText().equals("")) {
                            System.out.println(6666);
                            Node_Bookinfo node_bookinfo1 = new Node_Bookinfo(textFieldID.getText(), textFieldName.getText(), textFieldAuthor.getText(), textFieldCount.getText(), textFieldCount.getText(), tree_bookinfo.getSize());
                            tree_bookinfo.addNode(node_bookinfo1, tree_bookinfo.getRoot());
                            System.out.println(node_bookinfo1.output());
                            robot.changeBookInfo(node_bookinfo1.getRow() + 1, node_bookinfo1.output(), tree_bookinfo.getSize());
                            JOptionPane.showMessageDialog(null,
                                    new JLabel("<html><h2><font color='blue'><font size=\"25\"> 进书成功</font></h2></html>"),
                                    "消息",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
//                            System.out.println(10086);
                            JOptionPane.showMessageDialog(null,
                                    new JLabel("<html><h2><font color='blue'><font size=\"25\">  输入有误，请重试</font></h2></html>"),
                                    "消息",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }catch (Exception e1){
//                        System.out.println(1008611);
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null,
                                new JLabel("<html><h2><font color='blue'><font size=\"25\"> 输入有误，请重试</font></h2></html>"),
                                "消息",
                                JOptionPane.INFORMATION_MESSAGE);
                    }


                }
            });

            JButton buttonAllInfo = new JButton("查询借阅记录");
            buttonAllInfo.setFont(new Font("微软雅黑", Font.BOLD, 20));
            buttonAllInfo.setBackground(new Color(0,0,0));
            buttonAllInfo.setOpaque(false);
            buttonAllInfo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<String> logs = new ArrayList <String>();
                    if ((logs=robot.get_Log_Server())!=null){
                           LogInfo_Server = new Object[logs.size()][5];
                           for (int i =0;i<logs.size();i++){
                               String []content = logs.get(i).split("-");
                               LogInfo_Server[i][0] = content[0];
                               LogInfo_Server[i][1] = content[1];
                               LogInfo_Server[i][2] = content[2];
                               LogInfo_Server[i][3] = content[3];
                               LogInfo_Server[i][4] = content[4];
                           }
                           modelLog_Server = new DefaultTableModel(LogInfo_Server,Log_Server);
                           TableLog_Server.setModel(modelLog_Server);
                    }
                }
            });

            JButton buttonSearchAll = new JButton("查询所有书籍");
            buttonSearchAll.setFont(new Font("微软雅黑", Font.BOLD, 20));
            buttonSearchAll.setBackground(new Color(0,0,0));
            buttonSearchAll.setOpaque(false);

            buttonSearchAll.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Tree_Bookinfo tree_bookinfo = robot.initialize_BooksTree();
                    node_bookinfos_Server = tree_bookinfo.initializeTable();
                    BooksInfo_Server = new Object[tree_bookinfo.getSize()][5];
                   for (int i =0;i<tree_bookinfo.getSize();i++) {
                       BooksInfo_Server[i][0] = node_bookinfos_Server[i].getId();
                       BooksInfo_Server[i][1] = node_bookinfos_Server[i].getName();
                       BooksInfo_Server[i][2] = node_bookinfos_Server[i].getAuthor();
                       BooksInfo_Server[i][3] = node_bookinfos_Server[i].getBooksNum();
                       BooksInfo_Server[i][4] = node_bookinfos_Server[i].getAllBooksNum();
                   }
                    modelBooks_Server = new DefaultTableModel(BooksInfo_Server, nameBooksTable_Server);
                    tableInfoBook_Server.setModel(modelBooks_Server);

                }
            });




        ////书库
        modelBooks_Server = new DefaultTableModel(new Object[0][5], nameBooksTable_Server);
        tableInfoBook_Server.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                System.out.println(tableInfoBook_Server.getSelectedColumn());
            }
        });
        tableInfoBook_Server.setBackground(Color.WHITE);
        tableInfoBook_Server.setOpaque(false);
        tableInfoBook_Server.getTableHeader().setPreferredSize(new Dimension(50, 20));
        tableInfoBook_Server.getTableHeader().setReorderingAllowed(false);
        tableInfoBook_Server.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableInfoBook_Server.getTableHeader().setResizingAllowed(true);
        tableInfoBook_Server.setModel(modelBooks_Server);
        tableInfoBook_Server.setRowHeight(20);
        tableInfoBook_Server.setSelectionForeground(Color.GREEN);
        tableInfoBook_Server.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        JScrollPane scrollPaneTable = new JScrollPane(tableInfoBook_Server);
        scrollPaneTable.setOpaque(false);
        JPanel panelTable =new JPanel();
        panelTable.setBackground(Color.WHITE);
        panelTable.setBounds(500,100,500,600);




        /////日志
        modelLog_Server = new DefaultTableModel(new Object[0][5], Log_Server);
        TableLog_Server.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                System.out.println(TableLog_Server.getSelectedColumn());
            }
        });
        TableLog_Server.setBackground(Color.WHITE);
        TableLog_Server.setOpaque(false);
        TableLog_Server.getTableHeader().setPreferredSize(new Dimension(50, 20));
        TableLog_Server.getTableHeader().setReorderingAllowed(false);
        TableLog_Server.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableLog_Server.getTableHeader().setResizingAllowed(true);
        TableLog_Server.setModel(modelLog_Server);
        TableLog_Server.setRowHeight(20);
        TableLog_Server.setSelectionForeground(Color.GREEN);
        TableLog_Server.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        JScrollPane scrollPaneTablelog = new JScrollPane(TableLog_Server);
        scrollPaneTablelog.setOpaque(false);
        JPanel panelTablelog =new JPanel();
        panelTablelog.setBackground(Color.WHITE);
        panelTablelog.setBounds(1000,100,500,600);

            panelID.add(labelID);
            panelID.add(textFieldID);
            panelName.add(labelName);
            panelName.add(textFieldName);
            panelAuthor.add(labelAuthor);
            panelAuthor.add(textFieldAuthor);
            panelCount.add(labelCount);
            panelCount.add(textFieldCount);
            panelFunction.add(panelID);
            panelFunction.add(panelName);
            panelFunction.add(panelAuthor);
            panelFunction.add(panelCount);
            panelFunction.add(buttonStorage);
            panelFunction.add(buttonAllInfo);
            panelFunction.add(buttonSearchAll);
            panelTable.add(scrollPaneTable);
            panelTablelog.add(scrollPaneTablelog);

            panelMaster.add(panelTable);
            panelMaster.add(panelTablelog);
            panelMaster.add(panelFunction);
            panelMaster.add(labelMaster);
            frameMaster.add(panelMaster);
            frameMaster.setVisible(true);

    }

    //用户界面
    public void LM_User_Screen(LMScreen robot_UI,String user){
          JFrame  frameUser = new JFrame("用户主界面");
          frameUser.setBounds(200,100,1500,600);
          frameUser.setResizable(false);
          frameUser.setIconImage(Toolkit.getDefaultToolkit().getImage(Config.logoPath));

          //用户信息头
          JPanel panelUserInfo = new JPanel();
          panelUserInfo.setOpaque(false);
          panelUserInfo.setBounds(30,30,300,100);
          panelUserInfo.setLayout(new FlowLayout(FlowLayout.LEFT));
          JLabel labelName = new JLabel("用户:"+robot.query_user_name(user)+"    ");
          labelName.setFont(new Font("微软雅黑", Font.BOLD, 25));
          JLabel labelCardNum = new JLabel("校园卡号:"+robot.query_user_cardNumber(user));
          labelCardNum.setFont(new Font("微软雅黑", Font.BOLD, 25));


          //功能列表
          JPanel panelFunction = new JPanel();
          panelFunction.setLayout(new GridLayout(3,1));
          panelFunction.setOpaque(false);
          panelFunction.setBounds(0,200,400,150);


          JPanel panelSearch = new JPanel();
          panelSearch.setOpaque(false);
          panelSearch.setLayout(new FlowLayout(FlowLayout.LEFT));
          JTextField textFieldBookName = new JTextField(9);

          textFieldBookName.setFont(new Font("微软雅黑", Font.BOLD, 20));
          JButton buttonSearch = new JButton("查找");

          ////查找书
          buttonSearch.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  boolean empty = true;
                  Tree_Bookinfo tree_bookinfo = robot.initialize_BooksTree();
                  node_bookinfos_Client = tree_bookinfo.initializeTable();
                  BooksInfo_Client = new Object[tree_bookinfo.getSize()][4];
                  if (textFieldBookName.getText().equals("")){
//                  System.out.println(node_bookinfos.length);
//                  System.out.println(tree_bookinfo.getSize());
//                  System.out.println(node_bookinfos[0].getId());
                  for (int i = 0;i<tree_bookinfo.getSize();i++){
//                      System.out.println(node_bookinfos[i]==null);
//                      System.out.println(String.valueOf(node_bookinfos[i].getId()));
                      BooksInfo_Client[i][0] =node_bookinfos_Client[i].getId();
                      BooksInfo_Client[i][1] = node_bookinfos_Client[i].getName();
                      BooksInfo_Client[i][2] = node_bookinfos_Client[i].getAuthor();
                      BooksInfo_Client[i][3] = node_bookinfos_Client[i].getBooksNum();
                      empty = false;
                  }
                      modelBooks_Client = new DefaultTableModel(BooksInfo_Client, nameBooksTable_Client);
              }
              else {
                      int size = 0;
                      for (int i = 0;i<tree_bookinfo.getSize();i++){

                          if (node_bookinfos_Client[i].getName().toUpperCase().contains(textFieldBookName.getText().toUpperCase())) {
                              BooksInfo_Client[size][0] = node_bookinfos_Client[i].getId();
                              BooksInfo_Client[size][1] = node_bookinfos_Client[i].getName();
                              BooksInfo_Client[size][2] = node_bookinfos_Client[i].getAuthor();
                              BooksInfo_Client[size][3] = node_bookinfos_Client[i].getBooksNum();
                              empty = false;
                              size++;
                          }
                      }
                      BooksInfoSome_Client = new Object[size][4];
                      for (int i= 0;i<size;i++){
                          BooksInfoSome_Client[i] = BooksInfo_Client[i];
                      }
                      modelBooks_Client = new DefaultTableModel(BooksInfoSome_Client, nameBooksTable_Client);
                  }
                  if (empty
                          ){
                      BooksInfo_Client = new Object[0][4];
                      modelBooks_Client = new DefaultTableModel(BooksInfo_Client, nameBooksTable_Client);
                  }
                  tableInfoBook_Client.setModel(modelBooks_Client);
              }
          });
          buttonSearch.setBackground(new Color(0,0,0));
          buttonSearch.setOpaque(false);
          buttonSearch.setFont(new Font("微软雅黑", Font.BOLD, 20));
          textFieldBookName .addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textFieldBookName.getText().length()>9){
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
          JPanel panelBR = new JPanel();
          panelBR.setOpaque(false);
          panelBR.setLayout(new FlowLayout(FlowLayout.LEFT));
          JTextField textFieldBookNumber = new JTextField(9);
          textFieldBookNumber.setFont(new Font("微软雅黑", Font.BOLD, 20));
          textFieldBookNumber.addKeyListener(listenerOnlyNum);
          textFieldBookNumber.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textFieldBookNumber.getText().length()>11){
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
          JButton buttonBorrow = new JButton("借书");

          ///////借书
          buttonBorrow.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  //借书
                  try {
                      Tree_Bookinfo tree_bookinfo = robot.initialize_BooksTree();
                      Node_Bookinfo node_bookinfo = tree_bookinfo.search(Integer.parseInt(textFieldBookNumber.getText()), tree_bookinfo.getRoot());
                      if (node_bookinfo != null&&!textFieldBookNumber.getText().equals("")) {
                          if (node_bookinfo.getBooksNum() - 1 >= 0) {
                              node_bookinfo.setBooksNum(node_bookinfo.getBooksNum() - 1);

                             if (robot.borrowBook(user,node_bookinfo)){
//                                 修改该书籍的数据
                                 robot.changeBookInfo(node_bookinfo.getRow(), node_bookinfo.output(),tree_bookinfo.getSize());
                                 JOptionPane.showMessageDialog(null,
                                         new JLabel("<html><h2><font color='blue'><font size=\"25\"> 借书成功</font></h2></html>"),
                                         "消息",
                                         JOptionPane.INFORMATION_MESSAGE);
                             }
                             else{
                                 JOptionPane.showMessageDialog(null,
                                         new JLabel("<html><h2><font color='blue'><font size=\"25\"> 借书失败</font></h2></html>"),
                                         "消息",
                                         JOptionPane.INFORMATION_MESSAGE);
                             }

                          } else {
                              JOptionPane.showMessageDialog(null,
                                      new JLabel("<html><h2><font color='blue'><font size=\"25\"> 借书失败,现存量不足</font></h2></html>"),
                                      "消息",
                                      JOptionPane.INFORMATION_MESSAGE);
                          }
                      } else {
                          JOptionPane.showMessageDialog(null,
                                  new JLabel("<html><h2><font color='blue'><font size=\"25\"> 借书失败，请确认书号</font></h2></html>"),
                                  "消息",
                                  JOptionPane.INFORMATION_MESSAGE);
                      }
                  }catch (Exception e1){
                      e1.printStackTrace();
                      JOptionPane.showMessageDialog(null,
                              new JLabel("<html><h2><font color='blue'><font size=\"25\"> 借书失败，请确认书号</font></h2></html>"),
                              "消息",
                              JOptionPane.INFORMATION_MESSAGE);
                  }
              }
          });
          buttonBorrow.setBackground(new Color(0,0,0));
          buttonBorrow.setOpaque(false);
          buttonBorrow.setFont(new Font("微软雅黑", Font.BOLD, 20));
          JButton buttonReturn = new JButton("还书");
          buttonReturn.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  //还书
                  try {
                      Tree_Bookinfo tree_bookinfo = robot.initialize_BooksTree();
                      Node_Bookinfo node_bookinfo = tree_bookinfo.search(Integer.parseInt(textFieldBookNumber.getText()), tree_bookinfo.getRoot());
                      if (node_bookinfo != null&&!textFieldBookNumber.getText().equals("")) {

                              if (robot.returnBook(user,node_bookinfo)){
//                                 修改该书籍的数据
                                  node_bookinfo.setBooksNum(node_bookinfo.getBooksNum() + 1);
                                  robot.changeBookInfo(node_bookinfo.getRow(), node_bookinfo.output(),tree_bookinfo.getSize());
                                  JOptionPane.showMessageDialog(null,
                                          new JLabel("<html><h2><font color='blue'><font size=\"25\"> 还书成功</font></h2></html>"),
                                          "消息",
                                          JOptionPane.INFORMATION_MESSAGE);
                              }
                              else{
                                  JOptionPane.showMessageDialog(null,
                                          new JLabel("<html><h2><font color='blue'><font size=\"25\"> 还书失败，请确认书号</font></h2></html>"),
                                          "消息",
                                          JOptionPane.INFORMATION_MESSAGE);
                              }


                      } else {
                          JOptionPane.showMessageDialog(null,
                                  new JLabel("<html><h2><font color='blue'><font size=\"25\"> 还书失败，请确认书号</font></h2></html>"),
                                  "消息",
                                  JOptionPane.INFORMATION_MESSAGE);
                      }
                  }catch (Exception e1){
                      e1.printStackTrace();
                      JOptionPane.showMessageDialog(null,
                              new JLabel("<html><h2><font color='blue'><font size=\"25\"> 还书失败，请确认书号</font></h2></html>"),
                              "消息",
                              JOptionPane.INFORMATION_MESSAGE);
                  }

              }
          });
          buttonReturn.setBackground(new Color(0,0,0));
          buttonReturn.setOpaque(false);
          buttonReturn.setFont(new Font("微软雅黑", Font.BOLD, 20));


          JButton buttonLog = new JButton("借阅记录");
          buttonLog.setFont(new Font("微软雅黑", Font.BOLD,20));
          buttonLog.setBackground(new Color(0,0,0));
          buttonLog.setOpaque(false);
          buttonLog.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  ArrayList <String> logs = new ArrayList <String>();
                  if ((logs = robot.get_Log_Client(user)) != null) {
                      LogInfo_Client = new Object[logs.size()][5];
                      for (int i = 0; i < logs.size(); i++) {
                          String[] content = logs.get(i).split("-");
                          LogInfo_Client[i][0] = content[0];
                          LogInfo_Client[i][1] = content[1];
                          LogInfo_Client[i][2] = content[2];
                          LogInfo_Client[i][3] = content[3];
                          LogInfo_Client[i][4] = content[4];
                      }
                      modelLog_Client = new DefaultTableModel(LogInfo_Client, Log_Client);
                      TableLog_Client.setModel(modelLog_Client);
                  }
              }
          });

//////查书
        modelBooks_Client = new DefaultTableModel(new Object[0][4], nameBooksTable_Client);
        tableInfoBook_Client.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                System.out.println(tableInfoBook_Client.getSelectedColumn());
            }
        });
        tableInfoBook_Client.setBackground(Color.WHITE);
        tableInfoBook_Client.setOpaque(false);
        tableInfoBook_Client.getTableHeader().setPreferredSize(new Dimension(50, 20));
        tableInfoBook_Client.getTableHeader().setReorderingAllowed(false);
        tableInfoBook_Client.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableInfoBook_Client.getTableHeader().setResizingAllowed(true);
        tableInfoBook_Client.setModel(modelBooks_Client);
        tableInfoBook_Client.setRowHeight(20);
        tableInfoBook_Client.setSelectionForeground(Color.GREEN);
        tableInfoBook_Client.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        JScrollPane scrollPaneTable = new JScrollPane(tableInfoBook_Client);
        scrollPaneTable.setOpaque(false);
        JPanel panelTable =new JPanel();
        panelTable.setBackground(Color.WHITE);
        panelTable.setBounds(400,100,500,600);


        ///日志
        modelLog_Client = new DefaultTableModel(new Object[0][5], Log_Client);
        TableLog_Client.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                System.out.println(TableLog_Client.getSelectedColumn());
            }
        });
        TableLog_Client.setBackground(Color.WHITE);
        TableLog_Client.setOpaque(false);
        TableLog_Client.getTableHeader().setPreferredSize(new Dimension(50, 20));
        TableLog_Client.getTableHeader().setReorderingAllowed(false);
        TableLog_Client.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableLog_Client.getTableHeader().setResizingAllowed(true);
        TableLog_Client.setModel(modelLog_Client);
        TableLog_Client.setRowHeight(20);
        TableLog_Client.setSelectionForeground(Color.GREEN);
        TableLog_Client.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        JScrollPane scrollPaneTablelog = new JScrollPane(TableLog_Client);
        scrollPaneTablelog.setOpaque(false);
        JPanel panelTablelog =new JPanel();
        panelTablelog.setBackground(Color.WHITE);
        panelTablelog.setBounds(1000,100,500,600);




        JPanel panelUser = new JPanel();
          panelUser.setBackground(Color.WHITE);
          panelUser.setLayout(null);


          panelSearch.add(textFieldBookName);
          panelSearch.add(buttonSearch);
          panelBR.add(textFieldBookNumber);
          panelBR.add(buttonBorrow);
          panelBR.add(buttonReturn);
          panelFunction.add(panelSearch);
          panelFunction.add(panelBR);
          panelFunction.add(buttonLog);
          panelTable.add(scrollPaneTable);
          panelTablelog.add(scrollPaneTablelog);

          panelUserInfo.add(labelName);
          panelUserInfo.add(labelCardNum);

          panelUser.add(panelTable);
          panelUser.add(panelTablelog);
          panelUser.add(panelFunction);
          panelUser.add(panelUserInfo);
          frameUser.add(panelUser);
          frameUser.setVisible(true);
    }

    //注册界面
    public  void  LM_Register(LMScreen robot_UI){
             JFrame frameRegister = new JFrame("注册");
             frameRegister.setBounds(600,200,700,600);
             frameRegister.setIconImage(Toolkit.getDefaultToolkit().getImage(Config.logoPath));
             frameRegister.setResizable(false);
             JPanel panelRegister = new JPanel();
             panelRegister.setBackground(Color.WHITE);
             panelRegister.setLayout(null);
             frameRegister.add(panelRegister);

             //标题
             JPanel panelTitle = new JPanel();
             JLabel labelTitle = new JLabel("欢迎注册,朋友！");
             labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 35));
             panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
             panelTitle.add(labelTitle);
             panelTitle.setOpaque(false);
             panelTitle.setBounds(100,50,300,100);

             //信息输入框
             JPanel panelInfo = new JPanel();
             panelInfo.setOpaque(false);
             panelInfo.setLayout(new GridLayout(4,1));
             panelInfo.setBounds(250,150,400,300);

             JPanel panelName = new JPanel();
             panelName.setOpaque(false);
             panelName.setLayout(new FlowLayout(FlowLayout.LEFT));
             JLabel labelName = new JLabel("姓名：");
             labelName.setFont(new Font("微软雅黑", Font.BOLD, 20));
             JTextField textFieldName =  new JTextField(10);
             textFieldName.setFont(new Font("微软雅黑", Font.BOLD, 20));

             JPanel panelCardNumber = new JPanel();
             panelCardNumber.setLayout(new FlowLayout(FlowLayout.LEFT));
             panelCardNumber.setOpaque(false);
             JLabel labelCardNumber = new JLabel("学号：");
             labelCardNumber.setFont(new Font("微软雅黑", Font.BOLD, 20));
             JTextField textFieldCardNumber =  new JTextField(10);
             textFieldCardNumber.setFont(new Font("微软雅黑", Font.BOLD, 20));

            JPanel panelPsw = new JPanel();
            panelPsw.setOpaque(false);
            panelPsw.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel labelPsw = new JLabel("密码：");
            labelPsw.setFont(new Font("微软雅黑", Font.BOLD, 20));
            JPasswordField textFieldPsw =  new JPasswordField(10);
            textFieldPsw.setEchoChar('*');
            textFieldPsw.setFont(new Font("微软雅黑", Font.BOLD, 20));

            JPanel panelBt = new JPanel();
            panelBt.setOpaque(false);
            panelBt.setLayout(new FlowLayout(FlowLayout.LEFT));
            JButton buttonRegister =new JButton("立即注册");
            buttonRegister.setFont(new Font("微软雅黑", Font.BOLD, 20));
            buttonRegister.setBackground(new Color(0,0,0));
            buttonRegister.setOpaque(false);
            JButton buttonOK = new JButton("完成返回");
            buttonOK.setFont(new Font("微软雅黑", Font.BOLD, 20));
            buttonOK.setBackground(new Color(0,0,0));
            buttonOK.setOpaque(false);

             panelName.add(labelName);
             panelName.add(textFieldName);
             panelCardNumber.add(labelCardNumber);
             panelCardNumber.add(textFieldCardNumber);
             panelPsw.add(labelPsw);
             panelPsw.add(textFieldPsw);
             panelBt.add(buttonRegister);
             panelBt.add(buttonOK);
             panelInfo.add(panelName);
             panelInfo.add(panelCardNumber);
             panelInfo.add(panelPsw);
             panelInfo.add(panelBt);

             panelRegister.add(panelTitle);
             panelRegister.add(panelInfo);

             frameRegister.setVisible(true);
             textFieldName.setText("");
             textFieldCardNumber.setText("");
             textFieldPsw.setText("");
             textFieldCardNumber.addKeyListener(listenerOnlyNum);
             textFieldCardNumber.addKeyListener(new KeyListener() {
                 @Override
                 public void keyTyped(KeyEvent e) {
                     if (textFieldCardNumber.getText().length()>11){
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

        textFieldName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textFieldName.getText().length()>8){
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

        textFieldPsw.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (String.valueOf(textFieldPsw.getPassword()).length()>11){
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

             //注册
             buttonRegister.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     if (!textFieldName.getText().equals("")&&!textFieldCardNumber.getText().equals("")&&!String.valueOf(textFieldPsw.getPassword()).equals("")) {
                         String newAccount = robot.Register(textFieldName.getText(), textFieldCardNumber.getText(), String.valueOf(textFieldPsw.getPassword()));
                         textFieldName.setText("");
                         textFieldCardNumber.setText("");
                         textFieldPsw.setText("");
                         JOptionPane.showMessageDialog(null,
                                 new JLabel("<html><h2><font color='blue'><font size=\"25\"> 注册成功 您的借书账号为:"+newAccount+"</font></h2></html>"),
                                 "消息",
                                 JOptionPane.INFORMATION_MESSAGE);
                     }
                     else {
                         textFieldName.setText("");
                         textFieldCardNumber.setText("");
                         textFieldPsw.setText("");
                         JOptionPane.showMessageDialog(null,
                                 new JLabel("<html><h2><font color='blue'><font size=\"25\"> 注册信息不完整，请确认</font></h2></html>"),
                                 "消息",
                                 JOptionPane.INFORMATION_MESSAGE);
                     }
                 }
             });

             //返回
            buttonOK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frameRegister.dispose();
                }
            });

    }
    public void LM_Login(LMScreen robot_UI){
             JFrame framelogin = new JFrame("图书馆管理");
             framelogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Config.logoPath));
             framelogin.setBounds(750, 350, 450, 300);
             framelogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             framelogin.setResizable(false);

             JPanel panelLogin = new JPanel(){
                 public void paintComponent(Graphics g) {
                     super.paintComponent(g);
                     g.drawImage(Config.login.getImage(), 0, 0, null);
                 }
             };
             panelLogin.setLayout(null);
             panelLogin.setBackground(Color.WHITE);
             framelogin.add(panelLogin);
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
                    if (textFieldUser.getText().length()>8){
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
           passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (String.valueOf(passwordField.getPassword()).length()>11){
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
            panelLogin.add(panelPassword);
            panelLogin.add(panelUser);
            panelLogin.add(panelBt);
            panelUser.add(labelUser);
            panelUser.add(textFieldUser);
            panelPassword.add(labelPassword);
            panelPassword.add(passwordField);
            panelBt.add(buttonLogin);
            panelBt.add(buttonRegister);
            textFieldUser.setText("10000");
            passwordField.setText("793579");
            framelogin.setVisible(true);
            buttonLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (robot.Enter(textFieldUser.getText(),String .valueOf(passwordField.getPassword()))==1){
                        JOptionPane.showMessageDialog(null,
                                new JLabel("<html><h2><font color='blue'><font size=\"25\"> 欢迎你，管理员</font></h2></html>"),
                                "消息",
                                JOptionPane.INFORMATION_MESSAGE);
                        robot_UI.LM_Master_Screen(robot_UI,"10000");
                        framelogin.dispose();
                    }
                    else if (robot.Enter(textFieldUser.getText(),String .valueOf(passwordField.getPassword()))== 2 ){
                        JOptionPane.showMessageDialog(null,
                                new JLabel("<html><h2><font color='blue'><font size=\"25\"> 欢迎你，用户"+textFieldUser.getText()+"</font></h2></html>"),
                                "消息",
                                JOptionPane.INFORMATION_MESSAGE);
                        robot_UI.LM_User_Screen(robot_UI,textFieldUser.getText());
                        framelogin.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,
                                new JLabel("<html><h2><font color='blue'><font size=\"25\"> 账号密码错误，请确认</font></h2></html>"),
                                "消息",
                                JOptionPane.INFORMATION_MESSAGE);

                    }


                }
            });
            buttonRegister.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    robot_UI.LM_Register(robot_UI);
                }
            });
    }
    public static void main(String[] args) {
        LMScreen robot_UI = new LMScreen();
        robot_UI.LM_Login(robot_UI);
    }
}
