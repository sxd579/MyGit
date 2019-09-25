import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Bird {
    private int x;
    private int y;
    //    private  int speed_x = 4;
    private  int speed_y = 8;
    private  int jump = 40;
    private  boolean alive = true;
    private World world;
    private  int score = 0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getSpeed_y() {
        return speed_y;
    }

    public void setSpeed_y(int speed_y) {
        this.speed_y = speed_y;
    }
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public Bird(World world){
       x = 50;
       y = 250;
       this.world = world;
    }
    public void draw(Graphics g){
        g.drawImage(config.bird.getImage(), x, y, null);
        if (alive){
            move();
        }

    }
    void  move(){
//        x +=speed_x;
        y +=speed_y;

    }
    void gameover(){
        if (y<20||y>464){
            setAlive(false);
        }
        if (hit_remora()){
            setAlive(false);
        }
    }
    boolean hit_remora(){
        for (int i =0;i<=world.remoras.size()-1;i++) {

            if (this.x>= world.remoras.get(i).getX()&&this.x<= world.remoras.get(i).getX()+40
                    &&((this.y>= world.remoras.get(i).getY()+3&&this.y<=world.remoras.get(i).getY()+317)||(this.y>= world.remoras.get(i).getY()+403&&this.y<=world.remoras.get(i).getY()+717)||(this.y>= world.remoras.get(i).getY()-397&&this.y<=world.remoras.get(i).getY()-83))) {
                    return true;
            }
        }
        return false;
    }

    void goal(){
        for (int i =0;i<=world.remoras.size()-1;i++) {

            if (this.x>= world.remoras.get(i).getX()+40&&!world.remoras.get(i).isAcross()) {
                score++;
                world.remoras.get(i).setAcross(true);
            }
        }
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE){
            this.y-=jump ;
        }

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE){
            speed_y = 6 ;
        }

    }


}
