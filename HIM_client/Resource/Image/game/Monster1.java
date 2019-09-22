import javax.swing.*;
import java.awt.*;

public class Monster1 {

    private ImageIcon imageIcon = null;
    private int i =1;
    private int x;
    private int j = 1;
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

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

    private int y;
    private boolean live = true ;
    private double life = 100;
    private double lifeMst = 100;
    public double getLifeMst() {
        return lifeMst;
    }

    public void setLifeMst(int lifeMst) {
        this.lifeMst = lifeMst;
    }
    RadishClient rc = new RadishClient();
    public boolean isLive() {
        return live;
    }

    public double getLife() {
        return life;
    }
    public void setLive(boolean live) {
        this.live = live;
    }
    public void setLife(double life) {
        this.life = life;
    }
    public Rectangle getRect() {
        return new Rectangle(x, y, imageIcon.getIconWidth()-20, imageIcon.getIconHeight()-40);
    }

    public Monster1(int x , int y, ImageIcon imageIcon){
        this.x = x ;
        this.y = y ;
        this.imageIcon = imageIcon;

    }

    public void draw(Graphics g){
        if (!(rc.getRound()==16)) {
            g.drawImage(imageIcon.getImage(), x - 10, y - 10, null);
            g.setColor(Color.red);
            g.drawRect(x + 20, y - 10, imageIcon.getIconWidth()/3, 15);
            g.fillRect(x + 20, y - 8, (int) ((imageIcon.getIconWidth()/3) * (life / lifeMst)), 13);
            move();
        }
        else {
            g.drawImage(imageIcon.getImage(), x-50, y - 100, null);
            g.setColor(Color.red);
            g.drawRect(x+30, y - 110, imageIcon.getIconWidth() /3, 15);
            g.fillRect(x+30, y - 108, (int) ((imageIcon.getIconWidth() /3) * (life / lifeMst)), 13);
            move();
        }


    }

    public void move (){
        if (i<=153){
        x += 5;
            if (j <= 10 ){
                y -= 4 ;
                j++;
            }
            else if (j<=20){
                y +=4;
                j++;
            }
            else if (j<=30){
                j++;
            }
            else {
                j= 1;
            }
        i++;
        }
       else if (i<=178){
            y+=5;
            i++;
        }
        else if (i<=248){
            if (j <= 10 ){
                y -= 4 ;
                j++;
            }
            else if (j<=20){
                y +=4;
                j++;
            }
            else if (j<=30){
                j++;
            }
            else {
                j= 1;
            }
           x+=5;
           i++;
        }
        else if (i<=295){
            y+=5;
            i++;
        }
        else if (i<=365){
            if (j <= 10 ){
                y -= 4 ;
                j++;
            }
            else if (j<=20){
                y +=4;
                j++;
            }
            else if (j<=30){
                j++;
            }
            else {
                j= 1;
            }
            x-=5;
            i++;
        }
        else if (i<=415){
            y+=5;
            i++;
        }
        else if (i<=520){
            if (j <= 10 ){
                y -= 4 ;
                j++;
            }
            else if (j<=20){
                y +=4;
                j++;
            }
            else if (j<=30){
                j++;
            }
            else {
                j= 1;
            }
            x-=5;
            i++;
        }

    }

    public void hitRadish (Radish radish){
        if (!(rc.getRound()==16)) {
            if (this.isLive() && this.getRect().intersects(radish.getRect())) {
                this.setLive(false);
                rc.monster1List.remove(this);
                radish.setLife(radish.getLife() - 1);
            }
        }
        else {
            if (this.isLive() && this.getRect().intersects(radish.getRect())) {
                this.setLive(false);
                rc.monster1List.remove(this);
                radish.setLife(radish.getLife() - 5);
            }
        }

    }


}
