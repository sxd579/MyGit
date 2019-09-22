import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class Bullet1 {
    private int x ,y,Width,Height;
    public static final int X_Speed = 20 ,Y_Speed = 20;
    private boolean live = true;
    private ImageIcon img = null;
    enum Direction {L,LU,U,RU,R,RD,D,LD,STOP};
    private Direction dir = Direction.STOP;
    Random random = new Random();
    Direction [] dirs = Direction.values();
    RadishClient rc ;
    int ptNum ;
    private static  int m =1 ;
    private int old_X,old_Y;


    private ImageIcon imgBt301 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹3.gif");
    private ImageIcon imgBt302 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹302.png");
    private ImageIcon imgBt303 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹303.png");
    private ImageIcon imgBt304 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹304.png");
    private ImageIcon imgBt305 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹305.png");
    private ImageIcon imgBt3 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹305.png");
    private int through = 1;

    public  Bullet1 (int x, int y,int Width,int Height,ImageIcon img,int ptNum,Direction dir){
        this.x = x;
        this.y = y ;
        this.Width = Width ;
        this.Height = Height;
        this.img = img ;
        this.dir =dir ;
        this.ptNum = ptNum;
        old_X = x;
        old_Y = y;
        System.out.println(1);


    }

    public void draw (Graphics g){
        if (ptNum == 1) {
            if (!live) {
                rc.bullet1s1.remove(this);
            } else {
                g.drawImage(img.getImage(), x + Width / 2, y + Height / 2, null);
                move();
            }
        }
        if (ptNum == 2) {
            if (!live) {
                rc.bullet1s2.remove(this);
            } else {
                g.drawImage(img.getImage(), x + Width / 2, y + Height / 2, null);
                move();
            }
        }
        if (ptNum == 3) {
            if (!live) {
                rc.bullet1s3.remove(this);
            } else {
                img = imgBt301;
                g.drawImage(imgBt301.getImage(), old_X + Width / 2 - imgBt301.getIconWidth() / 3 - 20, old_Y + Height / 2 - imgBt301.getIconHeight() / 3 - 30, null);
            }
        }
        if (ptNum == 4) {
            if (!live) {
                rc.bullet1s4.remove(this);
            } else {
                g.drawImage(img.getImage(), x + Width / 2, y + Height / 2 , null);
                move();
            }
        }

        }



    public void move () {
        switch (dir) {
            case R:
                x += X_Speed;
                break;
            case RU:
                x += (int)(X_Speed/Math.sqrt(2));
                y -= (int)(Y_Speed/Math.sqrt(2));
                break;
            case U:
                y -= Y_Speed;
                break;
            case LU:
                x -= (int)(X_Speed/Math.sqrt(2));
                y -= (int)(Y_Speed/Math.sqrt(2));
                break;
            case L:
                x -= X_Speed;
                break;
            case LD:
                x -= (int)(X_Speed/Math.sqrt(2));
                y += (int)(Y_Speed/Math.sqrt(2));
                break;
            case D:
                y += Y_Speed;
                break;
            case RD:
                x += (int)(X_Speed/Math.sqrt(2));
                y += (int)(Y_Speed/Math.sqrt(2));
                break;
            case STOP:
                break;
        }
        if (x < -100 || y < -100 || x > 1500 || y > 1000) {
            live = false;
            if (ptNum ==1) {
                rc.bullet1s1.remove(this);
            }
            if (ptNum ==2) {
                rc.bullet1s2.remove(this);
            }
            if (ptNum ==3) {
                rc.bullet1s3.remove(this);
            }
            if (ptNum ==4) {
                rc.bullet1s4.remove(this);
            }
        }
    }
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
}

    public Rectangle getRect() {
        return new Rectangle(x, y, img.getIconWidth(), img.getIconHeight());
    }

    public  void hitMonster(List<Monster1> monster1s,Money money){
        for (int i = 0; i<monster1s.size();i++){
            if (monster1s.get(i).isLive()&&this.live&&this.getRect().intersects(monster1s.get(i).getRect())){

                if (ptNum ==1) {
                    if (through==1) {
                        monster1s.get(i).setLife(monster1s.get(i).getLife() - 20);
                        through++;
                    }
                }
                if (ptNum ==2) {
                    monster1s.get(i).setLife(monster1s.get(i).getLife()-20);
                    rc.bullet1s2.remove(this);
                }
                if (ptNum ==3) {
                    monster1s.get(i).setLife(monster1s.get(i).getLife() - 20);
                    rc.bullet1s3.remove(this);
                }
                if (ptNum ==4) {
                    monster1s.get(i).setLife(monster1s.get(i).getLife()-20);
                    rc.bullet1s4.remove(this);
                }
               if (monster1s.get(i).getLife()<=0){
                   monster1s.get(i).setLive(false);
                   monster1s.remove(monster1s.get(i));
                   money.setMoney(money.getMoney()+50);
               }

            }
        }

    }






}
