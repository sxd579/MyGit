import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

public class RadishClient extends JFrame {
    private static int index = 0;
    private ImageIcon img = new ImageIcon("C:\\Users\\zk\\RadishProtect\\保卫萝卜图片1.jpg");
    private ImageIcon img1 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\保卫萝卜图片2.jpg");
    private ImageIcon img2 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\保卫萝卜图片3.jpg");
    private ImageIcon img3 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\Scene1.jpg");
    private ImageIcon img4 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\Scene2.jpg");
    private ImageIcon img5 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\保卫萝卜图片6.jpg");
    private ImageIcon img6 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\保卫萝卜图片7.jpg");
    private ImageIcon img7 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\保卫萝卜图片8.jpg");
    private ImageIcon img8 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\保卫萝卜图片9.jpg");
    private ImageIcon img9 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\保卫萝卜图片10.jpg");
    private ImageIcon img10 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\保卫萝卜图片11.jpg");
    private ImageIcon imgPt1 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台101.png");
    private ImageIcon imgPt2 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台201.png");
    private ImageIcon imgPt3 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台3.png");
    private ImageIcon imgPt4 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台4.png");
    private ImageIcon imgBt1 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹101.png");
    private ImageIcon imgBt2 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹2.png");
    private ImageIcon imgBt3 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹3.png");
    private ImageIcon imgBt4 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹4.png");
    JPanel cardPanel,panelMain,panelSelect,panel1,panel2,panel3,panel4,panel5,panel6,panel7;
    int [][] scene1 = {
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,1},
            {0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,1},
            {0,0,0,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,0,1},
            {0,0,0,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,1,1,0,1,1},
            {0,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1},
            {0,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1},
            {0,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0}
    };
    int [][] scene2 = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,1,1,1,1,1,1,1,0,0,0,0},
            {1,1,1,1,1,1,1,0,0,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0},
            {1,1,1,1,1,1,1,0,0,1,1,1,0,0,0,1,1,1,1,1,1,0,1,1,1,1,1,0,0},
            {1,1,1,1,1,1,1,0,0,1,1,1,0,0,0,1,1,1,1,1,1,0,1,1,1,1,1,0,0},
            {0,0,0,0,1,1,1,0,0,1,1,1,0,0,0,1,1,1,1,1,1,0,1,1,1,1,1,0,0},
            {1,1,1,0,1,1,1,0,0,1,1,1,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0},
            {1,1,1,0,1,1,1,0,0,1,1,1,0,0,0,1,1,1,0,0,1,1,1,0,1,1,1,0,0},
            {1,1,1,0,1,1,1,0,0,1,1,1,0,0,0,1,1,1,0,0,1,1,1,0,1,1,1,0,0},
            {1,1,1,0,1,1,1,0,0,1,1,1,0,0,0,1,1,1,0,0,1,1,1,0,1,1,1,0,0},
            {1,1,1,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,0,0,1,1,1,0,1,1,1,0,0},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,0,1,1,1,0,0},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,0,1,1,1,0,0},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,0,1,1,1,0,0},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,0},
            {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1},
            {1,1,1,0,1,1,0,0,1,1,1,1,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1},
            {1,1,1,0,1,1,0,0,1,1,1,1,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1},
            {1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,1,1}
    };

    CardLayout cardLayout;
    private int num1 = 0;
    static List<Monster1> monster1List = new ArrayList<Monster1>();
    static List<Tower1> tower1s = new ArrayList<Tower1>();
    static List<Tower1> tower1sPre = new ArrayList<Tower1>();
    private static int round = 1;
    Time1Thread t1 = new Time1Thread();
    Thread thread1 = new Thread(t1);
    static int i1 =1;
    static  int round1 = 1;
    Money money = new Money();
    private static Radish radish1;
    private static  Tower1 tower1 = null;
    private  int time  = 1000;

    public static int getRound() {
        return round;
    }

    public static void setRound(int round) {
        RadishClient.round = round;
    }

    public int getI1() {
        return i1;
    }

    public  void setI1(int i1) {
        RadishClient.i1 = i1;
    }

    public  int getRound1() {
        return round1;
    }

    public void setRound1(int round1) {
        RadishClient.round1 = round1;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public  void rePlay(){
        i1=31;
        monster1List.removeAll(monster1List);
        round = 1;
        bullet1s1.removeAll(bullet1s1);
        bullet1s2.removeAll(bullet1s2);
        bullet1s3.removeAll(bullet1s3);
        bullet1s4.removeAll(bullet1s4);
        tower1s.removeAll(tower1s);
        radish1.setLive(false);
        money.setMoney(1000);
    }
    static List<JButton> jButtons1 = new ArrayList <JButton>();
    static List<Bullet1> bullet1s1 = new ArrayList <Bullet1>() ;
    static List<Bullet1> bullet1s2 = new ArrayList <Bullet1>() ;
    static List<Bullet1> bullet1s3 = new ArrayList <Bullet1>() ;
    static List<Bullet1> bullet1s4 = new ArrayList <Bullet1>() ;



    public void launchFrame() {
        this.setTitle("保卫萝卜.exe");
        this.setBounds(300, 20, img.getIconWidth()+10, img.getIconHeight()+30);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        this.add(cardPanel);
        panelMain = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, null);
            }

        };
        cardPanel.add("1", panelMain);
        panelMain.setLayout(null);

        JButton buttonStart = new JButton(img1);
        buttonStart.setBackground(new Color(0, 0, 0));
        buttonStart.setOpaque(false);
        buttonStart.setBounds(550, 750, 300, 70);
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "2");
            }
        });
        panelMain.add(buttonStart);


        panelSelect = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img2.getImage(), 0, 0, null);
            }
        };
        panelSelect.setLayout(null);
        cardPanel.add("2", panelSelect);
        JLabel labelSelect = new JLabel("选择关卡");
        labelSelect.setFont(new Font("华文彩云", Font.BOLD, 70));
        labelSelect.setBounds(550, 20, 300, 100);
        panelSelect.add(labelSelect);

        JButton button1 = new JButton("第一关");
        button1.setFont(new Font("华文行楷", Font.BOLD, 40));
        button1.setBackground(new Color(0, 0, 0));
        button1.setBounds(10, 200, 200, 50);
        button1.setOpaque(false);
        JButton button2 = new JButton("第二关");
        button2.setFont(new Font("华文行楷", Font.BOLD, 40));
        button2.setBackground(new Color(0, 0, 0));
        button2.setBounds(240, 200, 200, 50);
        button2.setOpaque(false);
        JButton button3 = new JButton("第三关");
        button3.setFont(new Font("华文行楷", Font.BOLD, 40));
        button3.setBounds(470, 200, 200, 50);
        button3.setBackground(new Color(0, 0, 0));
        button3.setOpaque(false);
        JButton button4 = new JButton("第四关");
        button4.setFont(new Font("华文行楷", Font.BOLD, 40));
        button4.setBounds(700, 200, 200, 50);
        button4.setBackground(new Color(0, 0, 0));
        button4.setOpaque(false);
        JButton button5 = new JButton("第五关");
        button5.setFont(new Font("华文行楷", Font.BOLD, 40));
        button5.setBounds(930, 200, 200, 50);
        button5.setBackground(new Color(0, 0, 0));
        button5.setOpaque(false);
        JButton button6 = new JButton("第六关");
        button6.setFont(new Font("华文行楷", Font.BOLD, 40));
        button6.setBounds(1160, 200, 200, 50);
        button6.setBackground(new Color(0, 0, 0));
        button6.setOpaque(false);
        JButton button7 = new JButton("第七关");
        button7.setFont(new Font("华文行楷", Font.BOLD, 40));
        button7.setBounds(10, 300, 200, 50);
        button7.setBackground(new Color(0, 0, 0));
        button7.setOpaque(false);
        JButton buttonFuture = new JButton("敬请期待……");
        buttonFuture.setFont(new Font("华文行楷", Font.BOLD, 40));
        buttonFuture.setBounds(240, 300, 400, 50);
        buttonFuture.setBackground(new Color(0, 0, 0));
        buttonFuture.setOpaque(false);

        panelSelect.add(button1);
        panelSelect.add(button2);
        panelSelect.add(button3);
        panelSelect.add(button4);
        panelSelect.add(button5);
        panelSelect.add(button6);
        panelSelect.add(button7);
        panelSelect.add(buttonFuture);

        JButton buttonSelect1 = new JButton("选择关卡");
        buttonSelect1.setBackground(new Color(0,0,0));
        buttonSelect1.setOpaque(false);
        buttonSelect1.setFont(new Font("华文彩云",Font.BOLD,20));
        JButton buttonOver1 = new JButton("退出游戏");
        buttonOver1.setBackground(new Color(0,0,0));
        buttonOver1.setOpaque(false);
        buttonOver1.setFont(new Font("华文彩云",Font.BOLD,20));
        JButton buttonTime1 = new JButton("X1");
        buttonTime1.setBackground(new Color(0,0,0));
        buttonTime1.setOpaque(false);
        buttonTime1.setFont(new Font("华文彩云",Font.BOLD,20));
        JButton buttonTime2 = new JButton("X2");
        buttonTime2.setFont(new Font("华文彩云",Font.BOLD,20));
        buttonTime2.setBackground(new Color(0,0,0));
        buttonTime2.setOpaque(false);
        buttonOver1.setFont(new Font("华文彩云",Font.BOLD,20));
        buttonSelect1.setBounds(600,0,250,30);
        buttonOver1.setBounds(850,0,250,30);
        buttonTime1.setBounds(600,30,250,30);
        buttonTime2.setBounds(850,30,250,30);

        //血一般的教训！！！！！。。。。。
