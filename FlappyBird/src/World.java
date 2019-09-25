import jdk.nashorn.internal.scripts.JO;
import sun.reflect.generics.tree.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class World extends JPanel {
    private int m = 3;
    private int x = 0;
    Bird myBird =new Bird(this);
    Image offScreenImage = null;
    Graphics gOffScreenImage;
        ArrayList<Remora> remoras = new ArrayList <Remora>();
    Random random_Type = new Random();
    Random random_Length = new Random();
    Random random_Location = new Random();
//    boolean play = false;
    public  World() {
        this.setLayout(new BorderLayout(1,1));
        initRemoras();
        new Thread(new PaintThread()).start();

    }

    public  void  paint (Graphics g){
        g.drawImage(config.background.getImage(), x-288, 0, null);
        g.drawImage(config.background.getImage(), x, 0, null);
        g.drawImage(config.background.getImage(), x+288, 0, null);
        g.drawImage(config.background.getImage(), x+288*2, 0, null);
        g.drawImage(config.background.getImage(), x+288*3, 0, null);
//    if (play) {
        if (m >= 0) {
            if (m == 3) {
                g.drawImage(config.ready_go.getImage(), 300, 200, null);
            }
            if (m == 2) {
                g.drawImage(config.s3.getImage(), 400, 200, null);
            }
            if (m == 1) {
                g.drawImage(config.s2.getImage(), 400, 200, null);
            }
            if (m == 0) {
                g.drawImage(config.s1.getImage(), 400, 200, null);
            }

        }
        else {
            myBird.goal();
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            g.drawString("Across Remoras: "+myBird.getScore(),30,50);
            myBird.gameover();
            if (!myBird.isAlive()) {
                myBird.setSpeed_y(0);
                remoras.clear();
                g.drawImage(config.gameover.getImage(), 300, 200, null);
            } else {
                myBird.draw(g);
                for (int i = 0; i <= remoras.size() - 1; i++) {
                    Remora r = remoras.get(i);
                    r.draw(g);
                }

            }
        }
//    }
    }

    public  void  update(Graphics g){
        if (offScreenImage == null){
            offScreenImage = this.createImage(288*3,512);
            gOffScreenImage = offScreenImage.getGraphics();
        }

        paint(gOffScreenImage);
        g.drawImage(offScreenImage,0,0,this);

    }

    public void initRemoras() {
        for (int i = 0;i<=2;i++){
            int location = 200+random_Location.nextInt(100)+250*i;
            int type = random_Type.nextInt(4);
            if (type<2){
                int length = - random_Length.nextInt(100);
                Remora remora =new Remora(location,length,type,this);
                remoras.add(remora);
            }
            else {
                int length = 200+random_Length.nextInt(100);
                Remora remora =new Remora(location,length,type,this);
                remoras.add(remora);
            }
    }

    }

    public  void  create_one_remoras(){
        int location = 864;
        int type = random_Type.nextInt(4);
        if (type<2){
            int length = - random_Length.nextInt(100);
            Remora remora =new Remora(location,length,type,this);
            remoras.add(remora);
        }
        else {
            int length = 200+random_Length.nextInt(100);
            Remora remora =new Remora(location,length,type,this);
            remoras.add(remora);
        }

    }

    private class  PaintThread implements Runnable{
        @Override
        public void run() {
            while (true) {
                repaint();
                //首先会调用update  然后update调用paint
                try{
                    if (m>=0){
                        Thread.sleep(1000);
                        m--;
                    }
                    else {
                        Thread.sleep(50);
                    }
                    x-=2;
                    if (x<=-288){
                        x = 0;
                    }
                    if (!myBird.isAlive()){
                        Thread.interrupted();
                    }
                }catch (Exception e){

                }
            }

        }

    }

    private class keyMonitor extends KeyAdapter{
        public void keyPressed(KeyEvent e){ myBird.keyPressed(e); }
        public void keyReleased(KeyEvent e){
            myBird.keyReleased(e);
        }

    }
}
