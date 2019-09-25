import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;

public class Radish {
    private int x ,y ;
    private int life = 10 ;
    private boolean live = true ;
    private ImageIcon img = null ;
    RadishClient rc ;
    public  Radish (int x , int y, ImageIcon img){
        this.x = x ;
        this.y = y;
        this.img = img;

    }
    public  void draw (Graphics g){
        g.drawImage(img.getImage(),x,y,null);
        if (this.getLife()<=0){
            this.setLive(false);
        }
        if (!this.getLive()){
            JOptionPane.showMessageDialog(null,
                    new JLabel("<html><h2><font color='red'><font size=\"25\"> 失败，请自行选择是否重新游戏</font></h2></html>"),
                    "失败",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public  int getLife (){
        return life;
    }

    public void setLife(int life){
        this.life = life;
    }
    public boolean getLive (){
        return live;
    }
    public void setLive (boolean live){
        this.live  = live;
    }
    public Rectangle getRect() {
        return new Rectangle(x, y, img.getIconWidth(), img.getIconHeight());
    }

}