//        JButton buttonSet1_1 = new JButton();
//        buttonSet1_1.setBackground(new Color(0,0,0));
//        buttonSet1_1.setOpaque(false);
//        buttonSet1_1.setBounds(80,0,70,50);
//        buttonSet1_1.setActionCommand("0");
//        jButtons1.add(buttonSet1_1);
//
//
//        JButton buttonSet1_2 = new JButton();
//        buttonSet1_2.setBackground(new Color(0,0,0));
//        buttonSet1_2.setOpaque(false);
//        buttonSet1_2.setBounds(150,0,70,50);
//        buttonSet1_2.setActionCommand("1");
//        jButtons1.add(buttonSet1_2);
//
//        JButton buttonSet1_3 = new JButton();
//        buttonSet1_3.setBackground(new Color(0,0,0));
//        buttonSet1_3.setOpaque(false);
//        buttonSet1_3.setBounds(220,0,70,50);
//        buttonSet1_3.setActionCommand("2");
//        jButtons1.add(buttonSet1_3);
//
//        JButton buttonSet1_4 = new JButton();
//        buttonSet1_4.setBackground(new Color(0,0,0));
//        buttonSet1_4.setOpaque(false);
//        buttonSet1_4.setBounds(290,0,70,50);
//        buttonSet1_4.setActionCommand("3");
//        jButtons1.add(buttonSet1_4);
//
//        JButton buttonSet1_5 = new JButton();
//        buttonSet1_5.setBackground(new Color(0,0,0));
//        buttonSet1_5.setOpaque(false);
//        buttonSet1_5.setBounds(360,0,70,50);
//        buttonSet1_5.setActionCommand("4");
//        jButtons1.add(buttonSet1_5);
//
//        JButton buttonSet1_6 = new JButton();
//        buttonSet1_6.setBackground(new Color(0,0,0));
//        buttonSet1_6.setOpaque(false);
//        buttonSet1_6.setBounds(430,0,70,50);
//        buttonSet1_6.setActionCommand("5");
//        jButtons1.add(buttonSet1_6);
//
//        JButton buttonSet1_7 = new JButton();
//        buttonSet1_7.setBackground(new Color(0,0,0));
//        buttonSet1_7.setOpaque(false);
//        buttonSet1_7.setBounds(500,0,70,50);
//        buttonSet1_7.setActionCommand("6");
//        jButtons1.add(buttonSet1_7);
//
//        JButton buttonSet1_8 = new JButton();
//        buttonSet1_8.setBackground(new Color(0,0,0));
//        buttonSet1_8.setOpaque(false);
//        buttonSet1_8.setBounds(10,250,70,50);
//        buttonSet1_8.setActionCommand("7");
//        jButtons1.add(buttonSet1_8);
//
//        JButton buttonSet1_9 = new JButton();
//        buttonSet1_9.setBackground(new Color(0,0,0));
//        buttonSet1_9.setOpaque(false);
//        buttonSet1_9.setBounds(80,250,70,50);
//        buttonSet1_9.setActionCommand("8");
//        jButtons1.add(buttonSet1_9);
//
//        JButton buttonSet1_10 = new JButton();
//        buttonSet1_10.setBackground(new Color(0,0,0));
//        buttonSet1_10.setOpaque(false);
//        buttonSet1_10.setBounds(150,250,70,50);
//        buttonSet1_10.setActionCommand("9");
//        jButtons1.add(buttonSet1_10);
//
//        JButton buttonSet1_11 = new JButton();;
//        buttonSet1_11.setBackground(new Color(0,0,0));
//        buttonSet1_11.setOpaque(false);
//        buttonSet1_11.setBounds(220,250,70,50);
//        buttonSet1_11.setActionCommand("10");
//        jButtons1.add(buttonSet1_11);
//
//        JButton buttonSet1_12 = new JButton();
//        buttonSet1_12.setBackground(new Color(0,0,0));
//        buttonSet1_12.setOpaque(false);
//        buttonSet1_12.setBounds(290,250,70,50);
//        buttonSet1_12.setActionCommand("11");
//        jButtons1.add(buttonSet1_12);
//
//        JButton buttonSet1_13 = new JButton();
//        buttonSet1_13.setBackground(new Color(0,0,0));
//        buttonSet1_13.setOpaque(false);
//        buttonSet1_13.setBounds(360,250,70,50);
//        buttonSet1_13.setActionCommand("12");
//        jButtons1.add(buttonSet1_13);
//
//        JButton buttonSet1_14 = new JButton();
//        buttonSet1_14.setBackground(new Color(0,0,0));
//        buttonSet1_14.setOpaque(false);
//        buttonSet1_14.setBounds(430,250,70,50);
//        buttonSet1_14.setActionCommand("13");
//        jButtons1.add(buttonSet1_14);
//
//        JButton buttonSet1_15 = new JButton();
//        buttonSet1_15.setBackground(new Color(0,0,0));
//        buttonSet1_15.setOpaque(false);
//        buttonSet1_15.setBounds(500,250,70,50);
//        buttonSet1_15.setActionCommand("14");
//        jButtons1.add(buttonSet1_15);
//
//        JButton buttonSet1_16 = new JButton();
//        buttonSet1_16.setBackground(new Color(0,0,0));
//        buttonSet1_16.setOpaque(false);
//        buttonSet1_16.setBounds(570,250,70,50);
//        buttonSet1_16.setActionCommand("15");
//        jButtons1.add(buttonSet1_16);
//
//        JButton buttonSet1_17 = new JButton();
//        buttonSet1_17.setBackground(new Color(0,0,0));
//        buttonSet1_17.setOpaque(false);
//        buttonSet1_17.setBounds(640,250,70,50);
//        buttonSet1_17.setActionCommand("16");
//        jButtons1.add(buttonSet1_17);
//
//        JButton buttonSet1_18 = new JButton("");
//        buttonSet1_18.setBackground(new Color(0,0,0));
//        buttonSet1_18.setOpaque(false);
//        buttonSet1_18.setBounds(10,300,70,50);
//        buttonSet1_18.setActionCommand("17");
//        jButtons1.add(buttonSet1_18);
//
//        JButton buttonSet1_19 = new JButton("");
//        buttonSet1_19.setBackground(new Color(0,0,0));
//        buttonSet1_19.setOpaque(false);
//        buttonSet1_19.setBounds(80,300,70,50);
//        buttonSet1_19.setActionCommand("18");
//        jButtons1.add(buttonSet1_19);
//
//        JButton buttonSet1_20 = new JButton("");
//        buttonSet1_20.setBackground(new Color(0,0,0));
//        buttonSet1_20.setOpaque(false);
//        buttonSet1_20.setBounds(150,300,70,50);
//        buttonSet1_20.setActionCommand("19");
//        jButtons1.add(buttonSet1_20);
//
//        JButton buttonSet1_21 = new JButton("");
//        buttonSet1_21.setBackground(new Color(0,0,0));
//        buttonSet1_21.setOpaque(false);
//        buttonSet1_21.setBounds(220,300,70,50);
//        buttonSet1_21.setActionCommand("20");
//        jButtons1.add(buttonSet1_21);
//
//
//        JButton buttonSet1_22 = new JButton("");
//        buttonSet1_22.setBackground(new Color(0,0,0));
//        buttonSet1_22.setOpaque(false);
//        buttonSet1_22.setBounds(290,300,70,50);
//        buttonSet1_22.setActionCommand("21");
//        jButtons1.add(buttonSet1_22);
//
//        JButton buttonSet1_23 = new JButton("");;
//        buttonSet1_23.setBackground(new Color(0,0,0));
//        buttonSet1_23.setOpaque(false);
//        buttonSet1_23.setBounds(360,300,70,50);
//        buttonSet1_23.setActionCommand("22");
//        jButtons1.add(buttonSet1_23);
//
//        JButton buttonSet1_24 = new JButton("");
//        buttonSet1_24.setBackground(new Color(0,0,0));
//        buttonSet1_24.setOpaque(false);
//        buttonSet1_24.setBounds(430,300,70,50);
//        buttonSet1_24.setActionCommand("23");
//        jButtons1.add(buttonSet1_24);
//
//        JButton buttonSet1_25 = new JButton("");
//        buttonSet1_25.setBackground(new Color(0,0,0));
//        buttonSet1_25.setOpaque(false);
//        buttonSet1_25.setBounds(500,300,70,50);
//        buttonSet1_25.setActionCommand("24");
//        jButtons1.add(buttonSet1_25);
//
//        JButton buttonSet1_26 = new JButton("");
//        buttonSet1_26.setBackground(new Color(0,0,0));
//        buttonSet1_26.setOpaque(false);
//        buttonSet1_26.setBounds(570,300,70,50);
//        buttonSet1_26.setActionCommand("25");
//        jButtons1.add(buttonSet1_26);
//
//        JButton buttonSet1_27 = new JButton("");
//        buttonSet1_27.setBackground(new Color(0,0,0));
//        buttonSet1_27.setOpaque(false);
//        buttonSet1_27.setBounds(640,300,70,50);
//        buttonSet1_27.setActionCommand("26");
//        jButtons1.add(buttonSet1_27);
//
//        JButton buttonSet1_28 = new JButton("");
//        buttonSet1_28.setBackground(new Color(0,0,0));
//        buttonSet1_28.setOpaque(false);
//        buttonSet1_28.setBounds(570,370,70,50);
//        buttonSet1_28.setActionCommand("27");
//        jButtons1.add(buttonSet1_28);
//
//        JButton buttonSet1_29 = new JButton("");
//        buttonSet1_29.setBackground(new Color(0,0,0));
//        buttonSet1_29.setOpaque(false);
//        buttonSet1_29.setBounds(640,370,70,50);
//        buttonSet1_29.setActionCommand("28");
//        jButtons1.add(buttonSet1_29);
//
//        JButton buttonSet1_30 = new JButton("");
//        buttonSet1_30.setBackground(new Color(0,0,0));
//        buttonSet1_30.setOpaque(false);
//        buttonSet1_30.setBounds(710,370,70,50);
//        buttonSet1_30.setActionCommand("29");
//        jButtons1.add(buttonSet1_30);
//
//        JButton buttonSet1_31 = new JButton("");
//        buttonSet1_31.setBackground(new Color(0,0,0));
//        buttonSet1_31.setOpaque(false);
//        buttonSet1_31.setBounds(780,370,70,50);
//        buttonSet1_31.setActionCommand("30");
//        jButtons1.add(buttonSet1_31);
//
//        JButton buttonSet1_32 = new JButton("");
//        buttonSet1_32.setBackground(new Color(0,0,0));
//        buttonSet1_32.setOpaque(false);
//        buttonSet1_32.setBounds(850,370,70,50);
//        buttonSet1_32.setActionCommand("31");
//        jButtons1.add(buttonSet1_32);
//
//        JButton buttonSet1_33 = new JButton("");
//        buttonSet1_33.setBackground(new Color(0,0,0));
//        buttonSet1_33.setOpaque(false);
//        buttonSet1_33.setBounds(920,370,70,50);
//        buttonSet1_33.setActionCommand("32");
//        jButtons1.add(buttonSet1_33);
//
//        JButton buttonSet1_34 = new JButton("");
//        buttonSet1_34.setBackground(new Color(0,0,0));
//        buttonSet1_34.setOpaque(false);
//        buttonSet1_34.setBounds(990,370,70,50);
//        buttonSet1_34.setActionCommand("33");
//        jButtons1.add(buttonSet1_34);
//
//        JButton buttonSet1_35 = new JButton("");
//        buttonSet1_35.setBackground(new Color(0,0,0));
//        buttonSet1_35.setOpaque(false);
//        buttonSet1_35.setBounds(1060,370,70,50);
//        buttonSet1_35.setActionCommand("34");
//        jButtons1.add(buttonSet1_35);
//
//        JButton buttonSet1_36 = new JButton("");
//        buttonSet1_36.setBackground(new Color(0,0,0));
//        buttonSet1_36.setOpaque(false);
//        buttonSet1_36.setBounds(640,420,70,50);
//        buttonSet1_36.setActionCommand("35");
//        jButtons1.add(buttonSet1_36);
//
//        JButton buttonSet1_37 = new JButton("");
//        buttonSet1_37.setBackground(new Color(0,0,0));
//        buttonSet1_37.setOpaque(false);
//        buttonSet1_37.setBounds(570,420,70,50);
//        buttonSet1_37.setActionCommand("36");
//        jButtons1.add(buttonSet1_37);
//
//        JButton buttonSet1_38 = new JButton("");
//        buttonSet1_38.setBackground(new Color(0,0,0));
//        buttonSet1_38.setOpaque(false);
//        buttonSet1_38.setBounds(640,470,70,50);
//        buttonSet1_38.setActionCommand("37");
//        jButtons1.add(buttonSet1_38);
//
//        JButton buttonSet1_39 = new JButton("");
//        buttonSet1_39.setBackground(new Color(0,0,0));
//        buttonSet1_39.setOpaque(false);
//        buttonSet1_39.setBounds(570,470,70,50);
//        buttonSet1_39.setActionCommand("38");
//        jButtons1.add(buttonSet1_39);
//
//        JButton buttonSet1_40 = new JButton("");
//        buttonSet1_40.setBackground(new Color(0,0,0));
//        buttonSet1_40.setOpaque(false);
//        buttonSet1_40.setBounds(570,520,70,50);
//        buttonSet1_40.setActionCommand("39");
//        jButtons1.add(buttonSet1_40);
//
//        JButton buttonSet1_41 = new JButton("");
//        buttonSet1_41.setBackground(new Color(0,0,0));
//        buttonSet1_41.setOpaque(false);
//        buttonSet1_41.setBounds(640,520,70,50);
//        buttonSet1_41.setActionCommand("40");
//        jButtons1.add(buttonSet1_41);
//
//        JButton buttonSet1_42 = new JButton("");
//        buttonSet1_42.setBackground(new Color(0,0,0));
//        buttonSet1_42.setOpaque(false);
//        buttonSet1_42.setBounds(570,570,70,50);
//        buttonSet1_42.setActionCommand("41");
//        jButtons1.add(buttonSet1_42);
//
//        JButton buttonSet1_43 = new JButton("");
//        buttonSet1_43.setBackground(new Color(0,0,0));
//        buttonSet1_43.setOpaque(false);
//        buttonSet1_43.setBounds(640,570,70,50);
//        buttonSet1_43.setActionCommand("42");
//        jButtons1.add(buttonSet1_43);
//
//        JButton buttonSet1_44 = new JButton("");
//        buttonSet1_44.setBackground(new Color(0,0,0));
//        buttonSet1_44.setOpaque(false);
//        buttonSet1_44.setBounds(570,620,70,50);
//        buttonSet1_44.setActionCommand("43");
//        jButtons1.add(buttonSet1_44);
//
//        JButton buttonSet1_45 = new JButton("");
//        buttonSet1_45.setBackground(new Color(0,0,0));
//        buttonSet1_45.setOpaque(false);
//        buttonSet1_45.setBounds(640,620,70,50);
//        buttonSet1_45.setActionCommand("44");
//        jButtons1.add(buttonSet1_45);
//
//        JButton buttonSet1_46 = new JButton("");
//        buttonSet1_46.setBackground(new Color(0,0,0));
//        buttonSet1_46.setOpaque(false);
//        buttonSet1_46.setBounds(500,520,70,50);
//        buttonSet1_46.setActionCommand("45");
//        jButtons1.add(buttonSet1_46);
//
//        JButton buttonSet1_47 = new JButton("");
//        buttonSet1_47.setBackground(new Color(0,0,0));
//        buttonSet1_47.setOpaque(false);
//        buttonSet1_47.setBounds(430,520,70,50);
//        buttonSet1_47.setActionCommand("46");
//        jButtons1.add(buttonSet1_47);
//
//        JButton buttonSet1_48 = new JButton("");
//        buttonSet1_48.setBackground(new Color(0,0,0));
//        buttonSet1_48.setOpaque(false);
//        buttonSet1_48.setBounds(360,520,70,50);
//        buttonSet1_48.setActionCommand("47");
//        jButtons1.add(buttonSet1_48);
//
//        JButton buttonSet1_49 = new JButton("");
//        buttonSet1_49.setBackground(new Color(0,0,0));
//        buttonSet1_49.setOpaque(false);
//        buttonSet1_49.setBounds(290,520,70,50);
//        buttonSet1_49.setActionCommand("48");
//        jButtons1.add(buttonSet1_49);
//
//        JButton buttonSet1_50 = new JButton("");
//        buttonSet1_50.setBackground(new Color(0,0,0));
//        buttonSet1_50.setOpaque(false);
//        buttonSet1_50.setBounds(220,520,70,50);
//        buttonSet1_50.setActionCommand("49");
//        jButtons1.add(buttonSet1_50);
//
//        JButton buttonSet1_51 = new JButton("");
//        buttonSet1_51.setBackground(new Color(0,0,0));
//        buttonSet1_51.setOpaque(false);
//        buttonSet1_51.setBounds(150,520,70,50);
//        buttonSet1_51.setActionCommand("50");
//        jButtons1.add(buttonSet1_51);
//
//        JButton buttonSet1_52 = new JButton("");
//        buttonSet1_52.setBackground(new Color(0,0,0));
//        buttonSet1_52.setOpaque(false);
//        buttonSet1_52.setBounds(80,520,70,50);
//        buttonSet1_52.setActionCommand("51");
//        jButtons1.add(buttonSet1_52);
//
//        JButton buttonSet1_53 = new JButton("");
//        buttonSet1_53.setBackground(new Color(0,0,0));
//        buttonSet1_53.setOpaque(false);
//        buttonSet1_53.setBounds(10,520,70,50);
//        buttonSet1_53.setActionCommand("52");
//        jButtons1.add(buttonSet1_53);
//
//        JButton buttonSet1_54 = new JButton("");
//        buttonSet1_54.setBackground(new Color(0,0,0));
//        buttonSet1_54.setOpaque(false);
//        buttonSet1_54.setBounds(10,570,70,50);
//        buttonSet1_54.setActionCommand("53");
//        jButtons1.add(buttonSet1_54);
//
//        JButton buttonSet1_55 = new JButton("");
//        buttonSet1_55.setBackground(new Color(0,0,0));
//        buttonSet1_55.setOpaque(false);
//        buttonSet1_55.setBounds(80,570,70,50);
//        buttonSet1_55.setActionCommand("54");
//        jButtons1.add(buttonSet1_55);
//
//        JButton buttonSet1_56 = new JButton("");
//        buttonSet1_56.setBackground(new Color(0,0,0));
//        buttonSet1_56.setOpaque(false);
//        buttonSet1_56.setBounds(150,570,70,50);
//        buttonSet1_56.setActionCommand("55");
//        jButtons1.add(buttonSet1_56);
//
//        JButton buttonSet1_57 = new JButton("");
//        buttonSet1_57.setBackground(new Color(0,0,0));
//        buttonSet1_57.setOpaque(false);
//        buttonSet1_57.setBounds(220,570,70,50);
//        buttonSet1_57.setActionCommand("56");
//        jButtons1.add(buttonSet1_57);
//
//        JButton buttonSet1_58 = new JButton("");
//        buttonSet1_58.setBackground(new Color(0,0,0));
//        buttonSet1_58.setOpaque(false);
//        buttonSet1_58.setBounds(290,570,70,50);
//        buttonSet1_58.setActionCommand("57");
//        jButtons1.add(buttonSet1_58);
//
//        JButton buttonSet1_59 = new JButton("");
//        buttonSet1_59.setBackground(new Color(0,0,0));
//        buttonSet1_59.setOpaque(false);
//        buttonSet1_59.setBounds(360,570,70,50);
//        buttonSet1_59.setActionCommand("58");
//        jButtons1.add(buttonSet1_59);
//
//        JButton buttonSet1_60 = new JButton("");
//        buttonSet1_60.setBackground(new Color(0,0,0));
//        buttonSet1_60.setOpaque(false);
//        buttonSet1_60.setBounds(430,570,70,50);
//        buttonSet1_60.setActionCommand("59");
//        jButtons1.add(buttonSet1_60);
//
//        JButton buttonSet1_61 = new JButton("");
//        buttonSet1_61.setBackground(new Color(0,0,0));
//        buttonSet1_61.setOpaque(false);
//        buttonSet1_61.setBounds(500,570,70,50);
//        buttonSet1_61.setActionCommand("60");
//        jButtons1.add(buttonSet1_61);
//
//        JButton buttonSet1_62 = new JButton("");
//        buttonSet1_62.setBackground(new Color(0,0,0));
//        buttonSet1_62.setOpaque(false);
//        buttonSet1_62.setBounds(10,620,70,50);
//        buttonSet1_62.setActionCommand("61");
//        jButtons1.add(buttonSet1_62);
//
//        JButton buttonSet1_63 = new JButton("");
//        buttonSet1_63.setBackground(new Color(0,0,0));
//        buttonSet1_63.setOpaque(false);
//        buttonSet1_63.setBounds(80,620,70,50);
//        buttonSet1_63.setActionCommand("62");
//        jButtons1.add(buttonSet1_63);
//
//        JButton buttonSet1_64 = new JButton("");
//        buttonSet1_64.setBackground(new Color(0,0,0));
//        buttonSet1_64.setOpaque(false);
//        buttonSet1_64.setBounds(150,620,70,50);
//        buttonSet1_64.setActionCommand("63");
//        jButtons1.add(buttonSet1_64);
//
//        JButton buttonSet1_65 = new JButton("");
//        buttonSet1_65.setBackground(new Color(0,0,0));
//        buttonSet1_65.setOpaque(false);
//        buttonSet1_65.setBounds(220,620,70,50);
//        buttonSet1_65.setActionCommand("64");
//        jButtons1.add(buttonSet1_65);
//
//        JButton buttonSet1_66 = new JButton("");
//        buttonSet1_66.setBackground(new Color(0,0,0));
//        buttonSet1_66.setOpaque(false);
//        buttonSet1_66.setBounds(290,620,70,50);
//        buttonSet1_66.setActionCommand("65");
//        jButtons1.add(buttonSet1_66);
//
//        JButton buttonSet1_67 = new JButton("");
//        buttonSet1_67.setBackground(new Color(0,0,0));
//        buttonSet1_67.setOpaque(false);
//        buttonSet1_67.setBounds(360,620,70,50);
//        buttonSet1_67.setActionCommand("66");
//        jButtons1.add(buttonSet1_67);
//
//        JButton buttonSet1_68 = new JButton("");
//        buttonSet1_68.setBackground(new Color(0,0,0));
//        buttonSet1_68.setOpaque(false);
//        buttonSet1_68.setBounds(430,620,70,50);
//        buttonSet1_68.setActionCommand("67");
//        jButtons1.add(buttonSet1_68);
//
//        JButton buttonSet1_69 = new JButton("");
//        buttonSet1_69.setBackground(new Color(0,0,0));
//        buttonSet1_69.setOpaque(false);
//        buttonSet1_69.setBounds(500,620,70,50);
//        buttonSet1_69.setActionCommand("68");
//        jButtons1.add(buttonSet1_69);
//
//        JButton buttonSet1_70 = new JButton("");
//        buttonSet1_70.setBackground(new Color(0,0,0));
//        buttonSet1_70.setOpaque(false);
//        buttonSet1_70.setBounds(10,670,70,50);
//        buttonSet1_70.setActionCommand("69");
//        jButtons1.add(buttonSet1_70);
//
//        JButton buttonSet1_71 = new JButton("");
//        buttonSet1_71.setBackground(new Color(0,0,0));
//        buttonSet1_71.setOpaque(false);
//        buttonSet1_71.setBounds(80,670,70,50);
//        buttonSet1_71.setActionCommand("70");
//        jButtons1.add(buttonSet1_71);
//
//        JButton buttonSet1_72 = new JButton("");
//        buttonSet1_72.setBackground(new Color(0,0,0));
//        buttonSet1_72.setOpaque(false);
//        buttonSet1_72.setBounds(10,720,70,50);
//        buttonSet1_72.setActionCommand("71");
//        jButtons1.add(buttonSet1_72);
//
//        JButton buttonSet1_73 = new JButton("");
//        buttonSet1_73.setBackground(new Color(0,0,0));
//        buttonSet1_73.setOpaque(false);
//        buttonSet1_73.setBounds(80,720,70,50);
//        buttonSet1_73.setActionCommand("72");
//        jButtons1.add(buttonSet1_73);
//
//        JButton buttonSet1_74 = new JButton("");
//        buttonSet1_74.setBackground(new Color(0,0,0));
//        buttonSet1_74.setOpaque(false);
//        buttonSet1_74.setBounds(10,770,70,50);
//        buttonSet1_74.setActionCommand("73");
//        jButtons1.add(buttonSet1_74);
//
//        JButton buttonSet1_75 = new JButton("");
//        buttonSet1_75.setBackground(new Color(0,0,0));
//        buttonSet1_75.setOpaque(false);
//        buttonSet1_75.setBounds(80,770,70,50);
//        buttonSet1_75.setActionCommand("74");
//        jButtons1.add(buttonSet1_75);
//
//        JButton buttonSet1_76 = new JButton("");
//        buttonSet1_76.setBackground(new Color(0,0,0));
//        buttonSet1_76.setOpaque(false);
//        buttonSet1_76.setBounds(920,60,70,50);
//        buttonSet1_76.setActionCommand("75");
//        jButtons1.add(buttonSet1_76);
//
//        JButton buttonSet1_77 = new JButton("");
//        buttonSet1_77.setBackground(new Color(0,0,0));
//        buttonSet1_77.setOpaque(false);
//        buttonSet1_77.setBounds(990,60,70,50);
//        buttonSet1_77.setActionCommand("76");
//        jButtons1.add(buttonSet1_77);
//
//        JButton buttonSet1_78 = new JButton("");
//        buttonSet1_78.setBackground(new Color(0,0,0));
//        buttonSet1_78.setOpaque(false);
//        buttonSet1_78.setBounds(920,110,70,50);
//        buttonSet1_78.setActionCommand("77");
//        jButtons1.add(buttonSet1_78);
//
//        JButton buttonSet1_79 = new JButton("");
//        buttonSet1_79.setBackground(new Color(0,0,0));
//        buttonSet1_79.setOpaque(false);
//        buttonSet1_79.setBounds(990,110,70,50);
//        buttonSet1_79.setActionCommand("78");
//        jButtons1.add(buttonSet1_79);
//
//        JButton buttonSet1_80 = new JButton("");
//        buttonSet1_80.setBackground(new Color(0,0,0));
//        buttonSet1_80.setOpaque(false);
//        buttonSet1_80.setBounds(1270,60,70,50);
//        buttonSet1_80.setActionCommand("79");
//        jButtons1.add(buttonSet1_80);
//
//        JButton buttonSet1_81 = new JButton("");
//        buttonSet1_81.setBackground(new Color(0,0,0));
//        buttonSet1_81.setOpaque(false);
//        buttonSet1_81.setBounds(1340,60,70,50);
//        buttonSet1_81.setActionCommand("80");
//        jButtons1.add(buttonSet1_81);
//
//        JButton buttonSet1_82 = new JButton("");
//        buttonSet1_82.setBackground(new Color(0,0,0));
//        buttonSet1_82.setOpaque(false);
//        buttonSet1_82.setBounds(1270,110,70,50);
//        buttonSet1_82.setActionCommand("81");
//        jButtons1.add(buttonSet1_82);
//
//        JButton buttonSet1_83 = new JButton("");
//        buttonSet1_83.setBackground(new Color(0,0,0));
//        buttonSet1_83.setOpaque(false);
//        buttonSet1_83.setBounds(1340,110,70,50);
//        buttonSet1_83.setActionCommand("82");
//        jButtons1.add(buttonSet1_83);
//
//        JButton buttonSet1_84 = new JButton("");
//        buttonSet1_84.setBackground(new Color(0,0,0));
//        buttonSet1_84.setOpaque(false);
//        buttonSet1_84.setBounds(1270,160,70,50);
//        buttonSet1_84.setActionCommand("83");
//        jButtons1.add(buttonSet1_84);
//
//        JButton buttonSet1_85 = new JButton("");
//        buttonSet1_85.setBackground(new Color(0,0,0));
//        buttonSet1_85.setOpaque(false);
//        buttonSet1_85.setBounds(1340,160,70,50);
//        buttonSet1_85.setActionCommand("84");
//        jButtons1.add(buttonSet1_85);
//
//        JButton buttonSet1_86 = new JButton("");
//        buttonSet1_86.setBackground(new Color(0,0,0));
//        buttonSet1_86.setOpaque(false);
//        buttonSet1_86.setBounds(1270,210,70,50);
//        buttonSet1_86.setActionCommand("85");
//        jButtons1.add(buttonSet1_86);
//
//        JButton buttonSet1_87 = new JButton("");
//        buttonSet1_87.setBackground(new Color(0,0,0));
//        buttonSet1_87.setOpaque(false);
//        buttonSet1_87.setBounds(1340,210,70,50);
//        buttonSet1_87.setActionCommand("86");
//        jButtons1.add(buttonSet1_87);
//
//        JButton buttonSet1_88 = new JButton("");
//        buttonSet1_88.setBackground(new Color(0,0,0));
//        buttonSet1_88.setOpaque(false);
//        buttonSet1_88.setBounds(1270,260,70,50);
//        buttonSet1_88.setActionCommand("87");
//        jButtons1.add(buttonSet1_88);
//
//        JButton buttonSet1_89 = new JButton("");
//        buttonSet1_89.setBackground(new Color(0,0,0));
//        buttonSet1_89.setOpaque(false);
//        buttonSet1_89.setBounds(1270,310,70,50);
//        buttonSet1_89.setActionCommand("88");
//        jButtons1.add(buttonSet1_89);
//
//        JButton buttonSet1_90 = new JButton("");
//        buttonSet1_90.setBackground(new Color(0,0,0));
//        buttonSet1_90.setOpaque(false);
//        buttonSet1_90.setBounds(1270,360,70,50);
//        buttonSet1_90.setActionCommand("89");
//        jButtons1.add(buttonSet1_90);
//
//        JButton buttonSet1_91 = new JButton("");
//        buttonSet1_91.setBackground(new Color(0,0,0));
//        buttonSet1_91.setOpaque(false);
//        buttonSet1_91.setBounds(1270,410,70,50);
//        buttonSet1_91.setActionCommand("90");
//        jButtons1.add(buttonSet1_91);
//
//        JButton buttonSet1_92 = new JButton("");
//        buttonSet1_92.setBackground(new Color(0,0,0));
//        buttonSet1_92.setOpaque(false);
//        buttonSet1_92.setBounds(1270,460,70,50);
//        buttonSet1_92.setActionCommand("91");
//        jButtons1.add(buttonSet1_92);
//
//        JButton buttonSet1_93 = new JButton("");
//        buttonSet1_93.setBackground(new Color(0,0,0));
//        buttonSet1_93.setOpaque(false);
//        buttonSet1_93.setBounds(1270,510,70,50);
//        buttonSet1_93.setActionCommand("92");
//        jButtons1.add(buttonSet1_93);
//
//        JButton buttonSet1_94 = new JButton("");
//        buttonSet1_94.setBackground(new Color(0,0,0));
//        buttonSet1_94.setOpaque(false);
//        buttonSet1_94.setBounds(920,610,70,50);
//        buttonSet1_94.setActionCommand("93");
//        jButtons1.add(buttonSet1_94);
//
//        JButton buttonSet1_95 = new JButton("");
//        buttonSet1_95.setBackground(new Color(0,0,0));
//        buttonSet1_95.setOpaque(false);
//        buttonSet1_95.setBounds(920,670,70,50);
//        buttonSet1_95.setActionCommand("94");
//        jButtons1.add(buttonSet1_95);
//
//        JButton buttonSet1_96 = new JButton("");
//        buttonSet1_96.setBackground(new Color(0,0,0));
//        buttonSet1_96.setOpaque(false);
//        buttonSet1_96.setBounds(920,730,70,50);
//        buttonSet1_96.setActionCommand("95");
//        jButtons1.add(buttonSet1_96);
//
//        JButton buttonSet1_97 = new JButton("");
//        buttonSet1_97.setBackground(new Color(0,0,0));
//        buttonSet1_97.setOpaque(false);
//        buttonSet1_97.setBounds(920,790,70,50);
//        buttonSet1_97.setActionCommand("96");
//        jButtons1.add(buttonSet1_97);



        JButton buttonSelect2 = new JButton("选择关卡");
        buttonSelect2.setBackground(new Color(0,0,0));
        buttonSelect2.setOpaque(false);
        buttonSelect2.setFont(new Font("华文彩云",Font.BOLD,50));
        JButton buttonOver2 = new JButton("退出游戏");
        buttonOver2.setBackground(new Color(0,0,0));
        buttonOver2.setOpaque(false);
        buttonOver2.setFont(new Font("华文彩云",Font.BOLD,50));
        buttonSelect2.setBounds(800,20,250,70);
        buttonOver2.setBounds(1100,20,250,70);


        JLabel labelMoney3 = new JLabel("金币");
        labelMoney3.setFont(new Font("华文彩云",Font.BOLD,50));
        JLabel labelRound3 = new JLabel("第"+"   "+"波");
        labelRound3.setFont(new Font("华文彩云",Font.BOLD,50));
        JButton buttonSelect3 = new JButton("选择关卡");
        buttonSelect3.setBackground(new Color(0,0,0));
        buttonSelect3.setOpaque(false);
        buttonSelect3.setFont(new Font("华文彩云",Font.BOLD,50));
        JButton buttonOver3 = new JButton("退出游戏");
        buttonOver3.setBackground(new Color(0,0,0));
        buttonOver3.setOpaque(false);
        buttonOver3.setFont(new Font("华文彩云",Font.BOLD,50));
        labelMoney3.setBounds(250,20,250,70);
        labelRound3.setBounds(540,20,250,70);
        buttonSelect3.setBounds(800,20,250,70);
        buttonOver3.setBounds(1100,20,250,70);

        JLabel labelMoney4 = new JLabel("金币");
        labelMoney4.setFont(new Font("华文彩云",Font.BOLD,50));
        JLabel labelRound4 = new JLabel("第"+"   "+"波");
        labelRound4.setFont(new Font("华文彩云",Font.BOLD,50));
        JButton buttonSelect4 = new JButton("选择关卡");
        buttonSelect4.setBackground(new Color(0,0,0));
        buttonSelect4.setOpaque(false);
        buttonSelect4.setFont(new Font("华文彩云",Font.BOLD,50));
        JButton buttonOver4 = new JButton("退出游戏");
        buttonOver4.setBackground(new Color(0,0,0));
        buttonOver4.setOpaque(false);
        buttonOver4.setFont(new Font("华文彩云",Font.BOLD,50));
        labelMoney4.setBounds(250,20,250,70);
        labelRound4.setBounds(540,20,250,70);
        buttonSelect4.setBounds(800,20,250,70);
        buttonOver4.setBounds(1100,20,250,70);

        JLabel labelMoney5 = new JLabel("金币");
        labelMoney5.setFont(new Font("华文彩云",Font.BOLD,50));
        JLabel labelRound5 = new JLabel("第"+"   "+"波");
        labelRound5.setFont(new Font("华文彩云",Font.BOLD,50));
        JButton buttonSelect5 = new JButton("选择关卡");
        buttonSelect5.setBackground(new Color(0,0,0));
        buttonSelect5.setOpaque(false);
        buttonSelect5.setFont(new Font("华文彩云",Font.BOLD,50));
        JButton buttonOver5 = new JButton("退出游戏");
        buttonOver5.setBackground(new Color(0,0,0));
        buttonOver5.setOpaque(false);
        buttonOver5.setFont(new Font("华文彩云",Font.BOLD,50));
        labelMoney5.setBounds(250,20,250,70);
        labelRound5.setBounds(540,20,250,70);
        buttonSelect5.setBounds(800,20,250,70);
        buttonOver5.setBounds(1100,20,250,70);

        JLabel labelMoney6 = new JLabel("金币");
        labelMoney6.setFont(new Font("华文彩云",Font.BOLD,50));
        JLabel labelRound6 = new JLabel("第"+"   "+"波");
        labelRound6.setFont(new Font("华文彩云",Font.BOLD,50));
        JButton buttonSelect6 = new JButton("选择关卡");
        buttonSelect6.setBackground(new Color(0,0,0));
        buttonSelect6.setOpaque(false);
        buttonSelect6.setFont(new Font("华文彩云",Font.BOLD,50));
        JButton buttonOver6 = new JButton("退出游戏");
        buttonOver6.setBackground(new Color(0,0,0));
        buttonOver6.setOpaque(false);
        buttonOver6.setFont(new Font("华文彩云",Font.BOLD,50));
        labelMoney6.setBounds(250,20,250,70);
        labelRound6.setBounds(540,20,250,70);
        buttonSelect6.setBounds(800,20,250,70);
        buttonOver6.setBounds(1100,20,250,70);



        JLabel labelMoney7 = new JLabel("金币");
        labelMoney7.setFont(new Font("华文彩云",Font.BOLD,50));
        JLabel labelRound7 = new JLabel("第"+"   "+"波");
        labelRound7.setFont(new Font("华文彩云",Font.BOLD,50));
        JButton buttonSelect7 = new JButton("选择关卡");
        buttonSelect7.setBackground(new Color(0,0,0));
        buttonSelect7.setOpaque(false);
        buttonSelect7.setFont(new Font("华文彩云",Font.BOLD,50));
        JButton buttonOver7 = new JButton("退出游戏");
        buttonOver7.setBackground(new Color(0,0,0));
        buttonOver7.setOpaque(false);
        buttonOver7.setFont(new Font("华文彩云",Font.BOLD,50));
        labelMoney7.setBounds(250,20,250,70);
        labelRound7.setBounds(540,20,250,70);
        buttonSelect7.setBounds(800,20,250,70);
        buttonOver7.setBounds(1100,20,250,70);


        buttonOver1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonSelect1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              cardLayout.show(cardPanel,"2");
                i1=31;
                monster1List.removeAll(monster1List);
                round = 1;
                bullet1s1.removeAll(bullet1s1);
                bullet1s2.removeAll(bullet1s2);
                bullet1s3.removeAll(bullet1s3);
                bullet1s4.removeAll(bullet1s4);
                tower1s.removeAll(tower1s);
                money.setMoney(1000);
            }
        });

        buttonTime1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTime(1000);
            }
        });

        buttonTime2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTime(500);
            }
        });
        buttonOver2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonSelect2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameSelect = new JFrame("选择关卡");
                frameSelect.setResizable(false);
                frameSelect.setVisible(true);
                frameSelect.setBounds(550,150,img10.getIconWidth()+10,img10.getIconHeight());
                JPanel  panelSelect = new JPanel() {
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(img10.getImage(), 0, 0, null);
                    }
                };
                frameSelect.add(panelSelect);
                panelSelect.setLayout(null);

                JLabel labelSelect = new JLabel("选择关卡");
                labelSelect.setFont(new Font("华文彩云", Font.BOLD, 70));
                labelSelect.setBounds(300, 20, 300, 100);

                JButton button1 = new JButton("第一关");
                button1.setFont(new Font("华文行楷", Font.BOLD, 40));
                button1.setBackground(new Color(0, 0, 0));
                button1.setBounds(10, 150, 200, 50);
                button1.setOpaque(false);

                JButton button2 = new JButton("第二关");
                button2.setFont(new Font("华文行楷", Font.BOLD, 40));
                button2.setBackground(new Color(0, 0, 0));
                button2.setBounds(240, 150, 200, 50);
                button2.setOpaque(false);
                JButton button3 = new JButton("第三关");
                button3.setFont(new Font("华文行楷", Font.BOLD, 40));
                button3.setBounds(470, 150, 200, 50);
                button3.setBackground(new Color(0, 0, 0));
                button3.setOpaque(false);
                JButton button4 = new JButton("第四关");
                button4.setFont(new Font("华文行楷", Font.BOLD, 40));
                button4.setBounds(700, 150, 200, 50);
                button4.setBackground(new Color(0, 0, 0));
                button4.setOpaque(false);
                JButton button5 = new JButton("第五关");
                button5.setFont(new Font("华文行楷", Font.BOLD, 40));
                button5.setBounds(10, 250, 200, 50);
                button5.setBackground(new Color(0, 0, 0));
                button5.setOpaque(false);
                JButton button6 = new JButton("第六关");
                button6.setFont(new Font("华文行楷", Font.BOLD, 40));
                button6.setBounds(240, 250, 200, 50);
                button6.setBackground(new Color(0, 0, 0));
                button6.setOpaque(false);
                JButton button7 = new JButton("第七关");
                button7.setFont(new Font("华文行楷", Font.BOLD, 40));
                button7.setBounds(470, 250, 200, 50);
                button7.setBackground(new Color(0, 0, 0));
                button7.setOpaque(false);
                JButton buttonFuture = new JButton("敬请期待……");
                buttonFuture.setFont(new Font("华文行楷", Font.BOLD, 40));
                buttonFuture.setBounds(10, 350, 400, 50);
                buttonFuture.setBackground(new Color(0, 0, 0));
                buttonFuture.setOpaque(false);


                button1.setForeground(Color.WHITE);
                button2.setForeground(Color.WHITE);
                button3.setForeground(Color.WHITE);
                button4.setForeground(Color.WHITE);
                button5.setForeground(Color.WHITE);
                button6.setForeground(Color.WHITE);
                button7.setForeground(Color.WHITE);
                buttonFuture.setForeground(Color.WHITE);
                labelSelect.setForeground(Color.WHITE);

                panelSelect.add(labelSelect);
                panelSelect.add(button1);
                panelSelect.add(button2);
                panelSelect.add(button3);
                panelSelect.add(button4);
                panelSelect.add(button5);
                panelSelect.add(button6);
                panelSelect.add(button7);
                panelSelect.add(buttonFuture);


                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"3");
                    }
                });
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"4");
                    }
                });
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"5");
                    }
                });
                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"6");
                    }
                });
                button5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"7");
                    }
                });
                button6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"8");
                    }
                });
                button7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"9");
                    }
                });

            }
        });

        buttonOver3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonSelect3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameSelect = new JFrame("选择关卡");
                frameSelect.setResizable(false);
                frameSelect.setVisible(true);
                frameSelect.setBounds(550,150,img10.getIconWidth()+10,img10.getIconHeight());
                JPanel  panelSelect = new JPanel() {
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(img10.getImage(), 0, 0, null);
                    }
                };
                frameSelect.add(panelSelect);
                panelSelect.setLayout(null);

                JLabel labelSelect = new JLabel("选择关卡");
                labelSelect.setFont(new Font("华文彩云", Font.BOLD, 70));
                labelSelect.setBounds(300, 20, 300, 100);

                JButton button1 = new JButton("第一关");
                button1.setFont(new Font("华文行楷", Font.BOLD, 40));
                button1.setBackground(new Color(0, 0, 0));
                button1.setBounds(10, 150, 200, 50);
                button1.setOpaque(false);

                JButton button2 = new JButton("第二关");
                button2.setFont(new Font("华文行楷", Font.BOLD, 40));
                button2.setBackground(new Color(0, 0, 0));
                button2.setBounds(240, 150, 200, 50);
                button2.setOpaque(false);
                JButton button3 = new JButton("第三关");
                button3.setFont(new Font("华文行楷", Font.BOLD, 40));
                button3.setBounds(470, 150, 200, 50);
                button3.setBackground(new Color(0, 0, 0));
                button3.setOpaque(false);
                JButton button4 = new JButton("第四关");
                button4.setFont(new Font("华文行楷", Font.BOLD, 40));
                button4.setBounds(700, 150, 200, 50);
                button4.setBackground(new Color(0, 0, 0));
                button4.setOpaque(false);
                JButton button5 = new JButton("第五关");
                button5.setFont(new Font("华文行楷", Font.BOLD, 40));
                button5.setBounds(10, 250, 200, 50);
                button5.setBackground(new Color(0, 0, 0));
                button5.setOpaque(false);
                JButton button6 = new JButton("第六关");
                button6.setFont(new Font("华文行楷", Font.BOLD, 40));
                button6.setBounds(240, 250, 200, 50);
                button6.setBackground(new Color(0, 0, 0));
                button6.setOpaque(false);
                JButton button7 = new JButton("第七关");
                button7.setFont(new Font("华文行楷", Font.BOLD, 40));
                button7.setBounds(470, 250, 200, 50);
                button7.setBackground(new Color(0, 0, 0));
                button7.setOpaque(false);
                JButton buttonFuture = new JButton("敬请期待……");
                buttonFuture.setFont(new Font("华文行楷", Font.BOLD, 40));
                buttonFuture.setBounds(10, 350, 400, 50);
                buttonFuture.setBackground(new Color(0, 0, 0));
                buttonFuture.setOpaque(false);


                button1.setForeground(Color.WHITE);
                button2.setForeground(Color.WHITE);
                button3.setForeground(Color.WHITE);
                button4.setForeground(Color.WHITE);
                button5.setForeground(Color.WHITE);
                button6.setForeground(Color.WHITE);
                button7.setForeground(Color.WHITE);
                buttonFuture.setForeground(Color.WHITE);
                labelSelect.setForeground(Color.WHITE);

                panelSelect.add(labelSelect);
                panelSelect.add(button1);
                panelSelect.add(button2);
                panelSelect.add(button3);
                panelSelect.add(button4);
                panelSelect.add(button5);
                panelSelect.add(button6);
                panelSelect.add(button7);
                panelSelect.add(buttonFuture);

                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"3");
                    }
                });
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"4");
                    }
                });
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"5");
                    }
                });
                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"6");
                    }
                });
                button5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"7");
                    }
                });
                button6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"8");
                    }
                });
                button7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"9");
                    }
                });
            }
        });


        buttonOver4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonSelect4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameSelect = new JFrame("选择关卡");
                frameSelect.setResizable(false);
                frameSelect.setVisible(true);
                frameSelect.setBounds(550,150,img10.getIconWidth()+10,img10.getIconHeight());
                JPanel  panelSelect = new JPanel() {
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(img10.getImage(), 0, 0, null);
                    }
                };
                frameSelect.add(panelSelect);
                panelSelect.setLayout(null);

                JLabel labelSelect = new JLabel("选择关卡");
                labelSelect.setFont(new Font("华文彩云", Font.BOLD, 70));
                labelSelect.setBounds(300, 20, 300, 100);

                JButton button1 = new JButton("第一关");
                button1.setFont(new Font("华文行楷", Font.BOLD, 40));
                button1.setBackground(new Color(0, 0, 0));
                button1.setBounds(10, 150, 200, 50);
                button1.setOpaque(false);

                JButton button2 = new JButton("第二关");
                button2.setFont(new Font("华文行楷", Font.BOLD, 40));
                button2.setBackground(new Color(0, 0, 0));
                button2.setBounds(240, 150, 200, 50);
                button2.setOpaque(false);
                JButton button3 = new JButton("第三关");
                button3.setFont(new Font("华文行楷", Font.BOLD, 40));
                button3.setBounds(470, 150, 200, 50);
                button3.setBackground(new Color(0, 0, 0));
                button3.setOpaque(false);
                JButton button4 = new JButton("第四关");
                button4.setFont(new Font("华文行楷", Font.BOLD, 40));
                button4.setBounds(700, 150, 200, 50);
                button4.setBackground(new Color(0, 0, 0));
                button4.setOpaque(false);
                JButton button5 = new JButton("第五关");
                button5.setFont(new Font("华文行楷", Font.BOLD, 40));
                button5.setBounds(10, 250, 200, 50);
                button5.setBackground(new Color(0, 0, 0));
                button5.setOpaque(false);
                JButton button6 = new JButton("第六关");
                button6.setFont(new Font("华文行楷", Font.BOLD, 40));
                button6.setBounds(240, 250, 200, 50);
                button6.setBackground(new Color(0, 0, 0));
                button6.setOpaque(false);
                JButton button7 = new JButton("第七关");
                button7.setFont(new Font("华文行楷", Font.BOLD, 40));
                button7.setBounds(470, 250, 200, 50);
                button7.setBackground(new Color(0, 0, 0));
                button7.setOpaque(false);
                JButton buttonFuture = new JButton("敬请期待……");
                buttonFuture.setFont(new Font("华文行楷", Font.BOLD, 40));
                buttonFuture.setBounds(10, 350, 400, 50);
                buttonFuture.setBackground(new Color(0, 0, 0));
                buttonFuture.setOpaque(false);


                button1.setForeground(Color.WHITE);
                button2.setForeground(Color.WHITE);
                button3.setForeground(Color.WHITE);
                button4.setForeground(Color.WHITE);
                button5.setForeground(Color.WHITE);
                button6.setForeground(Color.WHITE);
                button7.setForeground(Color.WHITE);
                buttonFuture.setForeground(Color.WHITE);
                labelSelect.setForeground(Color.WHITE);

                panelSelect.add(labelSelect);
                panelSelect.add(button1);
                panelSelect.add(button2);
                panelSelect.add(button3);
                panelSelect.add(button4);
                panelSelect.add(button5);
                panelSelect.add(button6);
                panelSelect.add(button7);
                panelSelect.add(buttonFuture);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"3");
                    }
                });
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"4");
                    }
                });
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"5");
                    }
                });
                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"6");
                    }
                });
                button5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"7");
                    }
                });
                button6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"8");
                    }
                });
                button7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"9");
                    }
                });

            }
        });

        buttonOver5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonSelect5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameSelect = new JFrame("选择关卡");
                frameSelect.setResizable(false);
                frameSelect.setVisible(true);
                frameSelect.setBounds(550,150,img10.getIconWidth()+10,img10.getIconHeight());
                JPanel  panelSelect = new JPanel() {
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(img10.getImage(), 0, 0, null);
                    }
                };
                frameSelect.add(panelSelect);
                panelSelect.setLayout(null);

                JLabel labelSelect = new JLabel("选择关卡");
                labelSelect.setFont(new Font("华文彩云", Font.BOLD, 70));
                labelSelect.setBounds(300, 20, 300, 100);

                JButton button1 = new JButton("第一关");
                button1.setFont(new Font("华文行楷", Font.BOLD, 40));
                button1.setBackground(new Color(0, 0, 0));
                button1.setBounds(10, 150, 200, 50);
                button1.setOpaque(false);

                JButton button2 = new JButton("第二关");
                button2.setFont(new Font("华文行楷", Font.BOLD, 40));
                button2.setBackground(new Color(0, 0, 0));
                button2.setBounds(240, 150, 200, 50);
                button2.setOpaque(false);
                JButton button3 = new JButton("第三关");
                button3.setFont(new Font("华文行楷", Font.BOLD, 40));
                button3.setBounds(470, 150, 200, 50);
                button3.setBackground(new Color(0, 0, 0));
                button3.setOpaque(false);
                JButton button4 = new JButton("第四关");
                button4.setFont(new Font("华文行楷", Font.BOLD, 40));
                button4.setBounds(700, 150, 200, 50);
                button4.setBackground(new Color(0, 0, 0));
                button4.setOpaque(false);
                JButton button5 = new JButton("第五关");
                button5.setFont(new Font("华文行楷", Font.BOLD, 40));
                button5.setBounds(10, 250, 200, 50);
                button5.setBackground(new Color(0, 0, 0));
                button5.setOpaque(false);
                JButton button6 = new JButton("第六关");
                button6.setFont(new Font("华文行楷", Font.BOLD, 40));
                button6.setBounds(240, 250, 200, 50);
                button6.setBackground(new Color(0, 0, 0));
                button6.setOpaque(false);
                JButton button7 = new JButton("第七关");
                button7.setFont(new Font("华文行楷", Font.BOLD, 40));
                button7.setBounds(470, 250, 200, 50);
                button7.setBackground(new Color(0, 0, 0));
                button7.setOpaque(false);
                JButton buttonFuture = new JButton("敬请期待……");
                buttonFuture.setFont(new Font("华文行楷", Font.BOLD, 40));
                buttonFuture.setBounds(10, 350, 400, 50);
                buttonFuture.setBackground(new Color(0, 0, 0));
                buttonFuture.setOpaque(false);


                button1.setForeground(Color.WHITE);
                button2.setForeground(Color.WHITE);
                button3.setForeground(Color.WHITE);
                button4.setForeground(Color.WHITE);
                button5.setForeground(Color.WHITE);
                button6.setForeground(Color.WHITE);
                button7.setForeground(Color.WHITE);
                buttonFuture.setForeground(Color.WHITE);
                labelSelect.setForeground(Color.WHITE);

                panelSelect.add(labelSelect);
                panelSelect.add(button1);
                panelSelect.add(button2);
                panelSelect.add(button3);
                panelSelect.add(button4);
                panelSelect.add(button5);
                panelSelect.add(button6);
                panelSelect.add(button7);
                panelSelect.add(buttonFuture);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"3");
                    }
                });
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"4");
                    }
                });
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"5");
                    }
                });
                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"6");
                    }
                });
                button5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"7");
                    }
                });
                button6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"8");
                    }
                });
                button7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"9");
                    }
                });

            }
        });

        buttonOver6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonSelect6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameSelect = new JFrame("选择关卡");
                frameSelect.setResizable(false);
                frameSelect.setVisible(true);
                frameSelect.setBounds(550,150,img10.getIconWidth()+10,img10.getIconHeight());
                JPanel  panelSelect = new JPanel() {
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(img10.getImage(), 0, 0, null);
                    }
                };
                frameSelect.add(panelSelect);
                panelSelect.setLayout(null);

                JLabel labelSelect = new JLabel("选择关卡");
                labelSelect.setFont(new Font("华文彩云", Font.BOLD, 70));
                labelSelect.setBounds(300, 20, 300, 100);

                JButton button1 = new JButton("第一关");
                button1.setFont(new Font("华文行楷", Font.BOLD, 40));
                button1.setBackground(new Color(0, 0, 0));
                button1.setBounds(10, 150, 200, 50);
                button1.setOpaque(false);

                JButton button2 = new JButton("第二关");
                button2.setFont(new Font("华文行楷", Font.BOLD, 40));
                button2.setBackground(new Color(0, 0, 0));
                button2.setBounds(240, 150, 200, 50);
                button2.setOpaque(false);
                JButton button3 = new JButton("第三关");
                button3.setFont(new Font("华文行楷", Font.BOLD, 40));
                button3.setBounds(470, 150, 200, 50);
                button3.setBackground(new Color(0, 0, 0));
                button3.setOpaque(false);
                JButton button4 = new JButton("第四关");
                button4.setFont(new Font("华文行楷", Font.BOLD, 40));
                button4.setBounds(700, 150, 200, 50);
                button4.setBackground(new Color(0, 0, 0));
                button4.setOpaque(false);
                JButton button5 = new JButton("第五关");
                button5.setFont(new Font("华文行楷", Font.BOLD, 40));
                button5.setBounds(10, 250, 200, 50);
                button5.setBackground(new Color(0, 0, 0));
                button5.setOpaque(false);
                JButton button6 = new JButton("第六关");
                button6.setFont(new Font("华文行楷", Font.BOLD, 40));
                button6.setBounds(240, 250, 200, 50);
                button6.setBackground(new Color(0, 0, 0));
                button6.setOpaque(false);
                JButton button7 = new JButton("第七关");
                button7.setFont(new Font("华文行楷", Font.BOLD, 40));
                button7.setBounds(470, 250, 200, 50);
                button7.setBackground(new Color(0, 0, 0));
                button7.setOpaque(false);
                JButton buttonFuture = new JButton("敬请期待……");
                buttonFuture.setFont(new Font("华文行楷", Font.BOLD, 40));
                buttonFuture.setBounds(10, 350, 400, 50);
                buttonFuture.setBackground(new Color(0, 0, 0));
                buttonFuture.setOpaque(false);


                button1.setForeground(Color.WHITE);
                button2.setForeground(Color.WHITE);
                button3.setForeground(Color.WHITE);
                button4.setForeground(Color.WHITE);
                button5.setForeground(Color.WHITE);
                button6.setForeground(Color.WHITE);
                button7.setForeground(Color.WHITE);
                buttonFuture.setForeground(Color.WHITE);
                labelSelect.setForeground(Color.WHITE);

                panelSelect.add(labelSelect);
                panelSelect.add(button1);
                panelSelect.add(button2);
                panelSelect.add(button3);
                panelSelect.add(button4);
                panelSelect.add(button5);
                panelSelect.add(button6);
                panelSelect.add(button7);
                panelSelect.add(buttonFuture);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"3");
                    }
                });
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"4");
                    }
                });
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"5");
                    }
                });
                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"6");
                    }
                });
                button5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"7");
                    }
                });
                button6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"8");
                    }
                });
                button7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"9");
                    }
                });

            }
        });

        buttonOver7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonSelect7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameSelect = new JFrame("选择关卡");
                frameSelect.setVisible(true);
                frameSelect.setBounds(550,150,img10.getIconWidth()+10,img10.getIconHeight());
                frameSelect.setResizable(false);
                JPanel  panelSelect = new JPanel() {
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(img10.getImage(), 0, 0, null);
                    }
                };
                frameSelect.add(panelSelect);
                panelSelect.setLayout(null);

                JLabel labelSelect = new JLabel("选择关卡");
                labelSelect.setFont(new Font("华文彩云", Font.BOLD, 70));
                labelSelect.setBounds(300, 20, 300, 100);

                JButton button1 = new JButton("第一关");
                button1.setFont(new Font("华文行楷", Font.BOLD, 40));
                button1.setBackground(new Color(0, 0, 0));
                button1.setBounds(10, 150, 200, 50);
                button1.setOpaque(false);

                JButton button2 = new JButton("第二关");
                button2.setFont(new Font("华文行楷", Font.BOLD, 40));
                button2.setBackground(new Color(0, 0, 0));
                button2.setBounds(240, 150, 200, 50);
                button2.setOpaque(false);
                JButton button3 = new JButton("第三关");
                button3.setFont(new Font("华文行楷", Font.BOLD, 40));
                button3.setBounds(470, 150, 200, 50);
                button3.setBackground(new Color(0, 0, 0));
                button3.setOpaque(false);
                JButton button4 = new JButton("第四关");
                button4.setFont(new Font("华文行楷", Font.BOLD, 40));
                button4.setBounds(700, 150, 200, 50);
                button4.setBackground(new Color(0, 0, 0));
                button4.setOpaque(false);
                JButton button5 = new JButton("第五关");
                button5.setFont(new Font("华文行楷", Font.BOLD, 40));
                button5.setBounds(10, 250, 200, 50);
                button5.setBackground(new Color(0, 0, 0));
                button5.setOpaque(false);
                JButton button6 = new JButton("第六关");
                button6.setFont(new Font("华文行楷", Font.BOLD, 40));
                button6.setBounds(240, 250, 200, 50);
                button6.setBackground(new Color(0, 0, 0));
                button6.setOpaque(false);
                JButton button7 = new JButton("第七关");
                button7.setFont(new Font("华文行楷", Font.BOLD, 40));
                button7.setBounds(470, 250, 200, 50);
                button7.setBackground(new Color(0, 0, 0));
                button7.setOpaque(false);
                JButton buttonFuture = new JButton("敬请期待……");
                buttonFuture.setFont(new Font("华文行楷", Font.BOLD, 40));
                buttonFuture.setBounds(10, 350, 400, 50);
                buttonFuture.setBackground(new Color(0, 0, 0));
                buttonFuture.setOpaque(false);


                button1.setForeground(Color.WHITE);
                button2.setForeground(Color.WHITE);
                button3.setForeground(Color.WHITE);
                button4.setForeground(Color.WHITE);
                button5.setForeground(Color.WHITE);
                button6.setForeground(Color.WHITE);
                button7.setForeground(Color.WHITE);
                buttonFuture.setForeground(Color.WHITE);
                labelSelect.setForeground(Color.WHITE);

                panelSelect.add(labelSelect);
                panelSelect.add(button1);
                panelSelect.add(button2);
                panelSelect.add(button3);
                panelSelect.add(button4);
                panelSelect.add(button5);
                panelSelect.add(button6);
                panelSelect.add(button7);
                panelSelect.add(buttonFuture);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        radish1.setLive(true);
                        cardLayout.show(cardPanel,"3");
                    }
                });
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"4");
                    }
                });
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"5");
                    }
                });
                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"6");
                    }
                });
                button5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"7");
                    }
                });
                button6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"8");
                    }
                });
                button7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel,"9");
                    }
                });

            }
        });

    /////////     按钮



        panel1 = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img3.getImage(), 0, 0, null);
                if (radish1.getLive()) {
                    radish1.draw(g);
                    for (int i = num1;i<monster1List.size();i++){
                        monster1List.get(i).draw(g);
                        monster1List.get(i).hitRadish(radish1);
                    };
                }
                else {
                    cardLayout.show(cardPanel,"2");
                    rePlay();
                }
                g.setFont(new Font("微软雅黑",Font.BOLD,15));
                g.drawString("金钱:"+money.getMoney(),0,30);
                g.drawString("第"+round+"波",0,60);
                g.drawString("生命:"+radish1.getLife(),225,800);;
                for (int m = 0 ;m<tower1s.size();m++) {
                    if (tower1s.get(m).isLive()) {
                        tower1s.get(m).draw(g);
                    }
                }
                for (int n = 0 ;n<bullet1s1.size();n++){
                    Bullet1 bullet1 =bullet1s1.get(n);
                    bullet1.draw(g);
                    bullet1.hitMonster(monster1List,money);
                }
                for (int n = 0 ;n<bullet1s2.size();n++){
                    Bullet1 bullet1 =bullet1s2.get(n);
                    bullet1.draw(g);
                    bullet1.hitMonster(monster1List,money);
                }
                for (int n = 0 ;n<bullet1s3.size();n++){
                    Bullet1 bullet1 =bullet1s3.get(n);
                    bullet1.draw(g);
                    bullet1.hitMonster(monster1List,money);
                }
                for (int n = 0 ;n<bullet1s4.size();n++){
                    Bullet1 bullet1 =bullet1s4.get(n);
                    bullet1.draw(g);
                    bullet1.hitMonster(monster1List,money);
                }

            }
        };
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "3");
                monster1List.removeAll(monster1List);
                radish1.setLife(10);
                radish1.setLive(true);
                i1=1;
                round=1;
            }
        });
        //关卡1

        panel2 = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img4.getImage(), 0, 0, null);
            }
        };
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "4");
            }
        });
        //关卡2

        panel3 = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img5.getImage(), 0, 0, null);
            }
        };

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"5");
            }
        });
        //关卡3

        panel4 = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img6.getImage(), 0, 0, null);
            }
        };
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "6");
            }
        });
        //关卡4

        panel5 = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img7.getImage(), 0, 0, null);
            }
        };
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "7");

            }
        });
        //关卡5

        panel6 = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img8.getImage(), 0, 0, null);
            }
        };
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "8");



            }
        });
        //关卡6

        panel7 = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawString("",100,100);
                g.drawImage(img9.getImage(), 0, 0, null);
            }
        };
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "9");

            }
        });
        //关卡7
        new Thread(new PaintThread()).start();
        thread1.start();
        panel1.setLayout(new GridLayout(15,20));
        panel2.setLayout(new GridLayout(20,29));
        panel3.setLayout(null);
        panel4.setLayout(null);
        panel5.setLayout(null);
        panel6.setLayout(null);
        panel7.setLayout(null);

