package com.HIM.client;

import com.HIM.common.Bean_picture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawPlayerFrame {
    public Graphics g;
    public JPanel panelSuppose;
   public DrawPlayerFrame(){
       JFrame frameSuppose = new JFrame();
       frameSuppose.setIconImage(Toolkit.getDefaultToolkit().getImage(config.logo));
       frameSuppose.setTitle("你画我猜");
       int drawType=1,color=5;
       frameSuppose.setVisible(true);
       frameSuppose.setBounds(500,300,600,538);
       this.panelSuppose = new JPanel();
       panelSuppose.setLayout(null);
       panelSuppose.setBackground(Color.RED);
       System.out.println(this);
       System.out.println(this.panelSuppose);
       frameSuppose.add(this.panelSuppose);
       JTextField textField = new JTextField(10);
       textField.setBounds(200,450,100,40);
       textField.setFont(new Font("微软雅黑",Font.BOLD,20));
       textField.addKeyListener(new KeyListener() {

           public void keyTyped(KeyEvent e) {
               if (textField.getText().length()>9){
                   e.consume();
               }
           }


           public void keyPressed(KeyEvent e) {

           }


           public void keyReleased(KeyEvent e) {

           }
       });
       JButton buttonOK =new JButton("完成");
       buttonOK.setFont(new Font("微软雅黑",Font.BOLD,20));
       buttonOK.setBounds(300,450,100,40);
       buttonOK.setBackground(Color.WHITE);
       panelSuppose.add(textField);
       panelSuppose.add(buttonOK);
       buttonOK.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //比对答案
           }
       });

        g  = panelSuppose.getGraphics();
   }
    public void draw(Bean_picture picture)
    {
        if (picture.getColor() ==5){
            this.g.setColor(Color.BLACK);

        }
        if (picture.getColor() ==6){
            this.g.setColor(Color.WHITE);

        }
        if (picture.getColor() ==7){
           this.g.setColor(Color.RED);

        }
        if (picture.getColor() ==8){

            this.g.setColor(Color.BLUE);

        }
        if (picture.getColor() ==9){
            this.g.setColor(Color.GREEN);

        }
        if (picture.getColor() ==10){

            this.g.setColor(Color.YELLOW);


        }
        int drawType = picture.getDrawType();
        if (drawType == 1) {
            System.out.println(1);
            g.fillOval(200, 200, 10, 10);
            System.out.println(g);
            g.fillOval(picture.getX(), picture.getY(), 10, 10);
            System.out.println(2);
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

    public static void main(String[] args) {
        new DrawPlayerFrame().draw(new Bean_picture(320 ,91,5,1));
    }
}

