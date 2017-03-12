package slythr;

import javax.swing.*;
import java.awt.*;

/**
 * Created by teddy on 3/6/17.
 */
public class Splash extends JFrame {

    Graphics g;
    Primitive title;
    Primitive templogo;

    public Splash(Frame frame){
        super();
        title = new Text("Powered by Slythr", 48);
        title.setpos(frame.getHeight() / 2 + 20, frame.getWidth() / 2 - 150);
        templogo = new Rect();
        templogo.setColor(238, 255, 0);
        templogo.centerx(frame.getWidth() / 2);
        templogo.centery(frame.getHeight() / 2);

    }

    public void paintComponent(Graphics g){
        templogo.draw(g);
        title.draw(g);
    }


}