//设置地图上建造炮台的按钮
        //给各个按钮设置了相应的命令码 使其能够分辨是哪个按钮
        for (int i = 0;i<scene1.length;i++) {
            for (int j = 0; j < 20; j++) {
                JButton sceneBt1 = new JButton();
                sceneBt1.setBackground(new Color(0,0,0));
                sceneBt1.setOpaque(false);
                sceneBt1.setActionCommand("0");
                jButtons1.add(sceneBt1);
                sceneBt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (sceneBt1.getActionCommand()=="0"){
                            JFrame frameBuild = new JFrame("建设");
                            frameBuild.setVisible(true);
                            frameBuild.setLayout(new FlowLayout(FlowLayout.CENTER));
                            frameBuild.setResizable(false);
                            frameBuild.setLocation(700, 400);
                            JButton buttonPt1 = new JButton(imgPt1);
                            buttonPt1.setSize(50, 50);
                            JButton buttonPt2 = new JButton(imgPt2);
                            buttonPt2.setSize(50, 50);
                            JButton buttonPt3 = new JButton(imgPt3);
                            buttonPt3.setSize(50, 50);
                            JButton buttonPt4 = new JButton(imgPt4);
                            buttonPt4.setSize(50, 50);
                            frameBuild.add(buttonPt1);
                            frameBuild.add(buttonPt2);
                            frameBuild.add(buttonPt3);
                            frameBuild.add(buttonPt4);
                            frameBuild.pack();

                            buttonPt1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e1) {
                                    frameBuild.dispose();
                                    if (money.getMoney() -500 >= 0) {
                                        money.setMoney(money.getMoney() - 500);
                                        tower1 = new Tower1(sceneBt1.getX(), sceneBt1.getY(), imgPt1, imgBt1, 1);
                                        tower1.setLive(true);
                                        tower1s.add(tower1);
                                        index = jButtons1.indexOf(sceneBt1);
                                        tower1.btNum = index ;
                                        sceneBt1.setActionCommand("1");
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                new JLabel("<html><h2><font color='red'><font size=\"25\"> 金钱不足以建造 请确认</font></h2></html>"),
                                                "余额不足",
                                                JOptionPane.INFORMATION_MESSAGE);

                                    }
                                }
                            });
                            buttonPt2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e1) {
                                    frameBuild.dispose();
                                    if (money.getMoney() - 500 >= 0) {
                                        money.setMoney(money.getMoney() -500);
                                        tower1 = new Tower1(sceneBt1.getX(), sceneBt1.getY(), imgPt2, imgBt2, 2);
                                        tower1.setLive(true);
                                        tower1s.add(tower1);
                                        index = jButtons1.indexOf(sceneBt1);
                                        tower1.btNum = index ;
                                        sceneBt1.setActionCommand("2");
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                new JLabel("<html><h2><font color='red'><font size=\"25\"> 金钱不足以建造 请确认</font></h2></html>"),
                                                "余额不足",
                                                JOptionPane.INFORMATION_MESSAGE);

                                    }
                                }
                            });
                            buttonPt3.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e1) {
                                    frameBuild.dispose();
                                    if (money.getMoney() - 1000 >= 0) {
                                        money.setMoney(money.getMoney() - 1000);
                                        tower1 = new Tower1(sceneBt1.getX(), sceneBt1.getY(), imgPt3, imgBt3, 3);
                                        tower1.setLive(true);
                                        tower1s.add(tower1);
                                        index = jButtons1.indexOf(sceneBt1);
                                        tower1.btNum = index ;
                                        sceneBt1.setActionCommand("3");
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                new JLabel("<html><h2><font color='red'><font size=\"25\"> 金钱不足以建造 请确认</font></h2></html>"),
                                                "余额不足",
                                                JOptionPane.INFORMATION_MESSAGE);

                                    }
                                }
                            });
                            buttonPt4.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e1) {
                                    frameBuild.dispose();
                                    if (money.getMoney() - 500 >= 0) {
                                        money.setMoney(money.getMoney() - 500);
                                        tower1 = new Tower1(sceneBt1.getX(), sceneBt1.getY(), imgPt4, imgBt4, 4);
                                        tower1.setLive(true);
                                        tower1s.add(tower1);
                                        index = jButtons1.indexOf(sceneBt1);
                                        tower1.btNum = index ;
                                        sceneBt1.setActionCommand("4");
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                new JLabel("<html><h2><font color='red'><font size=\"25\"> 金钱不足以建造 请确认</font></h2></html>"),
                                                "余额不足",
                                                JOptionPane.INFORMATION_MESSAGE);

                                    }
                                }
                            });

                        }
                        else if (sceneBt1.getActionCommand() == "1") {
                            JFrame frameBuild = new JFrame("升级");
                            frameBuild.setVisible(true);
                            frameBuild.setLayout(new FlowLayout(FlowLayout.CENTER));
                            frameBuild.setResizable(false);
                            frameBuild.setLocation(700, 400);
                            JButton buttonPt1 = new JButton(imgPt1);
                            JButton buttonPt5 = new JButton("销毁");
                            buttonPt5.setSize(50, 50);
                            buttonPt5.setFont(new Font("微软雅黑",Font.BOLD,30));

                            frameBuild.add(buttonPt1);
                            frameBuild.add(buttonPt5);
                            frameBuild.pack();

                            buttonPt1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e1) {
                                    frameBuild.dispose();
                                    if (money.getMoney() -500 >= 0) {
                                        money.setMoney(money.getMoney() - 500);
                                        for (int i = 0;i<tower1s.size();i++){
                                            if (tower1s.get(i).btNum==jButtons1.indexOf(sceneBt1)){
                                                tower1s.get(i).setN(8);
                                            }
                                        }
                                        sceneBt1.setActionCommand("5");
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                new JLabel("<html><h2><font color='red'><font size=\"25\"> 金钱不足以升级 请确认</font></h2></html>"),
                                                "余额不足",
                                                JOptionPane.INFORMATION_MESSAGE);

                                    }
                                }
                            });
                            buttonPt5.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                   for (int i = 0;i<tower1s.size();i++){
                                       if (tower1s.get(i).btNum==jButtons1.indexOf(sceneBt1)){
                                           tower1s.remove(tower1s.get(i));
                                       }
                                   }
                                   money.setMoney(money.getMoney()+250);
                                    sceneBt1.setActionCommand("0");
                                    frameBuild.dispose();
                                }
                            });

                        }
                        else if (sceneBt1.getActionCommand() == "4") {
                            JFrame frameBuild = new JFrame("升级");
                            frameBuild.setVisible(true);
                            frameBuild.setLayout(new FlowLayout(FlowLayout.CENTER));
                            frameBuild.setResizable(false);
                            frameBuild.setLocation(700, 400);
                            JButton buttonPt4 = new JButton(imgPt4);
                            JButton buttonPt5 = new JButton("销毁");
                            buttonPt5.setSize(50, 50);
                            buttonPt5.setFont(new Font("微软雅黑",Font.BOLD,30));

                            frameBuild.add(buttonPt4);
                            frameBuild.add(buttonPt5);
                            frameBuild.pack();

                            buttonPt4.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e1) {
                                    frameBuild.dispose();
                                    if (money.getMoney() -500 >= 0) {
                                        money.setMoney(money.getMoney() - 500);
                                        for (int i = 0;i<tower1s.size();i++){
                                            if (tower1s.get(i).btNum==jButtons1.indexOf(sceneBt1)){
                                                tower1s.get(i).setN(8);
                                            }
                                        }
                                        sceneBt1.setActionCommand("5");
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                new JLabel("<html><h2><font color='red'><font size=\"25\"> 金钱不足以升级 请确认</font></h2></html>"),
                                                "余额不足",
                                                JOptionPane.INFORMATION_MESSAGE);

                                    }
                                }
                            });
                            buttonPt5.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    for (int i = 0;i<tower1s.size();i++){
                                        if (tower1s.get(i).btNum==jButtons1.indexOf(sceneBt1)){
                                            tower1s.remove(tower1s.get(i));
                                        }
                                    }
                                    money.setMoney(money.getMoney()+250);
                                    sceneBt1.setActionCommand("0");
                                    frameBuild.dispose();
                                }
                            });

                        }
                        else if (sceneBt1.getActionCommand() == "2") {
                            JFrame frameBuild = new JFrame("升级");
                            frameBuild.setVisible(true);
                            frameBuild.setLayout(new FlowLayout(FlowLayout.CENTER));
                            frameBuild.setResizable(false);
                            frameBuild.setLocation(700, 400);
                            JButton buttonPt2 = new JButton(imgPt2);
                            JButton buttonPt5 = new JButton("销毁");
                            buttonPt5.setSize(50, 50);
                            buttonPt5.setFont(new Font("微软雅黑",Font.BOLD,30));

                            frameBuild.add(buttonPt2);
                            frameBuild.add(buttonPt5);
                            frameBuild.pack();

                            buttonPt2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e1) {
                                    frameBuild.dispose();
                                    if (money.getMoney() -500 >= 0) {
                                        money.setMoney(money.getMoney() - 500);
                                        for (int i = 0;i<tower1s.size();i++){
                                            if (tower1s.get(i).btNum==jButtons1.indexOf(sceneBt1)){
                                                tower1s.get(i).setN(8);
                                            }
                                        }
                                        sceneBt1.setActionCommand("5");
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                new JLabel("<html><h2><font color='red'><font size=\"25\"> 金钱不足以升级 请确认</font></h2></html>"),
                                                "余额不足",
                                                JOptionPane.INFORMATION_MESSAGE);

                                    }
                                }
                            });
                            buttonPt5.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    for (int i = 0;i<tower1s.size();i++){
                                        if (tower1s.get(i).btNum==jButtons1.indexOf(sceneBt1)){
                                            tower1s.remove(tower1s.get(i));
                                        }
                                    }
                                    money.setMoney(money.getMoney()+250);
                                    sceneBt1.setActionCommand("0");
                                    frameBuild.dispose();
                                }
                            });

                        }
                        else if (sceneBt1.getActionCommand() == "3") {
                            JFrame frameBuild = new JFrame("升级");
                            frameBuild.setVisible(true);
                            frameBuild.setLayout(new FlowLayout(FlowLayout.CENTER));
                            frameBuild.setResizable(false);
                            frameBuild.setLocation(700, 400);
                            JButton buttonPt3 = new JButton(imgPt3);
                            JButton buttonPt5 = new JButton("销毁");
                            buttonPt5.setSize(50, 50);
                            buttonPt5.setFont(new Font("微软雅黑",Font.BOLD,30));

                            frameBuild.add(buttonPt3);
                            frameBuild.add(buttonPt5);
                            frameBuild.pack();

                            buttonPt3.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e1) {
                                    frameBuild.dispose();
                                    if (money.getMoney() -1000 >= 0) {
                                        money.setMoney(money.getMoney() - 1000);
                                        for (int i = 0;i<tower1s.size();i++){
                                            if (tower1s.get(i).btNum==jButtons1.indexOf(sceneBt1)){
                                                tower1s.get(i).setN(8);
                                            }
                                        }
                                        sceneBt1.setActionCommand("6");
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                new JLabel("<html><h2><font color='red'><font size=\"25\"> 金钱不足以升级 请确认</font></h2></html>"),
                                                "余额不足",
                                                JOptionPane.INFORMATION_MESSAGE);

                                    }
                                }
                            });
                            buttonPt5.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    for (int i = 0;i<tower1s.size();i++){
                                        if (tower1s.get(i).btNum==jButtons1.indexOf(sceneBt1)){
                                            tower1s.remove(tower1s.get(i));
                                        }
                                    }
                                    money.setMoney(money.getMoney()+500);
                                    sceneBt1.setActionCommand("0");
                                    frameBuild.dispose();
                                }
                            });


                        }
                        else if (sceneBt1.getActionCommand()=="5"){
                            JFrame frameBuild = new JFrame("升级");
                            frameBuild.setVisible(true);
                            frameBuild.setLayout(new FlowLayout(FlowLayout.CENTER));
                            frameBuild.setResizable(false);
                            frameBuild.setLocation(700, 400);
                            JButton buttonPt5 = new JButton("销毁");
                            buttonPt5.setSize(50, 50);
                            JLabel label = new JLabel("已经升级至最高级");
                            label.setFont(new Font("微软雅黑",Font.BOLD,30));
                            buttonPt5.setFont(new Font("微软雅黑",Font.BOLD,30));
                            frameBuild.add(label);
                            frameBuild.add(buttonPt5);
                            frameBuild.pack();
                            buttonPt5.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    for (int i = 0;i<tower1s.size();i++){
                                        if (tower1s.get(i).btNum==jButtons1.indexOf(sceneBt1)){
                                            tower1s.remove(tower1s.get(i));
                                        }
                                    }
                                    money.setMoney(money.getMoney()+500);
                                    sceneBt1.setActionCommand("0");
                                    frameBuild.dispose();
                                }
                            });


                        }
                        else {
                            JFrame frameBuild = new JFrame("升级");
                            frameBuild.setVisible(true);
                            frameBuild.setLayout(new FlowLayout(FlowLayout.CENTER));
                            frameBuild.setResizable(false);
                            frameBuild.setLocation(700, 400);
                            JButton buttonPt5 = new JButton("销毁");
                            buttonPt5.setSize(50, 50);
                            JLabel label = new JLabel("已经升级至最高级");
                            label.setFont(new Font("微软雅黑",Font.BOLD,30));
                            buttonPt5.setFont(new Font("微软雅黑",Font.BOLD,30));
                            frameBuild.add(label);
                            frameBuild.add(buttonPt5);
                            frameBuild.pack();
                            buttonPt5.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    for (int i = 0;i<tower1s.size();i++){
                                        if (tower1s.get(i).btNum==jButtons1.indexOf(sceneBt1)){
                                            tower1s.remove(tower1s.get(i));
                                        }
                                    }
                                    money.setMoney(money.getMoney()+1000);
                                    sceneBt1.setActionCommand("0");
                                    frameBuild.dispose();
                                }
                            });
                        }

                    }
                });
                if (scene1[i][j]==0) {
                    panel1.add(sceneBt1);
                }
                else {

                    if (i==0&&j==19){
                        JButton buttonExit = new JButton("退出");
                        buttonExit.setBackground(new Color(0,0,0));
                        buttonExit.setFont(new Font("微软雅黑",Font.BOLD,15));
                        buttonExit.setOpaque(false);
                        panel1.add(buttonExit);
                        buttonExit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.exit(0);
                            }
                        });
                    }
                    else if (i==0&&j==18){
                        JButton buttonSet = new JButton("设置");
                        buttonSet.setBackground(new Color(0,0,0));
                        buttonSet.setFont(new Font("微软雅黑",Font.BOLD,15));
                        buttonSet.setOpaque(false);
                        panel1.add(buttonSet);
                        buttonSet.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame frameSet = new JFrame("设置");
                                frameSet.setVisible(true);
                                frameSet.setLayout(new FlowLayout(FlowLayout.CENTER));
                                frameSet.setResizable(false);
                                frameSet.setLocation(700, 400);
                                JButton buttonTime1 = new JButton("X1");
                                buttonTime1.setFont(new Font("微软雅黑",Font.BOLD,20));
                                buttonTime1.setSize(50, 50);
                                JButton buttonTime2 = new JButton("X2");
                                buttonTime2.setSize(50, 50);
                                buttonTime2.setFont(new Font("微软雅黑",Font.BOLD,20));
                                JButton buttonSelect = new JButton("选择关卡");
                                buttonSelect.setSize(50, 50);
                                buttonSelect.setFont(new Font("微软雅黑",Font.BOLD,20));
                                frameSet.add(buttonTime1);
                                frameSet.add(buttonTime2);
                                frameSet.add(buttonSelect);
                                frameSet.pack();
                                buttonTime1.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        setTime(1000);
                                        frameSet.dispose();
                                    }
                                });
                                buttonTime2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        setTime(500);
                                        frameSet.dispose();
                                    }
                                });
                                buttonSelect.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        cardLayout.show(cardPanel,"2");
                                        i1=31;
                                        monster1List.removeAll(monster1List);
                                        round = 1;
                                        bullet1s1.removeAll(bullet1s1);
                                        bullet1s2.removeAll(bullet1s2);
                                        bullet1s3.removeAll(bullet1s3);
                                        bullet1s4.removeAll(bullet1s4);
                                        tower1s.removeAll(tower1s);
                                        money.setMoney(1000);
                                        frameSet.dispose();

                                    }
                                });
                            }
                        });
                    }
                    else {
                        JPanel panelBt1 = new JPanel();
                        panelBt1.setOpaque(false);
                        panel1.add(panelBt1);
                    }
                }
            }
        }

        for (int i = 0;i<scene2.length;i++) {
            for (int j = 0; j < 29; j++) {
                JButton sceneBt2 = new JButton();
                sceneBt2.setBackground(new Color(0,0,0));
                sceneBt2.setOpaque(false);
                if (scene2[i][j]==0) {
                    panel2.add(sceneBt2);
                }
                else {
                    JPanel panelBt2 = new JPanel();
                    panelBt2.setOpaque(false);
                    panel2.add(panelBt2);
                }
            }
        }
        panel3.add(labelMoney3);
        panel3.add(labelRound3);
        panel3.add(buttonSelect3);
        panel3.add(buttonOver3);

        panel4.add(labelMoney4);
        panel4.add(labelRound4);
        panel4.add(buttonSelect4);
        panel4.add(buttonOver4);

        panel5.add(labelMoney5);
        panel5.add(labelRound5);
        panel5.add(buttonSelect5);
        panel5.add(buttonOver5);

        panel6.add(labelMoney6);
        panel6.add(labelRound6);
        panel6.add(buttonSelect6);
        panel6.add(buttonOver6);
        panel7.add(labelMoney7);
        panel7.add(labelRound7);
        panel7.add(buttonSelect7);
        panel7.add(buttonOver7);

        cardPanel.add("3", panel1);
        cardPanel.add("4", panel2);
        cardPanel.add("5", panel3);
        cardPanel.add("6", panel4);
        cardPanel.add("7", panel5);
        cardPanel.add("8", panel6);
        cardPanel.add("9", panel7);

    }

    public static void main(String[] args) {
        RadishClient franeRadish = new RadishClient();
        franeRadish.launchFrame();
    }

