package stardust; /**
 * Created by teddy on 3/3/17.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.nio.file.Paths;


public class Main {

    static BufferStrategy buffer_strategy;
    static boolean evar_nosplash = false;
    static boolean evar_nolog = false;
    static boolean evar_drawfps = false;


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
                    System.out.println("GODMODE is on");
                }
                if (arg.equals("-nolog")) { //todo - make a better way of doing this
                    evar_nolog = true;
                }
                if (arg.equals("-drawfps")) {
                    evar_drawfps = true;
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

            System.out.print("gathering system data...");
            GlobalGamestate.getOS();
            System.out.println("done");
            System.out.print("This program's cwd ");
            System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
            System.out.print("Initializing Driver...");






            if (!evar_nosplash) {
                System.out.print("showing splash...");
                slythr.SplashScreen splash = new slythr.SplashScreen(3000);
                splash.showSplashAndExit();
                System.out.println("done");

            } else {
                System.out.print("skipping splash...");
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
