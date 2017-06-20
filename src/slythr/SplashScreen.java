package slythr;

/**
 * Created by teddy on 4/28/17.
 */

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
    private int duration;

    public static JLabel watermark = new JLabel
            ("Stardust: Powered by Slythr", JLabel.CENTER);
    public static JLabel status = new JLabel("", JLabel.LEFT);
    public static JLabel copyright = new JLabel("Copyright 2017 Theodore Herzfeld. All rights reserved.", JLabel.RIGHT);

    public SplashScreen(int d) {
        duration = d;
    }


    public void showSplash() {
        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.white);

        // Set the window's bounds, centering the window
        int width = 650;
        int height = 230;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Build the splash screen

        watermark.setFont(new Font("Sans-Serif", Font.BOLD, 36));
        status.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
        copyright.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
        content.add(watermark, BorderLayout.CENTER);
        content.add(status, BorderLayout.SOUTH);
        content.add(copyright, BorderLayout.NORTH);
        Color oraRed = new Color(0, 0, 200, 255);
        content.setBorder(BorderFactory.createLineBorder(oraRed, 10));

        // Display it
        setVisible(true);




        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            //pass;
        }
    }

    public void showSplashAndExit() {
        showSplash();

    }

    public void status(String status){
        SplashScreen.status.setText(status);
    }

    public void setInvisible(){
        setVisible(false);
    }
}