import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FlappyBird extends JFrame {
    static World world = new World();
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Flappy Bird");
        frame.setSize(288*3,512);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(world);
        frame.addKeyListener(new keyMonitor());
//       World world = new World();
    }
    private static class keyMonitor extends KeyAdapter {
        public void keyPressed(KeyEvent e){ world.myBird.keyPressed(e); }
        public void keyReleased(KeyEvent e){
            world.myBird.keyReleased(e);
        }

    }
}
