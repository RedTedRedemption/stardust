package stardust;


import slythr.SplashScreen;
import slythr.SplashThread;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Stardust Main class. Runs initialization code, creates the window and starts the game.
 */

public class Main {

    static BufferStrategy buffer_strategy;
    static boolean evar_nosplash = false;
    static boolean evar_nolog = false;
    static boolean evar_drawfps = false;
    public static boolean evar_drawboundingboxes;
    public static Thread splashthread;
    public static SplashThread splash_direct_access;


    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        try {
            System.out.print("launching with arguments: ");
            for (String arg : args) {
                System.out.print(arg + ", ");
                if (arg.equals("-nosplash")) {
                    evar_nosplash = true;
                }
                if (arg.equals("-god")) {
                    GlobalGamestate.statevar_alwaysgod = true;
                }
                if (arg.equals("-nolog")) { //todo - make a better way of doing this
                    evar_nolog = true;
                }
                if (arg.equals("-drawfps")) {
                    evar_drawfps = true;
                }
                if (arg.equals("-drawboundingboxes")) {
                    evar_drawboundingboxes = true;
                }


                if (evar_nolog) {
                    //System.out.println("output is going to console");
                } else {
//                    PrintStream lout = new PrintStream(new FileOutputStream("log.txt"));
//                    System.setOut(lout);
                    //System.out.println("output is going to file");
                }
            }
            System.out.println();
            if (GlobalGamestate.statevar_alwaysgod){
                System.out.println("GODMODE is on");
            }

            System.out.print("gathering system data...");
            slythr.SplashScreen.status.setText("gathering system data");
            GlobalGamestate.getOS();
            System.out.println("done");
            System.out.print("This program's cwd is ");
            System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
            System.out.print("Initializing Driver...");
            SplashScreen.status.setText("initializing driver");






            if (!evar_nosplash) {
                splash_direct_access = new SplashThread();
                splashthread = new Thread(splash_direct_access, "Splash thread");
                splashthread.start();
                Thread.sleep(2000);

            } else {
                System.out.print("skipping splash...");
            }
            JFrame frame = new JFrame("Stardust");
            frame.setIconImage(new ImageIcon("./images/cutscene_back_1.png").getImage());


            frame.setTitle("Stardust: Powered by Slythr");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(false);
            frame.setResizable(false);

            System.out.println("done");
            System.out.println("spawning pane");
            SplashScreen.status.setText("spawning pane");
            frame.pack();
            frame.setSize(900, 900);
            frame.setLocation(100, 50);
//            frame.createBufferStrategy(3);

            //buffer_strategy = frame.getBufferStrategy();
            frame.setContentPane(new MainPane(frame, new GlobalGamestate()));
            frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new Frame(), e.toString());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
