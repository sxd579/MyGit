import java.awt.*;

public class Remora {
    private  int x ;
    private  int y ;
    private  int type;
    private  int speed;
    private  boolean alive ;
    private World world;
    private boolean across = false;
    public boolean isAcross() {
        return across;
    }

    public void setAcross(boolean across) {
        this.across = across;
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

    public Remora(int x ,int y,int type,World world){
        this.speed = 8;
        this.x = x;
        this.y = y;
        this.type = type;
        alive =true;
        this.world =world;
//        System.out.println("x"+x);
//        System.out.println("y"+y);
//        System.out.println(type);

    }
   public   void  draw(Graphics g){
        if (alive) {
            if (type ==0) {
                g.drawImage(config.remora1.getImage(), x, y, null);
//                System.out.println(1111);
                g.drawImage(config.remora3.getImage(), x, y+400, null);
                move();
            }
            if (type ==1) {
                g.drawImage(config.remora2.getImage(), x, y, null);
                g.drawImage(config.remora4.getImage(), x, y+400, null);
//                System.out.println(2222);
                move();
            }
            if (type ==2) {
                g.drawImage(config.remora1.getImage(), x, y-400, null);
                g.drawImage(config.remora3.getImage(), x, y, null);
//                System.out.println(3333);
                move();
            }
            if (type ==3) {
                g.drawImage(config.remora2.getImage(), x, y-400, null);
                g.drawImage(config.remora4.getImage(), x, y, null);
//                System.out.println(4444);
                move();
            }
            dead();
        }

    }

    void move(){
           x -= speed;
    }
    void dead(){
        if (x <= -52 ){
            alive = false;
            world.remoras.remove(this);
            world.create_one_remoras();
        }
    }

}
