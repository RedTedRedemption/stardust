package stardust; /**
 * Created by teddy on 3/3/17.
 */

import slythr.SplashScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.nio.file.Paths;


public class Main {

    static BufferStrategy buffer_strategy;
    static boolean evar_nosplash = false;


    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        try {

            System.out.print("gathering system data...");
            GlobalGamestate.getOS();
            System.out.println("done");
            System.out.print("This program's cwd is ");
            System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
            System.out.print("Initializing Driver...");

            System.out.print("showing splash...");
            System.out.println(args.toString());
            for (String arg : args){
                if (arg.equals("-nosplash")){
                    evar_nosplash = true;
                }
                if (arg.equals("-god")){
                    GlobalGamestate.statevar_god = true;
                }
            }
            if (!evar_nosplash) {
                SplashScreen splash = new SplashScreen(3000);
                splash.showSplashAndExit();
            }

            JFrame frame = new JFrame("Stardust");
            frame.setTitle("Stardust: Powered by Slythr");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(false);
            frame.setResizable(false);

            System.out.println("done");
            System.out.println("spawning pane");
            frame.pack();
            frame.setSize(900, 900);
            frame.setLocation(100, 50);
            frame.createBufferStrategy(3);

            buffer_strategy = frame.getBufferStrategy();
            frame.setContentPane(new MainPane(frame, new GlobalGamestate(), buffer_strategy));
            frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new Frame(), e.toString());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
