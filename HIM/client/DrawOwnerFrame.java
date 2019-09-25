package com.HIM.client;

import com.HIM.common.Bean_picture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class DrawOwnerFrame {
    static JScrollPane scrollPaneList;
    JFrame frameDraw;
    int drawType = 1;
    int color = 5;
    static MyList listPlayers;
    Graphics g;
    static Vector<String> plays  = new Vector <>();
    public DrawOwnerFrame(HIMUI miniQQ,int qq,int fqq,YouDrawMeGuessComm drawer) {
        frameDraw = new JFrame();
        frameDraw.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
        listPlayers = new MyList();
//        frameDraw. addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                int a = JOptionPane.showConfirmDialog(frameDraw, "确定关闭吗？", "温馨提示",
//                        JOptionPane.YES_NO_OPTION);
//                System.out.println(a);
//                if (a == 1) {
//                    frameDraw.dispose();  //关闭
//                    DrawFrameManager.removeDraw(fqq);
//                    //发送一个退出信息
//                }
//            }
//        });
        frameDraw.setResizable(false);
        frameDraw.setBounds(500, 300, 1000, 538);
        frameDraw.setVisible(true);
        frameDraw.setTitle("你画我猜");
        frameDraw.setLayout(null);
        JPanel panelList = new JPanel();
        panelList.setBounds(800, 0, 200, 500);
        panelList.setBackground(Color.white);
        panelList.setLayout(new BorderLayout());
        listPlayers.setFont(new Font("微软雅黑", Font.BOLD, 20));
        scrollPaneList = new JScrollPane(listPlayers);
        panelList.add(scrollPaneList);
        frameDraw.add(panelList);

        JPanel panelButton = new JPanel();
        panelButton.setBounds(0, 0, 200, 500);
        panelButton.setLayout(new GridLayout(5, 2));
        JPanel panelDraw = new JPanel();
        panelDraw.setLayout(null);
        panelDraw.setBackground(Color.WHITE);
        panelDraw.setBounds(200, 0, 600, 538);
        JTextField textField = new JTextField(10);
        textField.setBounds(200, 450, 100, 40);
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textField.getText().length() > 9) {
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
        textField.setFont(new Font("微软雅黑", Font.BOLD, 20));
        JButton buttonOK = new JButton("完成");
        buttonOK.setFont(new Font("微软雅黑", Font.BOLD, 20));
        buttonOK.setBounds(300, 450, 100, 40);
        buttonOK.setBackground(Color.WHITE);
        panelDraw.add(textField);
        panelDraw.add(buttonOK);
        JButton buttonPencil = new JButton(config.Pencil);
        buttonPencil.setActionCommand("1");
        JButton buttonEraser = new JButton(config.Eraser);
        buttonEraser.setActionCommand("2");
        JButton buttonOval = new JButton(config.Oval);
        buttonOval.setActionCommand("3");
        JButton buttonRectangle = new JButton(config.Rectangle);
        buttonRectangle.setActionCommand("4");
        JButton color_Black = new JButton();
        color_Black.setActionCommand("5");
        color_Black.setBackground(Color.BLACK);
        JButton color_White = new JButton();
        color_White.setActionCommand("6");
        color_White.setBackground(Color.WHITE);
        JButton color_Red = new JButton();
        color_Red.setActionCommand("7");
        color_Red.setBackground(Color.RED);
        JButton color_Blue = new JButton();
        color_Blue.setActionCommand("8");
        color_Blue.setBackground(Color.BLUE);
        JButton color_Green = new JButton();
        color_Green.setActionCommand("9");
        color_Green.setBackground(Color.GREEN);
        JButton color_Yellow = new JButton();
        color_Yellow.setActionCommand("10");
        color_Yellow.setBackground(Color.YELLOW);
        panelButton.add(buttonPencil);
        panelButton.add(buttonEraser);
        panelButton.add(buttonOval);
        panelButton.add(buttonRectangle);
        panelButton.add(color_Black);
        panelButton.add(color_White);
        panelButton.add(color_Red);
        panelButton.add(color_Blue);
        panelButton.add(color_Green);
        panelButton.add(color_Yellow);
        frameDraw.add(panelButton);
        frameDraw.add(panelDraw);
        g = panelDraw.getGraphics();
        ;
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //send
                Bean_picture picture = new Bean_picture(100, 100, color, drawType);
                drawer.offerPictrue(picture);
            }
        });
        ActionListener listenerButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() == "1") {
                    drawType = 1;
                }
                if (e.getActionCommand() == "2") {
                    drawType = 2;
                }
                if (e.getActionCommand() == "3") {
                    drawType = 3;
                }
                if (e.getActionCommand() == "4") {
                    drawType = 4;
                }
                if (e.getActionCommand() == "5") {
                    g.setColor(Color.BLACK);
                    color = 5;
                }
                if (e.getActionCommand() == "6") {
                    g.setColor(Color.WHITE);
                    color = 6;
                }
                if (e.getActionCommand() == "7") {
                    g.setColor(Color.RED);
                    color = 7;
                }
                if (e.getActionCommand() == "8") {
                    g.setColor(Color.BLUE);
                    color = 8;
                }
                if (e.getActionCommand() == "9") {
                    g.setColor(Color.GREEN);
                    color = 9;
                }
                if (e.getActionCommand() == "10") {
                    g.setColor(Color.YELLOW);
                    color = 10;
                }

            }
        };
        buttonPencil.addActionListener(listenerButton);
        buttonEraser.addActionListener(listenerButton);
        buttonOval.addActionListener(listenerButton);
        buttonRectangle.addActionListener(listenerButton);
        color_Black.addActionListener(listenerButton);
        color_White.addActionListener(listenerButton);
        color_Red.addActionListener(listenerButton);
        color_Blue.addActionListener(listenerButton);
        color_Green.addActionListener(listenerButton);
        color_Yellow.addActionListener(listenerButton);
        panelDraw.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drawType==1||drawType==2) {
                    Point p = e.getPoint();
                    Bean_picture picture = new Bean_picture(e.getX(), e.getY(), color, drawType);
                    draw(picture);
                    drawer.offerPictrue(picture);
                }
                    //想过两种处理方式，创建一个对象之后，不断改变数值，或者第采取不断创建对象的方法， 后期根据速度再调试

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        panelDraw.addMouseListener(new MouseListener() {
            int x1, y1;

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                x1 = e.getX();
                y1 = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (drawType==3||drawType==4) {
                    int x2 = e.getX();
                    int y2 = e.getY();
                    Bean_picture picture = new Bean_picture(x1, y1, x2, y2, color, drawType);
                    draw(picture);
                    drawer.offerPictrue(picture);
                }



            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    public void draw(Bean_picture picture)
    {
        drawType = picture.getDrawType();
        if (drawType == 1) {
            g.fillOval(picture.getX(), picture.getY(), 10, 10);
        }
        else if (drawType == 2) {
            g.setColor(Color.WHITE);
            g.fillOval(picture.getX(), picture.getY(),  10, 10);
        }
        else if (drawType == 3) {
            g.drawOval(picture.getX(), picture.getY(), Math.abs(picture.getX() - picture.getX1()), Math.abs(picture.getY() - picture.getY1()));
        }
        else if (drawType == 4) {
            g.drawRect(picture.getX(), picture.getY(), Math.abs(picture.getX() - picture.getX1()), Math.abs(picture.getY() - picture.getY1()));
        }


    }

    

}
