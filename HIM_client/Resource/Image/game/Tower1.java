import javafx.scene.shape.Circle;
import org.omg.CORBA.IMP_LIMIT;

import java.util.List;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class Tower1 {
    int btNum =0;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    int n = 10;
    int m = 10;
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    int speed = 37;
    private ImageIcon img1 = null;
    private ImageIcon img2 = null ;
    private ImageIcon imgb1 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台101.png") ;
    private ImageIcon imgb2 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台102.png") ;
    private ImageIcon imgb3 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台103.png") ;
    private ImageIcon imgb4 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台104.png") ;
    private ImageIcon imgb5 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台105.png") ;
    private ImageIcon imgb6 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台106.png") ;
    private ImageIcon imgb7 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台107.png") ;
    private ImageIcon imgb8 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台108.png") ;
    private ImageIcon imgb9 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台201.png") ;
    private ImageIcon imgb10 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台202.png") ;
    private ImageIcon imgb11 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台203.png") ;
    private ImageIcon imgb12 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台204.png") ;
    private ImageIcon imgb13 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台205.png") ;
    private ImageIcon imgb14 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台206.png") ;
    private ImageIcon imgb15 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台207.png") ;
    private ImageIcon imgb16 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台208.png") ;
    private ImageIcon imgb17 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\炮台4.png") ;

    private ImageIcon imgp1 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹101.png") ;
    private ImageIcon imgp2 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹102.png") ;
    private ImageIcon imgp3 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹103.png") ;
    private ImageIcon imgp4 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹104.png") ;
    private ImageIcon imgp5 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹105.png") ;
    private ImageIcon imgp6 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹106.png") ;
    private ImageIcon imgp7 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹107.png") ;
    private ImageIcon imgp8 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹108.png") ;
    private ImageIcon imgp9 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹201.png") ;
    private ImageIcon imgp10 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹202.png") ;
    private ImageIcon imgp11 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹203.png") ;
    private ImageIcon imgp12 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹204.png") ;
    private ImageIcon imgp13 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹205.png") ;
    private ImageIcon imgp14 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹206.png") ;
    private ImageIcon imgp15 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹207.png") ;
    private ImageIcon imgp16 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹208.png") ;
    private ImageIcon imgp17 = new ImageIcon("C:\\Users\\zk\\RadishProtect\\子弹4.png") ;

    Bullet1.Direction dir = Bullet1.Direction.STOP;
    private int x;
    private int y;
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    private  boolean live = false ;
    RadishClient rc;
    Random random = new Random();
    int ptNum ;
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
    public Tower1(int x ,int y , ImageIcon img1 ,ImageIcon img2,int ptNum){
        this.x = x ;
        this.y  = y ;
        this.img1 = img1;
        this.img2 = img2;
        this.ptNum = ptNum;
    }

    public void  draw (Graphics g) {
        if (ptNum == 1) {

            for (int i = 0; i < rc.monster1List.size(); i++) {
                if (this.getX() + img1.getIconWidth() / 2 == rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() > rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    //上
                    dir = Bullet1.Direction.U;
                    img2 = imgp1;
                    g.drawImage(imgb1.getImage(), x + 10, y, null);
                }
                if (this.getX() + img1.getIconWidth() / 2 == rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() < rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.D;
                    img2 = imgp5;
                    g.drawImage(imgb5.getImage(), x + 10, y, null);
                    //下
                }
                if (this.getX() + img1.getIconWidth() / 2 > rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() == rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.L;
                    img2 = imgp7;
                    g.drawImage(imgb7.getImage(), x + 10, y, null);
                    //左
                }
                if (this.getX() + img1.getIconWidth() / 2 < rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() == rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.R;
                    img2 = imgp3;
                    g.drawImage(imgb3.getImage(), x + 10, y, null);
                    //右
                }
                if (this.getX() + img1.getIconWidth() / 2 > rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() > rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.LU;
                    img2 = imgp8;
                    g.drawImage(imgb8.getImage(), x + 10, y, null);
                    //左上
                }
                if (this.getX() + img1.getIconWidth() / 2 > rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() < rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.LD;
                    img2 = imgp6;
                    g.drawImage(imgb6.getImage(), x + 10, y, null);
                    //左下
                }
                if (this.getX() + img1.getIconWidth() / 2 < rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() > rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.RU;
                    img2 = imgp2;
                    g.drawImage(imgb2.getImage(), x + 10, y, null);
                    //右上
                }
                if (this.getX() + img1.getIconWidth() / 2 < rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() < rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.RD;
                    img2 = imgb4;
                    g.drawImage(imgb4.getImage(), x + 10, y, null);
                    //右下
                }
                if (m == n){
                    if (this.isCollisionWithCircle(this.getX() + img1.getIconWidth() / 2, this.getY() + img2.getIconHeight(), rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2, rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2, 300, rc.monster1List.get(i).getImageIcon().getIconHeight() / 2 + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2)) {
                        fire();
                        m=1;
                        break;
                    }
                }
                else {
                    m++;
                }
            }

        }
            if (ptNum==2){
            for (int i = 0; i < rc.monster1List.size(); i++) {
                if (this.getX() + img1.getIconWidth() / 2 == rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() > rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    //上
                    dir = Bullet1.Direction.U;
                    img2 = imgp9;
                    g.drawImage(imgb9.getImage(), x + 10, y, null);
                }
                if (this.getX() + img1.getIconWidth() / 2 == rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() < rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.D;
                    img2 = imgp13;
                    g.drawImage(imgb13.getImage(), x + 10, y, null);
                    //下
                }
                if (this.getX() + img1.getIconWidth() / 2 > rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() == rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.L;
                    img2 = imgp15;
                    g.drawImage(imgb15.getImage(), x + 10, y, null);
                    //左
                }
                if (this.getX() + img1.getIconWidth() / 2 < rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() == rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.R;
                    img2 = imgp11;
                    g.drawImage(imgb11.getImage(), x + 10, y, null);
                    //右
                }
                if (this.getX() + img1.getIconWidth() / 2 > rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() > rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.LU;
                    img2 = imgp16;
                    g.drawImage(imgb16.getImage(), x + 10, y, null);
                    //左上
                }
                if (this.getX() + img1.getIconWidth() / 2 > rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() < rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.LD;
                    img2 = imgp14;
                    g.drawImage(imgb14.getImage(), x + 10, y, null);
                    //左下
                }
                if (this.getX() + img1.getIconWidth() / 2 < rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() > rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.RU;
                    img2 = imgp10;
                    g.drawImage(imgb10.getImage(), x + 10, y, null);
                    //右上
                }
                if (this.getX() + img1.getIconWidth() / 2 < rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() < rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.RD;
                    img2 = imgp12;
                    g.drawImage(imgb12.getImage(), x + 10, y, null);
                    //右下
                }
                if (m == n){
                    if (this.isCollisionWithCircle(this.getX() + img1.getIconWidth() / 2, this.getY() + img2.getIconHeight(), rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2, rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2, 300, rc.monster1List.get(i).getImageIcon().getIconHeight() / 2 + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2)) {
                        fire();
                        m=1;
                        break;
                    }
                }
                else {
                    m++;
                }
            }

        }
              if (ptNum ==4){
                for (int i = 0; i < rc.monster1List.size(); i++) {
                if (this.getX() + img1.getIconWidth() / 2 == rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() > rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    //上
                    dir = Bullet1.Direction.U;
                    g.drawImage(img1.getImage(), x + 10, y, null);
                }
                if (this.getX() + img1.getIconWidth() / 2 == rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() < rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.D;
                    g.drawImage(img1.getImage(), x + 10, y, null);
                    //下
                }
                if (this.getX() + img1.getIconWidth() / 2 > rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() == rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.L;
                    g.drawImage(img1.getImage(), x + 10, y, null);
                    //左
                }
                if (this.getX() + img1.getIconWidth() / 2 < rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() == rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.R;
                    g.drawImage(img1.getImage(), x + 10, y, null);
                    //右
                }
                if (this.getX() + img1.getIconWidth() / 2 > rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() > rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.LU;
                    g.drawImage(img1.getImage(), x + 10, y, null);
                    //左上
                }
                if (this.getX() + img1.getIconWidth() / 2 > rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() < rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.LD;
                    g.drawImage(img1.getImage(), x + 10, y, null);
                    //左下
                }
                if (this.getX() + img1.getIconWidth() / 2 < rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() > rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.RU;
                    g.drawImage(img1.getImage(), x + 10, y, null);
                    //右上
                }
                if (this.getX() + img1.getIconWidth() / 2 < rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2 && this.getY() + img2.getIconHeight() < rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2) {
                    dir = Bullet1.Direction.RD;
                    g.drawImage(img1.getImage(), x + 10, y, null);
                    //右下
                }
                    if (m == n){
                        if (this.isCollisionWithCircle(this.getX() + img1.getIconWidth() / 2, this.getY() + img2.getIconHeight(), rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2, rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2, 300, rc.monster1List.get(i).getImageIcon().getIconHeight() / 2 + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2)) {
                            fire();
                            m=1;
                            break;
                        }
                    }
                    else {
                        m++;
                    }
            }

        }
        if (ptNum==3){
            g.drawImage(img1.getImage(), x + 10, y, null);
            for (int i = 0; i < rc.monster1List.size(); i++) {
                if (m == n){
                    if (this.isCollisionWithCircle(this.getX() + img1.getIconWidth() / 2, this.getY() + img2.getIconHeight(), rc.monster1List.get(i).getX() + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2, rc.monster1List.get(i).getY() + rc.monster1List.get(i).getImageIcon().getIconHeight() / 2, 300, rc.monster1List.get(i).getImageIcon().getIconHeight() / 2 + rc.monster1List.get(i).getImageIcon().getIconWidth() / 2)) {
                        fire();
                        m=1;
                        break;
                    }
                }
                else {
                    m++;
                }
            }
            }
        }


    public void fire (){
        Bullet1 bullet1 =new Bullet1(x-img1.getIconWidth()/2,y-img1.getIconHeight()/2,img1.getIconWidth(),img1.getIconHeight(),img2,ptNum,dir);
        if (ptNum == 1) {
                rc.bullet1s1.add(bullet1);
            }
        if (ptNum == 2) {
            rc.bullet1s2.add(bullet1);
        }
        if (ptNum == 3) {
            rc.bullet1s3.add(bullet1);
        }
        if (ptNum == 4) {
            rc.bullet1s4.add(bullet1);
        }

    }

    private boolean isCollisionWithCircle(int x1, int y1, int x2, int y2,
            int r1,int r2) {
// Math.sqrt:开平方  
// Math.pow(double x, double y): X的Y次方  
             if (Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2))<=r1+r2){
// 如果两圆的圆心距小于或等于两圆半径和则认为发生碰撞  
             return true;
}
           return false;
}



}
