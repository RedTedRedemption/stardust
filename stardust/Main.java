package stardust; /**
 * Created by teddy on 3/3/17.
 */

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.sql.Time;

import javax.swing.JFrame;
import javax.swing.JPanel;
import slythr.*;





public class Main {


    public static void main(String[] args) {



        System.out.println("Initializing Driver...");

        JFrame frame = new JFrame("asteroids");
        frame.setTitle("Asteroids: Powered by Slythr");
        frame.setSize(700, 700);
        frame.setLocation(100, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(new Splash(frame));
//        frame.setVisible(true);
//        try {
//            Thread.sleep(300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        frame.setVisible(false);
        frame.setContentPane(new MainMenuPane(frame, new GlobalGamestate()));
        frame.setVisible(true);

    }
}
