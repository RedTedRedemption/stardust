package slythr;

/**
 * Created by teddy on 5/23/17.
 */
public class SplashThread implements Runnable {

    Thread thread;

    public SplashThread(){
        thread = new Thread(this, "splash thread");
    }

    public void run(){
        System.out.println("showing splash...");
        slythr.SplashScreen splash = new slythr.SplashScreen(3000);
        splash.showSplashAndExit();
        System.out.println("splash done");
    }

}