//bgm
    private class PaintThread implements Runnable {

        @Override
        public void run() {
            File file ;
            URI uri;
            URL url;
            try{
                file = new File("彩虹糖的梦 - 金娃娃.wav");
//            file文件直接转url会有bug(编译出错)故应file.toURI().toURL().
                uri = file.toURI();
                url = uri.toURL();
//            单曲循环
                AudioClip a;
                a = Applet.newAudioClip(url);
                a.loop();

            }catch (Exception e){
                e.printStackTrace();
            }
            while (true) {
                panelMain.repaint();
                panelSelect.repaint();
                panel1.repaint();
                panel2.repaint();
                panel3.repaint();
                panel4.repaint();
                panel5.repaint();
                panel6.repaint();
                panel7.repaint();

                try{
                    Thread.sleep(50);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    //关卡 各波 boss
    private class Time1Thread implements Runnable {

        @Override
        public void run() {
             radish1 = new Radish(155,600,new ImageIcon("C:\\Users\\zk\\RadishProtect\\萝卜1.gif"));
            while (true) {
                if (round == 1) {
                        if (i1 <= 15) {
                            monster1List.add(new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster1.png")));
                            i1++;
                        }
                        else if (i1 <= 30) {
                            ;
                            monster1List.add(new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster2.png")));
                            i1++;
                        }
                    else {
                            if (monster1List.size() == 0) {
                                round++;
                                i1 = 1;
                            }
                        }

                }

                if (round == 2) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster2.png"));
                        monster1.setLife(500);
                        monster1.setLifeMst(500);
                        monster1List.add(monster1);

                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster3.png"));
                        monster1.setLife(500);
                        monster1.setLifeMst(500);
                        monster1List.add(monster1);
                        i1++;
                    }
                   else {
                        if (monster1List.size() == 0) {
                            round++;
                            i1 = 1;
                        }
                    }
                }

                if (round == 3) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster1.png"));
                        monster1.setLife(500);
                        monster1.setLifeMst(500);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster3.png"));
                        monster1.setLife(500);
                        monster1.setLifeMst(500);
                        monster1List.add(monster1);

                        i1++;
                    }
                    else {
                        if (monster1List.size() == 0) {
                            round++;
                            i1 = 1;
                        }
                    }
                }

                if (round == 4) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster2.png"));
                        monster1.setLife(500);
                        monster1.setLifeMst(500);
                        monster1List.add(monster1);
                        i1++;
                    } else if (i1 <= 30) {
                        ;  Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster4.png"));
                        monster1.setLife(500);
                        monster1.setLifeMst(500);
                        monster1List.add(monster1);
                        i1++;
                    } else {
                        if (monster1List.size() == 0) {
                            round++;
                            i1 = 1;
                        }
                    }
                }

                if (round == 5) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster3.png"));
                        monster1.setLife(500);
                        monster1.setLifeMst(500);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster4.png"));
                        monster1.setLife(500);
                        monster1.setLifeMst(500);
                        monster1List.add(monster1);

                        i1++;
                    }
                    else {
                        if (monster1List.size() == 0) {
                            round++;
                            i1 = 1;
                        }
                    }
                }

                if (round == 6) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster1.png"));
                        monster1.setLife(1000);
                        monster1.setLifeMst(1000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster5.png"));
                        monster1.setLife(1000);
                        monster1.setLifeMst(1000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else {
                        if (monster1List.size() == 0) {
                            round++;
                            i1 = 1;
                        }
                    }
                }

                if (round == 7) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster2.png"));
                        monster1.setLife(1000);
                        monster1.setLifeMst(1000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster5.png"));
                        monster1.setLife(1000);
                        monster1.setLifeMst(1000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    if (monster1List.size() ==0){
                        round ++;
                        i1 = 1;
                    }
                }

                if (round == 8) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster3.png"));
                        monster1.setLife(1000);
                        monster1.setLifeMst(1000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster5.png"));
                        monster1.setLife(1000);
                        monster1.setLifeMst(1000);
                        monster1List.add(monster1);
                        i1++;
                    }
                   else {
                        if (monster1List.size() == 0) {
                            round++;
                            i1 = 1;
                        }
                    }
                }

                if (round == 9) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster4.png"));
                        monster1.setLife(1000);
                        monster1.setLifeMst(1000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster5.png"));
                        monster1.setLife(1000);
                        monster1.setLifeMst(1000);
                        monster1List.add(monster1);
                        i1++;
                    }
                   else{ if (monster1List.size() ==0) {
                        round++;
                        i1 = 1;

                    }
                    }
                }
                if (round == 10) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster6.png"));
                        monster1.setLife(1000);
                        monster1.setLifeMst(1000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster7.png"));
                        monster1.setLife(1000);
                        monster1.setLifeMst(1000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else{ if (monster1List.size() ==0) {
                        round++;
                        i1 = 1;

                    }
                    }
                }
                if (round == 11) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster5.png"));
                        monster1.setLife(1500);
                        monster1.setLifeMst(1500);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster7.png"));
                        monster1.setLife(1500);
                        monster1.setLifeMst(1500);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else{ if (monster1List.size() ==0) {
                        round++;
                        i1 = 1;

                    }
                    }
                }
                if (round == 12) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster4.png"));
                        monster1.setLife(2000);
                        monster1.setLifeMst(2000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster7.png"));
                        monster1.setLife(2000);
                        monster1.setLifeMst(2000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else{ if (monster1List.size() ==0) {
                        round++;
                        i1 = 1;

                    }
                    }
                }
                if (round == 13) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster4.png"));
                        monster1.setLife(3000);
                        monster1.setLifeMst(3000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster6.png"));
                        monster1.setLife(3000);
                        monster1.setLifeMst(3000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else{ if (monster1List.size() ==0) {
                        round++;
                        i1 = 1;

                    }
                    }
                }
                if (round == 14) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster5.png"));
                        monster1.setLife(4000);
                        monster1.setLifeMst(4000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster6.png"));
                        monster1.setLife(4000);
                        monster1.setLifeMst(4000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else{ if (monster1List.size() ==0) {
                        round++;
                        i1 = 1;
                    }
                    }
                }
                if (round == 15) {

                    if (i1 <= 15) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster3.png"));
                        monster1.setLife(5000);
                        monster1.setLifeMst(5000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else if (i1 <= 30) {
                        Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\Monster6.png"));
                        monster1.setLife(5000);
                        monster1.setLifeMst(5000);
                        monster1List.add(monster1);
                        i1++;
                    }
                    else {
                        if (monster1List.size() == 0) {
                            round++;
                            i1 = 1;
                        }
                    }



                }

                if (round == 16) {

                   if (i1<=30) {
                       Monster1 monster1 = new Monster1(0, 100, new ImageIcon("C:\\Users\\zk\\RadishProtect\\BOSS.png"));
                       monster1.setLife(15000);
                       monster1.setLifeMst(15000);
                       monster1List.add(monster1);
                       i1=31;
                   }

                    else{
                       if (monster1List.size() ==0) {
                        JOptionPane.showMessageDialog(null,
                                new JLabel("<html><h2><font color='red'><font size=\"25\"> 通关啦</font></h2></html>"),
                                "666",
                                JOptionPane.INFORMATION_MESSAGE);
                        cardLayout.show(cardPanel,"2");
                        round=1;
                        i1=1;
                    }
                    }
                }


                try{
                    Thread.sleep(getTime());
                    System.out.println(getTime());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
